package com.scu.Contral;

/*     */
import com.scu.Signal.*;
import com.scu.GlobelControl.ConfigManager;
import com.scu.Signal.JudgeSignal;
/*     */
import com.scu.Utils.*;
import com.scu.Media.MediaPlay;
/*     */
import com.scu.Model.ExamWindow;

public class ChangeLaneThread extends ModuleThread {
	private boolean lamb_Left_State=false;
	private boolean lamb_Right_State=false;
	private int iState = 1;
	/* 起步、转向�?变更车道、超车�?停车前不使用或错误使用转向灯 */
	private boolean changelane_30205 = false;
	/* 起步、转向�?变更车道、超车�?停车前，�?��向灯少于3s即转�?*/
	private boolean changelane_30206 = false;
	/* 不按考试员指令驾�?*/
	private boolean changelane_30103 = true;
	/* �?��时间 */
	private long turnLightTime = 0;
	/* �?��关灯时间 */
	private long lightOffStartTime = 0L;
	/* 触发距离 */
	public static double RANGETIGGER = ConfigManager.changeLane
			.getTriggerDistance();
	/* 偏转方向�?*/
	private int turnAngle = ConfigManager.changeLane.getOffsetAngle();// CARPARM_CHANGELANE_BGCDZJD变更车道转角�?
	/* 时间 */
	public double angle;
	private long czsj = 0;
	private JudgeSignal carsignal = JudgeSignal.getInstance();
	double angleStart = 0;
	double angleEnd = 0;
	/**
	 * 构�?函数
	 * 
	 * @param window
	 * @param moduleFlag
	 */
	public ChangeLaneThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.jsfs = ConfigManager.changeLane.getTimeOrDistance();// StaticVariable.CARPARM_BUS_JSFS;
		this.dRangeOut = ConfigManager.changeLane.getEndDistance();// StaticVariable.CARPARM_BUS_JSJL;
		this.iTimeOut = ConfigManager.changeLane.getEndTime();// StaticVariable.CARPARM_BUS_JSSJ;
		RANGETIGGER = ConfigManager.changeLane.getTriggerDistance();// StaticVariable.CARPARM_BUS_CFJL;
	}

	public synchronized void run() {
		try {
			MediaPlay.getInstance().play("bgcd.wav");
//			this.isPause = true;
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
		/* 从窗口List中移除该线程 */
		this.window.remove(this);
		/* 是否有刹车信�?*/
		if (!this.isBreakFlag) {
			judge();
			sendEndMessage(4);
		} else {
			sendEndMessage(4, 1);
		}
	}

	public void execute() {
		lamb_Left_State=JudgeSignal.getInstance().lamp_left;
		lamb_Right_State=JudgeSignal.getInstance().lamp_right;
		/* 当前距离 */
		this.curRange += Tools
				.getDistinceByOBDV(JudgeSignal.getInstance().gpsspeed, 200);
		/* 初始化汽车信�?*/
		// CarSignal carSignal = CarSignal.getInstance();
		/* 这个角度是如何得到的  */
		if (angleStart == 0.0)
			angleStart = carsignal.gpsangle;
		angleEnd = carsignal.gpsangle;
		angle = angleEnd - angleStart;
		if (angle > 180)
			angle -= 360;
		else if (angle < -180)
			angle += 360;
		switch (this.iState) {
		case 1:// 第一次状态是1
			/* 如果转向大于要求角度但是没有使用转向灯（转向了但是没有用转向灯） */
			if ((angle > this.turnAngle) || (angle < -this.turnAngle)) {
				if ((ConfigManager.changeLane.isOpen())
						&& (!this.changelane_30205)) {
					/* 是否使用转向�?*/
					this.changelane_30205 = true;
					sendMessage("30205", 4);
				}
				// 没有使用转向灯状态转�?
				this.iState = 4;
			}
			// 如果打了左转向灯
			if (lamb_Left_State) {
				// 使用左转向状态转换位2
				this.iState = 2;
				System.currentTimeMillis();
			} else {
				if (!lamb_Right_State)
					break;
				this.iState = 3;// 使用右转�?
				System.currentTimeMillis();
			}
			break;
		case 2:
			// 判断是否已转�?
			if ((angle > this.turnAngle) || (angle < -this.turnAngle)) {
				if ((ConfigManager.changeLane.isOpen())
						&& (!this.changelane_30206)) {
					
					this.changelane_30206 = true;
					sendMessage("30206", 4);
				}
				this.iState = 4;
			}
			// 如果转向灯时间大于等�?S
			if (this.turnLightTime >= ConfigManager.commonConfig
					.getTurnLightWaitTime()) {
				this.iState = 4;//(bug  只打灯不转向 不扣�?
			} else if (lamb_Left_State) {
		//		System.out.println("加灯�?��间：" + this.turnLightTime);
				this.turnLightTime += 200;
				this.lightOffStartTime = 0L;
			} else {
				this.lightOffStartTime += 200L;
				// 如果灯关闭时间大于系统预设时�?1000ms)则将�?��时间重置�?
				if (this.lightOffStartTime > 1000)
				{
			//		System.out.println("将开灯时间清�?);
					this.turnLightTime = 0;
				}
				else {
					this.turnLightTime += 200;
				}
			}
			break;
		case 3:
			if ((angle > this.turnAngle) || (angle < -this.turnAngle)) {
				if ((ConfigManager.changeLane.isOpen())
						&& (!this.changelane_30206)) {
					this.changelane_30206 = true;
					sendMessage("30206", 4);
				}
				this.iState = 4;
			}
			if (this.turnLightTime >= ConfigManager.commonConfig
					.getTurnLightWaitTime()) {
				this.iState = 4;
			} else if (lamb_Right_State) {
				this.turnLightTime += 200;
				this.lightOffStartTime = 0L;
			} else {
				this.lightOffStartTime += 200L;
				// 如果灯关闭时间大于系统预设时�?1000ms)则将�?��时间重置�?
				if (this.lightOffStartTime > 1000)
					this.turnLightTime = 0;
				else {
					this.turnLightTime += 200;
				}
			}

			break;
		case 4:
			this.czsj += 200;// 刹车时间增加0.2S
			if (this.czsj < 5000)
				break;
			while(!isOut())
			{
				angleEnd = carsignal.gpsangle;
				angle = angleEnd - angleStart;
				if((angle > this.turnAngle) || (angle < -this.turnAngle))
				{
					this.changelane_30103 = false;// 按�?试员指令驾驶
					this.runFlag=false;
					break;
				}
			}
			break;
		}
	}

	public void judge() {
		if (ConfigManager.changeLane.isOpen())
			return;
		if (this.changelane_30103)
			sendMessage("30103", 4);
	}
}
