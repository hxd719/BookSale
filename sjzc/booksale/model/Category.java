package cn.sjzc.booksale.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Category {
	
	private int id;
	private String name;
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
	
	
}
