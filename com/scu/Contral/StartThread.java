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
	private boolean start_40206 = true;
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
	private boolean start_40200 = true;
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
	private double startAngle=0;
	private double endAngle=0;
	/* 手刹 */
	private boolean handbrake = true;
	/* 手刹持续时间 */
	private long handbraketime = 0L;
	
	/*用来表示程序当前的状态*/	
	private int sate=0;
	private int nextSate=1;
	private boolean order=true;
	private long handlosstime=0L;
	private boolean start_40201=false;
	private boolean start_30200=false;
	public StartThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.jsfs = ConfigManager.startCar.getTimeOrDistance();
		this.dRangeOut = ConfigManager.startCar.getEndDistance();
		this.iTimeOut = ConfigManager.startCar.getEndTime();
		RANGETIGGER = ConfigManager.startCar.getTriggerDistance();
	}
	public synchronized void run() {
		try {
			if(super.moduleFlag==1){
				try {
					Thread.sleep(200L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			//MediaPlay.getInstance().play("jjkslk.wav");
			Thread.sleep(1000);
			MediaPlay.getInstance().play("train_qb.wav");
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
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MediaPlay.getInstance().play("finish.wav");
		this.window.remove(this);
//		if (this.jsfs == 2)
//			this.start_40200 = false;
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
		if(this.startAngle==0)
		{
			this.startAngle=carSignal.gpsangle;
		}
		this.endAngle = (int) carSignal.gpsangle;
		double angle = this.endAngle - this.startAngle;
		if (angle > 180)
			angle -= 360;
		else if (angle < -180)
			angle += 360;
		if(angle>3)
		{
			this.turnleft=true;
		}
		/**
		 * 判断手刹是否正确
		 */
		//松手刹后没有起步的时间
		 carSignal = JudgeSignal.getInstance();
		 try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!carSignal.signal_handbrake)
		{
			this.handlosstime += 200;
			System.out.println("松手刹时间---------》"+this.handlosstime);
		}
		else {
			this.handlosstime = 0L;
		}
		//判断松手刹后5S内车辆的速度
		if(!this.start_40201&&!carSignal.signal_handbrake&&this.handlosstime>5000
				&&carSignal.gpsspeed==0)
		{
			this.start_40201=true;
		//	sendMessage("40201", 2);
		}
		if(carSignal.gpsspeed>0){
		if ((this.handbrake) && (!carSignal.signal_handbrake)) {
			this.handbrake = false;
		}
		if (carSignal.signal_handbrake)
		{
			this.handbraketime += 200;
		}
		else {
			this.handbraketime = 0L;
		}//判断起步后没有松手刹
		if (this.handbraketime > ConfigManager.startCar.getMaxTime()
				&& (this.start_40200)) {
			this.start_40200 = false;
			System.out.println("我进来了-----------------------------");
			//this.runFlag = false;
			sendMessage("40200", 2);
		}
		else if(this.handbraketime>0 && this.start_40206)
		{
			System.out.println("wo jin lai l --------------------------");
			this.start_40206 = false;
			//this.runFlag = false;
			sendMessage("40206", 2);
		}
		}
		if (this.car_speed > 0.0D) {
			if (ConfigManager.startCar.isOpen()) {//判断档位
				if ((carSignal.gear != 1) && (!this.start_30204)) {
					this.start_30204 = true;
					//sendMessage("30204", 2);
				}//判断转向灯
				if ((!this.start_30205) && (!this.turnleft)) 
				{
					this.start_30205 = true;
					sendMessage("30205", 2);
				} else if (!this.start_30206//判断开灯时间
						&& (this.turnLightTime < ConfigManager.commonConfig
								.getTurnLightWaitTime())) {
					this.start_30206 = true;
					sendMessage("30206", 2);
				}
			}
		} else if (this.turnLightTime < ConfigManager.commonConfig
				.getTurnLightWaitTime()) {
			System.out.println("开灯时间变化------------》"+this.turnLightTime);
			if (carSignal.lamp_left) {
				this.start_30200=true;
				this.turnLightTime += 200;
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
		//判断是否使用喇叭
		if ((ConfigManager.startCar.isOpen()) && (carSignal.signal_horn)) {
			this.start_40208 = true;
		}
		//判断车门是否关闭
		if ((!this.start_40202) && (ConfigManager.startCar.isOpen())
				&& (this.car_speed > 0.0D) && (!carSignal.signal_door)) {
			this.start_40202 = true;
			sendMessage("40202", 2);
		}
     //不松驻车制动器起步，但能及时纠正
//		if ((!this.start_40206) && (ConfigManager.startCar.isOpen())
//				&& (this.car_speed > 0.0D) && (carSignal.signal_handbrake)) {
//			this.start_40206 = true;
//			sendMessage("40206", 2);
//		}
		//起步时，加速踏板控制不当，致使发动机转速过高
		if ((!this.start_40210) && (ConfigManager.startCar.isOpen())
				&& (carSignal.n >= ConfigManager.addClass.CARPARM_QB_QBFDJZZGG)) {
			this.start_40210 = true;
			sendMessage("40210", 2);
		}
		//未按语音指令完成起步
		if (this.car_speed >= 1.0D) {
			// Log.debug("完成起步判定");
			//this.start_40200 = false;
			//this.runFlag = false;
		}
	}

	public void judge() {
		if (!ConfigManager.startCar.isOpen())
			return;
//		if (this.start_40200)
//			sendMessage("30103", 2);
		if(!this.start_40208)
		{
			sendMessage("40208", 2);
		}
//		if(!order||this.sate!=5)
//		{
//			sendMessage("30201", 2);
//		}
		if(!this.start_30200)
		{
			sendMessage("30200", 2);
		}
	}
}