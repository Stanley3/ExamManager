package com.scu.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.scu.GlobelControl.ConfigManager;
/**
 * Ñ¡ÔñÊ±¼ä´°¿Ú
 * @author ËïÏþÓê
 *
 */
public class DgTimeDialog extends JDialog{
	private final JPanel contentPanel = new JPanel();
	  private String title = "";
	  private JLabel titleLabel = null;
	  private int width = 300;
	  private int height = 400;
	  private JList list;
	  private int[] ttks;
	  private String[] waits;
	  public LampEditWindow examWindow;
	  private JButton btn_ok;
	  private HashMap dgitems;

	  public DgTimeDialog(LampEditWindow window, HashMap item)
	  {
	    this.examWindow = window;
	    this.dgitems = item;
	    init();
	    this.title = "ÇëÑ¡ÔñµÈ´ýÊ±¼ä";
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
	    this.titleLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 22));
	    this.titleLabel.setForeground(Color.WHITE);
	    panel1.add(this.titleLabel);

	    JPanel panel2 = new JPanel();
	    panel2.setBorder(new LineBorder(new Color(0, 0, 0)));
	    FlowLayout flowLayout_2 = (FlowLayout)panel2.getLayout();
	    flowLayout_2.setVgap(0);
	    flowLayout_2.setHgap(0);
	    panel2.setPreferredSize(new Dimension(this.width, this.height - 120));
	    this.contentPanel.add(panel2);

	    DefaultListModel listModel = new DefaultListModel();
	    for (int i = 0; i < this.waits.length; i++) {
	      listModel.addElement(this.waits[i]);
	    }

	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setPreferredSize(new Dimension(this.width, this.height - 120));
	    scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
	    panel2.add(scrollPane);
	    this.list = new JList(listModel);
	    this.list.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	        int[] index = DgTimeDialog.this.list.getSelectedIndices();
	        if (index != null)
	          DgTimeDialog.this.btn_ok.setEnabled(true);
	      }
	    });
	    this.list.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 24));
	    this.list.setSelectionMode(0);

	    scrollPane.setViewportView(this.list);

	    JPanel panel = new JPanel();
	    panel.setBorder(new LineBorder(new Color(0, 0, 0)));
	    panel.setPreferredSize(new Dimension(this.width, 60));
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
	        DgTimeDialog.this.closeActionPerformed(e);
	      }
	    });
	    button.setRolloverIcon(ConfigManager.signalIcon.getBack_touchIcon());
	    button.setIcon(ConfigManager.signalIcon.getBackIcon());
	    button.setContentAreaFilled(false);
	    button.setBorderPainted(false);
	    panel.add(button);
	    this.btn_ok.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent arg0) {
	        DgTimeDialog.this.closeActionPerformed(arg0);
	        try {
	          int selected = DgTimeDialog.this.list.getSelectedIndex();
	          int id = 0;
	          String title = DgTimeDialog.this.dgitems.get("type_title").toString();
	          String desc = DgTimeDialog.this.dgitems.get("type_desc").toString();
	          int type_id = Integer.parseInt(DgTimeDialog.this.dgitems.get("type_no").toString());
	          DgTimeDialog.this.examWindow.addLampItem(id, title, desc, DgTimeDialog.this.ttks[selected], type_id);
	        } catch (Exception ex) {
	          ex.printStackTrace();
	        }
	      }
	    });
	  }

	  private void closeActionPerformed(ActionEvent evt) {
	    dispose();
	  }

	  private void init() {
	    this.ttks = new int[] { 1, 2, 3, 4, 5 };
	    this.waits = new String[] { "1Ãë", "2Ãë", "3Ãë", "4Ãë", "5Ãë" };
	  }

}
