package com.scu.Model;
/*��������˴�����Ϣ������������͡��������ϸ��Ϣ�Լ��۷����*/
public class MarkRule {
	private long mID;// ����
	private Integer litemno;//���ڵڼ���ģ��Ĵ���
	private String markdepend;//�������ϸ��Ϣ
	private Integer markreal;//�۷����
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
