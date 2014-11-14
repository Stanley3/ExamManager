package com.scu.Config;
import com.scu.Utils.DBHelper;
/**
 * ·��ͣ��������
 * @author ������ 2014.10.9
 *
 */
public class PullOverConfig extends ConfigFather{
	private static PullOverConfig instance= null;
	public static PullOverConfig getInstance(){
		if(instance == null){
			instance = new PullOverConfig();
		}
		return instance;
	}
	private PullOverConfig(){
		isOpen = true;
		timeOrDistance = 0;
		endTime = 120000;
		endDistance = 200;
		triggerDistance = 10;
		offsetAngle = 10;
		isEvaluateHandBrake = true;
		isEvaluateShutDown = true;
		
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
	//���򴥷��Ƕ�
	private int offsetAngle;
	//ͣ������ɲ�Ƿ�����
	private boolean isEvaluateHandBrake;
	//ͣ����Ϩ���Ƿ�����
	private boolean isEvaluateShutDown;
	
	
	public boolean isOpen() {
		 String res=super.dbHelper.QureyConfig("stop_sfpp");
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
		dbHelper.updateConfig("stop_sfpp", pValue);
		this.isOpen = isOpen;
	}
	public int getTimeOrDistance() {
		String res=super.dbHelper.QureyConfig("stop_jsfs");
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
		dbHelper.updateConfig("stop_jsfs", timeOrDistance+"");
		this.timeOrDistance = timeOrDistance;
	}
	public long getEndTime() {
		String res=super.dbHelper.QureyConfig("stop_jssj");
		endTime=Long.parseLong(res);
		return endTime;
	}
	public void setEndTime(long endTime) {
		dbHelper.updateConfig("stop_jssj",endTime+"");
		this.endTime = endTime;
	}
	public int getEndDistance() {
		String res=super.dbHelper.QureyConfig("stop_jsjl");
		endDistance=Integer.parseInt(res);
		return endDistance;
	}
	public void setEndDistance(int endDistance) {
		dbHelper.updateConfig("stop_jsjl",endDistance+"");
		
		this.endDistance = endDistance;
	}
	public int getTriggerDistance() {
		String res=super.dbHelper.QureyConfig("stop_cfjl");
		triggerDistance=Integer.parseInt(res);
		return triggerDistance;
	}
	public void setTriggerDistance(int triggerDistance) {
		dbHelper.updateConfig("stop_cfjl",triggerDistance+"");
		this.triggerDistance = triggerDistance;
	}
	public int getOffsetAngle() {
		String res=super.dbHelper.QureyConfig("stop_kbqfxdjd");
		triggerDistance=Integer.parseInt(res);
		return offsetAngle;
	}
	public void setOffsetAngle(int offsetAngle) {
		dbHelper.updateConfig("stop_kbqfxdjd",offsetAngle+"");
		this.offsetAngle = offsetAngle;
	}
	public boolean isEvaluateHandBrake() {
		String res=super.dbHelper.QureyConfig("stop_sssfpp");
		if(res.trim().compareTo("1")==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void setEvaluateHandBrake(boolean isEvaluateHandBrake) {
		String pvalue="";
		if(isEvaluateHandBrake)
		{
			pvalue="1";
		}
		else
		{
			pvalue="0";
		}
		dbHelper.updateConfig("stop_sssfpp",pvalue+"");
	}
	public boolean isEvaluateShutDown() {
		String res=super.dbHelper.QureyConfig("stop_xhsfpp");
		if(res.trim().compareTo("1")==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void setEvaluateShutDown(boolean isEvaluateShutDown) {
		String pvalue="";
		if(isEvaluateShutDown)
		{
			pvalue="1";
		}
		else
		{
			pvalue="0";
		}
		dbHelper.updateConfig("stop_xhsfpp",pvalue+"");
		this.isEvaluateShutDown = isEvaluateShutDown;
	}
	
	

}
