package com.iu.home.member.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.iu.home.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogoutCoustom implements LogoutHandler{
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
		log.info("===========LogoutHandler==========");
		//1. 일반 로그인 ?? 아니면 social login 사용??
		//뭔로 구분함? request에선 알수없음 request에선 요청정보만 있음 authentication - 사용자 
		log.info("Auth => {}", authentication);
		//일반 로그인 시 UsernamePasswordAuthenticationToken social=null
		//social 로그인 시 OAuth2AuthenticationToken social=kakao
		
		
//		if(social != null && social.equals("kakao")) {
//			
//		}else if(social != null && social.equals("google")){
//			
//		}else {
//			
//		}
		request.getSession().invalidate();
	}
}
