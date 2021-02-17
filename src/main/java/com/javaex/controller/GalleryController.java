package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController {

	@Autowired
	private GalleryService GalleryService;
	
	//전체 리스트 출력
	@RequestMapping(value="/list" , method = {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		System.out.println("GalleryController.list()");
		List<GalleryVo> galleryList = GalleryService.list();
		model.addAttribute("galleryList", galleryList);
		return "/gallery/list";
	};
	
	//이미지 등록
	@RequestMapping(value="/upload")
	public String upload(@RequestParam("file") MultipartFile file,
						 @RequestParam("content") String content) {
		System.out.println("GalleryController.upload()");
		GalleryService.restore(file, content);
		return "";
	}
	
}
