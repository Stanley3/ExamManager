package com.scu.Contral;
import com.scu.Signal.*;
import com.scu.Utils.DBHelper;
import com.scu.Utils.*;
import com.scu.Model.ExamWindow;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.sql.ResultSet;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.Player;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
public class NightLightThread extends ModuleThread implements
		ControllerListener {
	/* 是否可读 */
	private boolean canRead = false;
	/* 考试ID */
	private int examId = 0;
	/* 当前题目ID */
	private int curId = 0;
	/* 当前考题 */
	private int[] curTest;
	/* 音频文件名称 */
	private String[] mp3File;
	private int[] waitTimes;
	private JudgeSignal curCarSignal;
	private JudgeSignal lastCarSignal;
	private String mp3rootpath;
	private Player player;
	/* 等待时间，多长时间开启什么灯 */
	private int waitTime = 5000;
	public NightLightThread(ExamWindow window, int moduleFlag) {
		super(window, moduleFlag);
		this.mp3rootpath = "./mp3/";
	}

	public void controllerUpdate(ControllerEvent ce) {
		if ((ce instanceof RealizeCompleteEvent)) {
			this.player.prefetch();
		} else if ((ce instanceof PrefetchCompleteEvent)) {
			this.player.start();
		} else if ((ce instanceof EndOfMediaEvent)) {
			this.player = null;
			this.canRead = true;
		}
	}
	/**
	 * 得到考试项目
	 * 
	 * @param examId
	 */
	public void setExamId(int examId) {
		this.examId = examId;
		DBHelper db = new DBHelper();
		try {
			db.conn();
			System.out.println(this.examId+"");
			int cnt = db.QueryCnt("lamps_point", "lamp_id=" + this.examId);
			if (cnt > 0) {
				this.curTest = new int[cnt];
				System.out.println(cnt);
				this.mp3File = new String[cnt];
				this.waitTimes = new int[cnt];
			}
			ResultSet rs = db.Query("lamps_point,lamps_type",
					"lamps_point.lamp_id=" + this.examId
							+ " and lamps_point.point_type=lamps_type.type_no",
					"point_no,type_no,type_title,mp3file,waittime");
			int idx = 0;
			while (rs.next()) {
				String mp3 = rs.getString("mp3file");
				System.out.println(mp3);
				int type_no = rs.getInt("type_no");
				int iwaittime = rs.getInt("waittime");
				this.curTest[idx] = type_no;
				this.mp3File[idx] = mp3;
				this.waitTimes[idx] = iwaittime;
				idx++;
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			db.close();
		}
	}

	public synchronized void run() {
		if (this.curTest != null) {
			for (int i = 0; i < this.curTest.length; i++) {
				if (!this.runFlag)
					break;
				try {
					this.curCarSignal = (this.lastCarSignal = null);
					this.curId = this.curTest[i];
					this.waitTime = (this.waitTimes[i] * 1000);
					String mp3file = this.mp3File[i];
					File f = new File(this.mp3rootpath + mp3file);
					URL mp3url = f.toURI().toURL();
					try {
						this.player = Manager.createPlayer(mp3url);
						this.player.addControllerListener(this);
						this.player.realize();
					} catch (Exception e) {
						this.player = null;
						this.canRead = true;
					}
					do {
						sleep(50L);
					} while (!this.canRead);

					long startTime = System.currentTimeMillis();
					/* 判断时间是否大于5S */
					while (System.currentTimeMillis()
							- startTime < this.waitTime) {
						execute();
					}
					this.canRead = false;
					judge();
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}
		}
		if (this.player != null) {
			this.player.close();
		}
		this.window.remove(this);
		sendEndMessage(41);
	}
	public void execute() {
		JudgeSignal carSignal = JudgeSignal.getInstance();
		this.lastCarSignal = carSignal;
		if (this.canRead)
			if (this.curCarSignal != null)
				// 调用add方法
				add(this.curCarSignal, this.lastCarSignal);
			else
				this.curCarSignal = JudgeSignal.getInstance().creatNew();
	}

	private void add(JudgeSignal cur, JudgeSignal last) {
		cur.signal_footbrake |= last.signal_footbrake;
		cur.lamp_width |= last.lamp_width;
		cur.lamp_highbeam |= last.lamp_highbeam;
		cur.lamp_near |= last.lamp_near;
		cur.lamp_left |= last.lamp_left;
		cur.lamp_right |= last.lamp_right;
		cur.lamp_fog |= last.lamp_fog;
		cur.signal_clutchpedal |= last.signal_clutchpedal;
	}

	/*     */
	private boolean allLightDown(JudgeSignal carSignal) {
		boolean isAllDown = true;
		if (carSignal != null) {
			isAllDown = carSignal.lamp_width | carSignal.lamp_near
					| carSignal.lamp_highbeam;
			isAllDown = !isAllDown;
		}
		return isAllDown;
	}

	public void judge() {
		if ((this.curCarSignal == null) || (this.lastCarSignal == null)) {
			/* 不能正确�?��灯光 */
			sendMessage("41601", 41);
			return;
		}
		switch (this.curId) {
		case 1://�?��灯光考试
			break;
		case 2://低能见度道路
			if (!this.lastCarSignal.lamp_near) {
				/* /* 不能正确�?��灯光 */
				sendMessage("41601", 41);
			} else {/*???????*/
				if (!this.lastCarSignal.lamp_highbeam)
					break;
				sendMessage("41609", 13);
			}
			break;
		case 3://夜间会车
			if (allLightDown(this.lastCarSignal)) {
				sendMessage("41601", 41);
			} else {
				if ((this.lastCarSignal.lamp_near)
						&& (!this.lastCarSignal.lamp_highbeam))
					break;
				/* 会车时不按规定使用近光灯 */sendMessage("41604", 13);
			}
			break;
		case 4://路口转弯
			if (allLightDown(this.lastCarSignal)) {
				sendMessage("41601", 41);
			} else {
				if ((this.lastCarSignal.lamp_near)
						&& (!this.lastCarSignal.lamp_highbeam))
					break;
				sendMessage("41603", 13);
			}
			break;
		case 5://夜间通过人行横道
			if (allLightDown(this.lastCarSignal)) {
				sendMessage("41601", 41);
			} else {
				if ((this.lastCarSignal.lamp_near)
						&& (!this.lastCarSignal.lamp_highbeam))
					break;
				sendMessage("41604", 13);
			}
			break;
		case 6://夜间通过急弯
			if (allLightDown(this.lastCarSignal)) {
				sendMessage("41601", 41);
			} else {
				if ((this.curCarSignal.lamp_highbeam)
						&& (this.curCarSignal.lamp_near)
						&& (this.lastCarSignal.lamp_near))
					break;
				/*
				 * markdepend 通过急弯、坡路�?拱桥、人行横道或者没有交通信号灯控制的路口时，不交替使用远近光灯示意
				 */
				sendMessage("41603", 13);
			}
			break;
		case 7://夜间通过拱桥
			if (allLightDown(this.lastCarSignal)) {
				sendMessage("41601", 41);
			} else {
				if ((this.curCarSignal.lamp_highbeam)
						&& (this.curCarSignal.lamp_near)
						&& (this.lastCarSignal.lamp_near))
					break;
				sendMessage("41603", 13);
			}
			break;
		case 8://夜间通过坡路
			if (allLightDown(this.lastCarSignal)) {
				sendMessage("41601", 41);
			} else {
				if ((this.curCarSignal.lamp_highbeam)
						&& (this.curCarSignal.lamp_near)
						&& (this.lastCarSignal.lamp_near))
					break;
				sendMessage("41603", 13);
			}
			break;
		case 9://通过路口
			if (allLightDown(this.lastCarSignal)) {
				sendMessage("41601", 41);
			} else {
				if ((this.lastCarSignal.lamp_width)
						&& (this.curCarSignal.lamp_left)
						&& (this.curCarSignal.lamp_right))
					break;
				/* 在路边临时停车不关闭前照灯或不开启示廓灯 */
				sendMessage("41603", 13);
			}
			break;
		case 10://夜间超车
			if (allLightDown(this.lastCarSignal)) {
				sendMessage("41601", 41);
			} else {
				if ((this.lastCarSignal.lamp_width)
						&& (this.curCarSignal.lamp_left)
						&& (this.curCarSignal.lamp_right)
						&& (this.curCarSignal.lamp_fog))
					break;
				sendMessage("41606", 13);
			}
			break;
		case 11://照明不良
			if (allLightDown(this.lastCarSignal)) {
				sendMessage("41601", 41);
			} else {
				if ((this.curCarSignal.lamp_highbeam)
						&& (this.curCarSignal.lamp_near)
						&& (this.lastCarSignal.lamp_near))
					break;
				/* 超车时未交替使用远近光灯提醒被超越车�?*/
				sendMessage("41609", 13);
			}
			break;
		case 12://考试完成
			if (allLightDown(this.lastCarSignal)) {
				sendMessage("41601", 41);
			} else {
				if (this.lastCarSignal.lamp_highbeam)
					break;
				sendMessage("41601", 41);
			}
			break;
		case 13:
			if ((!this.lastCarSignal.lamp_near)
					&& (!this.lastCarSignal.lamp_highbeam)
					&& (!this.lastCarSignal.lamp_left)
					&& (!this.lastCarSignal.lamp_right)
					&& (!this.lastCarSignal.lamp_width)
					&& (!this.lastCarSignal.lamp_fog))
				break;
			/* 不安考试员指令驾�?*/
			sendMessage("30103", 41);
			break;
		}
	}
}