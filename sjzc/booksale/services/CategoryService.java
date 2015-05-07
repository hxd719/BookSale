package cn.sjzc.booksale.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sjzc.booksale.dao.CategoryDao;
import cn.sjzc.booksale.model.Category;
@Service("CategoryService")
public class CategoryService {
	
	@Resource
	private CategoryDao dao;



	public List<Category> getCategorise() {
		return dao.findAll(Category.class);
	}


}
