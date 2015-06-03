package com.ydbaobao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydbaobao.dao.BrandDao;
import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Category;

@Service
public class BrandService {

	@Resource
	private BrandDao brandDao;

	public List<Brand> readBrands() {
		return brandDao.readBrands();
	}

	public void createBrand(String brandName) {
		if(brandDao.readBrandByBrandName(brandName) != null) {
			// TODO 예외처리
		}
		brandDao.createBrand(brandName);
	}

	public List<Category> read() {
		return brandDao.read();
	}
}
