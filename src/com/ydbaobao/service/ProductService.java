package com.ydbaobao.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.support.ImageResizeUtil;
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
	}
	
	public String uploadImage(Product product, MultipartFile productImage) {
		String[] imageSplitName = productImage.getOriginalFilename().split("\\.");
		String extension = imageSplitName[imageSplitName.length-1];
		String imageName = product.getProductId()+"."+extension;
		try {
			File imageFile = new File(ImageResizeUtil.savingPath + imageName);
			productImage.transferTo(imageFile);
			product.setProductImage(imageName);
			ImageResizeUtil.imageResize(imageFile.getPath(), extension);
		} catch (IllegalStateException | IOException e) {
			// TODO 예외처리 추가(giyatto)
			e.printStackTrace();
		}
		return imageName;
	}
	
	public Map<String, Integer> unregisteredProductsCountByBrand() {
		List<Brand> brandList = brandDao.readBrands();
		Map<String, Integer> map = new TreeMap<String, Integer>();
		for(Brand brand:brandList){
			map.put(brand.getBrandName(), productDao.unregisteredProductsCountByBrand(brand.getBrandId()));
		}
		return map;
	}
	
	public List<Product> readListByCategoryId(int categoryId, int index, int quantity) {
		return productDao.readListByCategoryId(categoryId, index, quantity);
	}
	
	public List<Product> readProducts() {
		List<Product> productList = productDao.readProductsList();
		for(Product product:productList){
			Brand brand = product.getBrand();
			brand.setBrandName(brandDao.readBrandByBrandId(brand.getBrandId()).getBrandName());
		}
		return productList;
	}
	
	public List<Product> readByProductName(String query, int index, int quantity) {
		return productDao.readByProductName(query, index, quantity);
	}

	public List<Product> readListByCategoryId(int categoryId) {
		return productDao.readListByCategoryId(categoryId);
	}
	
	public List<Product> readByBrandName(String query, int index, int quantity) {
		return productDao.readByBrandName(query, index, quantity);
	}
	
	public int count() {
		return productDao.count();
	}
	
	public int countBySearchProductName(String terms) {
		return productDao.countBySearchProductName(terms);
	}
	
	public int countBySearchBrandName(String terms) {
		return productDao.countBySearchBrandName(terms);
	}

	public List<Product> readRange(int start, int range) {
		return productDao.readRange(start, range);
	}

	public List<Product> readUnclassifiedProducts() {
		List<Product> productList = productDao.readListByCategoryId(0);
		for(Product product:productList){
			Brand brand = product.getBrand();
			brand.setBrandName(brandDao.readBrandByBrandId(brand.getBrandId()).getBrandName());
		}
		return productList;
	}

	public List<Product> readListByBrandId(int brandId, int index, int quantity) {
		return productDao.readListByBrandId(brandId, index, quantity);
	}
}
