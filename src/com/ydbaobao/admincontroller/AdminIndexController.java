package com.ydbaobao.admincontroller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.support.ImageFactoryUtil;

@Controller
@RequestMapping("/admin/indexImages")
public class AdminIndexController {
	private static final Logger logger = LoggerFactory.getLogger(AdminIndexController.class);
	/**
	 * 첫화면 이미지 설정 관리 페이지 
	 * 이미지 경로와 저장된 이미지 갯수를 돌려준다.
	 */
	@RequestMapping()
	public String indexImage(Model model) {
		model.addAttribute("imgPath", getIndexImages());
		return "/admin/indexManager";
	}
	
	/**
	 * 첫화면 이미지 설정 관리 페이지 
	 * 이미지 추가.
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String indexImageCreate(Model model, @RequestParam MultipartFile imageFile, @RequestParam int imgIndex) throws IllegalStateException, IOException {
		if(imageFile.getSize() > 512000) {
			logger.debug("이미지 업로드 실패!, 용량이 500kb 초과되었습니다");
			model.addAttribute("errorMessage", "용량이 500kb 초과되었습니다");
			return "/admin/indexManager";
		}
		String imgFilePath = ImageFactoryUtil.realIndexPath + System.currentTimeMillis();
		imageFile.transferTo(new File(imgFilePath));
		model.addAttribute("imgPath", getIndexImages());
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
		new File(ImageFactoryUtil.realIndexPath + "/index_0" + imgIndex + ".jpg").delete();
		model.addAttribute("imgPath", getIndexImages());
		return "/admin/indexManager";
	}

	private List<String> getIndexImages() {
		File dirFile = new File(ImageFactoryUtil.realIndexPath);
		String[] imgList = dirFile.list();
		List<String> imgPaths = new ArrayList<String>();
		for(int i=0; i<ImageFactoryUtil.indexImageNum; i++) {
			
		}
		for (String imgFile : imgList) {
			if(imgFile.indexOf("index") != 0)
				continue;
			imgPaths.add(ImageFactoryUtil.accessIndexPath + imgFile);
			logger.debug(ImageFactoryUtil.accessIndexPath + imgFile);
		}
		for(int i=imgPaths.size(); i<8; i++){
			imgPaths.add("");
		}
		return imgPaths;
	}
}
