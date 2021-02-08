package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	// 회원가입
	public int join(UserVo userVo) {
		System.out.println("userService join()");
		return userDao.insert(userVo);
	}
	
	//로그인
	public UserVo login(UserVo userVo) {
		System.out.println("userService login()");
		return userDao.selectUser(userVo);
	}
	
	//회원정보 수정 폼
	public UserVo modifyForm(int no) {
		System.out.println("userSerivce modifyForm()");
		return userDao.getUser(no);
	}
	
	//회원정보 수정
	public int modify(UserVo userVo) {
		System.out.println("userService modify()");
		return userDao.update(userVo);
	}
	
	//회원가입 아이디 체크
	public String idcheck(String id) {
		System.out.println("userService idcheck()");
		UserVo userVo = userDao.selectOne(id);
		String result = "";
		
		if(userVo==null) {
			//사용할수 있는 아이디
			result = "can";
		}else {
			//중복 아이디, 사용할수 없는 아이디
			result= "cant";
		}
		return result;
	}
	
}
