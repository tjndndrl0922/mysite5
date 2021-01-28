package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestBookDao guestDao;
	
	//리스트
	public List<GuestBookVo> addList(){
		System.out.println("service 리스트");
		return guestDao.selectList();
	}
	
	//저장
	public int insert(GuestBookVo guestVo) {
		System.out.println("service 저장");
		return guestDao.insert(guestVo);
	}
	
	//삭제
	public int delete(GuestBookVo guestVo) {
		return guestDao.delete(guestVo);
	}
	
}
