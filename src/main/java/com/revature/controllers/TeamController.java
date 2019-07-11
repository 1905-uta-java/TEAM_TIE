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
import com.revature.models.Team;
import com.revature.validAnnot.ValidTeam;

@Controller
@CrossOrigin
@RequestMapping("/Teams")
@ComponentScan("com.revature")
public class TeamController {
	@Autowired
	TeamDao tdi;
	
	@GetMapping(value={"/", "/list"})
	@ResponseBody
	public ResponseEntity<List<Team>> getTeams(){
		List<Team> lt = tdi.getTeams();
		return new ResponseEntity<>(lt, null, HttpStatus.OK);
	}
	
	@PostMapping(value="/id")
	@ResponseBody
	public ResponseEntity<Team> getTeamById(@RequestBody int id) {
		Team t = tdi.getTeamById(id);
		return new ResponseEntity<>(t, null, HttpStatus.OK);
	}
	
	//[team_id, teamName]
	@PostMapping(value="/new")
	@ResponseBody
	@ValidTeam
	public ResponseEntity<Team> newTeam(@RequestBody String[] tm) {
		Team t = new Team();
		t.setTeamName(tm[1]);
		tdi.createTeam(t);
		return new ResponseEntity<>(t, null, HttpStatus.OK);
	}
	
	@PutMapping(value="/update")
	@ValidTeam
	public ResponseEntity<String> editTeam(@RequestBody String[] tm) {
		Team t = tdi.getTeamById(Integer.parseInt(tm[0]));
		if(t == null)
			return new ResponseEntity<>("Team does not exist", null, HttpStatus.BAD_REQUEST);
		t.setTeamName(tm[1]);
		tdi.updateTeam(t);
		return new ResponseEntity<>(null, null, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete")
	public ResponseEntity<String> deleteTeam(@RequestBody int id) {
		tdi.deleteTeam(id);
		return new ResponseEntity<>(null, null, HttpStatus.OK);
	}
}
