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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.scu.GlobelControl.ConfigManager;
import com.scu.Utils.DBHelper;
import com.scu.Utils.GetLocateUtil;

/**
 * Ö÷½çÃæÏßÂ·Ñ¡Ôñ¶Ô»°¿ò
 * @author ËïÏþÓê
 *
 */
public class LineSelectDialog extends JDialog{
	private final JPanel contentPanel = new JPanel();
	  private String title = "";
	  private JLabel titleLabel = null;
	  private int WIDTH = 650;
	  private int HEIGHT = 200;
	  private JComboBox lxcomboBox;
	  private JCheckBox cb_mldg;
	  private JCheckBox cb_ykms;
	  private int[] linenos;
	  private String[] linetitles;

	  public LineSelectDialog()
	  {
	    init();
	    this.setModal(true);
	    this.title = "ÇëÑ¡Ôñ¿¼ÊÔÏßÂ·";
	    setAlwaysOnTop(true);
	    setUndecorated(true);
	    this.title = "±à¼­Â·Ïß";
		this.setAlwaysOnTop(true);
		this.setUndecorated(true);
		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		this.setLocation(x, y);
		this.setSize(WIDTH, HEIGHT);
	    getContentPane().setLayout(new BorderLayout());
	    FlowLayout flowLayout = (FlowLayout)this.contentPanel.getLayout();
	    flowLayout.setVgap(0);
	    flowLayout.setHgap(0);

	    getContentPane().add(this.contentPanel, "Center");

	    JPanel panel1 = new JPanel();
	    FlowLayout flowLayout_1 = (FlowLayout)panel1.getLayout();
	    flowLayout_1.setAlignment(0);
	    panel1.setBackground(Color.BLACK);

	    panel1.setPreferredSize(new Dimension(this.WIDTH, 60));
	    this.contentPanel.add(panel1);

	    JLabel lblNewLabel = new JLabel("");
	    lblNewLabel.setIcon(ConfigManager.signalIcon.getLeftTopIcon());
	    lblNewLabel.setSize(new Dimension(48, 48));
	    panel1.add(lblNewLabel);

	    this.titleLabel = new JLabel(this.title);
	    this.titleLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 22));
	    this.titleLabel.setForeground(Color.WHITE);
	    panel1.add(this.titleLabel);

	    JPanel panel2 = new JPanel();
	    panel2.setBorder(new LineBorder(new Color(0, 0, 0)));
	    FlowLayout flowLayout_2 = (FlowLayout)panel2.getLayout();
	    panel2.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT - 120));
	    this.contentPanel.add(panel2);

	    JLabel lblNewLabel_1 = new JLabel("ÇëÑ¡Ôñ¿¼ÊÔÏßÂ·£º");
	    lblNewLabel_1.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));
	    lblNewLabel_1.setForeground(new Color(70, 82, 98));
	    panel2.add(lblNewLabel_1);

	    this.lxcomboBox = new JComboBox(this.linetitles);
	    this.lxcomboBox.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));
	    this.lxcomboBox.setForeground(new Color(70, 82, 98));
	    panel2.add(this.lxcomboBox);

	    this.cb_mldg = new JCheckBox("Ä£ÄâÒ¹¼äµÆ¹â¿ªÆô");
	    this.cb_mldg.addChangeListener(new ChangeListener() {
	      public void stateChanged(ChangeEvent e) {
	        if (LineSelectDialog.this.cb_mldg.isSelected())
	        	LineSelectDialog.this.cb_ykms.setSelected(false);
	      }
	    });
	    this.cb_mldg.setForeground(new Color(70, 82, 98));
	    this.cb_mldg.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));
	    panel2.add(this.cb_mldg);

	    this.cb_ykms = new JCheckBox("Ò¹¿¼¿ªÆô");
	    this.cb_ykms.addChangeListener(new ChangeListener() {
	      public void stateChanged(ChangeEvent e) {
	        if (LineSelectDialog.this.cb_ykms.isSelected())
	        	LineSelectDialog.this.cb_mldg.setSelected(false);
	      }
	    });
	    this.cb_ykms.setForeground(new Color(70, 82, 98));
	    this.cb_ykms.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));
	    panel2.add(this.cb_ykms);

	    JPanel panel = new JPanel();
	    panel.setBorder(new LineBorder(new Color(0, 0, 0)));
	    panel.setPreferredSize(new Dimension(this.WIDTH, 60));
	    this.contentPanel.add(panel);

	    JButton btnNewButton = new JButton("");
	    btnNewButton.setRolloverIcon(ConfigManager.allImage.getOk_touchIcon());
	    btnNewButton.setIcon(ConfigManager.allImage.getOkIcon());
	    btnNewButton.setContentAreaFilled(false);
	    btnNewButton.setBorderPainted(false);
	    panel.add(btnNewButton);

	    JButton button = new JButton("");
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  LineSelectDialog.this.closeActionPerformed(e);
	      }
	    });
	    button.setRolloverIcon(ConfigManager.allImage.getBack_touchIcon());
	    button.setIcon(ConfigManager.allImage.getBackIcon());
	    button.setContentAreaFilled(false);
	    button.setBorderPainted(false);
	    panel.add(button);
	    btnNewButton.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent arg0) {
	    	LineSelectDialog.this.closeActionPerformed(arg0);
	        int selected = LineSelectDialog.this.lxcomboBox.getSelectedIndex();
	        ConfigManager.LINE_ID = LineSelectDialog.this.linenos[selected];
	        ConfigManager.CARPARM_NIGHT_AUTO = LineSelectDialog.this.cb_mldg.isSelected();
	        ConfigManager.CARPARM_NIGHT_YKMS = LineSelectDialog.this.cb_ykms.isSelected();
	        EventQueue.invokeLater(new Runnable()
	        {
	          public void run() {
	            new ExamFrm("Ä£Äâ¿¼ÊÔ");
	          }
	        });
	      }
	    });
	    this.cb_mldg.setSelected(ConfigManager.CARPARM_NIGHT_AUTO);
	    this.cb_ykms.setSelected(ConfigManager.CARPARM_NIGHT_YKMS);
	  }

	  private void closeActionPerformed(ActionEvent evt) {
	    dispose();
	  }

	  private void init() {
	    DBHelper db = new DBHelper();
	    try {
	      db.conn();
	      int cnt = db.QueryCnt("lines", null);
	      this.linenos = new int[cnt];
	      this.linetitles = new String[cnt];
	      int idx = 0;
	      ResultSet rs = 
	        db.Query("lines", null, "lines_no,lines_title");
	      if (rs != null)
	        while (rs.next()) {
	          int line_no = rs.getInt("lines_no");
	          String line_title = rs.getString("lines_title");
	          this.linenos[idx] = line_no;
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

}
