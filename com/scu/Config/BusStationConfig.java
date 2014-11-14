package com.scu.Config;
import com.scu.Utils.DBHelper;
/*
 * 公交车站配置类
 * 作者：孙晓雨 2014.10.9
 */

public class BusStationConfig extends ConfigFather{
	
	private static BusStationConfig instance = null;
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
	
	
	private BusStationConfig() {
		isOpen = true;
		timeOrDistance = 0;
		endTime = 60000;
		endDistance = 40;
		triggerDistance = 30;
		maxSpeed = 30;
	}
	public static BusStationConfig getInstance(){
		if(instance == null){
			instance = new BusStationConfig();
		}
		return instance;
	}
	//以下是设置方法
	public boolean isOpen() {
		 String res=super.dbHelper.QureyConfig("bus_sfpp");
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
		dbHelper.updateConfig("bus_sfpp", pValue);
		this.isOpen = isOpen;
		this.isOpen = isOpen;
	}
	public int getTimeOrDistance() {
		dbHelper.updateConfig("bus_jsfs", timeOrDistance+"");
		return timeOrDistance;
	}
	public void setTimeOrDistance(int timeOrDistance) {
		dbHelper.updateConfig("bus_jsfs", timeOrDistance+"");
		this.timeOrDistance = timeOrDistance;
	}
	public long getEndTime() {
		String res=super.dbHelper.QureyConfig("bus_jssj");
		endTime=Long.parseLong(res);
		return endTime;
	}
	public void setEndTime(long endTime) {
		dbHelper.updateConfig("bus_jssj",endTime+"");
		this.endTime = endTime;
	}
	public int getEndDistance() {
		String res=super.dbHelper.QureyConfig("bus_jsjl");
		endDistance=Integer.parseInt(res);
		return endDistance;
	}
	public void setEndDistance(int endDistance) {
		dbHelper.updateConfig("bus_jsjl",endDistance+"");
		this.endDistance = endDistance;
	}
	public int getTriggerDistance() {
		String res=super.dbHelper.QureyConfig("bus_cfjl");
		triggerDistance=Integer.parseInt(res);
		return triggerDistance;
	}
	public void setTriggerDistance(int triggerDistance) {
		dbHelper.updateConfig("bus_cfjl",triggerDistance+"");
		this.triggerDistance = triggerDistance;
	}
	public int getMaxSpeed() {
		String res=super.dbHelper.QureyConfig("bus_cs");
		maxSpeed=Integer.parseInt(res);
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		dbHelper.updateConfig("bus_cs",maxSpeed+"");
		this.maxSpeed = maxSpeed;
	}
	
	
	
	

}
