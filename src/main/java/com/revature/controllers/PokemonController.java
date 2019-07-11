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
import com.revature.dao.TrainerDao;
import com.revature.models.Pokemon;
import com.revature.models.Trainer;
import com.revature.validAnnot.ValidPokemon;

@Controller
@CrossOrigin
@RequestMapping("/Pokemon")
@ComponentScan("com.revature")
public class PokemonController {
	@Autowired
	PokemonDao pdi;
	@Autowired
	TrainerDao tdi;
	
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
	
	// [pk_id, pkmn_id, nickname, trainer_id, move1, move2, move3, move4]
	// pk_id is unused, but must be a valid integer for validation
	@PostMapping(value="/new")
	@ResponseBody
	@ValidPokemon
	public ResponseEntity<Pokemon> createPokemon(@RequestBody String[] pkmn) {
		Pokemon p = new Pokemon();
		p.setPkmn_id(Integer.parseInt(pkmn[1]));
		p.setNickname(pkmn[2]);
		Trainer t = tdi.getTrainerById(Integer.parseInt(pkmn[3]));
		p.setTrainer_id(t);
		p.setMove_one(pkmn[4]);
		p.setMove_two(pkmn[5]);
		p.setMove_three(pkmn[6]);
		p.setMove_four(pkmn[7]);
		p.genCreated();
		pdi.createPokemon(p);
		return new ResponseEntity<>(p, null, HttpStatus.OK);
	}
	
	// [pk_id, pkmn_id, nickname, trainer_id, move1, move2, move3, move4]
	@PutMapping(value="/update")
	@ValidPokemon
	public ResponseEntity<String> updatePokemon(@RequestBody String[] pkmn) {
		Pokemon p = pdi.getPokemonById(Integer.parseInt(pkmn[0]));
		p.setPkmn_id(Integer.parseInt(pkmn[1]));
		p.setNickname(pkmn[2]);
		if(p.getTrainer_id().getId() != Integer.parseInt(pkmn[3])) {
			Trainer t = tdi.getTrainerById(Integer.parseInt(pkmn[3]));
			p.setTrainer_id(t);
		}
		p.setMove_one(pkmn[4]);
		p.setMove_two(pkmn[5]);
		p.setMove_three(pkmn[6]);
		p.setMove_four(pkmn[7]);
		pdi.updatePokemon(p);
		return new ResponseEntity<>("Updated", null, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete")
	public ResponseEntity<String> deletePokemon(@RequestBody int id) {
		pdi.deletePokemon(id);
		return new ResponseEntity<>("Complete", null, HttpStatus.OK);
	}
}
