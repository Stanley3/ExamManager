package com.scu.Contral;

import com.scu.GlobelControl.ConfigManager;
import com.scu.Media.MediaPlay;
import com.scu.Model.ExamWindow;
import com.scu.Signal.JudgeSignal;
import com.scu.Utils.Tools;

public class SchoolAreaThread extends ModuleThread {
	private boolean breakFlag = false;
	private double curspeed = 0.0D;
	public static double RANGETIGGER = ConfigManager.schoolArea
			.getTriggerDistance();
	private boolean drive_41101=false;
	public SchoolAreaThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.jsfs = ConfigManager.schoolArea.getTimeOrDistance();
		this.dRangeOut = ConfigManager.schoolArea.getEndDistance();
		this.iTimeOut = ConfigManager.schoolArea.getEndTime();
		RANGETIGGER = ConfigManager.schoolArea.getTriggerDistance();
	}

	public synchronized void run() {
		try {
			 MediaPlay.getInstance().play("train_xxqy.wav");
			// Thread curr = Thread.currentThread();
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
			sendEndMessage(7);
		} else {
			sendEndMessage(7, 1);
		}
	}

	public void execute() {
		this.curRange += Tools.getDistinceByOBDV(
				JudgeSignal.getInstance().gpsspeed, 200);
		this.curspeed = JudgeSignal.getInstance().gpsspeed;
		if (this.curspeed > ConfigManager.schoolArea.getMaxSpeed()&&!this.drive_41101) {
			this.drive_41101=true;
			sendMessage("41101", 7);
		} 
		this.breakFlag |= JudgeSignal.getInstance().signal_footbrake;
	}

	public void judge() {
		if (!ConfigManager.schoolArea.isOpen())
			return;
//		if (this.curspeed > ConfigManager.schoolArea.getMaxSpeed()) {
//			sendMessage("41101", 7);
//		} 
		if ((ConfigManager.autoJadge.isNeedBrake()) && (!this.breakFlag))
			sendMessage("41103", 7);
	}
}
