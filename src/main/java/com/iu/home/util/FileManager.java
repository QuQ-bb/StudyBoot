package com.iu.home.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.AbstractView;

import com.iu.home.board.qna.QnaFileVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileManager extends AbstractView {
	
	@Value("${app.download.base}")
	private String base;
	@Value("${app.download.base}")
	private String delete;

	public String saveFile(MultipartFile multipartFile, String path) throws Exception {
		String fileName = UUID.randomUUID().toString();
		StringBuffer sb = new StringBuffer();
		sb.append(fileName);
		sb.append("_");
		sb.append(multipartFile.getOriginalFilename());
		log.info("sb : {}", sb.toString());
		
		File file = new File(path, sb.toString());
//		FileCopyUtils.copy(multipartFile.getBytes(),file);
		multipartFile.transferTo(file);
		return sb.toString();
	}
	
	public boolean deleteFile(QnaFileVO qnaFileVO,String path)throws Exception{
		File file = new File(path, qnaFileVO.getFileName());
		
		return file.delete();
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		QnaFileVO qnaFileVO = (QnaFileVO) model.get("fileVO");
		String path = (String) model.get("path");
		File file = new File(base+path, qnaFileVO.getFileName());
		// 한글처리
		response.setCharacterEncoding("UTF-8");
		
		// 총 파일의 크기
		response.setContentLengthLong(file.length());
		
		// 다운로드 시 파일 이름 인코딩
		String oriName = URLEncoder.encode(qnaFileVO.getOriName(), "UTF-8");
		
		// header 설정(부가정보)
		response.setHeader("Content-Disposition", "attachment;filename=\""+oriName+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		// HDD에서 파일을 읽고
		FileInputStream fis = new FileInputStream(file);
		// Client로 전송 준비
		OutputStream os = response.getOutputStream();
		// 전송
		FileCopyUtils.copy(fis, os);
		// 자원 해제
		os.close();
		fis.close();
	}
}
