package com.scu.Model;

import java.awt.*;

import javax.swing.*;
/**
 * ±Í«©¿‡
 * @author ÀÔœ˛”Í
 *
 */
public class MyLabel extends JLabel{
	private ImageIcon icon;
	private String name;
	private Font font = new Font("SanSerif", Font.BOLD, 18);
	public MyLabel(ImageIcon labelIcon, String name) {
		super();
		this.icon = labelIcon;
		this.name = name;
	}
	public MyLabel(ImageIcon labelIcon, String name, Font font) {
		super();
		this.icon = labelIcon;
		this.name = name;
		this.font = font;
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		int imageWidth = icon.getImage().getWidth(null);
		int imageHeight = icon.getImage().getHeight(null);
		g.setFont(font);
	//	g.drawString(name, this.getWidth() - imageWidth/ 2 , 10);
		g.drawImage(icon.getImage(), 0, 0, imageWidth, imageHeight,this);
		super.paint(g);
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	
}
