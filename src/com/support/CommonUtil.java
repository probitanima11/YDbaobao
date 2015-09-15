package com.support;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.ydbaobao.dao.AdminConfigDao;

@Component
public class CommonUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(CommonUtil.class);

	public static int PRODUCT_PER_PAGE = 16;

	@Resource
	private AdminConfigDao adminConfigDao;

	@PostConstruct
	public void initProductsPerPage() {
		PRODUCT_PER_PAGE = adminConfigDao.read().getAdminDisplayProducts();
	}

	/**
	 * <pre>
	 * matched
	 *
	 * <pre>
	 * @param regex
	 * @param inputTxt
	 * @return
	 */
	public static boolean matched(String regex, String inputTxt) {
		return Pattern.matches(regex, inputTxt);
	}

	/**
	 * 전체 상품 갯수를 페이지 당 보여줄 상품 갯수로 나누어서 전체 페이지를 구한다
	 * 
	 * @param 전체
	 *            상품 갯수, 페이지 당 보여줄 상품 갯수
	 * @return 총 페이지 수
	 */
	public static int countTotalPage(int count, int productPerPage) {
		if (count % productPerPage == 0) {
			return count / productPerPage;
		}
		return count / productPerPage + 1;
	}

}
