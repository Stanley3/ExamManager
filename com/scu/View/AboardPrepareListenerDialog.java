package com.scu.View;

import java.awt.FlowLayout;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.scu.GlobelControl.ConfigManager;
import com.scu.GlobelControl.ImageIconSet;
import com.scu.Model.MyButton;
import com.scu.Utils.GetLocateUtil;
import com.scu.Utils.StringUtil;
/**
 * 上车准备按钮监听器, 内部弹出一个设置对话框
 * @author 孙晓雨
 */

public class AboardPrepareListenerDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private static ImageIconSet allImage = ConfigManager.allImage;
	private static final int WIDTH = 500;
	private static final int HEIGHT = 450;
	//主窗口
	private static JFrame AboardFrm;
	//是否开启此项评判复选框
	private JCheckBox isOpenJudge;
	
	//结束方式 标签和单选框
	private JLabel j_endMode;
	private JRadioButton timeMode;
	private JRadioButton distanceMode;
	private ButtonGroup td;
	//结束时间标签和单选框
	private JLabel j_endTime;
	private JTextField endTime;
	//结束距离标签和单选框
	private JLabel j_endDistance;
	private JTextField endDistance;
	//触发距离标签和单选框
	private JLabel j_triggerDistance;
	private JTextField triggerDistance;
	//绕车顺序 标签和单选框
	private JLabel j_aroundTurn;
	private JRadioButton fblf;//先车后后车前
	private JRadioButton fflb;//先车前后车后
	private ButtonGroup fb;
	//取消和保存按钮
	private JButton ok;
	private JButton cancel;
	
	public AboardPrepareListenerDialog() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(AboardFrm != null){
			AboardFrm.dispose();
		}
		AboardFrm = new JFrame("上车准备配置对话框");
		JPanel contentPanel = new JPanel();
		//AboardFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		AboardFrm.setSize(WIDTH, HEIGHT);
		AboardFrm.setUndecorated(true);
		AboardFrm.setContentPane(contentPanel);
		AboardFrm.setLocation(x, y);
		AboardFrm.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		
		isOpenJudge = new JCheckBox("开启此项评判", ConfigManager.abordPrepare.isOpen());
		
		j_endMode = new JLabel("结束方式", JLabel.CENTER);
		td = new ButtonGroup();
		timeMode = new JRadioButton("按时间结束");
		distanceMode = new JRadioButton("按距离结束");
		td.add(timeMode);
		td.add(distanceMode);
		if(ConfigManager.abordPrepare.getTimeOrDistance() == 1){
			timeMode.setSelected(true);
		}else
		{
			distanceMode.setSelected(true);
		}
		
		
		j_endTime = new JLabel("结束时间 ", JLabel.CENTER);
		endTime = new JTextField(new Long(ConfigManager.abordPrepare.getEndTime() / 1000).toString(),4);
		endTime.setHorizontalAlignment(JTextField.CENTER);
		
		j_endDistance = new JLabel("结束距离 ", JLabel.CENTER);
		endDistance = new JTextField(new Integer(ConfigManager.abordPrepare.getEndDistance()).toString(),4);
		endDistance.setHorizontalAlignment(JTextField.CENTER);
		
		j_triggerDistance = new JLabel("触发距离 ", JLabel.CENTER);
		triggerDistance = new JTextField(new Integer(ConfigManager.abordPrepare.getTriggerDistance()).toString(),4);
		triggerDistance.setHorizontalAlignment(JTextField.CENTER);
		
		j_aroundTurn = new JLabel("绕车顺序:", JLabel.CENTER);
		fb = new ButtonGroup();
		fblf = new JRadioButton("先车后，再车前");
		fflb = new JRadioButton("先车前，再车后");
		fb.add(fblf);
		fb.add(fflb);
		if(ConfigManager.abordPrepare.getAround() == 1){
			fblf.setSelected(true);
		}else{
			fflb.setSelected(true);
		}
		
		ok = new MyButton(allImage.getSaveIcon(), allImage.getSave_touchIcon());
		cancel = new MyButton(allImage.getBackIcon(), allImage.getBack_touchIcon());
		
		contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 0));
		contentPanel.setLayout(new GridLayout(7, 1, 0, 10));
	//	isOpenJudge.setMargin(new Insets(0, 50, 0, 0));
		contentPanel.add(isOpenJudge);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel1.add(j_endMode);
		panel1.add(timeMode);
		panel1.add(distanceMode);
		contentPanel.add(panel1);
		
		JPanel panel2 = new JPanel();
		JLabel second = new JLabel("秒", JLabel.CENTER);
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel2.add(j_endTime);
		panel2.add(endTime);
		panel2.add(second);
		contentPanel.add(panel2);
		
		JPanel panel3 = new JPanel();
		JLabel meter1 = new JLabel("米", JLabel.CENTER);
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel3.add(j_endDistance);
		panel3.add(endDistance);
		panel3.add(meter1);
		contentPanel.add(panel3);
		
		JPanel panel4 = new JPanel();
		JLabel meter2 = new JLabel("米", JLabel.CENTER);
		panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel4.add(j_triggerDistance);
		panel4.add(triggerDistance);
		panel4.add(meter2);
		contentPanel.add(panel4);
		
		JPanel panel5 = new JPanel();
		panel5.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel5.add(j_aroundTurn);
		panel5.add(fblf);
		panel5.add(fflb);
		contentPanel.add(panel5);
		

		JPanel panel6 = new JPanel();
		panel6.setLayout(new GridLayout(1, 2, 0, 0));
		panel6.add(ok);
		panel6.add(cancel);
		contentPanel.add(panel6);
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AboardFrm.dispose();
			}	
		});
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//如果点击保存按钮， 分别获取每一个控件的值，然后设置配置文件
				boolean isOpen = isOpenJudge.isSelected();
				ConfigManager.abordPrepare.setOpen(isOpen);
				
				boolean timeOrDistance = timeMode.isSelected();
				if(timeOrDistance){
					ConfigManager.abordPrepare.setTimeOrDistance(1);
				}else
				{
					ConfigManager.abordPrepare.setTimeOrDistance(0);
				}
				
				String endTimeString = endTime.getText();
				if(StringUtil.isStringEmpty(endTimeString)){
					JOptionPane.showMessageDialog(null, "结束时间不能为空！");
					return;
				}else
				{
					Long endTimeValue = Long.parseLong(endTimeString ) *1000;
					ConfigManager.abordPrepare.setEndTime(endTimeValue);
				}
				
				String endDistanceString = endDistance.getText();
				if(StringUtil.isStringEmpty(endDistanceString)){
					JOptionPane.showMessageDialog(null, "结束距离不能为空！");
					return;
				} else {
					int endDistanceValue = Integer.parseInt(endDistanceString);
					ConfigManager.abordPrepare.setEndDistance(endDistanceValue);
				}

				String triggerDistanceString = triggerDistance.getText();
				if (StringUtil.isStringEmpty(triggerDistanceString)) {
					JOptionPane.showMessageDialog(null, "触发距离不能为空！");
					return;
				} else {
					int triggerDistanceValue = Integer.parseInt(triggerDistanceString);
					ConfigManager.abordPrepare.setTriggerDistance(triggerDistanceValue);
				}
				
				boolean aroundTurn = fblf.isSelected();
				if(aroundTurn){
					ConfigManager.abordPrepare.setAround(1);
				}else
				{
					ConfigManager.abordPrepare.setAround(0);
				}
				
				AboardFrm.dispose();
			}

		});
		AboardFrm.setVisible(true);
	}

}
