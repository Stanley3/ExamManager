package com.scu.Utils;

import java.io.File;
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

	public void conn() {
		try {
			Class.forName(DBDriverClass);

			this.conn = DriverManager.getConnection(this.DBUrl);

			this.stmt = this.conn.createStatement();
			
		//	System.out.println("连接数据库成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//
//	public DBHelper()
//	{
//		conn();
//	}
	public void close() {
		try {
			this.stmt.close();
			this.conn.close();
		} catch (Exception localException) {
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
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				while(rs.next())
				{
					res=rs.getString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close();
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
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
