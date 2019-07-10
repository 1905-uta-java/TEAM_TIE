package com.revature.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

public class ValidationAspect {
	@Around("within(com.revature.controllers.*) && !within(com.revature.controllers.LoginController.java)")
	public void TokenAuth(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Testing authentication.");
		pjp.proceed();
	}
}
