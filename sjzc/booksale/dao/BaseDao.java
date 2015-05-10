package cn.sjzc.booksale.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import cn.sjzc.booksale.utill.PagerVO;
import cn.sjzc.booksale.utill.SystemContext;
public class BaseDao {
	@Resource
	private SessionFactory sessionFactory;
	
	
	public void save(Object entity) {
		getSession().save(entity);
		getSession().flush();
	}

	public void update(Object entity) {
		getSession().update(entity);
		getSession().flush();
	}


	public void del(Object entity) {
		getSession().delete(entity);
	}

	public List<?> find(Object entity) {
		return getSession().createCriteria(entity.getClass()).add(
				Example.create(entity)).list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<T> entityClass) {
		return getSession().createCriteria(entityClass).list();
	}

	@SuppressWarnings("unchecked")
	public <T> T findById(Class<T> entityClass, int id) {
		return (T) getSession().get(entityClass, id);
	}

	public PagerVO findPaginated(String query) {
		return findPaginated(query, null);
	}

	public PagerVO findPaginated(String query, Object param) {
		return findPaginated(query, new Object[]{param});
	}

	public PagerVO findPaginated(String query, Object[] params) {
		return findPaginated(query, params, SystemContext.getOffset(), SystemContext.getPagesize());
	}

	public PagerVO findPaginated(String query, int offset, int pagesize) {
		return findPaginated(query, null,offset,pagesize);
	}

	public PagerVO findPaginated(String query, Object param, int offset,
			int pagesize) {
		return findPaginated(query, new Object[]{param}, offset, pagesize);
	}

	public PagerVO findPaginated(String queryHql, Object[] params, int offset,
			int pagesize) {
		String countHql = getCountHql(queryHql);
		Query query = getSession().createQuery(countHql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		int total = ((Long) query.uniqueResult()).intValue();
		query = getSession().createQuery(queryHql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.setFirstResult(offset);
		query.setMaxResults(pagesize);
		List<?> datas = query.list();

		PagerVO pv = new PagerVO();
		pv.setDatas(datas);
		pv.setTotal(total);

		return pv;
	}

	protected String getCountHql(String queryHql) {
		int index = queryHql.toLowerCase().indexOf("from");
		if (index == -1) {
			throw new RuntimeException("非法的" + queryHql + "语句");
		}
		return "select count(*) " + queryHql.substring(index);
	}

	protected Session getSession() {
		
		if(sessionFactory.getCurrentSession() == null) {
			return sessionFactory.openSession();
		}
		return sessionFactory.getCurrentSession();
	}

}
