package cn.sjzc.booksale.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.sjzc.booksale.utill.MD5;
import cn.sjzc.booksale.utill.SdkRequest;
import cn.sjzc.booksale.utill.SdkResponse;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
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
		objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		SdkResponse rep = new SdkResponse();
		String requestString = IOUtils.toString(request.getInputStream());
		System.out.println(requestString);
		String author = request.getHeader("MDAuthentication");
		if(true || MD5.getMD5("book"+requestString+"sale").equals(author)){
			SdkRequest req = objectMapper.readValue(requestString, SdkRequest.class);
			rep = dispatch(request,req);
		} else {
			rep.resultTip = "请求不合法";
		}
		String repsonseString =  objectMapper.writeValueAsString(rep);
		response.setContentType("application/json;charset=UTF-8");
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
