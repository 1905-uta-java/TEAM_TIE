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
	public List<Pokemon> getPokemon(){
		List<Pokemon> lp = pdi.getPokemon();
		return lp;
	}
	
	@RequestMapping(value="/id", method=RequestMethod.POST)
	@ResponseBody
	public Pokemon getPokemonById(@RequestBody int id) {
		return pdi.getPokemonById(id);
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	@ResponseBody
	public Pokemon createPokemon(@RequestBody Pokemon p) {
		pdi.createPokemon(p);
		return p;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public void updatePokemon(@RequestBody Pokemon p) {
		pdi.updatePokemon(p);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void deletePokemon(@RequestBody int id) {
		pdi.deletePokemon(id);
	}
}
