package cn.sjzc.booksale.controllers.data;

import java.util.Date;

import cn.sjzc.booksale.model.User;

public class UserInfo {
	private String name;
	private String tel;
	private int sex;
	private String token;
	
	
	private Date expired;
	private Date date;
	
	
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public UserInfo(User u) {
		this.date = u.getDate();
		this.expired = u.getExpired();
		this.token = u.getToken();
		this.name = u.getName();
		this.tel = u.getTel();
		this.expired = u.getExpired();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getExpired() {
		return expired;
	}
	public void setExpired(Date expired) {
		this.expired = expired;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
