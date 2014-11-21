package com.scu.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
	/**
	 * 连接数据库
	 * 
	 * @param fileName
	 * @return Connection
	 */
	private String DBDriverClass = "sun.jdbc.odbc.JdbcOdbcDriver";
	private String DBUrl = "jdbc:odbc:EXAM";
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	public DBHelper()
	{
		setDBUrl();
	}
	public void conn() {
		try {
			Class.forName(DBDriverClass);
			this.conn = DriverManager.getConnection(DBUrl);
			this.stmt = this.conn.createStatement();
		//	System.out.println("连接数据库成功");
		} catch (Exception e) {
			System.out.println("数据库连接失败！");
		}
	}
	public void close() {
		try {
			this.stmt.close();
			this.conn.close();
		} catch (Exception localException) {
			System.out.println("数据库关闭失败!");
		}
	}
	public ResultSet Query(String tableName, String condition, String cols) {
		try {
			String sql = "select " + cols + " from " + tableName;
			if (condition != null)
				sql = sql + " where " + condition;
			this.rs = this.stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.rs;
	}

	public ResultSet Query(String sql) {
		try {
			conn();
			this.rs = this.stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.rs;
	}

	public String getDBUrl() {
		//构造函数中初始化信号源
		
		return DBUrl;
	}
	public void setDBUrl() {
		String fileName="./signalSource.config";
		String sourceSource="";
		try {
			BufferedReader br=new BufferedReader(new FileReader(fileName));
			String line=br.readLine();
			line=br.readLine();
			int beginIndex=line.indexOf("\"");
			int endIndex=line.lastIndexOf("\"");
			sourceSource=line.substring(beginIndex+1, endIndex);
			DBUrl="jdbc:odbc:"+sourceSource.trim();
	//		System.out.println("DBUrl------------>:"+DBUrl);
			br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	public int QueryCnt(String tableName, String condition) {
		int cnt = 0;
		try {
			String sql = "select count(*) as CNT from " + tableName;
			if (condition != null) {
				sql = sql + " where " + condition;
			}
			this.rs = this.stmt.executeQuery(sql);
			if ((this.rs != null) && (this.rs.next()))
				cnt = this.rs.getInt("CNT");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	public int Delete(String tableName, String condition) {
		int iret = 0;
		try {
			String sql = "delete from " + tableName;
			if (condition != null)
				sql = sql + " where " + condition;
			PreparedStatement ps = this.conn.prepareStatement(sql);
			iret = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return iret;
	}

	public int Update(String tableName, String sets, String condition) {
		int iret = 0;
		try {
			conn();
			String sql = "update " + tableName + " set " + sets;
			if (condition != null)
				sql = sql + " where " + condition;
			System.out.println(sql);
			PreparedStatement ps = this.conn.prepareStatement(sql);
			iret = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return iret;
	}

	public int Insert(String insertSql) {
		int iret = 0;
		try {
			PreparedStatement ps = this.conn.prepareStatement(insertSql);
			iret = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return iret;
	}
	/**
	 * @author feng
	 * @param parmName 配置变量在数据库中的名字
	 * @return string 配置变量的值
	 */
	public String QureyConfig(String parmName)
	{
		String res="";
		if(parmName.length()>0)
		{
			try {
				String sql = "select pvalue from  carparmset where  parmname='" + parmName+"'";
		//		System.out.println(sql);
				conn();
				this.rs = this.stmt.executeQuery(sql);
				while(rs.next())
				{
					res=rs.getString(1);
				}
				
			} catch (SQLException e) {
				System.out.println("数据库sql异常");
				e.printStackTrace();
			}finally
			{
				try {
					if(rs != null){
				//		rs.close();
					}
					close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return res;
	}
	
	/**
	 * @author feng
	 * @param parmname parmname 
	 * @param pValue  pvalue
	 */
	public void updateConfig(String  parmname,String pValue)
	{
		try {
			conn();
			String sql = "update carparmset set pvalue= '"+pValue+"' where  parmname= '" +  parmname+"'";
			PreparedStatement ps = this.conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
