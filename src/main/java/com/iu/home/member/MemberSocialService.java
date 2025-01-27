package com.iu.home.member;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberSocialService extends DefaultOAuth2UserService {
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {	//로그인하고 userRequest에 담아옴
		log.info("=====social Login 시도 =========");
		log.info("UserRequest {}",userRequest);
		log.info("AccessToken {}",userRequest.getAccessToken());
		log.info("ClientRegistration {}",userRequest.getClientRegistration());
		
		String social = userRequest.getClientRegistration().getRegistrationId();
		log.info("Social : {}", social);
		
		OAuth2User auth2User = this.socialJoinCheck(userRequest);
		
		
		return auth2User;
	}
	
	private OAuth2User socialJoinCheck(OAuth2UserRequest userRequest) {
		//회원가입 유무 판단
		OAuth2User auth2User = super.loadUser(userRequest);
		log.info("=================사용자 정보=========================");
		log.info("Name => {}",auth2User.getName());
		log.info("Attr => {}", auth2User.getAttributes());
		log.info("Auth => {}",auth2User.getAuthorities());
		
		Map<String, Object> map = auth2User.getAttributes();
		
		//key들을 꺼내기
		Iterator<String> keys = map.keySet().iterator();//열거형으로 변경가능함
		while(keys.hasNext()) {
			String key = keys.next();
			log.info("Key : {} ",key);
		}
//		log.info("ClassName : {} ",auth2User.getAttribute("properties").getClass());  --ClassName : class java.util.LinkedHashMap 
		
		Map<String, String> lm = auth2User.getAttribute("properties");
		Map<String, Object> ka = auth2User.getAttribute("kakao_account");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId(auth2User.getName()); //id
		
		//pw가 없으므로, 비워두거나, 핸덤한 값으로 생성
//		memberVO.setPw(null);
		memberVO.setName(lm.get("nickname"));
		memberVO.setEmail(ka.get("email").toString());
		
		memberVO.setSocial(userRequest.getClientRegistration().getRegistrationId());
		memberVO.setAttributes(auth2User.getAttributes());
		
		//role 임의 작성하는것임 나중엔 db에서 해서 가져와야 함
		List<RoleVO> list = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleName("ROLE_MEMBER");
		
		memberVO.setRoleVOs(list);
		
		
		return memberVO;
	}

}
