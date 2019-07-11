package com.revature.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		Session s = sf.getCurrentSession();
		return s.createQuery("from Trade", Trade.class).list();
	}

	@Override
	@Transactional
	public Trade getTradeById(int id) {
		Session s = sf.getCurrentSession();
		return s.get(Trade.class, id);
	}

	@Override
	@Transactional
	public List<Trade> getTradesBySender(int id) {
		Session s = sf.getCurrentSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Trade> cq = cb.createQuery(Trade.class);
		Root<Trade> rt = cq.from(Trade.class);
		cq.select(rt);
		cq.where(cb.equal(rt.<Pokemon>get("pkmn_1").<Trainer>get("trainer_id").get("id"), id));
		Query<Trade> q = s.createQuery(cq);
		return q.list();
	}

	@Override
	@Transactional
	public List<Trade> getTradesByReciever(int id) {
		Session s = sf.getCurrentSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Trade> cq = cb.createQuery(Trade.class);
		Root<Trade> rt = cq.from(Trade.class);
		cq.select(rt);
		cq.where(cb.equal(rt.<Pokemon>get("pkmn_2").<Trainer>get("trainer_id").get("id"), id));
		Query<Trade> q = s.createQuery(cq);
		return q.list();
	}

	@Override
	@Transactional
	public int createTrade(Trade t) {
		Session s = sf.getCurrentSession();
		if(t.getPkmn_1().getTrainer_id().getTeam_id() != null)
			s.update(t.getPkmn_1().getTrainer_id().getTeam_id());
		if(t.getPkmn_2().getTrainer_id().getTeam_id() != null)
			s.update(t.getPkmn_2().getTrainer_id().getTeam_id());
		s.update(t.getPkmn_1().getTrainer_id());
		s.update(t.getPkmn_2().getTrainer_id());
		s.update(t.getPkmn_1());
		s.update(t.getPkmn_2());
		return (int) s.save(t);
	}
	
	@Override
	@Transactional
	public void acceptTrade(Trade t) {
		Session s = sf.getCurrentSession();
		Pokemon pkmn1 = t.getPkmn_1();
		Pokemon pkmn2 = t.getPkmn_2();
		Trainer t1 = pkmn1.getTrainer_id();
		pkmn1.setTrainer_id(pkmn2.getTrainer_id());
		pkmn2.setTrainer_id(t1);
		if(pkmn1.getTrainer_id().getTeam_id() != null)
			s.update(pkmn1.getTrainer_id().getTeam_id());
		if(pkmn2.getTrainer_id().getTeam_id() != null)
			s.update(pkmn2.getTrainer_id().getTeam_id());
		s.update(pkmn1.getTrainer_id());
		s.update(pkmn2.getTrainer_id());
		s.update(pkmn1);
		s.update(pkmn2);
		s.createQuery("delete from Trade where (PKMN_1_PK_ID = :id_one) OR (PKMN_2_PK_ID = "
				+ ":id_one) OR (PKMN_1_PK_ID = :id_two) OR (PKMN_2_PK_ID = :id_two)")
		.setParameter("id_one", pkmn1.getId()).setParameter("id_two", pkmn2.getId()).executeUpdate();
	}

	@Override
	@Transactional
	public void updateTrade(Trade t) {
		Session s = sf.getCurrentSession();
		if(t.getPkmn_1().getTrainer_id().getTeam_id() != null)
			s.update(t.getPkmn_1().getTrainer_id().getTeam_id());
		if(t.getPkmn_2().getTrainer_id().getTeam_id() != null)
			s.update(t.getPkmn_2().getTrainer_id().getTeam_id());
		s.update(t.getPkmn_1().getTrainer_id());
		s.update(t.getPkmn_2().getTrainer_id());
		s.update(t.getPkmn_1());
		s.update(t.getPkmn_2());
		s.update(t);
	}

	@Override
	@Transactional
	public void deleteTrade(int id) {
		Session s = sf.getCurrentSession();
		Trade t = s.get(Trade.class, id);
		if(t != null)
			s.delete(t);
	}

}
