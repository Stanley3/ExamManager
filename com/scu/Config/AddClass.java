package com.scu.Config;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.scu.Utils.DBHelper;
public class AddClass {
	
	//起步时发动机的最低转速
	public  int CARPARM_QB_QBFDJZS = 1000;
	//起步时发动机最高转速
	 public  int CARPARM_QB_QBFDJZZGG = 2100;
	//开始时间
	String CARPARM_NIGHT_KSSJ="18:00";
	//结束时间
	String CARPARM_NIGHT_JSSJ="23:00";
	//是否需要夜间考试
	public  boolean CARPARM_NIGHT_YKMS=false;
	//一档的最长使用距离
	public int CARPARM_GOLBAL_1DJL=30;
	//二档最长使用距离
	public int CARPARM_GOLBAL_2DJL=150;
	//发动机的最低速度没有该信号默认为零
	private int SPEED_ENGINE_LOW=0;
	//发动机的最高速度没有该信号默认为零
		public  int SPEED_ENGINE_HIGH=0;
	//如果灯关闭时间大于系统预设时间则将开灯时间重置为0
	private long lightOffTime=1000;
	//发动机低速不匹配持续时间（暂无配置）
	public  int CARPARM_GOLBAL_KDHX=5;
	private long CARPARM_GOLBAL_DDGZSKFSJ=20000;
	//几档表示高档位
	private int CARPARM_GOLBAL_KSZDDW=4;
	/* ？？左转向灯不能开启超过10S？？？ */ 
	private long CARPARM_GOLBAL_ZDDBGBJL=10000;
	private static  AddClass instance= null;
	public  boolean isYkms()
	/*     */   {
	/* 540 */     boolean isykms = false;
	/* 541 */     if (CARPARM_NIGHT_YKMS) {
	/* 542 */       SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
	/*     */       try {
	/* 544 */         Date bd = sdf.parse(CARPARM_NIGHT_KSSJ);
	/* 545 */         Date ed = sdf.parse(CARPARM_NIGHT_JSSJ);
	/* 546 */         Calendar curd = Calendar.getInstance();
	/* 547 */         Calendar bc = Calendar.getInstance();
	/* 548 */         Calendar ec = Calendar.getInstance();
	/* 549 */         bc.setTime(bd);
	/* 550 */         ec.setTime(ed);
	/* 551 */         bc.set(1, curd.get(1));
	/* 552 */         bc.set(2, curd.get(2));
	/* 553 */         bc.set(5, curd.get(5));
	/* 554 */         ec.set(1, curd.get(1));
	/* 555 */         ec.set(2, curd.get(2));
	/* 556 */         ec.set(5, curd.get(5));
	/* 557 */         if ((curd.after(bc)) && (curd.before(ec))) {
	/* 558 */           isykms = true;
	/* 560 */         }isykms = false;
	/*     */       }
	/*     */       catch (Exception ex) {
	/* 563 */         isykms = false;
	/*     */       }
	/*     */     } else {
	/* 566 */       isykms = false;
	/*     */     }
	/* 568 */      return isykms;
	/*     */   }

	public static AddClass getInstance(){
		if(instance == null){
			instance = new AddClass();
		}
		return instance;
	}
	
	public long getCARPARM_GOLBAL_ZDDBGBJL() {
		return CARPARM_GOLBAL_ZDDBGBJL;
	}

	public int getSPEED_ENGINE_LOW() {
		return SPEED_ENGINE_LOW;
	}

	public void setSPEED_ENGINE_LOW(int sPEED_ENGINE_LOW) {
		SPEED_ENGINE_LOW = sPEED_ENGINE_LOW;
	}

	public void setCARPARM_GOLBAL_ZDDBGBJL(int cARPARM_GOLBAL_ZDDBGBJL) {
		CARPARM_GOLBAL_ZDDBGBJL = cARPARM_GOLBAL_ZDDBGBJL;
	}

	public int getCARPARM_GOLBAL_KSZDDW() {
		return CARPARM_GOLBAL_KSZDDW;
	}

	public void setCARPARM_GOLBAL_KSZDDW(int cARPARM_GOLBAL_KSZDDW) {
		CARPARM_GOLBAL_KSZDDW = cARPARM_GOLBAL_KSZDDW;
	}

	public long getCARPARM_GOLBAL_DDGZSKFSJ() {
		return CARPARM_GOLBAL_DDGZSKFSJ;
	}

	public void setCARPARM_GOLBAL_DDGZSKFSJ(long cARPARM_GOLBAL_DDGZSKFSJ) {
		CARPARM_GOLBAL_DDGZSKFSJ = cARPARM_GOLBAL_DDGZSKFSJ;
	}

	public long getLightOffTime() {
		return lightOffTime;
	}

	public void setLightOffTime(int lightOffTime) {
		this.lightOffTime = lightOffTime;
	}
	
}
