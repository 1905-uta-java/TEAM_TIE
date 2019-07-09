package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.dao.TeamDao;

@Controller
@RequestMapping("/Teams")
@ComponentScan("com.revature")
public class TeamController {
	@Autowired
	TeamDao tdi;
}
