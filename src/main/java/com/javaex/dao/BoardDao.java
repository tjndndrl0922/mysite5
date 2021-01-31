package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	private SqlSession sqlSession;
	
	public List<BoardVo> selectList(){
		System.out.println("dao selectList");
		return sqlSession.selectList("board.selectList");
	}
	
}
