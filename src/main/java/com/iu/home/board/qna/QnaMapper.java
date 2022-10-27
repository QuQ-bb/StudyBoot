package com.iu.home.board.qna;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iu.home.util.Pager;

@Mapper
public interface QnaMapper {

	public List<QnaVO> getList(Pager pager) throws Exception;
	
	public int setQna(QnaVO qnaVO) throws Exception;
	
	public int setQnaFile(QnaFileVO qnaFileVO) throws Exception;
	
	public QnaVO getQnaDetail(QnaVO qnaVO) throws Exception;
	
	public QnaFileVO getFileDetail(QnaFileVO qnaFileVO)throws Exception;
	
	public int setQnaUpdate(QnaVO qnaVO)throws Exception;
	
	public int setFileDelete(QnaFileVO qnaFileVO)throws Exception;
}