package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.dao.TrainerDao;
import com.revature.models.Trainer;

@Controller
@RequestMapping("/Trainers")
@ComponentScan("com.revature")
public class TrainerController {
	
	@Autowired
	TrainerDao tdi;
	
	@RequestMapping(value= {"/", "/list"}, method=RequestMethod.GET)
	@ResponseBody
	public List<Trainer> listTrainers() {
		List<Trainer> lt = tdi.getTrainers();
		return lt;
	}
	
	@RequestMapping(value="/team", method=RequestMethod.POST)
	@ResponseBody
	public List<Trainer> listTrainersByTeam(@RequestBody int team_id) {
		List<Trainer> lt = tdi.getTrainersByTeam(team_id);
		return lt;
	}
	
	@RequestMapping(value="/tst", method=RequestMethod.GET)
	@ResponseBody
	public String testConnection() {
		return "Worked.";
	}
}
