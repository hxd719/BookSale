package cn.sjzc.booksale.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import cn.sjzc.booksale.model.SellInfor;
import cn.sjzc.booksale.services.InformationService;
import cn.sjzc.booksale.utill.PageModel;
import cn.sjzc.booksale.utill.PagerVO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller("SellInfoAction")
public class SellInfoAction implements ModelDriven<SellInfor> {

	@Resource
	private InformationService service;
	
	private String searchKey;
	private SellInfor entity;
	
	private PageModel pm;

	@Override
	public SellInfor getModel() {
		if(entity == null) {
			entity = new SellInfor();
		}
		return entity;
	}
	public PageModel getPm() {
		return pm;
	}
	public void setPm(PageModel pm) {
		this.pm = pm;
	}
	
	

	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String list() {
		if(searchKey == null) {
			searchKey = "";
		}
		PagerVO data = service.getSellInfoList(10, pm.getPageNum(), searchKey);
		ActionContext.getContext().put("data", data);
		ActionContext.getContext().put("searchKey", searchKey);
		return "success";
	}

	
	
	public String delete() {
		service.deleteSellInfor(entity.getId());
		return "add";
	}
	
	public String deletebatch() {
		if(ServletActionContext.getRequest().getParameterValues("ids") != null) {
			String ids[] = ServletActionContext.getRequest().getParameterValues("ids");
			for (int i = 0; i < ids.length; i++) {
				service.deleteSellInfor(Integer.parseInt(ids[i]));
			}
		}
		return "add";
	}
	

}
