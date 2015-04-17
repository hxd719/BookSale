package cn.sjzc.booksale.utill;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.sjzc.booksale.model.Book;
import com.sun.xml.internal.fastinfoset.stax.events.Util;

public class GetBookFromG {
	
	public static Book getBookByISBN(String isbn) {
		String rerular = "题名与责任[\\s\\S]*载体形态项 ";
		String url = "http://opac.nlc.gov.cn/F?find_base=NLC01&find_base=NLC09&func=find-m&find_code=ISB&filter_code_1=WLN&filter_code_2=WYR&filter_code_3=WYR&filter_code_4=WFM&filter_request_4=&filter_code_5=WSL&request=";
		String url_ = url+isbn;
		String htmls = getPageSource(url_.replace(" ", ""), "utf-8");
		String bookInfor = null;
		Book book = null;
		if(htmls != null) {
			bookInfor = getBookInfor(htmls,rerular);
			if(bookInfor != null) {
				book = turnOver(bookInfor.replace(" ", ""));
				book.setISBN(isbn);
			}
		}
		return book;
	}
	
	
	
	// 根据网页源码获取图书信息
	public static String getBookInfor(String htmls,String regular) {
		String s = null;
		if (!Util.isEmptyString(htmls)) {
			Pattern pattern = Pattern.compile(regular);
			Matcher matcher = pattern.matcher(htmls);
				while (matcher.find()) {
					int gc = matcher.groupCount();
					for(int e = 0; e <= gc; e++) {
						s = matcher.group(e);
						
					}
				}
		}
		return s;
	}
	
	//将字符转换成Book
	public static Book turnOver(String s) {
		Book book = new Book();
		String nameAndauthor = s.split("\\);'>")[1];
		String press = s.split("\\);'>")[2]; 
		nameAndauthor = nameAndauthor.split("</A>")[0];
		nameAndauthor = nameAndauthor.replace("&nbsp;", "");
		String name = nameAndauthor.split("/")[0];
		String author = nameAndauthor.split("/")[1];
		press = press.split("</A>")[0];
		press = press.replace("&nbsp;", "");
		book.setAuthor(author);
		book.setName(name);
		book.setPress(press);
		return book;
	}
	
	
	//获取网页源码
	public static String getPageSource(String pageUrl, String encoding) {
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(pageUrl);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), encoding));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			in.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
		return sb.toString();
	}

}
