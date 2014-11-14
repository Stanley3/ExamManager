package com.scu.Contral;

import com.scu.GlobelControl.ConfigManager;
import com.scu.Signal.*;
import com.scu.Utils.*;
import com.scu.Media.MediaPlay;
import com.scu.Model.ExamWindow;

public class StartThread extends ModuleThread {

	/* 车门未关闭起�?*/
	private boolean start_40202 = false;
	/* 不松驻车制动器起步，但能及时纠正 */
	private boolean start_40206 = false;
	/* 发动机启动后，不及时松开启动�?�� */
	private boolean start_40207 = false;
	private boolean start_40210 = true;
	/* 道路交�?情况复杂时起步不能合理使用喇�?*/
	private boolean start_40208 = false;
	/* 起步、转向�?变更车道、超车�?停车前不使用或错误使用转向灯 */
	private boolean start_30205 = false;
	/* 起步、转向�?变更车道、超车�?停车前，�?��向灯少于3s即转�?*/
	private boolean start_30206 = false;
	/* 不按考试员指令驾�?*/
	private boolean start_30103 = true;
	/* 起步时车辆发生闯�?置为true暂不判断) */
	private boolean start_40209 = true;
	/* 驾驶姿势不正�?*/
	private boolean start_30201 = false;
	/* 起步或行驶中挂错挡，不能及时纠正 */
	private boolean start_30204 = false;
	/* 23 */
	private long turnLightTime = 0;
	/* �?��点火时间 */
	public long ignitionstartTime = System.currentTimeMillis();
	public static double RANGETIGGER = ConfigManager.startCar
			.getTriggerDistance();
	/* 点火时间 */
	private long ignitionTime = 0;
	/* 关灯时间 */
	private long lightOffStartTime = 0L;
	/* 左转 */
	private boolean turnleft = false;
	private double car_speed = 0.0D;
	private boolean is2Gear = false;
	private boolean is3Gear = false;
	/* 手刹 */
	private boolean handbrake = true;
	/* 手刹持续时间 */
	private long handbraketime = 0L;

	public StartThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.jsfs = ConfigManager.startCar.getTimeOrDistance();
		this.dRangeOut = ConfigManager.startCar.getEndDistance();
		this.iTimeOut = ConfigManager.startCar.getEndTime();
		RANGETIGGER = ConfigManager.startCar.getTriggerDistance();
	}

	public synchronized void run() {
		try {
			MediaPlay.getInstance().play("start.wav");
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
		if (this.jsfs == 2)
			this.start_30103 = false;
		if (!this.isBreakFlag) {
			judge();
			sendEndMessage(2);
		} else {
			sendEndMessage(2, 1);
		}
	}

	public void execute() {
		JudgeSignal carSignal = JudgeSignal.getInstance();
		this.curRange += Tools.getDistinceByOBDV(carSignal.gpsspeed, 200);
		this.car_speed = carSignal.gpsspeed;
		System.out.println(this.car_speed );
		/**
		 * 判断手刹是否正确
		 */
		if ((this.handbrake) && (!carSignal.signal_handbrake)) {
			this.handbrake = false;
		}
		if (!carSignal.signal_handbrake)
			this.handbraketime += 200;
		else {
			this.handbraketime = 0L;
		}
		if ((this.handbraketime > ConfigManager.startCar.getMaxTime() )
				&& (this.start_30103)) {
			this.start_30103 = false;
			this.runFlag = false;
			sendMessage("30103", 2);
		}

		if (this.car_speed > 0.0D) {
			if (ConfigManager.startCar.isOpen()) {
				if ((carSignal.gear != 1) && (!this.start_30204)) {
					this.start_30204 = true;
					sendMessage("30204", 2);
				}
				if ((!this.start_30205) && (!this.turnleft)) {
					this.start_30205 = true;
					sendMessage("30205", 2);
				} else if ((!this.start_30206)
						&& (this.turnLightTime < ConfigManager.commonConfig
								.getTurnLightWaitTime())) {
					this.start_30206 = true;
					sendMessage("30206", 2);
				}
			}
		} else if (this.turnLightTime < ConfigManager.commonConfig
				.getTurnLightWaitTime()) {
			if (carSignal.lamp_left) {
				this.turnleft = true;
				this.turnLightTime += 200;
				//if(this.turnLightTime>=3)
				System.out.println("时间"+ this.turnLightTime);
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
		}
	
		if ((ConfigManager.startCar.isOpen()) && (carSignal.signal_horn)) {
			this.start_40208 = true;
		}
		if ((!this.start_40202) && (ConfigManager.startCar.isOpen())
				&& (this.car_speed > 0.0D) && (!carSignal.signal_door)) {
			this.start_40202 = true;
			sendMessage("40202", 2);
		}

		if ((!this.start_40206) && (ConfigManager.startCar.isOpen())
				&& (this.car_speed > 0.0D) && (carSignal.signal_handbrake)) {
			this.start_40206 = true;
			sendMessage("40206", 2);
		}

		if ((!this.start_40210) && (ConfigManager.startCar.isOpen())
				&& (carSignal.n >= ConfigManager.addClass.CARPARM_QB_QBFDJZZGG)) {
			this.start_40210 = true;
			sendMessage("40210", 2);
		}

		if ((!this.start_40209) && (ConfigManager.startCar.isOpen())
				&& (this.car_speed > 0.0D)
				&& (carSignal.n < ConfigManager.addClass.CARPARM_QB_QBFDJZS)) {
			this.start_40209 = true;
			sendMessage("40209", 2);
		}

		if (this.car_speed >= 5.0D) {
			// Log.debug("完成起步判定");
			this.start_30103 = false;
			this.runFlag = false;
		}
	}

	public void judge() {
		if (!ConfigManager.startCar.isOpen())
			return;
		if (this.start_30103)
			sendMessage("30103", 2);
	}
}