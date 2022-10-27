package com.iu.home.aop.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.iu.home.board.qna.QnaVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class QnaAdvise {
	
	@After("execution(* com.iu.home.board.qna.QnaService.get*(..))")
	public void beforeTest(JoinPoint joinPoint) {
		log.info("------------------ After ---------------------");
		log.info("Args : {}", joinPoint.getArgs());
		log.info("Kind : {}", joinPoint.getKind());
	}

//	@Around("execution(* com.iu.home.board.qna.QnaService.set*(..))")
	public Object aroundTest(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("------------------ Before ---------------------");
		log.info("Target : {}", joinPoint.getTarget());
		log.info("This : {}", joinPoint.getThis());
		log.info("Args : {}", joinPoint.getArgs());
		Object[] objects = joinPoint.getArgs();
		QnaVO qnaVO = (QnaVO) objects[0]; // 다형성
		
		Object object = joinPoint.proceed();
		
		log.info("------------------ Affer ---------------------");
		log.info("object : {}", object);
		return object;
	}
}