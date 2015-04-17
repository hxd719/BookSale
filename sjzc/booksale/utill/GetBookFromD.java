package cn.sjzc.booksale.utill;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.sjzc.booksale.model.Book;

import com.sun.xml.internal.fastinfoset.stax.events.Util;

public class GetBookFromD {
	
	public static Book getBookByISBN(String isbn) {
		String rerular = "subtitle[\\s\\S]*isbn10";
		String url = "https://api.douban.com/v2/book/isbn/:";
		String url_ = url+isbn;
		String htmls = getPageSource(url_.replace(" ", ""), "utf-8");
		String bookInfor = null;
		Book book = null;
		if(htmls != null) {
			bookInfor = getBookInfor(htmls,rerular);
			if(bookInfor != null) {
				book = turnOver(bookInfor.replace(" ", ""));
				book.setISBN(isbn);
				try {
					String path = Book.class.getClass().getResource("/").getPath().split("WEB-INF")[0]+"Image";
					downloadImage(book.getCover(), isbn+".jpg", path);
					book.setCover("Image/"+isbn+".jpg");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		String nameAndauthor = s.split("\"],\"pubdate")[0];
		nameAndauthor = nameAndauthor.replace("[", "<>");
		String name = nameAndauthor.split("\",\"author\":<>\"")[0].split("subtitle\":\"")[1];
		String author = nameAndauthor.split("\",\"author\":<>\"")[1];
		String pressAndCover = s.split("\"],\"pubdate")[1].split("small\":\"")[1];
		String coverURL = pressAndCover.split("\",\"large")[0].replace("\\", "");
		pressAndCover = pressAndCover.split("publisher\":\"")[1];
		String press = pressAndCover.split("\",\"")[0]; 
		book.setAuthor(author);
		book.setName(name);
		book.setPress(press);
		book.setCover(coverURL);
		return book;
	}
	
	
	
	//普通下载图片
	public static void downloadImage(String urlString, String filename, String savePath)throws Exception {
		String path;
		if(savePath == ""){
			path = "D:\\Picture";
		}
		path = savePath;
		URL url = new URL(urlString);
		URLConnection con = url.openConnection();
		InputStream is = con.getInputStream();
		byte[] bs = new byte[1024];
		int len;
		File sf = new File(path.substring(0));
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		os.close();
		is.close();
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
