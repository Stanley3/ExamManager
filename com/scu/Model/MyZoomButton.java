package com.scu.Model;

import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * 放缩按钮图标
 * @author 孙晓雨
 *
 */
public class MyZoomButton extends JButton{
	private ImageIcon buttonIcon;
	private ImageIcon pressIcon;

	public MyZoomButton(ImageIcon buttonIcon, ImageIcon pressIcon) {
		super();
		this.buttonIcon = buttonIcon;
		this.pressIcon = pressIcon;
		//按钮周围留下空白
		this.setMargin(new Insets(0, 0, 0, 0));
		//设置为透明，可以不调用
		//this.setOpaque(false);
		//不绘制边框
		this.setBorderPainted(false);
		//设置 contentAreaFilled 属性。如果该属性为 true，则按钮将绘制内容区域。如果希望有一个透明的按钮，比如只是一个图标的按钮，那么应该将此属性设置为 false。
		this.setContentAreaFilled(false);
		//绘制焦点状态
		this.setFocusPainted(false);
		this.changeImage(buttonIcon, this);
		//this.setPressedIcon(pressIcon);
		//this.setIcon(buttonIcon);
		this.changePressImage(pressIcon, this);
	}
	public void changeImage(ImageIcon icon, JButton com){  
		Image temp=icon.getImage().getScaledInstance(icon.getIconWidth() * 7 / 10,icon.getIconHeight() * 7 / 10,icon.getImage().SCALE_DEFAULT);
		ImageIcon ico=new ImageIcon(temp); 
		com.setIcon(ico); 
	} 

	public void changePressImage(ImageIcon icon, JButton com){  
		Image temp=icon.getImage().getScaledInstance(icon.getIconWidth() * 7 / 10,icon.getIconHeight() * 7 / 10,icon.getImage().SCALE_DEFAULT);
		ImageIcon ico=new ImageIcon(temp); 
		com.setPressedIcon(ico); 
	} 

	
	public void setButtonIcon(ImageIcon buttonIcon) {
		this.buttonIcon = buttonIcon;
	}

	public void setPressIcon(ImageIcon pressIcon) {
		this.pressIcon = pressIcon;
	}
}
