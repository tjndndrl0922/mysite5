package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	//list
	public List<BoardVo> list() {
		System.out.println("service list");
		return boardDao.selectList();
	}
	
	//read
	public BoardVo read(int no) {
		System.out.println("service read");
		boardDao.hitupdate(no);
		return boardDao.selectOne(no);
	}
	
	//write
	public int write(BoardVo boardVo) {
		System.out.println("service write");
		return boardDao.insert(boardVo);
	}
	
	//modifyForm
	public BoardVo modifyForm(int no) {
		System.out.println("service modifyForm");
		return boardDao.selectOne(no);
	}
	
	//modify
	public int modify(BoardVo boardVo) {
		System.out.println("service modify");
		return boardDao.update(boardVo);
	}
	
	//delete
	public int remove(int no) {
		System.out.println("service remove");
		return boardDao.delete(no);
	}
	
	//rlist
	public List<BoardVo> rList(){
		System.out.println("service rList");
		return boardDao.rList();
	}
}
