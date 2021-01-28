package com.javaex.vo;

public class GuestBookVo {

	// 필드
	private int no;
	private String name, password, content, regDate;

	// 생성자
	public GuestBookVo() {
		super();
	}
	
	public GuestBookVo(int no) {
		super();
		this.no = no;
	}

	public GuestBookVo(int no, String password) {
		super();
		this.no = no;
		this.password = password;
	}

	public GuestBookVo(String name, String password, String content) {
		super();
		this.name = name;
		this.password = password;
		this.content = content;
	}

	public GuestBookVo(int no, String name, String content, String regDate) {
		super();
		this.no = no;
		this.name = name;
		this.content = content;
		this.regDate = regDate;
	}

	public GuestBookVo(int no, String name, String password, String content, String regDate) {
		super();
		this.no = no;
		this.name = name;
		this.password = password;
		this.content = content;
		this.regDate = regDate;
	}

	// 메소드 g.s
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	// 메소드 일반
	@Override
	public String toString() {
		return "GuestBookVo [no=" + no + ", name=" + name + ", password=" + password + ", content=" + content
				+ ", regDate=" + regDate + "]";
	}
	
	
	
}
