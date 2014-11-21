package com.scu.View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import com.scu.GlobelControl.ConfigManager;
import com.scu.GlobelControl.ImageIconSet;
import com.scu.Model.MyButton;
import com.scu.Utils.GetLocateUtil;

public class HandWheelListenerDialog implements ActionListener{

	private static final long serialVersionUID = 2L;
	private static ImageIconSet allImage = new ImageIconSet();
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	//主窗口
	private static JFrame HandWheelFrm;
	//是否开启此项评判复选框
	private JCheckBox jgJudge;
	private JCheckBox ygJudge;
	private JCheckBox jsJudge;
	private JCheckBox ssJudge;
	private JCheckBox wdJudge;
	
	//取消和保存按钮
	private JButton ok;
	private JButton cancel;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(HandWheelFrm != null){
			HandWheelFrm.dispose();
		}
		HandWheelFrm = new JFrame("信号源翻转");
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 200, 20, 0));
		//AboardFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		HandWheelFrm.setSize(WIDTH, HEIGHT);
		HandWheelFrm.setUndecorated(true);
		HandWheelFrm.setContentPane(contentPanel);
		HandWheelFrm.setLocation(x, y);
		HandWheelFrm.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		
		
		jgJudge = new JCheckBox("近光灯", ConfigManager.signalSource.isLamp_near_reversal());
		ygJudge = new JCheckBox("远光灯", ConfigManager.signalSource.isLamp_far_reversal());
		wdJudge = new JCheckBox("雾    灯", ConfigManager.signalSource.isLamp_fog_reversal());
		ssJudge = new JCheckBox("手    刹", ConfigManager.signalSource.isHandBreak_reversal());
		jsJudge = new JCheckBox("脚    刹", ConfigManager.signalSource.isFootBreak_reversal());
		
		
		ok = new MyButton(allImage.getSaveIcon(), allImage.getSave_touchIcon());
		cancel = new MyButton(allImage.getBackIcon(), allImage.getBack_touchIcon());
		
		contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 0));
		contentPanel.setLayout(new GridLayout(6, 1, 30, 30));
		contentPanel.add(jgJudge);
		contentPanel.add(ygJudge);
		contentPanel.add(jsJudge);
		contentPanel.add(ssJudge);
		contentPanel.add(wdJudge);
		
		JPanel panel8 = new JPanel();
		panel8.setLayout(new GridLayout(1, 2, 0, 0));
		panel8.add(ok);
		panel8.add(cancel);
		contentPanel.add(panel8);
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				HandWheelFrm.dispose();
			}	
		});
		
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//如果点击保存按钮， 分别获取每一个控件的值，然后设置配置文件
				boolean jgisOpen = jgJudge.isSelected();
				ConfigManager.signalSource.setLamp_near_reversal(jgisOpen);
				boolean ygisOpen = ygJudge.isSelected();
				ConfigManager.signalSource.setLamp_far_reversal(ygisOpen);
				boolean jsisOpen = jsJudge.isSelected();
				ConfigManager.signalSource.setFootBreak_reversal(jsisOpen);
				boolean ssisOpen = ssJudge.isSelected();
				ConfigManager.signalSource.setHandBreak_reversal(ssisOpen);
				boolean wdisOpen = wdJudge.isSelected();
				ConfigManager.signalSource.setLamp_fog_reversal(wdisOpen);
				
				
				HandWheelFrm.dispose();
			}
		});
		
		HandWheelFrm.setVisible(true);
	}

}

	

