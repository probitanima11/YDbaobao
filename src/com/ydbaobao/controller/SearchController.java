package com.ydbaobao.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.support.CommonUtil;
import com.ydbaobao.model.Product;
import com.ydbaobao.model.SessionCustomer;
import com.ydbaobao.service.AdminConfigService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ProductService;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Resource
	private CategoryService categoryService;
	@Resource
	private ProductService productService;
	@Resource
	private AdminConfigService adminConfigService;

	private String preprocessingTerms(String terms) {
		Pattern pt = Pattern.compile("^\\s{1,}|\\s{1,}$");
		Matcher m = pt.matcher(terms);
		String query = m.replaceAll("").replaceAll(" ", "|");
		return query;
	}

	@RequestMapping(value = "/brands", method = RequestMethod.GET)
	public String searchBrand(HttpSession session, Model model, @RequestParam String terms, @RequestParam int page) {
		String termsForQuery = this.preprocessingTerms(terms);

		if (termsForQuery.length() < 1) {
			model.addAttribute("count", 0);
			return "search";
		}
		int count = productService.countBySearchBrandName(termsForQuery);
		List<Product> products = productService.readByBrandName(termsForQuery, page, adminConfigService.read()
				.getAdminDisplayProducts(), (SessionCustomer) session.getAttribute("sessionCustomer"));

		int totalPage = CommonUtil.countTotalPage(count, CommonUtil.productsPerPage);

		model.addAttribute("prev", CommonUtil.prevBlock(page));
		model.addAttribute("next", CommonUtil.nextBlock(page, totalPage));
		model.addAttribute("selectedIndex", page);
		model.addAttribute("range", IntStream.range(CommonUtil.startPage(page), CommonUtil.endPage(page, totalPage))
				.toArray());
		model.addAttribute("url", "/search/brands?terms=" + terms + "&page=");
		model.addAttribute("categories", categoryService.read());
		model.addAttribute("products", products);
		model.addAttribute("count", count);
		model.addAttribute("terms", terms);
		model.addAttribute("query", termsForQuery);
		model.addAttribute("select", "브랜드명");
		return "search";
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String search(HttpSession session, Model model, @RequestParam String terms, @RequestParam int page) {
		String termsForQuery = this.preprocessingTerms(terms);

		if (termsForQuery.length() < 1) {
			model.addAttribute("count", 0);
			return "search";
		}
		int count = productService.countBySearchProductName(termsForQuery);
		List<Product> products = productService.readByProductName(termsForQuery, page, adminConfigService.read()
				.getAdminDisplayProducts(), (SessionCustomer) session.getAttribute("sessionCustomer"));

		int totalPage = CommonUtil.countTotalPage(count, CommonUtil.productsPerPage);

		model.addAttribute("prev", CommonUtil.prevBlock(page));
		model.addAttribute("next", CommonUtil.nextBlock(page, totalPage));
		model.addAttribute("selectedIndex", page);
		model.addAttribute("range", IntStream.range(CommonUtil.startPage(page), CommonUtil.endPage(page, totalPage))
				.toArray());
		model.addAttribute("url", "/search/products?terms=" + terms + "&page=");
		model.addAttribute("categories", categoryService.readWithoutUnclassifiedCategory());
		model.addAttribute("products", products);
		model.addAttribute("count", count);
		model.addAttribute("terms", terms);
		model.addAttribute("query", termsForQuery);
		model.addAttribute("select", "상품명");
		return "search";
	}
}
