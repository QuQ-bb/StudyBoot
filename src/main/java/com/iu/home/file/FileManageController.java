package com.iu.home.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iu.home.board.qna.QnaFileVO;
import com.iu.home.board.qna.QnaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/fileDown/*")
@Slf4j
public class FileManageController {
	
	@Autowired
	private QnaService qnaService;

	@GetMapping("{path}") // RestFul, RestAPI
	public ModelAndView fileDownQna(@PathVariable String path, QnaFileVO qnaFileVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		if(path.equals("qna")) {
			qnaFileVO = qnaService.getFileDetail(qnaFileVO);
		}else if(path.equals("notice")) {
			qnaFileVO.setFileName("KakaoTalk_20221020_144855641_01.jpg");
			qnaFileVO.setOriName("KakaoTalk_20221020_144855641_01.jpg");
		}
		log.info("Path {}", path);
		
		mv.addObject("fileVO", qnaFileVO);
		mv.addObject("path", path);
		mv.setViewName("fileManager");
		
		//BeanNameResolver:
		//view의 이름과 일치하는 bean의 이름이 있으면  해당 bean을 실행
		
		//InternalResourceViewResolver
		//WEB-INF/views/fileManager.jsp
		return mv;
	}
}
