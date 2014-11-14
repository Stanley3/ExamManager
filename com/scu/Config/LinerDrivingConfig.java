package com.scu.Config;
import com.scu.Utils.DBHelper;
/**
 * ֱ����ʻ����
 * @author ������ 2014.10.9
 *
 */
public class LinerDrivingConfig extends ConfigFather{
	
	private static LinerDrivingConfig instance = null;
	public static LinerDrivingConfig getInstance(){
		if(instance == null){
			instance = new LinerDrivingConfig();
		}
		return instance;
	}
	private LinerDrivingConfig(){
		isOpen = true;
		timeOrDistance = 0;
		endTime =  10000;
		endDistance = 100;
		triggerDistance = 15;
		minSpeed = 15;
		maxSpeed = 60;
		angleNum = 10;
	}
	//����Ϊ��Ҫ��ʼ���Ĳ���
	//�Ƿ�ʼ�������У� Ĭ���ǿ���
	private boolean isOpen;
	//��ʱ������  1����ʱ��    0:������             ��ʼ��Ϊ0
	private int timeOrDistance;
	//����ʱ��
	private long endTime;
	//��������
	private int endDistance;
	//��������
	private int triggerDistance;
	//���ٷ�Χ
	private int minSpeed;
	private int maxSpeed;
	//ƫת�����
	private int angleNum;
	public boolean isOpen() {
		String res=super.dbHelper.QureyConfig("online_sfpp");
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
		dbHelper.updateConfig("online_sfpp", pValue);
		this.isOpen = isOpen;
	}
	public int getTimeOrDistance() {
		String res=super.dbHelper.QureyConfig("online_jsfs");
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
		dbHelper.updateConfig("online_jsfs", timeOrDistance+"");
		this.timeOrDistance = timeOrDistance;
	}
	public long getEndTime() {
		String res=super.dbHelper.QureyConfig("online_jssj");
		endTime=Long.parseLong(res);
		return endTime;
	}
	public void setEndTime(long endTime) {
		dbHelper.updateConfig("online_jssj",endTime+"");
		this.endTime = endTime;
	}
	public int getEndDistance() {
		String res=super.dbHelper.QureyConfig("online_jsjl");
		endDistance=Integer.parseInt(res);
		return endDistance;
	}
	public void setEndDistance(int endDistance) {
		dbHelper.updateConfig("online_jsjl",endDistance+"");
		this.endDistance = endDistance;
	}
	public int getTriggerDistance() {
		String res=super.dbHelper.QureyConfig("online_cfjl");
		triggerDistance=Integer.parseInt(res);
		return triggerDistance;
	}
	public void setTriggerDistance(int triggerDistance) {
		dbHelper.updateConfig("online_cfjl",triggerDistance+"");
		this.triggerDistance = triggerDistance;
	}
	public int getMinSpeed() {
		String res=super.dbHelper.QureyConfig("online_dbcs");
		return Integer.parseInt(res);
	}
	public void setMinSpeed(int minSpeed) {
		dbHelper.updateConfig("online_dbcs",minSpeed+"");
		this.minSpeed = minSpeed;
	}
	public int getMaxSpeed() {
		String res=super.dbHelper.QureyConfig("online_zgcs");
		maxSpeed=Integer.parseInt(res);
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		dbHelper.updateConfig("online_zgcs",maxSpeed+"");
		this.maxSpeed = maxSpeed;
	}
	public int getAngleNum() {
		String res=super.dbHelper.QureyConfig("online_zxxsz");
		angleNum=Integer.parseInt(res);
		return angleNum;
	}
	public void setAngleNum(int angleNum) {
		dbHelper.updateConfig("online_zxxsz",angleNum+"");
		this.angleNum = angleNum;
	}
}
