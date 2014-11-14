package com.scu.Utils;
 
import java.net.URL;
import java.net.URLDecoder;
import java.text.DecimalFormat;
 
public class Tools
{
private static DecimalFormat df = new DecimalFormat("0.0");

private static String[] HexCode = { 
     "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
     "a", "b", "c", "d", "e", "f" };
   /**
    * ��������ľ�γ�ȼ������
    * @param newlat
    * @param newlon
    * @param oldlat
    * @param oldlon
    * @return ����
    */
   public static double getDistince(double newlat, double newlon, double oldlat, double oldlon)
   {
     double jl_jd = 102834.74258026089D;
     double jl_wd = 111712.69150641056D;
     double b = Math.abs((newlat - oldlat) * jl_jd);
     double a = Math.abs((newlon - oldlon) * jl_wd);
     return Math.sqrt(a * a + b * b);
   }
   /**
    * ����Ƕ�
    * @param newlat
    * @param newlon
    * @param oldlat
    * @param oldlon
    * @return ����������ľ�γ��ֵ������������γɵ�ֱ�߰�˳ʱ�����������ĽǶ�
    */
   public static double getAngleByPoint(double newlat, double newlon, double oldlat, double oldlon) {
     double angle = 0.0D;
 
     double dx = newlon - oldlon;
     double dy = newlat - oldlat;
 
     if (Math.abs(dy) < 1.0E-010D)
     {
       if (dx > 0.0D)
         angle = Math.PI / 2.0 ;
       else
         angle = Math.PI * 3.0 / 2.0;
     }
     else if (dy > 1.0E-010D)
     {
       if (dx > 1.0E-010D)
        angle = Math.PI / 2.0 - Math.atan2(Math.abs(dy), Math.abs(dx));
       else if (dx < 1.0E-010D) {
         angle = Math.PI * 3.0 / 2.0 + Math.atan2(Math.abs(dy), Math.abs(dx));
       }
 
     }
     else if (dx > 1.0E-010D)
       angle = Math.PI / 2.0 + Math.atan2(Math.abs(dy), Math.abs(dx));
     else if (Math.abs(dx) < 1.0E-010D)
       angle = Math.PI;
     else if (dx < 1.0E-010D) {
       angle = Math.PI * 3.0 / 2.0 - Math.atan2(Math.abs(dy), Math.abs(dx));
     }
     return angle * 180.0D / Math.PI;
   }
 
   public static double getSinBC(double angle, double AC) {
     return AC * Math.sin(Math.abs(angle * Math.PI / 180.0D)); } 

   public static int getAngleByGpsAngle(int newAngle, int oldAngle) {
	   return 0;
   }
   public static double getSpeedByPulse(int pulse, int radio, int timespan) { double speed = 0.0D;
    try {
       speed = pulse * 1000 * 3600 / radio / timespan;
    }
     catch (Exception localException) {
     }
     return Double.parseDouble(df.format(speed)); }
 
   public static double getDistinceByPulse(int pulse, int radio)
   {
     double distince = 0.0D;
     try {
       distince = pulse * 1000 / radio;
     }
     catch (Exception localException)
     {
     }
 
     return Double.parseDouble(df.format(distince));
   }
   /**
    * �����ٶȺ�ʱ�������ʻ�ľ���
    * @param V ��λ��ÿ��(m/s)
    * @param time ��λ����(s)
    * @return ��ʱ�������ʻ�ľ���  ��λ��(m)
    */
   public static double getDistinceByOBDV(double V, int time) {
     double distince = 0.0D;
     try {
       distince = V * time / 1000.0D;
     }
     catch (Exception localException) {
     }
    return distince;
   }
 
   public static double getAngleByAcc(double[][] acc) {
     double angle = 0.0D;
    if ((acc == null) || (acc.length == 0))
       return angle;
     for (int i = 0; i < acc.length; i++) {
       angle += Math.atan2(acc[i][1], acc[i][2]) * 57.295779513082323D;
     }
     return angle;
   }
 
   public static String byteToHexString(byte b)
   {
     int n = b;
     if (n < 0)
       n += 256;
     int d1 = n / 16;
     int d2 = n % 16;
     return HexCode[d1] + HexCode[d2];
   }
 
   public static String byteArrayToHexString(byte[] b) {
     String result = "";
     for (int i = 0; i < b.length; i++)
       result = result + byteToHexString(b[i]);
     return result;
   }
   /**
    * 
    * @return  ������̥���ܳ�
    */
   public static double tyreCircumference() {
     return 0.0;
   }
   
   /**
    * ���㵵λ
    * @param n
    * @param V
    * @return ��λ
    */
//   public static int getGear(double n, double V)
//   {
//     int iGear = 0;
//     return iGear;
//   }
   /**
    * ����ĳ��λ���ٶ�
    * @param gear
    * @param V
    * @return ��λ���ٶ�
    */
//   public static int getGearSpeed(int gear, double V) {
//     int iGear = 0;
//     return iGear;
//   }
   /**
    * ���㷽���̵ĽǶ�
    * @param len
    * @param diameter �����̵�ֱ��
    * @return �����̵ĽǶ�
    */
   public static double getWheelAngleByLen(double len, int diameter) {
     double angle = 0.0D;
     try {
      angle = 360.0D * len / (Math.PI * diameter);
     } catch (Exception ex) {
       angle = 0.0D;
     }
     return angle;
   }
   /**
    * δ֪�ķ���
    * @param cls ����
    * @return Ӧ�õ�·��
    */
   public static String getAppPath(Class cls)
   {
     if (cls == null)
       throw new IllegalArgumentException("��������Ϊ�գ�");
     ClassLoader loader = cls.getClassLoader();
 
     String clsName = cls.getName() + ".class";
 
     Package pack = cls.getPackage();
     String path = "";
 
     if (pack != null) {
       String packName = pack.getName();
 
       if ((packName.startsWith("java.")) || (packName.startsWith("javax."))) {
         throw new IllegalArgumentException("��Ҫ����ϵͳ�࣡");
       }
       clsName = clsName.substring(packName.length() + 1);
 
       if (packName.indexOf(".") < 0) { path = packName + "/";
       } else {
         int start = 0; int end = 0;
         end = packName.indexOf(".");
         while (end != -1) {
           path = path + packName.substring(start, end) + "/";
           start = end + 1;
          end = packName.indexOf(".", start);
         }
         path = path + packName.substring(start) + "/";
       }
     }
 
     URL url = loader.getResource(path + clsName);
 
     String realPath = url.getPath();
 
     int pos = realPath.indexOf("file:");
     if (pos > -1) realPath = realPath.substring(pos + 5);
 
    pos = realPath.indexOf(path + clsName);
     realPath = realPath.substring(0, pos - 1);
 
     if (realPath.endsWith("!")) {
       realPath = realPath.substring(0, realPath.lastIndexOf("/"));
     }
 
     try
     {
       realPath = URLDecoder.decode(realPath, "utf-8"); } catch (Exception e) {
       throw new RuntimeException(e);
     }return realPath;
   }
 }