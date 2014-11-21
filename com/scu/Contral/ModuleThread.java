package com.scu.Contral;
import com.scu.Utils.DBHelper;
import com.scu.GlobelControl.ConfigManager;
import com.scu.Model.ExamWindow;
import com.scu.Model.Message;
import java.sql.ResultSet;
public abstract class ModuleThread extends Thread {
	public boolean runFlag = true;
	public boolean isBreakFlag = false;
	/* �?��时间 */
	public long startTime = System.currentTimeMillis();
	/* 结束方式 1 代表按时�?2代表按距�?*/
	public int jsfs = 1;
	/* 时间结束 */
	public long iTimeOut = 0;
	/* 当前距离 */
	public double curRange = 0.0D;
	public double dRangeOut = 0.0D;
	public int moduleFlag = 1;
	/* 是否暂停 */
	public boolean isPause = false;
	/* 考试模式 */
	public static int EXAMFLAG = 1;
	/* 训练模式 */
	public static int TRAINFLAG = 2;
	/* 声明窗体的父�?*/
	public ExamWindow window;

	/* 构�?函数 */
	public ModuleThread(ExamWindow window, int moduleFlag) {
		this.window = window;
		this.moduleFlag = moduleFlag;
	}

	/**
	 * 停止线程运行
	 */
	public void Break() {
		this.isBreakFlag = true;
		this.runFlag = false;
	}

	/**
	 * 时间超出
	 * 
	 * @return
	 */
	public boolean TimeOut() {
		long endTime = System.currentTimeMillis();
		return (endTime - this.startTime) >= this.iTimeOut;
	}

	/**
	 * 判断 距离是否超出
	 * 
	 * @return TRUE FALSE
	 */
	public boolean RangeOut() {
		return this.curRange > this.dRangeOut;
	}

	/**
	 * 判断超出的方�?
	 * 
	 * @return
	 */
	public boolean isOut() {
		if (this.jsfs == 1) {
			return TimeOut();
		}
		return RangeOut();
	}

	/**
	 * 发�?错误信息
	 * 
	 * @param code
	 *            错误种类
	 * @param type
	 *            当前错误属于16个模块的那个模块
	 */
	public void sendMessage(String code, int type) {
		// 自动评判是否�?��
		if (!ConfigManager.autoJadge.isOpenAutoJudge())
			return;
		Message msg = new Message();
		DBHelper db = new DBHelper();
		try {
			db.conn();
			ResultSet rs = db.Query("markrule", "itemno=" + type
					+ " and markcatalog='" + code + "'",
					"itemno,markcatalog,markreal,markshow");
			if ((rs != null) && (rs.next())) {
				msg.Bundle.put("score", Integer.valueOf(rs.getInt("markreal")));
				msg.Bundle.put("reason", rs.getString("markshow"));
				msg.Bundle.put("code", code);
				msg.Bundle.put("type", Integer.valueOf(type));
			}
			rs.close();
			rs = db.Query("systemparm", "no1=6 and no2=" + type, "txt3");
			if ((rs != null) && (rs.next())) {
				msg.Bundle.put("title", rs.getString("txt3"));
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
		}
		msg.what = -4;
		this.window.handleMessage(msg);
	}
	/**
	 * 
	 * @param type
	 * @param flag
	 */
	public void sendEndMessage(int type, int flag) {
		try {
			Message msg = new Message();
			msg.Bundle.put("flag", Integer.valueOf(flag));
			msg.what = type;
			this.window.handleMessage(msg);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void sendEndMessage(int type) {
		sendEndMessage(type, 0);
	}

	public abstract void judge();

	public abstract void execute();
}