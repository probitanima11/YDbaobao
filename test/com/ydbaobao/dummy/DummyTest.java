package com.ydbaobao.dummy;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Category;
import com.ydbaobao.model.Product;
import com.ydbaobao.service.AdminConfigService;
import com.ydbaobao.service.BrandService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.CustomerService;
import com.ydbaobao.service.ItemService;
import com.ydbaobao.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class DummyTest {
	private static final Logger logger = LoggerFactory.getLogger(DummyTest.class);
	
	@Resource
	private AdminConfigService adminConfigService;
	@Resource
	private BrandService brandService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private CustomerService customerService;
	@Resource
	private ItemService itemService;
	@Resource
	private ProductService productService;
	
	private String getCharForNumber(int i) {
	    return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
	}
	
	@Test
	public void insertBrands() {
		for (int index=1; index <= 26; index++) {
			String brandName = getCharForNumber(index)+"_brand";
			Brand brand = new Brand(brandName, 0, 0, 0, 0, 0, 0, null);
			brandService.createBrand(brand);
		}
	}
	
	@Test
	public void insertCategorys() {
		String[] categorys = {"상의일반", "하의일반", "티셔츠", "아우터", "반바지", "멜빵바지", "원피스", "스커트", "상하정장", "양말/스타킹", "속옷/수영복", "액세서리", "신발"};
		int range = categorys.length;
		for (int index=0; index < range; index++) {
			String categoryName = categorys[index];
			categoryService.create(categoryName);
		}
	}
	
	@Test
	public void insertProducts() {
		List<Brand> brands = brandService.readBrands();
		for (Brand brand : brands) {
			List<Category> categorys = categoryService.read();
			for (Category category : categorys) {
				for (int index=1; index <= 1; index++) {
					int productId = productService.create(brand.getBrandId());
					Product product = productService.read(productId);
					product.setBrand(brand);
					product.setCategory(category);
					product.setProductName("더미상 품");
					product.setProductPrice(10000);
					product.setProductSize("L|XL");
					product.setProductDescription("더미상품입니다.");
					product.setProductImage("dummy.jpg");
					productService.update(product);
				}
			}
		}
	}
}
