package cn.sjzc.booksale.action;

import org.springframework.stereotype.Controller;
import cn.sjzc.booksale.model.User;
import com.opensymphony.xwork2.ModelDriven;


@Controller("useraction")
public class UserAction implements ModelDriven<User> {

	private User user;
	@Override
	public User getModel() {
		if(user == null) {
			user = new User();
		}
		return null;
	}
	
	public void login() {
		
	}

}
