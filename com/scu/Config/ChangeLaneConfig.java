package com.scu.Config;
import com.scu.Utils.DBHelper;
/*
 * �������������
 * ���ߣ������� 2014.10.9
 */
public class ChangeLaneConfig extends ConfigFather{
	
	private static ChangeLaneConfig instance = null;
	// ����Ϊ��Ҫ��ʼ���Ĳ���
	// �Ƿ�ʼ�������У� Ĭ���ǿ���
	private static boolean isOpen;
	//��ʱ������  1����ʱ��    0:������             ��ʼ��Ϊ0
	private int timeOrDistance;
	// ����ʱ��
	private long endTime;
	// ��������
	private int endDistance;
	// ��������
	private int triggerDistance;
	//ƫת�����
	private int offsetAngle;
	//����������
	private int changeLaneLength;
	
	private ChangeLaneConfig() {
		isOpen = true;
		timeOrDistance = 0;
		endTime = 20000;
		endDistance = 150;
		triggerDistance = 30;
		offsetAngle = 3;
		changeLaneLength = 4;
	}

	public static ChangeLaneConfig getInstance()
	{
		if(instance == null){
			instance = new ChangeLaneConfig();
		}
		return instance;
	}
	
	public boolean isOpen() {
		 String res=super.dbHelper.QureyConfig("changelane_sfpp");
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
		dbHelper.updateConfig("changelane_sfpp", pValue);
		//this.isOpen = isOpen;
		ChangeLaneConfig.isOpen = isOpen;
	}
	public int getTimeOrDistance() {
		String res=super.dbHelper.QureyConfig("changelane_jsfs");
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
		dbHelper.updateConfig("changelane_jsfs", timeOrDistance+"");
		this.timeOrDistance = timeOrDistance;
	}
	public long getEndTime() {
		String res=super.dbHelper.QureyConfig("changelane_jssj");
		endTime=Long.parseLong(res);
		return endTime;
	}
	public void setEndTime(long endTime) {
		dbHelper.updateConfig("changelane_jssj",endTime+"");
		this.endTime = endTime;
	}
	public int getEndDistance() {
		String res=super.dbHelper.QureyConfig("changelane_jsjl");
		endDistance=Integer.parseInt(res);
		return endDistance;
	}
	public void setEndDistance(int endDistance) {
		dbHelper.updateConfig("changelane_jsjl",endDistance+"");
		this.endDistance = endDistance;
	}
	public int getTriggerDistance() {
		String res=super.dbHelper.QureyConfig("changelane_cfjl");
		triggerDistance=Integer.parseInt(res);
		return triggerDistance;
	}
	public void setTriggerDistance(int triggerDistance) {
		dbHelper.updateConfig("changelane_cfjl",triggerDistance+"");
		this.triggerDistance = triggerDistance;
	}
	public int getOffsetAngle() {
		String res=super.dbHelper.QureyConfig("changelane_bgcdzjd");	
		return Integer.parseInt(res);
	}
	public void setOffsetAngle(int offsetAngle) {
		dbHelper.updateConfig("changelane_bgcdzjd",offsetAngle+"");
		this.offsetAngle = offsetAngle;
	}
	public int getChangeLaneLength() {
		String res=super.dbHelper.QureyConfig("changelane_lxbd");	
		return Integer.parseInt(res);
	}
	public void setChangeLaneLength(int changeLaneLength) {
		dbHelper.updateConfig("changelane_bgcdzjd",changeLaneLength+"");
		this.changeLaneLength = changeLaneLength;
	}
	
	
	
}
