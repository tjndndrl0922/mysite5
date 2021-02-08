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
		return sqlSession.insert("user.insert", userVo);
	}
	
	//로그인 --> 회원정보 가져오기
	public UserVo selectUser(UserVo userVo) {
		System.out.println("dao - selectUser");
		System.out.println(userVo.toString());
		
		return sqlSession.selectOne("user.selectUser", userVo);
	}
	
	//수정폼 --> 회원정보 가져오기
	public UserVo getUser(int no) {
		System.out.println("dao - getUser");
		
		return sqlSession.selectOne("user.getUser", no);
	}
	
	//수정
	public int update(UserVo userVo) {
		System.out.println("dao - update");
		return sqlSession.update("user.update", userVo);
	}
	
	//회원가입 아이디 체크
	public UserVo selectOne(String id) {
		System.out.println("dao - idcheck");
		return sqlSession.selectOne("user.selectById", id);
	}
}
