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
 * @author 顾建辉
 * @version 1.0
 *          <p>
 *          该线程用于接收GPS的信号，此处只对GPRMC的信息进行解析。
 *          GPS接收器接收到的信号解释详见：http://blog.csdn.net
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
			System.out.println("GPS未连接或端口配置错误！");
			JudgeSignal.getInstance().gps = false;
			return;
		} else if (portId.isCurrentlyOwned()) {
//			System.out.println("GPS端口被占用或配置错误，请检查！");
//			JudgeSignal.getInstance().gps = false;
			return;
		}
		if (portId != null) {
			try {
				sp = (SerialPort) portId.open(name, 1000);
				if (sp == null) {
					System.out.println("GPS未连接或端口配置错误，请检查！");
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
				System.out.println("卫星信号太差，未能定位，请勿在室内操作本系统！");
				return false;
			}
			currentLon = JudgeSignal.getInstance().lon = Double.valueOf(data[5]
					.trim()) / 100.0; // 经度，默认为东经
			currentLat = JudgeSignal.getInstance().lat = Double.valueOf(data[3]
					.trim()) / 100.0; // 纬度，默认为北纬
			if (Math.abs(lastLat - currentLat) < 0.001
					&& Math.abs(lastLon - currentLon) < 0.0001)
				JudgeSignal.getInstance().gpsspeed = 0.0;
			else
				JudgeSignal.getInstance().gpsspeed = Double.valueOf(data[7]) * 0.5144444 * 3.6; // 速度，单位km/h
			lastLon = currentLon;
			lastLat = currentLat;
//			JudgeSignal.getInstance().gpsspeed = Double.valueOf(data[7]) * 0.5144444 * 3.6;
			if (!data[8].isEmpty())
				JudgeSignal.getInstance().gpsangle = Double.valueOf(data[8]); // 角度，单位
																				// 度
			else
				JudgeSignal.getInstance().gpsangle = 0.0;
			return true;
		} else
			return true;
	}

}