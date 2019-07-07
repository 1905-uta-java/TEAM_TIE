package com.revature.dao;

import java.util.List;

import com.revature.models.Trade;
import com.revature.models.Trainer;

public interface TradeDao {
	public List<Trade> getTrades();
	public Trade getTradeById(int id);
	public List<Trade> getTradesBySender(Trainer t);
	public List<Trade> getTradesByReciever(Trainer t);
	public int createTrade(Trade t);
	public void updateTrade(Trade t);
	public void deleteTrade(int id);
}
