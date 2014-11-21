package com.scu.Config;
import com.scu.Utils.DBHelper;
/*
 * ͨ����������
 * ���ߣ������� 2014.10.9
 */
public class CommonConfig extends ConfigFather{
	
	private static CommonConfig instance = null;
	public static CommonConfig getInstance()
	{
		if(instance == null){
			instance = new CommonConfig();
		}
		return instance;
	}
//����Ϊ��Ҫ��ʼ���Ĳ��������ɵ�
	
	//����Ƶȴ�ʱ���ʼ��Ϊ3��
	private long turnLightWaitTime;
	//���������� ,��λ:RPM
	private int engineIdleSpeed;
	//���ת��,��λ: RPM
	private int maxRoundSpeed;
	//�յ�����ʱ��,��λ:��  
	private long kongDangTime;
	//�͵���ת���ظ��۷�ʱ��,��λ:��  
	private long repeatPoint;
	//��ʱ�䲻�ر�ת���ʱ��,��λ:��  
	private long openTurnLightTime;
	//������� ��λ:��
	private int examLength;
	//ȫ����߳���, ��λ: ����/Сʱ
	private int maxSpeed;
	//��λ�ﵽ�ĵ���ʱ�� �����Ƿ�ﵽ40����ÿСʱ
	private int Dang;
	private int SpeedNeed;
	
	private CommonConfig() {
		turnLightWaitTime = 3000 ;
		engineIdleSpeed = 1000;
		maxRoundSpeed = 2500;
		kongDangTime = 5000;
		repeatPoint = 20000;
		openTurnLightTime = 10000;
		examLength = 3000;
		maxSpeed = 60;
		Dang = 4;
		SpeedNeed =60 ;
	}
	public long getTurnLightWaitTime() {
		String res=super.dbHelper.QureyConfig("golbal_fxdyc3s");
		turnLightWaitTime=Long.parseLong(res);
		return turnLightWaitTime;
	}
	public void setTurnLightWaitTime(long turnLightWaitTime) {
		dbHelper.updateConfig("golbal_fxdyc3s",turnLightWaitTime+"");
		this.turnLightWaitTime = turnLightWaitTime;
	}
	public int getEngineIdleSpeed() {
		String res=super.dbHelper.QureyConfig("golbal_fdjds");
		engineIdleSpeed=Integer.parseInt(res);
		return engineIdleSpeed;
	}
	public void setEngineIdleSpeed(int engineIdleSpeed) {
		dbHelper.updateConfig("golbal_fdjds",engineIdleSpeed+"");
		this.engineIdleSpeed = engineIdleSpeed;
	}
	public int getMaxRoundSpeed() {
		return maxRoundSpeed;
	}
	public void setMaxRoundSpeed(int maxRoundSpeed) {
		this.maxRoundSpeed = maxRoundSpeed;
	}
	public long getKongDangTime() {
		String res=super.dbHelper.QureyConfig("golbal_kdhx");
		kongDangTime=Long.parseLong(res);
		return kongDangTime;
	}
	public void setKongDangTime(long kongDangTime) {
		dbHelper.updateConfig("golbal_kdhx",kongDangTime+"");
		this.kongDangTime = kongDangTime;
	}
	public long getRepeatPoint() {
		String res=super.dbHelper.QureyConfig("golbal_ddgzskfsj");
		repeatPoint=Long.parseLong(res);
		return repeatPoint;
	}
	public void setRepeatPoint(long repeatPoint) {
		dbHelper.updateConfig("golbal_ddgzskfsj",repeatPoint+"");
		this.repeatPoint = repeatPoint;
	}
	public long getOpenTurnLightTime() {
		
		String res=super.dbHelper.QureyConfig("golbal_zxdbgbsj");
		openTurnLightTime=Long.parseLong(res);
		
		return openTurnLightTime;
	}
	public void setOpenTurnLightTime(long openTurnLightTime) {
		dbHelper.updateConfig("golbal_zxdbgbsj",openTurnLightTime+"");
		this.openTurnLightTime = openTurnLightTime;
	}
	public int getExamLength() {
		String res=super.dbHelper.QureyConfig("golbal_kslc");
		openTurnLightTime=Integer.parseInt(res);
		return examLength;
	}
	public void setExamLength(int examLength) {
		dbHelper.updateConfig("golbal_kslc",examLength+"");
		this.examLength = examLength;
	}
	public int getMaxSpeed() {
		String res=super.dbHelper.QureyConfig("golbal_zgcs");
		maxSpeed=Integer.parseInt(res);
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		dbHelper.updateConfig("golbal_zgcs",maxSpeed+"");
		this.maxSpeed = maxSpeed;
	}
	public int getDang() {
		
		return Dang;
	}
	public void setDang(int dang) {
		Dang = dang;
	}
	public int getSpeedNeed() {
		return SpeedNeed;
	}
	public void setSpeedNeed(int speedNeed) {
		SpeedNeed = speedNeed;
	}
	
	

}
