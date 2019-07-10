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

import com.revature.dao.TeamDao;
import com.revature.models.Team;

@Controller
@CrossOrigin
@RequestMapping("/Teams")
@ComponentScan("com.revature")
public class TeamController {
	@Autowired
	TeamDao tdi;
	
	@RequestMapping(value={"/", "/list"}, method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Team>> getTeams(){
		List<Team> lt = tdi.getTeams();
		return new ResponseEntity<List<Team>>(lt, null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/id", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Team> getTeamById(@RequestBody int id) {
		Team t = tdi.getTeamById(id);
		return new ResponseEntity<Team>(t, null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Team> newTeam(@RequestBody Team t) {
		tdi.createTeam(t);
		return new ResponseEntity<Team>(t, null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public ResponseEntity<String> editTeam(@RequestBody Team t) {
		tdi.updateTeam(t);
		return new ResponseEntity<String>("Complete", null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteTeam(@RequestBody int id) {
		tdi.deleteTeam(id);
		return new ResponseEntity<String>("Complete", null, HttpStatus.OK);
	}
}
