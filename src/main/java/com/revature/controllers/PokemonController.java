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

import com.revature.dao.PokemonDao;
import com.revature.models.Pokemon;
import com.revature.validAnnot.ValidPokemon;

@Controller
@CrossOrigin
@RequestMapping("/Pokemon")
@ComponentScan("com.revature")
public class PokemonController {
	@Autowired
	PokemonDao pdi;
	
	@GetMapping(value= {"/", "/list"})
	@ResponseBody
	public ResponseEntity<List<Pokemon>> getPokemon(){
		List<Pokemon> lp = pdi.getPokemon();
		return new ResponseEntity<>(lp, null, HttpStatus.OK);
	}
	
	@PostMapping(value="/id")
	@ResponseBody
	public ResponseEntity<Pokemon> getPokemonById(@RequestBody int id) {
		return new ResponseEntity<>(pdi.getPokemonById(id), null, HttpStatus.OK);
	}
	
	@PostMapping(value="/new")
	@ResponseBody
	@ValidPokemon
	public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon p) {
		pdi.createPokemon(p);
		return new ResponseEntity<>(p, null, HttpStatus.OK);
	}
	
	@PutMapping(value="/update")
	@ValidPokemon
	public ResponseEntity<String> updatePokemon(@RequestBody Pokemon p) {
		pdi.updatePokemon(p);
		return new ResponseEntity<>("Updated", null, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete")
	public ResponseEntity<String> deletePokemon(@RequestBody int id) {
		pdi.deletePokemon(id);
		return new ResponseEntity<>("Complete", null, HttpStatus.OK);
	}
}
