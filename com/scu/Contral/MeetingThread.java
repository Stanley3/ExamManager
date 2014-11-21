package com.scu.Contral;

import com.scu.Signal.*;
import com.scu.Utils.*;
import com.scu.Media.MediaPlay;
import com.scu.Model.ExamWindow;
import com.scu.GlobelControl.ConfigManager;

public class MeetingThread extends ModuleThread {
	double angle ;
	double startAngle=0.0D;
	double endAngle;
	private JudgeSignal carSignal = JudgeSignal.getInstance();
	/* 横向安全间距判断差，紧�?转向避让对方来车 */
	private boolean metting_41303 = false;
	/* 通过路口时使用原光灯 */
	private boolean drive_41605 = false;
	/* 通过急弯、坡路�?拱桥、人行横道或者没有交通信号灯控制的路口时，不交替使用远近光灯示意 */
	private boolean drive_41603 = false;
	/* 是否打开远光�?*/
	private boolean isOpenHighLight = false;
	/* 是否打开近光�?*/
	private boolean isNearLight = false;
	private boolean lastHightLight = false;
	/* 是否打开 */
	private boolean isTurn = false;
	/* 19 */
	private boolean breakFlag = false;
	/* 超�?不当 */private boolean csbd = false;
	private boolean isInitAngle = false;
	private long startTime = System.currentTimeMillis();
	/* �?��角度 */
	private static double MAX_ANGLE = ConfigManager.meetingCar.getCircleAngle();
	/* 触发距离 */
	public static double RANGETIGGER = ConfigManager.meetingCar
			.getTriggerDistance();;

	/*     */
	public MeetingThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.jsfs = ConfigManager.meetingCar.getTimeOrDistance();
		this.dRangeOut = ConfigManager.meetingCar.getEndDistance();
		this.iTimeOut = ConfigManager.meetingCar.getEndTime();
		RANGETIGGER = ConfigManager.meetingCar.getTriggerDistance();
		this.setName("MeettingThread" + this.activeCount());
	}

	/*     */
	public synchronized void run() {
		try {
			MediaPlay.getInstance().play("hc150.wav");
			this.isPause = false;
			this.startTime = System.currentTimeMillis();
			while (this.runFlag)
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
		} catch (Exception localException) {
		}
		this.window.remove(this);
		if (!this.isBreakFlag) {
			judge();
			sendEndMessage(9);
		} else {
			sendEndMessage(9, 1);
		}
	}

	public void execute() {
		/* 初始化汽车信�?*/
		JudgeSignal carSignal = JudgeSignal.getInstance();
		/* 当前距离 */
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
		/* 判断是否超�? */
		if (carSignal.gpsspeed > ConfigManager.meetingCar.getMaxSpeed()) {
			this.csbd = true;
		}
		this.breakFlag |= JudgeSignal.getInstance().signal_footbrake;
		this.isOpenHighLight |= carSignal.lamp_highbeam;
		this.isNearLight |= carSignal.lamp_near;
		this.lastHightLight = carSignal.lamp_highbeam;
		if (Math.abs(angle) > MAX_ANGLE)
			this.isTurn = true;
	}

	public void judge() {
		if (!ConfigManager.meetingCar.isOpen())
			return;
		/* 横向安全间距判断差，紧�?转向避让对方来车 */
		if ((!this.metting_41303) && (this.isTurn)) {
			sendMessage("41303", 9);
		}
		/* 在没有中心隔离设施或者中心线的道路上会车时，不减速靠右行驶，或未与其他车辆�?行人、非机动车保持安全距�?*/
		if (this.csbd) {
			sendMessage("41301", 9);
		}
		/* 是否�?��刹车 */
		else if ((ConfigManager.autoJadge.isNeedBrake())
				&& (!this.breakFlag)) {
			sendMessage("41301", 9);
		}
		/* 通过路口时使用灯 */
//		if (ConfigManager.addClass.isYkms()) {
//			if ((!this.drive_41605) && (this.lastHightLight)) {
//				this.drive_41605 = true;
//				sendMessage("41605", 13);
//			}
//			/* 通过急弯、坡路�?拱桥、人行横道或者没有交通信号灯控制的路口时，不交替使用远近光灯示意 */
//			if ((!this.drive_41603)
//					&& ((!this.isOpenHighLight) || (!this.isNearLight))) {
//				this.drive_41603 = true;
//				sendMessage("41603", 13);
//			}
//		}
	}
}