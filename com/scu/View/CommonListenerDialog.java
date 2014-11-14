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
 * 通用评判配置窗口
 * @author 孙晓雨
 *
 */
public class CommonListenerDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static ImageIconSet allImage = new ImageIconSet();
	private static final int WIDTH = 600;
	private static final int HEIGHT = 550;
	//主窗口
	private static JFrame CommFrm;
	

	//方向灯等待时间标签和单选框
	private JLabel j_turnLightWaitTime;
	private JTextField turnLightWaitTime;
	//发动机怠速标签和单选框
	private JLabel j_engineIdleSpeed;
	private JTextField engineIdleSpeed;
	//最高转速不得超过标签和单选框
	private JLabel j_maxRoundSpeed;
	private JTextField maxRoundSpeed;
	//空挡滑行时间标签和单选框
	private JLabel j_kongDangTime;
	private JTextField kongDangTime;
	//低档高转速重复扣分时间 标签和单选框
	private JLabel j_repeatPoint;
	private JTextField repeatPoint;
	
	// 长时间不关闭转向灯时间标签和单选框
	private JLabel j_openTurnLightTime;
	private JTextField openTurnLightTime;

	// 考试里程标签和单选框
	private JLabel j_examLength;
	private JTextField examLength;
	
	// 全程最高车速标签和单选框
		private JLabel j_maxSpeed;
		private JTextField maxSpeed;
		
		// 档位达到四档的时候标签和单选框
		private JLabel j_Dang;
		private JTextField Dang;
		
		//  车速是否达到标签和单选框
		private JLabel j_SpeedNeed;
		private JTextField SpeedNeed;
	
	
	//取消和保存按钮
	private JButton ok;
	private JButton cancel;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(CommFrm != null){
			CommFrm.dispose();
		}
		CommFrm = new JFrame("通用评判配置对话框");
		JPanel contentPanel = new JPanel();
		//AboardFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		CommFrm.setSize(WIDTH, HEIGHT);
		CommFrm.setUndecorated(true);
		CommFrm.setContentPane(contentPanel);
		CommFrm.setLocation(x, y);
		CommFrm.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		

		
		j_turnLightWaitTime = new JLabel("方向灯等待时间", JLabel.CENTER);
		turnLightWaitTime = new JTextField(new Long(ConfigManager.commonConfig.getTurnLightWaitTime() / 1000).toString(),4);
		turnLightWaitTime.setHorizontalAlignment(JTextField.CENTER);
		
		j_engineIdleSpeed = new JLabel("发动机怠速 ", JLabel.CENTER);
		engineIdleSpeed = new JTextField(new Integer(ConfigManager.commonConfig.getEngineIdleSpeed()).toString(),4);
		engineIdleSpeed.setHorizontalAlignment(JTextField.CENTER);
		
		j_maxRoundSpeed = new JLabel("发动机转速不得超过 ", JLabel.CENTER);
		maxRoundSpeed = new JTextField(new Integer(ConfigManager.commonConfig.getMaxRoundSpeed()).toString(),4);
		maxRoundSpeed.setHorizontalAlignment(JTextField.CENTER);
		
		j_kongDangTime = new JLabel("空挡滑行时间时间", JLabel.CENTER);
		kongDangTime = new JTextField(new Long(ConfigManager.commonConfig.getKongDangTime() / 1000).toString(),4);
		kongDangTime.setHorizontalAlignment(JTextField.CENTER);
		
		j_repeatPoint = new JLabel("低档搞转速重复扣分时间", JLabel.CENTER);
		repeatPoint = new JTextField(new Long(ConfigManager.commonConfig.getRepeatPoint() /1000).toString(),4);
		repeatPoint.setHorizontalAlignment(JTextField.CENTER);
		
		j_openTurnLightTime = new JLabel("长时间不关转向灯时间", JLabel.CENTER);
		openTurnLightTime = new JTextField(new Long(ConfigManager.commonConfig.getOpenTurnLightTime() / 1000).toString(),4);
		openTurnLightTime.setHorizontalAlignment(JTextField.CENTER);
		
		j_examLength = new JLabel("考试里程 ", JLabel.CENTER);
		examLength = new JTextField(new Integer(ConfigManager.commonConfig.getExamLength()).toString(),4);
		examLength.setHorizontalAlignment(JTextField.CENTER);
		
		j_maxSpeed = new JLabel("全程最高车速 ", JLabel.CENTER);
		maxSpeed = new JTextField(new Integer(ConfigManager.commonConfig.getMaxSpeed()).toString(),4);
		maxSpeed.setHorizontalAlignment(JTextField.CENTER);
		
		
		j_Dang = new JLabel("档位需要达到 ", JLabel.CENTER);
		Dang = new JTextField(new Integer(ConfigManager.commonConfig.getDang()).toString(),4);
		Dang.setHorizontalAlignment(JTextField.CENTER);
		
		j_SpeedNeed = new JLabel("档,车速需达到 ", JLabel.CENTER);
		SpeedNeed = new JTextField(new Integer(ConfigManager.commonConfig.getSpeedNeed()).toString(),4);
		SpeedNeed.setHorizontalAlignment(JTextField.CENTER);
		
		ok = new MyButton(allImage.getSaveIcon(), allImage.getSave_touchIcon());
		cancel = new MyButton(allImage.getBackIcon(), allImage.getBack_touchIcon());
		
		contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 0));
		contentPanel.setLayout(new GridLayout(10, 1, 0, 10));
		
		JPanel panel1 = new JPanel();
		JLabel second = new JLabel("秒", JLabel.CENTER);
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel1.add(j_turnLightWaitTime);
		panel1.add(turnLightWaitTime);
		panel1.add(second);
		contentPanel.add(panel1);
		
		JPanel panel2 = new JPanel();
		JLabel second2 = new JLabel("RPM", JLabel.CENTER);
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel2.add(j_engineIdleSpeed);
		panel2.add(engineIdleSpeed);
		panel2.add(second2);
		contentPanel.add(panel2);
		
		JPanel panel3 = new JPanel();
		JLabel second3 = new JLabel("RPM", JLabel.CENTER);
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel3.add(j_maxRoundSpeed);
		panel3.add(maxRoundSpeed);
		panel3.add(second3);
		contentPanel.add(panel3);
		
		JPanel panel4 = new JPanel();
		JLabel second4 = new JLabel("秒", JLabel.CENTER);
		panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel4.add(j_kongDangTime);
		panel4.add(kongDangTime);
		panel4.add(second4);
		contentPanel.add(panel4);
		
		JPanel panel5 = new JPanel();
		JLabel second5 = new JLabel("秒", JLabel.LEFT);
		panel5.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel5.add(j_repeatPoint);
		panel5.add(repeatPoint);
		panel5.add(second5);
		contentPanel.add(panel5);
		
		JPanel panel6 = new JPanel();
		JLabel angle = new JLabel("秒", JLabel.LEFT);
		panel6.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel6.add(j_openTurnLightTime);
		panel6.add(openTurnLightTime);
		panel6.add(angle);
		contentPanel.add(panel6);
		
		JPanel panel7 = new JPanel();
		JLabel angle1 = new JLabel("米", JLabel.LEFT);
		panel7.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel7.add(j_examLength);
		panel7.add(examLength);
		panel7.add(angle1);
		contentPanel.add(panel7);
		
		JPanel panel8 = new JPanel();
		JLabel angle3 = new JLabel("公里/小时", JLabel.LEFT);
		panel8.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel8.add(j_maxSpeed);
		panel8.add(maxSpeed);
		panel8.add(angle3);
		contentPanel.add(panel8);
		
		
		JPanel panel9 = new JPanel();
		JLabel angle4 = new JLabel("公里/小时", JLabel.LEFT);
		panel9.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel9.add(j_Dang);
		panel9.add(Dang);
		panel9.add(j_SpeedNeed);
		panel9.add(SpeedNeed);
		panel9.add(angle4);
		contentPanel.add(panel9);
		
		JPanel panel10 = new JPanel();
		panel10.setLayout(new GridLayout(1, 2, 0, 0));
		panel10.add(ok);
		panel10.add(cancel);
		contentPanel.add(panel10);
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				CommFrm.dispose();
			}	
		});
		
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//如果点击保存按钮， 分别获取每一个控件的值，然后设置配置文件
			
				String turnLightWaitTimeString = turnLightWaitTime.getText();
				if(StringUtil.isStringEmpty(turnLightWaitTimeString)){
					JOptionPane.showMessageDialog(null, "方向灯等待时间不能为空！");
					return;
				}else
				{
					long turnLightWaitTimeValue = Long.parseLong(turnLightWaitTimeString) * 1000;
					ConfigManager.commonConfig.setTurnLightWaitTime(turnLightWaitTimeValue);
				}
				
				String maxRoundSpeedString = maxRoundSpeed.getText();
				if(StringUtil.isStringEmpty(maxRoundSpeedString)){
					JOptionPane.showMessageDialog(null, "最高转速不能为空！");
					return;
				} else {
					int maxRoundSpeedValue = Integer.parseInt(maxRoundSpeedString);
					ConfigManager.commonConfig.setMaxRoundSpeed(maxRoundSpeedValue);;
				}
				
				String engineIdleSpeedString = engineIdleSpeed.getText();
				if(StringUtil.isStringEmpty(engineIdleSpeedString)){
					JOptionPane.showMessageDialog(null, "发动机怠速不能为空！");
					return;
				} else {
					int engineIdleSpeedValue = Integer.parseInt(engineIdleSpeedString);
					ConfigManager.commonConfig.setEngineIdleSpeed(engineIdleSpeedValue);;
				}
				
				String kongDangTimeString = kongDangTime.getText();
				if(StringUtil.isStringEmpty(kongDangTimeString)){
					JOptionPane.showMessageDialog(null, "空挡滑行时间不能为空！");
					return;
				}else
				{
					long kongDangTimeValue = Long.parseLong(kongDangTimeString) * 1000;
					ConfigManager.commonConfig.setKongDangTime(kongDangTimeValue);
				}
				
				String repeatPointString = repeatPoint.getText();
				if(StringUtil.isStringEmpty(repeatPointString)){
					JOptionPane.showMessageDialog(null, "低档高转速重复扣分时间不能为空！");
					return;
				}else
				{
					long repeatPointValue = Long.parseLong(repeatPointString) * 1000;
					ConfigManager.commonConfig.setRepeatPoint(repeatPointValue);
				}
				
				String openTurnLightTimeString = openTurnLightTime.getText();
				if(StringUtil.isStringEmpty(openTurnLightTimeString)){
					JOptionPane.showMessageDialog(null, "长时间不关转向灯时间不能为空！");
					return;
				}else
				{
					long openTurnLightTimeValue = Long.parseLong(openTurnLightTimeString) * 1000;
					ConfigManager.commonConfig.setOpenTurnLightTime(openTurnLightTimeValue);
				}
				
				String examLengthString = examLength.getText();
				if(StringUtil.isStringEmpty(examLengthString)){
					JOptionPane.showMessageDialog(null, "考试里程不能为空！");
					return;
				} else {
					int examLengthValue = Integer.parseInt(examLengthString);
					ConfigManager.commonConfig.setExamLength(examLengthValue);
				}

				String maxSpeedString = maxSpeed.getText();
				if (StringUtil.isStringEmpty(maxSpeedString)) {
					JOptionPane.showMessageDialog(null, "全程最高车速不能为空！");
					return;
				} else {
					int maxSpeedValue = Integer.parseInt(maxSpeedString);
					ConfigManager.commonConfig.setMaxSpeed(maxSpeedValue);
				}
				
				
				String DangString = Dang.getText();
				if (StringUtil.isStringEmpty(DangString)) {
					JOptionPane.showMessageDialog(null, "档位不能为空！");
					return;
				} else {
					int DangValue = Integer.parseInt(DangString);
					ConfigManager.commonConfig.setDang(DangValue);
				} 
				
				
				String SpeedNeedString = SpeedNeed.getText();
				if (StringUtil.isStringEmpty(SpeedNeedString)) {
					JOptionPane.showMessageDialog(null, "车速不能为空！");
					return;
				} else {
					int SpeedNeedValue = Integer.parseInt(SpeedNeedString);
					ConfigManager.commonConfig.setSpeedNeed(SpeedNeedValue);
				} 
				CommFrm.dispose();
			}
		});
		
		
		CommFrm.setVisible(true);
	}
	

}
