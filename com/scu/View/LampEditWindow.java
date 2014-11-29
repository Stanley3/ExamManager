package com.scu.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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

import com.scu.Contral.NightLightThread;
import com.scu.GlobelControl.ConfigManager;
import com.scu.Model.ExamWindow;
import com.scu.Model.Message;
import com.scu.Utils.DBHelper;
import com.scu.Utils.GetLocateUtil;

/**
 * 新增和编辑灯光题库界面
 * @author 孙晓雨
 *
 */
public class LampEditWindow extends ExamWindow {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LampEditWindow window = null;
	private int type = 0;
	private String lamp_title = "";
	private JTextField tf_title;
	LampJTable lampTable;
	Vector lamplist;
	lampTable_Model et_Model;
	private ArrayList<HashMap<String, Object>> listData = new ArrayList();
	LampTypeJTable lamptypeTable;
	Vector lamptypelist;
	lamptypeTable_Model type_Model;
	private ArrayList<HashMap<String, Object>> typelistData = new ArrayList();
	int WIDTH = ConfigManager.WIDTH;
	int HEIGHT = ConfigManager.HEIGHT;
	public HashMap select = new HashMap();

	public LampEditWindow(int ltype) {
		this.window = this;
		this.type = ltype;
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
		// 标题面板
		JPanel titlePanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = ConfigManager.allImage.getHeadIcon().getImage();
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		};
		titlePanel.setPreferredSize(new Dimension(WIDTH, 50));
		titlePanel.setBounds(0, 0, WIDTH, 50);
		this.contentPane.add(titlePanel);
		titlePanel.setLayout(new FlowLayout(1, 15, 5));

		this.tf_title = new JTextField();
		this.tf_title.setHorizontalAlignment(0);
		this.tf_title.setFont(new Font("微软雅黑", 1, 26));
		titlePanel.add(this.tf_title);
		this.tf_title.setColumns(10);

		// 中间面板
		JPanel midPanel = new JPanel();
		midPanel.setBorder(null);
		midPanel.setBounds(0, 50, WIDTH, 400);
		this.contentPane.add(midPanel);
		midPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panel.setBounds(0, 0, WIDTH / 2 - 32, 400);
		midPanel.add(panel);

		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		panel_6.setPreferredSize(new Dimension(panel.getWidth(), 20));
		panel_6.setBounds(0, 0, panel.getWidth(), 20);
		panel_6.setBackground(new Color(83, 105, 128));
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[] { 60, 100, 30 };
		gbl_panel_6.columnWeights = new double[] { 0.0D, 2.0D, 0.0D };
		gbl_panel_6.rowWeights = new double[] { 0.0D };
		panel_6.setLayout(gbl_panel_6);

		JLabel item = new JLabel("项目");
		item.setHorizontalAlignment(JLabel.CENTER);
		item.setForeground(Color.WHITE);
		item.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = 2;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_6.add(item, gbc_lblNewLabel);

		JLabel content = new JLabel("内容");
		content.setHorizontalAlignment(0);
		content.setForeground(Color.WHITE);
		content.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.fill = 2;
		gbc_label_9.gridx = 1;
		gbc_label_9.gridy = 0;
		panel_6.add(content, gbc_label_9);

		JLabel times = new JLabel("间隔时间(秒)");
		times.setHorizontalAlignment(0);
		times.setForeground(Color.WHITE);
		times.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.insets = new Insets(0, 0, 5, 0);
		gbc_label_10.fill = 2;
		gbc_label_10.gridx = 2;
		gbc_label_10.gridy = 0;
		panel_6.add(times, gbc_label_10);

		// 已选择的项目列表的面板
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		scrollPane.setOpaque(false);
	//	System.out.println(panel.getWidth());
		scrollPane.setPreferredSize(new Dimension(panel.getWidth(), 380));
		scrollPane.setBounds(0, 0, panel.getWidth() - 10, 380);

		this.lamplist = new Vector();
		this.et_Model = new lampTable_Model(this.lamplist);
		this.lampTable = new LampJTable(this.et_Model);
		this.lampTable.hideHeader();
		this.lampTable.setFont(new Font("宋体", Font.PLAIN, 12));
		this.lampTable.setRowHeight(30);
		this.lampTable.setOpaque(false);
		this.lampTable.setColumnWidth(0, 60);
		this.lampTable.setColumnWidth(1, 100);
		this.lampTable.setColumnWidth(2, 30);
		// 当鼠标点击已选项目列表项的时候删除已选题目
		this.lampTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rowI = LampEditWindow.this.lamptypeTable.rowAtPoint(e
						.getPoint());
				LampEditWindow.this.listData.remove(rowI);
				LampEditWindow.this.lamplist.remove(rowI);
				LampEditWindow.this.lampTable.updateUI();
			}
		});
		scrollPane.setViewportView(this.lampTable);
		// 中间交换按钮面板
		JPanel midIcon = new JPanel();
		midIcon.setBounds(panel.getWidth(), 0, 64, 400);
		midPanel.add(midIcon);
		midIcon.setLayout(new BorderLayout(0, 0));

		JLabel midLabel = new JLabel("");
		midLabel.setIcon(new ImageIcon("images//icons//exchange.png"));
		midLabel.setHorizontalAlignment(JLabel.CENTER);
		midIcon.add(midLabel);

		JPanel midright = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) midright.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		midright.setBorder(new LineBorder(new Color(0, 0, 0)));
		midright.setBounds(WIDTH / 2 + 32, 0, WIDTH / 2 - 32, 400);
		midPanel.add(midright);

		JScrollPane scrollPane_1 = new JScrollPane();
		midright.add(scrollPane_1);
		scrollPane_1.setOpaque(false);
		scrollPane_1.setPreferredSize(new Dimension(panel.getWidth(), 400));
		this.lamptypelist = new Vector();
		this.type_Model = new lamptypeTable_Model(this.lamptypelist);
		this.lamptypeTable = new LampTypeJTable(this.type_Model);

//		this.lamptypeTable
//				.setPreferredSize(new Dimension(panel.getWidth(), 400));
		this.lamptypeTable.hideHeader();
		this.lamptypeTable.setFont(new Font("宋体", Font.PLAIN, 12));
		this.lamptypeTable.setRowHeight(25);
		this.lamptypeTable.setOpaque(false);
		this.lamptypeTable.setColumnWidth(0, 80);
		this.lamptypeTable.setColumnWidth(1, 150);
		scrollPane_1.setViewportView(this.lamptypeTable);
		initType();
		this.lamptypeTable.updateUI();

		this.lamptypeTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					int rowI = LampEditWindow.this.lamptypeTable.rowAtPoint(e
							.getPoint());

					HashMap hm = (HashMap) LampEditWindow.this.typelistData
							.get(rowI);
					LampEditWindow.this.select = hm;
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							DgTimeDialog dialog = new DgTimeDialog(
									LampEditWindow.this.window,
									LampEditWindow.this.select);
							dialog.setVisible(true);
						}
					});
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setPreferredSize(new Dimension(WIDTH, 100));
		panel_7.setBounds(0, 450, WIDTH, 100);
		panel_7.setBackground(Color.WHITE);
		this.contentPane.add(panel_7);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (LampEditWindow.this.type == 0) {
						if (LampEditWindow.this.tf_title.getText().trim()
								.equals("")) {
							InfoDialog dialog = new InfoDialog("错误", "题目名称不能为空");
							dialog.setVisible(true);
						} else {
							DBHelper db = new DBHelper();
							try {
								db.conn();
								int cnt = db.QueryCnt("lamps", "lamps_title='"
										+ LampEditWindow.this.tf_title
												.getText().trim() + "'");
								if (cnt > 0) {
									InfoDialog dialog = new InfoDialog("错误",
											"该题目名称已存在，请重新命名!");
									dialog.setVisible(true);
								} else if ((LampEditWindow.this.listData == null)
										|| (LampEditWindow.this.listData.size() == 0)) {
									InfoDialog dialog = new InfoDialog("错误",
											"请至少添加一项灯光考试选项");
									dialog.setVisible(true);
								} else {
									String sql = "insert into lamps(lamps_title) values('"
											+ LampEditWindow.this.tf_title
													.getText().trim() + "')";
									db.Insert(sql);
									ResultSet rs = db
											.Query("lamps",
													"lamps_title='"
															+ LampEditWindow.this.tf_title
																	.getText()
																	.trim()
															+ "'", "lamps_no");
									int lamps_no = 0;
									if ((rs != null) && (rs.next()))
										lamps_no = rs.getInt("lamps_no");
									rs.close();
									if (lamps_no > 0) {
										for (int i = 0; i < LampEditWindow.this.listData
												.size(); i++) {
											HashMap hm = (HashMap) LampEditWindow.this.listData
													.get(i);
											int point_type = Integer
													.parseInt(hm.get("type_id")
															.toString());
											int waittime = Integer
													.parseInt(hm
															.get("waittime")
															.toString());
											sql = "insert into lamps_point(lamp_id,point_type,waittime) values("
													+ lamps_no
													+ ","
													+ point_type
													+ ","
													+ waittime + ")";
											db.Insert(sql);
										}
									}
									LampEditWindow.this.dispose();
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
							db.Delete("lamps_point", "lamp_id="
									+ LampEditWindow.this.type);
							for (int i = 0; i < LampEditWindow.this.listData
									.size(); i++) {
								HashMap hm = (HashMap) LampEditWindow.this.listData
										.get(i);
								int point_type = Integer.parseInt(hm.get(
										"type_id").toString());
								int waittime = Integer.parseInt(hm.get(
										"waittime").toString());
								String sql = "insert into lamps_point(lamp_id,point_type,waittime) values("
										+ LampEditWindow.this.type
										+ ","
										+ point_type + "," + waittime + ")";
								db.Insert(sql);
							}
							LampEditWindow.this.dispose();
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
				LampEditWindow.this.dispose();
			}
		});
		button_3.setRolloverIcon(ConfigManager.allImage.getBack_touchIcon());
		button_3.setIcon(ConfigManager.allImage.getBackIcon());
		button_3.setMargin(new Insets(2, 2, 2, 2));
		button_3.setFont(new Font("宋体", Font.PLAIN, 26));
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
			ResultSet rs = db.Query("lamps_type", null, "*");
			// 数据库连接成功
			if (rs != null)
				while (rs.next()) {
					int type_no = rs.getInt("type_no");
					String type_title = rs.getString("type_title");
					String type_desc = rs.getString("type_desc");
					String mp3file = rs.getString("mp3file");
					addLampTypeItem(type_no, type_title, type_desc, mp3file);
				}
		} catch (Exception localException) {
		} finally {
			db.close();
		}
	}

	private void init() {
		DBHelper db = new DBHelper();
		try {
			db.conn();
			if (this.type == 0) {
				this.tf_title.setEnabled(true);
			} else {
				ResultSet rs = db.Query("lamps", "lamps_no=" + this.type,
						"lamps_title");
				if ((rs != null) && (rs.next())) {
					this.tf_title.setText(rs.getString("lamps_title"));
				}
				rs.close();
				this.tf_title.setEnabled(false);
				rs = db.Query("lamps_point,lamps_type", "lamp_id=" + this.type
						+ " and point_type=type_no",
						"point_no,type_title,type_desc,waittime,point_type");
				if (rs != null)
					while (rs.next()) {
						int id = rs.getInt("point_no");
						String title = rs.getString("type_title");
						String desc = rs.getString("type_desc");
						int waittime = rs.getInt("waittime");
						int type_id = rs.getInt("point_type");
						addLampItem(id, title, desc, waittime, type_id);
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

	public void addLampItem(int id, String title, String desc, int waittime,
			int type_id) {
		HashMap map = new HashMap();
		map.put("id", Integer.valueOf(id));
		map.put("title", title);
		map.put("desc", desc);
		map.put("waittime", Integer.valueOf(waittime));
		map.put("type_id", Integer.valueOf(type_id));
		this.listData.add(map);
		this.et_Model.addRow(title, desc, waittime);
		try {
			this.lampTable.updateUI();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Rectangle rect = this.lampTable.getCellRect(
				this.et_Model.getRowCount() - 1, 0, true);
		this.lampTable.scrollRectToVisible(rect);
	}

	public void addLampTypeItem(int type_no, String type_title,
			String type_desc, String mp3file) {
		HashMap map = new HashMap();
		map.put("type_no", Integer.valueOf(type_no));
		map.put("type_title", type_title);
		map.put("type_desc", type_desc);
		map.put("mp3file", mp3file);
		this.typelistData.add(map);
		// System.out.println(map);
		this.type_Model.addRow(type_title, type_desc);
	}

	public void startItem(int typeID) {
		removeAll();
		NightLightThread nightLightThread = new NightLightThread(this,
				NightLightThread.EXAMFLAG);
		nightLightThread.setExamId(typeID);
		addModule(nightLightThread);
		nightLightThread.start();
	}

	class LampJTable extends JTable {
		public LampJTable(LampEditWindow.lampTable_Model et_Model) {
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

	class LampTypeJTable extends JTable {
		public LampTypeJTable(LampEditWindow.lamptypeTable_Model et_Model) {
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

	class lampTable_Model extends AbstractTableModel {
		private Vector content = null;

		public lampTable_Model(Vector table) {
			this.content = table;

		}

		public void addRow(String lamp_title, String lamp_desc, int waittime) {
			Vector v = new Vector();
			v.add(0, lamp_title);
			v.add(1, lamp_desc);
			v.add(2, new Integer(waittime));
			this.content.add(v);
		}

		public int getColumnCount() {
			return 3;
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

	class lamptypeTable_Model extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		private Vector<Vector<String>> content = null;

		public lamptypeTable_Model(Vector<Vector<String>> table) {
			this.content = table;

		}

		public void addRow(String lamp_title, String lamp_desc) {
			Vector<String> v = new Vector<String>();
			v.add(0, lamp_title);
			v.add(1, lamp_desc);
			this.content.add(v);
		}

		public int getColumnCount() {
			return 2;
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
