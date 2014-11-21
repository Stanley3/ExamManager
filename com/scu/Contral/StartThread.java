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
			this.start_40200 = false;
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
		//this.turnAngle = angle;
		if(angle>0)
		{
			this.turnleft=true;
		}
		/*---------------------------修改-------------------------------------*/
		switch(this.nextSate)
		{
			/*一踩　二挂　三打转向　四按喇叭　五松手刹*/
			case 1:
				if(carSignal.signal_frontbumper)
					this.nextSate=2;
				break;
			case 2:
				if(carSignal.gear==1)
					this.sate=2;
				if(this.sate==this.nextSate)
					this.nextSate=3;
				else
					this.nextSate=6;
				break;
			case 3:
				if(carSignal.lamp_left)
					this.sate=3;
				if(this.sate==this.nextSate)
					this.nextSate=4;
				else
						this.nextSate=6;
				break;
			case 4:
				if(carSignal.signal_horn)
					this.sate=4;
				if(this.sate==this.nextSate)
					this.nextSate=5;
				else
					this.nextSate=6;
				break;
			case 5:
				if(!carSignal.signal_handbrake)
					this.sate=5;
				if(this.sate!=this.nextSate)
					this.nextSate=6;
				break;
			case 6:
				this.order=false;
				break;
		}
		/*---------------------------修改-------------------------------------*/
//		System.out.println(this.car_speed );
		/**
		 * 判断手刹是否正确
		 */
		//松手刹后没有起步的时间
		if (!carSignal.signal_handbrake)
			this.handlosstime += 200;
		else {
			this.handlosstime = 0L;
		}
		//判断松手刹后5S内车辆的速度
		if(!this.start_40201&&!carSignal.signal_handbrake&&this.handlosstime>5000&&carSignal.gpsspeed==0)
		{
			this.start_40201=true;
			sendMessage("40201", 2);
		}
		if(carSignal.gpsspeed>0){
		if ((this.handbrake) && (!carSignal.signal_handbrake)) {
			this.handbrake = false;
		}
		if (carSignal.signal_handbrake)
			this.handbraketime += 200;
		else {
			this.handbraketime = 0L;
		}//判断起步后没有松手刹
		if ((this.handbraketime > ConfigManager.startCar.getMaxTime() )
				&& (this.start_40200)) {
			this.start_40200 = false;
			this.runFlag = false;
			sendMessage("40200", 2);
		}
		else if(this.handbraketime>0&& (this.start_40206))
		{
			this.start_40206 = false;
			this.runFlag = false;
			sendMessage("40206", 2);
		}
		}
		if (this.car_speed > 0.0D) {
			if (ConfigManager.startCar.isOpen()) {//判断档位
				if ((carSignal.gear != 1) && (!this.start_30204)) {
					this.start_30204 = true;
					sendMessage("30204", 2);
				}//判断转向灯
				if ((!this.start_30205) && (!this.turnleft)) {
					this.start_30205 = true;
					sendMessage("30205", 2);
				} else if ((!this.start_30206)//判断开灯时间
						&& (this.turnLightTime < ConfigManager.commonConfig
								.getTurnLightWaitTime())) {
					this.start_30206 = true;
					sendMessage("30206", 2);
				}
			}
		} else if (this.turnLightTime < ConfigManager.commonConfig
				.getTurnLightWaitTime()) {
			if (carSignal.lamp_left) {
			//	this.turnleft = true;
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
		//起步时车辆发生闯动
		if ((!this.start_40209) && (ConfigManager.startCar.isOpen())
				&& (this.car_speed > 0.0D)
				&& (carSignal.n < ConfigManager.addClass.CARPARM_QB_QBFDJZS)) {
			this.start_40209 = true;
			sendMessage("40209", 2);
		}
		//未按语音指令完成起步
		if (this.car_speed >= 1.0D) {
			// Log.debug("完成起步判定");
			this.start_40200 = false;
			this.runFlag = false;
		}
	}

	public void judge() {
		if (!ConfigManager.startCar.isOpen())
			return;
		if (this.start_40200)
			sendMessage("30103", 2);
		if(!order||this.sate!=5)
		{
			sendMessage("30201", 2);
		}
	}
}