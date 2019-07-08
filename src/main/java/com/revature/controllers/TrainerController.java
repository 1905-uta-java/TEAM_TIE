package com.revature.controllers;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.models.Trainer;

@Controller
@RequestMapping("/Trainers")
@ComponentScan("com.revature")
public class TrainerController {
	
	
	@RequestMapping(value= {"/", "/list"}, method=RequestMethod.GET)
	public List<Trainer> listTrainers() {
		
		return null;
	}
	
	@RequestMapping(value="/tst")
	public String testConnection() {
		return "Worked.";
	}
}
