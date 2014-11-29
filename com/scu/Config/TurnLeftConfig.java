package com.scu.Config;
import com.scu.Utils.DBHelper;
/**
 * ·����ת������
 * @author ������ 2014.10.9
 *
 */
public class TurnLeftConfig extends ConfigFather{
	private static TurnLeftConfig instance= null;
	public static TurnLeftConfig getInstance(){
		if(instance == null){
			instance = new TurnLeftConfig();
		}
		return instance;
	}
	private TurnLeftConfig(){
		isOpen = true;
		timeOrDistance = 0;
		endTime = 60000;
		endDistance = 150;
		triggerDistance = 80;
		maxSpeed = 30;
		minAngle = 70;
	}
	// ����Ϊ��Ҫ��ʼ���Ĳ���
	// �Ƿ�ʼ�������У� Ĭ���ǿ���
	private boolean isOpen;
	//��ʱ������  1����ʱ��    0:������             ��ʼ��Ϊ0
	private int timeOrDistance;
	// ����ʱ��
	private long endTime;
	// ��������
	private int endDistance;
	// ��������
	private int triggerDistance;
	// ���������һ��ֵ
	private int maxSpeed;
	//ƫת����С�Ƕ�
	private int minAngle;
	
	public boolean isOpen() {
		 String res=super.dbHelper.QureyConfig("turnleft_sfpp");
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
		dbHelper.updateConfig("turnleft_sfpp", pValue);
		this.isOpen = isOpen;
	}
	public int getTimeOrDistance() {
	 String res=super.dbHelper.QureyConfig("turnleft_jsfs");
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
		dbHelper.updateConfig("turnleft_jsfs", timeOrDistance+"");
		this.timeOrDistance = timeOrDistance;
	}
	public long getEndTime() {
		String res=super.dbHelper.QureyConfig("turnleft_jssj");
		endTime=Long.parseLong(res);
		return endTime;
	}
	public void setEndTime(long endTime) {
		dbHelper.updateConfig("turnleft_jssj",endTime+"");
		this.endTime = endTime;
	}
	public int getEndDistance() {
		String res=super.dbHelper.QureyConfig("turnleft_jsjl");
		endDistance=Integer.parseInt(res);
		return endDistance;
	}
	public void setEndDistance(int endDistance) {
		dbHelper.updateConfig("turnleft_jsjl",endDistance+"");
		this.endDistance = endDistance;
	}
	public int getTriggerDistance() {
		String res=super.dbHelper.QureyConfig("turnleft_cfjl");
		triggerDistance=Integer.parseInt(res);
		return triggerDistance;
	}
	public void setTriggerDistance(int triggerDistance) {
		dbHelper.updateConfig("turnleft_cfjl",triggerDistance+"");
		this.triggerDistance = triggerDistance;
	}
	public int getMaxSpeed() {
		String res=super.dbHelper.QureyConfig("turnleft_cs");
		maxSpeed=Integer.parseInt(res);
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		dbHelper.updateConfig("turnleft_cs",maxSpeed+"");
	this.maxSpeed = maxSpeed;
	}
	public int getMinAngle() {
		String res=super.dbHelper.QureyConfig("turnleft_z");
		minAngle=Integer.parseInt(res);
	return minAngle;
	}
	public void setMinAngle(int minAngle) {
		dbHelper.updateConfig("turnleft_z",minAngle+"");
		this.minAngle = minAngle;
	}
	
	
	

}
