package cn.sjzc.booksale.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.sjzc.booksale.utill.CacheClientPool;
import cn.sjzc.booksale.utill.MD5;
import cn.sjzc.booksale.utill.NewInfo;
import cn.sjzc.booksale.utill.SdkRequest;
import cn.sjzc.booksale.utill.SdkResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BookServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BeanFactory factor;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		factor = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		ObjectMapper objectMapper = new ObjectMapper(); // create once, reuse
		SdkResponse rep = new SdkResponse();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		boolean flag = ServletFileUpload.isMultipartContent(request);
        InputStream is =null;
        String requestString = null;
        String name = null;
        String dir = this.getServletContext().getRealPath("/upload");
		File dirFile = new File(dir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		if (flag) {
		    try {
		        ServletFileUpload upload = new ServletFileUpload();
		        FileItemIterator iter = upload.getItemIterator(request);
		        while (iter.hasNext()){
		            FileItemStream fis = iter.next();
		            is = fis.openStream();
		            if(fis.isFormField()){
		                if(fis.getFieldName().equals("requestString")){
		                	requestString = Streams.asString(is);
		                }
		            }else{
		                String[] names = fis.getName().split(".");
		                byte[] buff = new byte[1024];
		                int len = 0;
		                ByteArrayOutputStream bos = new ByteArrayOutputStream();
		                while ((len=is.read(buff))>0){
		                	bos.write(buff,0,len);
		                }
		                name = MD5.getMD5(new Date().toString())+"."+names[names.length-1];
		                File f = new File(dirFile,name);
		                FileOutputStream out = new FileOutputStream(f);
		                bos.writeTo(out);
		                bos.close();
		            }
		        }
		    }catch (Exception e){
		    	e.printStackTrace();
		    } 
	    }
            
		System.out.println(requestString);
		String author = request.getHeader("MDAuthentication");
		if(true || MD5.getMD5("book"+requestString+"sale").equals(author)){
			SdkRequest req = objectMapper.readValue(requestString, SdkRequest.class);
			req.url = "/upload/"+name;
			rep = dispatch(request,req);
//			Map<String, Object> data = new HashMap<String, Object>();
//			try{
//				Integer id = Integer.valueOf(req.token.split("\\|")[0]);
//				Object o = CacheClientPool.getClient().get(id.toString());
//				data.put("newMesssage",o );
//			}catch(Exception e) {
//			}
//			if(req.lastRequestTime!=null && req.lastRequestTime < NewInfo.time){
//				data.put("newInfo", true);
//			}
//			rep.pushData = data;
		} else {
			rep.resultTip = "请求不合法";
		}
		
		
		
		String repsonseString =  objectMapper.writeValueAsString(rep);
		System.out.println(repsonseString);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(repsonseString);
		out.flush();
		out.close();
	}


	
	
	private SdkResponse dispatch(HttpServletRequest req,SdkRequest request) {
		
		String[] commandTokens = request.command.split("/");
		String methodNameString = commandTokens[commandTokens.length - 1];
		String classNameString = commandTokens[0];
		String beanName = null;
		SdkResponse response = null;
		for (int i = 1; i < commandTokens.length - 1; i++)
			classNameString += "." + commandTokens[i];
		try {
			beanName = classNameString+"Controller";
			classNameString = "cn.sjzc.booksale.controllers." + classNameString+"Controller";
			Class<?> c = Class.forName(classNameString);
			Method method = c.getMethod(methodNameString,
					new Class[] { SdkRequest.class });
			// Method method = c.getDeclaredMethod(methodNameString);
			response = (SdkResponse) method.invoke(factor.getBean(beanName), request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (response == null) {
			response = new SdkResponse();
		}

		return response;
	}
}
