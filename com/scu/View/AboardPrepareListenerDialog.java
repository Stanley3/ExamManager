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
 * �ϳ�׼����ť������, �ڲ�����һ�����öԻ���
 * @author ������
 */

public class AboardPrepareListenerDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private static ImageIconSet allImage = ConfigManager.allImage;
	private static final int WIDTH = 500;
	private static final int HEIGHT = 450;
	//������
	private static JFrame AboardFrm;
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
	//�Ƴ�˳�� ��ǩ�͵�ѡ��
	private JLabel j_aroundTurn;
	private JRadioButton fblf;//�ȳ����ǰ
	private JRadioButton fflb;//�ȳ�ǰ�󳵺�
	private ButtonGroup fb;
	//ȡ���ͱ��水ť
	private JButton ok;
	private JButton cancel;
	
	public AboardPrepareListenerDialog() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(AboardFrm != null){
			AboardFrm.dispose();
		}
		AboardFrm = new JFrame("�ϳ�׼�����öԻ���");
		JPanel contentPanel = new JPanel();
		//AboardFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		AboardFrm.setSize(WIDTH, HEIGHT);
		AboardFrm.setUndecorated(true);
		AboardFrm.setContentPane(contentPanel);
		AboardFrm.setLocation(x, y);
		AboardFrm.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		
		isOpenJudge = new JCheckBox("������������", ConfigManager.abordPrepare.isOpen());
		
		j_endMode = new JLabel("������ʽ", JLabel.CENTER);
		td = new ButtonGroup();
		timeMode = new JRadioButton("��ʱ�����");
		distanceMode = new JRadioButton("���������");
		td.add(timeMode);
		td.add(distanceMode);
		if(ConfigManager.abordPrepare.getTimeOrDistance() == 1){
			timeMode.setSelected(true);
		}else
		{
			distanceMode.setSelected(true);
		}
		
		
		j_endTime = new JLabel("����ʱ�� ", JLabel.CENTER);
		endTime = new JTextField(new Long(ConfigManager.abordPrepare.getEndTime() / 1000).toString(),4);
		endTime.setHorizontalAlignment(JTextField.CENTER);
		
		j_endDistance = new JLabel("�������� ", JLabel.CENTER);
		endDistance = new JTextField(new Integer(ConfigManager.abordPrepare.getEndDistance()).toString(),4);
		endDistance.setHorizontalAlignment(JTextField.CENTER);
		
		j_triggerDistance = new JLabel("�������� ", JLabel.CENTER);
		triggerDistance = new JTextField(new Integer(ConfigManager.abordPrepare.getTriggerDistance()).toString(),4);
		triggerDistance.setHorizontalAlignment(JTextField.CENTER);
		
		j_aroundTurn = new JLabel("�Ƴ�˳��:", JLabel.CENTER);
		fb = new ButtonGroup();
		fblf = new JRadioButton("�ȳ����ٳ�ǰ");
		fflb = new JRadioButton("�ȳ�ǰ���ٳ���");
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
				//���������水ť�� �ֱ��ȡÿһ���ؼ���ֵ��Ȼ�����������ļ�
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
					JOptionPane.showMessageDialog(null, "����ʱ�䲻��Ϊ�գ�");
					return;
				}else
				{
					Long endTimeValue = Long.parseLong(endTimeString ) *1000;
					ConfigManager.abordPrepare.setEndTime(endTimeValue);
				}
				
				String endDistanceString = endDistance.getText();
				if(StringUtil.isStringEmpty(endDistanceString)){
					JOptionPane.showMessageDialog(null, "�������벻��Ϊ�գ�");
					return;
				} else {
					int endDistanceValue = Integer.parseInt(endDistanceString);
					ConfigManager.abordPrepare.setEndDistance(endDistanceValue);
				}

				String triggerDistanceString = triggerDistance.getText();
				if (StringUtil.isStringEmpty(triggerDistanceString)) {
					JOptionPane.showMessageDialog(null, "�������벻��Ϊ�գ�");
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
