package cn.sjzc.booksale.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sjzc.booksale.dao.BookDao;
import cn.sjzc.booksale.model.Book;
import cn.sjzc.booksale.utill.GetBookFromD;
import cn.sjzc.booksale.utill.GetBookFromG;
import cn.sjzc.booksale.utill.PagerVO;
@Service("BookService")
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

	@SuppressWarnings("unchecked")
	public Book getBookByISBN(String ISBN) {
		Book b = null;
		Book book = new Book();
		book.setISBN(ISBN);
		List<Book> books = (List<Book>)dao.find(book);
		if(!books.isEmpty()) {
			return books.get(0);
		} else {
			b = GetBookFromD.getBookByISBN(ISBN);
			if(b != null) {
				dao.save(b);
				return b;
			} else {
				b = GetBookFromG.getBookByISBN(ISBN);
				if(b != null) {
					dao.save(b);
					return b;
				}
			}
		}
		return b;
	}

	public void addBook(Book book) {
		dao.save(book);
	}
	
	
	public Book getBookById(Integer id) {
		return dao.findById(Book.class, id);
	}
	
	public void delete(Integer id) {
		Book book = dao.findById(Book.class, id);
		dao.del(book);
	}
	
	
	public void update(Book b) {
		dao.update(b);
	}
	
	public PagerVO getBookLists(Integer pageNum,String searchKey) {
		if(searchKey == null) {
			searchKey = "";
		}
		String sql = "select b from Book b  where b.name like ? order by b.id desc";
		PagerVO data = dao.findPaginated(sql,"%"+searchKey+"%", (pageNum-1)*10, 10);
		for (Book book : (List<Book>)data.getDatas()) {
			book.setCover("http://123.57.219.149"+(book.getCover()==null?"/Image/0000000000.jpg":book.getCover()));
		}
		
		return data;
	}
	
	

}
