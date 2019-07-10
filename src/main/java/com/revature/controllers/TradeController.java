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

import com.revature.dao.TradeDao;
import com.revature.models.Trade;

@Controller
@CrossOrigin
@RequestMapping("/Trades")
@ComponentScan("com.revature")
public class TradeController {
	@Autowired
	TradeDao tdi;
	
	@RequestMapping(value= {"/", "/list"}, method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Trade>> listTrades(){
		List<Trade> lt = tdi.getTrades();
		return new ResponseEntity<List<Trade>>(lt, null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/id", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Trade> listTradesById(@RequestBody int id) {
		return new ResponseEntity<Trade>(tdi.getTradeById(id), null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/incoming", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<Trade>> incommingTrades(@RequestBody int id){
		List<Trade> lt = tdi.getTradesByReciever(id);
		return new ResponseEntity<List<Trade>>(lt, null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/outgoing", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<Trade>> outgoingTrades(@RequestBody int id){
		List<Trade> lt = tdi.getTradesBySender(id);
		return new ResponseEntity<List<Trade>>(lt, null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Trade> createTrade(@RequestBody Trade t) {
		tdi.createTrade(t);
		return new ResponseEntity<Trade>(t, null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/accept", method=RequestMethod.POST)
	public ResponseEntity<String> acceptTrade(@RequestBody Trade t) {
		tdi.acceptTrade(t);
		return new ResponseEntity<String>("Complete", null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public ResponseEntity<String> updateTrade(@RequestBody Trade t) {
		tdi.updateTrade(t);
		return new ResponseEntity<String>("Complete", null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteTrade(@RequestBody int id) {
		tdi.deleteTrade(id);
		return new ResponseEntity<String>("Complete", null, HttpStatus.OK);
	}
}
