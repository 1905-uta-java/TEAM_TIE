package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		List<Pokemon> pkmn = s.createQuery("from Pokemon", Pokemon.class).list();
		return pkmn;
	}

	@Override
	@Transactional
	public Pokemon getPokemonById(int id) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		Pokemon p = s.get(Pokemon.class, id);
		return p;
	}

	@Override
	@Transactional
	public int createPokemon(Pokemon p) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		int scs = (int) s.save(p);
		return scs;
	}

	@Override
	@Transactional
	public void updatePokemon(Pokemon p) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		s.update(p);
	}

	@Override
	@Transactional
	public void deletePokemon(int id) {
		// TODO Auto-generated method stub
		Session s = sf.getCurrentSession();
		s.delete(new Pokemon(id));
	}

}
