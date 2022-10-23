package com.iu.home.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iu.home.board.qna.QnaFileVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/fileDown/*")
@Slf4j
public class FileManageController {

	@GetMapping("{path}") // RestFul, RestAPI
	public ModelAndView fileDownQna(@PathVariable String path, QnaFileVO qnaFileVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("Path {}", path);
		qnaFileVO.setFileName("KakaoTalk_20221020_144855641_01.jpg");
		qnaFileVO.setOriName("KakaoTalk_20221020_144855641_01.jpg");
		
		mv.addObject("fileVO", qnaFileVO);
		mv.addObject("path", path);
		mv.setViewName("fileManager");
		return mv;
	}
}
