package com.scu.Thread;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Enumeration;

import com.scu.GlobelControl.ConfigManager;
import com.scu.Signal.JudgeSignal;

/**
 * 
 * @author �˽���
 * @version 1.0
 *          <p>
 *          ���߳����ڽ���GPS���źţ��˴�ֻ��GPRMC����Ϣ���н�����
 *          GPS���������յ����źŽ��������http://blog.csdn.net
 *          /quannii/article/details/8661680
 * 
 */
public class GPSThread extends Thread {
	public boolean isRun = true;
	private static GPSThread instance = null;

	private double lastLon = 0.0;
	private double lastLat = 0.0;
	private double currentLat = 0.0;
	private double currentLon = 0.0;
	private DecimalFormat format = new DecimalFormat("#.0000");

	public static GPSThread getInstance() {
		if (instance == null)
			return new GPSThread();
		else
			return instance;
	}

	private GPSThread() {
		this.setName("GPSThread" + Thread.activeCount());
	}

	@SuppressWarnings("restriction")
	public void run() {
		CommPortIdentifier portId = null;
		SerialPort sp;
		String name = ConfigManager.autoJadge.getCom();
		int count = 0;
		BufferedInputStream bi;
		Enumeration<?> en = CommPortIdentifier.getPortIdentifiers();

		while (en.hasMoreElements()) {
			portId = (CommPortIdentifier) en.nextElement();
//			 if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
//			 portId2 = portId;
//			 System.out.println(portId.getName());
//			 count++;
//			 }
//			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL
//					&& portId.getName().equalsIgnoreCase(name)) {
//				count = 1;
//				break;
//			}
			if(portId.getPortType() == CommPortIdentifier.PORT_SERIAL)
			{
				String portName = portId.getName();
				System.out.println(portName);
				if(portName.equalsIgnoreCase(name))
						{
					count = 1;
					break;
						}
			}
		}
		if (count == 0) {
			System.out.println("GPSδ���ӻ�˿����ô���");
			JudgeSignal.getInstance().gps = false;
			return;
		} else if (portId.isCurrentlyOwned()) {
//			System.out.println("GPS�˿ڱ�ռ�û����ô������飡");
//			JudgeSignal.getInstance().gps = false;
			return;
		}
		if (portId != null) {
			try {
				sp = (SerialPort) portId.open(name, 1000);
				if (sp == null) {
					System.out.println("GPSδ���ӻ�˿����ô������飡");
					JudgeSignal.getInstance().gps = false;
					return;
				}
				sp.setSerialPortParams(4800, 8, 1, 0);
				bi = new BufferedInputStream(sp.getInputStream());
				int b;
				String line = "";
				JudgeSignal.getInstance().gps = true;
				while (this.isRun) {
					if ((b = bi.read()) != -1) {
						if (b != 13 && b != 10)
							line += (char) b;
						else if (b == 10) {
							if (!praseGPRMC(line)) {
								JudgeSignal.getInstance().gps = false;
								return;
							}
//							System.out.println(line);
							line = "";
						}
					}
				}
			} catch (PortInUseException e) {
				JudgeSignal.getInstance().gps = false;
				e.printStackTrace();
			} catch (IOException e) {
				JudgeSignal.getInstance().gps = false;
				e.printStackTrace();
			} catch (UnsupportedCommOperationException e) {
				JudgeSignal.getInstance().gps = false;
				e.printStackTrace();
			}
		}
	}

	private boolean praseGPRMC(String line) {
		String[] data;
		data = line.split(",");
		if (data.length > 0 && data[0].equals("$GPRMC")) {
			// System.out.println(line);
			if (data[2].equalsIgnoreCase("V")) {
				System.out.println("�����ź�̫�δ�ܶ�λ�����������ڲ�����ϵͳ��");
				return false;
			}
			currentLon = JudgeSignal.getInstance().lon = Double.valueOf(data[5]
					.trim()) / 100.0; // ���ȣ�Ĭ��Ϊ����
			currentLat = JudgeSignal.getInstance().lat = Double.valueOf(data[3]
					.trim()) / 100.0; // γ�ȣ�Ĭ��Ϊ��γ
			if (Math.abs(lastLat - currentLat) < 0.001
					&& Math.abs(lastLon - currentLon) < 0.0001)
				JudgeSignal.getInstance().gpsspeed = 0.0;
			else
				JudgeSignal.getInstance().gpsspeed = Double.valueOf(data[7]) * 0.5144444 * 3.6; // �ٶȣ���λkm/h
			lastLon = currentLon;
			lastLat = currentLat;
//			JudgeSignal.getInstance().gpsspeed = Double.valueOf(data[7]) * 0.5144444 * 3.6;
			if (!data[8].isEmpty())
				JudgeSignal.getInstance().gpsangle = Double.valueOf(data[8]); // �Ƕȣ���λ
																				// ��
			else
				JudgeSignal.getInstance().gpsangle = 0.0;
			return true;
		} else
			return true;
	}

}