package com.revature.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Pokemon;
import com.revature.models.Trade;
import com.revature.models.Trainer;

@Repository
public class TradeDaoImpl implements TradeDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	@Transactional
	public List<Trade> getTrades() {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		List<Trade> trades = s.createQuery("from Trade", Trade.class).list();
		return trades;
	}

	@Override
	@Transactional
	public Trade getTradeById(int id) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		Trade t = s.get(Trade.class, id);
		return t;
	}

	@Override
	@Transactional
	public List<Trade> getTradesBySender(Trainer t) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Trade> cq = cb.createQuery(Trade.class);
		Root<Trade> rt = cq.from(Trade.class);
		cq.select(rt);
		cq.where(cb.equal(rt.<Pokemon>get("pkmn_1").<Trainer>get("trainer_id").get("id"), t.getId()));
		Query<Trade> q = s.createQuery(cq);
		List<Trade> trades = q.list();
		return trades;
	}

	@Override
	@Transactional
	public List<Trade> getTradesByReciever(Trainer t) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Trade> cq = cb.createQuery(Trade.class);
		Root<Trade> rt = cq.from(Trade.class);
		cq.select(rt);
		cq.where(cb.equal(rt.<Pokemon>get("pkmn_2").<Trainer>get("trainer_id").get("id"), t.getId()));
		Query<Trade> q = s.createQuery(cq);
		List<Trade> trades = q.list();
		return trades;
	}

	@Override
	@Transactional
	public int createTrade(Trade t) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		Transaction trs = s.beginTransaction();
		int scs = (int) s.save(t);
		trs.commit();
		return scs;
	}

	@Override
	@Transactional
	public void updateTrade(Trade t) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		Transaction trs = s.beginTransaction();
		s.update(t);
		trs.commit();
	}

	@Override
	@Transactional
	public void deleteTrade(int id) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		Transaction trs = s.beginTransaction();
		s.delete(new Trade(id));
		trs.commit();
	}

}
