package cn.sjzc.booksale.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import cn.sjzc.booksale.model.Admin;
import cn.sjzc.booksale.services.AdminService;

import com.opensymphony.xwork2.ModelDriven;

@Controller("adminaction")
public class AdminAction implements ModelDriven<Admin> {
	@Resource
	private AdminService service;
	private String password1;
	private Admin admin;
	@Override
	public Admin getModel() {
		if(admin == null) {
			admin = new Admin();
		}
		return admin;
	}

	public String getPassword1() {
		return password1;
	}

	public String login() {
		Admin a = service.getAdmin(admin);
		if(a != null && a.getPassword().equals(admin.getPassword())) {
			ServletActionContext.getRequest().getSession().setAttribute("LOGIN_USER", admin.getName());
			return "success";
		}
//		if(flag == true) {
//			if(!ServletActionContext.getRequest().getParameter("chknumber").equalsIgnoreCase((String)ServletActionContext.getRequest().getSession().getAttribute("LOGIN_CODE"))) {
//				ServletActionContext.getRequest().getSession().setAttribute("error", "验证码不正确");
//				return "fail";
//			} 
//			ServletActionContext.getRequest().getSession().setAttribute("LOGIN_USER", admin.getName());
//			return "success";
//		}
		ServletActionContext.getRequest().getSession().setAttribute("error", "用户名或密码不正确");
		return "fail";
	}
	
//	public String save() {
//		String n = ServletActionContext.getRequest().getSession().getAttribute("LOGIN_USER").toString();
//		Admin u = service.getUserByName(n);
//		if(u.getPassword().equals(password1)) {
//			if(null == service.getUserByName(admin.getName())) {
//				service.save(admin);
//				return "su";
//			} else {
//				ServletActionContext.getRequest().getSession().setAttribute("error1", "已有该用户");
//				return "fa";
//			}
//		} else {
//			ServletActionContext.getRequest().getSession().setAttribute("error1", "当前用户密码不正确ȷ");
//			return "fa";
//		}
//		
//		
//		
//	}
	
	
	
	public void setPassword1(String password1) {
		this.password1 = password1;
	}

//	public String list() {
//		
//		List<User> users = (List)service.query();
//		ActionContext.getContext().put("users", users);
//		return "list";
//	}
//	
//	public String update() {
//		User u = service.getUserByName(user.getName());
//		if(u.getPassword().equals(user.getPassword())) {
//			u.setPassword(password1);
//			service.update(u);
//			return "su";
//		} else {
//			return "fa";
//		}
//		
//	}
	
//	public String delete() {
//		User u = service.query(user.getId());
//		if(u.getPassword().equals(user.getPassword())) {
//			service.delete(user.getId());
//			return "su";
//		} else 
//			ServletActionContext.getRequest().getSession().setAttribute("error1", "���벻��ȷ");
//			return "fa";
//	}
//	
//	
	
	public String update() {
		
		int id = admin.getId();
		Admin a = service.getAdmin(id);
		if(a.getPassword().equals(password1)) {
			a.setPassword(admin.getPassword());
			service.update(a);
			return "su";
		}
		return "fa";
	}
	
	public String loginout() {
		
		ServletActionContext.getRequest().getSession().invalidate();
		return "fail";
	}

	
}
