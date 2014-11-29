package com.scu.Contral;

import com.scu.GlobelControl.ConfigManager;
import com.scu.Signal.*;
import com.scu.Utils.*;
import com.scu.Media.MediaPlay;
import com.scu.Model.ExamWindow;

public class BusThread extends ModuleThread {
	/* 线程暂停标记 */
	private boolean breakFlag = false;
	/* 汽车当前速度 */
	private double curspeed = 0.0D;
	/* 触发距离 */
	public static double RANGETIGGER = ConfigManager.busStation
			.getTriggerDistance();
	private boolean drive_41201=false;
	public BusThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.jsfs = ConfigManager.busStation.getTimeOrDistance();// StaticVariable.CARPARM_BUS_JSFS;
		this.dRangeOut = ConfigManager.busStation.getEndDistance();// StaticVariable.CARPARM_BUS_JSJL;
		this.iTimeOut = ConfigManager.busStation.getEndTime();// StaticVariable.CARPARM_BUS_JSSJ;
		RANGETIGGER = ConfigManager.busStation.getTriggerDistance();// StaticVariable.CARPARM_BUS_CFJL;
	}

	public synchronized void run() {
		try {
			MediaPlay.getInstance().play("train_ggqcz.wav");
			/* 是否暂停置为FALSE */
			this.isPause = false;

			while (this.runFlag)
				try {
					Thread.sleep(200L);
					/* 如果结束�?runflag 置为FALSE */
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
			sendEndMessage(8);
		} else {
			sendEndMessage(8, 1);
		}
	}

	public void execute() {
		/* 累计运行距离 */
		this.curRange += Tools
				.getDistinceByOBDV(JudgeSignal.getInstance().gpsspeed, 200);
		this.curspeed = JudgeSignal.getInstance().gpsspeed;
		if (this.curspeed > ConfigManager.busStation.getMaxSpeed()&&!this.drive_41201) {
			// 不安规定减�?慢行
			this.drive_41201=true;
			sendMessage("41201", 8);
		}
		// 是否有刹车信�?
		this.breakFlag |= JudgeSignal.getInstance().signal_footbrake;
	}

	public void judge() {
		// 閸掋倖鏌囩拠銉┿�閺勵垰鎯佺拠鍕灲
		if (!ConfigManager.busStation.isOpen())
			return;
		// 判断当前车�?是否大于规定车�?
		 // 是要求有刹车动作如果没有就不发�?错误信息如果you就发送是要求有刹车动�?
		 if ((ConfigManager.autoJadge.isNeedBrake()) && (!this.breakFlag))
			sendMessage("41203", 8);
	}
}
