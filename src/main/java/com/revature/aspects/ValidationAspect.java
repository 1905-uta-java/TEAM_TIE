package com.revature.aspects;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ValidationAspect {
	@Around("within(com.revature.controllers.*) && !within(com.revature.controllers.LoginController)")
	public Object TokenAuth(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Testing authentication.");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpHeaders responseHeaders = new HttpHeaders();
		System.out.println("Request URI: " + request.getRequestURI().toString());
		if(request.getHeader("Authentication") == null)
			return new ResponseEntity<String>("Invalid Authentication", responseHeaders, HttpStatus.FORBIDDEN);
		return pjp.proceed();
	}
}
