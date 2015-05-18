package cn.sjzc.booksale.intercerptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {
	private String[] urlPatterns = {"Servlet",".jsp"};
	private String[] escapeUrls = {"LoginServlet","login.jsp"};

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		String uri = request.getRequestURI();
		
		
		if(this.urlPatter(uri)) {
			if(!this.escapeUrl(uri)) {
				if(request.getSession().getAttribute("LOGIN_USER") == null) {
					response.sendRedirect(request.getContextPath()+"/backend/login.jsp");
				}
			}
		}
		
		
		chain.doFilter(request, response);
	}
	
	private boolean urlPatter(String url) {
		for(int i=0;i<urlPatterns.length;i++) {
			if(url.indexOf(urlPatterns[i]) > -1) {
				return true;
			}
		}
		return false;
	}
	
	private boolean escapeUrl(String url) {
		for(int i=0;i<escapeUrls.length;i++) {
			if(url.indexOf(escapeUrls[i]) > -1) {
				return true;
			}
		}
		return false;
	}
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.urlPatterns = config.getInitParameter("urlPattern").replace("��", ",").split(",");
		this.escapeUrls = config.getInitParameter("escapeUrl").replace("��", ",").split(",");

	}

}
