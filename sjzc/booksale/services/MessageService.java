package cn.sjzc.booksale.services;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sjzc.booksale.dao.MessageDao;
import cn.sjzc.booksale.model.Message;
import cn.sjzc.booksale.model.SellInfor;
import cn.sjzc.booksale.utill.CacheClientPool;

@Service("MessageService")
public class MessageService {
	
	@Resource
	private MessageDao dao;
	
	
	public List<Message> getNewMessage(Integer userId,Integer pageSize,Integer pageNum) {
		
		return dao.getMessage(userId, pageSize, pageNum);
		
	}
	
	
	public void addNewMessage(Integer userId,SellInfor infor) {
		Message m = new Message();
		m.setSellInfo(infor);
		m.setUserId(userId);
		m.setState(0);
		m.setTime(new Date());
		dao.save(m);
		CacheClientPool.getClient().set(userId.toString(), 10000, true);
	}
	

}
