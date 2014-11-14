package com.scu.Config;
import com.scu.Utils.DBHelper;
/**
 * 学校区域配置
 * @author 孙晓雨 2014.10.9
 *
 */
public class SchoolAreaConfig extends ConfigFather{
	private static SchoolAreaConfig instance= null;
	public static SchoolAreaConfig getInstance(){
		if(instance == null){
			instance = new SchoolAreaConfig();
		}
		return instance;
	}
	private SchoolAreaConfig(){
		isOpen = true;
		timeOrDistance = 0;
		endTime = 60000;
		endDistance = 40;
		triggerDistance = 30;
		maxSpeed = 30;
	}
	// 以下为需要初始化的参数
	// 是否开始此项评判， 默认是开启
	private boolean isOpen;
	//按时间或距离  1：按时间    0:按距离             初始化为0
	private int timeOrDistance;
	// 结束时间
	private long endTime;
	// 结束距离
	private int endDistance;
	// 触发距离
	private int triggerDistance;
	// 车速需低于一定值
	private int maxSpeed;
	public boolean isOpen() {
		String res=super.dbHelper.QureyConfig("school_sfpp");
		if(Integer.parseInt(res)==0)
		{
			return false;
		}
		else
		 return true;
	}
	public void setOpen(boolean isOpen) {
		String pValue="";
		if(isOpen)
		{
			pValue="1";
		}
		else
		{
			pValue="0";
		}
		dbHelper.updateConfig("school_sfpp", pValue);
		this.isOpen = isOpen;
	}
	public int getTimeOrDistance() {
		String res=super.dbHelper.QureyConfig("school_jsfs");
		if(Integer.parseInt(res)==1)
		{
			return 1;
		}
		else
		{
			return 2;
		}
	}
	public void setTimeOrDistance(int timeOrDistance) {
		dbHelper.updateConfig("school_jsfs", timeOrDistance+"");
		this.timeOrDistance = timeOrDistance;
	}
	public long getEndTime() {
		String res=super.dbHelper.QureyConfig("school_jssj");
		endTime=Long.parseLong(res);
		return endTime;
	}
	public void setEndTime(long endTime) {
		dbHelper.updateConfig("school_jssj",endTime+"");
		this.endTime = endTime;
	}
	public int getEndDistance() {
		String res=super.dbHelper.QureyConfig("school_jsjl");
		endDistance=Integer.parseInt(res);
		
		return endDistance;
	}
	public void setEndDistance(int endDistance) {
		dbHelper.updateConfig("school_jsjl",endDistance+"");
		this.endDistance = endDistance;
	}
	public int getTriggerDistance() {
		String res=super.dbHelper.QureyConfig("school_cfjl");
		triggerDistance=Integer.parseInt(res);
		return triggerDistance;
	}
	public void setTriggerDistance(int triggerDistance) {
		dbHelper.updateConfig("school_cfjl",triggerDistance+"");
		this.triggerDistance = triggerDistance;
	}
	public int getMaxSpeed() {
		String res=super.dbHelper.QureyConfig("school_cs");
		maxSpeed=Integer.parseInt(res);
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		dbHelper.updateConfig("school_cs",maxSpeed+"");
		this.maxSpeed = maxSpeed;
	}
}
