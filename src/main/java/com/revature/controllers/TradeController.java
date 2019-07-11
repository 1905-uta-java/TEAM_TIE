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

import com.revature.dao.TradeDao;
import com.revature.models.Trade;

@Controller
@CrossOrigin
@RequestMapping("/Trades")
@ComponentScan("com.revature")
public class TradeController {
	@Autowired
	TradeDao tdi;
	
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
	
	@PostMapping(value="/new")
	@ResponseBody
	public ResponseEntity<Trade> createTrade(@RequestBody Trade t) {
		tdi.createTrade(t);
		return new ResponseEntity<>(t, null, HttpStatus.OK);
	}
	
	@PostMapping(value="/accept")
	public ResponseEntity<String> acceptTrade(@RequestBody Trade t) {
		tdi.acceptTrade(t);
		return new ResponseEntity<>("Accepted", null, HttpStatus.OK);
	}
	
	@PutMapping(value="/update")
	public ResponseEntity<String> updateTrade(@RequestBody Trade t) {
		tdi.updateTrade(t);
		return new ResponseEntity<>("Updated", null, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete")
	public ResponseEntity<String> deleteTrade(@RequestBody int id) {
		tdi.deleteTrade(id);
		return new ResponseEntity<>("Deleted", null, HttpStatus.OK);
	}
}
