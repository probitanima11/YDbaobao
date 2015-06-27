package com.ydbaobao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.media.jfxmedia.logging.Logger;
import com.ydbaobao.dao.ItemDao;
import com.ydbaobao.dao.ProductDao;
import com.ydbaobao.model.Item;
import com.ydbaobao.model.Payment;
import com.ydbaobao.model.Product;

@Service
@Transactional
public class ItemService {
	//private static final Logger logger = LoggerFactory.getLogger(ItemService.class);
	@Resource
	private ItemDao itemDao;
	@Resource
	private ProductDao productDao;
	@Resource
	private PaymentService paymentService;
	@Resource
	private ProductService productService;
	
	/**
	 * 아이템 생성(장바구니에 등록/주문)
	 * @param customerId
	 * @param size
	 * @param quantity
	 * @param productId
	 * @param itemStatus
	 */
	public void createItems(String customerId, String size, String quantity, int productId, String itemStatus) {
		String[] sizeArray = size.split("-");
		String[] quantityArray = quantity.split("-");	
		
		// 사이즈가 구분이 없을 경우
		if (sizeArray.length == 0) {
			if(itemDao.readItemByProductIdAndSize(productId, size, customerId, itemStatus) != null) {
				itemDao.updateItemQuantity(itemDao.readItemByProductIdAndSize(productId, "-", customerId, itemStatus).getItemId(), Integer.parseInt(quantityArray[0]));
			}
			else{
				itemDao.createItem(customerId, productId, "-", Integer.parseInt(quantityArray[0]), itemStatus);
			}
		}
		
		// 사이즈 구분이 있는 경우
		for(int i=0; i< quantityArray.length; i++){
			// 해당 사이즈가 0개일 경우 넘어가
			if(quantityArray[i].equals("0")){
				continue;
			}
			if(itemDao.readItemByProductIdAndSize(productId, sizeArray[i], customerId, itemStatus) != null){
				itemDao.updateItemQuantity(itemDao.readItemByProductIdAndSize(productId, sizeArray[i], customerId, itemStatus).getItemId(), Integer.parseInt(quantityArray[i]));
			}
			else{
				itemDao.createItem(customerId, productId, sizeArray[i], Integer.parseInt(quantityArray[i]), itemStatus);
			}
		}
		
		if (itemStatus.equals("S")) {
			Item item = itemDao.readItemByProductIdAndSize(productId, size, customerId, "I");
			if (item != null) {
				itemDao.deleteItem(item.getItemId());	
			}
			item = itemDao.readItemByProductIdAndSize(productId, size, customerId, "S");
			int price = productService.readByDiscount(item.getProduct(), item.getCustomer()).getProductPrice();
			itemDao.updateItemPrice(item.getItemId(), item.getQuantity()*price);
		}
	}
	
	/**
	 * 아이템 생성(바로 주문 시)
	 * @param customerId
	 * @param size
	 * @param quantity
	 * @param productId
	 * @return
	 */
	public int[] createItemsDirectly(String customerId, String size, String quantity, int productId) {
		String[] sizeArray = size.split("-");
		String[] quantityArray = quantity.split("-");
		int[] itemList = new int[quantityArray.length];
		for(int i=0; i< quantityArray.length; i++){
			if(quantityArray[i].equals("0")){
				continue;
			}
			if(sizeArray.length == 0) {
				sizeArray[i] = "-";
			}
			itemList[i] = itemDao.createItemDirectly(customerId, productId, sizeArray[i], Integer.parseInt(quantityArray[i]));
		}
		return itemList;
	}
	
	/**
	 * 아이템 주문 및 아이템 가격 결정
	 * @param customerId
	 * @param itemList
	 */
	public void requestItems(String customerId, int[] itemList) {
		for (int itemId : itemList) {
			Item item = itemDao.readItem(itemId);
			itemDao.deleteItem(itemId);
			this.createItems(customerId, item.getSize(), Integer.toString(item.getQuantity()), item.getProduct().getProductId(), "S");
		}
	}
	
	public List<Item> readCartItems(String customerId, String itemStatus) {
		List<Item> items = itemDao.readCartItems(customerId, itemStatus);
		for (Item item : items) {
			Product product = item.getProduct();
			int discountRate = product.getBrand().getDiscountRate(item.getCustomer().getCustomerGrade());
			product.discount(discountRate);
		}
		return items;
	}
	
	public List<Item> readOrderedItems() {
		return itemDao.readOrderedItems();
	}
	
	public Item readItemByItemId(int itemId) {
		Item item = itemDao.readItem(itemId);
		Product product = item.getProduct(); 
		product.setProductPrice(productService.readByDiscount(product, item.getCustomer()).getProductPrice());
		return item;
	}
	
	public void deleteCartList(String customerId, int itemId) {
		if(!itemDao.readItem(itemId).getCustomer().getCustomerId().equals(customerId)){
			//TODO 아이템 고객아이디와 삭제하려는 고객아이디가 다를경우 예외처리.
		}
		itemDao.deleteItem(itemId);
	}

	public void updateItemQuantity(int itemId, int quantity) {
		itemDao.updateItemQuantity(itemId, quantity);
	}

	public boolean acceptOrder(int itemId, int quantity) {
		Item item = itemDao.readItemByStatus(itemId, "S");
		if (item == null)
			return false;
		if (item.getQuantity() < quantity)
			quantity = item.getQuantity();
		int price = productService.readByDiscount(item.getProduct(), item.getCustomer()).getProductPrice();
		paymentService.createPayment(new Payment(item.getCustomer(), "P", price * quantity));
		itemDao.updateItemQuantity(itemId, -quantity);
		itemDao.updateItemPrice(item.getItemId(), item.getQuantity()*price);
		return true;
	}

	public boolean rejectOrder(int itemId) {
		Item item = itemDao.readItemByStatus(itemId, "S");
		if(item == null)
			return false;
		itemDao.updateItemStatus(itemId, "R");
		return true;
	}
}
