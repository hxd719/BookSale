package cn.sjzc.booksale.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	private int id;
	private String name;
	private String password;
	private String tel;
	private Integer sex;
	private String token;
	private Date expired;
	private Date date;
	@JsonIgnore
	private Set<BuyInfor> buyinfors;
	@JsonIgnore
	private Set<SellInfor> sellinfors;
	
	public Set<BuyInfor> getBuyinfors() {
		if(buyinfors == null){
			buyinfors = new HashSet<BuyInfor>();
		}
		return buyinfors;
	}
	public void setBuyinfors(Set<BuyInfor> buyinfors) {
		this.buyinfors = buyinfors;
	}
	public Set<SellInfor> getSellinfors() {
		if(sellinfors == null) {
			sellinfors = new HashSet<SellInfor>();
		}
		return sellinfors;
	}
	public void setSellinfors(Set<SellInfor> sellinfors) {
		this.sellinfors = sellinfors;
	}
	
	public Date getExpired() {
		return expired;
	}
	public void setExpired(Date expired) {
		this.expired = expired;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
