package cn.sjzc.booksale.action;

import org.springframework.stereotype.Controller;
import cn.sjzc.booksale.model.Admin;
import com.opensymphony.xwork2.ModelDriven;

@Controller("DispatchServletAction")


public class DispatchServletAction implements ModelDriven<Admin> {
	
	public String servlet()
	{
	return "success";
	}

	@Override
	public Admin getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
