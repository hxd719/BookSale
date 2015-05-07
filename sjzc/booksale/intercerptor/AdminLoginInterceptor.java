package cn.sjzc.booksale.intercerptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AdminLoginInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String user = (String)invocation.getInvocationContext().getSession().get("LOGIN_USER");
		if(user == null) {
			return "login";
		}
		return invocation.invoke();
	}

}
