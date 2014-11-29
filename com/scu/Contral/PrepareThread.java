package com.scu.Contral;

import com.scu.GlobelControl.ConfigManager;
import com.scu.Media.MediaPlay;
import com.scu.Model.ExamWindow;
import com.scu.Signal.*;
import com.scu.Utils.Tools;

public class PrepareThread extends ModuleThread {
	private int iState = 0;
	private boolean sideA = false;
	private boolean sideB = false;
	public static double RANGETIGGER = ConfigManager.abordPrepare
			.getTriggerDistance();
	public int seq = 0;
	private boolean isOpen = false;
	private int curseq = 0;

	public PrepareThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.jsfs = ConfigManager.abordPrepare.getTimeOrDistance();
		this.dRangeOut = ConfigManager.abordPrepare.getEndDistance();
		this.iTimeOut = ConfigManager.abordPrepare.getEndTime();
		RANGETIGGER = ConfigManager.abordPrepare.getTriggerDistance();
		this.seq = ConfigManager.abordPrepare.getAround();
	//	this.setName("prepareThread" + this.activeCount());
	}

	public synchronized void run() {
		try {
			MediaPlay.getInstance().play("jjkslk.wav");
			Thread.sleep(10000);
			MediaPlay.getInstance().play("sczb.wav");
			Thread curt = Thread.currentThread();
			this.isPause = false;
			while (this.runFlag)
				try {
					Thread.sleep(200L);
					if(!isOut())
						execute();
					else
						this.runFlag = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
		} catch (Exception localException) {
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MediaPlay.getInstance().play("finish.wav");
		this.window.remove(this);
		if (!this.isBreakFlag) {
			judge();
			sendEndMessage(1);
		} else {
			sendEndMessage(1, 1);
		}
	}

	public void execute() {
		JudgeSignal carSignal = JudgeSignal.getInstance();
		this.curRange += Tools.getDistinceByOBDV(carSignal.gpsspeed, 200);

		if ((carSignal.signal_frontbumper) && (!this.sideA)) {
			this.sideA = true;
			if (this.curseq == 0)
				this.curseq = 2;
			MediaPlay.getInstance().play("zjgct.wav");
		}
		if ((this.sideA) && (!carSignal.signal_frontbumper)) {
			this.sideA = false;
		}
		if ((carSignal.signal_rearbumper) && (!this.sideB)) {
			this.sideB = true;
			if (this.curseq == 0)
				this.curseq = 1;
			MediaPlay.getInstance().play("zjgcw.wav");
		}
		if ((this.sideB) && (!carSignal.signal_rearbumper))
			this.sideB = false;
	}

	public void judge() {
		if (!ConfigManager.abordPrepare.isOpen())
			return;
		if (this.seq != this.curseq)
			sendMessage("40101", 1);
	}
}
