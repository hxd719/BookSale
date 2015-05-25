package cn.sjzc.booksale.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Book {
	private int id;
	private String ISBN;
	private String name;
	private String author;
	private String press;
	private String cover;
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
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getCover() {

		
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	
	

}
