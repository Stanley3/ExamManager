package com.scu.Model;
/*����������ݱ�chinaoffset����¼��γ���Լ�ƫ����*/
public class ChinaOffSet {
	private String ID;//�Զ�����
	private long lon;//����
	private long lat;//γ��
	private double offsetlon;//����ƫ����
	private double offsetlat;//γ��ƫ����
	public ChinaOffSet(long lon, long lat, double offsetlon, double offsetlat) {
		super();
		this.lon = lon;
		this.lat = lat;
		this.offsetlon = offsetlon;
		this.offsetlat = offsetlat;
	}
	public long getLon() {
		return lon;
	}
	public void setLon(long lon) {
		this.lon = lon;
	}
	public long getLat() {
		return lat;
	}
	public void setLat(long lat) {
		this.lat = lat;
	}
	public double getOffsetlon() {
		return offsetlon;
	}
	public void setOffsetlon(double offsetlon) {
		this.offsetlon = offsetlon;
	}
	public double getOffsetlat() {
		return offsetlat;
	}
	public void setOffsetlat(double offsetlat) {
		this.offsetlat = offsetlat;
	}
	public String getID() {
		return ID;
	}
	
}
