package com.ydbaobao.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydbaobao.dao.BrandDao;
import com.ydbaobao.dao.CategoryDao;
import com.ydbaobao.dao.ProductDao;
import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Category;
import com.ydbaobao.model.Product;

@Service
public class ProductService {
	@Resource
	ProductDao productDao;
	@Resource
	BrandDao brandDao;
	@Resource
	CategoryDao categoryDao;

	public int create(int brandId) {
		Brand brand = brandDao.readBrandByBrandId(brandId);
		Product product = new Product(brand.getBrandName(), new Category(0), brand, brand.getBrandSize());
		int productId = productDao.create(product);
		categoryDao.increaseCount(0);
		brandDao.increaseCount(brandId);
		return productId;
	}

	public Product read(int productId) {
		return productDao.read(productId);
	}

	public void updateProductImage(Product product, String imageName) {
		productDao.updateProductImage(product.getProductId(), imageName);
	}
	public void update(Product product) {
		Product oldStatus = productDao.read(product.getProductId());
		long oldCategoryId = oldStatus.getCategory().getCategoryId();
		long newCategoryId = product.getCategory().getCategoryId();
		if (oldCategoryId != newCategoryId) {
			categoryDao.increaseCount(newCategoryId);
			categoryDao.decreaseCount(oldCategoryId);
		}
		long oldBrandId = oldStatus.getBrand().getBrandId();
		long newBrandId = product.getBrand().getBrandId();
		if (oldBrandId != newBrandId) {
			brandDao.increaseCount(newBrandId);
			brandDao.decreaseCount(oldBrandId);
		}
		productDao.update(product);
		//재고관리 삭제...
		//updateStocks(product);
	}
}
