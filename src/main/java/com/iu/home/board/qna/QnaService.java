package com.iu.home.board.qna;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iu.home.util.FileManager;
import com.iu.home.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService {

	@Autowired
	private QnaMapper qnaMapper;
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.qna}")
	private String path;
	
	public List<QnaVO> getList(Pager pager) throws Exception {
		return qnaMapper.getList(pager);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int setQna(QnaVO qnaVO) throws Exception {
		int result = qnaMapper.setQna(qnaVO);
		File file = new File(path);
		if(!file.exists()) file.mkdirs();
		for(MultipartFile f : qnaVO.getFiles()) {
			
			if(f.isEmpty()) { 
				continue;
			}
			String fileName = fileManager.saveFile(f, path);
			QnaFileVO qnaFileVO = new QnaFileVO();
			qnaFileVO.setFileName(fileName);
			qnaFileVO.setOriName(f.getOriginalFilename());
			qnaFileVO.setNum(qnaVO.getNum());
			qnaMapper.setQnaFile(qnaFileVO);
		}
		return 0;
	}
	
	public QnaVO getQnaDetail(QnaVO qnaVO) throws Exception {
		return qnaMapper.getQnaDetail(qnaVO);
	}
}
