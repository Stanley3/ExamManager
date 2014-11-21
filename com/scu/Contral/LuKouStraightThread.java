package com.scu.Contral;

/* 路口直行   */
import com.scu.GlobelControl.ConfigManager;
import com.scu.Signal.JudgeSignal;
import com.scu.Utils.*;
import com.scu.Media.MediaPlay;
import com.scu.Model.ExamWindow;

public class LuKouStraightThread extends ModuleThread
{
	/* 不按考试员指令驾�?*/
	private boolean crossing_30103 = false;
	/* 通过路口时使用远光灯 */
	private boolean drive_41605 = false;
	/* 通过急弯、坡路�?拱桥、人行横道或者没有交通信号灯控制的路口时，不交替使用远近光灯示意 */
	private boolean drive_41603 = false;
	/* 打开远光�?*/
	private boolean isOpenHighLight = false;
	/* 打开近光�?*/
	private boolean drive_40701 = false;
	private boolean isNearLight = false;
	private boolean lastHightLight = false;
	private boolean lamp_break = false;
	private boolean isLine = true;
	private double curspeed = 0.0D;
	private int startAngle = 0;
	private int endAngle = 0;
	public static double RANGETIGGER = ConfigManager.luKouStraight
			.getTriggerDistance();

	public LuKouStraightThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.jsfs = ConfigManager.luKouStraight.getTimeOrDistance();
		this.dRangeOut = ConfigManager.luKouStraight.getEndDistance();
		this.iTimeOut = ConfigManager.luKouStraight.getEndTime();
		RANGETIGGER = ConfigManager.luKouStraight.getTriggerDistance();
	}

	public synchronized void run() {
		try {
			MediaPlay.getInstance().play("lkzx.wav");
			// Log.debug("路口直行线程被唤�?);
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
			sendEndMessage(5);
		} else {
			sendEndMessage(5, 1);
		}
	}

	public void execute() {
		// CarSignal carSignal = CarSignal.getInstance();
		this.curRange += Tools.getDistinceByOBDV(
				JudgeSignal.getInstance().gpsspeed, 200);
		this.curspeed = JudgeSignal.getInstance().gpsspeed;
		/*修改*/
		if (this.curspeed > ConfigManager.luKouStraight.getMaxSpeed()&&!this.drive_40701 ) {
			// 不安规定减�?或停车瞭�?
			this.drive_40701=true;
			sendMessage("40701", 5);
		}
		this.lamp_break |= JudgeSignal.getInstance().signal_footbrake;
		this.isOpenHighLight |= JudgeSignal.getInstance().lamp_highbeam;
		this.isNearLight |= JudgeSignal.getInstance().lamp_near;
		this.lastHightLight = JudgeSignal.getInstance().lamp_highbeam; 
		if (this.startAngle == 0)
			this.startAngle = (int) JudgeSignal.getInstance().gpsangle;
		this.endAngle = (int) JudgeSignal.getInstance().gpsangle;
	}

	public void judge() {
		if (!ConfigManager.luKouStraight.isOpen())
			return;
		int gpsangle = this.endAngle - this.startAngle;
		if (gpsangle > 180)
			gpsangle -= 360;
		else if (gpsangle < -180)
			gpsangle += 360;
		if (Math.abs(gpsangle) > 40) {
			this.isLine = false;// 判断是否走直�?
		}
		if ((!this.crossing_30103) && (!this.isLine)) {
			sendMessage("30103", 5);
		}
		// 判断是否超�?
//		if (this.curspeed > ConfigManager.luKouStraight.getMaxSpeed()) {
//			// 不安规定减�?或停车瞭�?
//			sendMessage("40701", 5);
//		}
		// 判断是否踩刹�?
		/*修改*/
		if ((ConfigManager.autoJadge.isNeedBrake()) && (!this.lamp_break)) {
			sendMessage("40701", 5);
		}
		// shifou 夜�?
//		if (ConfigManager.addClass.isYkms()) {
//			if ((!this.drive_41605) && (this.lastHightLight)) {
//				this.drive_41605 = true;
//				// 通过路口时使用远光灯
//				sendMessage("41605", 13);
//			}
//			if ((!this.drive_41603)
//					&& ((!this.isOpenHighLight) || (!this.isNearLight))) {
//				/* 通过急弯、坡路�?拱桥、人行横道或者没有交通信号灯控制的路口时，不交替使用远近光灯示意 */
//				this.drive_41603 = true;
//				sendMessage("41603", 13);
//			}
//		}

	}
}