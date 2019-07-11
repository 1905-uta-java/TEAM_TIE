package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Trainer;

@Repository
public class TrainerDaoImpl implements TrainerDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	@Transactional
	public List<Trainer> getTrainers() {
		Session s = sf.getCurrentSession();
		return s.createQuery("from Trainer", Trainer.class).list();
	}

	@Override
	@Transactional
	public Trainer getTrainerById(int id) {
		Session s = sf.getCurrentSession();
		return s.get(Trainer.class, id);
	}
	
	@Override
	@Transactional
	public List<Trainer> getTrainersByTeam(int id){
		Session s = sf.getCurrentSession();
		return s.createQuery("from Trainer where TEAM_ID_TEAM_ID = :id", Trainer.class).setParameter("id", id).list();
	}
	
	@Override
	@Transactional
	public Trainer getTrainerByLogin(String login) {
		Session s = sf.getCurrentSession();
		return s.createQuery("from Trainer where LOGIN = :login", Trainer.class).setParameter("login", login).getSingleResult();
	}

	@Override
	@Transactional
	public int createTrainer(Trainer t) {
		Session s = sf.getCurrentSession();
		if(t.getTeam_id() != null)
			s.update(t.getTeam_id());
		return (int) s.save(t);
	}

	@Override
	@Transactional
	public void editTrainer(Trainer t) {
		Session s = sf.getCurrentSession();
		if(t.getTeam_id() != null)
			s.update(t.getTeam_id());
		s.update(t);
	}

	@Override
	@Transactional
	public void deleteTrainer(int id) {
		Session s = sf.getCurrentSession();
		s.delete(new Trainer(id));
	}

}
