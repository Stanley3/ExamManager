package com.scu.Config;
import com.scu.Utils.DBHelper;
/**
 * �Ӽ���������
 * @author ������ 2014.10.9
 *
 */
public class PlusSubstractDangConfig extends ConfigFather{
	private static PlusSubstractDangConfig instance= null;
	public static PlusSubstractDangConfig getInstance(){
		if(instance == null){
			instance = new PlusSubstractDangConfig();
		}
		return instance;
	}
	private PlusSubstractDangConfig(){
		isOpen = true;
		timeOrDistance = 1;
		endTime = 60000;
		endDistance = 100;
		triggerDistance = 10;
		MaxDang1 = 30;
		MaxDang2 = 33;
		MinDang3 = 20;
		MaxDang3 = 40;
		MinDang4 = 20;
		MinDang5 = 20;
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
	//1����߳��� 
	private int MaxDang1;
	//2����߳��� 
	private int MaxDang2;
	//3����ͳ��� 
	private int MinDang3;
	//3����߳��� 
	private int MaxDang3;
	//4����ͳ��� 
	private int MinDang4;
	//5����ͳ��� 
	private int MinDang5;
	
	public boolean isOpen() {
		 String res=super.dbHelper.QureyConfig("gear_sfpp");
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
		dbHelper.updateConfig("gear_sfpp", pValue);
		this.isOpen = isOpen;
	}
	public int getTimeOrDistance() {
		String res=super.dbHelper.QureyConfig("gear_jsfs");
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
		dbHelper.updateConfig("gear_jsfs", timeOrDistance+"");
		this.timeOrDistance = timeOrDistance;
	}
	public long getEndTime() {
		String res=super.dbHelper.QureyConfig("gear_jssj");
		endTime=Long.parseLong(res);
		return endTime;
	}
	public void setEndTime(long endTime) {
		dbHelper.updateConfig("gear_jssj",endTime+"");
		this.endTime = endTime;
	}
	public int getEndDistance() {
		String res=super.dbHelper.QureyConfig("gear_jsjl");
		endDistance=Integer.parseInt(res);
		return endDistance;
	}
	public void setEndDistance(int endDistance) {
		dbHelper.updateConfig("gear_jsjl",endDistance+"");
		this.endDistance = endDistance;
	}
	public int getTriggerDistance() {
		String res=super.dbHelper.QureyConfig("gear_cfjl");
		triggerDistance=Integer.parseInt(res);
		return triggerDistance;
	}
	public void setTriggerDistance(int triggerDistance) {
		dbHelper.updateConfig("gear_cfjl",triggerDistance+"");
		this.triggerDistance = triggerDistance;
	}
	public int getMaxDang1() {
		String res=super.dbHelper.QureyConfig("golbal_ddgs1");
		MaxDang1=Integer.parseInt(res);
		return MaxDang1;
	}
	public void setMaxDang1(int maxDang1) {
		dbHelper.updateConfig("golbal_ddgs1",maxDang1+"");
		MaxDang1 = maxDang1;
	}
	public int getMaxDang2() {
		String res=super.dbHelper.QureyConfig("golbal_ddgs2");
		MaxDang2=Integer.parseInt(res);
		return MaxDang2;
	}
	public void setMaxDang2(int maxDang2) {
		dbHelper.updateConfig("golbal_ddgs2",maxDang2+"");
		MaxDang2 = maxDang2;
	}
	public int getMinDang3() {
		String res=super.dbHelper.QureyConfig("golbal_ddgs3");
		MaxDang3=Integer.parseInt(res);
		return MinDang3;
	}
	public void setMinDang3(int minDang3) {
		dbHelper.updateConfig("golbal_ddgs3",minDang3+"");
		MinDang3 = minDang3;
	}
	public int getMaxDang3() {
		String res=super.dbHelper.QureyConfig("golbal_ddgs3d");
		MaxDang3=Integer.parseInt(res);
		return MaxDang3;
	}
	public void setMaxDang3(int maxDang3) {
		dbHelper.updateConfig("golbal_ddgs3d", maxDang3+"");
		MaxDang3 = maxDang3;
	}
	public int getMinDang4() {
		String res=super.dbHelper.QureyConfig("golbal_ddgs4");
		MinDang4=Integer.parseInt(res);
		return MinDang4;
	}
	public void setMinDang4(int minDang4) {
		dbHelper.updateConfig("golbal_ddgs4",minDang4+"");
		MinDang4 = minDang4;
	}
	public int getMinDang5() {
		String res=super.dbHelper.QureyConfig("golbal_ddgs5");
		MinDang5=Integer.parseInt(res);
		return MinDang5;
	}
	public void setMinDang5(int minDang5) {
		dbHelper.updateConfig("golbal_ddgs5",minDang5+"");
		MinDang5 = minDang5;
	}
	
	

}
