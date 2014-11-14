package com.scu.Config;
import com.scu.Utils.DBHelper;
/**
 * 路边停车配置类
 * @author 孙晓雨 2014.10.9
 *
 */
public class PullOverConfig extends ConfigFather{
	private static PullOverConfig instance= null;
	public static PullOverConfig getInstance(){
		if(instance == null){
			instance = new PullOverConfig();
		}
		return instance;
	}
	private PullOverConfig(){
		isOpen = true;
		timeOrDistance = 0;
		endTime = 120000;
		endDistance = 200;
		triggerDistance = 10;
		offsetAngle = 10;
		isEvaluateHandBrake = true;
		isEvaluateShutDown = true;
		
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
	//方向触发角度
	private int offsetAngle;
	//停车后手刹是否评判
	private boolean isEvaluateHandBrake;
	//停车后熄火是否评判
	private boolean isEvaluateShutDown;
	
	
	public boolean isOpen() {
		 String res=super.dbHelper.QureyConfig("stop_sfpp");
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
		dbHelper.updateConfig("stop_sfpp", pValue);
		this.isOpen = isOpen;
	}
	public int getTimeOrDistance() {
		String res=super.dbHelper.QureyConfig("stop_jsfs");
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
		dbHelper.updateConfig("stop_jsfs", timeOrDistance+"");
		this.timeOrDistance = timeOrDistance;
	}
	public long getEndTime() {
		String res=super.dbHelper.QureyConfig("stop_jssj");
		endTime=Long.parseLong(res);
		return endTime;
	}
	public void setEndTime(long endTime) {
		dbHelper.updateConfig("stop_jssj",endTime+"");
		this.endTime = endTime;
	}
	public int getEndDistance() {
		String res=super.dbHelper.QureyConfig("stop_jsjl");
		endDistance=Integer.parseInt(res);
		return endDistance;
	}
	public void setEndDistance(int endDistance) {
		dbHelper.updateConfig("stop_jsjl",endDistance+"");
		
		this.endDistance = endDistance;
	}
	public int getTriggerDistance() {
		String res=super.dbHelper.QureyConfig("stop_cfjl");
		triggerDistance=Integer.parseInt(res);
		return triggerDistance;
	}
	public void setTriggerDistance(int triggerDistance) {
		dbHelper.updateConfig("stop_cfjl",triggerDistance+"");
		this.triggerDistance = triggerDistance;
	}
	public int getOffsetAngle() {
		String res=super.dbHelper.QureyConfig("stop_kbqfxdjd");
		triggerDistance=Integer.parseInt(res);
		return offsetAngle;
	}
	public void setOffsetAngle(int offsetAngle) {
		dbHelper.updateConfig("stop_kbqfxdjd",offsetAngle+"");
		this.offsetAngle = offsetAngle;
	}
	public boolean isEvaluateHandBrake() {
		String res=super.dbHelper.QureyConfig("stop_sssfpp");
		if(res.trim().compareTo("1")==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void setEvaluateHandBrake(boolean isEvaluateHandBrake) {
		String pvalue="";
		if(isEvaluateHandBrake)
		{
			pvalue="1";
		}
		else
		{
			pvalue="0";
		}
		dbHelper.updateConfig("stop_sssfpp",pvalue+"");
	}
	public boolean isEvaluateShutDown() {
		String res=super.dbHelper.QureyConfig("stop_xhsfpp");
		if(res.trim().compareTo("1")==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void setEvaluateShutDown(boolean isEvaluateShutDown) {
		String pvalue="";
		if(isEvaluateShutDown)
		{
			pvalue="1";
		}
		else
		{
			pvalue="0";
		}
		dbHelper.updateConfig("stop_xhsfpp",pvalue+"");
		this.isEvaluateShutDown = isEvaluateShutDown;
	}
	
	

}
