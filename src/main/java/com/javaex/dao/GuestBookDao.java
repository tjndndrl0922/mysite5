package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//리스트
	public List<GuestBookVo> selectList(){
		
		return sqlSession.selectList("guestbook.selectList");
	}
	
	//저장
	public int insert(GuestBookVo guestVo) {
		return sqlSession.insert("guestbook.insert", guestVo);
	}
	
	//삭제
	public int delete(GuestBookVo guestVo) {
		return sqlSession.delete("guestbook.delete", guestVo);
	}
}
