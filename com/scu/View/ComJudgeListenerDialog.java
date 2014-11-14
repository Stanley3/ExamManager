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
 * ϵͳ���ð�ť������, �ڲ�����һ���Ի���
 * @author ������
 *
 */
public class ComJudgeListenerDialog implements ActionListener{
	
	/**
	 * ͨ�����м�������
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
	//���캯��
	public ComJudgeListenerDialog() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(autoFrm != null){
			autoFrm.dispose();
		}
		autoFrm = new JFrame("ϵͳ���öԻ���");
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
		
		judge = new JCheckBox("�Ƿ����Զ�����", ConfigManager.autoJadge.isOpenAutoJudge());
		needBrake = new JCheckBox("������ﵽ��ȫ�����Ƿ���Ҫɲ��", ConfigManager.autoJadge.isNeedBrake());
		comText = new JLabel("gps�˿ڣ�");
		gpsCom = new JTextField(ConfigManager.autoJadge.getCom(), 4);
		gpsCom.setHorizontalAlignment(JTextField.CENTER);
		ok = new MyButton(allImage.getSaveIcon(), allImage.getSave_touchIcon());
		cancel = new MyButton(allImage.getBackIcon(), allImage.getBack_touchIcon());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		//�����ϵĿؼ����Ŵ�
		constraints.fill = GridBagConstraints.NONE;
		//���Ȩ��
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
