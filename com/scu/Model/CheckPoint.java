package com.scu.Model;
/**
 * GPS定位的点的类型
 * 作者: 孙晓雨
 */
public class CheckPoint {
	private double lon;
	  private double lat;
	  private int pointNo;
	  private int pointType;
	  private int angle;
	  private String title;
	  private boolean isChecking;
	  private long startTime;

	  public CheckPoint(String title, double lon, double lat, int pointNo, int angle, int pointType)
	  {
	    this.title = title;
	    this.lon = lon;
	    this.lat = lat;
	    this.pointNo = pointNo;
	    this.angle = angle;
	    this.pointType = pointType;
	    this.isChecking = false;
	  }

	  public void setTitle(String title) {
	    this.title = title;
	  }

	  public String getTitle() {
	    return this.title;
	  }

	  public void setLon(double lon) {
	    this.lon = lon;
	  }

	  public double getLon() {
	    return this.lon;
	  }

	  public void setLat(double lat) {
	    this.lat = lat;
	  }

	  public double getLat() {
	    return this.lat;
	  }

	  public void setPointNo(int pointNo) {
	    this.pointNo = pointNo;
	  }

	  public int getPointNo() {
	    return this.pointNo;
	  }

	  public void setAngle(int angle) {
	    this.angle = angle;
	  }

	  public int getAngle() {
	    return this.angle;
	  }

	  public void setPointType(int pointType) {
	    this.pointType = pointType;
	  }

	  public int getPointType() {
	    return this.pointType;
	  }

	  public void setIsChecking(boolean isChecking) {
	    this.isChecking = isChecking;
	  }

	  public boolean getIsChecking() {
	    return this.isChecking;
	  }

	  public void setStartTime(long startTime) {
	    this.startTime = startTime;
	  }

	  public long getStartTime() {
	    return this.startTime;
	  }

}
