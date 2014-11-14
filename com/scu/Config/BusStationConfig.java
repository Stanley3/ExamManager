package com.scu.Config;
import com.scu.Utils.DBHelper;
/*
 * ������վ������
 * ���ߣ������� 2014.10.9
 */

public class BusStationConfig extends ConfigFather{
	
	private static BusStationConfig instance = null;
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
	//���������÷���
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
