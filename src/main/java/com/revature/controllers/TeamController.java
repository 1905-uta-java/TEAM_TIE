package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
	public List<Team> getTeams(){
		List<Team> lt = tdi.getTeams();
		return lt;
	}
	
	@RequestMapping(value="/id", method=RequestMethod.POST)
	@ResponseBody
	public Team getTeamById(@RequestBody int id) {
		Team t = tdi.getTeamById(id);
		return t;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	@ResponseBody
	public Team newTeam(@RequestBody Team t) {
		tdi.createTeam(t);
		return t;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public void editTeam(@RequestBody Team t) {
		tdi.updateTeam(t);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void deleteTeam(@RequestBody int id) {
		tdi.deleteTeam(id);
	}
}
