package com.scu.Utils;

import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.*;
/**
 * ��ӿؼ�������
 * @author ������ 2014.10.10
 *
 */

public class AddComponentUtil {
	//�ռ䱻��ӵ����
	JPanel jPanel;	
	public AddComponentUtil(JPanel jPanel) {
		this.jPanel = jPanel;
	}

	/**
	 * 
	 * @param c ��Ҫ��ӵĿؼ�
	 * @param constraints ������
	 * @param x ��������
	 * @param y ��������
	 * @param w ����ռ����
	 * @param h ����ռ����
	 */
	public  void add(Component c, GridBagConstraints constraints, int x, int y, int w, int h ){
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		this.jPanel.add(c, constraints);
	}
}
