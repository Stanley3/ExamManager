package com.scu.Config;
/*
 * ��������
 * ���ߣ������� 2014.10.9
 */
import com.scu.Utils.DBHelper;
public class AboardPrepareConfig extends ConfigFather{
	private static AboardPrepareConfig instance= null;
	//����Ϊ��Ҫ��ʼ���Ĳ���
	//�Ƿ�ʼ�������У� Ĭ���ǿ���
	//��ʱ������  1����ʱ��    0:������             ��ʼ��Ϊ1
	private int timeOrDistance ;
	//����ʱ��
	private long endTime;
	//��������
	private int endDistance;
	//��������
	private int triggerDistance;
	//�Ƴ�˳�� 1���ȳ�ǰ�󳵺�  0���ȳ����ǰ   ��ʼ��Ϊ  1
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
