package com.scu.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
import com.scu.GlobelControl.SignalIcon;
import com.scu.Model.ExamWindow;
import com.scu.Model.Message;
import com.scu.Model.MyErrorTable;
import com.scu.Model.MyErrorTableModel;
import com.scu.Model.MyButton;
import com.scu.Signal.JudgeSignal;
import com.scu.Utils.GetLocateUtil;

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

	JLabel lab_signal_V = new JLabel("");
	JLabel lab_gps_lon = new JLabel("");
	JLabel lab_gps_lat = new JLabel("");
	JLabel lab_gps_angle = new JLabel("");
	JLabel lab_signal_gear = new JLabel("");
	
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
	
	

	Vector errList;
	MyErrorTable errTable;
	MyErrorTableModel errModel;

//	private TimerThread timerTickThread = null;
	private ArrayList<HashMap<String, Object>> listData = new ArrayList<HashMap<String, Object>>();
//	private ArrayList<HashMap<String, String>> listType = new ArrayList<HashMap<String, String>>();

	GolbalExamThread golbalExamThread = new GolbalExamThread(this, PrepareThread.TRAINFLAG);;
	UIThread uiThread = new UIThread();

	public int iState = -1;
	// 主屏幕的18个按钮
	JButton btn_rgzl_sczb = new MyButton(ConfigManager.simuteIcon.getSczb0Icon(),
			ConfigManager.simuteIcon.getSczb1Icon());
	JButton btn_rgzl_qb = new MyButton(ConfigManager.simuteIcon.getQb0Icon(),
			ConfigManager.simuteIcon.getQb1Icon());
	JButton btn_rgzl_zxxs = new MyButton(ConfigManager.simuteIcon.getZxxs0Icon(),
			ConfigManager.simuteIcon.getZxxs1Icon());
	JButton btn_rgzl_jjdcz = new MyButton(ConfigManager.simuteIcon.getJjdw0Icon(),
			ConfigManager.simuteIcon.getJjdw1Icon());

	JButton btn_rgzl_bgcd = new MyButton(ConfigManager.simuteIcon.getBgcd0Icon(),
			ConfigManager.simuteIcon.getBgcd1Icon());
	JButton btn_rgzl_kbtc = new MyButton(ConfigManager.simuteIcon.getKbtc0Icon(),
			ConfigManager.simuteIcon.getKbtc1Icon());
	JButton btn_rgzl_lkzx = new MyButton(ConfigManager.simuteIcon.getLkzx0Icon(),
			ConfigManager.simuteIcon.getLkzx1Icon());
	JButton btn_rgzl_lkzzw = new MyButton(ConfigManager.simuteIcon.getLkzz0Icon(),
			ConfigManager.simuteIcon.getLkzz1Icon());
	JButton btn_rgzl_lkyzw = new MyButton(ConfigManager.simuteIcon.getLkyz0Icon(),
			ConfigManager.simuteIcon.getLkyz1Icon());
	JButton btn_rgzl_rxhdx = new MyButton(ConfigManager.simuteIcon.getRxhd0Icon(),
			ConfigManager.simuteIcon.getRxhd1Icon());

	JButton btn_rgzl_xxqy = new MyButton(ConfigManager.simuteIcon.getXxqy0Icon(),
			ConfigManager.simuteIcon.getXxqy1Icon());
	JButton btn_rgzl_ggqcz = new MyButton(ConfigManager.simuteIcon.getGgcz0Icon(),
			ConfigManager.simuteIcon.getGgcz1Icon());
	JButton btn_rgzl_hc = new MyButton(ConfigManager.simuteIcon.getHc0Icon(),
			ConfigManager.simuteIcon.getHc1Icon());
	JButton btn_rgzl_cc = new MyButton(ConfigManager.simuteIcon.getCc0Icon(),
			ConfigManager.simuteIcon.getCc1Icon());
	JButton btn_rgzl_dt = new MyButton(ConfigManager.simuteIcon.getDt0Icon(),
			ConfigManager.simuteIcon.getDt1Icon());

	Rectangle rect = new Rectangle(0, 0, 10, 10);
	Font font = new Font("宋体", Font.PLAIN, 14);

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
	
	private SignalIcon signalIcon = ConfigManager.signalIcon;

	public RoutineTrainingFrm(String winName) {
		this.init();
	}

	private void init() {

	//	initType();
		JPanel contentPanel = (JPanel) this.getContentPane();
		this.setUndecorated(true);
		this.setForeground(new Color(182, 196, 211));
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		// 把系统设置对话窗口显示在屏幕正中心
		this.setLocation(x, y);
		this.setSize(WIDTH, HEIGHT);
		// 设置窗口的布局方式为GirdBagLayout
		contentPanel.setLayout(null);
		this.setVisible(true);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		topPanel.setBounds(0, 0, WIDTH, 50);
		contentPanel.add(topPanel);
		JLabel examCharacter = new JLabel(new ImageIcon(
				"images\\rcxl\\title.png"));
		topPanel.add(examCharacter);

		JPanel bottomPanel = new JPanel();
		// bottomPanel.setPreferredSize(new Dimension(ConfigManager.WIDTH, 60));
		bottomPanel.setBounds(0, 290, ConfigManager.WIDTH, 310);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		contentPanel.add(bottomPanel);

		
		// 除了上下标签以外的主窗口部分
		
		JPanel midPanel = new JPanel();
		midPanel.setBorder(null);
		// midPanel.setPreferredSize(new Dimension(ConfigManager.WIDTH,
		// ConfigManager.HEIGHT - topPanel.getHeight()
		// - bottomPanel.getHeight()));
		midPanel.setBounds(0, topPanel.getHeight(), 800, 240);

		contentPanel.add(midPanel);
		// *************************划分上下标签中间部分************************/
		midPanel.setLayout(null);
		// 设置中层面板的上半部分
		JPanel midTopPanel = new JPanel();
		midTopPanel.setBounds(0, 0, 800,
				185);
		midPanel.add(midTopPanel);
		
		JPanel emptyPanel3 = new JPanel();
		emptyPanel3.setBounds(0, 185, WIDTH,1 );
		emptyPanel3.setBorder(BorderFactory.createLineBorder(new Color(12, 97, 159)));
		midPanel.add(emptyPanel3);
		// 设置中层面板的下半部分
		JPanel midBottomPanel = new JPanel();
		midBottomPanel.setBounds(0, 186, 800, 53);
		midPanel.add(midBottomPanel);
		
		
		midBottomPanel.setBackground(Color.WHITE);
		midPanel.add(midBottomPanel);
		// 指示信号面板
		midBottomPanel.setLayout(new GridLayout(1, 17, 0, 0));
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

		midBottomPanel.add(gps);
		midBottomPanel.add(wl);
		midBottomPanel.add(dw);
		midBottomPanel.add(xh);
		midBottomPanel.add(zzxg);
		midBottomPanel.add(yzxg);
		midBottomPanel.add(yjdg);
		midBottomPanel.add(jgdg);
		midBottomPanel.add(ygdg);
		midBottomPanel.add(wdg);
		midBottomPanel.add(jss);
		midBottomPanel.add(sss);
		midBottomPanel.add(fss);
		midBottomPanel.add(qldg);
		midBottomPanel.add(hldg);
		midBottomPanel.add(lbs);
		midBottomPanel.add(aqdbd);
		
		JPanel emptyPanel2 = new JPanel();
		emptyPanel2.setBounds(0, 239, WIDTH, 1);
		emptyPanel2.setBorder(BorderFactory.createLineBorder(new Color(12, 97, 159)));
		midPanel.add(emptyPanel2);
			

		// 分割中层面板的上半部分
		midTopPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(
				midTopPanel.getWidth() * 2 / 3 - 30, midTopPanel.getHeight()));
		midTopPanel.add(leftPanel);

		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(
				midTopPanel.getWidth() * 1 / 3 + 30, midTopPanel.getHeight()));
		midTopPanel.add(rightPanel);
		
		// 分割中层面板的下半部分
		
		
		JPanel titlePane = new JPanel();
		titlePane.setBackground(Color.WHITE);
		bottomPanel.add(titlePane);
		titlePane.setPreferredSize(new Dimension(WIDTH, 29));
		GridBagLayout gridBageLayout = new GridBagLayout();
		gridBageLayout.columnWidths = new int[] { 100, 60 };
		gridBageLayout.columnWeights = new double[] { 0.0D, 0.0D, 1.0D };
		gridBageLayout.rowWeights = new double[] { 0.0D };
		titlePane.setLayout(gridBageLayout);

		JLabel newLabel1 = new JLabel("扣分项目");
		newLabel1.setHorizontalAlignment(JLabel.CENTER);
		newLabel1.setForeground(Color.BLACK);
		newLabel1.setFont(new Font("黑体", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		titlePane.add(newLabel1, gbc_lblNewLabel);

		JLabel newLabel2 = new JLabel("扣分");
		newLabel2.setHorizontalAlignment(JLabel.CENTER);
		newLabel2.setForeground(Color.BLACK);
		newLabel2.setFont(new Font("黑体", Font.BOLD, 15));
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_9.gridx = 1;
		gbc_label_9.gridy = 0;
		titlePane.add(newLabel2, gbc_label_9);

		JLabel newLabel3 = new JLabel("扣分原因");
		newLabel3.setHorizontalAlignment(JLabel.CENTER);
		newLabel3.setForeground(Color.BLACK);
		newLabel3.setFont(new Font("黑体", Font.BOLD, 15));
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.insets = new Insets(0, 0, 5, 0);
		gbc_label_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_10.gridx = 2;
		gbc_label_10.gridy = 0;
		titlePane.add(newLabel3, gbc_label_10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		bottomPanel.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(WIDTH, 280));
		scrollPane.getViewport().setBackground(Color.WHITE);

		this.errList = new Vector();
		this.errModel = new MyErrorTableModel(this.errList);
		this.errTable = new MyErrorTable(this.errModel);
//		this.errModel.addRow("准备错误", -10, "不按规定准备");

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

		leftPanel.setLayout(new GridLayout(3, 5, 0, 0));
		leftPanel.setBackground(Color.WHITE);
		leftPanel.add(btn_rgzl_sczb);
		leftPanel.add(btn_rgzl_qb);
		leftPanel.add(btn_rgzl_zxxs);
		leftPanel.add(btn_rgzl_jjdcz);
		leftPanel.add(btn_rgzl_bgcd);

		leftPanel.add(btn_rgzl_kbtc);
		leftPanel.add(btn_rgzl_lkzx);
		leftPanel.add(btn_rgzl_lkzzw);
		leftPanel.add(btn_rgzl_lkyzw);
		leftPanel.add(btn_rgzl_rxhdx);

		leftPanel.add(btn_rgzl_xxqy);
		leftPanel.add(btn_rgzl_ggqcz);
		leftPanel.add(btn_rgzl_hc);
		leftPanel.add(btn_rgzl_cc);
		leftPanel.add(btn_rgzl_dt);

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

		rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		rightPanel.setBackground(Color.WHITE);
		

		JPanel emptyPanel4 = new JPanel();
		emptyPanel4.setPreferredSize(new Dimension(1, midTopPanel.getHeight()));
		emptyPanel4.setBorder(BorderFactory.createLineBorder(new Color(12, 97, 159)));
		rightPanel.add(emptyPanel4);
		
		JPanel rightLeftPanel = new JPanel();
		rightLeftPanel.setOpaque(false);
		rightLeftPanel.setPreferredSize(new Dimension(160, midTopPanel.getHeight()));
		rightLeftPanel.setLayout(new GridLayout(5, 1, 0, 0));
		rightLeftPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
		rightPanel.add(rightLeftPanel);
		
		JPanel panel_lon = new JPanel();
		panel_lon.setOpaque(false);
		rightLeftPanel.add(panel_lon);
		panel_lon.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lon = new JLabel("经度：");
		lon.setHorizontalAlignment(0);
		lon.setForeground(Color.BLACK);
		lon.setFont(font);
		panel_lon.add(lon);

		this.lab_gps_lon = new JLabel("1144.12030");
		this.lab_gps_lon.setHorizontalAlignment(2);
		this.lab_gps_lon.setForeground(Color.BLACK);
		this.lab_gps_lon.setFont(font);
		panel_lon.add(this.lab_gps_lon);

		JPanel panel_lat = new JPanel();
		panel_lat.setOpaque(false);
		rightLeftPanel.add(panel_lat);
		panel_lat.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lat = new JLabel("纬度：");
		lat.setHorizontalAlignment(0);
		lat.setForeground(Color.BLACK);
		lat.setFont(font);
		panel_lat.add(lat);

		this.lab_gps_lat = new JLabel("123.1334");
		this.lab_gps_lat.setHorizontalAlignment(2);
		this.lab_gps_lat.setForeground(Color.BLACK);
		this.lab_gps_lat.setFont(font);
		panel_lat.add(this.lab_gps_lat);

		JPanel panel_ori = new JPanel();
		panel_ori.setOpaque(false);
		rightLeftPanel.add(panel_ori);
		panel_ori.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel ori = new JLabel("方向：");
		ori.setHorizontalAlignment(0);
		ori.setForeground(Color.BLACK);
		ori.setFont(font);
		panel_ori.add(ori);

		this.lab_gps_angle = new JLabel("0.0");
		this.lab_gps_angle.setHorizontalAlignment(2);
		this.lab_gps_angle.setForeground(Color.BLACK);
		this.lab_gps_angle.setFont(font);
		panel_ori.add(this.lab_gps_angle);
		
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		rightLeftPanel.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel label_5 = new JLabel("车速：");
		label_5.setHorizontalAlignment(4);
		label_5.setForeground(Color.BLACK);
		label_5.setFont(font);
		panel_3.add(label_5);

		this.lab_signal_V = new JLabel("50Km/h");
		this.lab_signal_V.setHorizontalAlignment(2);
		this.lab_signal_V.setForeground(Color.BLACK);
		this.lab_signal_V.setFont(font);
		panel_3.add(this.lab_signal_V);


		JPanel panel_dang = new JPanel();
		panel_dang.setOpaque(false);
		rightLeftPanel.add(panel_dang);
		panel_dang.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel dang = new JLabel("档位：");
		dang.setHorizontalAlignment(0);
		dang.setForeground(Color.BLACK);
		dang.setFont(font);
		panel_dang.add(dang);

		this.lab_signal_gear = new JLabel("0档");
		this.lab_signal_gear.setHorizontalAlignment(2);
		this.lab_signal_gear.setForeground(Color.BLACK);
		this.lab_signal_gear.setFont(font);
		panel_dang.add(this.lab_signal_gear);
		
		JButton btn_set_back = new JButton("");
		btn_set_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 if (RoutineTrainingFrm.this.golbalExamThread != null) {
					 RoutineTrainingFrm.this.golbalExamThread.runFlag = false;
			        }
				 RoutineTrainingFrm.this.removeAll();
			//	new MainWindow("路考王");
				RoutineTrainingFrm.this.dispose();
			}
		});
		btn_set_back.setBorderPainted(false);
		btn_set_back.setIcon(new ImageIcon("images\\dgmn\\end.png"));
		btn_set_back.setRolloverIcon(new ImageIcon("images\\dgmn\\end_touch.png"));
		btn_set_back.setMargin(new Insets(0, 0, 0, 0));
		btn_set_back.setContentAreaFilled(true);
		btn_set_back.setPreferredSize(new Dimension(130, 50));
		
		JPanel rightRightPanel = new JPanel();
		rightRightPanel.setLayout(new GridLayout(3, 1, 0, 0 ));
		rightRightPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 25, 0));
		rightRightPanel.setPreferredSize(new Dimension(120, midTopPanel.getHeight()));
		rightRightPanel.setOpaque(false);
		rightPanel.add(rightRightPanel);
		JLabel empty1 = new JLabel("");
		rightRightPanel.add(empty1);
		JLabel empty2 = new JLabel("");
		rightRightPanel.add(empty2);
		rightRightPanel.add(btn_set_back);
		
		
		
	//	panel_dang.add(btn_set_back);


		/**********************************************************************************/
		// 分割中层面板的上半部分的右半部分

		Rectangle rect = this.errTable.getCellRect(
				this.errModel.getRowCount() - 1, 0, true);
		this.errTable.scrollRectToVisible(rect);

//		this.timerTickThread = new TimerThread();
//		this.timerTickThread.start();
		this.uiThread = new UIThread();
		this.uiThread.start();

	    golbalExamThread.start();
	    
	    
	    
	    this.iState = 1;
		
		/*******************************************************************/

	}

//	private void initType() {
//		DBHelper db = new DBHelper();
//		try {
//			db.conn();
//			ResultSet rs = db.Query("select * from systemparm");
//			if (rs != null) {
//
//				try {
//					while (rs.next()) {
//						int id = rs.getInt("no2");
//						String title = new String(rs.getString("txt3"));
//						String desc = rs.getString("txt4");
//
//						HashMap hm = new HashMap();
//						hm.put("id", Integer.valueOf(id));
//						hm.put("title", title);
//						hm.put("desc", desc);
//						this.listType.add(hm);
//					}
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//			}
//			rs.close();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			db.close();
//		}
//	}

	public void handleMessage(Message msg) {
		if (!this.isRunning) {
			return;
		}

		try {
			switch (msg.what) {
			case -1:
//				String timetick = msg.Bundle.get("timetick").toString();
//				this.timerLabel.setText(timetick);
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
		 
		 
		 
		 
//	    if (this.timerTickThread != null)
//	      this.timerTickThread.runFlag = false;
//	    this.timerTickThread = null;
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
	 //     setFlagById(1);
	      break;
	    case 2:
	      startThread = new StartThread(this, StartThread.TRAINFLAG);
	      addModule(startThread);
	      startThread.start();
	 //     setFlagById(2);
	      break;
	    case 3:
	      onLineThread = new OnLineThread(this, OnLineThread.TRAINFLAG);
	      addModule(onLineThread);
	      onLineThread.start();
	 //     setFlagById(3);
	      break;
	    case 14:
	      gearThread = new GearThread(this, GearThread.TRAINFLAG);
	      addModule(gearThread);
	      gearThread.start();
	 //     setFlagById(14);
	      break;
	    case 4:
	      changeLaneThread = new ChangeLaneThread(this, ChangeLaneThread.TRAINFLAG);
	      addModule(changeLaneThread);
	      changeLaneThread.start();
	//      setFlagById(4);
	      break;
	    case 11:
	      stopThread = new StopThread(this, StopThread.TRAINFLAG);
	      addModule(stopThread);
	      stopThread.start();
	  //    setFlagById(11);
	      break;
	    case 5:
	      crossingLineThread = new LuKouStraightThread(this, LuKouStraightThread.TRAINFLAG);
			addModule(crossingLineThread);
			crossingLineThread.start();
	//		setFlagById(5);
			break;
		case 15:
			turnLeftThread = new TurnLeftThread(this, TurnLeftThread.TRAINFLAG);
			addModule(turnLeftThread);
			turnLeftThread.start();
	//		setFlagById(15);
			break;
		case 16:
			turnRightThread = new TurnRightThread(this,
					TurnRightThread.TRAINFLAG);
			addModule(turnRightThread);
	      turnRightThread.start();
	  //    setFlagById(16);
	      break;
	    case 6:
	      pavementThread = new PavementThread(this, PavementThread.TRAINFLAG);
	      addModule(pavementThread);
	      pavementThread.isPlay = true;
	      pavementThread.start();
	 //     setFlagById(6);
	      break;
	    case 7:
	      schoolThread = new SchoolAreaThread(this, SchoolAreaThread.TRAINFLAG);
	      addModule(schoolThread);
	      schoolThread.start();
	  //    setFlagById(7);
	      break;
	    case 8:
	      busThread = new BusThread(this, BusThread.TRAINFLAG);
	      addModule(busThread);
	      busThread.start();
	 //     setFlagById(8);
	      break;
	    case 9:
	      meetingThread = new MeetingThread(this, MeetingThread.TRAINFLAG);
	      addModule(meetingThread);
	      meetingThread.start();
	  //    setFlagById(9);
	      break;
	    case 10:
	      overTakenThread = new OverTakenThread(this, OverTakenThread.TRAINFLAG);
	      addModule(overTakenThread);
	      overTakenThread.start();
	 //     setFlagById(10);
	      break;
	    case 12:
	      turnAroundThread = new TurnAroundThread(this, TurnAroundThread.TRAINFLAG);
	      addModule(turnAroundThread);
	      turnAroundThread.start();
	//      setFlagById(12);
	      break;
	    case 13:
	    }
	  }


	
//	private void setFlagById(int typeId)
//	  {
////	    try
////	    {
////	      for (int i = 0; i < this.listType.size(); i++) {
////	        HashMap hm = (HashMap)this.listType.get(i);
////	        int id = Integer.parseInt(hm.get("id").toString());
////	        if (id == typeId) {
////	          String title = hm.get("title").toString();
////	          String desc = hm.get("desc").toString();
//////	          this.ItemTitleLabel.setText(title);
//////	          this.descLabel.setText(desc);
////	          break;
////	        }
////	      }
////	    } catch (Exception ex) {
////	      ex.printStackTrace();
////	    }
//	  }


	class UIThread extends Thread {
		public boolean runFlag = true;

		public UIThread() {
		}

		private void refreshState() {
			try {
				JudgeSignal carSignal = JudgeSignal.getInstance();
				
				RoutineTrainingFrm.this.lab_signal_V
						.setText((int) (carSignal.gpsspeed) + "Km/h");
				RoutineTrainingFrm.this.lab_gps_lon.setText(new DecimalFormat(
						"#.00000").format(carSignal.lon));
				RoutineTrainingFrm.this.lab_gps_lat.setText(new DecimalFormat(
						"#.00000").format(carSignal.lat));
				RoutineTrainingFrm.this.lab_gps_angle
						.setText(carSignal.gpsangle + "");
				RoutineTrainingFrm.this.lab_signal_gear.setText(carSignal.gear
						+ "");
				
				
				
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
		          refreshState();
		          sleep(200L);
		        } catch (Exception ex) {
		          ex.printStackTrace();
		        }
		    }
	}

//	class TimerThread extends Thread {
//		public boolean runFlag = true;
//		private int sleepTime = 200;
//
//		public TimerThread() {
//		}
//
//		public void run() {
//			while (this.runFlag) {
//				try {
//					//设置通用评判
//					ModuleThread globel = (ModuleThread)RoutineTrainingFrm.this.golbalExamThread;
//					Long globeltime =  10 - (System.currentTimeMillis() - globel.startTime )/ 1000L;
////					if(globeltime > 0){
////						
////						RoutineTrainingFrm.this.globelTimer.setText( globeltime + "秒");
////					}
////					else
////					{
////						RoutineTrainingFrm.this.globelTimer.setText( "开始");
////					}
////					
//					
//					if (RoutineTrainingFrm.this.itemList.size() > 0) {
//						ModuleThread it = (ModuleThread) RoutineTrainingFrm.this.itemList.get(0);
//						int jsfs = it.jsfs;
////						if (jsfs == 1)
////							RoutineTrainingFrm.this.timerLabel
////									.setText(it.iTimeOut /1000L
////											- (System.currentTimeMillis() - it.startTime )
////											/ 1000L + "秒");
////						else
////							RoutineTrainingFrm.this.timerLabel
////									.setText((int) (it.dRangeOut - it.curRange)
////											+ "米");
////					} else {
////						RoutineTrainingFrm.this.timerLabel.setText("");
//					}
//					sleep(this.sleepTime);
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//			}
//		}
//	}
}
