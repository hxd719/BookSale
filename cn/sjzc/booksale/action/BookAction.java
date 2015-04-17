package cn.sjzc.booksale.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import cn.sjzc.booksale.model.Book;
import cn.sjzc.booksale.services.BookService;

import com.opensymphony.xwork2.ModelDriven;

@Controller("bookaction")
public class BookAction implements ModelDriven<Book> {

	@Resource
	private BookService service;
	
	private String sSearch;
	private Book book;

	@Override
	public Book getModel() {
		if(book == null) {
			book = new Book();
		}
		return book;
	}
	

	public String getSSearch() {
		return sSearch;
	}

	public void setSSearch(String sSearch) {
		this.sSearch = sSearch;
		service.getClass();
	}




	

}
