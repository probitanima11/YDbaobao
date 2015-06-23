package com.ydbaobao.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ydbaobao.model.Category;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class CategoryDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(CategoryDaoTest.class);
	
	@Resource
	private CategoryDao categoryDao;
	
	@Test
	public void read() {
		List<Category> list = categoryDao.read();
		assertNotNull(list);
		for (Category category : list) {
			logger.debug(category.toString());
		}
	}
	
	@Test
	public void update() {
		int result = categoryDao.update(1, "수정됨");
		assertEquals(1, result);
	}
	
	@Test
	public void delete() {
		int result = categoryDao.delete(20);
		assertEquals(1, result);
	}
	
	@Test
	public void create() {
		int result = categoryDao.create("새로운 카테고리");
		assertEquals(1, result);
	}
}
