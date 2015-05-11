package cn.sjzc.booksale.controllers;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import cn.sjzc.booksale.controllers.commandinfo.BookCommandInfo;
import cn.sjzc.booksale.controllers.data.BookInfo;
import cn.sjzc.booksale.model.Book;
import cn.sjzc.booksale.services.BookService;
import cn.sjzc.booksale.utill.SdkRequest;
import cn.sjzc.booksale.utill.SdkResponse;
@Controller("BookController")
public class BookController extends AbstractController {
	
	@Resource
	private BookService service;
	
	
	public SdkResponse search(SdkRequest req) throws IOException	{
		SdkResponse rep = new SdkResponse();
		BookCommandInfo commandInfo = null;
		try {
			commandInfo = getCommandInfo(req.commandInfo, BookCommandInfo.class);
		} catch (Exception e) {
			rep.resultTip = "数据非法";
			return rep;
		}
		if(commandInfo.ISBN == null) {
			rep.resultTip = "数据为空";
			return rep;
		}
		Book book = service.getBookByISBN(commandInfo.ISBN);
		if(book == null) {
			rep.resultTip="无记录";
			return rep;
		}
		rep.responseData = new BookInfo(book);
		return rep;
	}
	
	
	
	


}
