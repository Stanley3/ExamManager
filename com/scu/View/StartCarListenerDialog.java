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

public class StartCarListenerDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	private static ImageIconSet allImage = new ImageIconSet();
	private static final int WIDTH = 500;
	private static final int HEIGHT = 450;
	//������
	private static JFrame StartCarFrm;
	//�Ƿ����������и�ѡ��
	private JCheckBox isOpenJudge;
	
	//������ʽ ��ǩ�͵�ѡ��
	private JLabel j_endMode;
	private JRadioButton timeMode;
	private JRadioButton distanceMode;
	private ButtonGroup td;
	//����ʱ���ǩ�͵�ѡ��
	private JLabel j_endTime;
	private JTextField endTime;
	//���������ǩ�͵�ѡ��
	private JLabel j_endDistance;
	private JTextField endDistance;
	//���������ǩ�͵�ѡ��
	private JLabel j_triggerDistance;
	private JTextField triggerDistance;
	//�����𲽿۷ֵ����ޱ�ǩ�͵�ѡ��
	private JLabel j_minEngineIdleSpeed;
	private JTextField minEngineIdleSpeed;
	//����������ת�ٹ��߱�ǩ�͵�ѡ��
	private JLabel j_startMaxRoundSpeed;
	private JTextField startMaxRoundSpeed;
	
	//����ɲ�������ǩ�͵�ѡ��
	private JLabel j_maxTime;
	private JTextField maxTime;
	//ȡ���ͱ��水ť
	private JButton ok;
	private JButton cancel;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(StartCarFrm != null){
			StartCarFrm.dispose();
		}
		StartCarFrm = new JFrame("�����öԻ���");
		JPanel contentPanel = new JPanel();
		//AboardFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		StartCarFrm.setSize(WIDTH, HEIGHT);
		StartCarFrm.setUndecorated(true);
		StartCarFrm.setContentPane(contentPanel);
		StartCarFrm.setLocation(x, y);
		StartCarFrm.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		
		isOpenJudge = new JCheckBox("������������", ConfigManager.startCar.isOpen());
		
		j_endMode = new JLabel("������ʽ", JLabel.CENTER);
		td = new ButtonGroup();
		timeMode = new JRadioButton("��ʱ�����");
		distanceMode = new JRadioButton("���������");
		td.add(timeMode);
		td.add(distanceMode);
		if(ConfigManager.startCar.getTimeOrDistance() == 1){
			timeMode.setSelected(true);
		}else
		{
			distanceMode.setSelected(true);
		}
		
		
		j_endTime = new JLabel("����ʱ�� ", JLabel.CENTER);
		endTime = new JTextField(new Long(ConfigManager.startCar.getEndTime() / 1000).toString(),4);
		endTime.setHorizontalAlignment(JTextField.CENTER);
		
		j_endDistance = new JLabel("�������� ", JLabel.CENTER);
		endDistance = new JTextField(new Integer(ConfigManager.startCar.getEndDistance()).toString(),4);
		endDistance.setHorizontalAlignment(JTextField.CENTER);
		
		j_triggerDistance = new JLabel("�������� ", JLabel.CENTER);
		triggerDistance = new JTextField(new Integer(ConfigManager.startCar.getTriggerDistance()).toString(),4);
		triggerDistance.setHorizontalAlignment(JTextField.CENTER);
		
		j_minEngineIdleSpeed = new JLabel("�����𲽿۷�ת������ ", JLabel.CENTER);
		minEngineIdleSpeed = new JTextField(new Integer(ConfigManager.startCar.getMinEngineIdleSpeed()).toString(),4);
		minEngineIdleSpeed.setHorizontalAlignment(JTextField.CENTER);
		
		j_startMaxRoundSpeed = new JLabel("����������ת�ٹ���", JLabel.CENTER);
		startMaxRoundSpeed = new JTextField(new Integer(ConfigManager.startCar.getStartMaxRoundSpeed()).toString(), 4);
		startMaxRoundSpeed.setHorizontalAlignment(JTextField.CENTER);
		
		j_maxTime = new JLabel("����ɲ��", JLabel.CENTER);
		maxTime = new JTextField(new Long(ConfigManager.startCar.getMaxTime() /1000).toString(), 4);
		maxTime.setHorizontalAlignment(JTextField.CENTER);
		
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
		JLabel second = new JLabel("��", JLabel.CENTER);
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel2.add(j_endTime);
		panel2.add(endTime);
		panel2.add(second);
		contentPanel.add(panel2);
		
		JPanel panel3 = new JPanel();
		JLabel meter1 = new JLabel("��", JLabel.CENTER);
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel3.add(j_endDistance);
		panel3.add(endDistance);
		panel3.add(meter1);
		contentPanel.add(panel3);
		
		JPanel panel4 = new JPanel();
		JLabel meter2 = new JLabel("��", JLabel.CENTER);
		panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel4.add(j_triggerDistance);
		panel4.add(triggerDistance);
		panel4.add(meter2);
		contentPanel.add(panel4);
		
		JPanel panel5 = new JPanel();
		JLabel kilometer = new JLabel("RPM", JLabel.LEFT);
		panel5.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel5.add(j_minEngineIdleSpeed);
		panel5.add(minEngineIdleSpeed);
		panel5.add(kilometer);
		contentPanel.add(panel5);
		
		JPanel panel6 = new JPanel();
		JLabel rpm = new JLabel("RPM", JLabel.LEFT);
		panel6.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel6.add(j_startMaxRoundSpeed);
		panel6.add(startMaxRoundSpeed);
		panel6.add(rpm);
		contentPanel.add(panel6);
		
		JPanel panel7 = new JPanel();
		JLabel miao = new JLabel("�복��δ���۷�", JLabel.LEFT);
		panel7.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel7.add(j_maxTime);
		panel7.add(maxTime);
		panel7.add(miao);
		contentPanel.add(panel7);
		
		
		JPanel panel8 = new JPanel();
		panel8.setLayout(new GridLayout(1, 2, 0, 0));
		panel8.add(ok);
		panel8.add(cancel);
		contentPanel.add(panel8);
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				StartCarFrm.dispose();
			}	
		});
		
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//���������水ť�� �ֱ��ȡÿһ���ؼ���ֵ��Ȼ�����������ļ�
				boolean isOpen = isOpenJudge.isSelected();
				ConfigManager.startCar.setOpen(isOpen);
				
				boolean timeOrDistance = timeMode.isSelected();
				if(timeOrDistance){
					ConfigManager.startCar.setTimeOrDistance(1);
				}else
				{
					ConfigManager.startCar.setTimeOrDistance(0);
				}
				
				String endTimeString = endTime.getText();
				if(StringUtil.isStringEmpty(endTimeString)){
					JOptionPane.showMessageDialog(null, "����ʱ�䲻��Ϊ�գ�");
					return;
				}else
				{
					long endTimeValue = Long.parseLong(endTimeString) * 1000;
					ConfigManager.startCar.setEndTime(endTimeValue);
				}
				
				String endDistanceString = endDistance.getText();
				if(StringUtil.isStringEmpty(endDistanceString)){
					JOptionPane.showMessageDialog(null, "�������벻��Ϊ�գ�");
					return;
				} else {
					int endDistanceValue = Integer.parseInt(endDistanceString);
					ConfigManager.startCar.setEndDistance(endDistanceValue);
				}

				String triggerDistanceString = triggerDistance.getText();
				if (StringUtil.isStringEmpty(triggerDistanceString)) {
					JOptionPane.showMessageDialog(null, "�������벻��Ϊ�գ�");
					return;
				} else {
					int triggerDistanceValue = Integer.parseInt(triggerDistanceString);
					ConfigManager.startCar.setTriggerDistance(triggerDistanceValue);
				}
				
				String minEngineIdleSpeedString = minEngineIdleSpeed.getText();
				if (StringUtil.isStringEmpty(minEngineIdleSpeedString)) {
					JOptionPane.showMessageDialog(null, "�����𲽿۷�ת�����޲���Ϊ�գ�");
					return;
				} else {
					int minEngineIdleSpeedValue = Integer.parseInt(minEngineIdleSpeedString);
					ConfigManager.startCar.setMinEngineIdleSpeed(minEngineIdleSpeedValue);
				} 
				
				String startMaxRoundSpeedSpeedString = startMaxRoundSpeed.getText();
				if (StringUtil.isStringEmpty(startMaxRoundSpeedSpeedString)) {
					JOptionPane.showMessageDialog(null, "�𲽷�����ת�ٲ���Ϊ�գ�");
					return;
				} else {
					int startMaxRoundSpeedValue = Integer.parseInt(startMaxRoundSpeedSpeedString);
					ConfigManager.startCar.setStartMaxRoundSpeed(startMaxRoundSpeedValue);
				} 
				
				String maxTimeString = maxTime.getText();
				if (StringUtil.isStringEmpty(maxTimeString)) {
					JOptionPane.showMessageDialog(null, "��ɲʱ�䲻��Ϊ�գ�");
					return;
				} else {
					long maxTimeValue = Long.parseLong(maxTimeString) * 1000;
					ConfigManager.startCar.setMaxTime(maxTimeValue);
				} 
				StartCarFrm.dispose();
			}
		});
		
		StartCarFrm.setVisible(true);
	}

}
