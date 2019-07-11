package com.revature.aspects;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.revature.dao.TrainerDao;
import com.revature.models.Pokemon;
import com.revature.models.Trainer;

@Aspect
@Component
public class ValidationAspect {
	@Autowired
	private TrainerDao td;
	
	@Around("within(com.revature.controllers.*) && !within(com.revature.controllers.LoginController)")
	public Object tokenAuth(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String auth = request.getHeader("Authentication");
		if(auth == null)
			return new ResponseEntity<String>("Invalid Authentication", null, HttpStatus.FORBIDDEN);
		String[] tokens = auth.split(":");
		if(tokens.length != 2 || !tokens[0].matches("\\d+"))
			return new ResponseEntity<String>("Invalid Authentication", null, HttpStatus.FORBIDDEN);
		Trainer t = td.getTrainerById(Integer.parseInt(tokens[0]));
		if(t == null || !t.getLogin().equals(tokens[1]))
			return new ResponseEntity<String>("Invalid Authentication", null, HttpStatus.FORBIDDEN);
		return pjp.proceed();
	}
	
	@Around("@annotation(com.revature.validAnnot.ValidTrainer)")
	public Object validTrainer(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Validating Trainer");
		Trainer t = (Trainer)pjp.getArgs()[0];
		if(t == null || t.getLogin() == null) {
			return new ResponseEntity<String>("Invalid Trainer", null, HttpStatus.BAD_REQUEST);
		}
		return pjp.proceed();
	}
	
	@Around("@annotation(com.revature.validAnnot.ValidPokemon)")
	public Object validPokemon(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Validating Pokemon");
		Pokemon p = (Pokemon)pjp.getArgs()[0];
		if(p == null || p.getTrainer_id() == null)
			return new ResponseEntity<String>("Invalid Pokemon", null, HttpStatus.BAD_REQUEST);
		return pjp.proceed();
	}
}
