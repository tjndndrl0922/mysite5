package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//회원가입 --> 회원정보 저장 
	public int insert(UserVo userVo) {
		System.out.println("dao - insert");	
		System.out.println(userVo.toString());
		
		int count = sqlSession.insert("user.insert", userVo);
		System.out.println("count" + count);
		return count;
	}
	
	//로그인 --> 회원정보 가져오기
	public UserVo selectUser(UserVo userVo) {
		System.out.println("dao - selectUser");
		System.out.println(userVo.toString());
		
		UserVo vo = sqlSession.selectOne("user.selectUser", userVo);
		return vo;
	}
	
	//수정폼 --> 회원정보 가져오기
	public int getUser(UserVo userVo) {
		System.out.println("dao - getUser");
		
		int count = sqlSession.selectOne("user.getUser", userVo);
		return count;
	}
	
	//수정
	public int update(UserVo userVo) {
		System.out.println("dao - update");
		int count = sqlSession.update("user.update", userVo); 
		return count;
	}
	
}
