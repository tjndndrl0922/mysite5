package com.javaex.controller;

import java.util.List;
import java.util.Map;

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
	
	//리스트2(검색 기능 추가)
	@RequestMapping(value="/list2")
	public String list2(@RequestParam(value="keyword", required = false, defaultValue = "") String keyword, Model model) {
		System.out.println("controller list2");
		
		List<BoardVo> boardList = boardService.getBoardList2(keyword);
		model.addAttribute("boardList", boardList);
		
		return "board/list2";
	}
	
	//리스트3(검색기능 + 페이징기능)
	@RequestMapping(value="/list3")
	public String list3(@RequestParam(value="keyword", required = false, defaultValue = "") String keyword,
						@RequestParam(value="crtPage", required = false, defaultValue = "1") int crtPage,
						Model model) {
		System.out.println("controller list3");

		Map<String, Object> pMap = boardService.getBoardList3(keyword, crtPage);
		System.out.println(pMap);
		model.addAttribute("pMap", pMap);
		return "board/list3";
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
	
	@RequestMapping(value="/rList",method = {RequestMethod.GET, RequestMethod.POST})
	public String rList(Model model) {
		System.out.println("controller rlist");
		List<BoardVo> boardrList = boardService.rList();
		model.addAttribute("boardrList", boardrList);
		return "board/rlist";
	}
	
	@RequestMapping(value="/rWrite", method = {RequestMethod.GET, RequestMethod.POST})
	public String rWrite(@ModelAttribute("boardVo") BoardVo boardVo) {
		System.out.println("controller rWrite");
		boardService.rWrite(boardVo);
		return "redirect:/board/rList";
	}
	
	@RequestMapping(value="/rRead", method = {RequestMethod.GET, RequestMethod.POST})
	public String rRead(@RequestParam("no") int no,Model model) {
		System.out.println("controller rRead");
		model.addAttribute("boardVo", boardService.rRead(no));
		return "board/rRead";
	}
}
