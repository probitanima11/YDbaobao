package com.ydbaobao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ydbaobao.dao.BrandDao;
import com.ydbaobao.model.Brand;

@Service
@Transactional
public class BrandService {

	@Resource
	private BrandDao brandDao;

	public Brand readBrandByBrandId(int brandId) {
		return brandDao.readBrandByBrandId(brandId);
	}
	public List<Brand> readBrands() {
		return brandDao.readBrands();
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
		return brandDao.search(firstLetter);
	}
}
