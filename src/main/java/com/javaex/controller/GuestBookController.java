package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping(value = "/guest")
public class GuestBookController {

	@Autowired
	private GuestBookService guestService;

	// 리스트
	@RequestMapping(value = "/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model) {
		System.out.println("리스트 처리");
		List<GuestBookVo> guestList = guestService.addList();
		model.addAttribute("guestList", guestList);
		return "guestbook/addList";
	}

	// 저장
	@RequestMapping(value = "/insert", method = { RequestMethod.GET, RequestMethod.POST })
	public String insert(@ModelAttribute GuestBookVo guestVo) {
		System.out.println("controller - insert");
		guestService.insert(guestVo);

		return "redirect:/guestbook/addList";
	}

	// 삭제폼
	@RequestMapping(value = "/deleteForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm() {

		return "guestbook/deleteForm";
	}

	// 삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@ModelAttribute GuestBookVo guestVo) {
		guestService.delete(guestVo);
		return "redirect:/guestbook/addList";
	}
}
