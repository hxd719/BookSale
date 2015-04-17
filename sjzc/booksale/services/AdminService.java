package cn.sjzc.booksale.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sjzc.booksale.dao.AdminDao;
import cn.sjzc.booksale.model.Admin;

@Service("adminservice")
public class AdminService {
	@Resource
	private AdminDao dao;
	
	public Admin getAdmin(Admin a) {
		return dao.getAdminByName(a.getName());
	}

}
