package com.scu.Model;

import java.util.Vector;

import javax.swing.AbstractListModel;

public class MyListModel extends AbstractListModel{
	
	private Vector item = null;
	

	public MyListModel(Vector item) {
		super();
		this.item = item;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return item.size();
	}

	@Override
	public Object getElementAt(int index) {
		Object value = null;
	    try {
	      
	      value = this.item.get(index);
	    }
	    catch (Exception localException) {
	    }
	    return value;
	}

}
