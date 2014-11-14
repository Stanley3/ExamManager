package com.scu.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.scu.GlobelControl.ConfigManager;
import com.scu.GlobelControl.ImageIconSet;
import com.scu.Model.MyButton;
import com.scu.Utils.AddComponentUtil;
import com.scu.Utils.GetLocateUtil;
/**
 * 参数设置控制面板
 * @author 孙晓雨  2013.10.10
 *
 */

public class ParSettingFrm extends JFrame{
	/*
	 * 配置文件
	 */
	private static final long serialVersionUID = 1L;

	private static ImageIconSet allImage = ConfigManager.allImage;
	
	private static final int WIDTH = ConfigManager.WIDTH;
	private static final int HEIGHT = ConfigManager.HEIGHT;
	public ParSettingFrm(String winName) {
		super(winName);
		this.init();
	}
	
	public void init(){	
		
		JPanel contentPanel = (JPanel)this.getContentPane();
		contentPanel.setBackground(new Color(188, 188, 188));//new Color(55, 77, 118)
		
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		//把系统设置对话窗口显示在屏幕正中心
		this.setLocation(x, y);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setLayout(null);
		
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
		contentPanel.add(topPanel);
		topPanel.setLayout(new BorderLayout(0, 0));

		JLabel titleLabel = new JLabel("参数设置");
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
		contentPanel.add(bottomPanel);

		JButton btn_set_back = new JButton("");
		btn_set_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SettingFrm("系统检测");
				ParSettingFrm.this.dispose();
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
		contentPanel.add(midPanel);
		
		//设置窗口的布局方式为GirdBagLayout
		GridBagLayout lay = new GridBagLayout();
		midPanel.setLayout(lay);
		//添加18个配置按钮
		JButton j_AutoJudge = new MyButton(allImage.getXtszIcon(), allImage.getXtsz_touchIcon());
		JButton j_Common = new MyButton(allImage.getTyppIcon(), allImage.getTypp_touchIcon());
		JButton j_AboardPrepare = new MyButton(allImage.getSczbIcon(), allImage.getSczb_touchIcon());
		JButton j_StartCar = new MyButton(allImage.getQbIcon(), allImage.getQb_touchIcon());
		JButton j_LinerDriving = new MyButton(allImage.getZxxsIcon(), allImage.getZxxs_touchIcon());
		JButton j_PlusSubstractDang = new MyButton(allImage.getJjdczIcon(), allImage.getJjdcz_touchIcon());
		
		JButton j_ChangLane = new MyButton(allImage.getBgcdIcon(), allImage.getBgcd_touchIcon());
		JButton j_PullOver = new MyButton(allImage.getKbtcIcon(), allImage.getKbtc_touchIcon());
		JButton j_LuKouStraight = new MyButton(allImage.getLkzzIcon(), allImage.getLkzz_touchIcon());
		JButton j_TurnLeft = new MyButton(allImage.getLkzzwIcon(), allImage.getLkzzw_touchIcon());
		JButton j_TurnRight = new MyButton(allImage.getLkyzwIcon(), allImage.getLkyzw_touchIcon());
		JButton j_FootWayLiner = new MyButton(allImage.getRxhdxIcon(), allImage.getRxhdx_touchIcon());
		
		JButton j_SchoolArea = new MyButton(allImage.getXxqyIcon(), allImage.getXxqy_touchIcon());
		JButton j_BusStation = new MyButton(allImage.getGjqczIcon(), allImage.getGjqcz_touchIcon());
		JButton j_MeetingCar = new MyButton(allImage.getHcIcon(), allImage.getHc_touchIcon());
		JButton j_OverCar = new MyButton(allImage.getCcIcon(), allImage.getCc_touchIcon());
		JButton j_TrunRound = new MyButton(allImage.getDtIcon(), allImage.getDt_touchIcon());
		JButton j_HandWheel = new MyButton(allImage.getFxpIcon(), allImage.getFxp_touchIcon());
		
		AddComponentUtil addComponent = new AddComponentUtil(midPanel);
		//添加布局控制器
		GridBagConstraints constraints = new GridBagConstraints();
		//窗口上的控件不放大
		constraints.fill = GridBagConstraints.NONE;
		//添加权重
		constraints.weightx = 1;
		constraints.weighty = 1;
		
		addComponent.add(j_AutoJudge, constraints,  0, 0, 1, 1);
		addComponent.add(j_Common, constraints,  0, 1, 1, 1);
		addComponent.add(j_AboardPrepare, constraints,  0, 2, 1, 1);
		addComponent.add(j_StartCar, constraints,  0, 3, 1, 1);
		addComponent.add(j_LinerDriving, constraints,  0, 4, 1, 1);
		addComponent.add(j_PlusSubstractDang, constraints,  0, 5, 1, 1);
		
		addComponent.add(j_ChangLane, constraints,  1, 0, 1, 1);
		addComponent.add(j_PullOver, constraints,  1, 1, 1, 1);
		addComponent.add(j_LuKouStraight, constraints,  1, 2, 1, 1);
		addComponent.add(j_TurnLeft, constraints,  1, 3, 1, 1);
		addComponent.add(j_TurnRight, constraints,  1, 4, 1, 1);
		addComponent.add(j_FootWayLiner, constraints,  1, 5, 1, 1);
		
		addComponent.add(j_SchoolArea, constraints,  2, 0, 1, 1);
		addComponent.add(j_BusStation, constraints,  2, 1, 1, 1);
		addComponent.add(j_MeetingCar, constraints,  2, 2, 1, 1);
		addComponent.add(j_OverCar, constraints,  2, 3, 1, 1);
		addComponent.add(j_TrunRound, constraints,  2, 4, 1, 1);
		addComponent.add(j_HandWheel, constraints,  2, 5, 1, 1);
		
		j_AboardPrepare.addActionListener(new AboardPrepareListenerDialog());
		j_AutoJudge.addActionListener(new ComJudgeListenerDialog());
		j_ChangLane.addActionListener(new ChangeLaneListenerDialog());
		j_TurnLeft.addActionListener(new TurnLeftListenerDialog());
		j_TurnRight.addActionListener(new TurnRightListenerDialog());
		j_TrunRound.addActionListener(new TurnRoundListenerDialog());
		j_SchoolArea.addActionListener(new SchoolAreaListenerDialog());
		j_BusStation.addActionListener(new BusStationListenerDialog());
		j_FootWayLiner.addActionListener(new FootWayLinerListenerDialog());
		j_MeetingCar.addActionListener(new MeetingCarListenerDialog());
		j_LuKouStraight.addActionListener(new LuKouStraightListenerDialog());
		j_OverCar.addActionListener(new OverCarListenerDialog());
		j_LinerDriving.addActionListener(new LinerDrivingListenerDialog());
		j_PlusSubstractDang.addActionListener(new PlusSubstractDangListenerDialog());
		j_StartCar.addActionListener(new StartCarListenerDialog());
		j_PullOver.addActionListener(new PullOverListenerDialog());
		j_Common.addActionListener(new CommonListenerDialog());
		this.setVisible(true);
	}
}
