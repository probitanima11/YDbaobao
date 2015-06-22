package com.ydbaobao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ydbaobao.dao.CategoryDao;
import com.ydbaobao.dao.ProductDao;
import com.ydbaobao.model.Category;

@Service
@Transactional
public class CategoryService {
	@Resource
	private CategoryDao categoryDao;
	@Resource
	private ProductDao productDao;

	public List<Category> read() {
		return categoryDao.read();
	}
	
	public List<Category> readWithoutUnclassifiedCategory() {
		return categoryDao.readWithoutUnclassifiedCategory();
	}

	public boolean update(long categoryId, String categoryName) {
		if(categoryDao.update(categoryId, categoryName) == 1) {
			return true;
		}
		return false;
	}

	public boolean delete(long categoryId) {
		productDao.changeUnclassifiedCategory(categoryId);
		if(categoryDao.delete(categoryId) == 1) {
			return true;
		}
		return false;
	}

	public boolean create(String categoryName) {
		if(categoryDao.create(categoryName) == 1) {
			return true;
		}
		return false;
	}
	
	public Category readByCategoryId(int categoryId) {
		return categoryDao.readByCategoryId(categoryId);
	}
}
