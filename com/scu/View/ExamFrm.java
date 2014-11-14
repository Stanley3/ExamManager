package com.scu.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import com.scu.Contral.*;
import com.scu.GlobelControl.ConfigManager;
import com.scu.GlobelControl.ImageIconSet;
import com.scu.GlobelControl.SignalIcon;
import com.scu.Model.CheckPoint;
import com.scu.Model.ExamWindow;
import com.scu.Model.Message;
import com.scu.Model.MyErrorTable;
import com.scu.Model.MyErrorTableModel;
import com.scu.Signal.JudgeSignal;
import com.scu.Thread.TimerTickThread;
import com.scu.Utils.DBHelper;
import com.scu.Utils.GetLocateUtil;
import com.scu.Utils.Tools;

/**
 * 模拟考试窗口
 * 
 * @author 孙晓雨
 *
 */
public class ExamFrm extends ExamWindow {

	private static final long serialVersionUID = 1L;
	private final int WIDTH = ConfigManager.WIDTH;
	private final int HEIGHT = ConfigManager.HEIGHT;
	private ImageIconSet allImage = ConfigManager.allImage;
	private SignalIcon signalIcon = ConfigManager.signalIcon;
	public int iState = -1;
	private int UI_INT = 200;

	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	JudgeSignal judge = JudgeSignal.getInstance();

	JLabel lb_km_1;
	JLabel lb_km_2;
	JLabel lb_km_3;
	JLabel lb_km_4;
	JLabel lb_km_5;
	JLabel lb_km_6;
	JLabel lb_km_7;
	JLabel lb_km_8;
	JLabel lb_km_9;
	JLabel lb_km_10;
	JLabel lb_km_11;
	JLabel lb_km_12;
	JLabel lb_km_13;
	JLabel lb_km_14;
	JLabel lb_km_15;
	JLabel lb_km_16;

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

	JButton ksks;
	JButton jsks;
	JButton dycj;

	JLabel lb_score;
	JLabel lb_startime;
	JLabel lb_timer;
	JLabel lb_speed;
	JLabel lb_lon;// 经度
	JLabel lb_lat;// 维度
	JLabel lb_ori;
	JLabel lb_dang;

	Vector errList;
	MyErrorTable errTable;
	MyErrorTableModel errModel;
	private int iScore = 100;

	private ArrayList<HashMap<String, Object>> listData = new ArrayList<HashMap<String, Object>>();

	private ArrayList<CheckPoint> listPoint = new ArrayList<CheckPoint>();
	Font font = new Font("黑体", Font.BOLD, 15);

	private TimerTickThread timerTickThread = null;
	private UIThread uiThread = null;
	TimerThread timeThread = new TimerThread();
	// PrepareThread prepareThread = null;
	public GolbalExamThread golbalExamThread = null;

	public ExamFrm(String winName) {
		this.init();
	}

	private void init() {
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
		// 将数据库中线路对应的点加载到 listPoint中
		initPoint();
		// System.out.println(this.listPoint);
		ConfigManager.EXAMSTARTTIME = this.timeFormat.format(Calendar
				.getInstance().getTime());
		JPanel headPanel = new JPanel();
		headPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		headPanel.setBounds(0, 0, WIDTH, 50);
		contentPanel.add(headPanel);
		JLabel examCharacter = new JLabel(new ImageIcon(
				"images\\mnks\\toptitle.jpg"));

		headPanel.add(examCharacter);

		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, headPanel.getHeight() + 1, WIDTH, 250);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		contentPanel.add(topPanel);

		JPanel titlePane = new JPanel();
		titlePane.setBackground(new Color(83, 105, 128));
		topPanel.add(titlePane);
		titlePane.setPreferredSize(new Dimension(titlePane.getParent()
				.getWidth(), 30));
		GridBagLayout gridBageLayout = new GridBagLayout();
		gridBageLayout.columnWidths = new int[] { 100, 60 };
		gridBageLayout.columnWeights = new double[] { 0.0D, 0.0D, 1.0D };
		gridBageLayout.rowWeights = new double[] { 0.0D };
		titlePane.setLayout(gridBageLayout);

		JLabel newLabel1 = new JLabel("扣分项目");
		newLabel1.setHorizontalAlignment(JLabel.CENTER);
		newLabel1.setForeground(Color.BLACK);
		newLabel1.setFont(font);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		titlePane.add(newLabel1, gbc_lblNewLabel);

		// 添加考试时候扣分和扣分原因表格
		JLabel newLabel2 = new JLabel("扣分");
		newLabel2.setHorizontalAlignment(JLabel.CENTER);
		newLabel2.setForeground(Color.BLACK);
		newLabel2.setFont(font);
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_9.gridx = 1;
		gbc_label_9.gridy = 0;
		titlePane.add(newLabel2, gbc_label_9);

		JLabel newLabel3 = new JLabel("扣分原因");
		newLabel3.setHorizontalAlignment(JLabel.CENTER);
		newLabel3.setForeground(Color.BLACK);
		newLabel3.setFont(font);
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.insets = new Insets(0, 0, 5, 0);
		gbc_label_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_10.gridx = 2;
		gbc_label_10.gridy = 0;
		titlePane.add(newLabel3, gbc_label_10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		topPanel.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(scrollPane.getParent()
				.getWidth(), topPanel.getHeight() - 30));

		this.errList = new Vector();
		this.errModel = new MyErrorTableModel(this.errList);
		this.errTable = new MyErrorTable(this.errModel);
		// this.errModel.addRow("准备错误", -10, "不按规定准备");

		this.errTable.hideHeader();
		this.errTable.setFont(font);
		this.errTable.setRowHeight(30);
		this.errTable.setOpaque(false);
		this.errTable.setColumnWidth(0, 100);
		this.errTable.setColumnWidth(1, 60);
		this.errTable.setColumnWidth(2, titlePane.getParent().getWidth() - 160);
		scrollPane.setViewportView(this.errTable);

		JPanel midPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(new ImageIcon("images\\mnks\\chengjititle.jpg")
						.getImage(), 0, 0, this.getWidth(), this.getHeight(),
						this);
			}
		};
		midPanel.setBounds(0, headPanel.getHeight() + topPanel.getHeight() + 1,
				WIDTH, 40);
		contentPanel.add(midPanel);
		// 指示信号面板
		midPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 13, 2));
		JLabel ksxmlb = new JLabel("考试项目列表");
		ksxmlb.setHorizontalAlignment(JLabel.CENTER);
		ksxmlb.setFont(new Font("黑体", 1, 18));
		ksxmlb.setForeground(Color.BLACK);
		midPanel.add(ksxmlb);

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
		yzxg = new JLabel(signalIcon.getYgdgIcon());
		ygdg = new JLabel(signalIcon.getYgdgIcon());
		zzxg = new JLabel(signalIcon.getZzxgIcon());

		midPanel.add(aqdbd);
		midPanel.add(fss);
		midPanel.add(hldg);
		midPanel.add(jss);
		midPanel.add(jgdg);
		midPanel.add(lbs);
		midPanel.add(qldg);
		midPanel.add(sss);
		midPanel.add(wdg);
		midPanel.add(yjdg);
		midPanel.add(yzxg);
		midPanel.add(ygdg);
		midPanel.add(zzxg);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(0, headPanel.getHeight() + topPanel.getHeight()
				+ midPanel.getHeight() + 1, WIDTH,
				HEIGHT - headPanel.getHeight() - topPanel.getHeight()
						- midPanel.getHeight() - 20);
		// bottomPanel.setBackground(new Color(83, 105, 128));
		contentPanel.add(bottomPanel);

		bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		JPanel left = new JPanel();
		left.setOpaque(false);
		left.setPreferredSize(new Dimension(450, bottomPanel.getHeight()));
		left.setBorder(BorderFactory.createEmptyBorder(10, 3, 20, 0));
		bottomPanel.add(left);
		left.setLayout(new GridLayout(6, 3, 0, 0));

		// JPanelUtils.addItem(left, lb_km_1, "images\\mnks\\sczb3.jpg");

		ImageIcon icon1 = new ImageIcon("images\\mnks\\sczb3.jpg");
		JPanel panel1 = new JPanel();
		panel1.setBorder(BorderFactory
				.createLineBorder(new Color(195, 201, 212)));
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
		panel1.setOpaque(false);
		left.add(panel1);
		lb_km_1 = new JLabel(icon1);// 上车准备
		panel1.add(lb_km_1);

		// JPanelUtils.addItem(left, lb_km_7, "images\\mnks\\lkzx3.jpg");
		ImageIcon icon2 = new ImageIcon("images\\mnks\\lkzx3.jpg");
		JPanel panel2 = new JPanel();
		panel2.setBorder(BorderFactory
				.createLineBorder(new Color(195, 201, 212)));
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
		panel2.setOpaque(false);
		left.add(panel2);
		lb_km_7 = new JLabel(icon2);// 路口直行
		panel2.add(lb_km_7);

		// JPanelUtils.addItem(left, lb_km_13, "images\\mnks\\hc3.jpg");
		ImageIcon icon3 = new ImageIcon("images\\mnks\\hc3.jpg");
		JPanel panel3 = new JPanel();
		panel3.setBorder(BorderFactory
				.createLineBorder(new Color(195, 201, 212)));
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
		panel3.setOpaque(false);
		left.add(panel3);
		lb_km_13 = new JLabel(icon3);// 会车
		panel3.add(lb_km_13);

		// JPanelUtils.addItem(left, lb_km_2, "images\\mnks\\qb3.jpg");
		ImageIcon icon4 = new ImageIcon("images\\mnks\\qb3.jpg");
		JPanel panel4 = new JPanel();
		panel4.setBorder(BorderFactory
				.createLineBorder(new Color(195, 201, 212)));
		panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
		panel4.setOpaque(false);
		left.add(panel4);
		lb_km_2 = new JLabel(icon4);// 起步
		panel4.add(lb_km_2);

		// JPanelUtils.addItem(left, lb_km_8, "images\\mnks\\lkzz3.jpg");
		ImageIcon icon5 = new ImageIcon("images\\mnks\\lkzz3.jpg");
		JPanel panel5 = new JPanel();
		panel5.setBorder(BorderFactory
				.createLineBorder(new Color(195, 201, 212)));
		panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
		panel5.setOpaque(false);
		left.add(panel5);
		lb_km_8 = new JLabel(icon5);// 路口直行
		panel5.add(lb_km_8);

		// JPanelUtils.addItem(left, lb_km_14, "images\\mnks\\cc3.jpg");
		ImageIcon icon6 = new ImageIcon("images\\mnks\\cc3.jpg");
		JPanel panel6 = new JPanel();
		panel6.setBorder(BorderFactory
				.createLineBorder(new Color(195, 201, 212)));
		panel6.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
		panel6.setOpaque(false);
		left.add(panel6);
		lb_km_14 = new JLabel(icon6);// 超车
		panel6.add(lb_km_14);

		// JPanelUtils.addItem(left, lb_km_3, "images\\mnks\\zxxs3.jpg");
		ImageIcon icon7 = new ImageIcon("images\\mnks\\zxxs3.jpg");
		JPanel panel7 = new JPanel();
		panel7.setBorder(BorderFactory
				.createLineBorder(new Color(195, 201, 212)));
		panel7.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
		panel7.setOpaque(false);
		left.add(panel7);
		lb_km_3 = new JLabel(icon7);// 直线行驶
		panel7.add(lb_km_3);

		// JPanelUtils.addItem(left, lb_km_9, "images\\mnks\\lkyz3.jpg");
		ImageIcon icon8 = new ImageIcon("images\\mnks\\lkyz3.jpg");
		JPanel panel8 = new JPanel();
		panel8.setBorder(BorderFactory
				.createLineBorder(new Color(195, 201, 212)));
		panel8.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
		panel8.setOpaque(false);
		left.add(panel8);
		lb_km_9 = new JLabel(icon8);// 会车
		panel8.add(lb_km_9);

		// JPanelUtils.addItem(left, lb_km_15, "images\\mnks\\dt3.jpg");
		ImageIcon icon9 = new ImageIcon("images\\mnks\\dt3.jpg");
		JPanel panel9 = new JPanel();
		panel9.setBorder(BorderFactory
				.createLineBorder(new Color(195, 201, 212)));
		panel9.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
		panel9.setOpaque(false);
		left.add(panel9);
		lb_km_15 = new JLabel(icon9);// 掉头
		panel9.add(lb_km_15);

		// JPanelUtils.addItem(left, lb_km_4, "images\\mnks\\jjdw3.jpg");
		ImageIcon icon10 = new ImageIcon("images\\mnks\\jjdw3.jpg");
		JPanel panel10 = new JPanel();
		panel10.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		panel10.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
		panel10.setOpaque(false);
		left.add(panel10);
		lb_km_4 = new JLabel(icon10);//
		panel10.add(lb_km_4);

		// JPanelUtils.addItem(left, lb_km_10, "images\\mnks\\rxhd3.jpg");
		ImageIcon icon11 = new ImageIcon("images\\mnks\\rxhd3.jpg");
		JPanel panel11 = new JPanel();
		panel11.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		panel11.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
		panel11.setOpaque(false);
		left.add(panel11);
		lb_km_10 = new JLabel(icon11);//
		panel11.add(lb_km_10);
		// JPanelUtils.addItem(left, lb_km_16, "images\\mnks\\yjxs3.jpg");

		// JPanelUtils.addItem(left, lb_km_5, "images\\mnks\\bgcd3.jpg");
		ImageIcon icon12 = new ImageIcon("images\\mnks\\bgcd3.jpg");
		JPanel panel12 = new JPanel();
		panel12.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		panel12.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
		panel12.setOpaque(false);
		left.add(panel12);
		lb_km_5 = new JLabel(icon12);//
		panel12.add(lb_km_5);

		// JPanelUtils.addItem(left, lb_km_11, "images\\mnks\\xxqy3.jpg");
		ImageIcon icon13 = new ImageIcon("images\\mnks\\xxqy3.jpg");
		JPanel panel13 = new JPanel();
		panel13.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		panel13.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
		panel13.setOpaque(false);
		left.add(panel13);
		lb_km_11 = new JLabel(icon13);//
		panel13.add(lb_km_11);

		// JPanelUtils.addItem(left, lb_km_6, "images\\mnks\\kbtc3.jpg");
		ImageIcon icon14 = new ImageIcon("images\\mnks\\kbtc3.jpg");
		JPanel panel14 = new JPanel();
		panel14.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		panel14.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
		panel14.setOpaque(false);
		left.add(panel14);
		lb_km_6 = new JLabel(icon14);//
		panel14.add(lb_km_6);

		// JPanelUtils.addItem(left, lb_km_12, "images\\mnks\\ggcz3.jpg");
		ImageIcon icon15 = new ImageIcon("images\\mnks\\ggcz3.jpg");
		JPanel panel15 = new JPanel();
		panel15.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		panel15.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));
		panel15.setOpaque(false);
		left.add(panel15);
		lb_km_12 = new JLabel(icon15);//
		panel15.add(lb_km_12);

		JPanel panel16 = new JPanel();
		panel16.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		FlowLayout flowLayout_14 = (FlowLayout) panel16.getLayout();
		flowLayout_14.setAlignment(0);
		flowLayout_14.setHgap(10);
		panel16.setOpaque(false);
		left.add(panel16);

		this.lb_km_16 = new JLabel("夜间行驶");
		this.lb_km_16.setHorizontalAlignment(2);
		this.lb_km_16.setForeground(new Color(83, 95, 252));
		this.lb_km_16.setFont(font);
		panel16.add(this.lb_km_16);

		JPanel panel17 = new JPanel();
		panel17.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		FlowLayout flowLayout_17 = (FlowLayout) panel17.getLayout();
		flowLayout_17.setHgap(25);
		panel17.setOpaque(false);
		left.add(panel17);

		JPanel panel_18 = new JPanel();
		panel_18.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		FlowLayout flowLayout_20 = (FlowLayout) panel_18.getLayout();
		flowLayout_20.setHgap(25);
		panel_18.setOpaque(false);
		left.add(panel_18);

		// 分割右下角界面
		JPanel right = new JPanel();
		// right.setOpaque(false);
		right.setPreferredSize(new Dimension(350, bottomPanel.getHeight()));
		// right.setBackground(Color.RED);
		bottomPanel.add(right);

		right.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		JPanel rightTop = new JPanel();
		rightTop.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		rightTop.setLayout(new GridLayout(1, 3, 0, 0));
		rightTop.setPreferredSize(new Dimension(350, 110));
		right.add(rightTop);
		ksks = new JButton(new ImageIcon("images\\mnks\\star.jpg"));
		ksks.setOpaque(false);
		ksks.setBorderPainted(false);
		// 设置 contentAreaFilled 属性。如果该属性为
		// true，则按钮将绘制内容区域。如果希望有一个透明的按钮，比如只是一个图标的按钮，那么应该将此属性设置为 false。
		ksks.setContentAreaFilled(false);

		jsks = new JButton(new ImageIcon("images\\mnks\\exit.jpg"));
		jsks.setOpaque(false);
		jsks.setBorderPainted(false);
		jsks.setContentAreaFilled(false);

		dycj = new JButton(new ImageIcon("images\\mnks\\print.jpg"));
		dycj.setOpaque(false);
		dycj.setBorderPainted(false);
		dycj.setContentAreaFilled(false);

		rightTop.add(ksks);
		rightTop.add(jsks);
		rightTop.add(dycj);

		ksks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 20; i++){
					ConfigManager.ITEMSTATE[i] = 0;
				}
				if(ExamFrm.this.timerTickThread != null){
					ExamFrm.this.timerTickThread.runFlag = false;
					ExamFrm.this.timerTickThread = null;
				}
				ExamFrm.this.timerTickThread = new TimerTickThread(ExamFrm.this);
				ExamFrm.this.timerTickThread.start();
				ExamFrm.this.startSimulate();
			}

		});
		jsks.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ExamFrm.this.golbalExamThread != null)
					ExamFrm.this.golbalExamThread.runFlag = false;
				ExamFrm.this.golbalExamThread = null;

				if (ExamFrm.this.timerTickThread != null)
					ExamFrm.this.timerTickThread.runFlag = false;
				ExamFrm.this.timerTickThread = null;

				ExamFrm.this.dispose();
			}
		});
		JPanel rightBottom = new JPanel();
		rightBottom.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));
		rightBottom.setLayout(new GridLayout(1, 3, 0, 0));
		rightBottom.setPreferredSize(new Dimension(350, 150));
		right.add(rightBottom);

		rightBottom.setLayout(new GridLayout(4, 2, 0, 0));

		JPanel panel = new JPanel();
		// panel.setOpaque(false);
		rightBottom.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel label = new JLabel("得分：");
		label.setHorizontalAlignment(0);
		label.setForeground(Color.BLACK);
		label.setFont(font);
		panel.add(label);

		this.lb_score = new JLabel("100");
		this.lb_score.setHorizontalAlignment(2);
		this.lb_score.setForeground(Color.BLACK);
		this.lb_score.setFont(font);
		panel.add(this.lb_score);

		JPanel panel_lon = new JPanel();
		// panel.setOpaque(false);
		rightBottom.add(panel_lon);
		panel_lon.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lon = new JLabel("经度：");
		lon.setHorizontalAlignment(0);
		lon.setForeground(Color.BLACK);
		lon.setFont(font);
		panel_lon.add(lon);

		this.lb_lon = new JLabel("1144.1203");
		this.lb_lon.setHorizontalAlignment(2);
		this.lb_lon.setForeground(Color.BLACK);
		this.lb_lon.setFont(font);
		panel_lon.add(this.lb_lon);

		JPanel panel_1 = new JPanel();
		// panel_1.setOpaque(false);
		rightBottom.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel label_1 = new JLabel("开始：");
		label_1.setHorizontalAlignment(4);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(font);
		panel_1.add(label_1);

		this.lb_startime = new JLabel("");
		this.lb_startime.setHorizontalAlignment(2);
		this.lb_startime.setForeground(Color.BLACK);
		this.lb_startime.setFont(font);
		panel_1.add(this.lb_startime);

		JPanel panel_lat = new JPanel();
		// panel.setOpaque(false);
		rightBottom.add(panel_lat);
		panel_lat.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lat = new JLabel("纬度：");
		lat.setHorizontalAlignment(0);
		lat.setForeground(Color.BLACK);
		lat.setFont(font);
		panel_lat.add(lat);

		this.lb_lat = new JLabel("123.1334");
		this.lb_lat.setHorizontalAlignment(2);
		this.lb_lat.setForeground(Color.BLACK);
		this.lb_lat.setFont(font);
		panel_lat.add(this.lb_lat);

		JPanel panel_2 = new JPanel();
		rightBottom.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel label_3 = new JLabel("用时：");
		label_3.setHorizontalAlignment(4);
		label_3.setForeground(Color.BLACK);
		label_3.setFont(font);
		panel_2.add(label_3);

		this.lb_timer = new JLabel("00:00:00");
		this.lb_timer.setHorizontalAlignment(2);
		this.lb_timer.setForeground(Color.BLACK);
		this.lb_timer.setFont(font);
		panel_2.add(this.lb_timer);

		JPanel panel_ori = new JPanel();
		rightBottom.add(panel_ori);
		panel_ori.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel ori = new JLabel("方向：");
		ori.setHorizontalAlignment(0);
		ori.setForeground(Color.BLACK);
		ori.setFont(font);
		panel_ori.add(ori);

		this.lb_ori = new JLabel("0.0");
		this.lb_ori.setHorizontalAlignment(2);
		this.lb_ori.setForeground(Color.BLACK);
		this.lb_ori.setFont(font);
		panel_ori.add(this.lb_ori);

		JPanel panel_3 = new JPanel();
		rightBottom.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel label_5 = new JLabel("车速：");
		label_5.setHorizontalAlignment(4);
		label_5.setForeground(Color.BLACK);
		label_5.setFont(font);
		panel_3.add(label_5);

		this.lb_speed = new JLabel("50Km/h");
		this.lb_speed.setHorizontalAlignment(2);
		this.lb_speed.setForeground(Color.BLACK);
		this.lb_speed.setFont(font);
		panel_3.add(this.lb_speed);

		JPanel panel_dang = new JPanel();
		rightBottom.add(panel_dang);
		panel_dang.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel dang = new JLabel("档位：");
		dang.setHorizontalAlignment(0);
		dang.setForeground(Color.BLACK);
		dang.setFont(font);
		panel_dang.add(dang);

		this.lb_dang = new JLabel("5档");
		this.lb_dang.setHorizontalAlignment(2);
		this.lb_dang.setForeground(Color.BLACK);
		this.lb_dang.setFont(font);
		panel_dang.add(this.lb_dang);

		// ConfigManager.ITEMSTATE = new int[20];

		// this.prepareThread = new PrepareThread(this, PrepareThread.EXAMFLAG);
		// addModule(this.prepareThread);
		// setFlagById(1, 1);
		// this.prepareThread.start();
		this.timeThread.start();

		this.uiThread = new UIThread();
		this.uiThread.start();

		this.setVisible(true);

	}

	private void startSimulate() {
		GpsMonitor gpsMonitor = new GpsMonitor();
		gpsMonitor.start();

		// int examId = getRandomNight();
		// NightLightThread nightLightThread = new NightLightThread(this,
		// NightLightThread.EXAMFLAG);
		// if (ConfigManager.CARPARM_NIGHT_AUTO)
		// nightLightThread.setExamId(examId);
		// else
		// nightLightThread.setExamId(0);
		// addModule(nightLightThread);
		// setFlagById(13, 1);
		// nightLightThread.start();

		this.golbalExamThread = new GolbalExamThread(this,
				GolbalExamThread.EXAMFLAG);
		addModule(this.golbalExamThread);
		this.golbalExamThread.start();
		this.iState = 2;
	}

	public void dispose() {
		if (this.timeThread != null)
			this.timeThread.runFlag = false;
		this.timeThread = null;

		if (this.uiThread != null)
			this.uiThread.runFlag = false;
		this.uiThread = null;

		if (this.golbalExamThread != null)
			this.golbalExamThread.runFlag = false;
		this.golbalExamThread = null;
		super.dispose();
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
						int id = rs.getInt("lamps_no");
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

	public void initPoint() {
		DBHelper db = new DBHelper();
		try {
			db.conn();
			ResultSet rs = db
					.Query("select *,(select txt3 from systemparm where no1=6 and no2=map_point.point_type) as typename from map_point where line_id="
							+ ConfigManager.LINE_ID + " order by point_no");
			if (rs != null)
				while (rs.next()) {
					int point_no = rs.getInt("point_no");
					// System.out.println("point_no-------------------->" +
					// point_no);
					double lon = rs.getDouble("lon");
					// System.out.println("lon-------------------->" + lon);
					double lat = rs.getDouble("lat");
					// System.out.println("lat-------------------->" + lat);
					int angle = rs.getInt("angle");
					// System.out.println("angle-------------------->" + angle);
					int point_type = rs.getInt("point_type");
					System.out.println("point_type-------------------->"
							+ point_type);
					String typename = rs.getString("typename");
					System.out.println("typename-------------------->"
							+ typename);
					this.listPoint.add(new CheckPoint(typename, lon, lat,
							point_no, angle, point_type));
				}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			db.close();
		}

	}

	public void handleMessage(Message msg) {
		if (!this.isRunning)
			return;
		try {
			switch (msg.what) {
			case -1:
				String timetick = msg.Bundle.get("timetick").toString();
				this.lb_timer.setText(timetick);
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
				// StaticVariable.EXAMENDTIME =
				// this.sdf.format(Calendar.getInstance().getTime());
				// FinishWindow finishWindow = new FinishWindow();
				// finishWindow.listData = this.listData;
				// finishWindow.setVisible(true);
				dispose();
				break;
			case 41:
				successFlag(13);
				StartThread startThread = new StartThread(this,
						StartThread.EXAMFLAG);
				addModule(startThread);
				startThread.start();
				setFlagById(2, 1);
				this.iState = 3;
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

	private void successFlag(int type) {
		clear();
		setFlagById(type, 3);
	}

	private void setFlagById(int typeId, int flag) {
		if (typeId > ConfigManager.ITEMSTATE.length)
			return;
		try {
			int curflag = ConfigManager.ITEMSTATE[(typeId - 1)];
			if (curflag == 2)
				return;
			ConfigManager.ITEMSTATE[(typeId - 1)] = flag;
		} catch (Exception ex) {
			ex.printStackTrace();
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
		if (type == 41)
			type = 13;
		if (type == 17)
			type = 6;
		setFlagById(type, 2);

		this.iScore += score;
		if (this.iScore < 0)
			this.iScore = 0;
	}

	public void startItem(int typeID) {
		switch (typeID) {
		case 1:
			PrepareThread prepareThread = new PrepareThread(this,
					PrepareThread.EXAMFLAG);
			addModule(prepareThread);
			prepareThread.start();
			setFlagById(1, 1);
			break;
		case 2:
			StartThread startThread = new StartThread(this,
					StartThread.EXAMFLAG);
			addModule(startThread);
			startThread.start();
			setFlagById(2, 1);
			break;
		case 3:
			OnLineThread onLineThread = new OnLineThread(this,
					OnLineThread.EXAMFLAG);
			addModule(onLineThread);
			onLineThread.start();
			setFlagById(3, 1);
			break;
		case 14:
			GearThread gearThread = new GearThread(this, GearThread.EXAMFLAG);
			addModule(gearThread);
			gearThread.start();
			setFlagById(14, 1);
			break;
		case 4:
			ChangeLaneThread changeLaneThread = new ChangeLaneThread(this,
					ChangeLaneThread.EXAMFLAG);
			addModule(changeLaneThread);
			changeLaneThread.start();
			setFlagById(4, 1);
			break;
		case 11:
			StopThread stopThread = new StopThread(this, StopThread.EXAMFLAG);
			addModule(stopThread);
			stopThread.start();
			setFlagById(11, 1);
			break;
		case 5:
			LuKouStraightThread crossingLineThread = new LuKouStraightThread(
					this, LuKouStraightThread.EXAMFLAG);
			addModule(crossingLineThread);
			crossingLineThread.start();
			setFlagById(5, 1);
			break;
		case 15:
			TurnLeftThread turnLeftThread = new TurnLeftThread(this,
					TurnLeftThread.EXAMFLAG);
			addModule(turnLeftThread);
			turnLeftThread.start();
			setFlagById(15, 1);
			break;
		case 16:
			TurnRightThread turnRightThread = new TurnRightThread(this,
					TurnRightThread.EXAMFLAG);
			addModule(turnRightThread);
			turnRightThread.start();
			setFlagById(16, 1);
			break;
		case 6:
			PavementThread pavementThread = new PavementThread(this,
					PavementThread.EXAMFLAG);
			addModule(pavementThread);
			pavementThread.start();
			setFlagById(6, 1);
			break;
		case 7:
			SchoolAreaThread schoolThread = new SchoolAreaThread(this,
					SchoolAreaThread.EXAMFLAG);
			addModule(schoolThread);
			schoolThread.start();
			setFlagById(7, 1);
			break;
		case 8:
			BusThread busThread = new BusThread(this, BusThread.EXAMFLAG);
			addModule(busThread);
			busThread.start();
			setFlagById(8, 1);
			break;
		case 9:
			MeetingThread meetingThread = new MeetingThread(this,
					MeetingThread.EXAMFLAG);
			addModule(meetingThread);
			meetingThread.start();
			setFlagById(9, 1);
			break;
		case 10:
			OverTakenThread overTakenThread = new OverTakenThread(this,
					OverTakenThread.EXAMFLAG);
			addModule(overTakenThread);
			overTakenThread.start();
			setFlagById(10, 1);
			break;
		case 12:
			TurnAroundThread turnAroundThread = new TurnAroundThread(this,
					TurnAroundThread.EXAMFLAG);
			addModule(turnAroundThread);
			turnAroundThread.start();
			setFlagById(12, 1);
			break;
		case 13:
		}

	}

	class GpsMonitor extends Thread {
		public boolean runFlag = true;

		public GpsMonitor() {
		}

		public void run() {
			while (this.runFlag)
				try {
					moniterPoint(ExamFrm.this.judge);
					sleep(ExamFrm.this.UI_INT);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}

		private void moniterPoint(JudgeSignal carSignal) {
			double lat = carSignal.lat;
			double lon = carSignal.lon;
			int angle = (int) carSignal.gpsangle;

			for (int i = 0; i < ExamFrm.this.listPoint.size(); i++)
				try {
					CheckPoint checkPoint = (CheckPoint) ExamFrm.this.listPoint
							.get(i);

					if (checkPoint.getIsChecking()) {
						continue;
					}
					double distince = Tools.getDistince(lat, lon,
							checkPoint.getLat(), checkPoint.getLon());

					switch (checkPoint.getPointType()) {
					case 1:
						if ((distince > PrepareThread.RANGETIGGER)
								|| (Math.abs(Tools.getAngleByGpsAngle(angle,
										checkPoint.getAngle())) > 20))
							continue;
						checkPoint.setIsChecking(true);
						PrepareThread prepareThread = new PrepareThread(
								ExamFrm.this, PrepareThread.EXAMFLAG);
						ExamFrm.this.addModule(prepareThread);
						prepareThread.start();
						ExamFrm.this.setFlagById(1, 1);

						break;
					case 2:
						if ((distince > StartThread.RANGETIGGER)
								|| (Math.abs(Tools.getAngleByGpsAngle(angle,
										checkPoint.getAngle())) > 20))
							continue;
						checkPoint.setIsChecking(true);
						StartThread startThread = new StartThread(ExamFrm.this,
								StartThread.EXAMFLAG);
						ExamFrm.this.addModule(startThread);
						startThread.start();
						ExamFrm.this.setFlagById(2, 1);

						break;
					case 3:
						if ((distince > OnLineThread.RANGETIGGER)
								|| (Math.abs(Tools.getAngleByGpsAngle(angle,
										checkPoint.getAngle())) > 20))
							continue;
						checkPoint.setIsChecking(true);
						OnLineThread onLineThread = new OnLineThread(
								ExamFrm.this, OnLineThread.EXAMFLAG);
						ExamFrm.this.addModule(onLineThread);
						onLineThread.start();
						ExamFrm.this.setFlagById(3, 1);

						break;
					case 4:
						if ((distince > ChangeLaneThread.RANGETIGGER)
								|| (Math.abs(Tools.getAngleByGpsAngle(angle,
										checkPoint.getAngle())) > 20))
							continue;
						checkPoint.setIsChecking(true);
						ChangeLaneThread changelaneThread = new ChangeLaneThread(
								ExamFrm.this, ChangeLaneThread.EXAMFLAG);
						ExamFrm.this.addModule(changelaneThread);
						changelaneThread.start();
						ExamFrm.this.setFlagById(4, 1);

						break;
					case 5:
						if ((distince > LuKouStraightThread.RANGETIGGER)
								|| (Math.abs(Tools.getAngleByGpsAngle(angle,
										checkPoint.getAngle())) > 20))
							continue;
						checkPoint.setIsChecking(true);
						LuKouStraightThread crossingLineThread = new LuKouStraightThread(
								ExamFrm.this, LuKouStraightThread.EXAMFLAG);
						ExamFrm.this.addModule(crossingLineThread);
						crossingLineThread.start();
						ExamFrm.this.setFlagById(5, 1);

						break;
					case 6:
						if ((distince > PavementThread.RANGETIGGER)
								|| (Math.abs(Tools.getAngleByGpsAngle(angle,
										checkPoint.getAngle())) > 20))
							continue;
						checkPoint.setIsChecking(true);
						PavementThread pavementThread = new PavementThread(
								ExamFrm.this, PavementThread.EXAMFLAG);
						pavementThread.isPlay = true;
						ExamFrm.this.addModule(pavementThread);
						pavementThread.start();
						ExamFrm.this.setFlagById(6, 1);

						break;
					case 7:
						if ((distince > SchoolAreaThread.RANGETIGGER)
								|| (Math.abs(Tools.getAngleByGpsAngle(angle,
										checkPoint.getAngle())) > 20))
							continue;
						checkPoint.setIsChecking(true);
						SchoolAreaThread schoolThread = new SchoolAreaThread(
								ExamFrm.this, SchoolAreaThread.EXAMFLAG);
						ExamFrm.this.addModule(schoolThread);
						schoolThread.start();
						ExamFrm.this.setFlagById(7, 1);

						break;
					case 8:
						if ((distince > BusThread.RANGETIGGER)
								|| (Math.abs(Tools.getAngleByGpsAngle(angle,
										checkPoint.getAngle())) > 20))
							continue;
						checkPoint.setIsChecking(true);
						BusThread busThread = new BusThread(ExamFrm.this,
								BusThread.EXAMFLAG);
						ExamFrm.this.addModule(busThread);
						busThread.start();
						ExamFrm.this.setFlagById(8, 1);

						break;
					case 9:
						if ((distince > MeetingThread.RANGETIGGER)
								|| (Math.abs(Tools.getAngleByGpsAngle(angle,
										checkPoint.getAngle())) > 20))
							continue;
						checkPoint.setIsChecking(true);
						MeetingThread meetingThread = new MeetingThread(
								ExamFrm.this, MeetingThread.EXAMFLAG);
						ExamFrm.this.addModule(meetingThread);
						meetingThread.start();
						ExamFrm.this.setFlagById(9, 1);

						break;
					case 10:
						if ((distince > OverTakenThread.RANGETIGGER)
								|| (Math.abs(Tools.getAngleByGpsAngle(angle,
										checkPoint.getAngle())) > 20))
							continue;
						checkPoint.setIsChecking(true);
						OverTakenThread overtakenThread = new OverTakenThread(
								ExamFrm.this, OverTakenThread.EXAMFLAG);
						ExamFrm.this.addModule(overtakenThread);
						overtakenThread.start();
						ExamFrm.this.setFlagById(10, 1);

						break;
					case 11:
						if ((distince > StopThread.RANGETIGGER)
								|| (Math.abs(Tools.getAngleByGpsAngle(angle,
										checkPoint.getAngle())) > 20))
							continue;
						checkPoint.setIsChecking(true);
						StopThread stopThread = new StopThread(ExamFrm.this,
								StopThread.EXAMFLAG);
						ExamFrm.this.addModule(stopThread);
						stopThread.start();
						ExamFrm.this.setFlagById(11, 1);

						break;
					case 12:
						if ((distince > TurnAroundThread.RANGETIGGER)
								|| (Math.abs(Tools.getAngleByGpsAngle(angle,
										checkPoint.getAngle())) > 20))
							continue;
						checkPoint.setIsChecking(true);
						TurnAroundThread turnAroundThread = new TurnAroundThread(
								ExamFrm.this, TurnAroundThread.EXAMFLAG);
						ExamFrm.this.addModule(turnAroundThread);
						turnAroundThread.start();
						ExamFrm.this.setFlagById(12, 1);

						break;
					case 13:
						break;
					case 14:
						if ((distince > GearThread.RANGETIGGER)
								|| (Math.abs(Tools.getAngleByGpsAngle(angle,
										checkPoint.getAngle())) > 20))
							continue;
						checkPoint.setIsChecking(true);
						GearThread gearThread = new GearThread(ExamFrm.this,
								GearThread.EXAMFLAG);
						ExamFrm.this.addModule(gearThread);
						gearThread.start();
						ExamFrm.this.setFlagById(14, 1);

						break;
					case 15:
						if ((distince > TurnLeftThread.RANGETIGGER)
								|| (Math.abs(Tools.getAngleByGpsAngle(angle,
										checkPoint.getAngle())) > 20))
							continue;
						checkPoint.setIsChecking(true);
						TurnLeftThread turnLeftThread = new TurnLeftThread(
								ExamFrm.this, TurnLeftThread.EXAMFLAG);
						ExamFrm.this.addModule(turnLeftThread);
						turnLeftThread.start();
						ExamFrm.this.setFlagById(15, 1);

						break;
					case 16:
						if ((distince > TurnRightThread.RANGETIGGER)
								|| (Math.abs(Tools.getAngleByGpsAngle(angle,
										checkPoint.getAngle())) > 20))
							continue;
						checkPoint.setIsChecking(true);
						TurnRightThread turnRightThread = new TurnRightThread(
								ExamFrm.this, TurnRightThread.EXAMFLAG);
						ExamFrm.this.addModule(turnRightThread);
						turnRightThread.start();
						ExamFrm.this.setFlagById(16, 1);
						break;
					}
				} catch (Exception localException) {
				}
		}
	}

	class UIThread extends Thread {
		public boolean runFlag = true;

		public UIThread() {
		}

		private void refreshTitle(JudgeSignal carSignal) {
			try {
				ExamFrm.this.lb_speed.setText(carSignal.gpsspeed + "Km/h");
				ExamFrm.this.lb_lat.setText(new DecimalFormat("#.0000").format(carSignal.lat));
				ExamFrm.this.lb_lon.setText(new DecimalFormat("#.0000").format(carSignal.lon));
				ExamFrm.this.lb_score.setText(ExamFrm.this.iScore + "");
				ExamFrm.this.lb_ori.setText(carSignal.gpsangle + "度");
				ExamFrm.this.lb_dang.setText(carSignal.gear + "档");

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		private void refreshItem() {
			try {
				for (int i = 0; i < ConfigManager.ITEMSTATE.length; i++) {
					int state = ConfigManager.ITEMSTATE[i];
					// System.out.println("state--------------------->" + state );
					switch (i + 1) {
					case 1:
						// System.out.println(ConfigManager.simuteIcon.getSczb1Icon());
						switch (state) {
						case 0:
							ExamFrm.this.lb_km_1
									.setIcon(ConfigManager.simuteIcon
											.getSczb0Icon());break;
						case 1:
							ExamFrm.this.lb_km_1
									.setIcon(ConfigManager.simuteIcon
											.getSczb1Icon());break;
						case 2:
							ExamFrm.this.lb_km_1
									.setIcon(ConfigManager.simuteIcon
											.getSczb2Icon());break;
						case 3:
							ExamFrm.this.lb_km_1
									.setIcon(ConfigManager.simuteIcon
											.getSczb3Icon());break;
						}
						break;
					case 2:
						switch (state) {
						case 0:
							ExamFrm.this.lb_km_2
									.setIcon(ConfigManager.simuteIcon
											.getQb0Icon());break;
						case 1:
							ExamFrm.this.lb_km_2
									.setIcon(ConfigManager.simuteIcon
											.getQb1Icon());break;
						case 2:
							ExamFrm.this.lb_km_2
									.setIcon(ConfigManager.simuteIcon
											.getQb2Icon());break;
						case 3:
							ExamFrm.this.lb_km_2
									.setIcon(ConfigManager.simuteIcon
											.getQb3Icon());break;
						}
						break;
					case 3:
						switch (state) {
						case 0:
							ExamFrm.this.lb_km_3
									.setIcon(ConfigManager.simuteIcon
											.getZxxs0Icon());break;
						case 1:
							ExamFrm.this.lb_km_3
									.setIcon(ConfigManager.simuteIcon
											.getZxxs1Icon());break;
						case 2:
							ExamFrm.this.lb_km_3
									.setIcon(ConfigManager.simuteIcon
											.getZxxs2Icon());break;
						case 3:
							ExamFrm.this.lb_km_3
									.setIcon(ConfigManager.simuteIcon
											.getZxxs3Icon());break;
						}
						break;
					case 14:
						switch (state) {
						case 0:
							ExamFrm.this.lb_km_4
									.setIcon(ConfigManager.simuteIcon
											.getCc0Icon());break;
						case 1:
							ExamFrm.this.lb_km_4
									.setIcon(ConfigManager.simuteIcon
											.getCc1Icon());break;
						case 2:
							ExamFrm.this.lb_km_4
									.setIcon(ConfigManager.simuteIcon
											.getCc2Icon());break;
						case 3:
							ExamFrm.this.lb_km_4
									.setIcon(ConfigManager.simuteIcon
											.getCc3Icon());break;
						}
						break;
					case 4:
						switch (state) {
						case 0:
							ExamFrm.this.lb_km_4
									.setIcon(ConfigManager.simuteIcon
											.getJjdw0Icon());break;
						case 1:
							ExamFrm.this.lb_km_4
									.setIcon(ConfigManager.simuteIcon
											.getJjdw1Icon());break;
						case 2:
							ExamFrm.this.lb_km_4
									.setIcon(ConfigManager.simuteIcon
											.getJjdw2Icon());break;
						case 3:
							ExamFrm.this.lb_km_4
									.setIcon(ConfigManager.simuteIcon
											.getJjdw3Icon());break;
						}
						break;
					case 11:
						switch (state) {
						case 0:
							ExamFrm.this.lb_km_11
									.setIcon(ConfigManager.simuteIcon
											.getXxqy0Icon());break;
						case 1:
							ExamFrm.this.lb_km_11
									.setIcon(ConfigManager.simuteIcon
											.getXxqy1Icon());break;
						case 2:
							ExamFrm.this.lb_km_11
									.setIcon(ConfigManager.simuteIcon
											.getXxqy2Icon());break;
						case 3:
							ExamFrm.this.lb_km_11
									.setIcon(ConfigManager.simuteIcon
											.getXxqy3Icon());break;
						}
						break;
					case 5:
						switch (state) {
						case 0:
							ExamFrm.this.lb_km_5
									.setIcon(ConfigManager.simuteIcon
											.getBgcd0Icon());break;
						case 1:
							ExamFrm.this.lb_km_5
									.setIcon(ConfigManager.simuteIcon
											.getBgcd1Icon());break;
						case 2:
							ExamFrm.this.lb_km_5
									.setIcon(ConfigManager.simuteIcon
											.getBgcd2Icon());break;
						case 3:
							ExamFrm.this.lb_km_5
									.setIcon(ConfigManager.simuteIcon
											.getBgcd3Icon());break;
						}
						break;
					case 15:
						switch (state) {
						case 0:
							ExamFrm.this.lb_km_15
									.setIcon(ConfigManager.simuteIcon
											.getDt0Icon());break;
						case 1:
							ExamFrm.this.lb_km_15
									.setIcon(ConfigManager.simuteIcon
											.getDt1Icon());break;
						case 2:
							ExamFrm.this.lb_km_15
									.setIcon(ConfigManager.simuteIcon
											.getDt2Icon());break;
						case 3:
							ExamFrm.this.lb_km_15
									.setIcon(ConfigManager.simuteIcon
											.getDt3Icon());break;
						}
						break;
					case 16:
						// switch(state){
						// case
						// 0:ExamFrm.this.lb_km_16.setIcon(ConfigManager.simuteIcon.getQb0Icon());
						// case
						// 1:ExamFrm.this.lb_km_16.setIcon(ConfigManager.simuteIcon.getQb1Icon());
						// case
						// 2:ExamFrm.this.lb_km_16.setIcon(ConfigManager.simuteIcon.getQb2Icon());
						// case
						// 3:ExamFrm.this.lb_km_16.setIcon(ConfigManager.simuteIcon.getQb3Icon());
						// }
						break;
					case 6:
						switch (state) {
						case 0:
							ExamFrm.this.lb_km_6
									.setIcon(ConfigManager.simuteIcon
											.getKbtc0Icon());break;
						case 1:
							ExamFrm.this.lb_km_6
									.setIcon(ConfigManager.simuteIcon
											.getKbtc1Icon());break;
						case 2:
							ExamFrm.this.lb_km_6
									.setIcon(ConfigManager.simuteIcon
											.getKbtc2Icon());break;
						case 3:
							ExamFrm.this.lb_km_6
									.setIcon(ConfigManager.simuteIcon
											.getKbtc3Icon());break;
						}
						break;
					case 7:
						switch (state) {
						case 0:
							ExamFrm.this.lb_km_7
									.setIcon(ConfigManager.simuteIcon
											.getLkzx0Icon());break;
						case 1:
							ExamFrm.this.lb_km_7
									.setIcon(ConfigManager.simuteIcon
											.getLkzx1Icon());break;
						case 2:
							ExamFrm.this.lb_km_7
									.setIcon(ConfigManager.simuteIcon
											.getLkzx2Icon());break;
						case 3:
							ExamFrm.this.lb_km_7
									.setIcon(ConfigManager.simuteIcon
											.getLkzx3Icon());break;
						}
						break;
					case 8:
						switch (state) {
						case 0:
							ExamFrm.this.lb_km_8
									.setIcon(ConfigManager.simuteIcon
											.getLkzz0Icon());break;
						case 1:
							ExamFrm.this.lb_km_8
									.setIcon(ConfigManager.simuteIcon
											.getLkzz1Icon());break;
						case 2:
							ExamFrm.this.lb_km_8
									.setIcon(ConfigManager.simuteIcon
											.getLkzz2Icon());break;
						case 3:
							ExamFrm.this.lb_km_8
									.setIcon(ConfigManager.simuteIcon
											.getLkzz3Icon());break;
						}
						break;
					case 9:
						switch (state) {
						case 0:
							ExamFrm.this.lb_km_9
									.setIcon(ConfigManager.simuteIcon
											.getLkyz0Icon());break;
						case 1:
							ExamFrm.this.lb_km_9
									.setIcon(ConfigManager.simuteIcon
											.getLkyz1Icon());break;
						case 2:
							ExamFrm.this.lb_km_9
									.setIcon(ConfigManager.simuteIcon
											.getLkyz2Icon());break;
						case 3:
							ExamFrm.this.lb_km_9
									.setIcon(ConfigManager.simuteIcon
											.getLkyz3Icon());break;
						}
						break;
					case 10:
						switch (state) {
						case 0:
							ExamFrm.this.lb_km_10
									.setIcon(ConfigManager.simuteIcon
											.getRxhd0Icon());break;
						case 1:
							ExamFrm.this.lb_km_10
									.setIcon(ConfigManager.simuteIcon
											.getRxhd1Icon());break;
						case 2:
							ExamFrm.this.lb_km_10
									.setIcon(ConfigManager.simuteIcon
											.getRxhd2Icon());break;
						case 3:
							ExamFrm.this.lb_km_10
									.setIcon(ConfigManager.simuteIcon
											.getRxhd3Icon());break;
						}
						break;
					case 12:
						switch (state) {
						case 0:
							ExamFrm.this.lb_km_12
									.setIcon(ConfigManager.simuteIcon
											.getGgcz0Icon());break;
						case 1:
							ExamFrm.this.lb_km_12
									.setIcon(ConfigManager.simuteIcon
											.getGgcz1Icon());break;
						case 2:
							ExamFrm.this.lb_km_12
									.setIcon(ConfigManager.simuteIcon
											.getGgcz2Icon());break;
						case 3:
							ExamFrm.this.lb_km_12
									.setIcon(ConfigManager.simuteIcon
											.getGgcz3Icon());break;
						}
						break;
					case 13:
						switch (state) {
						case 0:
							ExamFrm.this.lb_km_13
									.setIcon(ConfigManager.simuteIcon
											.getHc0Icon());break;
						case 1:
							ExamFrm.this.lb_km_13
									.setIcon(ConfigManager.simuteIcon
											.getHc1Icon());break;
						case 2:
							ExamFrm.this.lb_km_13
									.setIcon(ConfigManager.simuteIcon
											.getHc2Icon());break;
						case 3:
							ExamFrm.this.lb_km_13
									.setIcon(ConfigManager.simuteIcon
											.getHc3Icon());break;
						}
						break;
					}
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
					refreshItem();
					sleep(ExamFrm.this.UI_INT);
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
					String startTime = timeFormat.format(Calendar.getInstance()
							.getTime());
					ExamFrm.this.lb_startime.setText(startTime);
					sleep(this.sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
