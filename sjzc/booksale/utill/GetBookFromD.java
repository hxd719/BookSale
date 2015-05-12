package cn.sjzc.booksale.utill;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import cn.sjzc.booksale.model.Book;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class GetBookFromD {
	
	public static Book getBookByISBN(String isbn) {
		String url = "https://api.douban.com/v2/book/isbn/:";
		String url_ = url+isbn;
		String htmls = getPageSource(url_.replace(" ", ""), "utf-8");
		Book book = null;
		if(htmls != null && !htmls.equals("")) {
			try {
				book = turnOver(htmls);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(book != null) {
				book.setISBN(isbn);
				try {
					String path = book.getClass().getResource("/").getPath().split("WEB-INF")[0]+"Image";
					downloadImage(book.getCover(), isbn+".jpg", path);
					book.setCover("/Image/"+isbn+".jpg");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return book;
	}
	
	
	
	
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	//将字符转换成Book
	@SuppressWarnings("unchecked")
	public static Book turnOver(String s) {
		Map<String, Object> map = null;
		try {
			map = mapper.readValue(s, new TypeReference<Map<String, Object>>() {
			});
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Book book = new Book();
		String name = map.get("title").toString();
		if(name == null) {
			return null;
		}
		List<String> author = (List<String>) map.get("author");
		String coverURL = map.get("image").toString();
		String press = map.get("publisher").toString();
		if(!author.isEmpty()) {
			book.setAuthor("");
			for (String string : author) {
				book.setAuthor(book.getAuthor()+string);
			}
		}
		book.setName(name);
		book.setPress(press);
		book.setCover(coverURL);
		return book;
	}
	
	
	//普通下载图片
	public static void downloadImage(String urlString, String filename, String savePath)throws Exception {
		String path;
		if(savePath == null){
			path = "/Application/tomcat/webapps/ROOT/Image";
		}
		path = savePath;
		URL url = new URL(urlString);
		URLConnection con = url.openConnection();
		InputStream is = con.getInputStream();
		byte[] bs = new byte[1024];
		int len;
		File sf = new File(path);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		File file = new File(sf.getPath(), filename);
		file.setWritable(true, false);
		if (!file.exists()) {
			file.createNewFile();
		}
		OutputStream os = new FileOutputStream(file);
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
			}
			in.close();
		} catch (Exception ex) {
		}
		return sb.toString();
	}

}
