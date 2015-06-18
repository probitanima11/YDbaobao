package com.ydbaobao.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydbaobao.dao.ProductDao;
import com.ydbaobao.model.Product;

@Service
public class SearchService {
	@Resource
	private ProductDao productDao;
	
	public String changeParam(String param) {
		Pattern pt = Pattern.compile("^\\s{1,}|\\s{1,}$");
		Matcher m = pt.matcher(param);
		String query = m.replaceAll("").replaceAll(" ", "|");
		return query;
	}

	public int countBySearchBrandName(String paramForQuery) {
		return productDao.countBySearchBrandName(paramForQuery);
	}

	public List<Product> readByBrandName(String paramForQuery, int page, int productsPerPage) {
		return productDao.readByBrandName(paramForQuery, (page - 1) * productsPerPage, productsPerPage);
	}

	public int countBySearchProductName(String paramForQuery) {
		return productDao.countBySearchProductName(paramForQuery);
	}
	
	public List<Product> readByProductName(String paramForQuery, int page, int productsPerPage) {
		return productDao.readByProductName(paramForQuery, (page - 1) * productsPerPage, productsPerPage);
	}
}
