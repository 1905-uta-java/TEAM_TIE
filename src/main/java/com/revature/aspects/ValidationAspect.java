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
import com.revature.models.Trainer;

@Aspect
@Component
public class ValidationAspect {
	@Autowired
	private TrainerDao td;
	
	@Around("within(com.revature.controllers.*) && !within(com.revature.controllers.LoginController) && !execution(* com.revature.controllers.TrainerController.createTrainer(..))")
	public Object tokenAuth(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String auth = request.getHeader("Authentication");
		String invalid = "Invalid Authentication";
		if(auth == null)
			return new ResponseEntity<String>(invalid + ": no token.", null, HttpStatus.FORBIDDEN);
		String[] tokens = auth.split(":");
		if(tokens.length != 2 || !tokens[0].matches("\\d+"))
			return new ResponseEntity<String>(invalid + ": invalid token.", null, HttpStatus.FORBIDDEN);
		Trainer t = td.getTrainerById(Integer.parseInt(tokens[0]));
		if(t == null || !t.getLogin().equals(tokens[1]))
			return new ResponseEntity<String>(invalid + ": incorrect token.", null, HttpStatus.FORBIDDEN);
		return pjp.proceed();
	}
	
	// [trainer_id, login, pass, email, team_id, is_lead]
	@Around("@annotation(com.revature.validAnnot.ValidTrainer)")
	public Object validTrainer(ProceedingJoinPoint pjp) throws Throwable {
		String[] t = (String[])pjp.getArgs()[0];
		String failed = "Invalid Trainer";
		if(t == null || t.length != 6 || t[0] == null || t[4] == null) {
			return new ResponseEntity<String>(failed, null, HttpStatus.BAD_REQUEST);
		}
		if(t[0].length() > 9 || (t[1] != null && t[1].length() > 255) || (t[2] != null && t[2].length() > 255) || 
				(t[3] != null && t[3].length() > 255) || t[4].length() > 9 || (t[5] != null && t[5].length() > 1))
			return new ResponseEntity<String>(failed, null, HttpStatus.BAD_REQUEST);
		if(!t[0].matches("\\d+") || !t[4].matches("\\d+") || (t[3] != null && !t[3].matches("[^@]+@[^\\.]+\\..+")))
			return new ResponseEntity<String>(failed, null, HttpStatus.BAD_REQUEST);
		return pjp.proceed();
	}
	
	// [pk_id, pkmn_id, nickname, trainer_id, move1, move2, move3, move4]
	@Around("@annotation(com.revature.validAnnot.ValidPokemon)")
	public Object validPokemon(ProceedingJoinPoint pjp) throws Throwable {
		String[] pkmn = (String[])pjp.getArgs()[0];
		int[] max = {9, 9, 255, 9, 255, 255, 255, 255};
		String failed = "Invalid Pokemon";
		if(pkmn == null ||pkmn.length != 8 || pkmn[0] == null || pkmn[1] == null || pkmn[3] == null)
			return new ResponseEntity<String>(failed, null, HttpStatus.BAD_REQUEST);
		for(int i=0; i<8; i++) {
			if(pkmn[i].length() > max[i])
				return new ResponseEntity<String>(failed, null, HttpStatus.BAD_REQUEST);
		}
		if(!pkmn[0].matches("\\d+") || !pkmn[1].matches("\\d+") || !pkmn[3].matches("\\d+") || (pkmn[5] != null && !pkmn[5].matches("\\d+")))
			return new ResponseEntity<String>(failed, null, HttpStatus.BAD_REQUEST);
		return pjp.proceed();
	}
	
	// [team_id, teamName]
	@Around("@annotation(com.revature.validAnnot.ValidTeam)")
	public Object validTeam(ProceedingJoinPoint pjp) throws Throwable {
		String[] tm = (String[])pjp.getArgs()[0];
		String failed = "Invalid Team";
		if(tm == null || tm.length != 2 || tm[0] == null || tm[1] == null)
			return new ResponseEntity<String>(failed, null, HttpStatus.BAD_REQUEST);
		if(tm[0].length() > 9 || tm[1].length() > 255)
			return new ResponseEntity<String>(failed, null, HttpStatus.BAD_REQUEST);
		if(!tm[0].matches("\\d+"))
			return new ResponseEntity<String>(failed, null, HttpStatus.BAD_REQUEST);
		return pjp.proceed();
	}
	
	// [trade_id, pkmn1_id, pkmn2_id]
	@Around("@annotation(com.revature.validAnnot.ValidTrade)")
	public Object validTrade(ProceedingJoinPoint pjp) throws Throwable {
		String[] tds = (String[])pjp.getArgs()[0];
		String failed = "Invalid Trade";
		if(tds == null || tds.length != 3 || tds[0] == null || tds[1] == null || tds[2] == null)
			return new ResponseEntity<String>(failed, null, HttpStatus.BAD_REQUEST);
		if(tds[0].length() > 9 || tds[1].length() > 9 || tds[2].length() > 9)
			return new ResponseEntity<String>(failed, null, HttpStatus.BAD_REQUEST);
		if(!tds[0].matches("\\d+") || !tds[1].matches("\\d+") || !tds[2].matches("\\d+"))
			return new ResponseEntity<String>(failed, null, HttpStatus.BAD_REQUEST);
		return pjp.proceed();
	}
}
