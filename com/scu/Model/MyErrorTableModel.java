package com.scu.Model;

import java.awt.Component;
import java.awt.Font;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
/**
 * 错误信息表格模型
 * @author 孙晓雨
 *
 */
public class MyErrorTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private Vector item = null;
	
	public MyErrorTableModel(Vector item) {
		this.item = item;
	}

	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.item.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
	    try {
	      Vector v = (Vector)this.item.get(rowIndex);
	      value = v.get(columnIndex);
	    }
	    catch (Exception localException) {
	    }
	    return value;
	}
	 public void addRow(String err_item, int err_score, String err_reason)
	  {
	    Vector v = new Vector();
	    v.add(0, err_item);
	    v.add(1, new Integer(err_score));
	    v.add(2, err_reason);
	    this.item.add(v);
	  }
	
}


