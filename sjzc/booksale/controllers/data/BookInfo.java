package cn.sjzc.booksale.controllers.data;

import cn.sjzc.booksale.model.Book;

public class BookInfo {
	private int id;
	private String ISBN;
	private String name;
	private String author;
	private String press;
	private String cover;
	
	
	public BookInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public BookInfo(Book book) {
		this.author = book.getAuthor();
		this.cover = "http://123.57.219.149"+book.getCover();
		this.id = book.getId();
		this.ISBN = book.getISBN();
		this.name = book.getName();
		this.press = book.getPress();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getISBN() {
		return ISBN;
	}


	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPress() {
		return press;
	}


	public void setPress(String press) {
		this.press = press;
	}


	public String getCover() {
		return cover;
	}


	public void setCover(String cover) {
		this.cover = cover;
	}
	

	
	
	
	
}
