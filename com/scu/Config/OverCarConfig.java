package com.scu.Config;
import com.scu.Utils.DBHelper;
/**
 * 超车配置
 * @author 孙晓雨 2014.10.9
 *
 */
public class OverCarConfig extends ConfigFather{
	private static OverCarConfig instance= null;
	public static OverCarConfig getInstance(){
		if(instance == null){
			instance = new OverCarConfig();
		}
		return instance;
	}
	private OverCarConfig(){
		isOpen = true;
		timeOrDistance = 0;
		endTime = 60000;
		endDistance = 200;
		triggerDistance = 10;
		turnAngle = 15;
		
	}
	// 以下为需要初始化的参数
	// 是否开始此项评判， 默认是开启
	private boolean isOpen = true;
	//按时间或距离结束  1：按时间    0:按距离             初始化为0
	private int timeOrDistance;
	// 结束时间
	private long endTime;
	// 结束距离
	private int endDistance;
	// 触发距离
	private int triggerDistance;
	// 变道触发角度
	private int turnAngle;
	
	
	public boolean isOpen() {
		String res=super.dbHelper.QureyConfig("overtaken_sfpp");
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
		dbHelper.updateConfig("overtaken_sfpp", pValue);
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
		dbHelper.updateConfig("overtaken_jsfs", timeOrDistance+"");
		this.timeOrDistance = timeOrDistance;
	}
	public long getEndTime() {
		String res=super.dbHelper.QureyConfig("overtaken_jssj");
		endTime=Long.parseLong(res);
		return endTime;
	}
	public void setEndTime(long endTime) {
		dbHelper.updateConfig("overtaken_jssj",endTime+"");
		this.endTime = endTime;
	}
	public int getEndDistance() {
		String res=super.dbHelper.QureyConfig("overtaken_jsjl");
		endDistance=Integer.parseInt(res);
		return endDistance;
	}
	public void setEndDistance(int endDistance) {
		dbHelper.updateConfig("overtaken_jsjl",endDistance+"");
		
		this.endDistance = endDistance;
	}
	public int getTriggerDistance() {
		String res=super.dbHelper.QureyConfig("overtaken_cfjl");
		triggerDistance=Integer.parseInt(res);
		return triggerDistance;
	}
	public void setTriggerDistance(int triggerDistance) {
		dbHelper.updateConfig("overtaken_cfjl",triggerDistance+"");
		this.triggerDistance = triggerDistance;
	}
	public int getTurnAngle() {
		String res=super.dbHelper.QureyConfig("overtaken_z");
		turnAngle=Integer.parseInt(res);
		return turnAngle;
	}
	public void setTurnAngle(int turnAngle) {
		dbHelper.updateConfig("overtaken_z",turnAngle+"");
		this.turnAngle = turnAngle;
	}
	
	

}
