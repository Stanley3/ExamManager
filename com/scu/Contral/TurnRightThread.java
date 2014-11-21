package com.scu.Contral;

import com.scu.GlobelControl.ConfigManager;
import com.scu.Signal.*;
import com.scu.Utils.*;
import com.scu.Media.MediaPlay;
import com.scu.Model.ExamWindow;

public class TurnRightThread extends ModuleThread {
	private int iState = 1;
	private boolean turnright_30205 = false;
	private boolean turnright_30206 = false;
	private boolean drive_41605 = false;
	private boolean drive_41603 = false;
	private boolean isOpenHighLight = false;
	private boolean isNearLight = false;
	private boolean lastHightLight = false;
	private double turnLightTime = 0.0D;
	private boolean turnright = false;
	private int turnAngle = 0;
	private long lightOffStartTime = 0L;
	private int startAngle;
	private int endAngle;
	private boolean isBreak = false;
	private double curspeed = 0.0D;
	private static double MAX_ANGLE = ConfigManager.turnRight.getMinAngle();
	public static double RANGETIGGER = ConfigManager.turnRight
			.getTriggerDistance();

	public TurnRightThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.jsfs = ConfigManager.turnRight.getTimeOrDistance();
		this.dRangeOut = ConfigManager.turnRight.getEndDistance();
		this.iTimeOut = ConfigManager.turnRight.getEndTime();
		RANGETIGGER = ConfigManager.turnRight.getTriggerDistance();
		MAX_ANGLE = ConfigManager.turnRight.getMinAngle();
	}

	public synchronized void run() {
		try {
			MediaPlay.getInstance().play("lkyzw.wav");
			Thread curt = Thread.currentThread();
			// Log.debug("路口右转弯线程被唤醒");
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
			sendEndMessage(16);
		} else {
			sendEndMessage(16, 1);
		}
	}

	public void execute() {
		JudgeSignal carSignal = JudgeSignal.getInstance();
		this.curRange += Tools.getDistinceByOBDV(carSignal.gpsspeed, 200);
		this.curspeed = carSignal.gpsspeed;
		if (carSignal.signal_footbrake)
			this.isBreak = true;
		this.isOpenHighLight |= carSignal.lamp_highbeam;
		this.isNearLight |= carSignal.lamp_near;
		this.lastHightLight = carSignal.lamp_highbeam;
		if (this.startAngle == 0)
			this.startAngle = (int) carSignal.gpsangle;
		this.endAngle = (int) carSignal.gpsangle;
		int angle = this.endAngle - this.startAngle;
		if (angle > 180)
			angle -= 360;
		else if (angle < -180)
			angle += 360;
		this.turnAngle = angle;
		switch (this.iState) {
		case 1:
			if ((this.turnAngle > this.MAX_ANGLE) || (this.turnAngle < -this.MAX_ANGLE)) {
				this.iState = 3;
				if (!this.turnright) {
					if ((!ConfigManager.turnRight.isOpen())
							|| (this.turnright_30205))
						break;
					this.turnright_30205 = true;
					sendMessage("30205", 16);
				} else {
					if ((this.turnLightTime >= ConfigManager.commonConfig
							.getTurnLightWaitTime())
							|| (!ConfigManager.turnRight.isOpen())
							|| (this.turnright_30206))
						break;
					this.turnright_30206 = true;
					sendMessage("30206", 16);
				}

			} else if (this.turnLightTime >= ConfigManager.commonConfig
					.getTurnLightWaitTime()) {
				this.iState = 2;
			} else if (carSignal.lamp_right) {
				this.turnright = true;
				this.turnLightTime += 200;
				this.lightOffStartTime = 0L;
			} else {
				this.lightOffStartTime += 200;
				if (this.lightOffStartTime > ConfigManager.addClass
						.getLightOffTime())
					this.turnLightTime = 0.0D;
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
		if (!ConfigManager.turnRight.isOpen())
			return;
		if (this.iState != 3) {
			sendMessage("30103", 16);
		}
		if (this.curspeed > ConfigManager.turnRight.getMaxSpeed()) {
			sendMessage("40901", 16);
		} else if ((ConfigManager.autoJadge.isNeedBrake()) && (!this.isBreak)) {
			sendMessage("40901", 16);
		}
//		if (ConfigManager.addClass.isYkms()) {
//			if ((!this.drive_41605) && (this.lastHightLight)) {
//				this.drive_41605 = true;
//				sendMessage("41605", 13);
//			}
//			if ((!this.drive_41603)
//					&& ((!this.isOpenHighLight) || (!this.isNearLight))) {
//				this.drive_41603 = true;
//				sendMessage("41603", 13);
//			}
//		}
	}
}