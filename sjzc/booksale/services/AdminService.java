package cn.sjzc.booksale.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sjzc.booksale.dao.AdminDao;
import cn.sjzc.booksale.model.Admin;

@Service("AdminService")
public class AdminService {
	@Resource
	private AdminDao dao;
	
	public Admin getAdmin(Admin a) {
		return dao.getAdminByName(a.getName());
	}
	
	public Admin getAdmin(Integer id) {
		return dao.findById(Admin.class,id);
	}
	
	public void update(Admin a) {
		 dao.update(a);
	}

}
