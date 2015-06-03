package com.ydbaobao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydbaobao.dao.CategoryDao;
import com.ydbaobao.model.Category;

@Service
public class CategoryService {
	@Resource
	private CategoryDao categoryDao;

	public List<Category> read() {
		return categoryDao.read();
	}

	public Category create(Category category) {
		int categoryKey = categoryDao.create(category);
		return categoryDao.readByCategoryId(categoryKey);
	}
	
	public Category readByCategoryId(int categoryId) {
		return categoryDao.readByCategoryId(categoryId);
	}
}
