package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Pokemon;

@Repository
public class PokemonDaoImpl implements PokemonDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	@Transactional
	public List<Pokemon> getPokemon() {
		Session s = sf.getCurrentSession();
		return s.createQuery("from Pokemon", Pokemon.class).list();
	}

	@Override
	@Transactional
	public Pokemon getPokemonById(int id) {
		Session s = sf.getCurrentSession();
		return s.get(Pokemon.class, id);
	}

	@Override
	@Transactional
	public int createPokemon(Pokemon p) {
		Session s = sf.getCurrentSession();
		if(p.getTrainer_id().getTeam_id() != null)
			s.update(p.getTrainer_id().getTeam_id());
		s.update(p.getTrainer_id());
		return (int) s.save(p);
	}

	@Override
	@Transactional
	public void updatePokemon(Pokemon p) {
		Session s = sf.getCurrentSession();
		if(p.getTrainer_id().getTeam_id() != null)
			s.update(p.getTrainer_id().getTeam_id());
		s.update(p.getTrainer_id());
		s.update(p);
	}

	@Override
	@Transactional
	public void deletePokemon(int id) {
		Session s = sf.getCurrentSession();
		Pokemon pkmn = s.get(Pokemon.class, id);
		if(pkmn != null)
			s.delete(pkmn);
	}

}
