package com.scu.Signal;

import java.util.ArrayList;
import java.util.List;

import com.scu.Thread.SignalReaderThread;
 
/**
 * 
 * @author 顾建辉
 * @version 1.0
 * <p>
 * 该类的变量对应车辆的各种信号，这个类使用getInstance()创建一个实例。
 * “lamp_xxxx”表示某种灯光信号，false表示该灯关，true表示该灯开。
 *
 */
public class JudgeSignal
{
   private static JudgeSignal instance = null;
   
   /**
    * 示宽灯信号，暂无该信号，默认为false
    */
   public boolean lamp_width = false;
   /**
    * 远光灯信号
    */
   public boolean lamp_highbeam = false;
   /**
    * 近光灯信号
    */
   public boolean lamp_near = false;
   /**
    * 左转向灯信号, 脉冲信号，明暗间隔0.9秒，关闭之后该脉冲消失
    */
   public  boolean lamp_left = false;
   /**
    * 右转向灯信号， 脉冲信号，明暗间隔0.9秒，关闭之后该脉冲消失
    */
   public boolean lamp_right = false;
   /**
    * 雾灯信号
    */
   public boolean lamp_fog = false;
   /**
    * 警示灯信号或双闪信号（左右转向灯同时亮灭）
    */
   public boolean lamp_urgent = false;
 
   /**
    * 车门信号，false表示车门开，true表示车门关，暂无该信号，默认为true
    */
   public boolean signal_door = true;
   /**
    * 安全带插入信号，true表示未插入，false表示安全带插入
    */
   public boolean signal_seatbelt = false;
   /**
    * 离合器信号， false表示松开离合器，true表示踩下离合器
    */
   public boolean signal_clutchpedal = true;
   /**
    * 手刹信号， false表示松开手刹，true表示提手刹
    */
   public boolean signal_handbrake = false;
   /**
    * 脚刹信号， 发烧了表示松开脚刹，true表示踩下脚刹
    */
   public boolean signal_footbrake = false;
   /**
    * 副脚刹信号，true表示踩下副脚刹，一旦检测到信号就扣100分；副脚刹松开不发信号
    */
   public boolean signal_deputybrake = false;
   /**
    * 前保险杠信号， false表示未介绍到前保险杠信号，true表示接收到该信号；
    * 接收到该信号之后，需要使用者自行取消该信号。
    */
   public boolean signal_frontbumper = false;
   /**
    * 后保险杠信号， false表示未接收到后保险杠信号，true表示接收到该信号；
    * 接收到该信号之后，需要使用者自行取消该信号。
    */
   public boolean signal_rearbumper = false;
   /**
    * 喇叭信号，true表示按下喇叭开关； 按着喇叭一直发这个信号，直到松开喇叭开关。
    */
   public boolean signal_horn = false;
   /**
    * 熄火信号，false表示车辆启动，true表示车辆熄火
    */
   public boolean signal_off = false;
   /**
    * 车辆加速器信号，暂获取不到该信号，默认为true
    */
   public boolean signal_acc = true;
   /**
    * gps是否可用信号，true表示gps就绪，false表示gps暂不可用
    */
   public boolean gps = false;
   /**
    * tcp连接是否就绪信号，true表示tcp就绪，false表示tcp未连接
    */
   public boolean tcp = false;
   
   /**
    * 方向盘角度，单位度(°)
    */
   public double wheelangle = 0.0D;
   /**
    * 档位信息，值表示处于的档位，其中0表示空档，-1表示倒档
    */
   public int gear = 0;
   /**
    * 经度值，单位度(°)
    */
   public double lon = 0.0D;
   /**
    * 纬度值，单位度(°)
    */
   public double lat = 0.0D;
   /**
    * 车辆行驶时，按顺时针计算和正北方向的角度，单位度(°)
    */
   public double gpsangle = 0;
   /**
    * 车辆的速度值，单位km/h(km/h)
    */
   public double gpsspeed = 0.0D;
   /**
    * 发动机的转速，暂获取不到该值，默认为0
    */
   public int n = 0;
   public long sigLastUpdate;
 
   private JudgeSignal()
   {
     this.wheelangle = 0.0D;
     this.gear = 0;
     this.lon = 0.0D;
     this.lat = 0.0D;
     this.gpsangle = 0;
     this.gpsspeed = 0.0D;
     this.n = 0;
     this.sigLastUpdate = System.currentTimeMillis();
   }
   public static JudgeSignal getInstance() {
     if (instance == null)
      instance = new JudgeSignal();
 //    	System.out.println(lamp_left);
     return instance;
   }
   
   /**
	 * 
	 * @param timeLength
	 * @return 该段时间内的所有信号
	 */
	public List<?> getSignal(int length)
	
	{
		List<String> list = new ArrayList<String>();
		long currentTime = System.currentTimeMillis();
		while(System.currentTimeMillis() <= (currentTime + length))
		{
			if(!SignalReaderThread.signalCode.isEmpty())
			{
				list.add(praseSignal(SignalReaderThread.signalCode));
				SignalReaderThread.signalCode = ""; //以免获取到重复的信号，接收到信号之后就置空
			}
		}
		return list;
	}
	
	private String praseSignal(String signal)
	{
		signal = signal.replace(" ", "").trim();
		if(signal.equalsIgnoreCase(Signal.BEAMLIGHTOFF))
			return "beamLightOff"; //近光灯关
		else if(signal.equalsIgnoreCase(Signal.BEAMLIGNTON))
			return "beamLightOn"; //近光灯开
		else if(signal.equalsIgnoreCase(Signal.DEPUTYFOOTBRAKE))
			return "deputyFootBrake"; //副脚刹
		else if(signal.equalsIgnoreCase(Signal.FIRSTGEAR))
			return "firstGear"; //一档
		else if(signal.equalsIgnoreCase(Signal.FOGLAMPON))
			return "fogLampOn"; //雾灯开
		else if(signal.equalsIgnoreCase(Signal.FOGLAMPOFF))
			return "fogLampOff"; //雾灯关
		else if(signal.equalsIgnoreCase(Signal.FOOTBRAKEOFF))
			return "footBrakeOff"; //松开脚刹
		else if(signal.equalsIgnoreCase(Signal.FOOTBRAKEON))
			return "footBrakeOn"; //踩下脚刹
		else if(signal.equalsIgnoreCase(Signal.FOURTHGEAR))
			return "fourthGear"; //四档
		else if(signal.equalsIgnoreCase(Signal.FRONTBUMPER))
			return "frontBumper"; //前保险杠
		else if(signal.equalsIgnoreCase(Signal.HANDBRAKEOFF))
			return "handBrakeOff"; //松开手刹， 开车前需松手刹
		else if(signal.equalsIgnoreCase(Signal.HANDBRAKEON))
			return "handBrakeOn"; //提手刹，用于停车制动
		else if(signal.equalsIgnoreCase(Signal.HIGHBEAMOFF))
			return "highBeamOff"; //远光灯关
		else if(signal.equalsIgnoreCase(Signal.HIGHBEAMON))
			return "highBeamOn"; //远光灯开
		else if(signal.equalsIgnoreCase(Signal.HOOTER))
			return "hooter"; //鸣喇叭
		else if(signal.equalsIgnoreCase(Signal.LEFTLAMP))
			return "leftLamp"; //左转向灯，起步/左转之前必须打此灯
		else if(signal.equalsIgnoreCase(Signal.RIGHTLAMP))
			return "rightLamp"; // 右转向灯，右转之前必须打此灯
		else if(signal.equalsIgnoreCase(Signal.OFF))
			return "off"; //熄火
		else if(signal.equalsIgnoreCase(Signal.REARBUMPER))
			return "rearBumper"; //后保险杠
		else if(signal.equalsIgnoreCase(Signal.REVERSEOFF))
			return "reverseOff"; //倒档
		else if(signal.equalsIgnoreCase(Signal.REVERSEON))
			return "reverseOn";
		else if(signal.equalsIgnoreCase(Signal.SEATBELT))
			return "seatBelt"; //安全带，接收到该信号表示未系安全带，3s提醒一次
		else if(signal.equalsIgnoreCase(Signal.SECONDGEAR))
			return "secondGear"; //二档
		else if(signal.equalsIgnoreCase(Signal.THIRDGEAR))
			return "thirdGear"; //三档
		else if(signal.equalsIgnoreCase(Signal.WARNINGLIGHT))
			return "warningLigtht"; //警示灯，又称“双闪信号灯”，在超车时使用
		else
			return "unknownSignal";  //未知的信号
	}
	public JudgeSignal creatNew()
	{
		return new JudgeSignal();
	}
}
 
