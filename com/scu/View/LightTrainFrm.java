package com.scu.View;

import java.awt.Color;
import java.awt.Component;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.*;

import com.scu.Contral.NightLightThread;
import com.scu.GlobelControl.ConfigManager;
import com.scu.GlobelControl.SignalIcon;
import com.scu.Model.ExamWindow;
import com.scu.Model.Message;
import com.scu.Model.MyErrorTable;
import com.scu.Model.MyErrorTableModel;
import com.scu.Signal.JudgeSignal;
import com.scu.Utils.DBHelper;
import com.scu.Utils.GetLocateUtil;
import com.scu.View.ExamFrm.UIThread;
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
	private SignalIcon signalIcon = ConfigManager.signalIcon;
	LightTrainFrm window;
	NightLightThread nightLightThread = null;
	private ArrayList<HashMap<String, Object>> listData = new ArrayList<HashMap<String, Object>>();
	
	Vector<String> lamp_titles = new Vector<String>();
	DefaultListModel lamp_titles_mode = null;
	
	
	JLabel gps;
	JLabel wl;
	JLabel dw;
	JLabel xh;
	JLabel aqdbd;
	JLabel fss;
	JLabel hldg;
	JLabel jss;
	JLabel jgdg;
	JLabel lbs;
	JLabel qldg;
	JLabel sss;
	JLabel wdg;
	JLabel yjdg;
	JLabel yzxg;
	JLabel ygdg;
	JLabel zzxg;
	
	JLabel title_lb;
	String title_lb_string = "灯光题库名称";
	
	private JList typelist;
	private UIThread uiThread = null;

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
				new ImageIcon("images\\dgmn\\top.png"));
		headPanel.add(examCharacter);
		contentPanel.add(headPanel);
		
		JPanel btPanel = new JPanel();
		
		btPanel.setBounds(0, 50, WIDTH, 99);
		btPanel.setBackground(Color.WHITE);
		contentPanel.add(btPanel);
		btPanel.setLayout(new GridLayout(1, 5, 1, 1));

		//显示列表对话框
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LightListDialog dialog = new LightListDialog(
						LightTrainFrm.this.window);
				dialog.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon("images\\dgmn\\dgtk.png"));
		button.setRolloverIcon(new ImageIcon("images\\dgmn\\dgtk_touch.png"));
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
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
		button_1.setRolloverIcon(new ImageIcon("images\\dgmn\\add_touch.png"));
		button_1.setIcon(new ImageIcon("images\\dgmn\\add.png"));
		
		button_1.setMargin(new Insets(0, 0, 0, 0));
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		button_1.setFocusPainted(false);
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
		button_2.setIcon(new ImageIcon("images\\dgmn\\edit.png"));
		button_2.setRolloverIcon(new ImageIcon(
				"images\\dgmn\\edit_touch.png"));
		button_2.setMargin(new Insets(0, 0, 0, 0));
		button_2.setContentAreaFilled(false);
		button_2.setBorderPainted(false);
		button_2.setFocusPainted(false);
		btPanel.add(button_2);
		
		JLabel empty_lb = new JLabel();
		btPanel.add(empty_lb);

		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	new MainWindow("路考王");
				LightTrainFrm.this.dispose();
			}
		});
		button_3.setRolloverIcon(new ImageIcon("images\\dgmn\\end_touch.png"));
		button_3.setIcon(new ImageIcon("images\\dgmn\\end.png"));
		button_3.setMargin(new Insets(2, 2, 2, 2));
		button_3.setContentAreaFilled(false);
		button_3.setBorderPainted(false);
		button_3.setFocusPainted(false);
		btPanel.add(button_3);
		
		JPanel emptyPanel1 = new JPanel();
		emptyPanel1.setBounds(0, 149,
				WIDTH, 1);
//		emptyPanel1.setPreferredSize(new Dimension(WIDTH, 1));
		emptyPanel1.setBorder(BorderFactory.createLineBorder(new Color(12, 97, 159)));
		contentPanel.add(emptyPanel1);

		
		
		
		
		JPanel midPanel = new JPanel();
		midPanel.setBackground(Color.WHITE);
		midPanel.setBounds(0, 150,
				WIDTH, 54);
		contentPanel.add(midPanel);
		// 指示信号面板
		midPanel.setLayout(new GridLayout(1, 0, 0, 0));
		gps = new JLabel(signalIcon.getGpsgIcon());
		dw = new JLabel(signalIcon.getDkIcon());
		wl = new JLabel(signalIcon.getWlgIcon());
		xh = new JLabel(signalIcon.getXhgIcon());
		

		aqdbd = new JLabel(signalIcon.getAqdbdIcon());
		fss = new JLabel(signalIcon.getFssIcon());
		hldg = new JLabel(signalIcon.getHldgIcon());
		jss = new JLabel(signalIcon.getJssIcon());
		jgdg = new JLabel(signalIcon.getJgdgIcon());
		lbs = new JLabel(signalIcon.getLbsIcon());
		qldg = new JLabel(signalIcon.getQldgIcon());
		sss = new JLabel(signalIcon.getSssIcon());
		wdg = new JLabel(signalIcon.getWdgIcon());
		yjdg = new JLabel(signalIcon.getYjdgIcon());
		yzxg = new JLabel(signalIcon.getYzxgIcon());
		ygdg = new JLabel(signalIcon.getYgdgIcon());
		zzxg = new JLabel(signalIcon.getZzxgIcon());

		midPanel.add(gps);
		midPanel.add(wl);
		midPanel.add(dw);
		midPanel.add(xh);
		midPanel.add(zzxg);
		midPanel.add(yzxg);
		midPanel.add(yjdg);
		midPanel.add(jgdg);
		midPanel.add(ygdg);
		midPanel.add(wdg);
		midPanel.add(jss);
		midPanel.add(sss);
		midPanel.add(fss);
		midPanel.add(qldg);
		midPanel.add(hldg);
		midPanel.add(lbs);
		midPanel.add(aqdbd);
		
		JPanel emptyPanel2 = new JPanel();
		emptyPanel2.setBounds(0, 204,
				WIDTH, 1);
		emptyPanel2.setBorder(BorderFactory.createLineBorder(new Color(12, 97, 159)));
		contentPanel.add(emptyPanel2);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panel1.setBounds(0, 205, 600, 395);
		contentPanel.add(panel1);
		//添加表格标题面板
		JPanel panel_itemTitle = new JPanel();
		panel_itemTitle.setPreferredSize(new Dimension(600, 40));
		panel_itemTitle.setBackground(Color.WHITE);
		panel1.add(panel_itemTitle);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[] { 130, 40 };
		gbl_panel_6.columnWeights = new double[] { 0.0D, 0.0D,2.0D };
		gbl_panel_6.rowWeights = new double[] { 0.0D };
		panel_itemTitle.setLayout(gbl_panel_6);

		JLabel pointItem = new JLabel("扣分项目");
		pointItem.setHorizontalAlignment(0);
		pointItem.setForeground(Color.BLACK);
		pointItem.setFont(new Font("黑体", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_itemTitle.add(pointItem, gbc_lblNewLabel);

		JLabel label_minus = new JLabel("扣分");
		label_minus.setHorizontalAlignment(0);
		label_minus.setForeground(Color.BLACK);
		label_minus.setFont(new Font("黑体", Font.BOLD, 15));
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.fill = 2;
		gbc_label_9.gridx = 1;
		gbc_label_9.gridy = 0;
		panel_itemTitle.add(label_minus, gbc_label_9);

		JLabel label_result = new JLabel("扣分原因");
		label_result.setHorizontalAlignment(0);
		label_result.setForeground(Color.BLACK);
		label_result.setFont(new Font("黑体", Font.BOLD, 15));
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.insets = new Insets(0, 0, 5, 0);
		gbc_label_10.fill = 2;
		gbc_label_10.gridx = 2;
		gbc_label_10.gridy = 0;
		panel_itemTitle.add(label_result, gbc_label_10);

		JPanel emptyPanel3 = new JPanel();
		emptyPanel3.setPreferredSize(new Dimension(panel1.getWidth(), 1));
		emptyPanel3.setBorder(BorderFactory.createLineBorder(new Color(12, 97, 159)));
		panel1.add(emptyPanel3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setPreferredSize(new Dimension(600, 354));
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		panel1.add(scrollPane);

		this.errlist = new Vector();
		this.et_Model = new MyErrorTableModel(this.errlist);
		this.errTable = new MyErrorTable(this.et_Model);
		this.errTable.hideHeader();
		this.errTable.setFont(new Font("宋体", 1, 14));
		this.errTable.setRowHeight(30);
		this.errTable.setOpaque(false);
		this.errTable.setColumnWidth(0, 130);
		//this.errTable.setCellEditor(new MyEditor());
		this.errTable.setColumnWidth(1, 40);
		this.errTable.setColumnWidth(2, 430);
		scrollPane.setViewportView(this.errTable);
		
		JPanel emptyPanel4 = new JPanel();
		emptyPanel4.setBounds(600, 205, 1, 395);
		emptyPanel4.setBorder(BorderFactory.createLineBorder(new Color(12, 97, 159)));
		contentPanel.add(emptyPanel4);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panel2.setBounds(600, 206, 199, 395);
		contentPanel.add(panel2);
		
		title_lb = new JLabel(title_lb_string);
		title_lb.setPreferredSize(new Dimension(199, 40));
		title_lb.setHorizontalAlignment(0);
		title_lb.setBackground(new Color(12, 97, 159));
		title_lb.setOpaque(true);
		title_lb.setForeground(Color.WHITE);
		title_lb.setFont(new Font("黑体", Font.BOLD, 15));
		panel2.add(title_lb);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel2.add(scrollPane_1);
		scrollPane_1.setOpaque(false);
		scrollPane_1.setPreferredSize(new Dimension(199, 355));
		scrollPane_1.setBorder(BorderFactory.createEmptyBorder());
		
		this.typelist = new JList();
		this.typelist.setFont(new Font("宋体", Font.PLAIN, 14));
		this.typelist.setFixedCellHeight(30);
		this.typelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		scrollPane_1.setViewportView(this.typelist);
		
		Rectangle rect = this.errTable.getCellRect(
				this.et_Model.getRowCount() - 1, 0, true);
		this.errTable.scrollRectToVisible(rect);
		
		this.uiThread = new UIThread();
		this.uiThread.start();
		
		
		this.setVisible(true);
	}

	public void dispose() {
		if(this.nightLightThread != null)
			this.nightLightThread.runFlag = false;
		this.nightLightThread = null;
		
		if (this.uiThread != null)
			this.uiThread.runFlag = false;
		this.uiThread = null;
		
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
			System.out.println("灯光数据库有问题！");
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
		
		
		DBHelper db = new DBHelper();
		try {
			db.conn();

			
			String condition = "lamps_point.lamp_id = " + typeID + " and lamps_point.point_type = lamps_type.type_no";
			ResultSet rs = db.Query("lamps_point, lamps_type",
					condition, "type_title");
			if (rs != null)
				while (rs.next()) {
					String type_title = rs.getString("type_title");
					this.lamp_titles.add(type_title);
				}
			this.lamp_titles_mode = new DefaultListModel();
			for (int i = 0; i < this.lamp_titles.size(); i++) {
				this.lamp_titles_mode.addElement(this.lamp_titles.get(i));
			}
			this.typelist.setModel(this.lamp_titles_mode);
			
			rs.close();
			
			String condition1 = "lamps_no = " + typeID;
			ResultSet rs_title = db.Query("lamps", condition1, "lamps_title");
			String lamp_name = null;
			if (rs_title != null) {
				while (rs_title.next()) {
					lamp_name = rs_title.getString("lamps_title");
					this.title_lb_string = lamp_name;
					this.title_lb.setText(lamp_name);
				}
			}

			rs_title.close();
			
			
		} catch (Exception localException) {
			localException.printStackTrace();
		} finally {
			db.close();
		}
		
		LightTrainFrm.this.nightLightThread = new NightLightThread(this,
				NightLightThread.EXAMFLAG);
		nightLightThread.setExamId(typeID);
		addModule(nightLightThread);
		nightLightThread.start();
	}
	
	class UIThread extends Thread {
		public boolean runFlag = true;

		public UIThread() {
		}

		private void refreshTitle(JudgeSignal carSignal) {
			try {
			
				if(carSignal.gps){
					gps.setIcon(signalIcon.getGpskIcon());		
				}else
				{
					gps.setIcon(signalIcon.getGpsgIcon());					
				}
				
				if(carSignal.gear == -1){
					dw.setIcon(signalIcon.getDdIcon());					
				}else if(carSignal.gear == 0){
					dw.setIcon(signalIcon.getDkIcon());	
				}else if(carSignal.gear == 1){
					dw.setIcon(signalIcon.getD1Icon());	
				}else if(carSignal.gear == 2){
					dw.setIcon(signalIcon.getD2Icon());	
				}else if(carSignal.gear == 3){
					dw.setIcon(signalIcon.getD3Icon());	
				}else if(carSignal.gear == 4){
					dw.setIcon(signalIcon.getD4Icon());	
				}else {
					dw.setIcon(signalIcon.getD5Icon());	
				}
				
				if(carSignal.tcp){
					wl.setIcon(signalIcon.getWlkIcon());

				}else
				{
					wl.setIcon(signalIcon.getWlgIcon());
				}
				
				if(carSignal.signal_off){
					xh.setIcon(signalIcon.getXhkIcon());
				}else
				{
					xh.setIcon(signalIcon.getXhgIcon());
				}
				
				if(carSignal.signal_seatbelt){
					aqdbd.setIcon(signalIcon.getAqdbdIcon());
				}else
				{
					aqdbd.setIcon(signalIcon.getAqdcrIcon());
				}
				if(carSignal.signal_deputybrake){	
					fss.setIcon(signalIcon.getFscIcon());
				}else
				{
					fss.setIcon(signalIcon.getFssIcon());
				}
				
				if(carSignal.signal_rearbumper){
					hldg.setIcon(signalIcon.getHldkIcon());
				}else
				{
					hldg.setIcon(signalIcon.getHldgIcon());
				}
				
				if(carSignal.signal_footbrake){
					jss.setIcon(signalIcon.getJscIcon());
				}else
				{
					jss.setIcon(signalIcon.getJssIcon());
				}
				
				if(carSignal.lamp_near){
					jgdg.setIcon(signalIcon.getJgdkIcon());
				}else
				{
					jgdg.setIcon(signalIcon.getJgdgIcon());
				}
				
				if(carSignal.signal_horn){
					lbs.setIcon(signalIcon.getLbaIcon());
				}else
				{
					lbs.setIcon(signalIcon.getLbsIcon());
				}
				
				if(carSignal.signal_frontbumper){
					qldg.setIcon(signalIcon.getQldkIcon());
				}else
				{
					qldg.setIcon(signalIcon.getQldgIcon());
				}
				
				if(carSignal.signal_handbrake){
					sss.setIcon(signalIcon.getSskIcon());
				}else
				{
					sss.setIcon(signalIcon.getSssIcon());
				}
				
				if(carSignal.lamp_fog){
					wdg.setIcon(signalIcon.getWdkIcon());
				}else
				{
					wdg.setIcon(signalIcon.getWdgIcon());
				}
				
				if(carSignal.lamp_urgent){
					yjdg.setIcon(signalIcon.getYjdkIcon());					
				}else
				{
					yjdg.setIcon(signalIcon.getYjdgIcon());	
				}
				
				if(carSignal.lamp_right){
					yzxg.setIcon(signalIcon.getYzxkIcon());
				}else
				{
					yzxg.setIcon(signalIcon.getYzxgIcon());
				}
				
				if(carSignal.lamp_highbeam){
					ygdg.setIcon(signalIcon.getYgdkIcon());
				}else
				{
					ygdg.setIcon(signalIcon.getYgdgIcon());
				}
				
				if(carSignal.lamp_left){
					zzxg.setIcon(signalIcon.getZzxkIcon());
				}else
				{
					zzxg.setIcon(signalIcon.getZzxgIcon());
				}
				

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		public void run() {
			while (this.runFlag)
				try {
					JudgeSignal carSignal = JudgeSignal.getInstance();
					refreshTitle(carSignal);
					sleep(200);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}
}
