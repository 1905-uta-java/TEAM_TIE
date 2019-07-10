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

import com.revature.dao.PokemonDao;
import com.revature.models.Pokemon;

@Controller
@CrossOrigin
@RequestMapping("/Pokemon")
@ComponentScan("com.revature")
public class PokemonController {
	@Autowired
	PokemonDao pdi;
	
	@RequestMapping(value= {"/", "/list"}, method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Pokemon>> getPokemon(){
		List<Pokemon> lp = pdi.getPokemon();
		return new ResponseEntity<List<Pokemon>>(lp, null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/id", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Pokemon> getPokemonById(@RequestBody int id) {
		return new ResponseEntity<Pokemon>(pdi.getPokemonById(id), null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon p) {
		pdi.createPokemon(p);
		return new ResponseEntity<Pokemon>(p, null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public ResponseEntity<String> updatePokemon(@RequestBody Pokemon p) {
		pdi.updatePokemon(p);
		return new ResponseEntity<String>("Complete", null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public ResponseEntity<String> deletePokemon(@RequestBody int id) {
		pdi.deletePokemon(id);
		return new ResponseEntity<String>("Complete", null, HttpStatus.OK);
	}
}
