package com.scu.Config;
import com.scu.Utils.DBHelper;
/**
 * 路口直行配置
 * @author 孙晓雨 2014.10.9
 */

public class LuKouStraightConfig extends ConfigFather{
	
	private static LuKouStraightConfig instance= null;
	public static LuKouStraightConfig getInstance(){
		if(instance == null){
			instance = new LuKouStraightConfig();
		}
		return instance;
	}
	private LuKouStraightConfig(){
		isOpen = true;
		timeOrDistance = 0;
		endTime = 60000;
		endDistance = 70;
		triggerDistance = 50;
		maxSpeed = 30;
	}
	
	// 以下为需要初始化的参数
	// 是否开始此项评判， 默认是开启
	private boolean isOpen;
	//按时间或距离结束  1：按时间    0:按距离             初始化为1
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
		 String res=super.dbHelper.QureyConfig("crossline_sfpp");
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
		dbHelper.updateConfig("crossline_sfpp", pValue);
		this.isOpen = isOpen;
	}
	public int getTimeOrDistance() {
		String res=super.dbHelper.QureyConfig("crossline_jsfs");
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
		dbHelper.updateConfig("crossline_jsfs", timeOrDistance+"");
		this.timeOrDistance = timeOrDistance;
	}
	public long getEndTime() {
		String res=super.dbHelper.QureyConfig("crossline_jssj");
		endTime=Long.parseLong(res);
		return endTime;
	}
	public void setEndTime(long endTime) {
		dbHelper.updateConfig("crossline_jssj",endTime+"");
		this.endTime = endTime;
	}
	public int getEndDistance() {
		String res=super.dbHelper.QureyConfig("crossline_jsjl");
		endDistance=Integer.parseInt(res);
		return endDistance;
	}
	public void setEndDistance(int endDistance) {
		dbHelper.updateConfig("crossline_jsjl",endDistance+"");
		this.endDistance = endDistance;
	}
	public int getTriggerDistance() {
		String res=super.dbHelper.QureyConfig("crossline_cfjl");
		triggerDistance=Integer.parseInt(res);
		return triggerDistance;
	}
	public void setTriggerDistance(int triggerDistance) {
		dbHelper.updateConfig("crossline_cfjl",triggerDistance+"");
		this.triggerDistance = triggerDistance;
	}
	public int getMaxSpeed() {
		String res=super.dbHelper.QureyConfig("crossline_lkcs");
		maxSpeed=Integer.parseInt(res);
		return maxSpeed;
	}
	public  void setMaxSpeed(int maxSpeed) {
		dbHelper.updateConfig("crossline_lkcs",maxSpeed+"");
		this.maxSpeed = maxSpeed;
	}
}
