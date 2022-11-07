package com.iu.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.iu.home.member.security.LoginFail;
import com.iu.home.member.security.LoginSuccess;
import com.iu.home.member.security.LogoutCoustom;
import com.iu.home.member.security.LogoutSuccessCustom;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
		@Autowired
		private LoginSuccess loginSuccess;
		
		@Autowired
		private LoginFail loginFail;
		
		@Autowired
		private LogoutCoustom logoutCoustom;
		
		@Autowired
		private LogoutSuccessCustom logoutSuccessCustom;
	
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
					.antMatchers("/manager").hasAnyRole("ADMIN","MANAGER")
					.antMatchers("/qna/list").permitAll()	//아래꺼랑 순서 되게 중요함 위치변경하면 안됨
					.antMatchers("/qna/**").hasRole("MEMBER")
					.anyRequest().permitAll()
					.and()
				.formLogin()
					.loginPage("/member/login") //내장된 로그인폼을 사용하지 않고 개발자가 만든 폼요청URL
//					.loginProcessingUrl("login") 	//로그인을 진행 요청할 form 태그의 action의 주소 지정(필수아님)
					.passwordParameter("pw")	//패스워드 파라미터는 password이지만, 개발자가 다른이름으로 파라미터이름을 사용할 때
					.usernameParameter("id")	//아이디 파라미터는 username이지만, 개발자가 다른이름으로 파라미터이름을 사용할 때
					//.defaultSuccessUrl("/")		//인증에 성공할 경우 요청할 URL --redirect: 가 잇어도  그냥 뒤에 있는 주소만 사용해줘도 됨 
					.successHandler(loginSuccess)	//참조변수 넣어주기
//					.failureUrl("/member/login?error=true&message=LoginFail")//인증에 실패 했을 경우 요청할 URL -- 다시 login으로 가거나 실패메시지 뿌려주는 jsp URL사용가능
					.failureHandler(loginFail)
					.permitAll()
					.and()
				.logout()
					.logoutUrl("/member/logout")
//					.logoutSuccessUrl("/")
					.logoutSuccessHandler(logoutSuccessCustom)
					.addLogoutHandler(logoutCoustom)
					.invalidateHttpSession(true) //true : 한다 false : 안한다
					.deleteCookies("JSESSIONID")	//쿠기 제거
					.permitAll()
					.and()
				.rememberMe()	//rememberMe설정
					.rememberMeParameter("rememberMe")	//파라미터명
					.tokenValiditySeconds(300)	//토큰(로그인유지)유지시간 언제까지 유지할것인지 단위는 초
					.key("rememberMe")
					
					
					;
		
		return httpSecurity.build();
	}
	
	//평문(Clear Text)을 암호화 시켜주는 객체생성 --평문이란 사람이 읽을 수 있는 글자
	//@Bean 은 메소드 레벨에서 선언하며, 반환되는 객체(인스턴스)를 개발자가 수동으로 빈으로 등록
	//개발자가 직접 제어가 불가능한 외부 라이브러리 등을 Bean으로 만들려고 할 때 사용됩니다.
	@Bean
	public PasswordEncoder getEncoder() { //평문을 암호화시켜주는 클래스
		return new BCryptPasswordEncoder(); 
	}
	

}
