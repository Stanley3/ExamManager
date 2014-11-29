package com.scu.Contral;

import com.scu.GlobelControl.ConfigManager;
import com.scu.Signal.*;
import com.scu.Utils.*;
import com.scu.Media.MediaPlay;
import com.scu.Model.ExamWindow;

public class PavementThread extends ModuleThread {
	private boolean breakFlag = false;
	private double curspeed = 0.0D;
	public static double RANGETIGGER = ConfigManager.footWayLiner
			.getTriggerDistance();
	public boolean isPlay = false;
	private boolean drive_41001=false;

	public PavementThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.jsfs = ConfigManager.footWayLiner.getTimeOrDistance();
		this.dRangeOut = ConfigManager.footWayLiner.getEndDistance();
		this.iTimeOut = ConfigManager.footWayLiner.getEndTime();
		RANGETIGGER = ConfigManager.footWayLiner.getTriggerDistance();
	}

	public synchronized void run() {
		try {
			Thread curt = Thread.currentThread();
			if (this.isPlay) {
				MediaPlay.getInstance().play("train_rxhdx.wav");
			}
			// Log.debug("人行横道线线程被唤醒");
			this.isPause = false;
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
		MediaPlay.getInstance().play("finish.wav");
		this.window.remove(this);
		if (!this.isBreakFlag) {
			judge();
			sendEndMessage(6);
		} else {
			sendEndMessage(6, 1);
		}
	}

	public void execute() {
		this.curRange += Tools.getDistinceByOBDV(
				JudgeSignal.getInstance().gpsspeed, 200);
		this.curspeed = JudgeSignal.getInstance().gpsspeed;
		if (this.curspeed > ConfigManager.footWayLiner.getMaxSpeed()&&!this.drive_41001) {
			this.drive_41001=true;
			sendMessage("41004", 6);
		}// 是否�?��刹车
		this.breakFlag |= JudgeSignal.getInstance().signal_footbrake;
	}

	public void judge() {
		if (!ConfigManager.footWayLiner.isOpen())
			return;
//		if (this.curspeed > ConfigManager.footWayLiner.getMaxSpeed()) {
//			sendMessage("41001", 6);
//		}// 是否�?��刹车
		/*修改*/
		if ((ConfigManager.autoJadge.isNeedBrake()) && (!this.breakFlag))
			sendMessage("41001", 6);
	}
}