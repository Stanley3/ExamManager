package com.scu.Model;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * ��ť��
 * @author ������ 2014.10.11
 *
 */

public class MyButton extends JButton{
	private ImageIcon buttonIcon;
	private ImageIcon pressIcon;

	public MyButton(ImageIcon buttonIcon, ImageIcon pressIcon) {
		super();
		this.buttonIcon = buttonIcon;
		this.pressIcon = pressIcon;
		//��ť��Χ���¿հ�
		this.setMargin(new Insets(0, 0, 0, 0));
		//����Ϊ͸�������Բ�����
		this.setOpaque(false);
		//�����Ʊ߿�
		this.setBorderPainted(false);
		//���� contentAreaFilled ���ԡ����������Ϊ true����ť�����������������ϣ����һ��͸���İ�ť������ֻ��һ��ͼ��İ�ť����ôӦ�ý�����������Ϊ false��
		this.setContentAreaFilled(false);
		//���ƽ���״̬
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
