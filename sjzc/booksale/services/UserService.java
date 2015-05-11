package cn.sjzc.booksale.services;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sjzc.booksale.controllers.commandinfo.UserCommandInfo;
import cn.sjzc.booksale.dao.UserDao;
import cn.sjzc.booksale.model.User;
import cn.sjzc.booksale.utill.MD5;

@Service("UserService")
public class UserService {
	
	@Resource
	private UserDao dao;
	
	
	public User addUser(UserCommandInfo commandInfo) {
		Date date = new Date();
		User user = new User();
		user.setDate(date);
		user.setExpired(date);
		user.setExpired(null);
		user.setName(commandInfo.name);
		user.setPassword(MD5.getMD5(commandInfo.password));
		user.setSex(commandInfo.sex);
		user.setTel(commandInfo.phone);
		user.setToken(null);
		dao.save(user);
		user.setToken(user.getId()+"|"+MD5.getMD5(date.getTime()+commandInfo.phone));
		dao.update(user);
		return user;
		
	}
	
	public User login(UserCommandInfo commandInfo) {
		User user = getUserByPhone(commandInfo.phone);
		String password = MD5.getMD5(commandInfo.password);
		if(user == null) {
			return null;
		}
		if(user.getPassword().equals(password)) {
			user.setExpired(new Date());
			if(user.getToken() == null) {
				user.setToken(user.getId()+"|"+MD5.getMD5(commandInfo.phone));
			}
			dao.update(user);
			return user;
		} else {
			return null;
		}
		
	}
	
	public User forgetPassword(UserCommandInfo commandInfo) {
		User user = getUserByPhone(commandInfo.phone);
		user.setPassword(MD5.getMD5(commandInfo.newPassword));
		dao.update(user);
		return user;
	}
	
	public User updateUser(UserCommandInfo commandInfo) {
		User user = getUserByPhone(commandInfo.phone);
		String password = MD5.getMD5(commandInfo.password);
		if(user.getPassword().equals(password)) {
			user.setExpired(new Date());
			user.setName(commandInfo.name);
			user.setSex(commandInfo.sex);
			if(commandInfo.newPassword != null) {
				user.setPassword(MD5.getMD5(commandInfo.newPassword));
			}
			dao.update(user);
			return user;
		} else {
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByPhone(String phone) {
		User u = new User();
		u.setTel(phone);
		List<User> users = (List<User>)dao.find(u);
		if(!users.isEmpty()) {
			return users.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByToken(String token) {
		User u = new User();
		u.setToken(token);
		List<User> users = (List<User>)dao.find(u);
		if(!users.isEmpty()) {
			return users.get(0);
		} else {
			return null;
		}
	}

}
