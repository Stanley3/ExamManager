package com.scu.Contral;

import com.scu.GlobelControl.ConfigManager;
import com.scu.Signal.*;
import com.scu.Utils.*;
import com.scu.Media.MediaPlay;
import com.scu.Model.ExamWindow;

public class TurnLeftThread extends ModuleThread {
	private int iState = 1;
	private boolean turnleft_30205 = false;
	private boolean turnleft_30206 = false;
	private boolean drive_41605 = false;
	private boolean drive_41603 = false;
	private boolean isOpenHighLight = false;
	private boolean isNearLight = false;
	private boolean lastHightLight = false;
	private double turnLightTime = 0.0D;
	private boolean turnleft = false;
	private int turnAngle = 0;
	private long lightOffStartTime = 0L;
	private int startAngle;
	private int endAngle;
	private boolean isBreak = false;
	private double curspeed = 0.0D;
	private static double MAX_ANGLE = ConfigManager.turnLeft.getMinAngle();
	public static double RANGETIGGER = ConfigManager.turnLeft
			.getTriggerDistance();

	public TurnLeftThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.jsfs = ConfigManager.turnLeft.getTimeOrDistance();
		this.dRangeOut = ConfigManager.turnLeft.getEndDistance();
		this.iTimeOut = ConfigManager.turnLeft.getEndTime();
		RANGETIGGER = ConfigManager.turnLeft.getTriggerDistance();
		MAX_ANGLE = ConfigManager.turnLeft.getMinAngle();
	}

	public synchronized void run() {
		try {
			MediaPlay.getInstance().play("train_lkzzw.wav");
			Thread curt = Thread.currentThread();
			// Log.debug("路口左转弯线程被唤醒");
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
		MediaPlay.getInstance().play("finish.wav");
		this.window.remove(this);
		if (!this.isBreakFlag) {
			judge();
			sendEndMessage(15);
		} else {
			sendEndMessage(15, 1);
		}
	}

	public void execute() {
		System.out.println("开灯时间---------------------------"+this.turnLightTime);
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
		case 1:/*修改*/
			if ((this.turnAngle > ConfigManager.turnLeft.getMinAngle()) || (this.turnAngle < -ConfigManager.turnLeft.getMinAngle())) 
			{
				this.iState = 3;
				if (!this.turnleft) {
					if ((!ConfigManager.turnLeft.isOpen())
							&& (!this.turnleft_30205))
					{
						this.turnleft_30205 = true;
						sendMessage("30205", 15);
					}
				} else {
					if ((this.turnLightTime < ConfigManager.commonConfig
							.getTurnLightWaitTime())
							&&(ConfigManager.turnLeft.isOpen())
							&& (!this.turnleft_30206))
					{
						this.turnleft_30206 = true;
						sendMessage("30206", 15);
					}
				}
			} else if (this.turnLightTime >= ConfigManager.commonConfig
					.getTurnLightWaitTime()) {
				this.iState = 2;
			} else if (carSignal.lamp_left) {
				this.turnleft_30205 = true;
				this.turnleft = true;
				this.turnLightTime += 200;
				this.lightOffStartTime = 0L;
			} else {
				this.lightOffStartTime += 200;
				if (this.lightOffStartTime > ConfigManager.addClass
						.getLightOffTime())
					this.turnLightTime = 0;
				else {
					this.turnLightTime += 200;
				}
			}
			break;
		case 2:
			if (this.turnAngle >= -MAX_ANGLE)
				break;
			this.iState = 3;
			this.runFlag = false;
			break;
		}
	}

	public void judge() {
		if (!ConfigManager.turnLeft.isOpen())
			return;
		if (this.iState != 3) {
			sendMessage("30103", 15);
		}
		if (this.curspeed > ConfigManager.turnLeft.getMaxSpeed()) {
			sendMessage("40801", 15);
		} else if ((ConfigManager.autoJadge.isNeedBrake()) && (!this.isBreak)) {
			//sendMessage("40801", 15);
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
