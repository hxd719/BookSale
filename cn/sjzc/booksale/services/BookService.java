package cn.sjzc.booksale.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sjzc.booksale.dao.BookDao;
import cn.sjzc.booksale.model.Book;
import cn.sjzc.booksale.utill.PagerVO;
@Service("bookservice")
public class BookService {
	
	@Resource
	private BookDao dao;



	public PagerVO findBooks(String search) {
		if(search != null) {
			String sql = "select b.ISBN,b.name,b.author,b.press from Book b where b.name like ?";
			return dao.findPaginated(sql,"%"+search+"%");
		} else {
			String sql = "select b.ISBN,b.name,b.author,b.press from Book b";
			return dao.findPaginated(sql);
		}
	}



	public void addBook(Book book) {
		dao.save(book);
	}
	
	
	
	
	

}
