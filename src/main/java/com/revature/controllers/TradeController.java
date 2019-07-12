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
import com.revature.dao.TradeDao;
import com.revature.models.Pokemon;
import com.revature.models.Trade;
import com.revature.validAnnot.ValidTrade;

@Controller
@CrossOrigin
@RequestMapping("/Trades")
@ComponentScan("com.revature")
public class TradeController {
	@Autowired
	TradeDao tdi;
	@Autowired
	PokemonDao pdi;
	
	@GetMapping(value= {"/", "/list"})
	@ResponseBody
	public ResponseEntity<List<Trade>> listTrades(){
		List<Trade> lt = tdi.getTrades();
		return new ResponseEntity<>(lt, null, HttpStatus.OK);
	}
	
	@PostMapping(value="/id")
	@ResponseBody
	public ResponseEntity<Trade> listTradesById(@RequestBody int id) {
		return new ResponseEntity<>(tdi.getTradeById(id), null, HttpStatus.OK);
	}
	
	@PostMapping(value="/incoming")
	@ResponseBody
	public ResponseEntity<List<Trade>> incommingTrades(@RequestBody int id){
		List<Trade> lt = tdi.getTradesByReciever(id);
		return new ResponseEntity<>(lt, null, HttpStatus.OK);
	}
	
	@PostMapping(value="/outgoing")
	@ResponseBody
	public ResponseEntity<List<Trade>> outgoingTrades(@RequestBody int id){
		List<Trade> lt = tdi.getTradesBySender(id);
		return new ResponseEntity<>(lt, null, HttpStatus.OK);
	}
	
	//[trade_id, pkmn1_id, pkmn2_id]
	@PostMapping(value="/new")
	@ResponseBody
	@ValidTrade
	public ResponseEntity<Trade> createTrade(@RequestBody String[] tsa) {
		Trade t = new Trade();
		Pokemon p1 = pdi.getPokemonById(Integer.parseInt(tsa[1]));
		Pokemon p2 = pdi.getPokemonById(Integer.parseInt(tsa[2]));
		if(p1 == null || p2 == null)
			return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
		t.setPkmn_1(p1);
		t.setPkmn_2(p2);
		tdi.createTrade(t);
		return new ResponseEntity<>(t, null, HttpStatus.OK);
	}
	
	@PostMapping(value="/accept")
	public ResponseEntity<String> acceptTrade(@RequestBody int id) {
		Trade t = tdi.getTradeById(id);
		tdi.acceptTrade(t);
		return new ResponseEntity<>(null, null, HttpStatus.OK);
	}
	
	// [trade_id, pkmn1_id, pkmn2_id]
	@PutMapping(value="/update")
	@ValidTrade
	public ResponseEntity<String> updateTrade(@RequestBody String[] tsa) {
		Trade t = tdi.getTradeById(Integer.parseInt(tsa[0]));
		Pokemon p1 = pdi.getPokemonById(Integer.parseInt(tsa[1]));
		Pokemon p2 = pdi.getPokemonById(Integer.parseInt(tsa[2]));
		if(p1 == null || p2 == null || t == null)
			return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
		t.setPkmn_1(p1);
		t.setPkmn_2(p2);
		tdi.updateTrade(t);
		return new ResponseEntity<>(null, null, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete")
	public ResponseEntity<String> deleteTrade(@RequestBody int id) {
		tdi.deleteTrade(id);
		return new ResponseEntity<>(null, null, HttpStatus.OK);
	}
}
