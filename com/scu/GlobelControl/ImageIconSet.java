package com.scu.GlobelControl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 * 
 * 全部的图片集合
 * @author 孙晓雨 2014.10.11
 *
 */

public class ImageIconSet {
	/*********************************主窗口背景图片**********************************************/
	public static final String bg = "images\\main\\bg.png";
	public static final String title = "images\\icons\\title_icon.png";
	//模拟考试按钮图片
	public static final String mnks = "images\\icons\\mnks.png";
	public static final String mnks_touch = "images\\icons\\mnks_touch.png";
	//日常路讯按钮图片
	public static final String mnxl = "images\\icons\\mnxl.png";
	public static final String mnxl_touch = "images\\icons\\mnxl_touch.png";
	//灯光训练按钮图片
	public static final String dgxl = "images\\icons\\dgxl.png";
	public static final String dgxl_touch = "images\\icons\\dgxl_touch.png";
	//系统设置按钮图片
	public static final String setting = "images\\icons\\setting.png";
	public static final String setting_touch = "images\\icons\\setting_touch.png";
	//退出系统按钮图片
	public static final String exit = "images\\icons\\exit.png";
	public static final String exit_touch = "images\\icons\\exit_touch.png";
	/***********************************系统设置窗口图片********************************************/
	private static final String sysIdent = "images\\icons\\bg_setting.png";
	private static final String xtjc = "images\\icons\\btn_set_xtjc.png";
	private static final String xtjc_touch = "images\\icons\\btn_set_xtjc_touch.png";
	private static final String lxsz = "images\\icons\\btn_set_lxsz.png";
	private static final String lxsz_touch = "images\\icons\\btn_set_lxsz_touch.png";
	private static final String cssz = "images\\icons\\btn_rgpp_xtsz.png";
	private static final String cssz_touch = "images\\icons\\btn_rgpp_xtsz_touch.png";
	private static final String gybj = "images\\icons\\btn_set_about.png";
	private static final String gybj_touch = "images\\icons\\btn_set_about_touch.png";
	private static final String fh = "images\\icons\\btn_set_back.png";
	private static final String fh_touch = "images\\icons\\btn_set_back_touch.png";
	/*********************************** 系统检测窗口图片 *********************************************/
	private static final String switchclose = "images\\icons\\switchclose.png";
	private static final String switchopen = "images\\icons\\switchopen.png";
	private static final String head = "images\\icons\\head.png";
	private static final String set_back = "images\\icons\\btn_set_back.png";
	private static final String set_back_touch = "images\\icons\\btn_set_back_touch.png";
	
	/*********************************** 参数设置窗口按钮图片******************************************/
	public static final String sczb = "images\\icons\\btn_rgpp_sczb.png";
	public static final String xtsz = "images\\icons\\btn_rgpp_xtsz.png";
	public static final String gjqcz = "images\\icons\\btn_rgpp_ggqcz.png";
	public static final String bgcd = "images\\icons\\btn_rgpp_bgcd.png";
	public static final String typp = "images\\icons\\btn_rgpp_typp.png";
	public static final String rxhdx = "images\\icons\\btn_rgpp_rxhdx.png";
	public static final String fxp = "images\\icons\\btn_rgpp_fxp.png";
	public static final String zxxs = "images\\icons\\btn_rgpp_zxxs.png";
	public static final String lkzz = "images\\icons\\btn_rgpp_lkzx.png";
	public static final String hc = "images\\icons\\btn_rgpp_hc.png";
	public static final String cc = "images\\icons\\btn_rgpp_cc.png";
	public static final String jjdcz = "images\\icons\\btn_rgpp_jjdcz.png";
	public static final String kbtc = "images\\icons\\btn_rgpp_kbtc.png";
	public static final String xxqy = "images\\icons\\btn_rgpp_xxqy.png";
	public static final String qb = "images\\icons\\btn_rgpp_qb.png";
	public static final String lkyzw = "images\\icons\\btn_rgpp_lkyzw.png";
	public static final String lkzzw = "images\\icons\\btn_rgpp_lkzzw.png";
	public static final String dt = "images\\icons\\btn_rgpp_dt.png";
	//配置窗口按钮按下时候图片
	public static final String sczb_touch = "images\\icons\\btn_rgpp_sczb_touch.png";
	public static final String xtsz_touch = "images\\icons\\btn_rgpp_xtsz_touch.png";
	public static final String gjqcz_touch = "images\\icons\\btn_rgpp_ggqcz_touch.png";
	public static final String bgcd_touch = "images\\icons\\btn_rgpp_bgcd_touch.png";
	public static final String typp_touch = "images\\icons\\btn_rgpp_typp_touch.png";
	public static final String rxhdx_touch = "images\\icons\\btn_rgpp_rxhdx_touch.png";
	public static final String fxp_touch = "images\\icons\\btn_rgpp_fxp_touch.png";
	public static final String zxxs_touch = "images\\icons\\btn_rgpp_zxxs_touch.png";
	public static final String lkzz_touch = "images\\icons\\btn_rgpp_lkzx_touch.png";
	public static final String hc_touch = "images\\icons\\btn_rgpp_hc_touch.png";
	public static final String cc_touch = "images\\icons\\btn_rgpp_cc_touch.png";
	public static final String jjdcz_touch = "images\\icons\\btn_rgpp_jjdcz_touch.png";
	public static final String kbtc_touch = "images\\icons\\btn_rgpp_kbtc_touch.png";
	public static final String xxqy_touch = "images\\icons\\btn_rgpp_xxqy_touch.png";
	public static final String qb_touch = "images\\icons\\btn_rgpp_qb_touch.png";
	public static final String lkyzw_touch = "images\\icons\\btn_rgpp_lkyzw_touch.png";
	public static final String lkzzw_touch = "images\\icons\\btn_rgpp_lkzzw_touch.png";
	public static final String dt_touch = "images\\icons\\btn_rgpp_dt_touch.png";
	
	//保存和返回按钮
	public static final String save = "images\\icons\\btn_sub_save.png";
	public static final String save_touch = "images\\icons\\btn_sub_save_touch.png";
	public static final String back = "images\\icons\\btn_sub_back.png";
	public static final String back_touch = "images\\icons\\btn_sub_back_touch.png";
	
	public static final String ok = "images\\icons\\btn_sub_ok.png";
	public static final String ok_touch = "images\\icons\\btn_sub_ok_touch.png";
	
	/**************************************************************************************/
	
	//主窗口Icon
	private ImageIcon titleIcon;
	private ImageIcon background;
	private ImageIcon mnksIcon;
	private ImageIcon mnxlIcon;
	private ImageIcon dgxlIcon;
	private ImageIcon settingIcon;
	private ImageIcon exitIcon;
	
	private ImageIcon mnks_touchIcon;
	private ImageIcon mnxl_touchIcon;
	private ImageIcon dgxl_touchIcon;
	private ImageIcon setting_touchIcon;
	private ImageIcon exit_touchIcon;
	
	//系统设置窗口Icon
	private ImageIcon sysIdentIcon;
	private ImageIcon xtjcIcon;
	private ImageIcon xtjc_touchIcon;
	private ImageIcon lxszIcon;
	private ImageIcon lxsz_touchIcon;
	private ImageIcon csszIcon;
	private ImageIcon cssz_touchIcon;
	private ImageIcon gybjIcon;
	private ImageIcon gybj_touchIcon;
	private ImageIcon fhIcon;
	private ImageIcon fh_touchIcon;
	
	//系统检测窗口Icon
	private ImageIcon switchcloseIcon;
	private ImageIcon switchopenIcon;
	private ImageIcon headIcon;
	private ImageIcon set_backIcon;
	private ImageIcon set_back_touchIcon;
	
	
	//参数设置窗口Icon
	private ImageIcon sczbIcon; 
	private ImageIcon xtszIcon; 
	private ImageIcon gjqczIcon; 
	private ImageIcon bgcdIcon; 
	private ImageIcon typpIcon; 
	private ImageIcon rxhdxIcon; 
	private ImageIcon fxpIcon; 
	private ImageIcon zxxsIcon; 
	private ImageIcon lkzzIcon;
	private ImageIcon hcIcon; 
	private ImageIcon ccIcon;
	private ImageIcon jjdczIcon;
	private ImageIcon kbtcIcon; 
	private ImageIcon xxqyIcon; 
	private ImageIcon qbIcon; 
	private ImageIcon lkyzwIcon; 
	private ImageIcon lkzzwIcon; 
	private ImageIcon dtIcon;
	//参数设置窗口按下ICON
	private ImageIcon sczb_touchIcon; 
	private ImageIcon xtsz_touchIcon; 
	private ImageIcon gjqcz_touchIcon; 
	private ImageIcon bgcd_touchIcon; 
	private ImageIcon typp_touchIcon; 
	private ImageIcon rxhdx_touchIcon; 
	private ImageIcon fxp_touchIcon; 
	private ImageIcon zxxs_touchIcon; 
	private ImageIcon lkzz_touchIcon;
	private ImageIcon hc_touchIcon; 
	private ImageIcon cc_touchIcon;
	private ImageIcon jjdcz_touchIcon;
	private ImageIcon kbtc_touchIcon; 
	private ImageIcon xxqy_touchIcon; 
	private ImageIcon qb_touchIcon; 
	private ImageIcon lkyzw_touchIcon; 
	private ImageIcon lkzzw_touchIcon; 
	private ImageIcon dt_touchIcon;
	
	//保存和返回ICON
	private ImageIcon saveIcon;
	private ImageIcon save_touchIcon;
	private ImageIcon backIcon;
	private ImageIcon back_touchIcon;
	
	private ImageIcon okIcon;
	private ImageIcon ok_touchIcon;
	
	public ImageIconSet() {
		this.init();
	}
	public static BufferedImage getImage(String imagePath) {
		BufferedImage image = null;
		try {
			// 使用ImageIO读取图片
			image =  ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			// 读取图片发生异常，抛出GameException
		//	throw new GameException("read image error");
		}
		return image;
	}
	//icon初始化
	private void init(){
		okIcon = new ImageIcon(ok);
		ok_touchIcon = new ImageIcon(ok_touch);
		background = new ImageIcon(bg);
		titleIcon = new ImageIcon(title);
		
		mnksIcon = new ImageIcon(mnks);
		mnxlIcon = new ImageIcon(mnxl);
		dgxlIcon = new ImageIcon(dgxl);
		settingIcon = new ImageIcon(setting);
		exitIcon = new ImageIcon(exit);
		
		mnks_touchIcon = new ImageIcon(mnks_touch);
		mnxl_touchIcon = new ImageIcon(mnxl_touch);
		dgxl_touchIcon = new ImageIcon(dgxl_touch);
		setting_touchIcon = new ImageIcon(setting_touch);
		exit_touchIcon = new ImageIcon(exit_touch);
		
		sysIdentIcon = new ImageIcon(sysIdent);
		xtjcIcon = new ImageIcon(xtjc);
		xtjc_touchIcon = new ImageIcon(xtjc_touch);
		lxszIcon = new ImageIcon(lxsz);
		lxsz_touchIcon = new ImageIcon(lxsz_touch);
		csszIcon = new ImageIcon(cssz);
		cssz_touchIcon = new ImageIcon(cssz_touch);
		gybjIcon = new ImageIcon(gybj);
		gybj_touchIcon = new ImageIcon(gybj_touch);
		fhIcon = new ImageIcon(fh);
		fh_touchIcon = new ImageIcon(fh_touch);
		
		//系统检测窗口Icon初始化
		switchcloseIcon = new ImageIcon(switchclose);
		switchopenIcon = new ImageIcon(switchopen);
		headIcon = new ImageIcon(head);
		set_backIcon = new ImageIcon(set_back);
		set_back_touchIcon = new ImageIcon(set_back_touch);
		//参数设置窗口Icon初始化
		sczbIcon = new ImageIcon(sczb);
		xtszIcon = new ImageIcon(xtsz);
		gjqczIcon = new ImageIcon(gjqcz);
		bgcdIcon = new ImageIcon(bgcd);
		typpIcon = new ImageIcon(typp);
		rxhdxIcon = new ImageIcon(rxhdx);
		fxpIcon = new ImageIcon(fxp);
		zxxsIcon = new ImageIcon(zxxs);
		lkzzIcon = new ImageIcon(lkzz);
		hcIcon = new ImageIcon(hc);
		ccIcon = new ImageIcon(cc);
		jjdczIcon = new ImageIcon(jjdcz);
		kbtcIcon = new ImageIcon(kbtc);
		xxqyIcon = new ImageIcon(xxqy);
		qbIcon = new ImageIcon(qb);
		lkyzwIcon = new ImageIcon(lkyzw);
		lkzzwIcon = new ImageIcon(lkzzw);
		dtIcon = new ImageIcon(dt);
		
		sczb_touchIcon = new ImageIcon(sczb_touch);
		xtsz_touchIcon = new ImageIcon(xtsz_touch);
		gjqcz_touchIcon = new ImageIcon(gjqcz_touch);
		bgcd_touchIcon = new ImageIcon(bgcd_touch);
		typp_touchIcon = new ImageIcon(typp_touch);
		rxhdx_touchIcon = new ImageIcon(rxhdx_touch);
		fxp_touchIcon = new ImageIcon(fxp_touch);
		zxxs_touchIcon = new ImageIcon(zxxs_touch);
		lkzz_touchIcon = new ImageIcon(lkzz_touch);
		hc_touchIcon = new ImageIcon(hc_touch);
		cc_touchIcon = new ImageIcon(cc_touch);
		jjdcz_touchIcon = new ImageIcon(jjdcz_touch);
		kbtc_touchIcon = new ImageIcon(kbtc_touch);
		xxqy_touchIcon = new ImageIcon(xxqy_touch);
		qb_touchIcon = new ImageIcon(qb_touch);
		lkyzw_touchIcon = new ImageIcon(lkyzw_touch);
		lkzzw_touchIcon = new ImageIcon(lkzzw_touch);
		dt_touchIcon = new ImageIcon(dt_touch);
		
		saveIcon =new ImageIcon(save);
		save_touchIcon = new ImageIcon(save_touch);
		backIcon = new ImageIcon(back);
		back_touchIcon = new ImageIcon(back_touch);

	}
	public ImageIcon getOkIcon(){
		return okIcon;
	}
	public ImageIcon getOk_touchIcon(){
		return ok_touchIcon;
	}
	public ImageIcon getTitleIcon(){
		return titleIcon;
	}
	public ImageIcon getSet_backIcon(){
		return set_backIcon;
	}
	public ImageIcon getSet_back_touchIcon(){
		return set_back_touchIcon;
	}
	public ImageIcon getSwitchcloseIcon(){
		return switchcloseIcon;
	}
	public ImageIcon getSwithopenIcon(){
		return switchopenIcon;
	}
	public ImageIcon getHeadIcon(){
		return headIcon;
	}
	public ImageIcon getSysIdentIcon() {
		return sysIdentIcon;
	}
	public ImageIcon getXtjcIcon() {
		return xtjcIcon;
	}
	public ImageIcon getXtjc_touchIcon() {
		return xtjc_touchIcon;
	}
	public ImageIcon getLxszIcon() {
		return lxszIcon;
	}
	public ImageIcon getLxsz_touchIcon() {
		return lxsz_touchIcon;
	}
	public ImageIcon getCsszIcon() {
		return csszIcon;
	}
	public ImageIcon getCssz_touchIcon() {
		return cssz_touchIcon;
	}
	public ImageIcon getGybjIcon() {
		return gybjIcon;
	}
	public ImageIcon getGybj_touchIcon() {
		return gybj_touchIcon;
	}
	public ImageIcon getFhIcon() {
		return fhIcon;
	}
	public ImageIcon getFh_touchIcon() {
		return fh_touchIcon;
	}
	public ImageIcon getSaveIcon() {
		return saveIcon;
	}

	public ImageIcon getSave_touchIcon() {
		return save_touchIcon;
	}

	public ImageIcon getBackIcon() {
		return backIcon;
	}

	public ImageIcon getBack_touchIcon() {
		return back_touchIcon;
	}

	public ImageIcon getSczbIcon() {
		return sczbIcon;
	}
	public ImageIcon getXtszIcon() {
		return xtszIcon;
	}
	public ImageIcon getGjqczIcon() {
		return gjqczIcon;
	}
	public ImageIcon getBgcdIcon() {
		return bgcdIcon;
	}
	public ImageIcon getTyppIcon() {
		return typpIcon;
	}
	public ImageIcon getRxhdxIcon() {
		return rxhdxIcon;
	}
	public ImageIcon getFxpIcon() {
		return fxpIcon;
	}
	public ImageIcon getZxxsIcon() {
		return zxxsIcon;
	}
	public ImageIcon getLkzzIcon() {
		return lkzzIcon;
	}
	public ImageIcon getHcIcon() {
		return hcIcon;
	}
	public ImageIcon getCcIcon() {
		return ccIcon;
	}
	public ImageIcon getJjdczIcon() {
		return jjdczIcon;
	}
	public ImageIcon getKbtcIcon() {
		return kbtcIcon;
	}
	public ImageIcon getXxqyIcon() {
		return xxqyIcon;
	}
	public ImageIcon getQbIcon() {
		return qbIcon;
	}
	public ImageIcon getLkyzwIcon() {
		return lkyzwIcon;
	}
	public ImageIcon getLkzzwIcon() {
		return lkzzwIcon;
	}
	public ImageIcon getDtIcon() {
		return dtIcon;
	}
	public ImageIcon getSczb_touchIcon() {
		return sczb_touchIcon;
	}
	public ImageIcon getXtsz_touchIcon() {
		return xtsz_touchIcon;
	}
	public ImageIcon getGjqcz_touchIcon() {
		return gjqcz_touchIcon;
	}
	public ImageIcon getBgcd_touchIcon() {
		return bgcd_touchIcon;
	}
	public ImageIcon getTypp_touchIcon() {
		return typp_touchIcon;
	}
	public ImageIcon getRxhdx_touchIcon() {
		return rxhdx_touchIcon;
	}
	public ImageIcon getFxp_touchIcon() {
		return fxp_touchIcon;
	}
	public ImageIcon getZxxs_touchIcon() {
		return zxxs_touchIcon;
	}
	public ImageIcon getLkzz_touchIcon() {
		return lkzz_touchIcon;
	}
	public ImageIcon getHc_touchIcon() {
		return hc_touchIcon;
	}
	public ImageIcon getCc_touchIcon() {
		return cc_touchIcon;
	}
	public ImageIcon getJjdcz_touchIcon() {
		return jjdcz_touchIcon;
	}
	public ImageIcon getKbtc_touchIcon() {
		return kbtc_touchIcon;
	}
	public ImageIcon getXxqy_touchIcon() {
		return xxqy_touchIcon;
	}
	public ImageIcon getQb_touchIcon() {
		return qb_touchIcon;
	}
	public ImageIcon getLkyzw_touchIcon() {
		return lkyzw_touchIcon;
	}
	public ImageIcon getLkzzw_touchIcon() {
		return lkzzw_touchIcon;
	}
	public ImageIcon getDt_touchIcon() {
		return dt_touchIcon;
	}
	public ImageIcon getMnks_touchIcon() {
		return mnks_touchIcon;
	}
	public ImageIcon getMnxl_touchIcon() {
		return mnxl_touchIcon;
	}
	public ImageIcon getDgxl_touchIcon() {
		return dgxl_touchIcon;
	}
	public ImageIcon getSetting_touchIcon() {
		return setting_touchIcon;
	}
	public ImageIcon getExit_touchIcon() {
		return exit_touchIcon;
	}
	public ImageIcon getBackground() {
		return background;
	}
	public ImageIcon getMnksIcon() {
		return mnksIcon;
	}
	public ImageIcon getMnxlIcon() {
		return mnxlIcon;
	}
	public ImageIcon getDgxlIcon() {
		return dgxlIcon;
	}
	public ImageIcon getSettingIcon() {
		return settingIcon;
	}
	public ImageIcon getExitIcon() {
		return exitIcon;
	}
}
