package com.scu.Contral;

/*    */
import com.scu.GlobelControl.ConfigManager;
import com.scu.Signal.*;
/*    */
import com.scu.Utils.*;
import com.scu.Media.MediaPlay;
/*    */
import com.scu.Model.ExamWindow;

public class OnLineThread extends ModuleThread {
	private boolean online_40301 = false;
	private boolean online_30116 = false;
	private boolean turn = false;
	private boolean csbd = false;
	private static double MAX_ANGLE = ConfigManager.linerDriving.getAngleNum();
	public static double RANGETIGGER = ConfigManager.linerDriving
			.getTriggerDistance();

	public OnLineThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.jsfs = ConfigManager.linerDriving.getTimeOrDistance();
		this.dRangeOut = ConfigManager.linerDriving.getEndDistance();// StaticVariable.CARPARM_BUS_JSJL;
		this.iTimeOut = ConfigManager.linerDriving.getEndTime();// StaticVariable.CARPARM_BUS_JSSJ;
		RANGETIGGER = ConfigManager.linerDriving.getTriggerDistance();// StaticVariable.CARPARM_BUS_CFJL;
	}

	public synchronized void run() {
		try {
			MediaPlay.getInstance().play("zxxs.wav");
			Thread curt = Thread.currentThread();
			// Log.debug("直线行驶线程被唤�?);
			this.isPause = false;
			this.startTime = System.currentTimeMillis();
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
		MediaPlay.getInstance().play("zxxs_finish.wav");
		this.window.remove(this);
		if (!this.isBreakFlag) {
			judge();
			sendEndMessage(3);
		} else {
			sendEndMessage(3, 1);
		}
	}

	public void execute() {
		JudgeSignal carSignal = JudgeSignal.getInstance();
		if((int)carSignal.gpsspeed>0){
			this.curRange += Tools.getDistinceByOBDV(carSignal.gpsspeed, 200);
			double angle = carSignal.wheelangle;
			if ((this.curRange > 10.0D) && (// 车�?
					(carSignal.gpsspeed < ConfigManager.linerDriving.getMaxSpeed()) || (carSignal.gpsspeed > ConfigManager.linerDriving
							.getMinSpeed()))) {
				this.csbd = true;
			}
			if (Math.abs(angle) > MAX_ANGLE) {
				this.turn = true;
				this.runFlag = false;
			}
		}
		else
		{
			this.csbd=true;
		}
	
	}
	public void judge() {
		if (!ConfigManager.linerDriving.isOpen())
			return;
		if ((!this.online_40301) && (this.turn)) {
			this.online_40301 = true;
			sendMessage("40301", 3);
		}
		if ((!this.online_30116) && (this.csbd)) {
			this.online_30116 = true;
			sendMessage("30116", 3);
		}
	}
}