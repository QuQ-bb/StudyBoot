package com.iu.home;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.iu.home.board.qna.PostVO;
import com.iu.home.util.TestInterface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {
	
//	@Value("${my.message.hi}")
	private String message;
	
	@Value("${my.default}")
	private String app;
	
//	@Value(${})
	
	private final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("/arrow")
	public void arrow() {
		//Lamda식 람다식 (JS Arrow Function)
		TestInterface t = (m)->System.out.println(m); //매개변수가 여러개면 (m,t)쉼표로 구분해줌
		t.info("test");
		//구현클래스를 만들지않아도 되는 장점 interface를 오버라이딩하지않고 코드 생산성을 높이기 위함
		
		//예전방식
//		TestInterface t2 = new TestInterface() {
//			
//			@Override
//			public void info(String message) {
//				System.out.println(message);
//			}
//		};
//		t2.info("test");
	}
	
	
	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "Admin Role";
	}
	
	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "Manager Role";
	}
	
	@GetMapping("/user")
	@ResponseBody
	public String member() {
		return "Member Role";
	}
	
	@GetMapping("/web")
	public String webClientTest() {
		Calendar calendar = Calendar.getInstance(); //객체를 만들지 않고 사용가능한 메서드 클래스명.메서드
		WebClient webClient = WebClient.builder()
									 .baseUrl("https://jsonplaceholder.typicode.com")
									 .build();
		
////		Mono<ResponseEntity<PostVO>> res = webClient.get() //mono라는건 결과물이 하나 일때
//						Mono<PostVO> res = webClient.get()
//						   						    .uri("posts/2")
//						   						    .retrieve()
//						   						    .bodyToMono(PostVO.class);
////						   						    .toEntity(PostVO.class);
//		PostVO postVO = res.block();////.getBody();
						//여러개일때
						Flux<PostVO> res = webClient.get()
						   						    .uri("posts")
						   						    .retrieve()
						   						    .bodyToFlux(PostVO.class);
						
						PostVO postVO = res.blockFirst();
						//public void test(PostVO postVO){}
						//a.test(postVO)
						res.subscribe((s)-> {	//
							PostVO pvo = s;	//타입이 맞지않는다면 에러발생
							log.info("ID : {}",s.getId());
						}); //익명함수일 경우 메서드명이 없다면 매개변수는 넘겨줌/ 매개변수가 없다면 ()안을 비워둠 subscribe 는 반복문 개념 {함수내용}
		
		log.info("Result => {}",postVO); //Result => MonoFlatMap
		
		return "";
	}
	
	
	
	
	@GetMapping("/address")
	@ResponseBody
	public String address()throws Exception{
		//kakao 지도 요청
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK ");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("query", "전북 삼성동 100");
		
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String,String>>(params, headers);
		ResponseEntity<String> res = restTemplate.getForEntity("https://dapi.kakao.com/v2/local/search/address.json", String.class, req);
		
		return res.getBody();
	}
	
	
	@GetMapping("/")
	public String home(HttpSession session) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		
		//1. Header
		HttpHeaders headers = new HttpHeaders();
//		header : key:value
//		headers.add("key", "value");
//		headers.set헤더명("값");

		//2. parametergtd6le5dn
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("key", "value");
		
		//3. 요청정보 객체(1,2번을 모음)
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(params, headers);
		
		//4. 전송 후 결과
		List<PostVO> posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", List.class ,request);
		
//		PostVO postVO  = response.getBody();
		
		log.info("POSTS => {}",posts);
		
		
		log.info("=====================");
		Enumeration<String> en = session.getAttributeNames();
		
		while(en.hasMoreElements()) {
			String key = en.nextElement();
			log.info("Key => {}", key);
		}
		 SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		 if(context != null) {
//			 log.info("context => {}",((MemberVO)context.getAuthentication().getPrincipal()).getId());
			 log.info("context => {}",context);
		 }
		log.info("default {} ", app);
		log.info("======================");
		return "index";
	}
	
}