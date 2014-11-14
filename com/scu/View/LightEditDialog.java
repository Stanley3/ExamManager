package com.scu.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.scu.GlobelControl.ConfigManager;
import com.scu.Model.ExamWindow;
import com.scu.Utils.DBHelper;
/**
 * µ∆π‚±‡º≠∂‘ª∞øÚ
 * @author ÀÔœ˛”Í
 *
 */
public class LightEditDialog extends JDialog{
	 private final JPanel contentPanel = new JPanel();
	  private String title = "";
	  private JLabel titleLabel = null;
	  private int width = 400;
	  private int height = 400;
	  private JList list;
	  private int[] lampids;
	  private String[] lamptitles;
	  public ExamWindow examWindow;
	  private JButton btn_edit;
	  private JButton btn_del;
	  private DefaultListModel listModel;
	  
	  public int select = 0;

	  public LightEditDialog(ExamWindow window)
	  {
	    this.examWindow = window;
	    init();
	    this.title = "±‡º≠”Ô“ÙÃ‚ø‚";
	    setAlwaysOnTop(true);
	    setUndecorated(true);
	    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds(d.width / 2 - this.width / 2, d.height / 2 - this.height / 2, this.width, this.height);
	    setSize(this.width, this.height);
	    getContentPane().setLayout(new BorderLayout());
	    FlowLayout flowLayout = (FlowLayout)this.contentPanel.getLayout();
	    flowLayout.setVgap(0);
	    flowLayout.setHgap(0);

	    getContentPane().add(this.contentPanel, "Center");

	    JPanel panel1 = new JPanel();
	    FlowLayout flowLayout_1 = (FlowLayout)panel1.getLayout();
	    flowLayout_1.setAlignment(0);
	    panel1.setBackground(Color.BLACK);

	    panel1.setPreferredSize(new Dimension(this.width, 60));
	    this.contentPanel.add(panel1);

	    JLabel lblNewLabel = new JLabel("");
	    lblNewLabel.setIcon(ConfigManager.signalIcon.getLeftTopIcon());
	    lblNewLabel.setSize(new Dimension(48, 48));
	    panel1.add(lblNewLabel);

	    this.titleLabel = new JLabel(this.title);
	    this.titleLabel.setFont(new Font("Œ¢»Ì—≈∫⁄", 1, 22));
	    this.titleLabel.setForeground(Color.WHITE);
	    panel1.add(this.titleLabel);

	    JPanel panel2 = new JPanel();
	    panel2.setBorder(new LineBorder(new Color(0, 0, 0)));
	    FlowLayout flowLayout_2 = (FlowLayout)panel2.getLayout();
	    flowLayout_2.setVgap(0);
	    flowLayout_2.setHgap(0);
	    panel2.setPreferredSize(new Dimension(this.width, this.height - 120));
	    this.contentPanel.add(panel2);

	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setPreferredSize(new Dimension(this.width, this.height - 120));
	    scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
	    panel2.add(scrollPane);
	    this.list = new JList();
	    this.list.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	        int[] index = LightEditDialog.this.list.getSelectedIndices();
	        if (index != null) {
	          LightEditDialog.this.btn_edit.setEnabled(true);
	          LightEditDialog.this.btn_del.setEnabled(true);
	        }
	      }
	    });
	    this.list.setFont(new Font("Œ¢»Ì—≈∫⁄", 1, 18));
	    this.list.setSelectionMode(0);
	    scrollPane.setViewportView(this.list);
	    refreshList();

	    JPanel panel = new JPanel();
	    panel.setBorder(new LineBorder(new Color(0, 0, 0)));
	    panel.setPreferredSize(new Dimension(this.width, 60));
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
	        LightEditDialog.this.closeActionPerformed(e);
	      }
	    });
	    this.btn_del = new JButton("");
	    this.btn_del.setEnabled(false);
	    this.btn_del.setBorder(null);
	    this.btn_del.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        int selected = LightEditDialog.this.list.getSelectedIndex();
	        LightEditDialog.this.delLamp(LightEditDialog.this.lampids[selected]);
	        LightEditDialog.this.refreshList();
	        LightEditDialog.this.list.updateUI();
	        LightEditDialog.this.btn_edit.setEnabled(false);
	        LightEditDialog.this.btn_del.setEnabled(false);
	      }
	    });
	    this.btn_del.setRolloverIcon(ConfigManager.signalIcon.getDel_touchIcon());
	    this.btn_del.setIcon(ConfigManager.signalIcon.getDelIcon());
	    this.btn_del.setContentAreaFilled(false);
	    this.btn_del.setBorderPainted(false);
	    panel.add(this.btn_del);
	    button.setRolloverIcon(ConfigManager.signalIcon.getBack_touchIcon());
	    button.setIcon(ConfigManager.signalIcon.getBackIcon());
	    button.setContentAreaFilled(false);
	    button.setBorderPainted(false);
	    panel.add(button);
	    this.btn_edit.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent arg0) {
	        LightEditDialog.this.closeActionPerformed(arg0);
	        int selected = LightEditDialog.this.list.getSelectedIndex();
	        LightEditDialog.this.select = selected;
	        EventQueue.invokeLater(new Runnable()
	        {
	          public void run() {
	            new LampEditWindow(LightEditDialog.this.lampids[LightEditDialog.this.select]).setVisible(true);
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
	      int cnt = db.QueryCnt("lamps", null);
	      this.lampids = new int[cnt];
	      this.lamptitles = new String[cnt];
	      int idx = 0;
	      ResultSet rs = 
	        db.Query("lamps", null, "lamps_no,lamps_title");
	      if (rs != null)
	        while (rs.next()) {
	          int lamp_no = rs.getInt("lamps_no");
	          String lamp_title = rs.getString("lamps_title");
	          this.lampids[idx] = lamp_no;
	          this.lamptitles[idx] = lamp_title;
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

	  private void refreshList()
	  {
	    try {
	      init();
	      this.listModel = new DefaultListModel();
	      for (int i = 0; i < this.lamptitles.length; i++) {
	        this.listModel.addElement(this.lamptitles[i]);
	      }
	      this.list.setModel(this.listModel);
	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }
	  }

	  private void delLamp(int id) {
	    DBHelper db = new DBHelper();
	    try {
	      db.conn();
	      db.Delete("lamps", "lamps_no=" + id);
	      db.Delete("lamps_point", "lamp_id=" + id);
	    } catch (Exception ex) {
	      ex.printStackTrace();
	    } finally {
	      if (db != null)
	        db.close();
	    }
	  }

}
