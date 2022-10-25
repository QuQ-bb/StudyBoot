package com.iu.home.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("add")
	public String setJoin()throws Exception{
		return "member/join";
	}
	
	@PostMapping("add")
	public String setJoin(MemberVO memberVO)throws Exception{
		 memberService.setJoin(memberVO);
		
		return "redirect:/member/login";
	}
	
	@GetMapping("login")
	public String getLogin()throws Exception{
		return "member/login";
	}
	
	@PostMapping("login")
	public String getLogin(MemberVO memberVO,HttpSession session)throws Exception{
		memberVO = memberService.getLogin(memberVO);
		session.setAttribute("member", memberVO);
		return "redirect:/";
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session)throws Exception{
		session.invalidate();
		return "redirect:/member/login";
	}

}
