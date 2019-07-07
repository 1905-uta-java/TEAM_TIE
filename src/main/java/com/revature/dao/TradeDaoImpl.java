package com.revature.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.models.Pokemon;
import com.revature.models.Trade;
import com.revature.models.Trainer;
import com.revature.util.HibernateUtil;

public class TradeDaoImpl implements TradeDao {

	@Override
	public List<Trade> getTrades() {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		List<Trade> trades = s.createQuery("from Trade", Trade.class).list();
		s.close();
		return trades;
	}

	@Override
	public Trade getTradeById(int id) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Trade t = s.get(Trade.class, id);
		s.close();
		return t;
	}

	@Override
	public List<Trade> getTradesBySender(Trainer t) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Trade> cq = cb.createQuery(Trade.class);
		Root<Trade> rt = cq.from(Trade.class);
		cq.select(rt);
		cq.where(cb.equal(rt.<Pokemon>get("pkmn_1").<Trainer>get("trainer_id").get("id"), t.getId()));
		Query<Trade> q = s.createQuery(cq);
		List<Trade> trades = q.list();
		s.close();
		return trades;
	}

	@Override
	public List<Trade> getTradesByReciever(Trainer t) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Trade> cq = cb.createQuery(Trade.class);
		Root<Trade> rt = cq.from(Trade.class);
		cq.select(rt);
		cq.where(cb.equal(rt.<Pokemon>get("pkmn_2").<Trainer>get("trainer_id").get("id"), t.getId()));
		Query<Trade> q = s.createQuery(cq);
		List<Trade> trades = q.list();
		s.close();
		return trades;
	}

	@Override
	public int createTrade(Trade t) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Transaction trs = s.beginTransaction();
		int scs = (int) s.save(t);
		trs.commit();
		s.close();
		return scs;
	}

	@Override
	public void updateTrade(Trade t) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Transaction trs = s.beginTransaction();
		s.update(t);
		trs.commit();
		s.close();
	}

	@Override
	public void deleteTrade(int id) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Transaction trs = s.beginTransaction();
		s.delete(new Trade(id));
		trs.commit();
		s.close();
	}

}
