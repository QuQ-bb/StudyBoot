package com.iu.home.member.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.iu.home.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogoutSuccessCustom implements LogoutSuccessHandler{
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String restApi;
	@Value("${spring.kakao.logout-url}")
	private String rediectUrl;

	
	@Override
		public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
				throws IOException, ServletException {
			log.info("========api 주소================",restApi);
			MemberVO memberVO =  (MemberVO)authentication.getPrincipal(); // memberVO
			String social =  memberVO.getSocial();
			if(social != null) {
					if(social.equals("kakao")) {
//						try {
							response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id="+restApi+"&logout_redirect_uri="+rediectUrl);
//							response.sendRedirect("https://developers.kakao.com/logout");
//							RestTemplate restTemplate = new RestTemplate();
							//header X
							//parameter X
//							MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//							map.add("client_id", restApi);
							
//							HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String, String>>(map);
							
//							ResponseEntity<String> res = restTemplate.getForEntity("https://developers.kakao.com/logout", null, String.class);
//							log.info("res => {}", res);
//							response.sendRedirect("/");
							
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
					}else if(social.equals("google")) {
						
					}else {
					}
			}else {
				log.info("=======Logout Success시에만 실행 ========");
				response.sendRedirect("/");
				
			}
		}

}
