package com.scu.Model;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
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
		// TODO Auto-generated constructor stub
	}

	public TableCellRenderer getDefaultRenderer(Class<?> columnClass)
	  {
	    DefaultTableCellRenderer tableRenderer = (DefaultTableCellRenderer)super.getDefaultRenderer(columnClass);

	    tableRenderer.setHorizontalAlignment(0);
	    tableRenderer.setForeground(new Color(196, 22, 31));
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
