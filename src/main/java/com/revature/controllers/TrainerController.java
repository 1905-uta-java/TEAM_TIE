package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.dao.TrainerDao;
import com.revature.models.Trainer;
import com.revature.validAnnot.ValidTrainer;

@Controller
@CrossOrigin
@RequestMapping("/Trainers")
@ComponentScan("com.revature")
public class TrainerController {
	
	@Autowired
	TrainerDao tdi;
	
	@RequestMapping(value= {"/", "/list"}, method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Trainer>> listTrainers() {
		List<Trainer> lt = tdi.getTrainers();
		return new ResponseEntity<List<Trainer>>(lt, null, HttpStatus.OK);
		//return lt;
	}
	
	@RequestMapping(value="/id", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Trainer> getTrainer(@RequestBody int id) {
		return new ResponseEntity<Trainer>(tdi.getTrainerById(id), null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/username", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Trainer> getTrainerByLogin(@RequestBody String login) {
		return new ResponseEntity<Trainer>(tdi.getTrainerByLogin(login), null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/team", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<Trainer>> listTrainersByTeam(@RequestBody int team_id) {
		List<Trainer> lt = tdi.getTrainersByTeam(team_id);
		return new ResponseEntity<List<Trainer>>(lt, null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Trainer> createTrainer(@RequestBody String[] vals) {
		Trainer t = new Trainer(vals[0], vals[1], vals[2]);
		tdi.createTrainer(t);
		return new ResponseEntity<Trainer>(t, null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@ValidTrainer
	public ResponseEntity<String> updateTrainer(@RequestBody Trainer t) {
		tdi.editTrainer(t);
		return new ResponseEntity<String>("Complete", null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteTrainer(@RequestBody int id) {
		tdi.deleteTrainer(id);
		return new ResponseEntity<String>("Complete", null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/tst", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> testConnection() {
		return new ResponseEntity<String>("Complete", null, HttpStatus.OK);
	}
}
