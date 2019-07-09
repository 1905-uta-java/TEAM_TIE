package com.revature.dao;

import java.util.List;

import com.revature.models.Trade;
import com.revature.models.Trainer;

public interface TradeDao {
	public List<Trade> getTrades();
	public Trade getTradeById(int id);
	public List<Trade> getTradesBySender(int id);
	public List<Trade> getTradesByReciever(int id);
	public int createTrade(Trade t);
	public void updateTrade(Trade t);
	public void deleteTrade(int id);
}
