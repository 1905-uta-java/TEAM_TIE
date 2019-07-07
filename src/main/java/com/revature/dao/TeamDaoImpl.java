package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Team;
import com.revature.util.HibernateUtil;

public class TeamDaoImpl implements TeamDao {

	@Override
	public List<Team> getTeams() {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		List<Team> teams = s.createQuery("from Team", Team.class).list();
		s.close();
		return teams;
	}

	@Override
	public Team getTeamById(int id) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Team t = s.get(Team.class, id);
		s.close();
		return t;
	}

	@Override
	public int createTeam(Team t) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Transaction trs = s.beginTransaction();
		int scs = (int) s.save(t);
		trs.commit();
		s.close();
		return scs;
	}

	@Override
	public void updateTeam(Team t) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Transaction trs = s.beginTransaction();
		s.update(t);
		trs.commit();
		s.close();
	}

	@Override
	public void deleteTeam(int id) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Transaction trs = s.beginTransaction();
		s.delete(new Team(id));
		trs.commit();
		s.close();
	}

}
