package com.ydbaobao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ydbaobao.dao.AdminIndexImageDao;
import com.ydbaobao.model.IndexImage;

@Service
@Transactional
public class AdminIndexImageService {
	@Resource
	private AdminIndexImageDao adminIndexImageDao;
	
	public List<IndexImage> readIndexImages() {
		return adminIndexImageDao.readIndexImages();
	}
	
	public void create(String fileName) {
		adminIndexImageDao.create(fileName);
	}
	
	public void delete(int indexImageId) {
		adminIndexImageDao.delete(indexImageId);
	}
}