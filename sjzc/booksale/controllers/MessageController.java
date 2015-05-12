package cn.sjzc.booksale.controllers;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import cn.sjzc.booksale.model.User;
import cn.sjzc.booksale.services.MessageService;
import cn.sjzc.booksale.services.UserService;
import cn.sjzc.booksale.utill.SdkRequest;
import cn.sjzc.booksale.utill.SdkResponse;
@Controller("MessageController")
public class MessageController extends AbstractController {
	
	@Resource
	private MessageService service;
	
	@Resource
	private UserService userService;
	
	
	public SdkResponse getMessage(SdkRequest req) throws IOException	{
		SdkResponse rep = new SdkResponse();
		User u = userService.getUserByToken(req.token);
		if(u == null) {
			rep.resultTip = "请登录";
			return rep;
		}
		rep.responseData = service.getNewMessage(u.getId());
		return rep;
	}
	
	
	
	


}
