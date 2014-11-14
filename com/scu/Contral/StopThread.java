package com.scu.Contral;
import com.scu.GlobelControl.ConfigManager;
import com.scu.Signal.*;
import com.scu.Utils.*;
import com.scu.Media.MediaPlay;
import com.scu.Model.ExamWindow;
public class StopThread extends ModuleThread {
	double startAngle=0;
	double endAngle;
	double angle;
	private int iState = 1;
	/* 起步、转向�?变更车道、超车�?停车前不使用或错误使用转向灯 */
	private boolean stop_30205 = false;
	/* 起步、转向�?变更车道、超车�?停车前，�?��向灯少于3s即转�?*/
	private boolean stop_30206 = false;
	/* 考试员发出靠边停车指令后，未能在规定的距离内停车 */
	private boolean stop_406021 = false;
	/* 拉紧驻车制动器前放松行车制动踏板 */
	private boolean stop_40608 = false;
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
	public StopThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.jsfs = ConfigManager.pullOver.getTimeOrDistance();
		this.dRangeOut = ConfigManager.pullOver.getEndDistance();
		this.iTimeOut = ConfigManager.pullOver.getEndTime();
	}
	public synchronized void run() {
		try {
			MediaPlay.getInstance().play("kbtc.wav");
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
			this.window.remove(this);
			if (!this.isBreakFlag) {
				judge();
				sendEndMessage(11);
			} else {
				sendEndMessage(11, 1);
			}
		} catch (Exception localException) {
		}
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
				if (angle > ConfigManager.pullOver.getOffsetAngle()&&
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
				if (carSignal.gpsspeed == 0.0D) {
					this.iState = 3;
				}
				break;
			case 3://判刹车和手刹
				if ((!this.stop_40608) && (!carSignal.signal_footbrake)) {
					if(!carSignal.signal_handbrake){
						this.stop_40608 = true;
						sendMessage("40608", 11);//拉紧驻车制动器前放松行车制动踏板
					}
					else
					{
						this.iState = 4;
					}
				}
				break;
			case 4:
				this.runFlag = false;
				break;
			}
	}
	public void judge() {
		if (!ConfigManager.pullOver.isOpen())
			return;
		if (this.iState < 4)
			if ((this.iState == 2) && (!this.isBreak))
				sendMessage("40607", 11);//停车后，未拉紧驻车制动器
			else
				sendMessage("406021", 11);//考试员发出靠边停车指令后，未能在规定的距离内停车
	}
}