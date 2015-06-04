package com.ydbaobao.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
	public List<Product> readListByCategoryId(int categoryId) {
		return productsDao.readListByCategoryId(categoryId);
	}

	public void uplodeImage(Product product, String savingPath, MultipartFile productImage) {

		String fileName = productImage.getOriginalFilename();
		try {
			productImage.transferTo(new File(savingPath + fileName));
			product.setProductImage(fileName);
		} catch (IllegalStateException | IOException e) {
			// TODO 예외처리 추가 예정. (giyatto)
			e.printStackTrace();
		}
	}

	public void create(int brandId, String productImage) {
		Brand brand = brandDao.readBrandByBrandId(brandId);
		Product product = new Product(brand.getBrandName(), new Category(0), brand, productImage);
		productsDao.create(product);
	}

	public List<Product> readAsQuantity(int count) {
		return productsDao.readAsQuantity(count);
	}
}
