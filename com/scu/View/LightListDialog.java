package com.scu.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.scu.GlobelControl.ConfigManager;
import com.scu.Model.ExamWindow;
import com.scu.Utils.DBHelper;
import com.scu.Utils.GetLocateUtil;
/**
 * 灯光题库选择对话框
 * @author 孙晓雨 2014.10.21
 *
 */
public class LightListDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private String title = "";
	private JLabel titleLabel = null;
	private int WIDTH = 300;
	private int HEIGHT = 400;
	private JList list;
	private int[] lampid;
	private String[] lamptitles;
	public ExamWindow examWindow;
	private JButton btn_ok;

	public LightListDialog(ExamWindow window) {
		this.examWindow = window;
		init();
		this.setModal(true);
		this.title = "请选择语音题库";
		setAlwaysOnTop(true);
		setUndecorated(true);
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		// 把系统设置对话窗口显示在屏幕正中心
		this.setLocation(x, y);
		this.setSize(WIDTH, HEIGHT);
		getContentPane().setLayout(new BorderLayout());
		FlowLayout flowLayout = (FlowLayout) this.contentPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);

		getContentPane().add(this.contentPanel);

		JPanel panel1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel1.setBackground(Color.BLACK);

		panel1.setPreferredSize(new Dimension(this.WIDTH, 60));
		this.contentPanel.add(panel1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(ConfigManager.signalIcon.getLeftTopIcon());
		lblNewLabel.setSize(new Dimension(48, 48));
		panel1.add(lblNewLabel);

		this.titleLabel = new JLabel(this.title);
		this.titleLabel.setFont(new Font("黑体", Font.BOLD, 22));
		this.titleLabel.setForeground(Color.WHITE);
		panel1.add(this.titleLabel);

		JPanel panel2 = new JPanel();
		panel2.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout flowLayout_2 = (FlowLayout) panel2.getLayout();
		flowLayout_2.setVgap(0);
		flowLayout_2.setHgap(0);
		panel2.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT - 120));
		this.contentPanel.add(panel2);

		DefaultListModel listModel = new DefaultListModel();
		for (int i = 0; i < this.lamptitles.length; i++) {
			listModel.addElement(this.lamptitles[i]);
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT - 120));
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel2.add(scrollPane);
		this.list = new JList(listModel);
		this.list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int[] index = LightListDialog.this.list.getSelectedIndices();
				if (index != null)
					LightListDialog.this.btn_ok.setEnabled(true);
			}
		});
		this.list.setFont(new Font("微软雅黑", 1, 18));
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );

		scrollPane.setViewportView(this.list);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setPreferredSize(new Dimension(this.WIDTH, 60));
		this.contentPanel.add(panel);

		this.btn_ok = new JButton("");
		this.btn_ok.setEnabled(false);
		this.btn_ok.setRolloverIcon(ConfigManager.allImage.getOk_touchIcon());
		this.btn_ok.setIcon(ConfigManager.allImage.getOkIcon());
		this.btn_ok.setContentAreaFilled(false);
		this.btn_ok.setBorderPainted(false);
		panel.add(this.btn_ok);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LightListDialog.this.closeActionPerformed(e);
			}
		});
		button.setRolloverIcon(ConfigManager.signalIcon.getBack_touchIcon());
		button.setIcon(ConfigManager.signalIcon.getBackIcon());
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		panel.add(button);
		this.btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LightListDialog.this.closeActionPerformed(arg0);
				int selected = LightListDialog.this.list.getSelectedIndex();
				LightListDialog.this.examWindow.startItem(LightListDialog.this.lampid[selected]);
			}
		});
	}

	private void closeActionPerformed(ActionEvent evt) {
		dispose();
	}

	private void init() {
		DBHelper db = new DBHelper();
		try {
			db.conn();
			int cnt = db.QueryCnt("lamps", null);
			this.lampid = new int[cnt];
			this.lamptitles = new String[cnt];
			int idx = 0;
			ResultSet rs = db.Query("lamps", null, "lamps_no,lamps_title");
			if (rs != null)
				while (rs.next()) {
					int lamp_no = rs.getInt("lamps_no");
					String lamp_title = rs.getString("lamps_title");
					this.lampid[idx] = lamp_no;
					this.lamptitles[idx] = lamp_title;
					idx++;
				}
			System.out.println("idx------------->"+idx++);
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}
}
