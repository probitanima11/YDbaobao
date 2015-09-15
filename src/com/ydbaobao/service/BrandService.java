package com.ydbaobao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ydbaobao.dao.BrandDao;
import com.ydbaobao.dao.ProductDao;
import com.ydbaobao.domain.Brand;

@Service
@Transactional
public class BrandService {
	@Resource
	private BrandDao brandDao;
	@Resource
	private ProductDao productDao;

	public Brand readBrandByBrandId(int brandId) {
		return brandDao.readBrandByBrandId(brandId);
	}
	public List<Brand> readBrands() {
		return brandDao.readBrands();
	}
	
	public List<Brand> readBrandsByCategoryId(int categoryId) {
		List<Brand> brands = brandDao.readBrandsByCategoryId(categoryId);
		// 브랜드별 상품 개수 다시 계산.
		int count=0;
		for(Brand brand:brands){
			count = productDao.countProductByBrandIdAndCategoryId(categoryId, brand.getBrandId());
			brand.setBrandCount(count);			
		}
		
		return brands;
	}
	
	public List<Brand> findBrands(String searchValue) {
		return brandDao.findBrands(searchValue);
	}

	public int createBrand(Brand brand) {
		if(brandDao.readBrandByBrandName(brand.getBrandName()) != null) {
			// TODO 브랜드명 중복 예외처리
		}
		return brandDao.createBrand(brand);
	}

	public void updateBrand(Brand brand) {
		if(brandDao.readBrandByBrandName(brand.getBrandName()) != null) {
			// TODO 브랜드명 중복 예외처리
		}
		brandDao.updateBrand(brand);
	}

	public void deleteBrand(String brandId) {
		brandDao.deleteBrand(brandId);
	}

	public List<Brand> search(String firstLetter) {
		if(firstLetter.equals("all")) {
			return brandDao.search("");
		}
		return brandDao.search(firstLetter);
	}
}
