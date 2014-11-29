package com.scu.Contral;
import com.scu.GlobelControl.ConfigManager;
import com.scu.Signal.*;
import com.scu.Utils.*;
import com.scu.Media.MediaPlay;
import com.scu.Model.ExamWindow;
public class StopThread extends ModuleThread {
	double startAngle=0;
	double endAngle=0;
	double angle = 0;
	private int iState = 1;
	/* 起步、转向�?变更车道、超车�?停车前不使用或错误使用转向灯 */
	private boolean stop_30205 = false;
	/* 起步、转向�?变更车道、超车�?停车前，�?��向灯少于3s即转�?*/
	private boolean stop_30206 = false;
	/* 考试员发出靠边停车指令后，未能在规定的距离内停车 */
	private boolean stop_406021 = false;
	/* 拉紧驻车制动器前放松行车制动踏板 */
	private boolean stop_40608 = false;
	/* 下车前不将发动机熄火 */
	private boolean stop_40609 = false;
	/*下车前不摘为空挡*/
	private boolean stop_40610 = false;
	private boolean err_handbrake = false;
	private boolean err_footbrake = false;
	/* �?��时间 */
	private long turnLightTime = 0;
	/* 打开灯时�?*/
	private long lightstartTime = System.currentTimeMillis();
	public static double RANGETIGGER = ConfigManager.pullOver
			.getTriggerDistance();
	/* 又转向灯 */
	private boolean turnrightlight = false;
	/* 右转 */
	private boolean turnright = false;
	private int lastTurnLight = 0;
	private boolean isBreak = false;
	private long lightOffStartTime = 0L;
	private long stopTime = System.currentTimeMillis();
	private long waitTime = 0L;
	public StopThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.jsfs = ConfigManager.pullOver.getTimeOrDistance();
		this.dRangeOut = ConfigManager.pullOver.getEndDistance();
		this.iTimeOut = ConfigManager.pullOver.getEndTime();
		this.setName("StopThread" + Thread.activeCount());
	}
	public synchronized void run() {
		try {
			MediaPlay.getInstance().play("train_tc.wav");
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
			System.err.println("最终的状态： istate=" + iState);
//			System.err.println("触发角度：" + ConfigManager.pullOver.getOffsetAngle());
			this.window.remove(this);
			if (!this.isBreakFlag) {
				judge();
				sendEndMessage(11);
			} else {
				sendEndMessage(11, 1);
			}
		} catch (Exception localException) {
		}
		MediaPlay.getInstance().play("finish.wav");
	}
	public void execute() {
		JudgeSignal carSignal = JudgeSignal.getInstance();
		this.curRange += Tools.getDistinceByOBDV(carSignal.gpsspeed, 200);
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
		 
		 /*更新代码*/
		 
		 switch (this.iState) {
			case 1:// 判方向触发角�?
				if (angle >= ConfigManager.pullOver.getOffsetAngle()&&
						this.turnLightTime >= ConfigManager.commonConfig
						.getTurnLightWaitTime()) {
					this.iState = 2;
					if (!this.turnrightlight) {
						if ((!ConfigManager.pullOver.isOpen()) || (this.stop_30205))
							break;
						this.stop_30205 = true;
						sendMessage("30205", 11);
					} else {
						if ((this.turnLightTime >= ConfigManager.commonConfig
								.getTurnLightWaitTime())
								|| (!ConfigManager.pullOver.isOpen())
								|| (this.stop_30206))
							break;
						this.stop_30206 = true;
						sendMessage("30206", 11);
					}
				} else {
					if (this.turnLightTime < ConfigManager.commonConfig
							.getTurnLightWaitTime()) {
						//计算�?��时间
						if (carSignal.lamp_right) {
							this.turnrightlight = true;
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
					}
					this.turnLightTime = ((System.currentTimeMillis() - this.lightstartTime) );
				}
				break;
			case 2://判刹车和速度
				if (!carSignal.signal_footbrake)break;
				if (carSignal.gpsspeed == 0) {
					this.iState = 3;
				}
				break;
			case 3: //判断手刹
				if(waitTime == 0L)
					waitTime = System.currentTimeMillis();
				if(System.currentTimeMillis() - waitTime > 5000L)//5s内不给信号，报错，进入状态4
				{
					iState = 4;
					sendMessage("40607", 11);
					waitTime = 0L;
					break;
				}
				if(carSignal.signal_handbrake)
				{
					if(!carSignal.signal_footbrake)//拉紧手刹之前，判断是否脚刹已松开
						sendMessage("40608", 11);
					iState = 4;
					waitTime = 0L;
				}
				else if(carSignal.signal_off
						|| !carSignal.signal_footbrake
						|| carSignal.gear == 0) //5s内给出了别的信号，报错，进入状态4
				{
					iState = 4;
					sendMessage("40607", 11);
					waitTime = 0L;
				}
				break;
			case 4: //判断空挡
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				if(waitTime == 0L)
					waitTime = System.currentTimeMillis();
				if(System.currentTimeMillis() - waitTime > 5000L)//5s不给信号，报错，进入状态5
				{
					iState = 5;
					sendMessage("40610", 11);
					waitTime = 0L;
					break;
				}
				if(carSignal.gear == 0)
				{
					iState = 5;
					waitTime = 0L;
				}
				else if(carSignal.signal_off || carSignal.signal_handbrake
						 || !carSignal.signal_footbrake) //有信号但不是需要的空挡信号，报错，进入状态5
				{
					iState = 5;
					sendMessage("40610", 11);
					waitTime = 0L;
				}
				break;
			case 5://刹车
				if(waitTime == 0L)
					waitTime = System.currentTimeMillis();
				if(System.currentTimeMillis() - waitTime > 5000L)//5s内不给信号，报错，进入状态6
				{
					iState = 6;
					sendMessage("40608", 11);
					waitTime = 0L;
				}
				if(!carSignal.signal_footbrake) //接收到刹车信号
				{
					iState = 6;
					waitTime = 0L;
				}
				else if(carSignal.signal_off) //给出了别的信号，报错，进入状态6
				{
					iState = 6;
					sendMessage("40608", 11);
					waitTime = 0L;
				}
				break;
			case 6: //熄火
				if(waitTime == 0L)
					waitTime = System.currentTimeMillis();
				if(System.currentTimeMillis() - waitTime > 5000L)//5s内不给信号，进入状态7
				{
					iState = 7;
					waitTime = 0L;
					break;
				}
				//只有当不评判熄火信号和评判且接收到熄火信号时才进入下一个状态
				if(!ConfigManager.pullOver.isEvaluateShutDown() || 
						carSignal.signal_off)
				{
					this.iState = 7;
					stop_40609 = true; //表示此项操作正确
					waitTime = 0L;
				}
				break;
			case 7:
				this.runFlag = false;
				break;
			}
	}
	public void judge() {
		if (!ConfigManager.pullOver.isOpen())
			return;
		if(this.iState != 7)
			sendMessage("406021", 11);//考试员发出靠边停车指令后，未能在规定的距离内停车
		else if(!stop_40609)
			sendMessage("40609", 11);//车辆未熄火
	}
}