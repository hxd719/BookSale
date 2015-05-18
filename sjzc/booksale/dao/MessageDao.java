package cn.sjzc.booksale.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.sjzc.booksale.model.Message;
@Repository("MessageDao")
public class MessageDao extends BaseDao{
	public List<Message> getMessage(Integer userId,Integer pageSize,Integer pageNum) {
		String sql = "select m from Message m where m.userId = :uid and m.state=0 order by m.time desc";
		String sql2 = "update Message as m set m.state=1  where m.userId = :uid ";
		Query query = getSession().createQuery(sql);
		Query query2 = getSession().createQuery(sql2);
		query.setParameter("uid", userId);
		query2.setParameter("uid", userId);
		query.setFirstResult(pageNum);
		query.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<Message> list = (List<Message>)query.list();
		query2.executeUpdate();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public Boolean isNewMessage(Integer userId) {
		String sql = "select m from Message m where m.userId = :uid and m.state=0 order by m.time desc";
		Query query = getSession().createQuery(sql);
		query.setParameter("uid", userId);
		query.setFirstResult(0);
		query.setMaxResults(1);
		List<Message> list = (List<Message>)query.list();
		if(list== null || list.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

}
