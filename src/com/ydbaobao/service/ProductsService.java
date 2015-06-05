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

import com.support.RandomFactory;
import com.ydbaobao.dao.BrandDao;
import com.ydbaobao.dao.ProductsDao;
import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Category;
import com.ydbaobao.model.Product;

@Service
public class ProductsService {
	private static final Logger logger = LoggerFactory.getLogger(ProductsService.class);

	@Resource
	private ProductsDao productsDao;
	@Resource
	private BrandDao brandDao;
	
	public List<Product> readListByCategoryId(int categoryId, int index, int quantity) {
		return productsDao.readListByCategoryId(categoryId, index, quantity);
	}
	
	public List<Product> readListByCategoryId(int categoryId) {
		return productsDao.readListByCategoryId(categoryId);
	}
	
	public int countByCategoryId(int categoryId) {
		return productsDao.countByCategoryId(categoryId);
	}
	
	public int count() {
		return productsDao.count();
	}

	public String uplodeImage(Product product, String savingPath, MultipartFile productImage) {
		String[] imageSplitName = productImage.getOriginalFilename().split("\\.");
		String extension = imageSplitName[imageSplitName.length-1];
		String imageName = createImageName(extension);
		try {
			productImage.transferTo(new File(savingPath + imageName));
			product.setProductImage(imageName);
		} catch (IllegalStateException | IOException e) {
			// TODO 예외처리 추가 예정. (giyatto)
			e.printStackTrace();
		}
		return imageName;
	}
	
	private String createImageName(String extension) {
		String imageName = RandomFactory.getRandomId(5)+"."+extension;
		if (productsDao.isExistImageName(imageName)) {
			return createImageName(extension);
		}
		return imageName;
	}
	

	public void create(int brandId, String productImage) {
		Brand brand = brandDao.readBrandByBrandId(brandId);
		Product product = new Product(brand.getBrandName(), new Category(0), brand, productImage);
		productsDao.create(product);
	}

	public List<Product> readRange(int start, int range) {
		return productsDao.readRange(start, range);
	}

	public List<Product> readUnclassifiedProducts() {
		return productsDao.readListByCategoryId(0);
	}

	public List<Product> readListByBrandId(int brandId) {
		return productsDao.readListByBrandId(brandId);
	}

	public Map<String, Integer> unregisteredProductsCountByBrand() {
		List<Brand> brandList = brandDao.readBrands();
		Map<String, Integer> map = new TreeMap<String, Integer>();
		for(Brand brand:brandList){
			map.put(brand.getBrandName(), productsDao.unregisteredProductsCountByBrand(brand.getBrandName()));
		}
		return map;
	}
}
