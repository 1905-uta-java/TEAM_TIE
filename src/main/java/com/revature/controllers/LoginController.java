package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.dao.TrainerDao;
import com.revature.models.Trainer;

@Controller
@CrossOrigin
@RequestMapping("/Login")
@ComponentScan("com.revature")
public class LoginController {
	@Autowired
	TrainerDao tdi;
	
	@RequestMapping(value= "/", method=RequestMethod.POST)
	public ResponseEntity<String> listTrainers(@RequestBody String[] creds) {
		HttpHeaders responseHeaders = new HttpHeaders();
		if(creds.length != 2)
			return new ResponseEntity<String>("Failed to login.", responseHeaders, HttpStatus.FORBIDDEN);
		String login = creds[0];
		String pass = creds[1];
		Trainer lt = tdi.getTrainerByLogin(login);
		if(lt != null) {
			if(lt.getPass().equals(pass)) {
				responseHeaders.set("Access-Control-Expose-Headers","Authentication");
				responseHeaders.set("Access-Control-Allow-Headers", "*");
				responseHeaders.set("Authentication", lt.getId() + ":" + lt.getLogin());
				return new ResponseEntity<String>("Successfully logged in.", responseHeaders, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("Failed to log in.", responseHeaders, HttpStatus.FORBIDDEN);
			}
		}
		else {
			return new ResponseEntity<String>("Failed to login.", responseHeaders, HttpStatus.FORBIDDEN);
		}
	}
}
