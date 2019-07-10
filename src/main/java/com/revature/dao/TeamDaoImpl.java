package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Team;

@Repository
public class TeamDaoImpl implements TeamDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	@Transactional
	public List<Team> getTeams() {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		List<Team> teams = s.createQuery("from Team", Team.class).list();
		return teams;
	}

	@Override
	@Transactional
	public Team getTeamById(int id) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		Team t = s.get(Team.class, id);
		return t;
	}

	@Override
	@Transactional
	public int createTeam(Team t) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		int scs = (int) s.save(t);
		return scs;
	}

	@Override
	@Transactional
	public void updateTeam(Team t) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		s.update(t);
	}

	@Override
	@Transactional
	public void deleteTeam(int id) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		s.delete(new Team(id));
	}

}
