package com.scu.Thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;

import com.scu.Signal.JudgeSignal;
import com.scu.Signal.Signal;

//import java.nio.file.Paths;
public class SignalReaderThread extends Thread {
	private SignalReader signalReader;
	protected InputStream iStream;
	private JudgeSignal js;
	private int errorConnect = 0;
	private byte[] buffer = new byte[512];
	private int count = 0;
	private long lastCommTime;
	public static String signalCode = "";
	public long validTime = System.currentTimeMillis();
	public boolean isRun = true;
	private LampPlusThread lampPlusThread;

	public SignalReaderThread(SignalReader reader, InputStream iStream) {
		this.iStream = iStream;
		this.signalReader = reader;
		this.setName("SignalReaderThread" + Thread.activeCount());
		js = JudgeSignal.getInstance();
		lampPlusThread = new LampPlusThread();
		lampPlusThread.start();
	}

	public void run() {
		while (isRun) {
			try {
				// lampPlusThread.status = 0;
				count = iStream.read(buffer);
			} catch (SocketException e1) {
				System.out.println("���ӶϿ�������������");
				e1.printStackTrace();
				this.signalReader.throwError();
			} catch (IOException e2) {
				e2.printStackTrace();
				this.signalReader.throwError();
			}
			if (count > 0) {
//				 try {
//				 signalCode = new String(buffer, 0, count, "UTF-8");
//				 } catch (UnsupportedEncodingException e) {
//				 System.err.println("ָ���ı����ʽ����");
//				 this.signalReader.throwError();
//				 return ;
//				 }
				for (int i = 0; i < count; ++i) {
					signalCode += convertToString(buffer[i]);
				}
				String tmpSignal = signalCode;
				judgeCode(tmpSignal);
				signalCode = "";
				count = 0;
				lastCommTime = System.currentTimeMillis();
			}
			if (errorConnect >= 50) {
				System.out.println("���粻�ã��������ӣ�");
				this.signalReader.throwError();
			}
			try {
				sleep(1L);
			} catch (InterruptedException e) {
				e.printStackTrace();
				this.signalReader.throwError();
			}
		}
		lampPlusThread.isRun = false;
	}

	private void judgeCode(String code) {
		code = code.replace(" ", "");
		System.out.println("code= " + code);
		if (code.equalsIgnoreCase(Signal.BEAMLIGHTOFF))
			js.lamp_near = false;
		else if (code.equalsIgnoreCase(Signal.BEAMLIGNTON))
			js.lamp_near = true;
		else if (code.equalsIgnoreCase(Signal.FIRSTGEAR))
			js.gear = 1;
		else if (code.equalsIgnoreCase(Signal.SECONDGEAR))
			js.gear = 2;
		else if (code.equalsIgnoreCase(Signal.THIRDGEAR))
			js.gear = 3;
		else if (code.equalsIgnoreCase(Signal.FOURTHGEAR))
			js.gear = 4;
		else if (code.equalsIgnoreCase(Signal.FIRTHGEAR))
			js.gear = 5;
		else if (code.equalsIgnoreCase(Signal.REVERSEON))
			js.gear = -1;
		else if (code.equalsIgnoreCase(Signal.NOGEAR))
			js.gear = 0;
		else if (code.equalsIgnoreCase(Signal.FOGLAMPOFF))
			js.lamp_fog = false;
		else if (code.equalsIgnoreCase(Signal.FOGLAMPON))
			js.lamp_fog = true;
		else if (code.equalsIgnoreCase(Signal.HIGHBEAMOFF))
			js.lamp_highbeam = false;
		else if (code.equalsIgnoreCase(Signal.HIGHBEAMON))
			js.lamp_highbeam = true;
		else if (code.equalsIgnoreCase(Signal.LEFTLAMP))
			lampPlusThread.status = 1;
		else if (code.equalsIgnoreCase(Signal.RIGHTLAMP))
			lampPlusThread.status = 2;
		else if (code.equalsIgnoreCase(Signal.WARNINGLIGHT))
//			js.lamp_urgent = true;
			lampPlusThread.status = 3;
		else if (code.equalsIgnoreCase(Signal.FOOTBRAKEOFF))
			js.signal_footbrake = false;
		else if (code.equalsIgnoreCase(Signal.FOOTBRAKEON))
			js.signal_footbrake = true;
		else if (code.equalsIgnoreCase(Signal.HANDBRAKEOFF))
			js.signal_handbrake = false;
		else if (code.equalsIgnoreCase(Signal.HANDBRAKEON))
			js.signal_handbrake = true;
		else if (code.equalsIgnoreCase(Signal.DEPUTYFOOTBRAKE))
			js.signal_deputybrake = true;
		else if (code.equalsIgnoreCase(Signal.FRONTBUMPER))
			js.signal_frontbumper = true;
		else if (code.equalsIgnoreCase(Signal.REARBUMPER))
			js.signal_rearbumper = true;
		else if (code.equalsIgnoreCase(Signal.HOOTER))
			js.signal_horn = true;
		else if (code.equalsIgnoreCase(Signal.OFF))
			js.signal_off = true;
		else if (code.equalsIgnoreCase(Signal.SEATBELT))
//			js.signal_seatbelt = false;
			lampPlusThread.seltStatus = 1;
		else {
			System.err.println("����Ĵ��룺" + code);
			writeCode(code);
			++errorConnect;
		}
	}

	// ��δ֪���ź�д���ļ����Ա�۲��źŵĹ��ɣ��˽⵽�źŵķ��͹��ɺ󣬾�ɾ���÷���
	private void writeCode(String code) {
		try {
			File file = new File("d:\\errCode.txt");
			if (!file.exists())
				if (!file.createNewFile())
					System.out.println("�����ļ�ʧ�ܣ�����Ƿ��ж�дD�̵�Ȩ��");
			FileOutputStream fos = new FileOutputStream(file, true);
			fos.write((code + "\n").getBytes("UTF-8"));
			// fos.write('\n');
			fos.flush();
			fos.close();
		} catch (Exception e) {
		}
	}

	public void clear() {
		// js.lamp_left = false;
		// js.lamp_right = false;
//		js.lamp_urgent = false;
		// js.lamp_width = false;
		js.signal_off = false;
		js.signal_deputybrake = false;
		js.signal_frontbumper = false;
		js.signal_rearbumper = false;
		js.signal_door = true;
		js.signal_horn = false;
		js.signal_seatbelt = false; // false��ʾ��ȫ���Ѳ���
	}

	public static String convertToString(byte x) {
		String tmp = "";
		byte y;
		int count = 2;
		while (count-- > 0) {
			y = (byte) (x & 0x0f);
			x = (byte) (x >>> 4);
			switch (y) {
			case 0x0:
			case 0x1:
			case 0x2:
			case 0x3:
			case 0x4:
			case 0x5:
			case 0x6:
			case 0x7:
			case 0x8:
			case 0x9:
				tmp = (char) (y - 0x0 + 48) + tmp;
				break;
			case 0xa:
			case 0xb:
			case 0xc:
			case 0xd:
			case 0xe:
			case 0xf:
				tmp = (char) (y - 0xa + 65) + tmp;
				break;
			default:
				tmp = "#" + tmp;
				break;
			}
		}
		return tmp;
	}
}

class LampPlusThread extends Thread {
	private JudgeSignal js;
	public byte status = 0; // 0��ת��ƹأ�1����ʾ��ƿ���2�� ��ʾ�ҵƿ��� 3����ʾ˫����
	public byte seltStatus = 0;
	private long startLampTime = 0L;
	private long endLampTime = 0L;
	private long startSeltTime = 0L;
	public boolean isRun;

	protected LampPlusThread() {
		super();
		isRun = true;
		this.setName("LampPlusThread");
		js = JudgeSignal.getInstance();
	}

	public void run() {
		System.out.println("start LampPlusThread");
		while (isRun)
			plus();
	}

	private void plus() {
		// System.out.println("in LampPlusThread status=" + status);
		switch (status) {
		case 1: // ��ת��ƴ�һ�Σ�0.25s��ر�
			js.lamp_right = false;
			if (startLampTime == 0L)
				startLampTime = System.currentTimeMillis();
			endLampTime = System.currentTimeMillis();
			if (endLampTime - startLampTime <= 250) {
				js.lamp_left = true;
			} else// 0.25s��رյƹ��źţ�����ʼ����ʱ�丳ֵ0
			{
				startLampTime = endLampTime = 0L;
				js.lamp_left = false;
				status = 0;
			}
			break;
		case 2: // ��ת��ƴ�һ�Σ�0.25s��ر�
			js.lamp_left = false;
			if (startLampTime == 0L)
				startLampTime = System.currentTimeMillis();
			endLampTime = System.currentTimeMillis();
			if (endLampTime - startLampTime <= 250)
				js.lamp_right = true;
			else// 0.25s��رյƹ��źţ�����ʼ����ʱ�丳ֵ0
			{
				startLampTime = endLampTime = 0L;
				js.lamp_right = false;
				status = 0;
			}
			break;
		case 3:
			js.lamp_urgent = true;
			if (startLampTime == 0)
				startLampTime = System.currentTimeMillis();
			endLampTime = System.currentTimeMillis();
			if (endLampTime - startLampTime <= 250) {
				js.lamp_urgent = true;
			} else {
				startLampTime = endLampTime = 0L;
				js.lamp_urgent = false;
				status = 0;
			}
		case 0:
		default:
			js.lamp_left = false;
			js.lamp_right = false;
			js.lamp_urgent = false;
			break;
		}
		switch(seltStatus)
		{
		case 1:
			if(startSeltTime == 0)
				startSeltTime = System.currentTimeMillis();
			if(System.currentTimeMillis() - startSeltTime <= 2000)
			{
				js.signal_seatbelt = true;
			}
			else
			{
				startSeltTime = 0L;
				js.signal_seatbelt = false;
				seltStatus = 0;
			}
			break;
		case 0:
			default:
				js.signal_seatbelt = false;
		}
	}
}