package com.scu.GlobelControl;

import com.scu.Config.*;
/**
 * 配置管理类
 * @author 孙晓雨 2014.10.9
 */

public class ConfigManager {
	//初始化各种配置
	public static SignalSourceConfig signalSource = SignalSourceConfig.getInstance();
	public static AutoJudgeConfig autoJadge = AutoJudgeConfig.getInstance();
	public static CommonConfig commonConfig = CommonConfig.getInstance();
	public static AboardPrepareConfig abordPrepare = AboardPrepareConfig.getInstance();
	public static StartCarConfig startCar = StartCarConfig.getInstance();
	public static LinerDrivingConfig linerDriving = LinerDrivingConfig.getInstance();
	public static PlusSubstractDangConfig plusSubstractDang = PlusSubstractDangConfig.getInstance();
	public static ChangeLaneConfig changeLane = ChangeLaneConfig.getInstance();
	public static PullOverConfig pullOver = PullOverConfig.getInstance();
	public static LuKouStraightConfig luKouStraight = LuKouStraightConfig.getInstance();
	public static TurnLeftConfig turnLeft = TurnLeftConfig.getInstance();
	public static TurnRightConfig turnRight = TurnRightConfig.getInstance();
	public static FootWayLinerConfig footWayLiner = FootWayLinerConfig.getInstance();
	public static SchoolAreaConfig schoolArea = SchoolAreaConfig.getInstance();
	public static BusStationConfig busStation = BusStationConfig.getInstance();
	public static MeetingCarConfig meetingCar = MeetingCarConfig.getInstance();
	public static OverCarConfig overCar = OverCarConfig.getInstance();
	public static TurnRoundConfig turnRound = TurnRoundConfig.getInstance();
	public static HandWheelConfig handWheel = HandWheelConfig.getInstance();
	public static AddClass addClass = AddClass.getInstance();
	public static int ITEMSTATE[] = new int[20];
	public static int LINE_ID = 0;
	public static boolean CARPARM_NIGHT_AUTO = true;
	public static boolean CARPARM_NIGHT_YKMS = false;
	public static String EXAMSTARTTIME = null;
	
	
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	
	public static ImageIconSet allImage = new ImageIconSet();
	public static SignalIcon signalIcon = new SignalIcon();
	public static SimuteIcon simuteIcon = new SimuteIcon();
	
	//构造函数
	public ConfigManager() {
		//初始化信号连接代码
	}

	
	
	
	

}
