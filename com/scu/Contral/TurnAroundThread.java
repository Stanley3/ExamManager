package com.scu.Contral;

import com.scu.GlobelControl.ConfigManager;
import com.scu.Signal.*;
import com.scu.Utils.*;
import com.scu.Media.MediaPlay;
import com.scu.Model.ExamWindow;

public class TurnAroundThread extends ModuleThread {
	private int iState = 1;
	private boolean turnaround_30205 = false;
	private boolean turnaround_30206 = false;
	private double turnLightTime = 0.0D;
	private long lightstartTime = System.currentTimeMillis();
	private boolean turnleftlight = false;
	private boolean turnaround = false;
	private long lastTurnLight = 0;
	private int turnAngle = 0;
	private long lightOffStartTime = 0L;
	private int startAngle;
	private int endAngle;
	int angle;
	// 达标角度
	private static double MAX_ANGLE = ConfigManager.turnRound.getEnoughAngle();
	public static double RANGETIGGER = ConfigManager.turnRound
			.getTriggerDistance();

	public TurnAroundThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.jsfs = ConfigManager.turnRound.getTimeOrDistance();
		this.dRangeOut = ConfigManager.turnRound.getEndDistance();
		this.iTimeOut = ConfigManager.turnRound.getEndTime();
		MAX_ANGLE = ConfigManager.turnRound.getEnoughAngle();
		RANGETIGGER = ConfigManager.turnRound.getTriggerDistance();
	}

	public synchronized void run() {
		try {
			MediaPlay.getInstance().play("dt.wav");
			Thread curt = Thread.currentThread();
			this.isPause = false;
			while (this.runFlag) {
				try {
					Thread.sleep(200L);
					if (isOut())
						this.runFlag = false;
					else
						execute();
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}
		} catch (Exception localException) {
		}
		this.window.remove(this);
		if (!this.isBreakFlag) {
			judge();
			sendEndMessage(12);
		} else {
			sendEndMessage(12, 1);
		}
	}

	public void execute() {
		JudgeSignal carSignal = JudgeSignal.getInstance();
		this.curRange += Tools.getDistinceByOBDV(carSignal.gpsspeed, 200);
		if (this.startAngle == 0)
			this.startAngle = (int) carSignal.gpsangle;
		this.endAngle = (int) carSignal.gpsangle;
		 angle = this.endAngle - this.startAngle;
		if (angle > 180)
			angle -= 360;
		else if (angle < -180)
			angle += 360;
		this.turnAngle = Math.abs(angle);
		switch (this.iState) {
		case 1:
			if (this.turnAngle > 30) {
				this.iState = 3;
				this.runFlag = false;
			} else if (this.turnLightTime >= ConfigManager.commonConfig
					.getTurnLightWaitTime()) {
				this.iState = 2;
			} else if (carSignal.lamp_left) {
				this.turnleftlight = true;
				this.turnLightTime += 200;
				this.lightOffStartTime = 0L;
			} else {
				this.lightOffStartTime += 200;
				if (this.lightOffStartTime > ConfigManager.addClass
						.getLightOffTime())
					this.turnLightTime = 200;
				else {
					this.turnLightTime += 200;
				}

			}

			break;
		case 2:
			if (this.turnAngle <= MAX_ANGLE)
				break;
			this.iState = 3;
			this.runFlag = false;

			break;
		}
	}

	public void judge() {
		if (!ConfigManager.turnRound.isOpen())
			return;
		if (this.iState != 3) {
			sendMessage("30103", 12);
		}
		// Log.debug("turnleftlight=" + this.turnleftlight);
		if (!this.turnleftlight) {
			if (!this.turnaround_30205) {
				this.turnaround_30205 = true;
				sendMessage("30205", 12);
			}
		} else if ((this.turnLightTime < ConfigManager.addClass
				.getLightOffTime()) && (!this.turnaround_30206)) {
			this.turnaround_30206 = true;
			sendMessage("30206", 12);
		}
	}
}
