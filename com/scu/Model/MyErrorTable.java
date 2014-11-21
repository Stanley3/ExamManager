package com.scu.Model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
/**
 * 错误信息表格
 * @author 孙晓雨
 *
 */
public class MyErrorTable extends JTable{
	
	 public MyErrorTable(MyErrorTableModel model) {
		super(model);
	}

	public TableCellRenderer getDefaultRenderer(Class<?> columnClass)
	  {
	    DefaultTableCellRenderer tableRenderer = (DefaultTableCellRenderer)super.getDefaultRenderer(columnClass);

	    tableRenderer.setHorizontalAlignment(0);
	    tableRenderer.setForeground(new Color(196, 22, 31));
	    tableRenderer.setOpaque(false);
	    return tableRenderer;
	  }
	
//	public void setDefaultEditor(Class<?> columnClass, TableCellEditor editor){
//		editor = new MyEditor();
//	}

	  public void setColumnWidth(int columnIdx, int width) {
	    int columnCount = getColumnCount();
	    for (int i = 0; i < columnCount; i++)
	      if (i == columnIdx) {
	        TableColumn tableColumn = getColumnModel().getColumn(i);
	        tableColumn.setPreferredWidth(width);
	      }
	  }

	  public void hideHeader()
	  {
	    getTableHeader().setVisible(false);
	    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	    renderer.setPreferredSize(new Dimension(0, 0));
	    getTableHeader().setDefaultRenderer(renderer);
	  }

	  public void showHeader() {
	    getTableHeader().setVisible(true);
	  }
}

//class MyEditor extends DefaultCellEditor {     
//	  public MyEditor() {     
//	    super(new JTextField());     
//	  }     

//	public Component getTableCellEditorComponent(JTable table, Object value,
//			boolean isSelected, int row, int column) {
//		// 获得默认表格单元格控件
//		JTextField editor = (JTextField) super.getTableCellEditorComponent(
//				table, value, isSelected, row, column);
//
//		if (value != null)
//			editor.setText(value.toString());
//		if (column == 0) {
//			// 设置对齐方式
//			editor.setHorizontalAlignment(SwingConstants.RIGHT);
//			editor.setFont(new Font("宋体", Font.PLAIN, 14));
//		} else if (column == 1) {
//			editor.setHorizontalAlignment(SwingConstants.CENTER);
//			editor.setFont(new Font("宋体", Font.PLAIN, 14));
//		} else {
//			editor.setHorizontalAlignment(SwingConstants.LEFT);
//			editor.setFont(new Font("宋体", Font.PLAIN, 14));
//		}
//		return editor;
//	}
//}
