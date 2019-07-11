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

import com.revature.dao.TeamDao;
import com.revature.dao.TrainerDao;
import com.revature.models.Team;
import com.revature.models.Trainer;
import com.revature.validAnnot.ValidTrainer;

@Controller
@CrossOrigin
@RequestMapping("/Trainers")
@ComponentScan("com.revature")
public class TrainerController {
	
	@Autowired
	TrainerDao tdi;
	@Autowired
	TeamDao tmdi;
	
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
		if(t.getTeam_id() != null) {
			List<Trainer> tm = tdi.getTrainersByTeam(t.getTeam_id().getId());
			return new ResponseEntity<>(tm, null, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, null, HttpStatus.OK);
	}
	
	//[trainer_id, login, pass, email, team_id, is_lead]
	// Both trainer_id, team_id, is_lead must be valid integers for validation, but are unused in creation
	@PostMapping(value="/new")
	@ResponseBody
	@ValidTrainer
	public ResponseEntity<Trainer> createTrainer(@RequestBody String[] vals) {
		Trainer t = new Trainer(vals[1], vals[2], vals[3]);
		tdi.createTrainer(t);
		return new ResponseEntity<>(t, null, HttpStatus.OK);
	}
	
	//[trainer_id, login, pass, email, team_id, is_lead]
	// Only trainer_id, team_id is required, all others will be unchanged if not sent.
	@PutMapping(value="/update")
	@ValidTrainer
	public ResponseEntity<String> updateTrainer(@RequestBody String[] vals) {
		Trainer t = tdi.getTrainerById(Integer.parseInt(vals[0]));
		if(t == null)
			return new ResponseEntity<>("Trainer does not exist.", null, HttpStatus.BAD_REQUEST);
		if(vals[1] != null)
			t.setLogin(vals[1]);
		if(vals[2] != null)
			t.setPass(vals[2]);
		if(vals[3] != null)
			t.setEmail(vals[3]);
		if(Integer.parseInt(vals[4]) != t.getTeam_id().getId()) {
			Team tm = tmdi.getTeamById(Integer.parseInt(vals[4]));
			t.setTeam_id(tm);
		}
		if(vals[5] != null)
			t.setIs_lead(Integer.parseInt(vals[5]));
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
