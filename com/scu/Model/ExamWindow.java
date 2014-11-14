package com.scu.Model;

import java.util.*;

import javax.swing.*;

import com.scu.Contral.ModuleThread;
/**
 * ´°¿Ú¸¸Àà
 * @author ËïÏþÓê
 *
 */
public abstract class ExamWindow extends JFrame{

	public boolean isRunning = true;
	public LinkedList<ModuleThread> itemList = new LinkedList<ModuleThread>(); 
	public void dispose(){
		super.dispose();
	    this.isRunning = false;
		removeAll();
	}

	public void addModule(ModuleThread item) {
		this.itemList.add(item);
	}

	public void remove(ModuleThread item) {
		this.itemList.remove(item);
	}

	public void removeAll() {
		for (int i = 0; i < itemList.size(); i++) {
			ModuleThread item = (ModuleThread) this.itemList.get(i);
			item.runFlag = false;
		}
	}

	public void clear() {
		for (int i = 0; i < this.itemList.size(); i++) {
			ModuleThread item = (ModuleThread) this.itemList.get(i);
			if (!item.runFlag)
				this.itemList.remove(item);
		}
	}

	public void Break() {
		for (int i = 0; i < this.itemList.size(); i++) {
			ModuleThread item = (ModuleThread) this.itemList.get(i);
			if (item.runFlag) {
				item.Break();
			}
		}
	}
	
	 public abstract void handleMessage(Message paramMessage);

	 public abstract void startItem(int paramInt);
}
