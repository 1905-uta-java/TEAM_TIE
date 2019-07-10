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
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		List<Trainer> trainers = s.createQuery("from Trainer", Trainer.class).list();
		return trainers;
	}

	@Override
	@Transactional
	public Trainer getTrainerById(int id) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		Trainer t = s.get(Trainer.class, id);
		return t;
	}
	
	@Override
	@Transactional
	public List<Trainer> getTrainersByTeam(int id){
		Session s = sf.getCurrentSession();
		List<Trainer> trainers = s.createQuery("from Trainer where TEAM_ID_TEAM_ID = :id", Trainer.class).setParameter("id", id).list();
		return trainers;
	}
	
	@Override
	@Transactional
	public Trainer getTrainerByLogin(String login) {
		Session s = sf.getCurrentSession();
		Trainer t = s.createQuery("from Trainer where LOGIN = :login", Trainer.class).setParameter("login", login).getSingleResult();
		return t;
	}

	@Override
	@Transactional
	public int createTrainer(Trainer t) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		int scs = (int) s.save(t);
		return scs;
	}

	@Override
	@Transactional
	public void editTrainer(Trainer t) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		s.update(t);
	}

	@Override
	@Transactional
	public void deleteTrainer(int id) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		s.delete(new Trainer(id));
	}

}
