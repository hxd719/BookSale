package cn.sjzc.booksale.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sjzc.booksale.dao.MessageDao;
import cn.sjzc.booksale.model.Message;

@Service("MessageService")
public class MessageService {
	
	@Resource
	private MessageDao dao;
	
	
	public List<Message> getNewMessage(Integer userId) {
		
		return dao.getMessage(userId);
		
	}
	

}
