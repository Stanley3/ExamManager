package com.scu.Signal;

/**
 * 
 * @author gujh
 * 该类是各种单片机通过网口传到电脑的信号，所有的信号均为常量。
 */
public class Signal 
{
	public static final String SEATBELT = "FFAA550000001001"; //安全带信号
	
	public static final String RIGHTLAMP = "FFAA550000001004";//右转向信号
	
	public static final String LEFTLAMP = "FFAA550000001003";//左转向信号
	
	public static final String BEAMLIGNTON = "FFAA550000001007";//近光灯开信号
	
	public static final String BEAMLIGHTOFF = "FFAA550000001008";//近光灯关信号
	
	public static final String HIGHBEAMON = "FFAA550000001014";//远光灯开信号
	
	public static final String HIGHBEAMOFF = "FFAA550000001015";//远光灯关信号
	
	public static final String WARNINGLIGHT = "FFAA550000001016";//警示灯信号
	
	public static final String FOGLAMPON = "FFAA550000001012";//雾灯开信号
	
	public static final String FOGLAMPOFF = "FFAA550000001013";//雾灯关信号
	
	public static final String FOOTBRAKEON = "FFAA550000001018";//踩脚刹信号
	
	public static final String FOOTBRAKEOFF = "FFAA550000001024";//松脚刹信号
	
	public static final String DEPUTYFOOTBRAKE = "FFAA550000001019";//副脚刹信号
	
	public static final String HANDBRAKEON = "FFAA550000001006";//提手刹信号
	
	public static final String HANDBRAKEOFF = "FFAA550000001005";//松手刹信号
	
	public static final String REVERSEOFF = "FFAA550000000309";//退倒档信号
	
	public static final String REVERSEON = "FFAA550000000301";//挂倒档信号
	
	public static final String FIRSTGEAR = "FFAA550000000101";//一档信号
	
	public static final String SECONDGEAR = "FFAA550000000102";//二档信号
	
	public static final String THIRDGEAR = "FFAA550000000103";//三档信号
	
	public static final String FOURTHGEAR = "FFAA550000000104";//四档信号
	
	public static final String FIRTHGEAR = "FFAA550000000105";//五档信号
	
	public static final String NOGEAR = "FFAA550000000100";//空挡信号
	
	public static final String FRONTBUMPER = "FFAA550000001021";//前保险杠信号
	
	public static final String REARBUMPER = "FFAA550000001023";//后保险杠信号
	
	public static final String OFF = "FFAA550000001002";//熄火信号
	
	public static final String HOOTER = "FFAA550000001009";//喇叭信号
}