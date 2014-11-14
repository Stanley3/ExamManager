package com.scu.GlobelControl;

import javax.swing.ImageIcon;

/**
 * 信号专用图标
 * @author 孙晓雨
 *
 */
public class SignalIcon {

	public static final String aqdbd = "images\\light\\安全带拔掉.jpg";
	public static final String aqdcr = "images\\light\\安全带插入.jpg";

	public static final String fsc = "images\\light\\副刹踩.jpg";
	public static final String fss = "images\\light\\副刹松.jpg";
	
	public static final String hldg = "images\\light\\后雷达关.jpg";
	public static final String hldk = "images\\light\\后雷达开.jpg";
	
	public static final String jsc = "images\\light\\脚刹踩.jpg";
	public static final String jss = "images\\light\\脚刹松.jpg";
	
	public static final String jgdg = "images\\light\\近光灯关.jpg";
	public static final String jgdk = "images\\light\\近光灯开.jpg";
	
	public static final String lba = "images\\light\\喇叭按.jpg";
	public static final String lbs = "images\\light\\喇叭松.jpg";
	
	public static final String qldg = "images\\light\\前雷达关.jpg";
	public static final String qldk = "images\\light\\前雷达开.jpg";
	
	public static final String ssk = "images\\light\\手刹开.jpg";
	public static final String sss = "images\\light\\手刹松.jpg";
	
	public static final String wdk = "images\\light\\雾灯开.jpg";
	public static final String wdg = "images\\light\\雾灯关.jpg";
	
	public static final String yjdk = "images\\light\\应急灯开.jpg";
	public static final String yjdg = "images\\light\\应急灯关.jpg";
	
	public static final String yzxg = "images\\light\\右转向关.jpg";
	public static final String yzxk = "images\\light\\右转向开.jpg";
	
	public static final String ygdg = "images\\light\\远光灯关.jpg";
	public static final String ygdk = "images\\light\\远光灯开.jpg";
	
	public static final String zzxk = "images\\light\\左转向开.jpg";
	public static final String zzxg = "images\\light\\左转向关.jpg";
	//编辑路线左上角的图标
	public static final String leftTop = "images\\icons\\icon.png";
	public static final String add = "images\\icons\\btn_sub_add.png";
	public static final String add_touch = "images\\icons\\btn_sub_add_touch.png";
	public static final String back = "images\\icons\\btn_sub_back.png";
	public static final String back_touch = "images\\icons\\btn_sub_back_touch.png";
	public static final String del = "images\\icons\\btn_sub_del.png";
	public static final String del_touch = "images\\icons\\btn_sub_del_touch.png";
	public static final String edit = "images\\icons\\btn_sub_edit.png";
	public static final String edit_touch = "images\\icons\\btn_sub_edit_touch.png";
	
	private ImageIcon aqdbdIcon;
	private ImageIcon aqdcrIcon;
	private ImageIcon fscIcon;
	private ImageIcon fssIcon;
	
	private ImageIcon hldgIcon;
	private ImageIcon hldkIcon;
	
	private ImageIcon jscIcon;
	private ImageIcon jssIcon;
	
	private ImageIcon jgdgIcon;
	private ImageIcon jgdkIcon;
	
	private ImageIcon lbaIcon;
	private ImageIcon lbsIcon;
	
	private ImageIcon qldgIcon;
	private ImageIcon qldkIcon;
	
	private ImageIcon sskIcon;
	private ImageIcon sssIcon;
	
	private ImageIcon wdkIcon;
	private ImageIcon wdgIcon;
	
	private ImageIcon yjdkIcon;
	private ImageIcon yjdgIcon;
	
	private ImageIcon yzxgIcon;
	private ImageIcon yzxkIcon;
	
	private ImageIcon ygdgIcon;
	private ImageIcon ygdkIcon;
	
	private ImageIcon zzxkIcon;
	private ImageIcon zzxgIcon;
	
	private ImageIcon LeftTopIcon;
	private ImageIcon addIcon;
	private ImageIcon add_touchIcon;
	private ImageIcon backIcon;
	private ImageIcon back_touchIcon;
	private ImageIcon delIcon;
	private ImageIcon del_touchIcon;
	private ImageIcon editIcon;
	private ImageIcon edit_touchIcon;
	
	
	public ImageIcon getLeftTopIcon() {
		return LeftTopIcon;
	}
	public SignalIcon() {
		LeftTopIcon = new ImageIcon(leftTop);
		addIcon = new ImageIcon(add);
		add_touchIcon = new ImageIcon(add_touch);
		backIcon = new ImageIcon(back);
		back_touchIcon = new ImageIcon(back_touch);
		delIcon = new ImageIcon(del);
		del_touchIcon = new ImageIcon(del_touch);
		editIcon = new ImageIcon(edit);
		edit_touchIcon = new ImageIcon(edit_touch);
		
		
		
		aqdbdIcon = new ImageIcon(aqdbd);
		aqdcrIcon = new ImageIcon(aqdcr);
		
		fscIcon = new ImageIcon(fsc);
		fssIcon = new ImageIcon(fss);
		
		hldgIcon = new ImageIcon(hldg);
		hldkIcon = new ImageIcon(hldk);
		
		jscIcon = new ImageIcon(jsc);
		jssIcon = new ImageIcon(jss);
		
		jgdgIcon = new ImageIcon(jgdg);
		jgdkIcon = new ImageIcon(jgdk);
		
		lbaIcon = new ImageIcon(lba);
		lbsIcon = new ImageIcon(lbs);
		
		qldgIcon = new ImageIcon(qldg);
		qldkIcon = new ImageIcon(qldk);
		
		sskIcon = new ImageIcon(ssk);
		sssIcon = new ImageIcon(sss);
		
		wdkIcon = new ImageIcon(wdk);
		wdgIcon = new ImageIcon(wdg);
		
		yjdkIcon = new ImageIcon(yjdk);
		yjdgIcon = new ImageIcon(yjdg);
		
		yzxgIcon = new ImageIcon(yzxg);
		yzxkIcon = new ImageIcon(yzxk);
		
		ygdgIcon = new ImageIcon(ygdg);
		ygdkIcon = new ImageIcon(ygdk);
		
		zzxkIcon = new ImageIcon(zzxk);
		zzxgIcon = new ImageIcon(zzxg);
	}
	public ImageIcon getAddIcon() {
		return addIcon;
	}
	public ImageIcon getAdd_touchIcon() {
		return add_touchIcon;
	}
	public ImageIcon getBackIcon() {
		return backIcon;
	}
	public ImageIcon getBack_touchIcon() {
		return back_touchIcon;
	}
	public ImageIcon getDelIcon() {
		return delIcon;
	}
	public ImageIcon getDel_touchIcon() {
		return del_touchIcon;
	}
	public ImageIcon getEditIcon() {
		return editIcon;
	}
	public ImageIcon getEdit_touchIcon() {
		return edit_touchIcon;
	}
	public ImageIcon getAqdbdIcon() {
		return aqdbdIcon;
	}
	public ImageIcon getAqdcrIcon() {
		return aqdcrIcon;
	}
	public ImageIcon getFscIcon() {
		return fscIcon;
	}
	public ImageIcon getFssIcon() {
		return fssIcon;
	}
	public ImageIcon getHldgIcon() {
		return hldgIcon;
	}
	public ImageIcon getHldkIcon() {
		return hldkIcon;
	}
	public ImageIcon getJscIcon() {
		return jscIcon;
	}
	public ImageIcon getJssIcon() {
		return jssIcon;
	}
	public ImageIcon getJgdgIcon() {
		return jgdgIcon;
	}
	public ImageIcon getJgdkIcon() {
		return jgdkIcon;
	}
	public ImageIcon getLbaIcon() {
		return lbaIcon;
	}
	public ImageIcon getLbsIcon() {
		return lbsIcon;
	}
	public ImageIcon getQldgIcon() {
		return qldgIcon;
	}
	public ImageIcon getQldkIcon() {
		return qldkIcon;
	}
	public ImageIcon getSskIcon() {
		return sskIcon;
	}
	public ImageIcon getSssIcon() {
		return sssIcon;
	}
	public ImageIcon getWdkIcon() {
		return wdkIcon;
	}
	public ImageIcon getWdgIcon() {
		return wdgIcon;
	}
	public ImageIcon getYjdkIcon() {
		return yjdkIcon;
	}
	public ImageIcon getYjdgIcon() {
		return yjdgIcon;
	}
	public ImageIcon getYzxgIcon() {
		return yzxgIcon;
	}
	public ImageIcon getYzxkIcon() {
		return yzxkIcon;
	}
	public ImageIcon getYgdgIcon() {
		return ygdgIcon;
	}
	public ImageIcon getYgdkIcon() {
		return ygdkIcon;
	}
	public ImageIcon getZzxkIcon() {
		return zzxkIcon;
	}
	public ImageIcon getZzxgIcon() {
		return zzxgIcon;
	}
	
	
	
	
	
	
}
