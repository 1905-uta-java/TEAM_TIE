package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping(value= {"/", "/list"})
	@ResponseBody
	public ResponseEntity<List<Trainer>> listTrainers() {
		List<Trainer> lt = tdi.getTrainers();
		return new ResponseEntity<>(lt, null, HttpStatus.OK);
	}
	
	@PostMapping(value="/id")
	@ResponseBody
	public ResponseEntity<Trainer> getTrainer(@RequestBody int id) {
		return new ResponseEntity<>(tdi.getTrainerById(id), null, HttpStatus.OK);
	}
	
	@PostMapping(value="/username")
	@ResponseBody
	public ResponseEntity<Trainer> getTrainerByLogin(@RequestBody String login) {
		return new ResponseEntity<>(tdi.getTrainerByLogin(login), null, HttpStatus.OK);
	}
	
	@PostMapping(value="/team")
	@ResponseBody
	public ResponseEntity<List<Trainer>> listTrainersByTeam(@RequestBody int team_id) {
		List<Trainer> lt = tdi.getTrainersByTeam(team_id);
		return new ResponseEntity<>(lt, null, HttpStatus.OK);
	}
	
	@PostMapping(value="/teammates")
	@ResponseBody
	public ResponseEntity<List<Trainer>> listTrainerTeammates(@RequestBody int id){
		Trainer t = tdi.getTrainerById(id);
		List<Trainer> tm = tdi.getTrainersByTeam(t.getTeam_id().getId());
		return new ResponseEntity<>(tm, null, HttpStatus.OK);
	}
	
	@PostMapping(value="/new")
	@ResponseBody
	public ResponseEntity<Trainer> createTrainer(@RequestBody String[] vals) {
		Trainer t = new Trainer(vals[0], vals[1], vals[2]);
		tdi.createTrainer(t);
		return new ResponseEntity<>(t, null, HttpStatus.OK);
	}
	
	@PutMapping(value="/update")
	@ValidTrainer
	public ResponseEntity<String> updateTrainer(@RequestBody Trainer t) {
		tdi.editTrainer(t);
		return new ResponseEntity<>("Updated", null, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete")
	public ResponseEntity<String> deleteTrainer(@RequestBody int id) {
		tdi.deleteTrainer(id);
		return new ResponseEntity<>("Deleted", null, HttpStatus.OK);
	}
	
	@GetMapping(value="/tst")
	@ResponseBody
	public ResponseEntity<String> testConnection() {
		return new ResponseEntity<>("Worked", null, HttpStatus.OK);
	}
}
