package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public List<Trainer> listTrainers() {
		List<Trainer> lt = tdi.getTrainers();
		return lt;
	}
}
