package com.scu.Model;
/*��������˵ƹ�����ͺ͵ƹ⿼��ʱ��������Ϣ*/
public class Lamps_Type {
	private Integer type_no;//�����Զ�����
	private String Type_title;//�ƹ⿼�Ե�����
	private String type_desc;//�ƹ⿼�Ե���ϸ��Ϣ
	private String mp3_file;//�ƹ⿼�Ե�MP3��ʾ�ļ���
	public Lamps_Type(String type_title, String type_desc, String mp3_file) {
		super();
		Type_title = type_title;
		this.type_desc = type_desc;
		this.mp3_file = mp3_file;
	}
	public String getType_title() {
		return Type_title;
	}
	public void setType_title(String type_title) {
		Type_title = type_title;
	}
	public String getType_desc() {
		return type_desc;
	}
	public void setType_desc(String type_desc) {
		this.type_desc = type_desc;
	}
	public String getMp3_file() {
		return mp3_file;
	}
	public void setMp3_file(String mp3_file) {
		this.mp3_file = mp3_file;
	}
	public Integer getType_no() {
		return type_no;
	}
	
	
	
}
