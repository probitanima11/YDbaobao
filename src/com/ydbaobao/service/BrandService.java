package com.ydbaobao.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydbaobao.dao.BrandDao;

@Service
public class BrandService {

	@Resource
	private BrandDao brandDao;
}
