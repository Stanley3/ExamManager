package com.scu.Utils;

import java.awt.*;
/**
 * ��ȡ��Ļ��Ϣ��
 * @author ������ 2014.10.10
 *
 */

public class GetLocateUtil {
	/**
	 * ��ȡ���ڳߴ�
	 * @return ���ڳߴ�
	 */
	public static  Dimension getScreenInfo()
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		return screenSize;
	}
	/**
	 * 
	 * @param width Ҫ��ʾ���ڵĿ��
	 * @return Ҫ��ʾ�������Ͻ�x������
	 */
	public static int GetLocateX(int width){
		Dimension screenSize = GetLocateUtil.getScreenInfo();
		int x = (screenSize.width - width) / 2;
		return x;
	}
	/**
	 * 
	 * @param height Ҫ��ʾ���ڵĸ߶�
	 * @return Ҫ��ʾ�������Ͻ�y������
	 */

	public static int GetLocateY(int height){
		Dimension screenSize = GetLocateUtil.getScreenInfo();
		int y = (screenSize.height - height) / 2;
		return y;
	}
}
