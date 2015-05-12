package cn.sjzc.booksale.model;
import java.util.Date;

public class Message {
	private Integer id;
	private User user;
	private SellInfor sellInfo;
	private BuyInfor buyInfo;
	private Date time;
	private Integer state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SellInfor getSellInfo() {
		return sellInfo;
	}
	public void setSellInfo(SellInfor sellInfo) {
		this.sellInfo = sellInfo;
	}
	public BuyInfor getBuyInfo() {
		return buyInfo;
	}
	public void setBuyInfo(BuyInfor buyInfo) {
		this.buyInfo = buyInfo;
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
