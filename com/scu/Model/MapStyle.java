package com.scu.Model;
/*��������˵�ͼ�ϸ�������������*/
public class MapStyle {
	private long parmid;//����
	private int Serrorno;//�������Ӧ������Ϣ��
	private String Stxt1;//����������
	private String Stxt2;//�����õ�ʱ�������ź�
	private String Sfilename;//�����źŴ�ŵ��ļ���
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
