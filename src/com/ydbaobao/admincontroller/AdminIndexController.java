package com.ydbaobao.admincontroller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.support.ImageResizeUtil;

@Controller
@RequestMapping("/admin/indexImages")
public class AdminIndexController {

	/**
	 * 첫화면 이미지 설정 관리 페이지 
	 * 이미지 경로와 저장된 이미지 갯수를 돌려준다.
	 */
	@RequestMapping()
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
	@RequestMapping(method = RequestMethod.POST)
	public String indexImageCreate(Model model, @RequestParam MultipartFile profileImage, @RequestParam int imgIndex) throws IllegalStateException, IOException {
		String imgFilePath = "/home/baobao/index/index_0" + imgIndex + ".jpg";
		profileImage.transferTo(new File(imgFilePath));
		ImageResizeUtil.imageResize(imgFilePath, "jpg", 800);
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
	@RequestMapping(value = "/{imgIndex}", method = RequestMethod.POST)
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
