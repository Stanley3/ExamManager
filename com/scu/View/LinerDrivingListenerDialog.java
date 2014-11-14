package com.scu.View;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import com.scu.GlobelControl.ConfigManager;
import com.scu.GlobelControl.ImageIconSet;
import com.scu.Model.MyButton;
import com.scu.Utils.GetLocateUtil;
import com.scu.Utils.StringUtil;
/**
 * 直线行驶配置对话框
 * @author 孙晓雨
 *
 */
public class LinerDrivingListenerDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static ImageIconSet allImage = new ImageIconSet();
	private static final int WIDTH = 500;
	private static final int HEIGHT = 450;
	//主窗口
	private static JFrame LineFrm;
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
	//车速标签和单选框
	private JLabel j_minSpeed;
	private JTextField minSpeed;
	private JLabel j_maxSpeed;
	private JTextField maxSpeed;
	//偏转角度标签和单选框
	private JLabel j_angleNum;
	private JTextField angleNum;
	
	//取消和保存按钮
	private JButton ok;
	private JButton cancel;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(LineFrm != null){
			LineFrm.dispose();
		}
		LineFrm = new JFrame("直线行驶配置对话框");
		JPanel contentPanel = new JPanel();
		//AboardFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		LineFrm.setSize(WIDTH, HEIGHT);
		LineFrm.setUndecorated(true);
		LineFrm.setContentPane(contentPanel);
		LineFrm.setLocation(x, y);
		LineFrm.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		
		isOpenJudge = new JCheckBox("开启此项评判", ConfigManager.linerDriving.isOpen());
		
		j_endMode = new JLabel("结束方式", JLabel.CENTER);
		td = new ButtonGroup();
		timeMode = new JRadioButton("按时间结束");
		distanceMode = new JRadioButton("按距离结束");
		td.add(timeMode);
		td.add(distanceMode);
		if(ConfigManager.linerDriving.getTimeOrDistance() == 1){
			timeMode.setSelected(true);
		}else
		{
			distanceMode.setSelected(true);
		}
		
		
		j_endTime = new JLabel("结束时间 ", JLabel.CENTER);
		endTime = new JTextField(new Long(ConfigManager.linerDriving.getEndTime() / 1000).toString(),4);
		endTime.setHorizontalAlignment(JTextField.CENTER);
		
		j_endDistance = new JLabel("结束距离 ", JLabel.CENTER);
		endDistance = new JTextField(new Integer(ConfigManager.linerDriving.getEndDistance()).toString(),4);
		endDistance.setHorizontalAlignment(JTextField.CENTER);
		
		j_triggerDistance = new JLabel("触发距离 ", JLabel.CENTER);
		triggerDistance = new JTextField(new Integer(ConfigManager.linerDriving.getTriggerDistance()).toString(),4);
		triggerDistance.setHorizontalAlignment(JTextField.CENTER);
		
		j_minSpeed = new JLabel("最低车速 ", JLabel.CENTER);
		minSpeed = new JTextField(new Integer(ConfigManager.linerDriving.getMinSpeed()).toString(),4);
		
		minSpeed.setHorizontalAlignment(JTextField.CENTER);
		j_maxSpeed = new JLabel("最高车速 ", JLabel.CENTER);
		maxSpeed = new JTextField(new Integer(ConfigManager.linerDriving.getMaxSpeed()).toString(),4);
		maxSpeed.setHorizontalAlignment(JTextField.CENTER);
		
		j_angleNum = new JLabel("偏转方向角", JLabel.CENTER);
		angleNum = new JTextField(new Integer(ConfigManager.linerDriving.getAngleNum()).toString(),4);
		angleNum.setHorizontalAlignment(JTextField.CENTER);
		
		ok = new MyButton(allImage.getSaveIcon(), allImage.getSave_touchIcon());
		cancel = new MyButton(allImage.getBackIcon(), allImage.getBack_touchIcon());
		
		contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 0));
		contentPanel.setLayout(new GridLayout(9, 1, 0, 10));
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
		JLabel kilometer1 = new JLabel("公里/小时", JLabel.LEFT);
		panel5.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel5.add(j_minSpeed);
		panel5.add(minSpeed);
		panel5.add(kilometer1);
		contentPanel.add(panel5);
		
		JPanel panel6 = new JPanel();
		JLabel kilometer2 = new JLabel("公里/小时", JLabel.LEFT);
		panel6.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel6.add(j_maxSpeed);
		panel6.add(maxSpeed);
		panel6.add(kilometer2);
		contentPanel.add(panel5);
		
		JPanel panel7 = new JPanel();
		JLabel angle = new JLabel("度", JLabel.LEFT);
		panel7.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel7.add(j_angleNum);
		panel7.add(angleNum);
		panel7.add(angle);
		contentPanel.add(panel7);
		
		
		JPanel panel8 = new JPanel();
		panel8.setLayout(new GridLayout(1, 2, 0, 0));
		panel8.add(ok);
		panel8.add(cancel);
		contentPanel.add(panel8);
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				LineFrm.dispose();
			}	
		});
		
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//如果点击保存按钮， 分别获取每一个控件的值，然后设置配置文件
				boolean isOpen = isOpenJudge.isSelected();
				ConfigManager.linerDriving.setOpen(isOpen);
				
				boolean timeOrDistance = timeMode.isSelected();
				if(timeOrDistance){
					ConfigManager.linerDriving.setTimeOrDistance(1);
				}else
				{
					ConfigManager.linerDriving.setTimeOrDistance(0);
				}
				
				String endTimeString = endTime.getText();
				if(StringUtil.isStringEmpty(endTimeString)){
					JOptionPane.showMessageDialog(null, "结束时间不能为空！");
					return;
				}else
				{
					long endTimeValue = Long.parseLong(endTimeString) * 1000;
					ConfigManager.linerDriving.setEndTime(endTimeValue);
				}
				
				String endDistanceString = endDistance.getText();
				if(StringUtil.isStringEmpty(endDistanceString)){
					JOptionPane.showMessageDialog(null, "结束距离不能为空！");
					return;
				} else {
					int endDistanceValue = Integer.parseInt(endDistanceString);
					ConfigManager.linerDriving.setEndDistance(endDistanceValue);
				}

				String triggerDistanceString = triggerDistance.getText();
				if (StringUtil.isStringEmpty(triggerDistanceString)) {
					JOptionPane.showMessageDialog(null, "触发距离不能为空！");
					return;
				} else {
					int triggerDistanceValue = Integer.parseInt(triggerDistanceString);
					ConfigManager.linerDriving.setTriggerDistance(triggerDistanceValue);
				}
				
				String minSpeedString = minSpeed.getText();
				if (StringUtil.isStringEmpty(minSpeedString)) {
					JOptionPane.showMessageDialog(null, "最低车速不能为空！");
					return;
				} else {
					int minSpeedValue = Integer.parseInt(minSpeedString);
					ConfigManager.linerDriving.setMinSpeed(minSpeedValue);
				} 
				
				String maxSpeedString = maxSpeed.getText();
				if (StringUtil.isStringEmpty(maxSpeedString)) {
					JOptionPane.showMessageDialog(null, "最高车速不能为空！");
					return;
				} else {
					int maxSpeedValue = Integer.parseInt(maxSpeedString);
					ConfigManager.linerDriving.setMaxSpeed(maxSpeedValue);
				} 
				
				
				String angleNumString = angleNum.getText();
				if (StringUtil.isStringEmpty(angleNumString)) {
					JOptionPane.showMessageDialog(null, "偏转方向角不能为空！");
					return;
				} else {
					int angleNumValue = Integer.parseInt(angleNumString);
					ConfigManager.linerDriving.setAngleNum(angleNumValue);
				} 
				LineFrm.dispose();
			}
		});
		LineFrm.setVisible(true);
	}
}
