package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		Session s = sf.getCurrentSession();
		return s.createQuery("from Team", Team.class).list();
	}

	@Override
	@Transactional
	public Team getTeamById(int id) {
		Session s = sf.getCurrentSession();
		return s.get(Team.class, id);
	}

	@Override
	@Transactional
	public int createTeam(Team t) {
		Session s = sf.getCurrentSession();
		return (int) s.save(t);
	}

	@Override
	@Transactional
	public void updateTeam(Team t) {
		Session s = sf.getCurrentSession();
		s.update(t);
	}

	@Override
	@Transactional
	public void deleteTeam(int id) {
		Session s = sf.getCurrentSession();
		Team t = s.get(Team.class, id);
		if(t != null)
			s.delete(t);
	}

}
