package com.ydbaobao.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ydbaobao.dao.BrandDao;
import com.ydbaobao.dao.ProductsDao;
import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Product;

@Service
public class ProductsService {
	@Resource
	private ProductsDao productsDao;
	
	@Resource
	private BrandDao brandDao;

	public List<Product> readListByCategory(int categoryId) {
		return productsDao.readListByCategory(categoryId);
	}

	public void uplodeImage(Product product, String savingPath, MultipartFile productImage) {

		String fileName = productImage.getOriginalFilename();
		try {
			productImage.transferTo(new File(savingPath + fileName));
			product.setProductImage(fileName);
		} catch (IllegalStateException | IOException e) {
			// TODO 예외처리 해줘야 함.
			e.printStackTrace();
		}
	}

	public void create(int brandId, String productImage) {
		Brand brand = brandDao.readBrandByBrandId(brandId);
		Product product = new Product(brand.getBrandName(), brand, productImage);
		productsDao.create(brand, product);
	}
}
