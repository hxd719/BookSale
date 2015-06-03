package cn.sjzc.booksale.controllers;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import cn.sjzc.booksale.controllers.commandinfo.MessageCommandInfo;
import cn.sjzc.booksale.model.Message;
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
		MessageCommandInfo commandInfo = null;
		try {
			commandInfo = getCommandInfo(req.commandInfo, MessageCommandInfo.class);
		} catch (Exception e) {
			rep.resultTip = "数据非法";
			return rep;
		}
		User u = userService.getUserByToken(req.token);
		if(u == null) {
			rep.resultTip = "请登录";
			return rep;
		}
		
		List<Message> data = service.getNewMessage(u.getId(), commandInfo.getPageSize(), commandInfo.getPageNum());
		for (Message message : data) {
			if(message.getSellInfo().getBook().getCover() == null) {
				message.getSellInfo().getBook().setCover("http://123.57.219.149/Image/0000000000.jpg");
			} else {
				if(message.getSellInfo().getBook().getCover().indexOf("http") > -1) {
				} else {
					message.getSellInfo().getBook().setCover("http://123.57.219.149"+message.getSellInfo().getBook().getCover());
				}
			}
		}
		rep.responseData = data;
		return rep;
	}
	
	
	
	


}
