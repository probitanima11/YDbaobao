package com.ydbaobao.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.support.ImageResizeUtil;
import com.ydbaobao.dao.BrandDao;
import com.ydbaobao.dao.ProductDao;
import com.ydbaobao.dao.ProductsDao;
import com.ydbaobao.dao.StockDao;
import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Category;
import com.ydbaobao.model.Product;

@Service
public class ProductsService {
	private static final Logger logger = LoggerFactory.getLogger(ProductsService.class);

	@Resource
	private ProductsDao productsDao;
	@Resource
	private ProductDao productDao;
	@Resource
	private BrandDao brandDao;
	@Resource
	private StockDao stockDao;
	
	
	public List<Product> readListByCategoryId(int categoryId, int index, int quantity) {
		return productsDao.readListByCategoryId(categoryId, index, quantity);
	}
	
	public List<Product> readListByCategoryId(int categoryId) {
		return productsDao.readListByCategoryId(categoryId);
	}
	
	public List<Product> readByProductName(String query, int index, int quantity) {
		return productsDao.readByProductName(query, index, quantity);
	}
	
	public List<Product> readByBrandName(String query, int index, int quantity) {
		return productsDao.readByBrandName(query, index, quantity);
	}
	
	public int countByCategoryId(int categoryId) {
		return productsDao.countByCategoryId(categoryId);
	}
	
	public int count() {
		return productsDao.count();
	}
	
	public int countBySearchProductName(String terms) {
		return productsDao.countBySearchProductName(terms);
	}
	
	public int countBySearchBrandName(String terms) {
		return productsDao.countBySearchBrandName(terms);
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

	public int create(int brandId) {
		Brand brand = brandDao.readBrandByBrandId(brandId);
		Product product = new Product(brand.getBrandName(), new Category(0), brand);
		int productId = productDao.create(product);
		stockDao.create(productId);
		return productId;
	}

	public List<Product> readRange(int start, int range) {
		return productsDao.readRange(start, range);
	}

	public List<Product> readUnclassifiedProducts() {
		List<Product> productList = productsDao.readListByCategoryId(0);
		for(Product product:productList){
			Brand brand = product.getBrand();
			brand.setBrandName(brandDao.readBrandByBrandId(brand.getBrandId()).getBrandName());
		}
		return productList;
	}

	public List<Product> readListByBrandId(int brandId) {
		return productsDao.readListByBrandId(brandId);
	}

	public Map<String, Integer> unregisteredProductsCountByBrand() {
		List<Brand> brandList = brandDao.readBrands();
		Map<String, Integer> map = new TreeMap<String, Integer>();
		for(Brand brand:brandList){
			map.put(brand.getBrandName(), productsDao.unregisteredProductsCountByBrand(brand.getBrandId()));
		}
		return map;
	}

	public void updateProductImage(Product product, String imageName) {
		productDao.updateProductImage(product.getProductId(), imageName);
	}
}
