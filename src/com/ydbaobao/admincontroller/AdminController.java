package com.ydbaobao.admincontroller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ydbaobao.dao.GradeDao;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static String password = "1111";

	@Resource
	private GradeDao gradeDao;
	
	/**
	 * 관리자 페이지 접근을 위한 GET 요청을 응답.
	 * session 체크를 하여 sessionAdmin이 Null일 경우 관리자 번호 체크 페이지로 포워딩.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String home(HttpSession session) {
		if (session.getAttribute("sessionAdmin") == null) {
			return "admin/adminCheck";
		}
		return "admin/admin";
	}

	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String check() {
		return "admin/adminCheck";
	}
	

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String checkForm(@RequestParam String adminPassword, HttpSession session) {
		if (adminPassword.equals(password)) {
			session.setAttribute("sessionAdmin", adminPassword);
			return "admin/admin";
		}
		return "admin/adminCheck";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if (null == session.getAttribute("sessionAdmin")) {
			return "admin/adminCheck";
		}
		session.removeAttribute("sessionAdmin");
		return "redirect:/";
	}
	
	@RequestMapping(value = "/manage/grade", method = RequestMethod.GET)
	public String manageGrade(Model model) {
		model.addAttribute("grades", gradeDao.readGrades());
		return "admin/gradeManager";
	}

	/**
	 * 첫화면 이미지 설정 관리 페이지 
	 * 이미지 경로와 저장된 이미지 갯수를 돌려준다.
	 */
	@RequestMapping(value = "/indexImages")
	public String indexImage(Model model) {
		List<String> imgPath = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			String filePath = "index_0" + i + ".jpg";
			File f = new File("/home/baobao/index/"+filePath);
			if (f.isFile()) {
				imgPath.add("/img/index/"+filePath);
			} else {
				imgPath.add("");
			}
		}
		model.addAttribute("imgPath", imgPath);
		return "/admin/indexManager";
	}
	
	/**
	 * 첫화면 이미지 설정 관리 페이지 
	 * 이미지 추가.
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = "/indexImages", method = RequestMethod.POST)
	public String indexImageCreate(Model model, @RequestParam MultipartFile profileImage, @RequestParam int imgIndex) throws IllegalStateException, IOException {
		profileImage.transferTo(new File("/home/baobao/index/index_0" + imgIndex + ".jpg"));
		
		List<String> imgPath = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			String filePath = "index_0" + i + ".jpg";
			File f = new File("/home/baobao/index/"+filePath);
			if (f.isFile()) {
				imgPath.add("/img/index/"+filePath);
			} else {
				imgPath.add("");
			}
		}
		model.addAttribute("imgPath", imgPath);
		return "/admin/indexManager";
	}
	
	/**
	 * 첫화면 이미지 설정 관리 페이지 
	 * 이미지 삭제.
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = "/indexImages/{imgIndex}", method = RequestMethod.POST)
	public String indexImageDelete(Model model, @PathVariable int imgIndex) throws IllegalStateException, IOException {
		new File("/home/baobao/index/index_0" + imgIndex + ".jpg").delete();
		
		List<String> imgPath = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			String filePath = "index_0" + i + ".jpg";
			File f = new File("/home/baobao/index/"+filePath);
			if (f.isFile()) {
				imgPath.add("/img/index/"+filePath);
			} else {
				imgPath.add("");
			}
		}
		model.addAttribute("imgPath", imgPath);
		return "/admin/indexManager";
	}
}
