package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	
}
