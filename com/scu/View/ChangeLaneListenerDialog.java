package com.scu.View;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
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
 * ����������
 * @author ������
 *
 */

public class ChangeLaneListenerDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	private static ImageIconSet allImage = new ImageIconSet();
	private static final int WIDTH = 500;
	private static final int HEIGHT = 450;
	//������
	private static JFrame ChangeFrm;
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
	//ƫת�����
	private JLabel j_offsetAngle;
	private JTextField offsetAngle;
	//����������
	private JLabel j_changeLaneLength;
	private JTextField changeLaneLength;

	//ȡ���ͱ��水ť
	private JButton ok;
	private JButton cancel;
	
	public ChangeLaneListenerDialog() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(ChangeFrm != null){
			ChangeFrm.dispose();
		}
		ChangeFrm = new JFrame("����������öԻ���");
		JPanel contentPanel = new JPanel();
		//AboardFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout lay = new GridBagLayout();
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		ChangeFrm.setSize(WIDTH, HEIGHT);
		ChangeFrm.setContentPane(contentPanel);
		ChangeFrm.setLayout(lay);
		ChangeFrm.setLocation(x, y);
		ChangeFrm.setUndecorated(true);
		ChangeFrm.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		isOpenJudge = new JCheckBox("������������", ConfigManager.changeLane.isOpen());
		
		j_endMode = new JLabel("������ʽ", JLabel.CENTER);
		td = new ButtonGroup();
		timeMode = new JRadioButton("  ��ʱ�����  ");
		distanceMode = new JRadioButton("  ���������  ");
		td.add(timeMode);
		td.add(distanceMode);
		if(ConfigManager.changeLane.getTimeOrDistance() == 1){
			timeMode.setSelected(true);
		}else
		{
			distanceMode.setSelected(true);
		}
		
		
		j_endTime = new JLabel("����ʱ�� ", JLabel.CENTER);
		endTime = new JTextField(new Long(ConfigManager.changeLane.getEndTime() / 1000).toString(),5);
		endTime.setHorizontalAlignment(JTextField.CENTER);
		
		j_endDistance = new JLabel("�������� ", JLabel.CENTER);
		endDistance = new JTextField(new Integer(ConfigManager.changeLane.getEndDistance()).toString(),5);
		endDistance.setHorizontalAlignment(JTextField.CENTER);
		
		j_triggerDistance = new JLabel("�������� ", JLabel.CENTER);
		triggerDistance = new JTextField(new Integer(ConfigManager.changeLane.getTriggerDistance()).toString(),5);
		triggerDistance.setHorizontalAlignment(JTextField.CENTER);
		
		j_offsetAngle = new JLabel("ƫת�����", JLabel.CENTER);
		offsetAngle = new JTextField(new Integer(ConfigManager.changeLane.getOffsetAngle()).toString(), 5);
		offsetAngle.setHorizontalAlignment(JTextField.CENTER);
		
		j_changeLaneLength = new JLabel("����������", JLabel.CENTER);
		changeLaneLength = new JTextField(new Integer(ConfigManager.changeLane.getChangeLaneLength()).toString(),5);
		changeLaneLength.setHorizontalAlignment(JTextField.CENTER);
		
		ok = new MyButton(allImage.getSaveIcon(), allImage.getSave_touchIcon());
		cancel = new MyButton(allImage.getBackIcon(), allImage.getBack_touchIcon());
		
		
		contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 0));
		contentPanel.setLayout(new GridLayout(8, 1, 0, 10));
		
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
		JLabel kilometer = new JLabel("��", JLabel.LEFT);
		panel5.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel5.add(j_offsetAngle);
		panel5.add(offsetAngle);
		panel5.add(kilometer);
		contentPanel.add(panel5);
		
		JPanel panel6 = new JPanel();
		JLabel angle = new JLabel("��", JLabel.LEFT);
		panel6.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel6.add(j_changeLaneLength);
		panel6.add(changeLaneLength);
		panel6.add(angle);
		contentPanel.add(panel6);
		
		
		JPanel panel7 = new JPanel();
		panel7.setLayout(new GridLayout(1, 2, 0, 0));
		panel7.add(ok);
		panel7.add(cancel);
		contentPanel.add(panel7);
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ChangeFrm.dispose();
			}	
		});
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//���������水ť�� �ֱ��ȡÿһ���ؼ���ֵ��Ȼ�����������ļ�
				boolean isOpen = isOpenJudge.isSelected();
				ConfigManager.changeLane.setOpen(isOpen);
				
				boolean timeOrDistance = timeMode.isSelected();
				if(timeOrDistance){
					ConfigManager.changeLane.setTimeOrDistance(1);
				}else
				{
					ConfigManager.changeLane.setTimeOrDistance(0);
				}
				
				String endTimeString = endTime.getText();
				if(StringUtil.isStringEmpty(endTimeString)){
					JOptionPane.showMessageDialog(null, "����ʱ�䲻��Ϊ�գ�");
					return;
				}else
				{
					long endTimeValue = Long.parseLong(endTimeString) * 1000;
					ConfigManager.changeLane.setEndTime(endTimeValue);
				}
				
				String endDistanceString = endDistance.getText();
				if(StringUtil.isStringEmpty(endDistanceString)){
					JOptionPane.showMessageDialog(null, "�������벻��Ϊ�գ�");
					return;
				} else {
					int endDistanceValue = Integer.parseInt(endDistanceString);
					ConfigManager.changeLane.setEndDistance(endDistanceValue);
				}

				String triggerDistanceString = triggerDistance.getText();
				if (StringUtil.isStringEmpty(triggerDistanceString)) {
					JOptionPane.showMessageDialog(null, "�������벻��Ϊ�գ�");
					return;
				} else {
					int triggerDistanceValue = Integer.parseInt(triggerDistanceString);
					ConfigManager.changeLane.setTriggerDistance(triggerDistanceValue);
				}
				
				String offsetAngleString = offsetAngle.getText();
				if (StringUtil.isStringEmpty(offsetAngleString)) {
					JOptionPane.showMessageDialog(null, "ƫת����ǲ���Ϊ�գ�");
					return;
				} else {
					int offsetAngleValue = Integer.parseInt(offsetAngleString);
					ConfigManager.changeLane.setOffsetAngle(offsetAngleValue);
				}
				
				String changeLaneLengthString = changeLaneLength.getText();
				if (StringUtil.isStringEmpty(changeLaneLengthString)) {
					JOptionPane.showMessageDialog(null, "����������벻��Ϊ�գ�");
					return;
				} else {
					int changeLaneLengthValue = Integer.parseInt(changeLaneLengthString);
					ConfigManager.changeLane.setChangeLaneLength(changeLaneLengthValue);
				}

				ChangeFrm.dispose();
			}

		});
		ChangeFrm.setVisible(true);
	}

}

