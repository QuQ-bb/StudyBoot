package com.iu.home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	//public을 선언하면 default로 바꾸라는 메세지 출력
	WebSecurityCustomizer webSecurityConfig() {
		//Security에서 무시해야하는 URL 패턴 등록
		return web -> web
				.ignoring()
				.antMatchers("/images/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/favicon/**")
				.antMatchers("/resources/**");
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception {
		httpSecurity
					.cors()
					.and()
					.csrf()
					.disable()
				.authorizeRequests()
//					.antMatchers("/").permitAll()
					.antMatchers("/admin").hasRole("ADMIN")
					.antMatchers("/manager").hasRole("MANAGER")
					.antMatchers("/qna/list").permitAll()	//아래꺼랑 순서 되게 중요함 위치변경하면 안됨
					.antMatchers("/qna/**").hasRole("MEMBER")
					.anyRequest().permitAll()
					.and()
				.formLogin()
					.loginPage("/member/login") //내장된 로그인폼을 사용하지 않고 개발자가 만든 폼요청URL
//					.loginProcessingUrl("login") 	//로그인을 진행 요청할 form 태그의 action의 주소 지정(필수아님)
					.passwordParameter("pw")	//패스워드 파라미터는 password이지만, 개발자가 다른이름으로 파라미터이름을 사용할 때
					.usernameParameter("id")	//아이디 파라미터는 username이지만, 개발자가 다른이름으로 파라미터이름을 사용할 때
					.defaultSuccessUrl("/")		//인증에 성공할 경우 요청할 URL --redirect: 가 잇어도  그냥 뒤에 있는 주소만 사용해줘도 됨 
					.failureUrl("/member/login")//인증에 실패 했을 경우 요청할 URL -- 다시 login으로 가거나 실패메시지 뿌려주는 jsp URL사용가능
					.permitAll()
					.and()
				.logout()
					.permitAll();
		
		return httpSecurity.build();
	}

}
