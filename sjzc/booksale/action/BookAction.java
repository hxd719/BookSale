package cn.sjzc.booksale.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import cn.sjzc.booksale.model.Book;
import cn.sjzc.booksale.services.BookService;
import cn.sjzc.booksale.utill.PageModel;
import cn.sjzc.booksale.utill.PagerVO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller("BookAction")
public class BookAction implements ModelDriven<Book> {

	@Resource
	private BookService service;
	
	private String searchKey;
	private Book book;
	
	private PageModel pm;

	@Override
	public Book getModel() {
		if(book == null) {
			book = new Book();
		}
		return book;
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
		PagerVO data = service.getBookLists(pm.getPageNum(),searchKey);
		ActionContext.getContext().put("data", data);
		ActionContext.getContext().put("searchKey", searchKey);
		return "success";
	}
	
	public String add() {
		if(book.getISBN().equals("")||book.getAuthor().equals("")||book.getName().equals("")) {
			return "add";
		}
		service.addBook(book);
		return "add";
	}

	public String modify() {
		ActionContext.getContext().put("book", service.getBookById(book.getId()));
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
