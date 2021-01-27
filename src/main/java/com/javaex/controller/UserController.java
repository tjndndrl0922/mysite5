package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value= "/user")
public class UserController {
	
	//필드
	@Autowired
	private UserDao userDao;
	
	//생성자
	
	//메소드 g.s
	
	//일반메소드
	//http://localhost:8088/mysite5/user/joinForm 회원가입폼
	@RequestMapping(value="/joinForm", method = {RequestMethod.GET, RequestMethod.POST} )
	public String joinForm() {
		System.out.println("/user/joinForm");
		return "user/joinForm";
	}
	
	//http://localhost:8088/mysite5/user/join?id=?&password=?&name=?&gender=? 회원가입
	@RequestMapping(value="/join", method = {RequestMethod.GET,RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("/user/join");
		System.out.println(userVo.toString());
		
		int count = userDao.insert(userVo);
		
		return "user/joinOk";
	}
	
	//http://localhost:8088/mysite5/user/loginForm 로그인폼
	@RequestMapping(value="/loginForm",method = {RequestMethod.GET,RequestMethod.POST})
	public String loginForm() {
		System.out.println("/user/loginForm");
		return "user/loginForm";
	}
	
	//http://localhost:8088/mysite5/user/login?id=?&password=? 로그인
	@RequestMapping(value="/login", method = {RequestMethod.GET,RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("/user/login");
		System.out.println(userVo.toString());
		
		UserVo authUser = userDao.selectUser(userVo);
		
		//실패했을때 --> 로그인폼 result = fail
		if(authUser == null) {
			System.out.println("login/fail");
			return "redirect:/user/loginForm&result=fail";
		
		//성공했을때
		}else {
			System.out.println("login/success");
			
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		}
	}
	
	//http://localhost:8088/mysite5/user/logout	로그아웃
	@RequestMapping(value="/logout", method = {RequestMethod.GET,RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("/user/logout");
		
		session.getAttribute("authUser");
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	//http://localhost:8088/mysite5/user/modifyForm?no=?
	@RequestMapping(value="/modifyForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(HttpSession session, @ModelAttribute UserVo userVo) {
		System.out.println("/user/modifyForm");
		
		int count = userDao.getUser(userVo);
		
		return "user/modifyForm";
	}
	
	//http://localhost:8088/mysite5/user/modify?
	@RequestMapping(value="/modify",method = {RequestMethod.GET,RequestMethod.POST})
	public String modify(HttpSession session, @ModelAttribute UserVo userVo) {
		System.out.println("/user/modify");
		
		int count = userDao.update(userVo);
		
		return"";
	}
	
	
}
