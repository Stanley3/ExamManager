package com.scu.Contral;

import com.scu.GlobelControl.ConfigManager;
import com.scu.Signal.*;
import com.scu.Utils.*;
import com.scu.Media.MediaPlay;
import com.scu.Model.ExamWindow;

public class OverTakenThread extends ModuleThread {
	double startAngle=0.0D;
	double endAngle;
	double angle;
	private int iState = 1;
	/* 起步、转向�?变更车道、超车�?停车前不使用或错误使用转向灯 */
	private boolean overtaken_30205 = false;
	/* 起步、转向�?变更车道、超车�?停车前，�?��向灯少于3s即转�?*/
	private boolean overtaken_30206 = false;
	/* 在没有中心线或同方向只有�?��行车道的道路上从右侧超车 */
	private boolean overtaken_41406 = false;
	/* 不按考试员指令驾�?*/
	private boolean overtaken_30103 = false;
	/* 转向灯开启时�?*/
	private long turnLightTime = 0;
	public static double RANGETIGGER = ConfigManager.overCar
			.getTriggerDistance();
	/* 左转向灯是否�?�� */
	private boolean turnleftlight = false;
	/* 右转向灯是否�?�� */
	private boolean turnrightlight = false;
	/* 左转标记 */
	private boolean turnleft = false;
	/* 右转标记 */
	private boolean turnright = false;
	/* 转动角度 */
	private int turnAngle = ConfigManager.overCar.getTurnAngle();
	/* 关灯时间 */
	private long lightOffStartTime = 0L;

	private static int ZXCXSJ = 3000;
	private int cxsj = 0;

	public OverTakenThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.jsfs = ConfigManager.overCar.getTimeOrDistance();
		this.dRangeOut = ConfigManager.overCar.getEndDistance();
		this.iTimeOut = ConfigManager.overCar.getEndTime();
		RANGETIGGER = ConfigManager.overCar.getTriggerDistance();
	}

	public synchronized void run() {
		try {
			MediaPlay.getInstance().play("train_cc.wav");
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
			sendEndMessage(10);
		} else {
			sendEndMessage(10, 1);
		}
	}

	public void execute() {
		JudgeSignal carSignal = JudgeSignal.getInstance();
		this.curRange += Tools.getDistinceByOBDV(carSignal.gpsspeed, 200);
		/***
		 * 
		 * 判断左转还是右转
		 */
		if(startAngle==0.0D)
		{
			startAngle=carSignal.gpsangle;
		}
		endAngle=carSignal.gpsangle;
		/* 汽车角度 */
		 angle =endAngle-startAngle;
		 if(angle>180)
		 {
			 angle-=360;
		 }
		 else if(angle<-180)
		 {
			 angle +=360;
		 }
		if (angle > this.turnAngle) {
			this.turnleft = false;
			this.turnright = true;
		} else if (angle < -this.turnAngle) {
			this.turnleft = true;
			this.turnright = false;
		} else {
			this.turnleft = false;
			this.turnright = false;
		}
		/**
		 *  这个根本就没有判定时�?*/
		switch (this.iState) {
		case 1:
			if (this.turnleft) {
				if ((!this.turnleftlight) && (ConfigManager.overCar.isOpen())
						&& (!this.overtaken_30205)) {
					this.overtaken_30205 = true;
					sendMessage("30205", 10);
				}
				this.iState = 3;
			}
			if ((this.turnright) && (ConfigManager.overCar.isOpen())
					&& (!this.overtaken_41406)) {
				this.overtaken_41406 = true;
				this.iState = 9;// 右侧超车
				sendMessage("41406", 10);
			}
			if (carSignal.lamp_left)
				this.iState = 2;
			break;
		case 2:
			if (this.turnLightTime >= ConfigManager.commonConfig
					.getTurnLightWaitTime()) {
				this.iState = 3;
			} else {
				if (carSignal.lamp_left) {
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
				if (!this.turnleft)
					break;
				if ((ConfigManager.overCar.isOpen()) && (!this.overtaken_30206)) {
					this.overtaken_30206 = true;
					sendMessage("30206", 10);
				}
				this.iState = 9;
			}
			break;
		case 3:
			if ((this.turnright) && (ConfigManager.overCar.isOpen())
					&& (!this.overtaken_41406)) {
				this.overtaken_41406 = true;
				this.iState = 9;
				sendMessage("41406", 10);
			}
			if (!this.turnleft)
				break;
			this.iState = 4;

			break;
		case 4:
			if ((!this.turnleft) && (!this.turnright)) {
				this.cxsj += 200;
			} else
				this.cxsj = 0;
			/* 持续时间 */
			if (this.cxsj >= ZXCXSJ)
			{
				this.iState = 5;
				//MediaPlay.getInstance().play("cc_shycd.wav");
				// 请驾驶回原车�?
			}
			break;
		case 5:
			if ((this.turnright) && (!carSignal.lamp_right)
					&& (ConfigManager.overCar.isOpen())
					&& (!this.overtaken_30205)) {
				this.overtaken_30205 = true;
				this.iState = 9;
				sendMessage("30205", 10);
				// 起步、转向�?变更车道、超车�?停车前不使用或错误使用转向灯
			}
			if (carSignal.lamp_right)
			{
				this.iState = 6;
				this.turnLightTime = 0;
			}
			break;
		case 6:
			if (this.turnLightTime >= ConfigManager.commonConfig
					.getTurnLightWaitTime()) {
				this.iState = 7;
			} else {
				if (carSignal.lamp_right) {
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
				if (!this.turnright)
					break;
				if ((ConfigManager.overCar.isOpen()) && (!this.overtaken_30206)) {
					this.overtaken_30206 = true;
					sendMessage("30206", 10);
				}
				this.iState = 9;
			}
			break;
		case 7:
			if (!(this.turnleft) && !(this.turnright))
			{
				while(!isOut())
				{
					endAngle = JudgeSignal.getInstance().gpsangle;
					angle = endAngle - startAngle;
					if(angle > this.turnAngle)
					{
						this.iState = 8;
						break;
					}
				}
			}
			break;
		case 8:
			endAngle = JudgeSignal.getInstance().gpsangle;
			angle = endAngle - startAngle;
			if(angle < 3.0D)
				iState = 9;
			break;
		case 9:
			this.runFlag = false;
			break;
		}
	}
	public void judge() {
		if (!ConfigManager.overCar.isOpen())
			return;
		if ((!this.overtaken_30103) && (this.iState != 9)) {
			this.overtaken_30103 = true;
			sendMessage("30103", 10);// 不按指令进行驾驶
		}
	}
}