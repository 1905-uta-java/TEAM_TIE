package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Trainer;
import com.revature.util.HibernateUtil;

public class TrainerDaoImpl implements TrainerDao {

	@Override
	public List<Trainer> getTrainers() {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		List<Trainer> trainers = s.createQuery("from Trainer", Trainer.class).list();
		s.close();
		return trainers;
	}

	@Override
	public Trainer getTrainerById(int id) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Trainer t = s.get(Trainer.class, id);
		s.close();
		return t;
	}

	@Override
	public int createTrainer(Trainer t) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Transaction trs = s.beginTransaction();
		int scs = (int) s.save(t);
		trs.commit();
		s.close();
		return scs;
	}

	@Override
	public void editTrainer(Trainer t) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Transaction trs = s.beginTransaction();
		s.update(t);
		trs.commit();
		s.close();
	}

	@Override
	public void deleteTrainer(int id) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Transaction trs = s.beginTransaction();
		s.delete(new Trainer(id));
		trs.commit();
		s.close();
	}

}
