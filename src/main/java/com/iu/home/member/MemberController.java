package com.iu.home.member;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("idCheck")
	@ResponseBody
	public Long getIdCheck(MemberVO memberVO)throws Exception{
		Long result = memberService.getIdCheck(memberVO);
		if(result ==1) {
			log.info("result");
		}else {
			log.info("한종석 짜증");
		}
		
		return result;
	}
	
	@GetMapping("add")
	public String setJoin(@ModelAttribute MemberVO memberVO)throws Exception{
//		MemberVO memberVO = new MemberVO();
//		model.addAttribute("vo",memberVO);
		return "member/add";
	}
	
	@PostMapping("add")	//mv를 보내서 @valid로 검증하고 검증의 결과를 BindingResult로 보내줌
	public ModelAndView setJoin(ModelAndView mv, @Valid MemberVO memberVO,BindingResult bindingResult)throws Exception{
		
//		if(bindingResult.hasErrors()) {
//			//검증에 실패하면 회원가입하는 jsp로 foward 이동
//			log.info("========= 검증 에러 발생 ==========");
//			mv.setViewName("member/add");
//			return mv;
//		}
		boolean check = memberService.getMemberError(memberVO, bindingResult);
		if(check) {
			log.info("========= 검증 에러 발생 ==========");
			mv.setViewName("member/add");
			//======================================================
			List<FieldError> errors = bindingResult.getFieldErrors();
			
			for(FieldError fieldError:errors) {
				log.info("FieldError => {}",fieldError);
				log.info("Field = {}",fieldError.getField());
				log.info("Message => {}",fieldError.getRejectedValue());
				log.info("Default => {}",fieldError.getDefaultMessage());
				log.info("code => {}",fieldError.getCode());
				mv.addObject(fieldError.getField(), fieldError.getDefaultMessage());
				log.info("============================================");
				
				
			}
			return mv;
		}
//		 memberService.setJoin(memberVO);
		 mv.setViewName("redirect:/member/login");
		 	return mv;
	}
	
	@GetMapping("login")
	public String getLogin(@RequestParam(defaultValue = "false", required = false) boolean error , String message,Model model)throws Exception{ //reference타입 Boolean도 사용가능(reference타입 기본값은 null이기대문에)
//		if(error) {
//			model.addAttribute("msg", "ID 또는 PW를 확인하세요");
//		}
		//Controller에서 받아서 jsp로 다시 보내도 됨
	
		return "member/login";
	}
	
	@PostMapping("login")
	public String getLogin()throws Exception{
		log.info("=====Login Post===========");
		return "member/login";
	}
	
	@GetMapping("mypage")
	public String getMypage()throws Exception{
		return "member/mypage";
	}
	
	
//	@GetMapping("/logout")
//	public String logout(HttpSession session)throws Exception{
//		log.info("==== 내가 만든 logout메서드=====");
//		session.invalidate();
//		return "redirect:/member/login";
//	}

}
