package com.iu.home.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

//DI, IOC-주입해주세요
@Service
@Slf4j
public class MemberSecurityService implements UserDetailsService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		MemberVO memberVO = new MemberVO();
//		memberVO.setId(username);
		log.info("======로그인 시도 중=======");
		MemberVO memberVO= memberMapper.getLogin(username);
		log.info("Member => {}",memberVO);	
		return memberVO;	//reference 타입의 기본값은 null
	}
}
