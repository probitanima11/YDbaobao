package com.ydbaobao.dao;

import static org.junit.Assert.*;

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

}
