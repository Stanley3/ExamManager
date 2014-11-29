package com.scu.Contral;

/*  通用评判   */
import com.scu.GlobelControl.ConfigManager;
import com.scu.Signal.*;
import com.scu.Utils.*;
import com.scu.Model.ExamWindow;

public class GolbalExamThread extends ModuleThread {
	// �?��初始化时�?
	private long startTime = System.currentTimeMillis();
	// 车辆�?��角度
	private double satrtAngle = JudgeSignal.getInstance().gpsangle;
	// 车辆运行中的角度
	private double endAngle;
	private boolean isEngineDown = false;
	private boolean isHighFouth = false;
	private boolean isHighSixTH = false;
	private boolean isHightGear = false;// 判断是高档还是低�?
	/* 不按规定使用安全带或者安全头�?*/
	private boolean drive_30101 = false;
	/* 行驶中空挡滑�?*/
	private boolean drive_30112 = false;
	/* 使用挡位与车速长时间不匹配，造成车辆发动机转速过高或过低 */
	private boolean drive_30110 = false;
	/* 因操作不当�?成发动机熄火�?�� */
	private boolean drive_30210 = false;
	/* 不能根据交�?情况合理选择行驶车道、�?�?*/
	private boolean drive_30109 = false;
	/* 违反交�?安全法律、法规，影响交�?安全 */
	private boolean drive_30114 = false;
	/* 制动、加速踏板使用错�?*/
	private boolean drive_30126 = false;
	/* 不能正确�?��灯光 */
	private boolean drive_41601 = false;
	/* 车辆在行驶中低头看挡或连�?次挂挡不�?*/
	private boolean drive_30111 = false;
	/* 发动机开启时�?*/
	private long enginerStartTime = System.currentTimeMillis();
	private double lowEnginerTime = 0.0D;
	/* 档位不匹�?*/
	private boolean notMatchGear = false;
	/* 1�?档不匹配 */
	private boolean gear12noMatch = false;
	/* 空挡�?��时间 */
	private long noGearStartTime = System.currentTimeMillis();
	/* 空挡持续时间 */
	private long noGearTime = 0;
	/* 空挡 */
	private boolean noGear = false;
	/* 左转向灯没有关闭 */
	private boolean zzdnoClose = false;
	/* 灯打�?���?*/
	private long lightOpenTime = 0L;
	/* 灯开始关闭时�?*/
	private long lightOffStartTime = 0L;
	/* 档位标示 */
	private int preGear = 0;
	/* 当前档位下运行的距离 */
	private double gearDistince = 0.0D;
	/* 汽车的信�?*/
	JudgeSignal carSignal = JudgeSignal.getInstance();
	/* 打开灯的时间 */
	private long turnLightTime = 0;
	/* 关灯�?��时间 */
	private long turnlightOffStartTime = 0L;
	/* �?���?��时间 */
	private long lastlightOpenTime = System.currentTimeMillis();
	/* �?��3S结束时间 */
	private long turnLight3SEndTime = System.currentTimeMillis();
	/* 是否�?��3�?*/
	private boolean turnLight3S = false;
	private long lastturnlightpp = 0L;
	/* 当前档位 */
	private int curGear = 0;
	/* 档位改变时间 */
	private long gearchangetime = 0L;
	private int nogear0 = 0;
	private int pre0Gear = 0;
	private int iGear=0;
	//判断速度是否匹配
	private boolean noMatch=false;
	//当前速度
	private double curspeed=0.0;
	public GolbalExamThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
	}
	public synchronized void run() {
		try {
			while (this.runFlag) {
				try {
					// �?��评判时间（延迟评判）
					if(super.moduleFlag==2){
						if (System.currentTimeMillis() - this.startTime > 10000L) {
							Thread.sleep(200L);
							execute();
						}
					}else{
						Thread.sleep(200L);
						execute();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}
		} catch (Exception localException) {
		}
		if ((!this.isHighFouth) || (this.isHighSixTH) || (!this.isHightGear)) {
			sendMessage("30116", 20);// 不按规定速度行驶
		}
		this.window.remove(this);
		sendEndMessage(20);
	}
	public void Break() {
		this.isBreakFlag = true;
	}
	public void execute() {
		// 通过OBD得到20ms内运行的距离
		this.curRange += Tools.getDistinceByOBDV(this.carSignal.gpsspeed, 200);
		if (this.carSignal.gear >= ConfigManager.addClass
				.getCARPARM_GOLBAL_KSZDDW()) {
			this.isHightGear = true;// 大于等于四档表示高档
		}
		if (this.carSignal.gpsspeed > ConfigManager.commonConfig.getMaxSpeed()) {
			/* 如果速度大于�?��车�? */
			this.isHighSixTH = true;
		}
		/* 四档车�?大于考试�?��速度 */
		if ((!this.isHighFouth)
				&& (this.carSignal.gpsspeed >= ConfigManager.commonConfig
						.getMaxSpeed())) {
			this.isHighFouth = true;
		}
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
		/**
		 * 车�?不为零时，进行一系列判断
		 * */
		if (this.carSignal.gpsspeed > 0.0D) {
			/**
			 * 计算并判断空挡的时间是否与系统要求一�?
			 * */
			if (this.carSignal.gear == 0) {
				/* 如果是空挡计算空挡滑行时�?*/
				this.noGearTime = ((System.currentTimeMillis() - this.noGearStartTime));
			}
			/* 离合器信号无 ，无法判断踩离合器时间段内的运行距离 */
			// else if (this.carSignal.signal_clutchpedal)
			// {
			// this.noGearTime = ((System.currentTimeMillis() -
			// this.noGearStartTime) / 1000L);
			// }
			else {
				/* 不是空挡就重置空挡滑行时�?*/
				this.noGearStartTime = System.currentTimeMillis();
				this.noGearTime = 0;
			}
			/* 判断是否超出时间限制 */
			if (this.noGearTime >= ConfigManager.commonConfig.getKongDangTime())
				this.noGear = true;
		}
		/**
		 * 车�?不为零时，进行一系列判断，更新发动机�?��启动时间和空挡开始时�?
		 * */
		else {
			this.noGearStartTime = System.currentTimeMillis();
		}
		/**
		 * 判断一档二档行驶的距离
		 */
//		if (this.carSignal.gear != this.preGear) {
//			// 刚启�?
//			this.preGear = this.carSignal.gear;
//			this.gearDistince = 0.0D;
//		} else {
//			this.gearDistince += Tools.getDistinceByOBDV(
//					this.carSignal.gpsspeed, 200);
//			if ((this.preGear == 1)
//					&& (this.gearDistince > ConfigManager.addClass.CARPARM_GOLBAL_1DJL))
//				this.gear12noMatch = true;
//			if ((this.preGear == 2)
//					&& (this.gearDistince > ConfigManager.addClass.CARPARM_GOLBAL_2DJL)) {
//				this.gear12noMatch = true;// 1�?档不匹配
//			}
//		}
		/**
		 * 判断通用评判中的转向灯信号以及持续的时间
		 */
//		if ((this.carSignal.lamp_left) || (this.carSignal.lamp_right)) {
//			// 如果灯开�?��计算�?��时间
//			this.lightOpenTime = this.lightOpenTime + 200L;
//		} else {
//			// 如果灯关了判断开灯时间是否小于预定时�?s
//			this.lightOffStartTime += 200L;
//			if (this.lightOffStartTime > ConfigManager.addClass
//					.getLightOffTime()) {
//				this.lightOpenTime = 0L;
//				this.lightOffStartTime = 0L;
//			} else {
//				this.lightOpenTime = (long) (this.lightOpenTime + 200);
//			}
//		}

		/* 左转向灯不能�?��超过10S*/
//		if ((!this.zzdnoClose)
//				&& (this.lightOpenTime > ConfigManager.addClass
//						.getCARPARM_GOLBAL_ZDDBGBJL())) {
//			this.zzdnoClose = true;
//		}
		// 左转向开启或者右转向�?��
		if ((this.carSignal.lamp_left) || (this.carSignal.lamp_right)) {
			this.lastlightOpenTime = System.currentTimeMillis();
			this.turnLightTime += 200;
			this.turnlightOffStartTime = 0L;
			// 如果灯开启时间大�?S
			if (this.turnLightTime >= ConfigManager.commonConfig
					.getTurnLightWaitTime()&&!this.turnLight3S) {
				this.turnLight3S = true;
				this.turnLight3SEndTime = System.currentTimeMillis();
			}
		} 
		else 
		{
			// 左右转向灯均未开�?
			this.turnlightOffStartTime += 200L;// 关灯时间，如果关灯时间大�?秒，则初始化�?��时间
			if (this.turnlightOffStartTime > ConfigManager.addClass.getLightOffTime())
				this.turnLightTime = 0;
			else {
				this.turnLightTime += 200L;
			}
			//--------------------------------------------------------
			if (this.turnLightTime >=  ConfigManager.commonConfig.getOpenTurnLightTime()&&!this.turnLight3S) {
				//sendMessage("30206", 20);
				this.turnLight3S = true;
			}
		}
		//-----------------------------------------------------------
		/*如果打左转向灯结束时间时间大�?5秒，并且角度发生了变化就更新当前角度*/
//		if (this.carSignal != null && (int) this.carSignal.gpsspeed > 0
//				&& (Math.abs(this.carSignal.gpsangle - this.satrtAngle) >= ConfigManager.changeLane
//						.getOffsetAngle())) {
//			if(this.turnLight3SEndTime<15000L)
//			{
//				this.satrtAngle=this.carSignal.gpsangle;
//			}
//			else
//			{
//				sendMessage("30206", 20);
//			}
//		}
		// 档位发生了变�?
		/* 难道变档�?��时间信号和现实不同步 */
		if (this.curGear != this.carSignal.gear) {
			this.gearchangetime += 200L;
		}
		/* 变档时间大于1�?*/
		if (this.gearchangetime >= 1000L) {
			this.curGear = this.carSignal.gear;
			if ((this.curGear == this.pre0Gear) && (!this.drive_30111)) {
				this.drive_30111 = true;// 车辆在行驶中低头看挡或连�?次挂挡不�?
				sendMessage("30111s", 20);
			}
			if (this.curGear > 0)
				this.pre0Gear = this.curGear;
			this.gearchangetime = 0L;
			this.nogear0 = this.curGear;
		}
		judge();
	}

	public void judge() {
		/* 不按规定使用安全带或者安全头�?*/
		if(super.moduleFlag==1){
			if ((!this.drive_30101) && (this.carSignal.signal_seatbelt)) {
				this.drive_30101 = true;
				sendMessage("30101", 20);
			}
		}
		else
		{
			
		}
		
		// 行驶中空挡滑�?
		if ((!this.drive_30112) && (this.noGear)) {
			this.drive_30112 = true;
			sendMessage("30112", 20);
		}
		/* 使用挡位与车速长时间不匹配，造成车辆发动机转速过高或过低 */
		if ((!this.drive_30110) && (this.notMatchGear)) {
			this.drive_30110 = true;
			sendMessage("30110", 20);
		}
		/* 因操作不当�?成发动机熄火�?�� */
		if ((!this.drive_30210) && (this.isEngineDown)) {
			this.drive_30210 = true;
			sendMessage("30210", 20);
		}
		/* 不能根据交�?情况合理选择行驶车道、�?�?*/
		if ((!this.drive_30109) && (this.gear12noMatch)) {
			this.drive_30109 = true;
			sendMessage("30109", 20);
		}
		/* 违反交�?安全法律、法规，影响交�?安全 */
		if ((!this.drive_30114) && (this.zzdnoClose)) {
			this.drive_30114 = true;
			sendMessage("30114", 20);
		}
		/* 制动、加速踏板使用错�?*/
		// /* ？？？？�?*/ if ((this.carSignal != null) &&
		// (this.carSignal.gpsspeed== 0.0D) && (this.carSignal.n >
		// geStaticVariable.SPEED_ENGINE_HIGH) &&
		// (!this.drive_30126)) {
		// this.drive_30126 = true;
		// sendMessage("30126", 20);
		// }
		/* 起步、转向�?变更车道、超车�?停车前不使用或错误使用转向灯 */
//		if ((/* StaticVariable.CARPARM_SYSTEM_RCBDPPSFKQ */true)
//				&&
//				/* 车轮的角度没有更�?*/
//				(this.carSignal != null && (int) this.carSignal.gpsspeed > 0)
//				&& (Math.abs(this.carSignal.gpsangle - this.satrtAngle) >= ConfigManager.changeLane
//						.getOffsetAngle())) {
//			if (System.currentTimeMillis() - this.lastlightOpenTime >= 15000L) {
//				if (System.currentTimeMillis() - this.lastturnlightpp >= 15000L) {
//					this.lastturnlightpp = System.currentTimeMillis();
//					sendMessage("30205", 20);
//					//起步、转向�?变更车道、超车�?停车前不使用或错误使用转向灯
//				}
//			}// 起步、转向�?变更车道、超车�?停车前，�?��向灯少于3s即转�?
//			else if (((!this.turnLight3S) || (System.currentTimeMillis()
//					- this.turnLight3SEndTime >= 15000L))
//					&& (System.currentTimeMillis() - this.lastturnlightpp >= 15000L)) {
//				this.lastturnlightpp = System.currentTimeMillis();
//				sendMessage("30206", 20);
//			}
//		}
		/* 不能正确�?��灯光 */
		/* 夜间行驶 */
		if ((ConfigManager.addClass.isYkms()) && (!this.drive_41601)
				&& (!this.carSignal.lamp_near)
				&& (!this.carSignal.lamp_highbeam)) {
			this.drive_41601 = true;
			sendMessage("41601", 13);
		}
	}
}