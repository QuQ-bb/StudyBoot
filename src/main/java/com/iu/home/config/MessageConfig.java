package com.iu.home.config;

import java.util.Locale;

import javax.websocket.Session;

import org.apache.tomcat.jni.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration //****-context.xml
public class MessageConfig implements WebMvcConfigurer{
	
	@Bean
	public LocaleResolver locale() {
		//1, session
//		SessionLocaleResolver resolver = new SessionLocaleResolver();
//		resolver.setDefaultLocale(Locale.KOREAN);
		
		//2.
		CookieLocaleResolver cResolver = new CookieLocaleResolver();
		cResolver.setDefaultLocale(Locale.KOREAN);
		cResolver.setCookieName("lang");
		return cResolver;
	}
	//둘 중하나만 만들어서 사용하면됨
	
	//Interceptor 객체
	@Bean
	public LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("lang");
		//parameter를 받아서 언어 구분
		//url?lang=ko
		return changeInterceptor;
	}
	
	
	
	//***-context.xml
	//<bean class="" id=""> == new 생성자() --객체생성코드
	//메서드를 통해 객체생성
//	@Bean //<bean>
//	public String getString() {
//		return new String();
//	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		
		
	}
}
