package com.ydbaobao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ydbaobao.dao.ItemDao;
import com.ydbaobao.dao.OrderDao;
import com.ydbaobao.dao.ProductDao;
import com.ydbaobao.model.Item;
import com.ydbaobao.model.Product;

@Service
@Transactional
public class ItemService {
	@Resource
	private ItemDao itemDao;
	@Resource
	private OrderDao orderDao;
	@Resource
	private ProductDao productDao;
	
	public void createItems(String customerId, String size, String quantity, int productId) {
		String[] sizeArray = size.split("-");
		String[] quantityArray = quantity.split("-");
		for(int i=0; i< quantityArray.length; i++){
			if(quantityArray[i].equals("0")){
				continue;
			}
			if(sizeArray.length == 0) {
				if(itemDao.isItemByProductIdAndSize(productId, "-")){
					itemDao.updateItem(itemDao.readItemByProductIdAndSize(productId, sizeArray[i]).getItemId(), Integer.parseInt(quantityArray[i]));
				}
				else{
					itemDao.createItem(customerId, productId, "-", Integer.parseInt(quantityArray[i]));
				}
				return;
			}
			if(itemDao.isItemByProductIdAndSize(productId, sizeArray[i])){
				itemDao.updateItem(itemDao.readItemByProductIdAndSize(productId, sizeArray[i]).getItemId(), Integer.parseInt(quantityArray[i]));
			}
			else{
				itemDao.createItem(customerId, productId, sizeArray[i], Integer.parseInt(quantityArray[i]));
			}
		}
	}

	public List<Item> readOrderedItems(String customerId) {
		return itemDao.readOrderedItems(customerId);
	}

	public void deleteCartList(String customerId, int itemId) {
		if(!itemDao.readItemByItemId(itemId).getCustomer().getCustomerId().equals(customerId)){
			//TODO 아이템 고객아이디와 삭제하려는 고객아이디가 다를경우 예외처리.
		}
		itemDao.deleteCartList(itemId);
	}

	public void orderDirect(String customerId, int productId, String size, int quantity) {
//		itemDao.orderDirect(customerId, productId, orderDao.createOrder(customerId), size, quantity);
	}

	public List<Item> readCartItems(String customerId) {
		List<Item> items = itemDao.readCartItems(customerId);
		for (Item item : items) {
			Product product = item.getProduct();
			int discountRate = product.getBrand().getDiscountRate(item.getCustomer().getCustomerGrade());
			product.discount(discountRate);
		}
		return items;
	}

	public void updateQuantity(int itemId, int quantity) {
		itemDao.updateItem(itemId, quantity);
	}
}
