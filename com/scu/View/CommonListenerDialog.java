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
 * ͨ���������ô���
 * @author ������
 *
 */
public class CommonListenerDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static ImageIconSet allImage = new ImageIconSet();
	private static final int WIDTH = 600;
	private static final int HEIGHT = 550;
	//������
	private static JFrame CommFrm;
	

	//����Ƶȴ�ʱ���ǩ�͵�ѡ��
	private JLabel j_turnLightWaitTime;
	private JTextField turnLightWaitTime;
	//���������ٱ�ǩ�͵�ѡ��
	private JLabel j_engineIdleSpeed;
	private JTextField engineIdleSpeed;
	//���ת�ٲ��ó�����ǩ�͵�ѡ��
	private JLabel j_maxRoundSpeed;
	private JTextField maxRoundSpeed;
	//�յ�����ʱ���ǩ�͵�ѡ��
	private JLabel j_kongDangTime;
	private JTextField kongDangTime;
	//�͵���ת���ظ��۷�ʱ�� ��ǩ�͵�ѡ��
	private JLabel j_repeatPoint;
	private JTextField repeatPoint;
	
	// ��ʱ�䲻�ر�ת���ʱ���ǩ�͵�ѡ��
	private JLabel j_openTurnLightTime;
	private JTextField openTurnLightTime;

	// ������̱�ǩ�͵�ѡ��
	private JLabel j_examLength;
	private JTextField examLength;
	
	// ȫ����߳��ٱ�ǩ�͵�ѡ��
		private JLabel j_maxSpeed;
		private JTextField maxSpeed;
		
		// ��λ�ﵽ�ĵ���ʱ���ǩ�͵�ѡ��
		private JLabel j_Dang;
		private JTextField Dang;
		
		//  �����Ƿ�ﵽ��ǩ�͵�ѡ��
		private JLabel j_SpeedNeed;
		private JTextField SpeedNeed;
	
	
	//ȡ���ͱ��水ť
	private JButton ok;
	private JButton cancel;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(CommFrm != null){
			CommFrm.dispose();
		}
		CommFrm = new JFrame("ͨ���������öԻ���");
		JPanel contentPanel = new JPanel();
		//AboardFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		CommFrm.setSize(WIDTH, HEIGHT);
		CommFrm.setUndecorated(true);
		CommFrm.setContentPane(contentPanel);
		CommFrm.setLocation(x, y);
		CommFrm.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		

		
		j_turnLightWaitTime = new JLabel("����Ƶȴ�ʱ��", JLabel.CENTER);
		turnLightWaitTime = new JTextField(new Long(ConfigManager.commonConfig.getTurnLightWaitTime() / 1000).toString(),4);
		turnLightWaitTime.setHorizontalAlignment(JTextField.CENTER);
		
		j_engineIdleSpeed = new JLabel("���������� ", JLabel.CENTER);
		engineIdleSpeed = new JTextField(new Integer(ConfigManager.commonConfig.getEngineIdleSpeed()).toString(),4);
		engineIdleSpeed.setHorizontalAlignment(JTextField.CENTER);
		
		j_maxRoundSpeed = new JLabel("������ת�ٲ��ó��� ", JLabel.CENTER);
		maxRoundSpeed = new JTextField(new Integer(ConfigManager.commonConfig.getMaxRoundSpeed()).toString(),4);
		maxRoundSpeed.setHorizontalAlignment(JTextField.CENTER);
		
		j_kongDangTime = new JLabel("�յ�����ʱ��ʱ��", JLabel.CENTER);
		kongDangTime = new JTextField(new Long(ConfigManager.commonConfig.getKongDangTime() / 1000).toString(),4);
		kongDangTime.setHorizontalAlignment(JTextField.CENTER);
		
		j_repeatPoint = new JLabel("�͵���ת���ظ��۷�ʱ��", JLabel.CENTER);
		repeatPoint = new JTextField(new Long(ConfigManager.commonConfig.getRepeatPoint() /1000).toString(),4);
		repeatPoint.setHorizontalAlignment(JTextField.CENTER);
		
		j_openTurnLightTime = new JLabel("��ʱ�䲻��ת���ʱ��", JLabel.CENTER);
		openTurnLightTime = new JTextField(new Long(ConfigManager.commonConfig.getOpenTurnLightTime() / 1000).toString(),4);
		openTurnLightTime.setHorizontalAlignment(JTextField.CENTER);
		
		j_examLength = new JLabel("������� ", JLabel.CENTER);
		examLength = new JTextField(new Integer(ConfigManager.commonConfig.getExamLength()).toString(),4);
		examLength.setHorizontalAlignment(JTextField.CENTER);
		
		j_maxSpeed = new JLabel("ȫ����߳��� ", JLabel.CENTER);
		maxSpeed = new JTextField(new Integer(ConfigManager.commonConfig.getMaxSpeed()).toString(),4);
		maxSpeed.setHorizontalAlignment(JTextField.CENTER);
		
		
		j_Dang = new JLabel("��λ��Ҫ�ﵽ ", JLabel.CENTER);
		Dang = new JTextField(new Integer(ConfigManager.commonConfig.getDang()).toString(),4);
		Dang.setHorizontalAlignment(JTextField.CENTER);
		
		j_SpeedNeed = new JLabel("��,������ﵽ ", JLabel.CENTER);
		SpeedNeed = new JTextField(new Integer(ConfigManager.commonConfig.getSpeedNeed()).toString(),4);
		SpeedNeed.setHorizontalAlignment(JTextField.CENTER);
		
		ok = new MyButton(allImage.getSaveIcon(), allImage.getSave_touchIcon());
		cancel = new MyButton(allImage.getBackIcon(), allImage.getBack_touchIcon());
		
		contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 0));
		contentPanel.setLayout(new GridLayout(10, 1, 0, 10));
		
		JPanel panel1 = new JPanel();
		JLabel second = new JLabel("��", JLabel.CENTER);
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
		JLabel second4 = new JLabel("��", JLabel.CENTER);
		panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel4.add(j_kongDangTime);
		panel4.add(kongDangTime);
		panel4.add(second4);
		contentPanel.add(panel4);
		
		JPanel panel5 = new JPanel();
		JLabel second5 = new JLabel("��", JLabel.LEFT);
		panel5.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel5.add(j_repeatPoint);
		panel5.add(repeatPoint);
		panel5.add(second5);
		contentPanel.add(panel5);
		
		JPanel panel6 = new JPanel();
		JLabel angle = new JLabel("��", JLabel.LEFT);
		panel6.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel6.add(j_openTurnLightTime);
		panel6.add(openTurnLightTime);
		panel6.add(angle);
		contentPanel.add(panel6);
		
		JPanel panel7 = new JPanel();
		JLabel angle1 = new JLabel("��", JLabel.LEFT);
		panel7.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel7.add(j_examLength);
		panel7.add(examLength);
		panel7.add(angle1);
		contentPanel.add(panel7);
		
		JPanel panel8 = new JPanel();
		JLabel angle3 = new JLabel("����/Сʱ", JLabel.LEFT);
		panel8.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel8.add(j_maxSpeed);
		panel8.add(maxSpeed);
		panel8.add(angle3);
		contentPanel.add(panel8);
		
		
		JPanel panel9 = new JPanel();
		JLabel angle4 = new JLabel("����/Сʱ", JLabel.LEFT);
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
				//���������水ť�� �ֱ��ȡÿһ���ؼ���ֵ��Ȼ�����������ļ�
			
				String turnLightWaitTimeString = turnLightWaitTime.getText();
				if(StringUtil.isStringEmpty(turnLightWaitTimeString)){
					JOptionPane.showMessageDialog(null, "����Ƶȴ�ʱ�䲻��Ϊ�գ�");
					return;
				}else
				{
					long turnLightWaitTimeValue = Long.parseLong(turnLightWaitTimeString) * 1000;
					ConfigManager.commonConfig.setTurnLightWaitTime(turnLightWaitTimeValue);
				}
				
				String maxRoundSpeedString = maxRoundSpeed.getText();
				if(StringUtil.isStringEmpty(maxRoundSpeedString)){
					JOptionPane.showMessageDialog(null, "���ת�ٲ���Ϊ�գ�");
					return;
				} else {
					int maxRoundSpeedValue = Integer.parseInt(maxRoundSpeedString);
					ConfigManager.commonConfig.setMaxRoundSpeed(maxRoundSpeedValue);;
				}
				
				String engineIdleSpeedString = engineIdleSpeed.getText();
				if(StringUtil.isStringEmpty(engineIdleSpeedString)){
					JOptionPane.showMessageDialog(null, "���������ٲ���Ϊ�գ�");
					return;
				} else {
					int engineIdleSpeedValue = Integer.parseInt(engineIdleSpeedString);
					ConfigManager.commonConfig.setEngineIdleSpeed(engineIdleSpeedValue);;
				}
				
				String kongDangTimeString = kongDangTime.getText();
				if(StringUtil.isStringEmpty(kongDangTimeString)){
					JOptionPane.showMessageDialog(null, "�յ�����ʱ�䲻��Ϊ�գ�");
					return;
				}else
				{
					long kongDangTimeValue = Long.parseLong(kongDangTimeString) * 1000;
					ConfigManager.commonConfig.setKongDangTime(kongDangTimeValue);
				}
				
				String repeatPointString = repeatPoint.getText();
				if(StringUtil.isStringEmpty(repeatPointString)){
					JOptionPane.showMessageDialog(null, "�͵���ת���ظ��۷�ʱ�䲻��Ϊ�գ�");
					return;
				}else
				{
					long repeatPointValue = Long.parseLong(repeatPointString) * 1000;
					ConfigManager.commonConfig.setRepeatPoint(repeatPointValue);
				}
				
				String openTurnLightTimeString = openTurnLightTime.getText();
				if(StringUtil.isStringEmpty(openTurnLightTimeString)){
					JOptionPane.showMessageDialog(null, "��ʱ�䲻��ת���ʱ�䲻��Ϊ�գ�");
					return;
				}else
				{
					long openTurnLightTimeValue = Long.parseLong(openTurnLightTimeString) * 1000;
					ConfigManager.commonConfig.setOpenTurnLightTime(openTurnLightTimeValue);
				}
				
				String examLengthString = examLength.getText();
				if(StringUtil.isStringEmpty(examLengthString)){
					JOptionPane.showMessageDialog(null, "������̲���Ϊ�գ�");
					return;
				} else {
					int examLengthValue = Integer.parseInt(examLengthString);
					ConfigManager.commonConfig.setExamLength(examLengthValue);
				}

				String maxSpeedString = maxSpeed.getText();
				if (StringUtil.isStringEmpty(maxSpeedString)) {
					JOptionPane.showMessageDialog(null, "ȫ����߳��ٲ���Ϊ�գ�");
					return;
				} else {
					int maxSpeedValue = Integer.parseInt(maxSpeedString);
					ConfigManager.commonConfig.setMaxSpeed(maxSpeedValue);
				}
				
				
				String DangString = Dang.getText();
				if (StringUtil.isStringEmpty(DangString)) {
					JOptionPane.showMessageDialog(null, "��λ����Ϊ�գ�");
					return;
				} else {
					int DangValue = Integer.parseInt(DangString);
					ConfigManager.commonConfig.setDang(DangValue);
				} 
				
				
				String SpeedNeedString = SpeedNeed.getText();
				if (StringUtil.isStringEmpty(SpeedNeedString)) {
					JOptionPane.showMessageDialog(null, "���ٲ���Ϊ�գ�");
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
