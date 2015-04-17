package cn.sjzc.booksale.utill;

import java.util.List;

public class PagerVO {
	private int total;
	private List<Object> datas;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Object> getDatas() {
		return datas;
	}
	public void setDatas(List<Object> datas) {
		this.datas = datas;
	}
}
