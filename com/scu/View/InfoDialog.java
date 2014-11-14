package com.scu.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.scu.GlobelControl.ConfigManager;
/**
 * µ¯³öÐÅÏ¢´°¿Ú
 * @author ËïÏþÓê
 *
 */
public class InfoDialog extends JDialog
{
  private final JPanel contentPanel = new JPanel();
  private String title = "";
  private String info = "";
  private JLabel titleLabel = null;
  private int width = 400;
  private int height = 300;

  public InfoDialog(String title, String info)
  {
    title = title;
    info = info;
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

    this.titleLabel = new JLabel(title);
    this.titleLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 22));
    this.titleLabel.setForeground(Color.WHITE);
    panel1.add(this.titleLabel);

    JPanel panel2 = new JPanel();
    panel2.setBorder(new LineBorder(new Color(0, 0, 0)));
    FlowLayout flowLayout_2 = (FlowLayout)panel2.getLayout();
    panel2.setPreferredSize(new Dimension(this.width, this.height - 120));
    this.contentPanel.add(panel2);

    JLabel newLabel = new JLabel(info);
    newLabel.setBorder(null);
    newLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));
    newLabel.setHorizontalAlignment(0);
    newLabel.setPreferredSize(new Dimension(this.width, this.height - 120));
    panel2.add(newLabel);

    JPanel panel = new JPanel();
    panel.setBorder(new LineBorder(new Color(0, 0, 0)));
    panel.setPreferredSize(new Dimension(this.width, 60));
    this.contentPanel.add(panel);

    JButton btnNewButton = new JButton("");
    btnNewButton.setRolloverIcon(ConfigManager.allImage.getOk_touchIcon());
    btnNewButton.setIcon(ConfigManager.allImage.getOkIcon());
    btnNewButton.setContentAreaFilled(false);
    btnNewButton.setBorderPainted(false);
    panel.add(btnNewButton);
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        InfoDialog.this.dispose();;
      } } );
  }
}
