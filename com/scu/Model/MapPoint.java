package com.scu.Model;
/*��������ͼ�е�ĳ��������ʲô���� �Լ������ĸ���·*/
public class MapPoint {
	private long point_no;//����
	private Integer line_id;//��·���
	private double lon;
	private double lat;
	private int angle;
	private int point_type;//�������������������
	public MapPoint(Integer line_id, double lon, double lat, int angle,
			int point_type) {
		super();
		this.line_id = line_id;
		this.lon = lon;
		this.lat = lat;
		this.angle = angle;
		this.point_type = point_type;
	}
	public Integer getLine_id() {
		return line_id;
	}
	public void setLine_id(Integer line_id) {
		this.line_id = line_id;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public int getAngle() {
		return angle;
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}
	public int getPoint_type() {
		return point_type;
	}
	public void setPoint_type(int point_type) {
		this.point_type = point_type;
	}
	public long getPoint_no() {
		return point_no;
	}
	
	
}
