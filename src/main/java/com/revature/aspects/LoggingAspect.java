package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private static Logger log = Logger.getRootLogger();
	
	@AfterReturning("within(com.revature.dao.*)")
	public void logAfter(JoinPoint jp) {
		log.info(jp.getSignature()+" was called");
	}
	
	@AfterThrowing(pointcut="within(com.revature.dao.*)", throwing="ex")
	public void logAfterException(JoinPoint jp, Exception ex) {
		log.error(jp.getSignature()+ " threw an exception: "+ex);
	}
}
