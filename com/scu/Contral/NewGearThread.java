package com.scu.Contral;
/*   加减当操�? */
import com.scu.GlobelControl.ConfigManager;
import com.scu.Signal.*;
import com.scu.Utils.*;
import com.scu.Media.MediaPlay;
import com.scu.Model.ExamWindow;

public class NewGearThread extends ModuleThread {
	boolean erDangFalg=false;	
	/* 程序执行状�?状�?标示，初始�?设为-1 */
	private int iState = -1;
	/* 车辆运行档位和�?度不匹配 */
	private boolean gear_40402 = false;
	/* 未按指令进行加减当操�?*/
	private boolean gear_40401 = false;
	/* 不匹�?*/
	private boolean noMatch = false;
	/* 不匹配时�?*/
	private long noMatchTime = 0L;
	/* 档位 */
	private int iGear = 0;
	public int type = 0;
	/* 触发距离 */
	public static double RANGETIGGER = ConfigManager.plusSubstractDang
			.getTriggerDistance();
	/* 程序�?��执行时的时间 */
	private long timer = System.currentTimeMillis();
	public static int INTCZ = 3000;
	/* 下一档位 */
	private int nextGear = 0;
	/* 加减档标示，判断当前是在加档还是在减档操�?1 是加档；2是减�?*/
	private int addordecflag = 1;
	private boolean ismatch = false;
	/* 匹配 */
	double curspeed = 0.0D;
	private boolean flag=true;//标记是否为刚刚进行评判
	private int state;
	public NewGearThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.jsfs = ConfigManager.plusSubstractDang.getTimeOrDistance();// StaticVariable.CARPARM_BUS_JSFS;
		this.dRangeOut = ConfigManager.plusSubstractDang.getEndDistance();// StaticVariable.CARPARM_BUS_JSJL;
		this.iTimeOut = ConfigManager.plusSubstractDang.getEndTime();// StaticVariable.CARPARM_BUS_JSSJ;
		RANGETIGGER = ConfigManager.plusSubstractDang.getTriggerDistance();// StaticVariable.CARPARM_BUS_CFJL;
	}

	public synchronized void run() {
		try {
			// 加减档操�?
			play("jjdcz.wav");
			Thread.sleep(3000);
			while(!erDangFalg&&!isOut())
			{
				play("jjd_add2.wav");
				Thread.sleep(3000);
					erDangFalg=true;
			}
			this.addordecflag = 1;
			this.nextGear = 2;//将下�?��位置�?
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
		MediaPlay.getInstance().play("end_jjdcz.wav");
		this.window.remove(this);
		if (!this.isBreakFlag) {
			// 是否线程运行被阻�?
			judge();
			sendEndMessage(14);
		} else {
			sendEndMessage(14, 1);
		}
	}

	public void execute() {
		System.out.println("dangwei  "+this.iGear);
		/* 计算当前距离 */
		this.curRange += Tools.getDistinceByOBDV(
				JudgeSignal.getInstance().gpsspeed, 200);
		/* 得到当前速度 */
		this.curspeed = JudgeSignal.getInstance().gpsspeed;
		/*加减档开始时当前档位不为二档*/
		if(JudgeSignal.getInstance().gear!=2&&flag)
		{
			sendMessage("40401", 14);
			this.runFlag=false;
			return;
		}
		flag=false;
		/* 当前档位 */
		int curGear = JudgeSignal.getInstance().gear;
		if (JudgeSignal.getInstance().signal_clutchpedal)// 离合器信�?
			this.iGear = curGear;
		/* 按照当前档位进行判断是否和当前�?度匹�?*/
		switch (this.iGear) {
		case 1:
			if (this.curspeed <= ConfigManager.plusSubstractDang.getMaxDang1())
				break;
			this.noMatch = true;
			break;
		case 2:
			if (this.curspeed <= ConfigManager.plusSubstractDang.getMaxDang2())
				break;
			this.noMatch = true;
			break;
		case 3:
			if ((this.curspeed <= ConfigManager.plusSubstractDang.getMaxDang3())
					&& (this.curspeed >= ConfigManager.plusSubstractDang
							.getMinDang3()))
				break;
			this.noMatch = true;
			break;
		case 4:
			if (this.curspeed >= ConfigManager.plusSubstractDang.getMinDang4())
				break;
			this.noMatch = true;
			break;
		case 5:
			if (this.curspeed >= ConfigManager.plusSubstractDang.getMinDang5())
				break;
			this.noMatch = true;
			break;
		}
		/*
		 * 判断不匹配时间是否在允许范围之内 StaticVariable.CARPARM_GOLBAL_DDGZSKFSJ ==20
		 */
		if ((ConfigManager.plusSubstractDang.isOpen())
				&& ((System.currentTimeMillis() - this.noMatchTime)  > ConfigManager.addClass
						.getCARPARM_GOLBAL_DDGZSKFSJ()) && (this.noMatch)) {
			this.noMatchTime = System.currentTimeMillis();
			this.noMatch = false;
			sendMessage("40402", 14);
		}
      /*更改后的程序*/
		if(this.iGear==this.nextGear)
		{
			switch(this.state)
			{
			case 1:
					play("jjd_dec1.wav");// 加到san档
					this.nextGear = 1;
					break;
			case 2:
				if(this.addordecflag==1)
				{
					play("jjd_add4.wav");// 加到4�?
					this.nextGear = 4;
					this.addordecflag = 1;
					break;
				}
				else
				{
					play("jjd_dec2.wav");// 减到�?��
					this.nextGear = 2;
					this.addordecflag = 2;
					break;
				}
				
			case 4:
				if(this.addordecflag==1)
				{
					play("jjd_dec3.wav");// 加到二档
					this.nextGear = 3;
					this.addordecflag = 2;
					break;
				}
				else
				{
					break;
				}
			}
		}
	}
	private void play(String mp3) {
		MediaPlay.getInstance().play(mp3);
	}
	public void judge() {
		if (!ConfigManager.plusSubstractDang.isOpen())
			return;
		if (this.iGear != 2||!erDangFalg)// 未按指令平稳加减档操�?iState == 4从头到尾按顺序执�?
			sendMessage("40401", 14);
	}
}