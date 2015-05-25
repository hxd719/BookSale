package cn.sjzc.booksale.model;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Message {
	private int id;
	@JsonIgnore
	private Integer userId;
	private SellInfor sellInfo;
	private Date time;
	private Integer state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public SellInfor getSellInfo() {
		return sellInfo;
	}
	public void setSellInfo(SellInfor sellInfo) {
		this.sellInfo = sellInfo;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	

}
