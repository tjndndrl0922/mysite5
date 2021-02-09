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
	
	//ajax 글 저장
	public GuestBookVo writeResultVo(GuestBookVo guestVo) {
		
		//글 저장
		System.out.println("service dao실행전"+guestVo);
		guestDao.insertSelectKey(guestVo);
		System.out.println("servicedao 실행 후"+guestVo);
		int no = guestVo.getNo();
		
		//글 1개 가져오기
		return guestDao.selectOne(no);
	}
	
}
