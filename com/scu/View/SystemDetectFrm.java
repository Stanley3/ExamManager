package com.scu.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.scu.GlobelControl.ConfigManager;
import com.scu.GlobelControl.ImageIconSet;
import com.scu.Signal.JudgeSignal;
import com.scu.Utils.GetLocateUtil;
import com.scu.Utils.JPanelUtils;

/**
 * 系统检测面板
 * 
 * @author 孙晓雨 2014.10.12
 */

public class SystemDetectFrm extends JFrame {
	private static final long serialVersionUID = 1L;


	private ImageIconSet allImage = ConfigManager.allImage;
	
	private SystemDetectFrm frame;
	JLabel lab_lamp_width = new JLabel("");
	JLabel lab_lamp_near = new JLabel("");
	JLabel lab_lamp_highbeam = new JLabel("");
	JLabel lab_lamp_fog = new JLabel("");
	JLabel lab_lamp_left= new JLabel("");
	JLabel lab_lamp_right= new JLabel("");
	JLabel lab_lamp_urgent= new JLabel("");
	JLabel lab_lamp_brake= new JLabel("");
	JLabel lab_signal_rightbrake= new JLabel("");
	JLabel lab_signal_door= new JLabel("");
	JLabel lab_signal_life= new JLabel("");
	JLabel lab_signal_clutchpedal= new JLabel("");
	JLabel lab_signal_handbrake= new JLabel("");
	JLabel lab_signal_horn= new JLabel("");
	JLabel lab_signal_acc= new JLabel("");
	JLabel lab_signal_off= new JLabel("");
	JLabel lab_signal_carsidea= new JLabel("");
	JLabel lab_signal_carsideb= new JLabel("");
	JLabel lab_signal_wheelangle;
	JLabel lab_signal_n;
	JLabel lab_signal_V;
	JLabel lab_gps_lon;
	JLabel lab_gps_lat;
	JLabel lab_gps_angle;
	JLabel lab_gps_speed;
	JLabel lab_signal_gear;

	Rectangle rect;
	Font font;
	
	UIThread uiThread = null;

	public SystemDetectFrm(String winName){
		super(winName);
		this.init();
	}

	private void init() {
		this.frame = this;
		rect = new Rectangle(0, 0, 30, 30);
		font = new Font("华文中宋", 1, 24);
		
		int x = GetLocateUtil.GetLocateX(ConfigManager.WIDTH);
		int y = GetLocateUtil.GetLocateY(ConfigManager.HEIGHT);
		//把系统设置对话窗口显示在屏幕正中心
		this.setLocation(x, y);
		this.setSize(ConfigManager.WIDTH, ConfigManager.HEIGHT);
		this.setResizable(false);
		
		this.setForeground(new Color(185, 194, 203));
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);

		// Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(ConfigManager.WIDTH, ConfigManager.HEIGHT);

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
		contentPane.add(topPanel);
		topPanel.setLayout(new BorderLayout(0, 0));

		JLabel titleLabel = new JLabel("信号检测");
		titleLabel.setHorizontalAlignment(0);
		titleLabel.setFont(new Font("华文中宋", 1, 26));
		titleLabel.setForeground(Color.WHITE);
		topPanel.add(titleLabel, "Center");

		JPanel bottomPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = allImage.getHeadIcon().getImage();
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		};
		bottomPanel.setPreferredSize(new Dimension(ConfigManager.WIDTH, 60));
		bottomPanel
				.setBounds(0, ConfigManager.HEIGHT - 50, ConfigManager.WIDTH,
						50);
		contentPane.add(bottomPanel);

		JButton btn_set_back = new JButton("");
		btn_set_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SettingFrm("系统设置");
				setbackActionPerformed(arg0);
			}
		});
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		btn_set_back.setBorderPainted(false);
		btn_set_back.setIcon(allImage.getSet_backIcon());
		btn_set_back.setRolloverIcon(allImage.getSet_back_touchIcon());
		btn_set_back.setMargin(new Insets(2, 30, 2, 30));
		btn_set_back.setContentAreaFilled(false);
		btn_set_back.setPreferredSize(new Dimension(130, 50));
		bottomPanel.add(btn_set_back);
		

		JPanel midPanel = new JPanel();
		midPanel.setOpaque(false);
		midPanel.setBorder(null);
		midPanel.setPreferredSize(new Dimension(ConfigManager.WIDTH,
				ConfigManager.HEIGHT - topPanel.getHeight()
						- bottomPanel.getHeight()));
		midPanel.setBounds(0, topPanel.getHeight(), ConfigManager.WIDTH,
				ConfigManager.HEIGHT - topPanel.getHeight()
						- bottomPanel.getHeight());
		getContentPane().add(midPanel);
		midPanel.setLayout(new GridLayout(8, 4, 0, 0));


		
		JPanelUtils.compCombine(midPanel, this.lab_lamp_width, "示宽灯", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine(midPanel, this.lab_lamp_near, "近光灯", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine(midPanel, this.lab_lamp_highbeam, "远光灯", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine(midPanel, this.lab_lamp_left, "左转灯", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine(midPanel, this.lab_lamp_right, "右转灯", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine(midPanel, this.lab_lamp_fog, "雾   灯", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine(midPanel, this.lab_lamp_urgent, "应急灯", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine(midPanel, this.lab_signal_door, "车   门", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine(midPanel, this.lab_signal_handbrake, "手   刹", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine(midPanel, this.lab_signal_life, "安全带", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine(midPanel, this.lab_lamp_brake, "脚   刹", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine(midPanel, this.lab_signal_rightbrake, "副脚刹", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine(midPanel, this.lab_signal_acc, " ACC ", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine(midPanel, this.lab_signal_off, "熄   火", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine(midPanel, this.lab_signal_horn, "喇   叭", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine(midPanel, this.lab_signal_clutchpedal, "离合器", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine(midPanel, this.lab_signal_carsidea, "绕前点", allImage.getSwitchcloseIcon(), rect, font);
		JPanelUtils.compCombine(midPanel, this.lab_signal_carsideb, "绕后点", allImage.getSwitchcloseIcon(), rect, font);
		
		
		
		JPanel panel_13 = new JPanel();
		panel_13.setOpaque(false);
		panel_13.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		midPanel.add(panel_13);
		panel_13.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel label_25 = new JLabel("车   速");
		label_25.setHorizontalAlignment(0);
		label_25.setForeground(Color.BLACK);
		label_25.setFont(font);
		panel_13.add(label_25);

		this.lab_signal_V = new JLabel("25.0Km/h");
		this.lab_signal_V.setHorizontalAlignment(0);
		this.lab_signal_V.setForeground(Color.BLACK);
		this.lab_signal_V.setFont(font);
		panel_13.add(this.lab_signal_V);

		JPanel panel_14 = new JPanel();
		panel_14.setOpaque(false);
		panel_14.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		midPanel.add(panel_14);
		panel_14.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel label_26 = new JLabel("转   速");
		label_26.setHorizontalAlignment(0);
		label_26.setForeground(Color.BLACK);
		label_26.setFont(font);
		panel_14.add(label_26);

		this.lab_signal_n = new JLabel("0RPM");
		this.lab_signal_n.setHorizontalAlignment(0);
		this.lab_signal_n.setForeground(Color.BLACK);
		this.lab_signal_n.setFont(font);
		panel_14.add(this.lab_signal_n);

		JPanel panel_16 = new JPanel();
		panel_16.setOpaque(false);
		panel_16.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		midPanel.add(panel_16);
		panel_16.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblLon = new JLabel("LON");
		lblLon.setHorizontalAlignment(0);
		lblLon.setForeground(Color.BLACK);
		lblLon.setFont(font);
		panel_16.add(lblLon);

		this.lab_gps_lon = new JLabel("100.123456");
		this.lab_gps_lon.setHorizontalAlignment(0);
		this.lab_gps_lon.setForeground(Color.BLACK);
		this.lab_gps_lon.setFont(font);
		panel_16.add(this.lab_gps_lon);

		JPanel panel_17 = new JPanel();
		panel_17.setOpaque(false);
		panel_17.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		midPanel.add(panel_17);
		panel_17.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblLat = new JLabel("LAT");
		lblLat.setHorizontalAlignment(0);
		lblLat.setForeground(Color.BLACK);
		lblLat.setFont(font);
		panel_17.add(lblLat);

		this.lab_gps_lat = new JLabel("30.123456");
		this.lab_gps_lat.setHorizontalAlignment(0);
		this.lab_gps_lat.setForeground(Color.BLACK);
		this.lab_gps_lat.setFont(font);
		panel_17.add(this.lab_gps_lat);

		JPanel panel_18 = new JPanel();
		panel_18.setOpaque(false);
		panel_18.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		midPanel.add(panel_18);
		panel_18.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblGps = new JLabel("GPS角度");
		lblGps.setHorizontalAlignment(0);
		lblGps.setForeground(Color.BLACK);
		lblGps.setFont(font);
		panel_18.add(lblGps);

		this.lab_gps_angle = new JLabel("210");
		this.lab_gps_angle.setHorizontalAlignment(0);
		this.lab_gps_angle.setForeground(Color.BLACK);
		this.lab_gps_angle.setFont(font);
		panel_18.add(this.lab_gps_angle);

		JPanel panel_25 = new JPanel();
		panel_25.setOpaque(false);
		panel_25.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		midPanel.add(panel_25);
		panel_25.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblGps_1 = new JLabel("GPS车速");
		lblGps_1.setHorizontalAlignment(0);
		lblGps_1.setForeground(Color.BLACK);
		lblGps_1.setFont(font);
		panel_25.add(lblGps_1);

		this.lab_gps_speed = new JLabel("25Km/h");
		this.lab_gps_speed.setHorizontalAlignment(0);
		this.lab_gps_speed.setForeground(Color.BLACK);
		this.lab_gps_speed.setFont(font);
		panel_25.add(this.lab_gps_speed);

		JPanel panel_19 = new JPanel();
		panel_19.setOpaque(false);
		panel_19.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		midPanel.add(panel_19);
		panel_19.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel label_29 = new JLabel("档位");
		label_29.setHorizontalAlignment(0);
		label_29.setForeground(Color.BLACK);
		label_29.setFont(font);
		panel_19.add(label_29);

		this.lab_signal_gear = new JLabel("4");
		this.lab_signal_gear.setHorizontalAlignment(0);
		this.lab_signal_gear.setForeground(Color.BLACK);
		this.lab_signal_gear.setFont(font);
		panel_19.add(this.lab_signal_gear);

		JPanel panel_15 = new JPanel();
		panel_15.setOpaque(false);
		panel_15.setBorder(BorderFactory.createLineBorder(new Color(195, 201,
				212)));
		midPanel.add(panel_15);
		panel_15.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel label_27 = new JLabel("方向盘");
		label_27.setHorizontalAlignment(0);
		label_27.setForeground(Color.BLACK);
		label_27.setFont(font);
		panel_15.add(label_27);

		this.lab_signal_wheelangle = new JLabel("0");
		this.lab_signal_wheelangle.setHorizontalAlignment(0);
		this.lab_signal_wheelangle.setForeground(Color.BLACK);
		this.lab_signal_wheelangle.setFont(font);
		panel_15.add(this.lab_signal_wheelangle);
		uiThread = new UIThread();
		uiThread.start();

		this.setVisible(true);
	}

	 public void dispose()
	  {
	    if (this.uiThread != null)
	      this.uiThread.runFlag = false;
	    this.uiThread = null;
	    super.dispose();
	  }
	 private void setbackActionPerformed(ActionEvent evt) {
		    dispose();
		  }

	class UIThread extends Thread {
		public boolean runFlag = true;

		public UIThread() {
		}

		private void refreshState() {
			try {
				JudgeSignal carSignal = JudgeSignal.getInstance();
		//		System.out.println(carSignal.lamp_width);
				
		//		System.out.println(allImage.getSwitchcloseIcon());
			//	System.out.println(SystemDetectFrm.this.lab_lamp_width);
				if (carSignal.lamp_width)
					SystemDetectFrm.this.lab_lamp_width
							.setIcon(allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_lamp_width
							.setIcon(allImage
									.getSwitchcloseIcon());
				if (carSignal.lamp_near)
					SystemDetectFrm.this.lab_lamp_near
							.setIcon(allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_lamp_near
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.lamp_highbeam)
					SystemDetectFrm.this.lab_lamp_highbeam
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_lamp_highbeam
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.lamp_fog)
					SystemDetectFrm.this.lab_lamp_fog
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_lamp_fog
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.lamp_left)
					SystemDetectFrm.this.lab_lamp_left
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_lamp_left
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.lamp_right)
					SystemDetectFrm.this.lab_lamp_right
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_lamp_right
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.lamp_urgent)
					SystemDetectFrm.this.lab_lamp_urgent
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_lamp_urgent
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_footbrake)
					SystemDetectFrm.this.lab_lamp_brake
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_lamp_brake
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_deputybrake)
					SystemDetectFrm.this.lab_signal_rightbrake
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_signal_rightbrake
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_door)
					SystemDetectFrm.this.lab_signal_door
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_signal_door
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_seatbelt)
					SystemDetectFrm.this.lab_signal_life
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_signal_life
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_clutchpedal)
					SystemDetectFrm.this.lab_signal_clutchpedal
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_signal_clutchpedal
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_handbrake)
					SystemDetectFrm.this.lab_signal_handbrake
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_signal_handbrake
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_horn)
					SystemDetectFrm.this.lab_signal_horn
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_signal_horn
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_acc)
					SystemDetectFrm.this.lab_signal_acc
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_signal_acc
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_off)
					SystemDetectFrm.this.lab_signal_off
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_signal_off
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_frontbumper)
					SystemDetectFrm.this.lab_signal_carsidea
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_signal_carsidea
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				if (carSignal.signal_rearbumper)
					SystemDetectFrm.this.lab_signal_carsideb
							.setIcon(ConfigManager.allImage.getSwithopenIcon());
				else
					SystemDetectFrm.this.lab_signal_carsideb
							.setIcon(ConfigManager.allImage
									.getSwitchcloseIcon());
				SystemDetectFrm.this.lab_signal_wheelangle
						.setText(carSignal.wheelangle + "");
				SystemDetectFrm.this.lab_signal_n.setText(carSignal.n + "RPM");
				 SystemDetectFrm.this.lab_signal_V.setText((int)(carSignal.gpsspeed)
				 + "Km/h");
				SystemDetectFrm.this.lab_gps_lon.setText(new DecimalFormat("#.0000").format(carSignal.lon));
				SystemDetectFrm.this.lab_gps_lat.setText(new DecimalFormat("#.0000").format(carSignal.lat));
				SystemDetectFrm.this.lab_gps_angle.setText(carSignal.gpsangle
						+ "");
				SystemDetectFrm.this.lab_gps_speed.setText((int)(carSignal.gpsspeed)
						+ "Km/h");
				SystemDetectFrm.this.lab_signal_gear.setText(carSignal.gear
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
}
