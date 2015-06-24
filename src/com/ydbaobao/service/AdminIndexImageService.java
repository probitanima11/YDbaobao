package com.ydbaobao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ydbaobao.dao.AdminIndexImageDao;

@Service
@Transactional
public class AdminIndexImageService {
	
	@Resource
	private AdminIndexImageDao adminIndexImageDao;
	
	public List<String> read() {
		return adminIndexImageDao.read();
	}
	
	public void create() {
		adminIndexImageDao.create();
	}
	
	public void delete() {
		adminIndexImageDao.delete();
	}
}

