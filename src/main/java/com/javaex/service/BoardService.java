package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//리스트(검색기능)
	public List<BoardVo> getBoardList2(String keyword){
		System.out.println("BoardService-getBoardList2");
		System.out.println("keyword-" + keyword);
		
		List<BoardVo> boardList = boardDao.selectList2(keyword);
		return boardList;
	}
	
	//리스트3(검색기능 + 페이징기능)
	public Map<String, Object> getBoardList3(String keyword, int crtPage){
		System.out.println("BoardService-getList3");
		
		///////////////////////////
		//리스트구하기
		///////////////////////////
		int listCnt = 10;
		
		//현재페이지
		//if문 대신에 삼항연산자 사용
		crtPage = (crtPage > 0) ? crtPage : (crtPage=1);
		//crtPage
		
		//시작글 번호 startRnum
		int startRnum = (crtPage -1) * listCnt +1;
		
		//끝글 번호 endRnum
		int endRnum = (startRnum + listCnt) -1;
		
		List<BoardVo> boardList = boardDao.selectList3(keyword, startRnum, endRnum);
		
		/////////////////////////////
		//페이징 계산
		/////////////////////////////
		int pageBtnCount=5;
		
		//전체 글갯수 구하기
		int totalCount = boardDao.selectTotalCnt(keyword);
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount) * pageBtnCount;
				
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);
	
		//다음버튼 boolean
		boolean next;
		if(endPageBtnNo * listCnt < totalCount) {
			next = true;
		}else {
			next = false;
			endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
		};
		
		//이전버튼 boolean
		boolean prev;
		if(startPageBtnNo != 1) {
			prev = true;
		}else {
			prev = false;
			
		};
		//boardList, prev, startPageBtnNo, endPageBtnNo, next --> jsp map 전달
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("boardList", boardList);
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		
		return pMap;
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
		System.out.println(boardVo);
		for(int i =1; i<=1234; i++) {
			boardVo.setTitle(i+"번째 글 제목입니다.");
			boardVo.setContent(i+"번째 글 내용입니다");
		}	
		
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
	
	//rWrite
	public int rWrite(BoardVo boardVo) {
		System.out.println("service rWrite");
		return boardDao.rinsert(boardVo);
	}
	
	//rRead
	public BoardVo rRead(int no) {
		System.out.println("controller");
		return null;
	}
}
