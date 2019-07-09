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
	public List<Trade> listTrades(){
		List<Trade> lt = tdi.getTrades();
		return lt;
	}
	
	@RequestMapping(value="/id", method=RequestMethod.POST)
	@ResponseBody
	public Trade listTradesById(@RequestBody int id) {
		return tdi.getTradeById(id);
	}
	
	@RequestMapping(value="/incoming", method=RequestMethod.POST)
	@ResponseBody
	public List<Trade> incommingTrades(@RequestBody int id){
		List<Trade> lt = tdi.getTradesByReciever(id);
		return lt;
	}
	
	@RequestMapping(value="/outgoing", method=RequestMethod.POST)
	@ResponseBody
	public List<Trade> outgoingTrades(@RequestBody int id){
		List<Trade> lt = tdi.getTradesBySender(id);
		return lt;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	@ResponseBody
	public Trade createTrade(@RequestBody Trade t) {
		tdi.createTrade(t);
		return t;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public void updateTrade(@RequestBody Trade t) {
		tdi.updateTrade(t);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void deleteTrade(@RequestBody int id) {
		tdi.deleteTrade(id);
	}
}
