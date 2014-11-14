package com.scu.Model;
/*该类抽象了灯光的类型和灯光考试时的语音信息*/
public class Lamps_Type {
	private Integer type_no;//主键自动生成
	private String Type_title;//灯光考试的类型
	private String type_desc;//灯光考试的详细信息
	private String mp3_file;//灯光考试的MP3提示文件名
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
