package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value="/board")
public class BoradController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/list",method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("controller list");
		List<BoardVo> boardList = boardService.list();
		model.addAttribute("boardList", boardList);
		return "board/list";
	}
	
	@RequestMapping(value="/read", method = {RequestMethod.GET, RequestMethod.POST})
	public String read(@RequestParam("no") int no,Model model) {
		System.out.println("controller read");
		model.addAttribute("boardVo", boardService.read(no));
		return "board/read";
	}
	
	@RequestMapping(value="/writeForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String writeForm() {
		System.out.println("controller writeForm");
		return "board/writeForm";
	}
	
	@RequestMapping(value="/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute("boardVo") BoardVo boardVo) {
		System.out.println("controller write");
		boardService.write(boardVo);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@RequestParam("no") int no,Model model) {
		System.out.println("controller modifyForm");
		BoardVo boardVo = boardService.modifyForm(no);
		model.addAttribute("boardVo", boardVo);
		return "board/modifyForm";
	}
	
	@RequestMapping(value="modify",  method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute("boardVo") BoardVo boardVo) {
		System.out.println("controller modify");
		boardService.modify(boardVo);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public String remove(@RequestParam("no") int no) {
		System.out.println("controller remove");
		boardService.remove(no);
		return "redirect:/board/list"; 
	}
}
