package com.scu.Config;
import com.scu.Utils.DBHelper;
/**
 * ·����ת����
 * @author ������ 2014.10.9
 *
 */
public class TurnRightConfig extends ConfigFather{
	private static TurnRightConfig instance= null;
	public static TurnRightConfig getInstance(){
		if(instance == null){
			instance = new TurnRightConfig();
		}
		return instance;
	}
	private TurnRightConfig(){
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
		 String res=super.dbHelper.QureyConfig("turnright_sfpp");
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
		dbHelper.updateConfig("turnright_sfpp", pValue);
		this.isOpen = isOpen;
	}
	public int getTimeOrDistance() {
	 String res=super.dbHelper.QureyConfig("turnright_jsfs");
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
		dbHelper.updateConfig("turnright_jsfs", timeOrDistance+"");
		this.timeOrDistance = timeOrDistance;
	}
	public long getEndTime() {
		String res=super.dbHelper.QureyConfig("turnright_jssj");
		endTime=Long.parseLong(res);
		return endTime;
	}
	public void setEndTime(long endTime) {
		dbHelper.updateConfig("turnright_jssj",endTime+"");
		this.endTime = endTime;
	}
	public int getEndDistance() {
		String res=super.dbHelper.QureyConfig("turnright_jsjl");
		endDistance=Integer.parseInt(res);
		return endDistance;
	}
	public void setEndDistance(int endDistance) {
		dbHelper.updateConfig("turnright_jsjl",endDistance+"");
		this.endDistance = endDistance;
	}
	public int getTriggerDistance() {
		String res=super.dbHelper.QureyConfig("turnright_cfjl");
		triggerDistance=Integer.parseInt(res);
		return triggerDistance;
	}
	public void setTriggerDistance(int triggerDistance) {
		dbHelper.updateConfig("turnright_cfjl",triggerDistance+"");
		this.triggerDistance = triggerDistance;
	}
	public int getMaxSpeed() {
		String res=super.dbHelper.QureyConfig("turnright_cs");
		maxSpeed=Integer.parseInt(res);
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		dbHelper.updateConfig("turnright_cs",maxSpeed+"");
	this.maxSpeed = maxSpeed;
	}
	public int getMinAngle() {
		String res=super.dbHelper.QureyConfig("turnright_z");
		minAngle=Integer.parseInt(res);
	return minAngle;
	}
	public void setMinAngle(int minAngle) {
		dbHelper.updateConfig("turnright_cs",minAngle+"");
		this.minAngle = minAngle;
	}
	

}
