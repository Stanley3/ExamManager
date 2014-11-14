package com.scu.Model;
/*该类抽象数据表chinaoffset：记录经纬度以及偏移量*/
public class ChinaOffSet {
	private String ID;//自动增加
	private long lon;//经度
	private long lat;//纬度
	private double offsetlon;//经度偏移量
	private double offsetlat;//纬度偏移量
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
