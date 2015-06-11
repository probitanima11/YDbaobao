package com.ydbaobao.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydbaobao.dao.BrandDao;
import com.ydbaobao.dao.ProductDao;
import com.ydbaobao.dao.StockDao;
import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Category;
import com.ydbaobao.model.Product;
import com.ydbaobao.model.Stock;

@Service
public class ProductService {
	
	@Resource
	ProductDao productDao;
	@Resource
	StockDao stockDao;
	@Resource
	BrandDao brandDao;
	
	public int create(int brandId) {
		Brand brand = brandDao.readBrandByBrandId(brandId);
		Product product = new Product(brand.getBrandName(), new Category(0), brand);
		int productId = productDao.create(product);
		stockDao.createDefault(productId);
		return productId;
	}
	
	public Product read(int productId) {
		Product product = productDao.read(productId);
		product.setStockList(stockDao.readListByProductId(productId));
		return product;
	}
	
	public void updateProductImage(Product product, String imageName) {
		productDao.updateProductImage(product.getProductId(), imageName);
	}

	public void update(Product product) {
		productDao.update(product);
		
		//TODO 재고량 삭제에 대한 처리 필요.
		
		for(Stock stock:product.getStockList()){
			if(stock.getStockId() == 0){
				stockDao.create(product, stock);
			}else{
				stockDao.update(stock);
			}
		}
	}
}
