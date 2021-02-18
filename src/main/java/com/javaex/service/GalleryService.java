package com.javaex.service;

import javax.servlet.http.HttpSession;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao GalleryDao;

	private HttpSession session;
	
	public List<GalleryVo> list() {
		System.out.println("GalleryService.list()");
		return GalleryDao.selectList();
	}
	
	public void restore(MultipartFile file, String content) {
		System.out.println("GalleryService.restore()");
				
				//세션에 있는 userNo 가져오기
				UserVo authUser = (UserVo)session.getAttribute("authUser");
				int userNo = authUser.getNo();
				
				//db저정할 정보 수집
				String saveDir = "C:\\javaStudy\\upload";
				
				//오리지널 파일이름
				String orgName = file.getOriginalFilename();
				System.out.println("orgName:" + orgName);
				
				//확장자
				String exName = orgName.substring(orgName.lastIndexOf("."));
				System.out.println("exName:"+exName);
				
				//서버 저장 파일 이름	
				String saveName = System.currentTimeMillis()+UUID.randomUUID().toString() + exName;
				System.out.println("savaName:"+saveName);
				
				//서버 파일 패스 --> 저장경로
				String filePath = saveDir + "\\" + saveName;
				System.out.println("filePath:"+filePath);
				
				//파일 사이즈
				long fileSize = file.getSize();
				System.out.println("fileSize:"+fileSize);
				
				//dao에 보낼 자료 묶기
				GalleryVo galleryVo = new GalleryVo(userNo,content,filePath,orgName,saveName,fileSize);
				System.out.println(galleryVo);
				
				GalleryDao.insert(galleryVo);
				
				//서버 하드디스크 파일  byte 형식으로 저장
				try {
					byte[] fileData = file.getBytes();
					OutputStream out = new FileOutputStream(filePath);
					BufferedOutputStream bos = new BufferedOutputStream(out);
					
					bos.write(fileData);
					bos.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
		
	}
}
