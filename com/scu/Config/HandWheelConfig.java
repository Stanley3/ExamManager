package com.scu.Config;
import com.scu.Utils.DBHelper;
/**
 * ������������Ϣ
 * @author ������ 2014.10.10
 *
 */

public class HandWheelConfig {
	
	private static HandWheelConfig instance = null;
	public static HandWheelConfig getInstance(){
		if(instance == null){
			instance = new HandWheelConfig();
		}
		return instance;
	}
	private HandWheelConfig(){
		diameter = 31;
		sensorLineLength = 500;
	}
	// ����Ϊ��Ҫ��ʼ���Ĳ���
	//������ֱ��
	private int diameter;
	//�������߳�
	private int sensorLineLength;
	
	public int getDiameter() {
		return diameter;
	}
	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}
	public int getSensorLineLength() {
		return sensorLineLength;
	}
	public void setSensorLineLength(int sensorLineLength) {
		this.sensorLineLength = sensorLineLength;
	}
	
	

}
