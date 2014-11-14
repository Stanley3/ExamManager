package com.scu.Thread;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.scu.Model.ExamWindow;
import com.scu.Model.Message;

public class TimerTickThread extends Thread {
	private ExamWindow window;
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	private Calendar calendar = Calendar.getInstance();

	public boolean runFlag = true;

	public TimerTickThread(ExamWindow window) {
		this.window = window;
		this.calendar.set(11, 0);
		this.calendar.set(12, 0);
		this.calendar.set(13, 0);
	}

	public void run() {
		while (this.runFlag)
			try {
				Message msg = new Message();
				msg.what = -1;
				String sTick = this.sdf.format(this.calendar.getTime());
				msg.Bundle.put("timetick", sTick);
				this.window.handleMessage(msg);
				sleep(1000L);
				this.calendar.add(13, 1);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	}

}
