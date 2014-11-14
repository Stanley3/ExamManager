package com.scu.Model;
/*该类抽象了地图上各个特殊点的类型*/
public class MapStyle {
	private long parmid;//主键
	private int Serrorno;//外键（对应错误信息）
	private String Stxt1;//特殊点的类型
	private String Stxt2;//经过该点时的语音信号
	private String Sfilename;//语音信号存放的文件名
	public MapStyle(int serrorno, String stxt1, String stxt2, String sfilename) {
		super();
		Serrorno = serrorno;
		Stxt1 = stxt1;
		Stxt2 = stxt2;
		Sfilename = sfilename;
	}
	public int getSerrorno() {
		return Serrorno;
	}
	 /**
	  * 
	  * @param serrorno
	  */
	public void setSerrorno(int serrorno) {
		Serrorno = serrorno;
	}
	public String getStxt1() {
		return Stxt1;
	}
	public void setStxt1(String stxt1) {
		Stxt1 = stxt1;
	}
	public String getStxt2() {
		return Stxt2;
	}
	public void setStxt2(String stxt2) {
		Stxt2 = stxt2;
	}
	public String getSfilename() {
		return Sfilename;
	}
	public void setSfilename(String sfilename) {
		Sfilename = sfilename;
	}
	public long getParmid() {
		return parmid;
	}
	
	
	
}
