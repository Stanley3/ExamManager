package com.scu.Utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * @author 孙晓雨 2014.10.14
 *
 */
public class JPanelUtils {
	
	public static void compCombine(JPanel contentPanel, JLabel iconLabel , String labelName, ImageIcon icon, Rectangle rect, Font font){
		
		JPanel tempPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) tempPanel.getLayout();
		flowLayout.setAlignment(0);
		flowLayout.setHgap(25);
		tempPanel.setOpaque(false);
		tempPanel.setBorder(BorderFactory.createLineBorder(new Color(195, 201, 212)));
		contentPanel.add(tempPanel);

		JLabel label = new JLabel(labelName);
		label.setHorizontalAlignment(2);
		label.setForeground(Color.BLACK);
		label.setFont(font);
		tempPanel.add(label);

	//	iconLabel = new JLabel("");
		iconLabel.setIcon(icon);
		iconLabel.setBounds(rect);
		tempPanel.add(iconLabel);
	}
	public static void compCombine2(JPanel contentPanel, JLabel iconLabel , String labelName, ImageIcon icon, Rectangle rect, Font font){
		
		JPanel tempPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) tempPanel.getLayout();
		flowLayout.setAlignment(0);
		flowLayout.setHgap(15);
		tempPanel.setOpaque(false);
		tempPanel.setBorder(BorderFactory.createLineBorder(new Color(195, 201, 212)));
		contentPanel.add(tempPanel);

		JLabel label = new JLabel(labelName);
		label.setHorizontalAlignment(2);
		label.setForeground(Color.BLACK);
		label.setFont(font);
		tempPanel.add(label);

	//	iconLabel = new JLabel("");
		iconLabel.setIcon(icon);
		iconLabel.setBounds(rect);
		iconLabel.setPreferredSize(new Dimension(20, 20));
		tempPanel.add(iconLabel);
	}

	public static void addItem(JPanel pane, JLabel label, String path) {
		ImageIcon icon = new ImageIcon(path);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
		panel_1.setOpaque(false);
		pane.add(panel_1);
		label = new JLabel(icon);// 上车准备
		panel_1.add(label);

	}
}
