package com.scu.Model;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * 按钮类
 * @author 孙晓雨 2014.10.11
 *
 */

public class MyButton extends JButton{
	private ImageIcon buttonIcon;
	private ImageIcon pressIcon;

	public MyButton(ImageIcon buttonIcon, ImageIcon pressIcon) {
		super();
		this.buttonIcon = buttonIcon;
		this.pressIcon = pressIcon;
		//按钮周围留下空白
		this.setMargin(new Insets(0, 0, 0, 0));
		//设置为透明，可以不调用
		this.setOpaque(false);
		//不绘制边框
		this.setBorderPainted(false);
		//设置 contentAreaFilled 属性。如果该属性为 true，则按钮将绘制内容区域。如果希望有一个透明的按钮，比如只是一个图标的按钮，那么应该将此属性设置为 false。
		this.setContentAreaFilled(false);
		//绘制焦点状态
		this.setFocusPainted(false);
		this.setPressedIcon(pressIcon);
		this.setIcon(buttonIcon);
		
	}


	public void setButtonIcon(ImageIcon buttonIcon) {
		this.buttonIcon = buttonIcon;
	}

	public void setPressIcon(ImageIcon pressIcon) {
		this.pressIcon = pressIcon;
	}
	
	

}
