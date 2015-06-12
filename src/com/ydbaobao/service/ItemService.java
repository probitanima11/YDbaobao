package com.ydbaobao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydbaobao.dao.ItemDao;
import com.ydbaobao.model.Item;

@Service
public class ItemService {

	@Resource
	private ItemDao itemDao;
	
	public List<Item> readOrderedItems(String customerId) {
		return itemDao.readOrderedItems(customerId);
	}

	public void deleteCartList(String customerId, int itemId) {
		if(!itemDao.readItemByItemId(itemId).getCustomer().getCustomerId().equals(customerId)){
			//TODO 아이템 고객아이디와 삭제하려는 고객아이디가 다를경우 예외처리.
			
		}
		itemDao.deleteCartList(itemId);
	}
}
