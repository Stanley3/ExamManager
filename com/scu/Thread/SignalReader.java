package com.scu.Thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.imageio.IIOException;

import com.scu.Signal.*;

public class SignalReader extends Thread {
	InputStream iStream;
	OutputStream oStream;
	public boolean isRun = true;
	public boolean isConntect = false;
	private int timeout = 20000; // socket��ʱ��Ϊ20s78
	private static final int KEEP_ALIVE_TIME = 3000;
	private long startTime = 0L;
	SignalReaderThread readerThread = null;
	public static SignalReader instance = null;
	private JudgeSignal js = JudgeSignal.getInstance();

	String ip;
	int port;
	int count = 0;
	Socket s;
	ServerSocket ss;

	private SignalReader() {
		this.setName("SignalReader" + Thread.activeCount());
	}

	public static SignalReader getInstance() {
		if (instance == null)
			instance = new SignalReader();
		return instance;
	}

	public void reConnect() {
		++count;
		try {
			// s = new Socket(InetAddress.getByName(ip), port);
			// s = new Socket(InetAddress.getLocalHost(), port);
			ss = new ServerSocket(4601);
			s = ss.accept();
			System.out.println("�ͻ��������ӣ�");
			this.iStream = s.getInputStream();
			this.oStream = s.getOutputStream();
		} catch (IOException ex) {
			System.out.println("��" + count + "������ʧ�ܣ�");
			if (count > 3) {
				System.out.println("�������Ӵ���ϵͳ�˳���");
				this.isRun = false;
			}
			this.throwError();
			return;
		}
		if (count > 1)
			System.out.println("��" + count + "�������ɹ���");
		else
			System.out.println("��һ�����ӳɹ�");
		count = 0;
		js.tcp = true;
		this.isConntect = true;
		this.readerThread = new SignalReaderThread(this, this.iStream);
		this.readerThread.start();
	}

	public void run() {
		while (isRun) {
			if (!isConntect)
				this.reConnect();
			// else if(System.currentTimeMillis() -
			// SignalInterfaceImplement.getInstance().signalLastUpdate
			// > this.noDataTime)
			// {
			// System.err.println("���ݳ�ʱ��");
			// SignalInterfaceImplement.getInstance().signalLastUpdate =
			// System.currentTimeMillis();
			// throwError();
			// }
			try {
				// System.out.println("�����߳̿�ʼ����20s");
				Thread.sleep(5000);
				if (this.readerThread != null)
					this.readerThread.clear(); // �����������ܹرյ��ź�
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (startTime == 0L)
				startTime = System.currentTimeMillis();
			if (System.currentTimeMillis() - startTime > KEEP_ALIVE_TIME) {
				startTime = 0L;
				try {
					this.oStream.write("keep-alive".getBytes());
				} catch (IOException e) {
					this.throwError();
					// this.isRun = false;
					System.out.println("���¿������ӣ�");
				}
			}
		}
		System.out.println("������ֹ��");
	}

	public void throwError() {
		this.isConntect = false;
		js.tcp = false;
		try {
			if (this.iStream != null)
				this.iStream.close();
			if (this.oStream != null)
				this.oStream.close();
			if (this.readerThread != null)
				this.readerThread.isRun = false;
			if (this.s != null)
				this.s.close();
			if (this.ss != null)
				ss.close();
		} catch (IOException e) {
			this.iStream = null;
			this.oStream = null;
			this.readerThread = null;
			this.s = null;
			this.ss = null;
		}
		try {
			sleep(timeout);// ��ͣ20s������������
		} catch (InterruptedException e) {
		}
	}
}