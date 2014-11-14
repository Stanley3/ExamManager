package com.scu.Config;
/*
 * 起步配置类
 * 作者：孙晓雨 2014.10.9
 */
import com.scu.Utils.DBHelper;
public class AboardPrepareConfig extends ConfigFather{
	private static AboardPrepareConfig instance= null;
	//以下为需要初始化的参数
	//是否开始此项评判， 默认是开启
	//按时间或距离  1：按时间    0:按距离             初始化为1
	private int timeOrDistance ;
	//结束时间
	private long endTime;
	//结束距离
	private int endDistance;
	//触发距离
	private int triggerDistance;
	//绕车顺序 1：先车前后车后  0：先车后后车前   初始化为  1
	private int around;
	private  boolean isOpen = true;
	private AboardPrepareConfig() {
		timeOrDistance = 1;
		endTime =  120000;
		endDistance = 10;
		triggerDistance = 10;
		around = 1;
	}
	
	public static AboardPrepareConfig getInstance(){
		if(instance == null){
			instance = new AboardPrepareConfig();
		}
		return instance;
	}
	public boolean isOpen() {
		 String res=super.dbHelper.QureyConfig("prepare_sfpp");
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
		dbHelper.updateConfig("prepare_sfpp", pValue);
		this.isOpen = isOpen;
	}
	public int getTimeOrDistance() {
	 String res=super.dbHelper.QureyConfig("prepare_jsfs");
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
		dbHelper.updateConfig("prepare_jsfs", timeOrDistance+"");
		this.timeOrDistance = timeOrDistance;
	}
	public long getEndTime() {
		String res=super.dbHelper.QureyConfig("prepare_jssj");
		endTime=Long.parseLong(res);
		return endTime;
	}
	public void setEndTime(long endTime) {
		dbHelper.updateConfig("prepare_jssj",endTime+"");
		this.endTime = endTime;
	}
	public int getEndDistance() {
		String res=super.dbHelper.QureyConfig("prepare_jsjl");
		endDistance=Integer.parseInt(res);
		return endDistance;
	}
	public void setEndDistance(int endDistance) {
		dbHelper.updateConfig("prepare_jsjl",endDistance+"");
		this.endDistance = endDistance;
	}
	public int getTriggerDistance() {
		String res=super.dbHelper.QureyConfig("prepare_cfjl");
		triggerDistance=Integer.parseInt(res);
		return triggerDistance;
	}
	public void setTriggerDistance(int triggerDistance) {
		dbHelper.updateConfig("prepare_cfjl",triggerDistance+"");
		this.triggerDistance = triggerDistance;
	}
	public int getAround() {
		String res=super.dbHelper.QureyConfig("prepare_rcsx");
		around=Integer.parseInt(res);
		return around;
	}
	public void setAround(int around) {
		dbHelper.updateConfig("prepare_cfjl",around+"");
		this.around = around;
	}
}
