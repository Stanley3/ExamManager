package com.scu.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import com.scu.Contral.BusThread;
import com.scu.Contral.ChangeLaneThread;
import com.scu.Contral.GearThread;
import com.scu.Contral.GolbalExamThread;
import com.scu.Contral.LuKouStraightThread;
import com.scu.Contral.MeetingThread;
import com.scu.Contral.ModuleThread;
import com.scu.Contral.OnLineThread;
import com.scu.Contral.OverTakenThread;
import com.scu.Contral.PavementThread;
import com.scu.Contral.PrepareThread;
import com.scu.Contral.SchoolAreaThread;
import com.scu.Contral.StartThread;
import com.scu.Contral.StopThread;
import com.scu.Contral.TurnAroundThread;
import com.scu.Contral.TurnLeftThread;
import com.scu.Contral.TurnRightThread;
import com.scu.GlobelControl.ConfigManager;
import com.scu.GlobelControl.ImageIconSet;
import com.scu.Model.ExamWindow;
import com.scu.Model.Message;
import com.scu.Model.MyErrorTable;
import com.scu.Model.MyErrorTableModel;
import com.scu.Model.MyZoomButton;
import com.scu.Signal.JudgeSignal;
import com.scu.Utils.DBHelper;
import com.scu.Utils.GetLocateUtil;
import com.scu.Utils.JPanelUtils;

/**
 * 日常训练窗口
 * 
 * @author 孙晓雨 2014.10.13
 * 
 */
public class RoutineTrainingFrm extends ExamWindow {
	private static final long serialVersionUID = 1L;
	private final int WIDTH = ConfigManager.WIDTH;
	private final int HEIGHT = ConfigManager.HEIGHT;
	private static ImageIconSet allImage = ConfigManager.allImage;
	JLabel ItemTitleLabel;
	JLabel timerLabel;
	JTextArea descLabel;
	JLabel globelTimer;

	JLabel lab_lamp_width = new JLabel("");
	JLabel lab_lamp_near = new JLabel("");
	JLabel lab_lamp_highbeam = new JLabel("");
	JLabel lab_lamp_fog = new JLabel("");
	JLabel lab_lamp_left = new JLabel("");
	JLabel lab_lamp_right = new JLabel("");
	JLabel lab_lamp_urgent = new JLabel("");
	JLabel lab_lamp_brake = new JLabel("");
	JLabel lab_signal_rightbrake = new JLabel("");
	JLabel lab_signal_door = new JLabel("");
	JLabel lab_signal_life = new JLabel("");
	JLabel lab_signal_clutchpedal = new JLabel("");
	JLabel lab_signal_handbrake = new JLabel("");
	JLabel lab_signal_horn = new JLabel("");
	JLabel lab_signal_acc = new JLabel("");
	JLabel lab_signal_ignition = new JLabel("");
	JLabel lab_signal_carsidea = new JLabel("");
	JLabel lab_signal_carsideb = new JLabel("");
	JLabel lab_signal_wheelangle = new JLabel("");
	JLabel lab_signal_n = new JLabel("");
	JLabel lab_signal_V = new JLabel("");
	JLabel lab_gps_lon = new JLabel("");
	JLabel lab_gps_lat = new JLabel("");
	JLabel lab_gps_angle = new JLabel("");
	JLabel lab_signal_gear = new JLabel("");

	Vector errList;
	MyErrorTable errTable;
	MyErrorTableModel errModel;

	private TimerThread timerTickThread = null;
	private ArrayList<HashMap<String, Object>> listData = new ArrayList();
	private ArrayList<HashMap<String, String>> listType = new ArrayList();

	GolbalExamThread golbalExamThread = new GolbalExamThread(this, PrepareThread.TRAINFLAG);;
	UIThread uiThread = new UIThread();

	public int iState = -1;
	// 主屏幕的18个按钮
	JButton btn_rgzl_sczb = new MyZoomButton(allImage.getSczbIcon(),
			allImage.getSczb_touchIcon());
	JButton btn_rgzl_qb = new MyZoomButton(allImage.getQbIcon(),
			allImage.getQb_touchIcon());
	JButton btn_rgzl_zxxs = new MyZoomButton(allImage.getZxxsIcon(),
			allImage.getZxxs_touchIcon());
	JButton btn_rgzl_jjdcz = new MyZoomButton(allImage.getJjdczIcon(),
			allImage.getJjdcz_touchIcon());

	JButton btn_rgzl_bgcd = new MyZoomButton(allImage.getBgcdIcon(),
			allImage.getBgcd_touchIcon());
	JButton btn_rgzl_kbtc = new MyZoomButton(allImage.getKbtcIcon(),
			allImage.getKbtc_touchIcon());
	JButton btn_rgzl_lkzx = new MyZoomButton(allImage.getLkzzIcon(),
			allImage.getLkzz_touchIcon());
	JButton btn_rgzl_lkzzw = new MyZoomButton(allImage.getLkzzwIcon(),
			allImage.getLkzzw_touchIcon());
	JButton btn_rgzl_lkyzw = new MyZoomButton(allImage.getLkyzwIcon(),
			allImage.getLkyzw_touchIcon());
	JButton btn_rgzl_rxhdx = new MyZoomButton(allImage.getRxhdxIcon(),
			allImage.getRxhdx_touchIcon());

	JButton btn_rgzl_xxqy = new MyZoomButton(allImage.getXxqyIcon(),
			allImage.getXxqy_touchIcon());
	JButton btn_rgzl_ggqcz = new MyZoomButton(allImage.getGjqczIcon(),
			allImage.getGjqcz_touchIcon());
	JButton btn_rgzl_hc = new MyZoomButton(allImage.getHcIcon(),
			allImage.getHc_touchIcon());
	JButton btn_rgzl_cc = new MyZoomButton(allImage.getCcIcon(),
			allImage.getCc_touchIcon());
	JButton btn_rgzl_dt = new MyZoomButton(allImage.getDtIcon(),
			allImage.getDt_touchIcon());

	Rectangle rect = new Rectangle(0, 0, 10, 10);
	Font font = new Font("华文中宋", Font.BOLD, 15);

	PrepareThread prepareThread;
	StartThread startThread;
	OnLineThread onLineThread;
	GearThread gearThread;
	ChangeLaneThread changeLaneThread;
	StopThread stopThread;
	LuKouStraightThread crossingLineThread;
	TurnLeftThread turnLeftThread;
	TurnRightThread turnRightThread;
	PavementThread pavementThread;
	SchoolAreaThread schoolThread;
	BusThread busThread;
	MeetingThread meetingThread;
	OverTakenThread overTakenThread;
	TurnAroundThread turnAroundThread;

	public RoutineTrainingFrm(String winName) {
		this.init();
	}

	private void init() {

		initType();
		JPanel contentPanel = (JPanel) this.getContentPane();
		this.setUndecorated(true);
		this.setForeground(new Color(185, 194, 203));
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		// 把系统设置对话窗口显示在屏幕正中心
		this.setLocation(x, y);
		this.setSize(WIDTH, HEIGHT);
		// 设置窗口的布局方式为GirdBagLayout
		contentPanel.setLayout(null);
		this.setVisible(true);

		JPanel topPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = allImage.getHeadIcon().getImage();
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		};
		topPanel.setPreferredSize(new Dimension(ConfigManager.WIDTH, 50));
		topPanel.setBounds(0, 0, ConfigManager.WIDTH, 50);
		topPanel.setLayout(new BorderLayout(0, 0));

		JLabel titleLabel = new JLabel("日常路训");
		titleLabel.setHorizontalAlignment(0);
		titleLabel.setFont(new Font("华文中宋", 1, 26));
		titleLabel.setForeground(Color.WHITE);
		topPanel.add(titleLabel, "Center");
		contentPanel.add(topPanel);

		JPanel bottomPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = allImage.getHeadIcon().getImage();
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		};
		// bottomPanel.setPreferredSize(new Dimension(ConfigManager.WIDTH, 60));
		bottomPanel.setBounds(0, ConfigManager.HEIGHT - 50,
				ConfigManager.WIDTH, 50);
		contentPanel.add(bottomPanel);

		JButton btn_set_back = new JButton("");
		btn_set_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 if (RoutineTrainingFrm.this.golbalExamThread != null) {
					 RoutineTrainingFrm.this.golbalExamThread.runFlag = false;
			        }
				new MainWindow("路考王");
				RoutineTrainingFrm.this.dispose();
			}
		});
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		btn_set_back.setBorderPainted(false);
		btn_set_back.setIcon(allImage.getSet_backIcon());
		btn_set_back.setRolloverIcon(allImage.getSet_back_touchIcon());
		btn_set_back.setMargin(new Insets(2, 30, 2, 30));
		btn_set_back.setContentAreaFilled(true);
		btn_set_back.setPreferredSize(new Dimension(130, 50));
		bottomPanel.add(btn_set_back);
		// 除了上下标签以外的主窗口部分
		JPanel midPanel = new JPanel();
		midPanel.setBorder(null);
		// midPanel.setPreferredSize(new Dimension(ConfigManager.WIDTH,
		// ConfigManager.HEIGHT - topPanel.getHeight()
		// - bottomPanel.getHeight()));
		midPanel.setBounds(
				0,
				topPanel.getHeight(),
				ConfigManager.WIDTH,
				ConfigManager.HEIGHT - topPanel.getHeight()
						- bottomPanel.getHeight());

		contentPanel.add(midPanel);
		// *************************划分上下标签中间部分************************/
		midPanel.setLayout(null);
		// 设置中层面板的上半部分
		JPanel midTopPanel = new JPanel();
		midTopPanel.setBounds(0, 0, ConfigManager.WIDTH,
				midPanel.getHeight() * 3 / 4);
		midTopPanel.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		midPanel.add(midTopPanel);
		// 设置中层面板的下半部分
		JPanel midBottomPanel = new JPanel();
		midBottomPanel.setBounds(0, midTopPanel.getHeight(),
				ConfigManager.WIDTH, midPanel.getHeight() * 1 / 4);
		midBottomPanel.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		// midBottomPanel.setBackground(Color.BLACK);
		midPanel.add(midBottomPanel);

		// 分割中层面板的上半部分
		midTopPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(
				midTopPanel.getWidth() * 2 / 3, midTopPanel.getHeight()));
		leftPanel.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		midTopPanel.add(leftPanel);

		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(
				midTopPanel.getWidth() * 1 / 3 - 5, midTopPanel.getHeight()));
		rightPanel.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		midTopPanel.add(rightPanel);
		// 分割中层面板的下半部分
		midBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		JPanel titlePane = new JPanel();
		titlePane.setBackground(new Color(83, 105, 128));
		midBottomPanel.add(titlePane);
		titlePane.setPreferredSize(new Dimension(titlePane.getParent()
				.getWidth() - 5, 30));
		titlePane.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		GridBagLayout gridBageLayout = new GridBagLayout();
		gridBageLayout.columnWidths = new int[] { 100, 60 };
		gridBageLayout.columnWeights = new double[] { 0.0D, 0.0D, 1.0D };
		gridBageLayout.rowWeights = new double[] { 0.0D };
		titlePane.setLayout(gridBageLayout);

		JLabel newLabel1 = new JLabel("扣分项目");
		newLabel1.setHorizontalAlignment(JLabel.CENTER);
		newLabel1.setForeground(Color.WHITE);
		newLabel1.setFont(font);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		titlePane.add(newLabel1, gbc_lblNewLabel);

		JLabel newLabel2 = new JLabel("扣分");
		newLabel2.setHorizontalAlignment(JLabel.CENTER);
		newLabel2.setForeground(Color.WHITE);
		newLabel2.setFont(font);
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_9.gridx = 1;
		gbc_label_9.gridy = 0;
		titlePane.add(newLabel2, gbc_label_9);

		JLabel newLabel3 = new JLabel("扣分原因");
		newLabel3.setHorizontalAlignment(JLabel.CENTER);
		newLabel3.setForeground(Color.WHITE);
		newLabel3.setFont(font);
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.insets = new Insets(0, 0, 5, 0);
		gbc_label_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_10.gridx = 2;
		gbc_label_10.gridy = 0;
		titlePane.add(newLabel3, gbc_label_10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		midBottomPanel.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(scrollPane.getParent()
				.getWidth() - 5, midBottomPanel.getHeight() - 35));

		this.errList = new Vector();
		this.errModel = new MyErrorTableModel(this.errList);
		this.errTable = new MyErrorTable(this.errModel);
//		this.errModel.addRow("准备错误", -10, "不按规定准备");

//		scrollPane.setBackground(Color.BLACK);
		this.errTable.hideHeader();
		this.errTable.setFont(font);
		this.errTable.setRowHeight(30);
		this.errTable.setOpaque(false);
		this.errTable.setColumnWidth(0, 100);
		this.errTable.setColumnWidth(1, 60);
		this.errTable.setColumnWidth(2, titlePane.getParent().getWidth() - 170);
		scrollPane.setViewportView(this.errTable);

		// 分割中层面板的上半部分的左半部分
		leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		/******************** 分割中层面板的上半部分的左半部分 的上部分 **************************/
		JPanel leftTopPanel = new JPanel();
		leftTopPanel.setPreferredSize(new Dimension(
				midTopPanel.getWidth() * 2 / 3, midTopPanel.getHeight() / 2));
		leftTopPanel.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		// leftTopPanel.setBackground(Color.GREEN);
		leftPanel.add(leftTopPanel);

		leftTopPanel.setLayout(new GridLayout(3, 5, 0, 0));
		leftTopPanel.add(btn_rgzl_sczb);
		leftTopPanel.add(btn_rgzl_qb);
		leftTopPanel.add(btn_rgzl_zxxs);
		leftTopPanel.add(btn_rgzl_jjdcz);
		leftTopPanel.add(btn_rgzl_bgcd);

		leftTopPanel.add(btn_rgzl_kbtc);
		leftTopPanel.add(btn_rgzl_lkzx);
		leftTopPanel.add(btn_rgzl_lkzzw);
		leftTopPanel.add(btn_rgzl_lkyzw);
		leftTopPanel.add(btn_rgzl_rxhdx);

		leftTopPanel.add(btn_rgzl_xxqy);
		leftTopPanel.add(btn_rgzl_ggqcz);
		leftTopPanel.add(btn_rgzl_hc);
		leftTopPanel.add(btn_rgzl_cc);
		leftTopPanel.add(btn_rgzl_dt);

		this.btn_rgzl_sczb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RoutineTrainingFrm.this.startItem(1);
			}
		});

		this.btn_rgzl_qb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RoutineTrainingFrm.this.startItem(2);
			}
		});
		this.btn_rgzl_zxxs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RoutineTrainingFrm.this.startItem(3);
			}
		});
		this.btn_rgzl_jjdcz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RoutineTrainingFrm.this.startItem(14);
			}
		});
		this.btn_rgzl_bgcd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RoutineTrainingFrm.this.startItem(4);
			}
		});
		this.btn_rgzl_kbtc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RoutineTrainingFrm.this.startItem(11);
			}
		});
		this.btn_rgzl_lkzx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoutineTrainingFrm.this.startItem(5);
			}
		});
		this.btn_rgzl_lkzzw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoutineTrainingFrm.this.startItem(15);
			}
		});
		this.btn_rgzl_lkyzw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoutineTrainingFrm.this.startItem(16);
			}
		});
		this.btn_rgzl_rxhdx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoutineTrainingFrm.this.startItem(6);
			}
		});
		this.btn_rgzl_xxqy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoutineTrainingFrm.this.startItem(7);
			}
		});
		this.btn_rgzl_ggqcz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoutineTrainingFrm.this.startItem(8);
			}
		});
		this.btn_rgzl_hc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoutineTrainingFrm.this.startItem(9);
			}
		});
		this.btn_rgzl_cc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoutineTrainingFrm.this.startItem(10);
			}
		});
		this.btn_rgzl_dt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoutineTrainingFrm.this.startItem(12);
			}
		});
		/******************** 分割中层面板的上半部分的左半部分 的下部分 **************************/
		JPanel leftBottomPanel = new JPanel();
		leftBottomPanel
				.setPreferredSize(new Dimension(midTopPanel.getWidth() * 2 / 3,
						midTopPanel.getHeight() / 2 - 5));
		leftBottomPanel.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		// leftBottomPanel.setBackground(Color.orange);
		leftPanel.add(leftBottomPanel);

		leftBottomPanel.setLayout(new GridLayout(5, 5, 0, 0));

		JPanelUtils.compCombine2(leftBottomPanel, this.lab_lamp_width, "示宽灯",
				allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine2(leftBottomPanel, this.lab_lamp_near, "近光灯",
				allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine2(leftBottomPanel, this.lab_lamp_highbeam,
				"远光灯", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine2(leftBottomPanel, this.lab_lamp_left, "左转灯",
				allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine2(leftBottomPanel, this.lab_lamp_right, "右转灯",
				allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine2(leftBottomPanel, this.lab_lamp_fog, "雾   灯",
				allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine2(leftBottomPanel, this.lab_lamp_urgent, "应急灯",
				allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine2(leftBottomPanel, this.lab_signal_door,
				"车   门", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine2(leftBottomPanel, this.lab_signal_handbrake,
				"手   刹", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine2(leftBottomPanel, this.lab_signal_life, "安全带",
				allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine2(leftBottomPanel, this.lab_lamp_brake, "脚   刹",
				allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine2(leftBottomPanel, this.lab_signal_rightbrake,
				"副脚刹", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine2(leftBottomPanel, this.lab_signal_acc, " ACC ",
				allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine2(leftBottomPanel, this.lab_signal_ignition,
				"点   火", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine2(leftBottomPanel, this.lab_signal_horn,
				"喇   叭", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine2(leftBottomPanel, this.lab_signal_clutchpedal,
				"离合器", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine2(leftBottomPanel, this.lab_signal_carsidea,
				"绕前点", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine2(leftBottomPanel, this.lab_signal_carsideb,
				"绕后点", allImage.getSwitchcloseIcon(), rect, font);

		JPanel panel1 = new JPanel();
		panel1.setOpaque(false);
		panel1.setBorder(BorderFactory
				.createLineBorder(new Color(195, 201, 212)));
		leftBottomPanel.add(panel1);
		panel1.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel label_39 = new JLabel("车   速");
		label_39.setHorizontalAlignment(0);
		label_39.setForeground(Color.BLACK);
		label_39.setFont(new Font("华文中宋", Font.BOLD, 14));
		panel1.add(label_39);

		this.lab_signal_V = new JLabel("25.0Km/h");
		this.lab_signal_V.setHorizontalAlignment(0);
		this.lab_signal_V.setForeground(Color.BLACK);
		this.lab_signal_V.setFont(new Font("华文中宋", Font.BOLD, 14));
		panel1.add(this.lab_signal_V);

		JPanel panel2 = new JPanel();
		panel2.setOpaque(false);
		panel2.setBorder(BorderFactory
				.createLineBorder(new Color(195, 201, 212)));
		leftBottomPanel.add(panel2);
		panel2.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel label_41 = new JLabel("转   速");
		label_41.setHorizontalAlignment(0);
		label_41.setForeground(Color.BLACK);
		label_41.setFont(new Font("华文中宋", Font.BOLD, 14));
		panel2.add(label_41);

		this.lab_signal_n = new JLabel("1890RPM");
		this.lab_signal_n.setHorizontalAlignment(0);
		this.lab_signal_n.setForeground(Color.BLACK);
		this.lab_signal_n.setFont(new Font("华文中宋", Font.BOLD, 14));
		panel2.add(this.lab_signal_n);

		JPanel panel3 = new JPanel();
		panel3.setOpaque(false);
		panel3.setBorder(BorderFactory
				.createLineBorder(new Color(195, 201, 212)));
		leftBottomPanel.add(panel3);
		panel3.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel label_43 = new JLabel("LON");
		label_43.setHorizontalAlignment(0);
		label_43.setForeground(Color.BLACK);
		label_43.setFont(new Font("华文中宋", Font.BOLD, 14));
		panel3.add(label_43);

		this.lab_gps_lon = new JLabel("100.123456");
		this.lab_gps_lon.setHorizontalAlignment(0);
		this.lab_gps_lon.setForeground(Color.BLACK);
		this.lab_gps_lon.setFont(new Font("华文中宋", 1, 14));
		panel3.add(this.lab_gps_lon);

		JPanel panel4 = new JPanel();
		panel4.setOpaque(false);
		panel4.setBorder(BorderFactory
				.createLineBorder(new Color(195, 201, 212)));
		leftBottomPanel.add(panel4);
		panel4.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel label_45 = new JLabel("LAT");
		label_45.setHorizontalAlignment(0);
		label_45.setForeground(Color.BLACK);
		label_45.setFont(new Font("华文中宋", Font.BOLD, 14));
		panel4.add(label_45);

		this.lab_gps_lat = new JLabel("30.123456");
		this.lab_gps_lat.setHorizontalAlignment(0);
		this.lab_gps_lat.setForeground(Color.BLACK);
		this.lab_gps_lat.setFont(new Font("华文中宋", 1, 14));
		panel4.add(this.lab_gps_lat);

		JPanel panel5 = new JPanel();
		panel5.setOpaque(false);
		panel5.setBorder(BorderFactory
				.createLineBorder(new Color(195, 201, 212)));
		leftBottomPanel.add(panel5);
		panel5.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel label_47 = new JLabel("GPS角度");
		label_47.setHorizontalAlignment(0);
		label_47.setForeground(Color.BLACK);
		label_47.setFont(new Font("华文中宋", 1, 14));
		panel5.add(label_47);

		this.lab_gps_angle = new JLabel("210");
		this.lab_gps_angle.setHorizontalAlignment(0);
		this.lab_gps_angle.setForeground(Color.BLACK);
		this.lab_gps_angle.setFont(new Font("华文中宋", Font.BOLD, 14));
		panel5.add(this.lab_gps_angle);

		JPanel panel6 = new JPanel();
		panel6.setOpaque(false);
		panel6.setBorder(BorderFactory
				.createLineBorder(new Color(195, 201, 212)));
		leftBottomPanel.add(panel6);
		panel6.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel label_51 = new JLabel("档   位");
		label_51.setHorizontalAlignment(0);
		label_51.setForeground(Color.BLACK);
		label_51.setFont(new Font("华文中宋", Font.BOLD, 14));
		panel6.add(label_51);

		this.lab_signal_gear = new JLabel("4");
		this.lab_signal_gear.setHorizontalAlignment(0);
		this.lab_signal_gear.setForeground(Color.BLACK);
		this.lab_signal_gear.setFont(new Font("华文中宋", Font.BOLD, 14));
		panel6.add(this.lab_signal_gear);

		JPanel panel7 = new JPanel();
		panel7.setOpaque(false);
		panel7.setBorder(BorderFactory
				.createLineBorder(new Color(195, 201, 212)));
		leftBottomPanel.add(panel7);
		panel7.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel label_53 = new JLabel("方向盘");
		label_53.setHorizontalAlignment(0);
		label_53.setForeground(Color.BLACK);
		label_53.setFont(new Font("华文中宋", Font.BOLD, 14));
		panel7.add(label_53);

		this.lab_signal_wheelangle = new JLabel("270");
		this.lab_signal_wheelangle.setHorizontalAlignment(0);
		this.lab_signal_wheelangle.setForeground(Color.BLACK);
		this.lab_signal_wheelangle.setFont(new Font("华文中宋", Font.BOLD, 14));
		panel7.add(this.lab_signal_wheelangle);

		/**********************************************************************************/
		// 分割中层面板的上半部分的右半部分

		rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		JPanel trainTitle = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = allImage.getHeadIcon().getImage();
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		};
		trainTitle.setBackground(new Color(83, 105, 128));
		trainTitle.setPreferredSize(new Dimension(midTopPanel.getWidth() / 3,
				40));
		trainTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel label = new JLabel("训练项目");
		// label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("华文中宋", 1, 14));
		trainTitle.add(label);
		rightPanel.add(trainTitle);

		JPanel panelTemp = new JPanel();
		rightPanel.add(panelTemp);
		panelTemp.setPreferredSize(new Dimension(new Dimension(midTopPanel
				.getWidth() / 3 - 4, 40)));
		panelTemp.setLayout(new FlowLayout(1, 5, 7));
		panelTemp.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));

		this.ItemTitleLabel = new JLabel("");
		this.ItemTitleLabel.setHorizontalAlignment(JLabel.CENTER);
		this.ItemTitleLabel.setForeground(new Color(255, 0, 0));
		this.ItemTitleLabel.setFont(new Font("华文中宋", Font.BOLD, 16));
		//this.ItemTitleLabel.setText("你妹的");
		panelTemp.add(this.ItemTitleLabel);

		JPanel panelTimer = new JPanel();
		rightPanel.add(panelTimer);
		panelTimer.setPreferredSize(new Dimension(midTopPanel.getWidth() / 3,
				40));
		panelTimer.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		panelTimer.setLayout(new FlowLayout(1, 5, 7));

	
		
		this.timerLabel = new JLabel("");
		this.timerLabel.setHorizontalAlignment(2);
		this.timerLabel.setForeground(new Color(196, 22, 31));
		this.timerLabel.setFont(new Font("华文中宋", Font.BOLD, 16));
		//this.timerLabel.setText("辉哥爱凤姐");
		panelTimer.add(this.timerLabel);
		//添加通用评判倒计时
		JPanel gTimer = new JPanel();
		rightPanel.add(gTimer);
		gTimer.setPreferredSize(new Dimension(midTopPanel.getWidth() / 3,
				40));
		gTimer.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		gTimer.setLayout(new FlowLayout(1, 5, 7));

		gTimer.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		JLabel timeStart = new JLabel("     通用评判开始 :  ");
		timeStart.setHorizontalAlignment(2);
		timeStart.setForeground(new Color(196, 22, 31));
		timeStart.setFont(new Font("华文中宋", Font.BOLD, 16));
		gTimer.add(timeStart);
		
		this.globelTimer = new JLabel("");
		this.globelTimer.setHorizontalAlignment(2);
		this.globelTimer.setForeground(new Color(196, 22, 31));
		this.globelTimer.setFont(new Font("华文中宋", Font.BOLD, 16));
		this.globelTimer.setText("辉哥爱凤姐");
		gTimer.add(this.globelTimer);

		JScrollPane panelDec = new JScrollPane();
		panelDec.setPreferredSize(new Dimension(midTopPanel.getWidth() / 3,
				midTopPanel.getHeight() - 85));

		this.descLabel = new JTextArea("");
		this.descLabel.setEnabled(false);
		this.descLabel.setEditable(false);
		this.descLabel.setLineWrap(true);
		this.descLabel.setWrapStyleWord(true);
		this.descLabel.setForeground(Color.BLACK);
		this.descLabel.setFont(new Font("华文中宋", 1, 14));
		panelDec.setViewportView(this.descLabel);
		this.descLabel.setPreferredSize(new Dimension(
				midTopPanel.getWidth() / 3, midTopPanel.getHeight() - 85));
		Font font = new Font("黑体", Font.BOLD, 15);
		this.descLabel.setFont(font);
	//	this.descLabel.setText("你NND想死啊！");
		rightPanel.add(panelDec);

		Rectangle rect = this.errTable.getCellRect(
				this.errModel.getRowCount() - 1, 0, true);
		this.errTable.scrollRectToVisible(rect);

		this.timerTickThread = new TimerThread();
		this.timerTickThread.start();
		this.uiThread = new UIThread();
		this.uiThread.start();

	    golbalExamThread.start();
	    
	    
	    
	    this.iState = 1;
		
		/*******************************************************************/

	}

	private void initType() {
		DBHelper db = new DBHelper();
		try {
			db.conn();
			ResultSet rs = db.Query("select * from systemparm");
			if (rs != null) {

				try {
					while (rs.next()) {
						int id = rs.getInt("no2");
						String title = new String(rs.getString("txt3"));
						String desc = rs.getString("txt4");

						HashMap hm = new HashMap();
						hm.put("id", Integer.valueOf(id));
						hm.put("title", title);
						hm.put("desc", desc);
						this.listType.add(hm);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			db.close();
		}
	}

	public void handleMessage(Message msg) {
		if (!this.isRunning) {
			return;
		}

		try {
			switch (msg.what) {
			case -1:
				String timetick = msg.Bundle.get("timetick").toString();
				this.timerLabel.setText(timetick);
				break;
			case -4:
				HashMap errbundle = msg.Bundle;
				String title = errbundle.get("title").toString();
				int iscore = Integer
						.parseInt(errbundle.get("score").toString());
				String reason = errbundle.get("reason").toString();
				String code = errbundle.get("code").toString();
				int type = Integer.parseInt(errbundle.get("type").toString());
				addErrItem(title, iscore, reason, code, type);
				break;
			case 20:
				break;
			case 41:
				successFlag(13);
				break;
			case 1:
				successFlag(1);
				break;
			case 2:
				successFlag(2);
				this.iState = 0;
				break;
			case 4:
				successFlag(4);
				break;
			case 11:
				successFlag(11);
				break;
			case 5:
				successFlag(5);
				break;
			case 6:
				successFlag(6);
				break;
			case 17:
				successFlag(6);
				break;
			case 7:
				successFlag(7);
				break;
			case 8:
				successFlag(8);
				break;
			case 9:
				successFlag(9);
				break;
			case 3:
				successFlag(3);
				break;
			case 10:
				successFlag(10);
				break;
			case 12:
				successFlag(12);
				break;
			case 14:
				successFlag(14);
				break;
			case 15:
				successFlag(15);
				break;
			case 16:
				successFlag(16);
			case -3:
			case -2:
			case 0:
			case 13:
			case 18:
			case 19:
			case 21:
			case 22:
			case 23:
			case 24:
			case 25:
			case 26:
			case 27:
			case 28:
			case 29:
			case 30:
			case 31:
			case 32:
			case 33:
			case 34:
			case 35:
			case 36:
			case 37:
			case 38:
			case 39:
			case 40:
			}
		} catch (Exception localException) {
		}
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
		this.errModel.addRow(title, score, reason);
		try {
			this.errTable.updateUI();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Rectangle rect = this.errTable.getCellRect(
				this.errModel.getRowCount() - 1, 0, true);
		this.errTable.scrollRectToVisible(rect);
	}

	 public void dispose()
	  {
		if (this.prepareThread != null)
			this.prepareThread.runFlag = false;
		this.prepareThread = null;
		
		if (this.startThread != null)
			this.startThread.runFlag = false;
		this.startThread = null;

		if (this.onLineThread != null)
			this.onLineThread.runFlag = false;
		this.onLineThread = null;
		if (this.gearThread != null)
			this.gearThread.runFlag = false;
		this.gearThread = null;

		if (this.changeLaneThread != null)
			this.changeLaneThread.runFlag = false;
		this.changeLaneThread = null;

		if (this.stopThread != null)
			this.stopThread.runFlag = false;
		this.stopThread = null;

		if (this.crossingLineThread != null)
			this.crossingLineThread.runFlag = false;
		this.crossingLineThread = null;

		if (this.turnLeftThread != null)
			this.turnLeftThread.runFlag = false;
		this.turnLeftThread = null;

		if (this.turnRightThread != null)
			this.turnRightThread.runFlag = false;
		this.turnRightThread = null;

		if (this.pavementThread != null)
			this.pavementThread.runFlag = false;
		this.pavementThread = null;

		if (this.schoolThread != null)
			this.schoolThread.runFlag = false;
		this.schoolThread = null;

		if (this.busThread != null)
			this.busThread.runFlag = false;
		this.busThread = null;

		if (this.meetingThread != null)
			this.meetingThread.runFlag = false;
		this.meetingThread = null;

		if (this.overTakenThread != null)
			this.overTakenThread.runFlag = false;
		this.overTakenThread = null;

		if (this.turnAroundThread != null)
			this.turnAroundThread.runFlag = false;
		this.turnAroundThread = null;
		 
		 
		 
		 
	    if (this.timerTickThread != null)
	      this.timerTickThread.runFlag = false;
	    this.timerTickThread = null;
	    if (this.uiThread != null)
	      this.uiThread.runFlag = false;
	    this.uiThread = null;
	    if(this.golbalExamThread != null)
	    	this.golbalExamThread.runFlag = false;
	    this.golbalExamThread = null;
	    super.dispose();
	  }

	private void successFlag(int type) {
		clear();
	}

	public void startItem(int typeID)
	  {
	    Break();
	    switch (typeID) {
	    case 1:
	      prepareThread = new PrepareThread(this, PrepareThread.TRAINFLAG);
	      addModule(prepareThread);
	      prepareThread.start();
	      setFlagById(1);
	      break;
	    case 2:
	      startThread = new StartThread(this, StartThread.TRAINFLAG);
	      addModule(startThread);
	      startThread.start();
	      setFlagById(2);
	      break;
	    case 3:
	      onLineThread = new OnLineThread(this, OnLineThread.TRAINFLAG);
	      addModule(onLineThread);
	      onLineThread.start();
	      setFlagById(3);
	      break;
	    case 14:
	      gearThread = new GearThread(this, GearThread.TRAINFLAG);
	      addModule(gearThread);
	      gearThread.start();
	      setFlagById(14);
	      break;
	    case 4:
	      changeLaneThread = new ChangeLaneThread(this, ChangeLaneThread.TRAINFLAG);
	      addModule(changeLaneThread);
	      changeLaneThread.start();
	      setFlagById(4);
	      break;
	    case 11:
	      stopThread = new StopThread(this, StopThread.TRAINFLAG);
	      addModule(stopThread);
	      stopThread.start();
	      setFlagById(11);
	      break;
	    case 5:
	      crossingLineThread = new LuKouStraightThread(this, LuKouStraightThread.TRAINFLAG);
			addModule(crossingLineThread);
			crossingLineThread.start();
			setFlagById(5);
			break;
		case 15:
			turnLeftThread = new TurnLeftThread(this, TurnLeftThread.TRAINFLAG);
			addModule(turnLeftThread);
			turnLeftThread.start();
			setFlagById(15);
			break;
		case 16:
			turnRightThread = new TurnRightThread(this,
					TurnRightThread.TRAINFLAG);
			addModule(turnRightThread);
	      turnRightThread.start();
	      setFlagById(16);
	      break;
	    case 6:
	      pavementThread = new PavementThread(this, PavementThread.TRAINFLAG);
	      addModule(pavementThread);
	      pavementThread.isPlay = true;
	      pavementThread.start();
	      setFlagById(6);
	      break;
	    case 7:
	      schoolThread = new SchoolAreaThread(this, SchoolAreaThread.TRAINFLAG);
	      addModule(schoolThread);
	      schoolThread.start();
	      setFlagById(7);
	      break;
	    case 8:
	      busThread = new BusThread(this, BusThread.TRAINFLAG);
	      addModule(busThread);
	      busThread.start();
	      setFlagById(8);
	      break;
	    case 9:
	      meetingThread = new MeetingThread(this, MeetingThread.TRAINFLAG);
	      addModule(meetingThread);
	      meetingThread.start();
	      setFlagById(9);
	      break;
	    case 10:
	      overTakenThread = new OverTakenThread(this, OverTakenThread.TRAINFLAG);
	      addModule(overTakenThread);
	      overTakenThread.start();
	      setFlagById(10);
	      break;
	    case 12:
	      turnAroundThread = new TurnAroundThread(this, TurnAroundThread.TRAINFLAG);
	      addModule(turnAroundThread);
	      turnAroundThread.start();
	      setFlagById(12);
	      break;
	    case 13:
	    }
	  }


	
	private void setFlagById(int typeId)
	  {
	    try
	    {
	      for (int i = 0; i < this.listType.size(); i++) {
	        HashMap hm = (HashMap)this.listType.get(i);
	        int id = Integer.parseInt(hm.get("id").toString());
	        if (id == typeId) {
	          String title = hm.get("title").toString();
	          String desc = hm.get("desc").toString();
	          this.ItemTitleLabel.setText(title);
	          this.descLabel.setText(desc);
	          break;
	        }
	      }
	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }
	  }


	class UIThread extends Thread {
		public boolean runFlag = true;

		public UIThread() {
		}

		private void refreshState() {
			try {
				JudgeSignal carSignal = JudgeSignal.getInstance();
				if (carSignal.lamp_width)
					RoutineTrainingFrm.this.lab_lamp_width
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_lamp_width
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.lamp_near)
					RoutineTrainingFrm.this.lab_lamp_near
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_lamp_near
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.lamp_highbeam)
					RoutineTrainingFrm.this.lab_lamp_highbeam
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_lamp_highbeam
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.lamp_fog)
					RoutineTrainingFrm.this.lab_lamp_fog
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_lamp_fog
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.lamp_left)
					RoutineTrainingFrm.this.lab_lamp_left
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_lamp_left
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.lamp_right)
					RoutineTrainingFrm.this.lab_lamp_right
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_lamp_right
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.lamp_urgent)
					RoutineTrainingFrm.this.lab_lamp_urgent
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_lamp_urgent
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_footbrake)
					RoutineTrainingFrm.this.lab_lamp_brake
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_lamp_brake
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_deputybrake)
					RoutineTrainingFrm.this.lab_signal_rightbrake
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_signal_rightbrake
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_door)
					RoutineTrainingFrm.this.lab_signal_door
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_signal_door
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_seatbelt)
					RoutineTrainingFrm.this.lab_signal_life
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_signal_life
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_clutchpedal)
					RoutineTrainingFrm.this.lab_signal_clutchpedal
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_signal_clutchpedal
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_handbrake)
					RoutineTrainingFrm.this.lab_signal_handbrake
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_signal_handbrake
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_horn)
					RoutineTrainingFrm.this.lab_signal_horn
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_signal_horn
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_acc)
					RoutineTrainingFrm.this.lab_signal_acc
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_signal_acc
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_ignition)
					RoutineTrainingFrm.this.lab_signal_ignition
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_signal_ignition
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_frontbumper)
					RoutineTrainingFrm.this.lab_signal_carsidea
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_signal_carsidea
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_rearbumper)
					RoutineTrainingFrm.this.lab_signal_carsideb
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					RoutineTrainingFrm.this.lab_signal_carsideb
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				RoutineTrainingFrm.this.lab_signal_wheelangle
						.setText(carSignal.wheelangle + "");
				RoutineTrainingFrm.this.lab_signal_n.setText(carSignal.n
						+ "RPM");
				RoutineTrainingFrm.this.lab_signal_V
						.setText((int) (carSignal.gpsspeed) + "Km/h");
				RoutineTrainingFrm.this.lab_gps_lon.setText(new DecimalFormat(
						"#.0000").format(carSignal.lon));
				RoutineTrainingFrm.this.lab_gps_lat.setText(new DecimalFormat(
						"#.0000").format(carSignal.lat));
				RoutineTrainingFrm.this.lab_gps_angle
						.setText(carSignal.gpsangle + "");
				RoutineTrainingFrm.this.lab_signal_gear.setText(carSignal.gear
						+ "");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		public void run() {
		      while (this.runFlag)
		        try {
		          refreshState();
		          sleep(200L);
		        } catch (Exception ex) {
		          ex.printStackTrace();
		        }
		    }
	}

	class TimerThread extends Thread {
		public boolean runFlag = true;
		private int sleepTime = 200;

		public TimerThread() {
		}

		public void run() {
			while (this.runFlag) {
				try {
					//设置通用评判
					ModuleThread globel = (ModuleThread)RoutineTrainingFrm.this.golbalExamThread;
					Long globeltime =  10 - (System.currentTimeMillis() - globel.startTime )/ 1000L;
					if(globeltime > 0){
						
						RoutineTrainingFrm.this.globelTimer.setText( globeltime + "秒");
					}
					else
					{
						RoutineTrainingFrm.this.globelTimer.setText( "开始");
					}
					
					
					if (RoutineTrainingFrm.this.itemList.size() > 0) {
						ModuleThread it = (ModuleThread) RoutineTrainingFrm.this.itemList.get(0);
						int jsfs = it.jsfs;
						if (jsfs == 1)
							RoutineTrainingFrm.this.timerLabel
									.setText(it.iTimeOut /1000L
											- (System.currentTimeMillis() - it.startTime )
											/ 1000L + "秒");
						else
							RoutineTrainingFrm.this.timerLabel
									.setText((int) (it.dRangeOut - it.curRange)
											+ "米");
					} else {
						RoutineTrainingFrm.this.timerLabel.setText("");
					}
					sleep(this.sleepTime);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
