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
 * �Ӽ������ô���
 * @author ������
 *
 */
public class PlusSubstractDangListenerDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	private static ImageIconSet allImage = new ImageIconSet();
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	//������
	private static JFrame PJDangFrm;
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
	//���ٱ�ǩ�͵�ѡ��
	private JLabel j_MaxDang1;
	private JTextField MaxDang1;
	private JLabel j_MaxDang2;
	private JTextField MaxDang2;
	private JLabel j_MinDang3;
	private JTextField MinDang3;
	private JLabel j_MaxDang3;
	private JTextField MaxDang3;
	private JLabel j_MinDang4;
	private JTextField MinDang4;
	private JLabel j_MinDang5;
	private JTextField MinDang5;
	
	
	
	
	//ȡ���ͱ��水ť
	private JButton ok;
	private JButton cancel;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(PJDangFrm != null){
			PJDangFrm.dispose();
		}
		PJDangFrm = new JFrame("�Ӽ������öԻ���");
		JPanel contentPanel = new JPanel();
		//AboardFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		PJDangFrm.setSize(WIDTH, HEIGHT);
		PJDangFrm.setUndecorated(true);
		PJDangFrm.setContentPane(contentPanel);
		PJDangFrm.setLocation(x, y);
		PJDangFrm.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		
		isOpenJudge = new JCheckBox("������������", ConfigManager.plusSubstractDang.isOpen());
		
		j_endMode = new JLabel("������ʽ", JLabel.CENTER);
		td = new ButtonGroup();
		timeMode = new JRadioButton("��ʱ�����");
		distanceMode = new JRadioButton("���������");
		td.add(timeMode);
		td.add(distanceMode);
		if(ConfigManager.plusSubstractDang.getTimeOrDistance() == 1){
			timeMode.setSelected(true);
		}else
		{
			distanceMode.setSelected(true);
		}
		
		
		j_endTime = new JLabel("����ʱ�� ", JLabel.CENTER);
		endTime = new JTextField(new Long(ConfigManager.plusSubstractDang.getEndTime() / 1000).toString(),4);
		endTime.setHorizontalAlignment(JTextField.CENTER);
		
		j_endDistance = new JLabel("�������� ", JLabel.CENTER);
		endDistance = new JTextField(new Integer(ConfigManager.plusSubstractDang.getEndDistance()).toString(),4);
		endDistance.setHorizontalAlignment(JTextField.CENTER);
		
		j_triggerDistance = new JLabel("�������� ", JLabel.CENTER);
		triggerDistance = new JTextField(new Integer(ConfigManager.plusSubstractDang.getTriggerDistance()).toString(),4);
		triggerDistance.setHorizontalAlignment(JTextField.CENTER);
		
		j_MaxDang1 = new JLabel("1����߳��� ", JLabel.CENTER);
		MaxDang1 = new JTextField(new Integer(ConfigManager.plusSubstractDang.getMaxDang1()).toString(),4);
		MaxDang1.setHorizontalAlignment(JTextField.CENTER);
		
		j_MaxDang2 = new JLabel("2����߳��� ", JLabel.CENTER);
		MaxDang2 = new JTextField(new Integer(ConfigManager.plusSubstractDang.getMaxDang2()).toString(),4);
		MaxDang2.setHorizontalAlignment(JTextField.CENTER);
		
		j_MinDang3 = new JLabel("3����ͳ���, ", JLabel.CENTER);
		MinDang3 = new JTextField(new Integer(ConfigManager.plusSubstractDang.getMinDang3()).toString(),4);
		MinDang3.setHorizontalAlignment(JTextField.CENTER);
		
		j_MaxDang3 = new JLabel("3����߳��� ", JLabel.CENTER);
		MaxDang3 = new JTextField(new Integer(ConfigManager.plusSubstractDang.getMaxDang3()).toString(),4);
		MaxDang3.setHorizontalAlignment(JTextField.CENTER);
		
		j_MinDang4 = new JLabel("4����ͳ��� ", JLabel.CENTER);
		MinDang4 = new JTextField(new Integer(ConfigManager.plusSubstractDang.getMinDang4()).toString(),4);
		MinDang4.setHorizontalAlignment(JTextField.CENTER);
		
		j_MinDang5 = new JLabel("5����ͳ��� ", JLabel.CENTER);
		MinDang5 = new JTextField(new Integer(ConfigManager.plusSubstractDang.getMinDang5()).toString(),4);
		MinDang5.setHorizontalAlignment(JTextField.CENTER);
		
	
		ok = new MyButton(allImage.getSaveIcon(), allImage.getSave_touchIcon());
		cancel = new MyButton(allImage.getBackIcon(), allImage.getBack_touchIcon());
		
		contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 0));
		contentPanel.setLayout(new GridLayout(11, 1, 0, 10));
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
		JLabel kilometer1 = new JLabel("����/Сʱ", JLabel.LEFT);
		panel5.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel5.add(j_MaxDang1);
		panel5.add(MaxDang1);
		panel5.add(kilometer1);
		contentPanel.add(panel5);
		
		JPanel panel6 = new JPanel();
		JLabel kilometer2 = new JLabel("����/Сʱ", JLabel.LEFT);
		panel6.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel6.add(j_MaxDang2);
		panel6.add(MaxDang2);
		panel6.add(kilometer2);
		contentPanel.add(panel6);
		
		JPanel panel7 = new JPanel();
		JLabel kilometer3 = new JLabel("����/Сʱ", JLabel.LEFT);
		panel7.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel7.add(j_MinDang3);
		panel7.add(MinDang3);
		panel7.add(j_MaxDang3);
		panel7.add(MaxDang3);
		panel7.add(kilometer3);
		contentPanel.add(panel7);
		
		JPanel panel8 = new JPanel();
		JLabel kilometer4 = new JLabel("����/Сʱ", JLabel.LEFT);
		panel8.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel8.add(j_MinDang4);
		panel8.add(MinDang4);
		panel8.add(kilometer4);
		contentPanel.add(panel8);
		
		JPanel panel9 = new JPanel();
		JLabel kilometer5 = new JLabel("����/Сʱ", JLabel.LEFT);
		panel9.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel9.add(j_MinDang5);
		panel9.add(MinDang5);
		panel9.add(kilometer5);
		contentPanel.add(panel9);
		
		JPanel panel10 = new JPanel();
		panel10.setLayout(new GridLayout(1, 2, 0, 0));
		panel10.add(ok);
		panel10.add(cancel);
		contentPanel.add(panel10);
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				PJDangFrm.dispose();
			}	
		});
		
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//���������水ť�� �ֱ��ȡÿһ���ؼ���ֵ��Ȼ�����������ļ�
				boolean isOpen = isOpenJudge.isSelected();
				ConfigManager.plusSubstractDang.setOpen(isOpen);
				
				boolean timeOrDistance = timeMode.isSelected();
				if(timeOrDistance){
					ConfigManager.plusSubstractDang.setTimeOrDistance(1);
				}else
				{
					ConfigManager.plusSubstractDang.setTimeOrDistance(0);
				}
				
				String endTimeString = endTime.getText();
				if(StringUtil.isStringEmpty(endTimeString)){
					JOptionPane.showMessageDialog(null, "����ʱ�䲻��Ϊ�գ�");
					return;
				}else
				{
					long endTimeValue = Long.parseLong(endTimeString) * 1000;
					ConfigManager.plusSubstractDang.setEndTime(endTimeValue);
				}
				
				String endDistanceString = endDistance.getText();
				if(StringUtil.isStringEmpty(endDistanceString)){
					JOptionPane.showMessageDialog(null, "�������벻��Ϊ�գ�");
					return;
				} else {
					int endDistanceValue = Integer.parseInt(endDistanceString);
					ConfigManager.plusSubstractDang.setEndDistance(endDistanceValue);
				}

				String triggerDistanceString = triggerDistance.getText();
				if (StringUtil.isStringEmpty(triggerDistanceString)) {
					JOptionPane.showMessageDialog(null, "�������벻��Ϊ�գ�");
					return;
				} else {
					int triggerDistanceValue = Integer.parseInt(triggerDistanceString);
					ConfigManager.plusSubstractDang.setTriggerDistance(triggerDistanceValue);
				}
				
				String MaxDang1String = MaxDang1.getText();
				if (StringUtil.isStringEmpty(MaxDang1String)) {
					JOptionPane.showMessageDialog(null, "1����߳��ٲ���Ϊ�գ�");
					return;
				} else {
					int MaxDang1Value = Integer.parseInt(MaxDang1String);
					ConfigManager.plusSubstractDang.setMaxDang1(MaxDang1Value);
				} 
				
				String MaxDang2String = MaxDang2.getText();
				if (StringUtil.isStringEmpty(MaxDang2String)) {
					JOptionPane.showMessageDialog(null, "2����߳��ٲ���Ϊ�գ�");
					return;
				} else {
					int MaxDang2Value = Integer.parseInt(MaxDang2String);
					ConfigManager.plusSubstractDang.setMaxDang2(MaxDang2Value);
				} 
				
				String MinDang3String = MinDang3.getText();
				if (StringUtil.isStringEmpty(MinDang3String)) {
					JOptionPane.showMessageDialog(null, "3����ͳ��ٲ���Ϊ�գ�");
					return;
				} else {
					int MinDang3Value = Integer.parseInt(MinDang3String);
					ConfigManager.plusSubstractDang.setMinDang3(MinDang3Value);
				} 
				
				String MaxDang3String = MaxDang3.getText();
				if (StringUtil.isStringEmpty(MaxDang3String)) {
					JOptionPane.showMessageDialog(null, "3����߳��ٲ���Ϊ�գ�");
					return;
				} else {
					int MaxDang3Value = Integer.parseInt(MaxDang3String);
					ConfigManager.plusSubstractDang.setMaxDang3(MaxDang3Value);
				} 
				
				String MinDang4String = MinDang4.getText();
				if (StringUtil.isStringEmpty(MinDang4String)) {
					JOptionPane.showMessageDialog(null, "4����ͳ��ٲ���Ϊ�գ�");
					return;
				} else {
					int MinDang4Value = Integer.parseInt(MinDang4String);
					ConfigManager.plusSubstractDang.setMinDang4(MinDang4Value);
				} 
				
				String MinDang5String = MinDang5.getText();
				if (StringUtil.isStringEmpty(MinDang5String)) {
					JOptionPane.showMessageDialog(null, "5����ͳ��ٲ���Ϊ�գ�");
					return;
				} else {
					int MinDang5Value = Integer.parseInt(MinDang5String);
					ConfigManager.plusSubstractDang.setMinDang5(MinDang5Value);
				} 
				
				PJDangFrm.dispose();
			}
		});
		
		
		PJDangFrm.setVisible(true);
	}


}
