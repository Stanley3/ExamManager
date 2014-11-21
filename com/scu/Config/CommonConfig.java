package com.scu.Config;
import com.scu.Utils.DBHelper;
/*
 * 通用评判配置
 * 作者：孙晓雨 2014.10.9
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
//以下为需要初始化的参数，均可调
	
	//方向灯等待时间初始化为3秒
	private long turnLightWaitTime;
	//发动机怠速 ,单位:RPM
	private int engineIdleSpeed;
	//最高转速,单位: RPM
	private int maxRoundSpeed;
	//空挡滑行时间,单位:秒  
	private long kongDangTime;
	//低档高转速重复扣分时间,单位:秒  
	private long repeatPoint;
	//长时间不关闭转向灯时间,单位:秒  
	private long openTurnLightTime;
	//考试里程 单位:米
	private int examLength;
	//全程最高车速, 单位: 公里/小时
	private int maxSpeed;
	//档位达到四档的时候， 车速是否达到40公里每小时
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
