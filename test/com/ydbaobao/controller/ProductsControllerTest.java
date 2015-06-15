package com.ydbaobao.controller;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ydbaobao.dao.ProductsDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class ProductsControllerTest {

	@Resource
	private ProductsDao productsDao;
	
	@Test
	public void deleteAll() {
		int result = productsDao.deleteAll();
		assertEquals(1, result);
	}

}
