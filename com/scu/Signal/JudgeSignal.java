package com.scu.Signal;

import java.util.ArrayList;
import java.util.List;

import com.scu.Thread.SignalReaderThread;
 
/**
 * 
 * @author �˽���
 * @version 1.0
 * <p>
 * ����ı�����Ӧ�����ĸ����źţ������ʹ��getInstance()����һ��ʵ����
 * ��lamp_xxxx����ʾĳ�ֵƹ��źţ�false��ʾ�õƹأ�true��ʾ�õƿ���
 *
 */
public class JudgeSignal
{
   private static JudgeSignal instance = null;
   
   /**
    * ʾ����źţ����޸��źţ�Ĭ��Ϊfalse
    */
   public boolean lamp_width = false;
   /**
    * Զ����ź�
    */
   public boolean lamp_highbeam = false;
   /**
    * ������ź�
    */
   public boolean lamp_near = false;
   /**
    * ��ת����ź�, �����źţ��������0.9�룬�ر�֮���������ʧ
    */
   public  boolean lamp_left = false;
   /**
    * ��ת����źţ� �����źţ��������0.9�룬�ر�֮���������ʧ
    */
   public boolean lamp_right = false;
   /**
    * ����ź�
    */
   public boolean lamp_fog = false;
   /**
    * ��ʾ���źŻ�˫���źţ�����ת���ͬʱ����
    */
   public boolean lamp_urgent = false;
 
   /**
    * �����źţ�false��ʾ���ſ���true��ʾ���Źأ����޸��źţ�Ĭ��Ϊtrue
    */
   public boolean signal_door = true;
   /**
    * ��ȫ�������źţ�true��ʾδ���룬false��ʾ��ȫ������
    */
   public boolean signal_seatbelt = false;
   /**
    * ������źţ� false��ʾ�ɿ��������true��ʾ���������
    */
   public boolean signal_clutchpedal = true;
   /**
    * ��ɲ�źţ� false��ʾ�ɿ���ɲ��true��ʾ����ɲ
    */
   public boolean signal_handbrake = false;
   /**
    * ��ɲ�źţ� �����˱�ʾ�ɿ���ɲ��true��ʾ���½�ɲ
    */
   public boolean signal_footbrake = false;
   /**
    * ����ɲ�źţ�true��ʾ���¸���ɲ��һ����⵽�źžͿ�100�֣�����ɲ�ɿ������ź�
    */
   public boolean signal_deputybrake = false;
   /**
    * ǰ���ո��źţ� false��ʾδ���ܵ�ǰ���ո��źţ�true��ʾ���յ����źţ�
    * ���յ����ź�֮����Ҫʹ��������ȡ�����źš�
    */
   public boolean signal_frontbumper = false;
   /**
    * ���ո��źţ� false��ʾδ���յ����ո��źţ�true��ʾ���յ����źţ�
    * ���յ����ź�֮����Ҫʹ��������ȡ�����źš�
    */
   public boolean signal_rearbumper = false;
   /**
    * �����źţ�true��ʾ�������ȿ��أ� ��������һֱ������źţ�ֱ���ɿ����ȿ��ء�
    */
   public boolean signal_horn = false;
   /**
    * Ϩ���źţ�false��ʾ����������true��ʾ����Ϩ��
    */
   public boolean signal_off = false;
   /**
    * �����������źţ��ݻ�ȡ�������źţ�Ĭ��Ϊtrue
    */
   public boolean signal_acc = true;
   /**
    * gps�Ƿ�����źţ�true��ʾgps������false��ʾgps�ݲ�����
    */
   public boolean gps = false;
   /**
    * tcp�����Ƿ�����źţ�true��ʾtcp������false��ʾtcpδ����
    */
   public boolean tcp = false;
   
   /**
    * �����̽Ƕȣ���λ��(��)
    */
   public double wheelangle = 0.0D;
   /**
    * ��λ��Ϣ��ֵ��ʾ���ڵĵ�λ������0��ʾ�յ���-1��ʾ����
    */
   public int gear = 0;
   /**
    * ����ֵ����λ��(��)
    */
   public double lon = 0.0D;
   /**
    * γ��ֵ����λ��(��)
    */
   public double lat = 0.0D;
   /**
    * ������ʻʱ����˳ʱ��������������ĽǶȣ���λ��(��)
    */
   public double gpsangle = 0;
   /**
    * �������ٶ�ֵ����λkm/h(km/h)
    */
   public double gpsspeed = 0.0D;
   /**
    * ��������ת�٣��ݻ�ȡ������ֵ��Ĭ��Ϊ0
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
	 * @return �ö�ʱ���ڵ������ź�
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
				SignalReaderThread.signalCode = ""; //�����ȡ���ظ����źţ����յ��ź�֮����ÿ�
			}
		}
		return list;
	}
	
	private String praseSignal(String signal)
	{
		signal = signal.replace(" ", "").trim();
		if(signal.equalsIgnoreCase(Signal.BEAMLIGHTOFF))
			return "beamLightOff"; //����ƹ�
		else if(signal.equalsIgnoreCase(Signal.BEAMLIGNTON))
			return "beamLightOn"; //����ƿ�
		else if(signal.equalsIgnoreCase(Signal.DEPUTYFOOTBRAKE))
			return "deputyFootBrake"; //����ɲ
		else if(signal.equalsIgnoreCase(Signal.FIRSTGEAR))
			return "firstGear"; //һ��
		else if(signal.equalsIgnoreCase(Signal.FOGLAMPON))
			return "fogLampOn"; //��ƿ�
		else if(signal.equalsIgnoreCase(Signal.FOGLAMPOFF))
			return "fogLampOff"; //��ƹ�
		else if(signal.equalsIgnoreCase(Signal.FOOTBRAKEOFF))
			return "footBrakeOff"; //�ɿ���ɲ
		else if(signal.equalsIgnoreCase(Signal.FOOTBRAKEON))
			return "footBrakeOn"; //���½�ɲ
		else if(signal.equalsIgnoreCase(Signal.FOURTHGEAR))
			return "fourthGear"; //�ĵ�
		else if(signal.equalsIgnoreCase(Signal.FRONTBUMPER))
			return "frontBumper"; //ǰ���ո�
		else if(signal.equalsIgnoreCase(Signal.HANDBRAKEOFF))
			return "handBrakeOff"; //�ɿ���ɲ�� ����ǰ������ɲ
		else if(signal.equalsIgnoreCase(Signal.HANDBRAKEON))
			return "handBrakeOn"; //����ɲ������ͣ���ƶ�
		else if(signal.equalsIgnoreCase(Signal.HIGHBEAMOFF))
			return "highBeamOff"; //Զ��ƹ�
		else if(signal.equalsIgnoreCase(Signal.HIGHBEAMON))
			return "highBeamOn"; //Զ��ƿ�
		else if(signal.equalsIgnoreCase(Signal.HOOTER))
			return "hooter"; //������
		else if(signal.equalsIgnoreCase(Signal.LEFTLAMP))
			return "leftLamp"; //��ת��ƣ���/��ת֮ǰ�����˵�
		else if(signal.equalsIgnoreCase(Signal.RIGHTLAMP))
			return "rightLamp"; // ��ת��ƣ���ת֮ǰ�����˵�
		else if(signal.equalsIgnoreCase(Signal.OFF))
			return "off"; //Ϩ��
		else if(signal.equalsIgnoreCase(Signal.REARBUMPER))
			return "rearBumper"; //���ո�
		else if(signal.equalsIgnoreCase(Signal.REVERSEOFF))
			return "reverseOff"; //����
		else if(signal.equalsIgnoreCase(Signal.REVERSEON))
			return "reverseOn";
		else if(signal.equalsIgnoreCase(Signal.SEATBELT))
			return "seatBelt"; //��ȫ�������յ����źű�ʾδϵ��ȫ����3s����һ��
		else if(signal.equalsIgnoreCase(Signal.SECONDGEAR))
			return "secondGear"; //����
		else if(signal.equalsIgnoreCase(Signal.THIRDGEAR))
			return "thirdGear"; //����
		else if(signal.equalsIgnoreCase(Signal.WARNINGLIGHT))
			return "warningLigtht"; //��ʾ�ƣ��ֳơ�˫���źŵơ����ڳ���ʱʹ��
		else
			return "unknownSignal";  //δ֪���ź�
	}
	public JudgeSignal creatNew()
	{
		return new JudgeSignal();
	}
}
 
