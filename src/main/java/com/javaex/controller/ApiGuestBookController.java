package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping(value = "/api/guestbook")
public class ApiGuestBookController {
	
	@Autowired
	private GuestBookService guestbookService;
	
	//전체리스트 가져오기(ajax)
	@RequestMapping(value="/list")
	public List<GuestBookVo> list() {
		System.out.println("ApiGuestBookController list");
		return guestbookService.addList();
	}
	
	//글작성(ajax)
	@ResponseBody
	@RequestMapping(value="/write", method = {RequestMethod.GET,RequestMethod.POST})
	public GuestBookVo api(@ModelAttribute GuestBookVo guestVo) {
		System.out.println("ApiGusetBookController write");
		System.out.println(guestVo);
		
		//입력된 vo 전달하고 저장된 vo를 받아야됨
		return guestbookService.writeResultVo(guestVo);
	}
	

}
