package cn.sjzc.booksale.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import cn.sjzc.booksale.model.Category;
import cn.sjzc.booksale.services.CategoryService;
import cn.sjzc.booksale.utill.PageModel;
import cn.sjzc.booksale.utill.PagerVO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller("CategoryAction")
public class CategoryAction implements ModelDriven<Category> {

	@Resource
	private CategoryService service;
	
	private Category book;
	
	private PageModel pm;

	@Override
	public Category getModel() {
		if(book == null) {
			book = new Category();
		}
		return book;
	}
	public PageModel getPm() {
		return pm;
	}
	public void setPm(PageModel pm) {
		this.pm = pm;
	}
	
	
	public String list() {
		PagerVO data = service.getCategoryLists(pm.getPageNum(), null);
		ActionContext.getContext().put("data", data);
		ActionContext.getContext().put("searchKey", null);
		return "success";
	}
	
	public String add() {
		service.add(book);
		return "add";
	}

	public String modify() {
		ActionContext.getContext().put("book", service.getCategoryById(book.getId()));
		return "success";
	}
	
	public String update() {
		service.update(book);
		return "add";
	}
	
	
	public String delete() {
		service.delete(book.getId());
		return "add";
	}
	
	public String deletebatch() {
		if(ServletActionContext.getRequest().getParameterValues("ids") != null) {
			String ids[] = ServletActionContext.getRequest().getParameterValues("ids");
			for (int i = 0; i < ids.length; i++) {
				service.delete(Integer.parseInt(ids[i]));
			}
		}
		return "add";
	}
	

}
