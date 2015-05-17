package cn.sjzc.booksale.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sjzc.booksale.dao.CategoryDao;
import cn.sjzc.booksale.model.Category;
import cn.sjzc.booksale.utill.PagerVO;
@Service("CategoryService")
public class CategoryService {
	
	@Resource
	private CategoryDao dao;



	public List<Category> getCategorise() {
		return dao.findAll(Category.class);
	}

	
	public void add(Category c) {
		dao.save(c);
	}

	public Category getCategoryById(Integer id) {
		 return dao.findById(Category.class, id);
	}
	
	
	public void delete(Integer id) {
		Category c = dao.findById(Category.class, id);
		dao.del(c);
	}
	
	
	public void update(Category c) {
		dao.update(c);
	}
	
	public PagerVO getCategoryLists(Integer pageNum,String searchKey) {
		if(searchKey == null) {
			searchKey = "";
		}
		String sql = "select b from Category b where b.name like ? order by b.id desc";
		PagerVO data = dao.findPaginated(sql,"%"+searchKey+"%", (pageNum-1)*10, 10);
		return data;
	}
}
