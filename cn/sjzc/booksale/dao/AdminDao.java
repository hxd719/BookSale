package cn.sjzc.booksale.dao;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.sjzc.booksale.model.Admin;

@Repository("admindao")
public class AdminDao extends BaseDao {
	
	
	

	public void delete(int id) {
		Admin user = (Admin)getSession().get(Admin.class, id);
		getSession().delete(user);
	}

	public Admin getAdminByName(String name) {
		Admin user = null;
		String sql = "select a from Admin a where name = :na";
		Query query = getSession().createQuery(sql);
		query.setParameter("na", name);
		user = (Admin)query.uniqueResult();
		return user;
	}

	public Admin query(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Admin admin) {
		getSession().update(admin);

	}

	public void save(Admin admin) {
		// TODO Auto-generated method stub
		
	}

}
