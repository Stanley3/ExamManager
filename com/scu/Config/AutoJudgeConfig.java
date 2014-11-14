package com.scu.Config;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.scu.Utils.DBHelper;
/**
 * 自动评判配置类
 * @author 孙晓雨 2014.10.9
 *
 */
public class AutoJudgeConfig {
	public static AutoJudgeConfig instance = null;
	
	//自动评判是否开启
	private boolean isOpenAutoJudge;
	//减速类达到安全车速是否还需要刹车
	private boolean isNeedBrake;
	//gps端口
	private String com = "com1";
	
	private DBHelper dbHelper;
	private  AutoJudgeConfig() {
		dbHelper = new DBHelper();
		isOpenAutoJudge = true;
		isNeedBrake = true;
		com = "com1";
	}
	public static AutoJudgeConfig getInstance(){
		if(instance == null){
			instance = new AutoJudgeConfig();
		}
		return instance;
	}
	
	public boolean isOpenAutoJudge() {
		return isOpenAutoJudge;
	}
	public void setOpenAutoJudge(boolean isOpenAutoJudge) {
		this.isOpenAutoJudge = isOpenAutoJudge;
	}
	public boolean isNeedBrake() {
		return isNeedBrake;
	}
	public void setNeedBrake(boolean isNeedBrake) {
		this.isNeedBrake = isNeedBrake;
	}
	public void setCom(String com)
	{
		this.com = com;
		dbHelper.Update("gps", "com='"+this.com + "'", null);
	}
	public String getCom() 
	{
		ResultSet rs = dbHelper.Query("select com from gps");
		try {
			if(rs.next())
				this.com = rs.getString("com");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		return this.com;
	}
}
