package com.scu.Config;
import com.scu.Utils.DBHelper;
/**
 * ��������
 * @author ������ 2014.10.9
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
	// ����Ϊ��Ҫ��ʼ���Ĳ���
	// �Ƿ�ʼ�������У� Ĭ���ǿ���
	private boolean isOpen = true;
	//��ʱ���������  1����ʱ��    0:������             ��ʼ��Ϊ0
	private int timeOrDistance;
	// ����ʱ��
	private long endTime;
	// ��������
	private int endDistance;
	// ��������
	private int triggerDistance;
	// ��������Ƕ�
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
