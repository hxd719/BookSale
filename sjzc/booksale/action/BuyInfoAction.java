package cn.sjzc.booksale.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import cn.sjzc.booksale.model.BuyInfor;
import cn.sjzc.booksale.services.InformationService;
import cn.sjzc.booksale.utill.PageModel;
import cn.sjzc.booksale.utill.PagerVO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller("BuyInfoAction")
public class BuyInfoAction implements ModelDriven<BuyInfor> {

	@Resource
	private InformationService service;
	
	private String searchKey;
	private BuyInfor entity;
	
	private PageModel pm;

	@Override
	public BuyInfor getModel() {
		if(entity == null) {
			entity = new BuyInfor();
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
		PagerVO data = service.getBuyInfoList(10, pm.getPageNum(), searchKey);
		ActionContext.getContext().put("data", data);
		ActionContext.getContext().put("searchKey", searchKey);
		return "success";
	}

	
	
	public String delete() {
		service.deleteBuyInfor(entity.getId());
		return "add";
	}
	
	public String deletebatch() {
		if(ServletActionContext.getRequest().getParameterValues("ids") != null) {
			String ids[] = ServletActionContext.getRequest().getParameterValues("ids");
			for (int i = 0; i < ids.length; i++) {
				service.deleteBuyInfor(Integer.parseInt(ids[i]));
			}
		}
		return "add";
	}
	

}
