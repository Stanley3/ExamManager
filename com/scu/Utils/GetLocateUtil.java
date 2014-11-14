package com.scu.Utils;

import java.awt.*;
/**
 * 获取屏幕信息类
 * @author 孙晓雨 2014.10.10
 *
 */

public class GetLocateUtil {
	/**
	 * 获取窗口尺寸
	 * @return 窗口尺寸
	 */
	public static  Dimension getScreenInfo()
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		return screenSize;
	}
	/**
	 * 
	 * @param width 要显示窗口的宽度
	 * @return 要显示窗口左上角x的坐标
	 */
	public static int GetLocateX(int width){
		Dimension screenSize = GetLocateUtil.getScreenInfo();
		int x = (screenSize.width - width) / 2;
		return x;
	}
	/**
	 * 
	 * @param height 要显示窗口的高度
	 * @return 要显示窗口左上角y的坐标
	 */

	public static int GetLocateY(int height){
		Dimension screenSize = GetLocateUtil.getScreenInfo();
		int y = (screenSize.height - height) / 2;
		return y;
	}
}
