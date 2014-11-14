package com.scu.Model;

import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * ������ťͼ��
 * @author ������
 *
 */
public class MyZoomButton extends JButton{
	private ImageIcon buttonIcon;
	private ImageIcon pressIcon;

	public MyZoomButton(ImageIcon buttonIcon, ImageIcon pressIcon) {
		super();
		this.buttonIcon = buttonIcon;
		this.pressIcon = pressIcon;
		//��ť��Χ���¿հ�
		this.setMargin(new Insets(0, 0, 0, 0));
		//����Ϊ͸�������Բ�����
		//this.setOpaque(false);
		//�����Ʊ߿�
		this.setBorderPainted(false);
		//���� contentAreaFilled ���ԡ����������Ϊ true����ť�����������������ϣ����һ��͸���İ�ť������ֻ��һ��ͼ��İ�ť����ôӦ�ý�����������Ϊ false��
		this.setContentAreaFilled(false);
		//���ƽ���״̬
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
