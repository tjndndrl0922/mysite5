package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	//리스트가져오기
	public List<GalleryVo> selectList() {
		System.out.println("GalleryDao.selectList()");
		
		return sqlSession.selectList("gallery.selectList");
	}
	
	//사진 업로드
	public int insert(GalleryVo galleryVo) {
		System.out.println("GalleryDao.insert()");
		return sqlSession.insert("gallery.insert", galleryVo);
	}
	
}
