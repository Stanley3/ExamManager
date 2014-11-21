package com.scu.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.scu.GlobelControl.ConfigManager;
import com.scu.Model.ExamWindow;
import com.scu.Model.Message;
import com.scu.Signal.JudgeSignal;
import com.scu.Thread.GPSThread;
import com.scu.Thread.SignalReader;
import com.scu.Utils.DBHelper;
import com.scu.Utils.GetLocateUtil;
/**
 * 新增和编辑线路窗口
 * @author 孙晓雨
 *
 */
public class LineEditFrm extends ExamWindow {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private int lineid = 0;
	private JTextField tf_title;
	LineJTable lineTable;
	Vector linelist;
	lineTable_Model et_Model;
	private ArrayList<HashMap<String, Object>> listData = new ArrayList();
	private JList typelist;
	private DefaultListModel listModel;
	private int[] typeids;
	private String[] typetitles;
	int WIDTH = ConfigManager.WIDTH;
	int HEIGHT = ConfigManager.HEIGHT;
	

	public LineEditFrm(int lineId) {

		this.lineid = lineId;
		setDefaultCloseOperation(3);
		setForeground(new Color(185, 194, 203));
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);
		setUndecorated(true);

		int x = GetLocateUtil.GetLocateX(WIDTH);
		int y = GetLocateUtil.GetLocateY(HEIGHT);
		// 把系统设置对话窗口显示在屏幕正中心
		this.setLocation(x, y);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);

		JPanel jPanel1 = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = ConfigManager.allImage.getHeadIcon().getImage();
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		};
		jPanel1.setBounds(0, 0, WIDTH, 50);
		this.contentPane.add(jPanel1);
		jPanel1.setLayout(new FlowLayout(1, 15, 5));

		JLabel lblNewLabel_2 = new JLabel("路线名称：");
		lblNewLabel_2.setFont(new Font("微软雅黑", 1, 26));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(0);
		jPanel1.add(lblNewLabel_2);

		this.tf_title = new JTextField();
		this.tf_title.setHorizontalAlignment(0);
		this.tf_title.setFont(new Font("微软雅黑", 1, 26));
		jPanel1.add(this.tf_title);
		this.tf_title.setColumns(10);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(null);
		panel_5.setPreferredSize(new Dimension(WIDTH, 400));
		panel_5.setBounds(0, 50, WIDTH, 400);
		this.contentPane.add(panel_5);
		panel_5.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panel.setBounds(0, 0, 500, 400);
		panel_5.add(panel);

		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		panel_6.setPreferredSize(new Dimension(panel.getWidth(), 20));
		panel_6.setBounds(0, 0, panel.getWidth(), 20);
		panel_6.setBackground(new Color(83, 105, 128));
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[] { 120, 150, 150 };
		gbl_panel_6.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
		gbl_panel_6.rowWeights = new double[] { 0.0D };
		panel_6.setLayout(gbl_panel_6);

		JLabel lblNewLabel = new JLabel("项目");
		lblNewLabel.setHorizontalAlignment(0);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.fill = 2;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_6.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblLon = new JLabel("LON");
		lblLon.setHorizontalAlignment(0);
		lblLon.setForeground(Color.WHITE);
		lblLon.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_lblLon = new GridBagConstraints();
		gbc_lblLon.insets = new Insets(0, 0, 5, 5);
		gbc_lblLon.fill = 2;
		gbc_lblLon.gridx = 1;
		gbc_lblLon.gridy = 0;
		panel_6.add(lblLon, gbc_lblLon);

		JLabel lblLat = new JLabel("LAT");
		lblLat.setHorizontalAlignment(0);
		lblLat.setForeground(Color.WHITE);
		lblLat.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_lblLat = new GridBagConstraints();
		gbc_lblLat.insets = new Insets(0, 0, 5, 0);
		gbc_lblLat.fill = 2;
		gbc_lblLat.gridx = 2;
		gbc_lblLat.gridy = 0;
		panel_6.add(lblLat, gbc_lblLat);

		JLabel label = new JLabel("角度");
		label.setHorizontalAlignment(0);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 3;
		gbc_label.gridy = 0;
		panel_6.add(label, gbc_label);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		scrollPane.setOpaque(false);
		scrollPane.setPreferredSize(new Dimension(panel.getWidth(), 380));
		scrollPane.setBounds(0, 0, panel.getWidth(), 380);
		this.linelist = new Vector();
		this.et_Model = new lineTable_Model(this.linelist);
		this.lineTable = new LineJTable(this.et_Model);
		this.lineTable.hideHeader();
		this.lineTable.setFont(new Font("宋体", Font.PLAIN, 14));
		this.lineTable.setRowHeight(30);
		this.lineTable.setOpaque(false);
		this.lineTable.setColumnWidth(0, 120);
		this.lineTable.setColumnWidth(1, 150);
		this.lineTable.setColumnWidth(2, 150);
		this.lineTable.setColumnWidth(3, 80);
		this.lineTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rowI = LineEditFrm.this.lineTable.rowAtPoint(e.getPoint());
				LineEditFrm.this.listData.remove(rowI);
				LineEditFrm.this.linelist.remove(rowI);
				LineEditFrm.this.lineTable.updateUI();
			}
		});
		scrollPane.setViewportView(this.lineTable);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(64, 400));
		panel_1.setBounds(panel.getWidth(), 0, 64, 400);
		panel_5.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("images//icons//exchange.png"));
		lblNewLabel_1.setHorizontalAlignment(0);
		panel_1.add(lblNewLabel_1);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setPreferredSize(new Dimension(WIDTH - panel_1.getWidth()
				- panel.getWidth(), 400));
		panel_2.setBounds(panel_1.getWidth() + panel.getWidth(), 0, WIDTH
				- panel_1.getWidth() - panel.getWidth(), 400);
		panel_5.add(panel_2);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1);
		scrollPane_1.setOpaque(false);
		scrollPane_1.setPreferredSize(new Dimension(WIDTH - panel_1.getWidth()
				- panel.getWidth(), 400));
		scrollPane_1.setBounds(panel_1.getWidth() + panel.getWidth(), 0, WIDTH
				- panel_1.getWidth() - panel.getWidth(), 400);

		this.typelist = new JList();
		this.typelist.setFont(new Font("宋体", Font.PLAIN, 14));
		this.typelist.setSelectionMode(0);
		this.typelist.setFixedCellHeight(25);
		scrollPane_1.setViewportView(this.typelist);
		this.typelist.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				int rowI = LineEditFrm.this.typelist.getSelectedIndex();

				LineEditFrm.this.addLineItem(LineEditFrm.this.typetitles[rowI],
						JudgeSignal.getInstance().lon,
						JudgeSignal.getInstance().lat,
						(int) JudgeSignal.getInstance().gpsangle,
						LineEditFrm.this.typeids[rowI]);
			}
		});
		initType();

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		// panel_7.setPreferredSize(new Dimension(WIDTH, 100));
		panel_7.setBounds(0, 450, WIDTH, 100);
		panel_7.setBackground(Color.WHITE);
		this.contentPane.add(panel_7);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (LineEditFrm.this.lineid == 0) {
						if (LineEditFrm.this.tf_title.getText().trim()
								.equals("")) {
							InfoDialog dialog = new InfoDialog("错误", "路线名称不能为空");
							dialog.setVisible(true);
						} else {
							DBHelper db = new DBHelper();
							try {
								db.conn();
								int cnt = db.QueryCnt("lines", "lines_title='"
										+ LineEditFrm.this.tf_title.getText()
												.trim() + "'");
								if (cnt > 0) {
									InfoDialog dialog = new InfoDialog("错误",
											"该线路名称已存在，请重新命名!");
									dialog.setVisible(true);
								} else if ((LineEditFrm.this.listData == null)
										|| (LineEditFrm.this.listData.size() == 0)) {
									InfoDialog dialog = new InfoDialog("错误",
											"请至少添加一项考试选项");
									dialog.setVisible(true);
								} else {
									String sql = "insert into lines(lines_title) values('"
											+ LineEditFrm.this.tf_title
													.getText().trim() + "')";
									db.Insert(sql);
									ResultSet rs = db.Query("lines",
											"lines_title='"
													+ LineEditFrm.this.tf_title
															.getText().trim()
													+ "'", "lines_no");
									int lines_no = 0;
									if ((rs != null) && (rs.next()))
										lines_no = rs.getInt("lines_no");
									rs.close();
									if (lines_no > 0) {
										for (int i = 0; i < LineEditFrm.this.listData
												.size(); i++) {
											HashMap hm = (HashMap) LineEditFrm.this.listData
													.get(i);
											double lon = Double.parseDouble(hm
													.get("lon").toString());
											double lat = Double.parseDouble(hm
													.get("lat").toString());
											int angle = Integer.parseInt(hm
													.get("angle").toString());
											int type_id = Integer.parseInt(hm
													.get("type_id").toString());
											sql = "insert into map_point(line_id,lon,lat,angle,point_type) values("
													+ lines_no
													+ ","
													+ lon
													+ ","
													+ lat
													+ ","
													+ angle
													+ "," + type_id + ")";
											db.Insert(sql);
										}
									}
									LineEditFrm.this.dispose();
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							} finally {
								db.close();
							}
						}
					} else {
						DBHelper db = new DBHelper();
						try {
							db.conn();
							db.Delete("map_point", "line_id="
									+ LineEditFrm.this.lineid);
							for (int i = 0; i < LineEditFrm.this.listData
									.size(); i++) {
								HashMap hm = (HashMap) LineEditFrm.this.listData
										.get(i);
								double lon = Double.parseDouble(hm.get("lon")
										.toString());
								double lat = Double.parseDouble(hm.get("lat")
										.toString());
								int angle = Integer.parseInt(hm.get("angle")
										.toString());
								int type_id = Integer.parseInt(hm
										.get("type_id").toString());
								String sql = "insert into map_point(line_id,lon,lat,angle,point_type) values("
										+ LineEditFrm.this.lineid
										+ ","
										+ lon
										+ ","
										+ lat
										+ ","
										+ angle
										+ ","
										+ type_id + ")";
								db.Insert(sql);
							}
							LineEditFrm.this.dispose();
						} catch (Exception localException1) {
						} finally {
							db.close();
						}
					}
				} catch (Exception localException2) {
				}
			}
		});
		panel_7.setLayout(new FlowLayout(1, 25, 25));
		button.setRolloverIcon(ConfigManager.allImage.getSave_touchIcon());
		button.setIcon(ConfigManager.allImage.getSaveIcon());
		button.setMargin(new Insets(2, 2, 2, 2));
		button.setFont(new Font("华文中宋", 1, 26));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		panel_7.add(button);

		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineEditFrm.this.dispose();
			}
		});
		button_3.setRolloverIcon(ConfigManager.allImage.getBack_touchIcon());
		button_3.setIcon(ConfigManager.allImage.getBackIcon());
		button_3.setMargin(new Insets(2, 2, 2, 2));
		button_3.setFont(new Font("华文中宋", 1, 26));
		button_3.setContentAreaFilled(false);
		button_3.setBorderPainted(false);
		panel_7.add(button_3);

		JPanel bottomPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = ConfigManager.allImage.getHeadIcon().getImage();
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		};
		bottomPanel.setBounds(0, ConfigManager.HEIGHT - 50,
				ConfigManager.WIDTH, 50);
		this.contentPane.add(bottomPanel);
		init();

	}

	private void initType() {
		DBHelper db = new DBHelper();
		try {
			db.conn();
			int cnt = db.QueryCnt("systemparm", "no1=6 and no2<26 and flag=0");
			this.typeids = new int[cnt];
			this.typetitles = new String[cnt];
			int idx = 0;
			ResultSet rs = db.Query("systemparm",
					"no1=6 and no2<26 and flag=0", "no2,txt3");
			if (rs != null)
				while (rs.next()) {
					int type_no = rs.getInt("no2");
					String type_title = rs.getString("txt3");
					this.typeids[idx] = type_no;
					this.typetitles[idx] = type_title;
					idx++;
				}
			this.listModel = new DefaultListModel();
			for (int i = 0; i < this.typetitles.length; i++) {
				this.listModel.addElement(this.typetitles[i]);
			}
			this.typelist.setModel(this.listModel);
		} catch (Exception localException) {
		} finally {
			db.close();
		}
	}

	private void init() {
		DBHelper db = new DBHelper();
		try {
			db.conn();
			if (this.lineid == 0) {
				this.tf_title.setEnabled(true);
			} else {
				ResultSet rs = db.Query("lines", "lines_no=" + this.lineid,
						"lines_title");
				if ((rs != null) && (rs.next())) {
					this.tf_title.setText(rs.getString("lines_title"));
				}
				rs.close();
				this.tf_title.setEnabled(false);
				rs = db.Query("select *,(select txt3 from systemparm where no1=6 and no2=map_point.point_type) as typename from map_point where line_id="
						+ this.lineid + " order by point_no");
				if (rs != null)
					while (rs.next()) {
						String title = rs.getString("typename");
						double lon = rs.getDouble("lon");
						double lat = rs.getDouble("lat");
						int angle = rs.getInt("angle");
						int type_id = rs.getInt("point_type");
						addLineItem(title, lon, lat, angle, type_id);
					}
			}
		} catch (Exception localException) {
		} finally {
			db.close();
		}
	}

	public void dispose() {
		super.dispose();
	}

	public void handleMessage(Message msg) {
		if (!this.isRunning)
			return;
	}

	public void addLineItem(String line_title, double lon, double lat,
			int angle, int type_id) {
		HashMap map = new HashMap();
		map.put("title", line_title);
		map.put("lon", Double.valueOf(lon));
		map.put("lat", Double.valueOf(lat));
		map.put("angle", Integer.valueOf(angle));
		map.put("type_id", Integer.valueOf(type_id));
		this.listData.add(map);
		this.et_Model.addRow(line_title, lon, lat, angle);
		try {
			this.lineTable.updateUI();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Rectangle rect = this.lineTable.getCellRect(
				this.et_Model.getRowCount() - 1, 0, true);
		this.lineTable.scrollRectToVisible(rect);
	}

	public void startItem(int typeID) {
	}

	class LineJTable extends JTable {
		public LineJTable(LineEditFrm.lineTable_Model et_Model) {
			super(et_Model);
		}

		public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {
			DefaultTableCellRenderer tableRenderer = (DefaultTableCellRenderer) super
					.getDefaultRenderer(columnClass);

			tableRenderer.setHorizontalAlignment(0);
			tableRenderer.setForeground(new Color(46, 75, 223));
			tableRenderer.setOpaque(false);
			return tableRenderer;
		}

		public void setColumnWidth(int columnIdx, int width) {
			int columnCount = getColumnCount();
			for (int i = 0; i < columnCount; i++)
				if (i == columnIdx) {
					TableColumn tableColumn = getColumnModel().getColumn(i);
					tableColumn.setPreferredWidth(width);
				}
		}

		public void hideHeader() {
			getTableHeader().setVisible(false);
			DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
			renderer.setPreferredSize(new Dimension(0, 0));
			getTableHeader().setDefaultRenderer(renderer);
		}

		public void showHeader() {
			getTableHeader().setVisible(true);
		}
	}

	class lineTable_Model extends AbstractTableModel {
		private Vector content = null;

		public lineTable_Model(Vector table) {
			this.content = table;
		}

		public void addRow(String line_title, double lon, double lat, int angle) {
			Vector v = new Vector();
			v.add(0, line_title);
			v.add(1, new Double(lon));
			v.add(2, new Double(lat));
			v.add(3, new Integer(angle));
			this.content.add(v);
		}

		public int getColumnCount() {
			return 4;
		}

		public int getRowCount() {
			return this.content.size();
		}

		public Object getValueAt(int arg0, int arg1) {
			Object val = null;
			try {
				Vector v = (Vector) this.content.get(arg0);
				val = v.get(arg1);
			} catch (Exception localException) {
			}
			return val;
		}
	}
}
