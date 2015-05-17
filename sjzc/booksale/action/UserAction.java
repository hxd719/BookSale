package cn.sjzc.booksale.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import cn.sjzc.booksale.model.User;
import cn.sjzc.booksale.services.UserService;
import cn.sjzc.booksale.utill.PageModel;
import cn.sjzc.booksale.utill.PagerVO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;


@Controller("UserAction")
public class UserAction implements ModelDriven<User> {

	
	@Resource
	private UserService service;
	
	private String searchKey;
	
	private PageModel pm;
	
	private User user;
	@Override
	public User getModel() {
		if(user == null) {
			user = new User();
		}
		return null;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public PageModel getPm() {
		return pm;
	}
	public void setPm(PageModel pm) {
		this.pm = pm;
	}
	
	public String list() {
		if(searchKey == null) {
			searchKey = "";
		}
		PagerVO data = service.getUserList(10, pm.getPageNum(), searchKey);
		ActionContext.getContext().put("data", data);
		ActionContext.getContext().put("searchKey", searchKey);
		return "success";
	}

	public String delete() {
		String id = ServletActionContext.getRequest().getParameter("id1");
		service.delUser(Integer.valueOf(id));
		return "add";
	}
	
	public String deletebatch() {
		if(ServletActionContext.getRequest().getParameterValues("ids") != null) {
			String ids[] = ServletActionContext.getRequest().getParameterValues("ids");
			for (int i = 0; i < ids.length; i++) {
				service.delUser(Integer.parseInt(ids[i]));
			}
		}
		return "add";
	}
	
	
}
