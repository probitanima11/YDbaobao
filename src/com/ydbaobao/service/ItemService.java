package com.ydbaobao.service;

import java.util.List;

import javax.annotation.Resource;

import com.ydbaobao.dao.ItemDao;
import com.ydbaobao.model.Item;

public class ItemService {

	@Resource
	private ItemDao itemDao;
	
	public List<Item> readCartList(String customerId) {
		List<Item> list = itemDao.readCartList("test");
		System.out.println(list);
		
		
		return null;
	}
}
