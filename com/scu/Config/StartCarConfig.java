package com.scu.Config;
import com.scu.Utils.DBHelper;
/**
 * ��������
 * @author ������ 2014.10.9
 *
 */

public class StartCarConfig extends ConfigFather{
	private static StartCarConfig instance= null;
	public static StartCarConfig getInstance(){
		if(instance == null){
			instance = new StartCarConfig();
		}
		return instance;
	}
	private StartCarConfig(){
		isOpen = true;
		timeOrDistance = 1;
		endTime = 20000;
		endDistance = 10;
		triggerDistance = 10;
		minEngineIdleSpeed = 650;
		startMaxRoundSpeed = 2500;
		maxTime = 5000;
	}
	// ����Ϊ��Ҫ��ʼ���Ĳ���
	// �Ƿ�ʼ�������У� Ĭ���ǿ���
	private static boolean isOpen = true;
	//��ʱ������  1����ʱ��    0:������             ��ʼ��Ϊ1
	private int timeOrDistance;
	// ����ʱ��
	private long endTime;
	// ��������
	private int endDistance;
	// ��������
	private int triggerDistance;
	//�����𲽿۷�ת������
	private int minEngineIdleSpeed;
	//�𲽷�����ת�ٹ���
	private int startMaxRoundSpeed;
	//����ɲ����ms����δ���۷�
	private long maxTime;
	
	
	public boolean isOpen() {
		 String res=super.dbHelper.QureyConfig("qb_sfpp");
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
		dbHelper.updateConfig("qb_sfpp", pValue);
		this.isOpen = isOpen;
		StartCarConfig.isOpen = isOpen;
	}
	public int getTimeOrDistance() {
		String res=super.dbHelper.QureyConfig("qb_jsfs");
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
		dbHelper.updateConfig("qb_jsfs", timeOrDistance+"");
		this.timeOrDistance = timeOrDistance;
	}
	public long getEndTime() {
		String res=super.dbHelper.QureyConfig("qb_jssj");
		endTime=Long.parseLong(res);
		return endTime;
	}
	public void setEndTime(long endTime) {
		dbHelper.updateConfig("qb_jssj",endTime+"");
		this.endTime = endTime;
	}
	public int getEndDistance() {
		String res=super.dbHelper.QureyConfig("qb_jsjl");
		endDistance=Integer.parseInt(res);
		return endDistance;
	}
	public void setEndDistance(int endDistance) {
		dbHelper.updateConfig("qb_jsjl",endDistance+"");
		this.endDistance = endDistance;
	}
	public int getTriggerDistance() {
		String res=super.dbHelper.QureyConfig("qb_cfjl");
		triggerDistance=Integer.parseInt(res);
		return triggerDistance;
	}
	public void setTriggerDistance(int triggerDistance) {
		dbHelper.updateConfig("qb_cfjl",triggerDistance+"");
		this.triggerDistance = triggerDistance;
	}
	public int getMinEngineIdleSpeed() {
		String res=super.dbHelper.QureyConfig("qb_qbfdjzs");
		minEngineIdleSpeed=Integer.parseInt(res);
		return minEngineIdleSpeed;
	}
	public void setMinEngineIdleSpeed(int minEngineIdleSpeed) {
		dbHelper.updateConfig("qb_qbfdjzs",minEngineIdleSpeed+"");
		this.minEngineIdleSpeed = minEngineIdleSpeed;
	}
	public int getStartMaxRoundSpeed() {
		String res=super.dbHelper.QureyConfig("qb_qbfdjzsgg");
		startMaxRoundSpeed=Integer.parseInt(res);
		return startMaxRoundSpeed;
	}
	public void setStartMaxRoundSpeed(int startMaxRoundSpeed) {
		dbHelper.updateConfig("qb_qbfdjzsgg", startMaxRoundSpeed+"");
		this.startMaxRoundSpeed = startMaxRoundSpeed;
	}
	public long getMaxTime() {
		String res=super.dbHelper.QureyConfig("qb_ssc2qjsj");
		maxTime=Integer.parseInt(res);
		return maxTime;
	}
	public void setMaxTime(long maxTime) {
		dbHelper.updateConfig("qb_ssc2qjsj", maxTime+"");
		this.maxTime = maxTime;
	}
	
	
	
 
}
