package com.iu.home.board.qna;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class QnaVO {

	private Long num;
	@NotBlank
	private String title;
	@Size(max=11,min=2)
	private String writer;
	@Size(max=10,min=2)
	private String contents;
	private Long hit;
	private Date regDate;
	private Long ref;
	private Long step;
	private Long depth;
	private MultipartFile[] files;
	private List<QnaFileVO> qnaFileVOs;
}