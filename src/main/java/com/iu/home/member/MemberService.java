package com.iu.home.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Value("${social.kakao.admin}")
	private String adiminKey;
	
	public int setDelete(MemberVO memberVO)throws Exception{
		//webClient방식으로 구현
		
		//1.webClient 생성
		WebClient webClient = WebClient.builder()
									   .baseUrl("https://kapi.kakao.com/")	//주소를 통으로 넣어도 괜찮음
									   .build();
		
		//2. parameter
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();// 타입이 다르면 Object
		map.add("target_id_type", "user_id");
		map.add("target_id", memberVO.getId());
			Mono<String> res = webClient.post()
									    .uri("v1/user/unlink")
								  	    .body(BodyInserters.fromFormData(map))
									    .header("Authorization","KakaoAK " + adiminKey)
									    .header("Content-Type","application/x-www-form-urlencoded")
									    .retrieve()
									    .bodyToMono(String.class);	//어떤 타입으로 받는지 그거에 대한 타입.class
									    
			log.info("WebClientResult =>",res);		
			
			return 1;
	}
	
	public int setDelete2(MemberVO memberVO)throws Exception{
		//restTemplate방식으로 구현
		int result =0;
		RestTemplate restTemplate = new RestTemplate();
		
		//--Header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); //application/x-www-form-urlencoded
		headers.add("Authorization","KakaoAK " + adiminKey);
		
		//--parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("target_id_type", "user_id");	// -d "target_id_type=user_id" 
		params.add("target_id", memberVO.getId()); // -d "target_id=123456789" 

		//--요청 객체
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params,headers);
		
		//--전송 후 결과 처리
		ResponseEntity<String> res	= restTemplate.postForEntity("https://kapi.kakao.com/v1/user/unlink", req, String.class);
		
		log.info("res => {}", res.getBody());
		
		if(res.getBody() != null) {
			result =1;
		}
		
		return result;
	}
	
	
	public Long getIdCheck(MemberVO memberVO)throws Exception{
		return memberMapper.getIdCheck(memberVO);
	}
	
	//사용자 정의 검증 메서드
	public boolean getMemberError(MemberVO memberVO,BindingResult bindingResult)throws Exception{
		boolean check =false;
		//check = false : 검증성공(error없음)
		//check = true  : 검증실패(error 있음)
		
		//1. annotation 검증
		check = bindingResult.hasErrors();
		
		//2. password가 일치하는지 검증
		if(!memberVO.getPw().equals(memberVO.getPwCheck())) {
			check=true;
			//에러메서지
//			bindingResult.rejectValue("멤버변수명(path)", "properties의 key(코드)");
			bindingResult.rejectValue("pwCheck", "member.password.notEqual");
		}
		
		//3. id가 중복 인지 검증
		Long result = memberMapper.getIdCheck(memberVO);
		if(result >0) {
			check =true;
			bindingResult.rejectValue("id", "member.id.duplication");
		}
		
		return check;
	}
	
	
	public int setJoin(MemberVO memberVO)throws Exception{
		
		int result = memberMapper.setJoin(memberVO);
		if(result <1) {
			throw new Exception();
		}
			memberMapper.setRole(memberVO);
		
		return result;
	}
	
	//로그인 처리는 Security에서 처리 함 
//	public MemberVO getLogin(MemberVO memberVO)throws Exception{
//		return memberMapper.getLogin(memberVO);
//	}

}
