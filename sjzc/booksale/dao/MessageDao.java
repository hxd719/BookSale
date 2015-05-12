package cn.sjzc.booksale.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.sjzc.booksale.model.Message;
@Repository("MessageDao")
public class MessageDao extends BaseDao{
	public List<Message> getMessage(Integer userId) {
		String sql = "select m from Message m where m.userId = :uid and m.state=0";
		Query query = getSession().createQuery(sql);
		query.setParameter("uid", userId);
		@SuppressWarnings("unchecked")
		List<Message> list = (List<Message>)query.list();
		return list;
	}

}
