package com.scu.View;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.scu.GlobelControl.ConfigManager;
import com.scu.GlobelControl.ImageIconSet;
import com.scu.Model.MyButton;
import com.scu.Signal.JudgeSignal;
import com.scu.Thread.GPSThread;
import com.scu.Thread.SignalReader;
import com.scu.Utils.AddComponentUtil;
import com.scu.Utils.GetLocateUtil;
/**
 * ������ 
 * @author ������ 2014.10.11
 *
 */
public class MainWindow  extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int WIDTH = ConfigManager.WIDTH;
	private final int HEIGHT = ConfigManager.HEIGHT;
	private ImageIconSet allImage = ConfigManager.allImage;
	
	private JButton mnks = new MyButton(allImage.getMnksIcon(), allImage
			.getMnks_touchIcon());
	private JButton mnxl = new MyButton(allImage.getMnxlIcon(), allImage
			.getMnxl_touchIcon());
	private JButton dgxl = new MyButton(allImage.getDgxlIcon(), allImage
			.getDgxl_touchIcon());
	private JButton setting = new MyButton(allImage.getSettingIcon(), allImage
			.getSetting_touchIcon());
	private JButton exit = new MyButton(allImage.getExitIcon(), allImage
			.getExit_touchIcon());

	private JLabel netLabel;
	private JLabel GPSLabel;
	private JLabel copyright;
	private JLabel comyrightEnglish;
	private boolean isGPSNomal;
	private boolean isCompNomal;
//	SignalReader signalReader = new SignalReader();
	SignalReader signalReader = SignalReader.getInstance();
	GPSThread gpsThread = GPSThread.getInstance();
	JudgeSignal judge = JudgeSignal.getInstance();
	UIThread uiThread = new UIThread();

	JLabel compLabel;
	JLabel GpsPanel;
//	SignalReader signalReader = new SignalReader();
//	private UIThread uiThread = null;
	
	public MainWindow(String name) throws HeadlessException {
		super(name);
		this.init();
	}
	public void init(){
		JPanel panel =(JPanel) this.getContentPane();
		JPanel contentPane = new JPanel(){
			public void paintComponent(Graphics g) {	
				g.drawImage(allImage.getBackground().getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
				super.printComponents(g);
			}
		};
		contentPane.setBounds(0, 0, WIDTH, HEIGHT);
		contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		contentPane.setOpaque(true);
		//ȥ���߿�
		this.setUndecorated(true);
		this.setContentPane(contentPane);
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		this.setLocation(x, y);
		this.setSize(WIDTH, HEIGHT);
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		topPanel.setPreferredSize(new Dimension(WIDTH, 160));
		JPanel ItPanel = new JPanel();
		ItPanel.setOpaque(false);
		ItPanel.setPreferredSize(new Dimension(WIDTH, 90));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setPreferredSize(new Dimension(WIDTH, 150));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);
		bottomPanel.setPreferredSize(new Dimension(WIDTH, 80));
		
		JPanel copyRightPanel = new JPanel();
		copyRightPanel.setOpaque(false);
		copyRightPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT - 480));
		
		contentPane.add(topPanel);
		contentPane.add(ItPanel);
		contentPane.add(buttonPanel);
		contentPane.add(bottomPanel);
		contentPane.add(copyRightPanel);
		//�ָ�topPanel
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 60));
		JLabel titleIcon = new JLabel(allImage.getTitleIcon());
//		{
//		protected void paintComponent(Graphics g) {
//			super.paintComponent(g);
//			g.drawImage(allImage.getTitleIcon().getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
//			}
//		};
		titleIcon.setPreferredSize(new Dimension(WIDTH, 120));
		topPanel.add(titleIcon);
		
		//�ָ�ItPanel
		ItPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
		JLabel examCharacter = new JLabel(new ImageIcon("images\\main\\It.png"));
//		{
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			protected void paintComponent(Graphics g) {
//				super.paintComponent(g);
//				g.drawImage(new ImageIcon("images\\icons\\lt.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
//			}
//		};
		examCharacter.setPreferredSize(new Dimension(WIDTH, 50));
		ItPanel.add(examCharacter);
		
		//�ָ�buttonPanel
		GridBagLayout bg = new GridBagLayout();
		buttonPanel.setLayout(bg);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.NONE;

		AddComponentUtil addComponent = new AddComponentUtil(buttonPanel);
		
		addComponent.add(mnks, constraints, 0, 1, 1, 1);
		addComponent.add(mnxl, constraints, 1, 1, 1, 1);
		addComponent.add(dgxl, constraints, 2, 1, 1, 1);
		addComponent.add(setting, constraints, 3, 1, 1, 1);
		addComponent.add(exit, constraints, 4, 1, 4, 1);
		
		//�ָ�bottomPanel
		bottomPanel.setLayout(new GridLayout(1, 2, 30, 0));
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		compLabel = new JLabel(new ImageIcon("images\\main\\net_off.png"), JLabel.RIGHT);
		
		bottomPanel.add(compLabel);

		GpsPanel = new JLabel(new ImageIcon("images\\main\\gps_off.png"), JLabel.LEFT);
		bottomPanel.add(GpsPanel);

		/**************************************************************************/
		
		setting.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

		         EventQueue.invokeLater(new Runnable()
		          {
		            public void run() {
		            	new SettingFrm("ϵͳ����");
		     //       	MainWindow.this.dispose();		       
		            }
		          });
			}
		});
		mnks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineSelectDialog dialog = new LineSelectDialog();
				dialog.setVisible(true);
			}

		});
		mnxl.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
		         EventQueue.invokeLater(new Runnable()
		          {
		            public void run() {
		              new RoutineTrainingFrm("�ճ�ѵ��");
		            }
		          });
			}
		});
		dgxl.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
		         EventQueue.invokeLater(new Runnable()
		          {
		            public void run() {
		              new LightTrainFrm("�ճ�ѵ��");
		  //            MainWindow.this.dispose();
		            }
		          });
			}
		});
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				   MainWindow.this.gpsThread.isRun = false;
			       MainWindow.this.signalReader.isRun = false;
				System.exit(0);
			}
		});
		this.uiThread.start();
		this.signalReader.start();
		this.gpsThread.start();
		this.setVisible(true);	
		
	}
	@Override
	public void dispose() {
		if(this.uiThread != null)
			this.uiThread.runFlag = false;
		this.uiThread = null;
		super.dispose();
	}
	
	class UIThread extends Thread {
	    public boolean runFlag = true;

	    public UIThread() {
	    }

		private void refreshState() {
			try {
				if (MainWindow.this.judge.gps) {
					MainWindow.this.GpsPanel.setIcon(new ImageIcon("images\\main\\gps_on.png"));;
				} else {
					MainWindow.this.GpsPanel.setIcon(new ImageIcon("images\\main\\gps_off.png"));;
				}

				if (MainWindow.this.judge.tcp) {
					MainWindow.this.compLabel.setIcon(new ImageIcon("images\\main\\net_on.png"));
				} else {
					MainWindow.this.compLabel.setIcon(new ImageIcon("images\\main\\net_off.png"));
				}

	      } catch (Exception ex) {
	        ex.printStackTrace();
	      }
	    }

	    public void run() {
	      while (this.runFlag)
	        try {
	          refreshState();
	          sleep(200L);
	        } catch (Exception ex) {
	          ex.printStackTrace();
	        }
	    }
	  }
	//���������
	public static void main(String[] args) {
		Font font = new Font("����", Font.BOLD, 15);
		java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}

		}
		
		EventQueue.invokeLater(new Runnable() {
		      public void run() {
		        try {
		    		new MainWindow("·����");
		        } catch (Exception e) {
		          e.printStackTrace();
		        }
		      }
		    });
	}
	
}
