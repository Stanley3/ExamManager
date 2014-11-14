package com.scu.View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import com.scu.GlobelControl.ConfigManager;
import com.scu.GlobelControl.ImageIconSet;
import com.scu.Model.MyButton;
import com.scu.Utils.AddComponentUtil;
import com.scu.Utils.GetLocateUtil;
/**
 * 系统配置按钮监听器, 内部弹出一个对话框
 * @author 孙晓雨
 *
 */
public class ComJudgeListenerDialog implements ActionListener{
	
	/**
	 * 通用评判监听窗口
	 */
	private static ImageIconSet allImage = new ImageIconSet();
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	private static JFrame autoFrm;
	private JCheckBox judge;
	private JCheckBox needBrake;
	private JButton ok;
	private JButton cancel;
	
	private JTextField gpsCom;
	private JLabel comText;
	//构造函数
	public ComJudgeListenerDialog() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(autoFrm != null){
			autoFrm.dispose();
		}
		autoFrm = new JFrame("系统配置对话框");
		JPanel contentPanel = new JPanel();
	//	autoFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout lay = new GridBagLayout();
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		autoFrm.setSize(WIDTH, HEIGHT);
		autoFrm.setContentPane(contentPanel);
		autoFrm.setLayout(lay);
		autoFrm.setLocation(x, y);
		autoFrm.setUndecorated(true);
		autoFrm.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		
		judge = new JCheckBox("是否开启自动评判", ConfigManager.autoJadge.isOpenAutoJudge());
		needBrake = new JCheckBox("减速类达到安全车速是否还需要刹车", ConfigManager.autoJadge.isNeedBrake());
		comText = new JLabel("gps端口：");
		gpsCom = new JTextField(ConfigManager.autoJadge.getCom(), 4);
		gpsCom.setHorizontalAlignment(JTextField.CENTER);
		ok = new MyButton(allImage.getSaveIcon(), allImage.getSave_touchIcon());
		cancel = new MyButton(allImage.getBackIcon(), allImage.getBack_touchIcon());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		//窗口上的控件不放大
		constraints.fill = GridBagConstraints.NONE;
		//添加权重
		constraints.weightx = 0;
		constraints.weighty = 6;
		
		AddComponentUtil addComponent = new AddComponentUtil(contentPanel);
		
		addComponent.add(judge, constraints, 0, 0, 2, 1);
		addComponent.add(needBrake, constraints, 0, 1, 5, 1);
		
		addComponent.add(comText, constraints, 0, 2, 1, 1);
		addComponent.add(gpsCom, constraints, GridBagConstraints.RELATIVE, 2, 1, 1);
		
		//addComponent.add(blank, constraints, 0, 3, 1, 1);
		addComponent.add(ok, constraints, 0, 4, 1, 1);
		addComponent.add(cancel, constraints, 2, 4, 1, 1);
		
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				autoFrm.dispose();
			}	
		});
		
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isOpenAutoJudge = judge.isSelected();
				boolean isneedBrake = needBrake.isSelected();
				String com = gpsCom.getText().trim();
				ConfigManager.autoJadge.setOpenAutoJudge(isOpenAutoJudge);
				ConfigManager.autoJadge.setNeedBrake(isneedBrake);
				ConfigManager.autoJadge.setCom(com);
				autoFrm.dispose();
			}
		});
		autoFrm.setVisible(true);	
	}
	

}
