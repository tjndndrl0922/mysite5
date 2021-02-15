package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.dao.UserDao;
import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value= "/user")
public class UserController {

	@Autowired
	private UserService userService;
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
		
		int count = userService.join(userVo); //꼭 count로 안받아도됨.
		
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

		UserVo authUser = userService.login(userVo);
		
		if (authUser != null) { // 성공했을때
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		} else { //실패했을때
			return "redirect:/user/loginForm?result=fail";
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
	
	//http://localhost:8088/mysite5/user/modifyForm
	@RequestMapping(value="/modifyForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("/user/modifyForm");
		
		//세션에서 no값 가져오기
		int no = ((UserVo) session.getAttribute("authUser")).getNo();
		
		//세션값이 없으면 --> 로그인폼으로
		
		
		//회원정보 가져오기
		UserVo userVo = userService.modifyForm(no);
		
		//jsp에 데이터 보내기
		model.addAttribute("userVo", userVo);
		return "user/modifyForm";
	}
	
	//http://localhost:8088/mysite5/user/modify?password=?&name=?&gender=?
	@RequestMapping(value="/modify",method = {RequestMethod.GET,RequestMethod.POST})
	public String modify(HttpSession session, @ModelAttribute UserVo userVo) {
		System.out.println("/user/modify");
		//세션에 사용자 정보 가져오기
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		//세션에서 no값 가져오기
		int no = authUser.getNo();
		
		//vo에 no 정보 추가
		userVo.setNo(no);
		
		//회원정보 수정
		int count = userService.modify(userVo);
		
		//세션에 이름변경
		authUser.setName(userVo.getName());
		
		
		return "redirect:/";
	}
	
	//회원가입 아이디 체크
	@ResponseBody
	@RequestMapping(value="/idcheck", method = {RequestMethod.GET,RequestMethod.POST})
	public String idcheck(@RequestParam("id") String id,
						  @RequestParam("password") String password) {
		System.out.println("/user/idcheck");
		System.out.println("id = " + id);
		System.out.println("password = "+ password);
		String result = userService.idcheck(id);
		System.out.println(result);
		return result; //@ResponseBody --> response의 body영역의 data만 보낸다(return 값)
	}
	
	
}
