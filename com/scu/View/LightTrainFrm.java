package com.scu.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.*;

import com.scu.Contral.NightLightThread;
import com.scu.GlobelControl.ConfigManager;
import com.scu.Model.ExamWindow;
import com.scu.Model.Message;
import com.scu.Model.MyErrorTable;
import com.scu.Model.MyErrorTableModel;
import com.scu.Utils.DBHelper;
import com.scu.Utils.GetLocateUtil;
/**
 * 灯光题库主界面
 * @author 孙晓雨 2014.10.21
 *
 */
public class LightTrainFrm extends ExamWindow {
	private static final long serialVersionUID = 1L;
	MyErrorTable errTable;
	Vector errlist;
	MyErrorTableModel et_Model;
	int WIDTH = ConfigManager.WIDTH;
	int HEIGHT = ConfigManager.HEIGHT;
	LightTrainFrm window;
	NightLightThread nightLightThread = null;
	private ArrayList<HashMap<String, Object>> listData = new ArrayList<HashMap<String, Object>>();

	public LightTrainFrm(String name) {
		this.window = this;
		JPanel contentPanel = (JPanel) this.getContentPane();
		this.setUndecorated(true);
		this.setForeground(new Color(185, 194, 203));
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		// 把系统设置对话窗口显示在屏幕正中心
		this.setLocation(x, y);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		// 设置窗口的布局方式为null
		contentPanel.setLayout(null);

		JPanel headPanel = new JPanel();
		headPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		headPanel.setBounds(0, 0, WIDTH, 50);
		JLabel examCharacter = new JLabel(
				new ImageIcon("images\\dgmn\\top.jpg"));
		headPanel.add(examCharacter);
		contentPanel.add(headPanel);

		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panel1.setBounds(0, 50, WIDTH, 400);
		contentPanel.add(panel1);
		//添加表格标题面板
		JPanel panel_itemTitle = new JPanel();
		panel_itemTitle.setPreferredSize(new Dimension(WIDTH, 40));
		panel_itemTitle.setBackground(new Color(60, 60, 248));
		panel1.add(panel_itemTitle);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[] { 100, 60 };
		gbl_panel_6.columnWeights = new double[] { 0.0D, 0.0D,2.0D };
		gbl_panel_6.rowWeights = new double[] { 0.0D };
		panel_itemTitle.setLayout(gbl_panel_6);

		JLabel pointItem = new JLabel("扣分项目");
		pointItem.setHorizontalAlignment(0);
		pointItem.setForeground(Color.WHITE);
		pointItem.setFont(new Font("华文中宋", 1, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = 2;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_itemTitle.add(pointItem, gbc_lblNewLabel);

		JLabel label_minus = new JLabel("扣分");
		label_minus.setHorizontalAlignment(0);
		label_minus.setForeground(Color.WHITE);
		label_minus.setFont(new Font("华文中宋", 1, 18));
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.fill = 2;
		gbc_label_9.gridx = 1;
		gbc_label_9.gridy = 0;
		panel_itemTitle.add(label_minus, gbc_label_9);

		JLabel label_result = new JLabel("扣分原因");
		label_result.setHorizontalAlignment(0);
		label_result.setForeground(Color.WHITE);
		label_result.setFont(new Font("华文中宋", 1, 18));
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.insets = new Insets(0, 0, 5, 0);
		gbc_label_10.fill = 2;
		gbc_label_10.gridx = 2;
		gbc_label_10.gridy = 0;
		panel_itemTitle.add(label_result, gbc_label_10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setPreferredSize(new Dimension(WIDTH, 460));
		panel1.add(scrollPane);

		this.errlist = new Vector();
		this.et_Model = new MyErrorTableModel(this.errlist);
		this.errTable = new MyErrorTable(this.et_Model);
		this.errTable.hideHeader();
		this.errTable.setFont(new Font("华文中宋", 1, 16));
		this.errTable.setRowHeight(30);
		this.errTable.setOpaque(false);
		this.errTable.setColumnWidth(0, 100);
		this.errTable.setColumnWidth(1, 60);
		scrollPane.setViewportView(this.errTable);

		JPanel btPanel = new JPanel();
		
		btPanel.setBounds(0, 450, WIDTH, 150);
		btPanel.setBackground(Color.WHITE);
		contentPanel.add(btPanel);
		btPanel.setLayout(new GridLayout(1, 4, 1, 1));

		//显示列表对话框
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LightListDialog dialog = new LightListDialog(
						LightTrainFrm.this.window);
				dialog.setVisible(true);
			}
		});
		button.setRolloverIcon(new ImageIcon(
				"images\\icons\\btn_dg_list_touch.png"));
		button.setIcon(new ImageIcon("images\\icons\\btn_dg_list.png"));
		button.setMargin(new Insets(2, 2, 2, 2));
		button.setFont(new Font("华文中宋", 1, 26));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		btPanel.add(button);

		//显示灯光题库编辑界面
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						new LampEditWindow(0).setVisible(true);
						// LightTrainiFrm.this.dispose();
					}
				});
			}
		});
		button_1.setRolloverIcon(new ImageIcon(
				"images\\icons\\btn_dg_add_touch.png"));
		button_1.setIcon(new ImageIcon("images\\icons\\btn_dg_add.png"));
		button_1.setMargin(new Insets(2, 2, 2, 2));
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		btPanel.add(button_1);

		//显示灯光题库编辑界面
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LightEditDialog dialog = new
						LightEditDialog(LightTrainFrm.this.window);
				 dialog.setVisible(true);
			}
		});
		button_2.setIcon(new ImageIcon("images\\icons\\btn_dg_edit.png"));
		button_2.setRolloverIcon(new ImageIcon(
				"images\\icons\\btn_dg_edit_touch.png"));
		button_2.setMargin(new Insets(2, 2, 2, 2));
		button_2.setContentAreaFilled(false);
		button_2.setBorderPainted(false);
		btPanel.add(button_2);

		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainWindow("路考王");
				LightTrainFrm.this.dispose();
			}
		});
		button_3.setRolloverIcon(new ImageIcon(
				"images\\icons\\btn_dg_back_touch.png"));
		button_3.setIcon(new ImageIcon("images\\icons\\btn_dg_back.png"));
		button_3.setMargin(new Insets(2, 2, 2, 2));
		button_3.setContentAreaFilled(false);
		button_3.setBorderPainted(false);
		btPanel.add(button_3);

		Rectangle rect = this.errTable.getCellRect(
				this.et_Model.getRowCount() - 1, 0, true);
		this.errTable.scrollRectToVisible(rect);
		this.setVisible(true);
	}

	public void dispose() {
		if(this.nightLightThread != null)
			this.nightLightThread.runFlag = false;
		this.nightLightThread = null;
		super.dispose();
	}

	public void handleMessage(Message msg) {
		if (!this.isRunning)
			return;
		try {
			switch (msg.what) {
			case -4:
				HashMap errbundle = msg.Bundle;
				String title = errbundle.get("title").toString();
				int iscore = Integer
						.parseInt(errbundle.get("score").toString());
				String reason = errbundle.get("reason").toString();
				String code = errbundle.get("code").toString();
				int type = Integer.parseInt(errbundle.get("type").toString());
				addErrItem(title, iscore, reason, code, type);
			}
		} catch (Exception localException) {
		}
	}

	private int getRandomNight() {
		int random = 0;
		int[] lamps_ids = new int[1];
		DBHelper db = new DBHelper();
		try {
			db.conn();
			int cnt = db.QueryCnt("lamps", null);
			if (cnt > 0) {
				lamps_ids = new int[cnt];
			}
			ResultSet rs = db.Query("lamps", null, "*");
			int idx = 0;
			if (rs != null)
				try {
					while (rs.next()) {
						int id = rs.getInt(0);
						lamps_ids[idx] = id;
						idx++;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			else
				System.out.println("result is null");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			db.close();
		}
		random = (int) (Math.random() * 100.0D) % lamps_ids.length;
		if ((random < 0) || (random > lamps_ids.length - 1))
			random = 0;
		random = lamps_ids[random];
		return random;
	}

	public void addErrItem(String title, int score, String reason, String code,
			int type) {
		HashMap map = new HashMap();
		map.put("title", title);
		map.put("score", Integer.valueOf(score));
		map.put("reason", reason);
		map.put("code", code);
		map.put("type", Integer.valueOf(type));
		this.listData.add(map);
		this.et_Model.addRow(title, score, reason);
		try {
			this.errTable.updateUI();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Rectangle rect = this.errTable.getCellRect(
				this.et_Model.getRowCount() - 1, 0, true);
		this.errTable.scrollRectToVisible(rect);
	}

	public void startItem(int typeID) {
		removeAll();
		LightTrainFrm.this.nightLightThread = new NightLightThread(this,
				NightLightThread.EXAMFLAG);
		nightLightThread.setExamId(typeID);
		addModule(nightLightThread);
		nightLightThread.start();
	}

}
