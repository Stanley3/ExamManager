package com.scu.Utils;

import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.*;
/**
 * 添加控件工具类
 * @author 孙晓雨 2014.10.10
 *
 */

public class AddComponentUtil {
	//空间被添加的面板
	JPanel jPanel;	
	public AddComponentUtil(JPanel jPanel) {
		this.jPanel = jPanel;
	}

	/**
	 * 
	 * @param c 需要添加的控件
	 * @param constraints 限制器
	 * @param x 横向索引
	 * @param y 纵向索引
	 * @param w 横向占几行
	 * @param h 纵向占几列
	 */
	public  void add(Component c, GridBagConstraints constraints, int x, int y, int w, int h ){
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		this.jPanel.add(c, constraints);
	}
}
