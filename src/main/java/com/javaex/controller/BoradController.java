package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value="/board")
public class BoradController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/list",method = {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		System.out.println("controller list");
		List<BoardVo> boardList = boardService.list();
		model.addAttribute("boardList", boardList);
		return "board/list";
	}
}
