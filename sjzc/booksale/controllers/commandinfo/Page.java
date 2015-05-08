package cn.sjzc.booksale.controllers.commandinfo;

public class Page {

	private Integer pageNum;
	private Integer pageSize;
	public Integer getPageNum() {
		if(pageNum==null || pageNum < 1) {
			pageNum = 1;
		}
		return (pageNum-1)*getPageSize();
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		if(pageSize == null || pageSize <1) {
			pageSize = 20;
		}
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
