package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//리스트(검색기능)
	public List<BoardVo> selectList2(String keyword){
		System.out.println("dao selectList2");
		return sqlSession.selectList("board.selectList2", keyword);
	}
	
	//리스트3(검색기능 + 페이징 기능)
	public List<BoardVo> selectList3(String keyword, int startRnum, int endRnum){
		System.out.println("dao selectList3");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		System.out.println(map);
		
		return sqlSession.selectList("board.selectList3",map);
	}
	
	
	public int selectTotalCnt(String keyword) {
		System.out.println("boardDao.selectTotalCnt()");
		return sqlSession.selectOne("board.selectTotalCnt", keyword);
	}
	
	//한사람 리스트 가져오기
	public BoardVo selectOne(int no) {
		System.out.println("dao selectOne");
		return sqlSession.selectOne("board.selectOne", no);
	}
	
	//조회수 증가
	public int hitupdate(int no) {
		System.out.println("dao hit");
		return sqlSession.update("board.hitUpdate", no);
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
	
	//삭제
	public int delete(int no) {
		System.out.println("dao delete");
		return sqlSession.delete("board.delete", no);
	}
	
	//rlist
	public List<BoardVo> rList(){
		System.out.println("dao rList");
		return sqlSession.selectList("board.rList");
	}
	
	//rWrite
	public int rinsert(BoardVo boardVo) {
		System.out.println("dao rWrite");
		return sqlSession.insert("board.rinsert", boardVo);
	}
	
	//rRead
	public void selectRone(int no) {
		System.out.println("dao selectRone");
		return ;
	}
}
