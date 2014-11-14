package com.scu.Config;
import com.scu.Utils.DBHelper;
/**
 * 方向盘配置信息
 * @author 孙晓雨 2014.10.10
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
	// 以下为需要初始化的参数
	//方向盘直径
	private int diameter;
	//传感器线长
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
