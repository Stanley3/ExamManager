package com.scu.Model;
/*该类抽象了错误信息包括错误的类型、错误的详细信息以及扣分情况*/
public class MarkRule {
	private long mID;// 主键
	private Integer litemno;//属于第几个模块的错误
	private String markdepend;//错误的详细信息
	private Integer markreal;//扣分情况
	public MarkRule(Integer litemno, String markdepend, Integer markreal) {
		super();
		this.litemno = litemno;
		this.markdepend = markdepend;
		this.markreal = markreal;
	}
	public Integer getLitemno() {
		return litemno;
	}
	public void setLitemno(Integer litemno) {
		this.litemno = litemno;
	}
	public String getMarkdepend() {
		return markdepend;
	}
	public void setMarkdepend(String markdepend) {
		this.markdepend = markdepend;
	}
	public Integer getMarkreal() {
		return markreal;
	}
	public void setMarkreal(Integer markreal) {
		this.markreal = markreal;
	}
	public long getmID() {
		return mID;
	}
	
}
