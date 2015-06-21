package com.ydbaobao.controller;

import java.util.List;
import java.util.stream.IntStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.support.CommonUtil;
import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Category;
import com.ydbaobao.model.Product;
import com.ydbaobao.model.SessionCustomer;
import com.ydbaobao.service.AdminConfigService;
import com.ydbaobao.service.BrandService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ProductService;

@Controller
public class ProductController {
	@Resource
	private ProductService productService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private BrandService brandService;
	@Resource
	private AdminConfigService adminConfigService;

	@RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
	public String read(@PathVariable int productId, Model model, HttpSession session) {
		model.addAttribute("customer", (SessionCustomer) session.getAttribute("sessionCustomer"));
		model.addAttribute("product", productService.read(productId));
		return "product";
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String loadAll(Model model, HttpSession session, @RequestParam("page") int page) {
		int totalPage = CommonUtil.countTotalPage(productService.count(), CommonUtil.productsPerPage);

		model.addAttribute("prev", CommonUtil.prevBlock(page));
		model.addAttribute("next", CommonUtil.nextBlock(page, totalPage));
		model.addAttribute("selectedIndex", page);
		model.addAttribute("range", IntStream.range(CommonUtil.startPage(page), CommonUtil.endPage(page, totalPage))
				.toArray());
		model.addAttribute("url", "/products?page=");
		model.addAttribute("products", productService.readRange(page, adminConfigService.read()
				.getAdminDisplayProducts(), (SessionCustomer) session.getAttribute("sessionCustomer")));
		model.addAttribute("categories", categoryService.readWithoutUnclassifiedCategory());
		model.addAttribute("brands", brandService.readBrands());
		model.addAttribute("firstLetterList", new Brand().getFirstLetters());
		return "products";
	}

	/**
	 * 카테고리 메뉴 별 상품 로드
	 * 
	 * @param categoryId
	 *            , page
	 */
	@RequestMapping(value = "/categories/{categoryId}/products", method = RequestMethod.GET)
	public String load(Model model, HttpSession session, @RequestParam("page") int page, @PathVariable int categoryId) {
		Category category = categoryService.readByCategoryId(categoryId);
		int totalPage = CommonUtil.countTotalPage(category.getCategoryCount(), CommonUtil.productsPerPage);

		model.addAttribute("prev", CommonUtil.prevBlock(page));
		model.addAttribute("next", CommonUtil.nextBlock(page, totalPage));
		model.addAttribute("selectedIndex", page);
		model.addAttribute("range", IntStream.range(CommonUtil.startPage(page), CommonUtil.endPage(page, totalPage))
				.toArray());
		model.addAttribute("url", "/categories/" + categoryId + "/products?page=");
		model.addAttribute("products", productService.readListByCategoryId(categoryId, page, adminConfigService.read()
				.getAdminDisplayProducts(), (SessionCustomer) session.getAttribute("sessionCustomer")));
		model.addAttribute("brands", brandService.readBrandsByCategoryId(categoryId));
		model.addAttribute("category", category);
		model.addAttribute("categories", categoryService.readWithoutUnclassifiedCategory());
		model.addAttribute("firstLetterList", new Brand().getFirstLetters());
		return "products";
	}

	/**
	 * 카테고리 별 브랜드 상품 로드
	 * 
	 * @param categoryId
	 *            , brandId, page
	 */
	@RequestMapping(value = "/categories/{categoryId}/brands/{brandId}/products", method = RequestMethod.GET)
	public String load(HttpSession session, Model model, @RequestParam("page") int page, @PathVariable int categoryId,
			@PathVariable int brandId) {
		List<Product> products = productService.readByCategoryIdAndBrandId(categoryId, brandId, page,
				CommonUtil.productsPerPage, (SessionCustomer) session.getAttribute("sessionCustomer"));
		int totalPage = (int) CommonUtil.countTotalPage(products.size(), CommonUtil.productsPerPage);

		model.addAttribute("prev", CommonUtil.prevBlock(page));
		model.addAttribute("next", CommonUtil.nextBlock(page, totalPage));
		model.addAttribute("selectedIndex", page);
		model.addAttribute("range", IntStream.range(CommonUtil.startPage(page), CommonUtil.endPage(page, totalPage))
				.toArray());
		model.addAttribute("url", "/categories/" + categoryId + "/brands/" + brandId + "/products?page=");
		model.addAttribute("products", products);
		model.addAttribute("selectedBrand", brandService.readBrandByBrandId(brandId));
		model.addAttribute("brands", brandService.readBrandsByCategoryId(categoryId));
		model.addAttribute("category", categoryService.readByCategoryId(categoryId));
		model.addAttribute("categories", categoryService.readWithoutUnclassifiedCategory());
		model.addAttribute("firstLetterList", new Brand().getFirstLetters());
		return "products";
	}
}
