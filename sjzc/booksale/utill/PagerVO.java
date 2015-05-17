package cn.sjzc.booksale.utill;

import java.util.List;

public class PagerVO {
	private int total;
	private int currenPageNum;
	private List<?> datas;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<?> getDatas() {
		return datas;
	}
	public void setDatas(List<?> datas) {
		this.datas = datas;
	}
	public int getCurrenPageNum() {
		return currenPageNum;
	}
	public void setCurrenPageNum(int currenPageNum) {
		this.currenPageNum = currenPageNum;
	}
	
	
}
