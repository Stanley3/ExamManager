package com.scu.Config;
import com.scu.Utils.DBHelper;
/**
 * ��ͷ������
 * @author ������ 2014.10.9
 *
 */

public class TurnRoundConfig extends ConfigFather{
	private static TurnRoundConfig instance= null;
	public static TurnRoundConfig getInstance(){
		if(instance == null){
			instance = new TurnRoundConfig();
		}
		return instance;
	}
	private TurnRoundConfig(){
		isOpen = true;
		timeOrDistance = 0;
		endTime = 60000;
		endDistance = 200;
		triggerDistance = 50;
		EnoughAngle = 120;
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
	// ���Ƕ�
	private int EnoughAngle;
	public boolean isOpen() {
		 String res=super.dbHelper.QureyConfig("turnaround_sfpp");
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
		dbHelper.updateConfig("turnaround_sfpp", pValue);
		this.isOpen = isOpen;
	}
	public int getTimeOrDistance() {
	 String res=super.dbHelper.QureyConfig("turnaround_jsfs");
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
		dbHelper.updateConfig("turnaround_jsfs", timeOrDistance+"");
		this.timeOrDistance = timeOrDistance;
	}
	public long getEndTime() {
		String res=super.dbHelper.QureyConfig("turnaround_jssj");
		endTime=Long.parseLong(res);
		return endTime;
	}
	public void setEndTime(long endTime) {
		dbHelper.updateConfig("turnaround_jssj",endTime+"");
		this.endTime = endTime;
	}
	public int getEndDistance() {
		String res=super.dbHelper.QureyConfig("turnaround_jsjl");
		endDistance=Integer.parseInt(res);
		return endDistance;
	}
	public void setEndDistance(int endDistance) {
		dbHelper.updateConfig("turnaround_jsjl",endDistance+"");
		this.endDistance = endDistance;
	}
	public int getTriggerDistance() {
		String res=super.dbHelper.QureyConfig("turnaround_cfjl");
		triggerDistance=Integer.parseInt(res);
		return triggerDistance;
	}
	public void setTriggerDistance(int triggerDistance) {
		dbHelper.updateConfig("turnaround_cfjl",triggerDistance+"");
		this.triggerDistance = triggerDistance;
	}
	public int getEnoughAngle() {
		String res=super.dbHelper.QureyConfig("turnaround_dtzzj");
		EnoughAngle=Integer.parseInt(res);
		return EnoughAngle;
	}
	public void setEnoughAngle(int enoughAngle) {
		dbHelper.updateConfig("turnaround_dtzzj",enoughAngle+"");
		EnoughAngle = enoughAngle;
	}
	
	
	
	

}
