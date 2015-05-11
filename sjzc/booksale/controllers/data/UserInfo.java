package cn.sjzc.booksale.controllers.data;

import java.util.Date;

import cn.sjzc.booksale.model.User;

public class UserInfo {
	private String name;
	private String phone;
	private Integer sex;
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
		this.phone = u.getTel();
		this.expired = u.getExpired();
		this.sex = u.getSex();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
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
