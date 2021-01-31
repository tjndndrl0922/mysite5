package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//리스트 가져오기
	public List<BoardVo> selectList(){
		  System.out.println("dao selectList");
	      return sqlSession.selectList("board.selectList");
	}
	
	//한사람 리스트 가져오기
	public BoardVo selectOne(int no) {
		System.out.println("dao selectOne");
		return sqlSession.selectOne("board.selectOne", no);
	}
	
	//저장
	public int insert(BoardVo boardVo) {
		System.out.println("dao insert");
		return sqlSession.insert("board.insert", boardVo);
	}
	
	//수정
	public int update(BoardVo boardVo) {
		System.out.println("dao update");
		return sqlSession.update("board.update", boardVo);
	}
}