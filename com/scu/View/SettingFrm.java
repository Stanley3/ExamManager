package com.scu.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.scu.GlobelControl.ConfigManager;
import com.scu.GlobelControl.ImageIconSet;
import com.scu.Model.MyButton;
import com.scu.Utils.AddComponentUtil;
import com.scu.Utils.GetLocateUtil;
/**
 * ϵͳ���ô���
 * @author ������ 2014.10.11
 *
 */
public class SettingFrm extends JFrame{
	private static final long serialVersionUID = 1L;
	private final int WIDTH = ConfigManager.WIDTH;
	private final int HEIGHT = ConfigManager.HEIGHT;
	private ImageIconSet allImage = ConfigManager.allImage;
	public SettingFrm(String winName) {
		super(winName);
		this.init();
	}
	private void init(){
		//setFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPanel = (JPanel)this.getContentPane();
		this.setUndecorated(true);
	//	contentPanel.setBackground(new Color(188, 188, 188));//new Color(55, 77, 118)
		this.setForeground(new Color(185, 194, 203));
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		//��ϵͳ���öԻ�������ʾ����Ļ������
		this.setLocation(x, y);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		//���ô��ڵĲ��ַ�ʽΪGirdBagLayout
		contentPanel.setLayout(null);
		
		JPanel topPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = allImage.getHeadIcon().getImage();
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		};
		topPanel.setPreferredSize(new Dimension(ConfigManager.WIDTH, 50));
		topPanel.setBounds(0, 0, ConfigManager.WIDTH, 50);
		topPanel.setLayout(new BorderLayout(0, 0));

		JLabel titleLabel = new JLabel("ϵͳ����");
		titleLabel.setHorizontalAlignment(0);
		titleLabel.setFont(new Font("��������", 1, 26));
		titleLabel.setForeground(Color.WHITE);
		topPanel.add(titleLabel, "Center");
		contentPanel.add(topPanel);
		
		JPanel bottomPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = allImage.getHeadIcon().getImage();
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		};
		bottomPanel.setPreferredSize(new Dimension(ConfigManager.WIDTH, 60));
		bottomPanel.setBounds(0, ConfigManager.HEIGHT - 50, ConfigManager.WIDTH, 50);
		contentPanel.add(bottomPanel);
		
		JPanel midPanel = new JPanel();
		//midPanel.setOpaque(false);
		midPanel.setBorder(null);
		GridBagLayout lay = new GridBagLayout();
		midPanel.setLayout(lay);
		midPanel.setPreferredSize(new Dimension(ConfigManager.WIDTH,
				ConfigManager.HEIGHT - topPanel.getHeight()
						- bottomPanel.getHeight()));
		midPanel.setBounds(0, topPanel.getHeight(), ConfigManager.WIDTH,
				ConfigManager.HEIGHT - topPanel.getHeight()
						- bottomPanel.getHeight());
	
		//������ͼ��
		JLabel settingImage = new JLabel(allImage.getSysIdentIcon());
		
		JButton xtjc = new MyButton(allImage.getXtjcIcon(), allImage.getXtjc_touchIcon());
		JButton lxsz = new MyButton(allImage.getLxszIcon(), allImage.getLxsz_touchIcon());
		JButton cssz = new MyButton(allImage.getCsszIcon(), allImage.getCssz_touchIcon());
		JButton gybj = new MyButton(allImage.getGybjIcon(), allImage.getGybj_touchIcon());
		JButton fh = new MyButton(allImage.getFhIcon(), allImage.getFh_touchIcon());
		
		AddComponentUtil addComponent = new AddComponentUtil(midPanel);
		//��Ӳ��ֿ�����
		GridBagConstraints constraints = new GridBagConstraints();
		//�����ϵĿؼ����Ŵ�
		constraints.fill = GridBagConstraints.NONE;
		//���Ȩ��
		//constraints.weightx = 4;
		//constraints.weighty = 3;
		constraints.ipady = 80;
		
		addComponent.add(settingImage, constraints, 0, 0, 5, 1);
		addComponent.add(xtjc, constraints, 0, 1, 1, 1);
		addComponent.add(lxsz, constraints, 1, 1, 1, 1);
		addComponent.add(cssz, constraints, 2, 1, 1, 1);
		addComponent.add(gybj, constraints, 3, 1, 1, 1);
		addComponent.add(fh, constraints, 4, 1, 1, 1);
		contentPanel.add(midPanel);
		
		this.setVisible(true);
		xtjc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						new SystemDetectFrm("ϵͳ���");
						SettingFrm.this.dispose();
					}
				});
			}
		});

		 lxsz.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        EventQueue.invokeLater(new Runnable()
		        {
		          public void run() {
		            LineEidtDialog dialog = new LineEidtDialog();
		            dialog.setVisible(true);
		          }
		        });
		      }
		    });
		 
		cssz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						new ParSettingFrm("��������");
						SettingFrm.this.dispose();
					}
				});
			}
		});
		fh.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainWindow("·����");
				SettingFrm.this.dispose();
			}
		});
	}

}
