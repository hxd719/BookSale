package cn.sjzc.booksale.intercerptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.sjzc.booksale.utill.SystemContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PagerInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		try{
			SystemContext.setOffset(getOffset(request));
			SystemContext.setPagesize(getPagesize(request));
			
			return invocation.invoke();
			
		}finally{
			SystemContext.removeOffset();
			SystemContext.removePagesize();
		}
	}
	

	protected int getOffset(HttpServletRequest request){
		int offset = 0;
		try {
			offset = Integer.parseInt(request.getParameter("iDisplayStart"));
			
		} catch (Exception ignore) {
		}
		return offset;
	}
	
	protected int getPagesize(HttpServletRequest request){
		int pagesize = 5;

		if (request.getParameter("iDisplayLength") != null) {
			request.getSession().setAttribute("iDisplayLength",
					Integer.parseInt(request.getParameter("iDisplayLength")));
		}
		
		
		
		Integer ps = (Integer) request.getSession().getAttribute("iDisplayLength");
		if (ps == null) {
			request.getSession().setAttribute("iDisplayLength", pagesize);
		} else {
			pagesize = ps;
		}
		return pagesize;
	}

}
