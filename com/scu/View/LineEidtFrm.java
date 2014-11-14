package com.scu.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.scu.GlobelControl.ConfigManager;
import com.scu.Utils.DBHelper;
import com.scu.Utils.GetLocateUtil;

public class LineEidtFrm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String title = "";
	private JLabel titleLabel = null;
	private int WIDTH = 300;
	private int HEIGHT = 400;
	private JList list;
	private int[] lineids;
	private String[] linetitles;
	private JButton btn_edit;
	private JButton btn_del;
	private DefaultListModel listModel;

	public LineEidtFrm() {
		init();
		this.title = "±à¼­Â·Ïß";
		this.setAlwaysOnTop(true);
		this.setUndecorated(true);
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		this.setLocation(x, y);
		this.setSize(WIDTH, HEIGHT);
		getContentPane().setLayout(new BorderLayout());

		this.contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		getContentPane().add(this.contentPanel, "Center");
	//	this.setVisible(true);
	//	this.setDefaultCloseOperation(2);
	//	this.setModalityType();

		JPanel panel1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel1.getLayout();
		flowLayout_1.setAlignment(0);
		panel1.setBackground(Color.BLACK);

		panel1.setPreferredSize(new Dimension(this.WIDTH, 60));
		this.contentPanel.add(panel1);

		JLabel leftTitle = new JLabel("");
		leftTitle.setIcon(ConfigManager.signalIcon.getLeftTopIcon());
		leftTitle.setSize(new Dimension(48, 48));
		panel1.add(leftTitle);

		this.titleLabel = new JLabel(this.title);
		this.titleLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 22));
		this.titleLabel.setForeground(Color.WHITE);
		panel1.add(this.titleLabel);

		JPanel panel2 = new JPanel();
		panel2.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout flowLayout_2 = (FlowLayout) panel2.getLayout();
		flowLayout_2.setVgap(0);
		flowLayout_2.setHgap(0);
		panel2.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT - 180));
		this.contentPanel.add(panel2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT - 180));
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel2.add(scrollPane);

		this.list = new JList();
		this.list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int[] index = LineEidtFrm.this.list.getSelectedIndices();
				if ((index != null) && (index.length > 0)) {
					LineEidtFrm.this.btn_edit.setEnabled(true);
					LineEidtFrm.this.btn_del.setEnabled(true);
				}
			}
		});
		this.list.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));
		this.list.setSelectionMode(0);
		scrollPane.setViewportView(this.list);
		this.refreshList();

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setPreferredSize(new Dimension(this.WIDTH, 120));
		this.contentPanel.add(panel);

		this.btn_edit = new JButton("");
		this.btn_edit.setEnabled(false);
		this.btn_edit.setBorder(null);

		this.btn_edit.setRolloverIcon(ConfigManager.signalIcon.getEdit_touchIcon());
		this.btn_edit.setIcon(ConfigManager.signalIcon.getEditIcon());
		this.btn_edit.setContentAreaFilled(false);
		this.btn_edit.setBorderPainted(false);
		panel.add(this.btn_edit);

		JButton button = new JButton("");
		button.setBorder(null);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineEidtFrm.this.closeActionPerformed(e);
			}
		});
		this.btn_del = new JButton("");
		this.btn_del.setEnabled(false);
		this.btn_del.setBorder(null);
		this.btn_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = LineEidtFrm.this.list.getSelectedIndex();
				LineEidtFrm.this.delLine(LineEidtFrm.this.lineids[selected]);
				LineEidtFrm.this.refreshList();
				LineEidtFrm.this.list.updateUI();
				LineEidtFrm.this.btn_edit.setEnabled(false);
				LineEidtFrm.this.btn_del.setEnabled(false);
			}
		});
		this.btn_del.setRolloverIcon(ConfigManager.signalIcon.getDel_touchIcon());
		this.btn_del.setIcon(ConfigManager.signalIcon.getDelIcon());
		this.btn_del.setContentAreaFilled(false);
		this.btn_del.setBorderPainted(false);
		panel.add(this.btn_del);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineEidtFrm.this.closeActionPerformed(e);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
//						new LineEditWindow(0).setVisible(true);
					}
				});
			}
		});
		button_1.setRolloverIcon(ConfigManager.signalIcon.getAdd_touchIcon());
		button_1.setIcon(ConfigManager.signalIcon.getAddIcon());
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		button_1.setBorder(null);
		panel.add(button_1);
		button.setRolloverIcon(ConfigManager.signalIcon.getBack_touchIcon());
		button.setIcon(ConfigManager.signalIcon.getBackIcon());
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		panel.add(button);
		this.btn_edit.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent arg0) {
		    	  LineEidtFrm.this.closeActionPerformed(arg0);
		        int selected = LineEidtFrm.this.list.getSelectedIndex();
		        EventQueue.invokeLater(new Runnable()
		        {
		          public void run() {
	//	            new LineEditWindow(LineEditDialog.this.lineids[this.val$selected]).setVisible(true);
		          } } );
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
			int cnt = db.QueryCnt("lines", null);
			this.lineids = new int[cnt];
			this.linetitles = new String[cnt];
			int idx = 0;
			ResultSet rs = db.Query("lines", null, "lines_no,lines_title");
			if (rs != null)
				while (rs.next()) {
					int line_no = rs.getInt("lines_no");
					String line_title = rs.getString("lines_title");
					this.lineids[idx] = line_no;
					this.linetitles[idx] = line_title;
					idx++;
				}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}

	private void refreshList() {
		try {
			init();
			this.listModel = new DefaultListModel();
			for (int i = 0; i < this.linetitles.length; i++) {
				this.listModel.addElement(this.linetitles[i]);
			}
			this.list.setModel(this.listModel);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void delLine(int id) {
		DBHelper db = new DBHelper();
		try {
			db.conn();
			db.Delete("lines", "lines_no=" + id);
			db.Delete("map_point", "line_id=" + id);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}
}
