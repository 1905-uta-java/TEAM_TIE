package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Pokemon;
import com.revature.util.HibernateUtil;

public class PokemonDaoImpl implements PokemonDao {

	@Override
	public List<Pokemon> getPokemon() {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		List<Pokemon> pkmn = s.createQuery("from Pokemon", Pokemon.class).list();
		s.close();
		return pkmn;
	}

	@Override
	public Pokemon getPokemonById(int id) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Pokemon p = s.get(Pokemon.class, id);
		s.close();
		return p;
	}

	@Override
	public int createPokemon(Pokemon p) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Transaction trs = s.beginTransaction();
		int scs = (int) s.save(p);
		trs.commit();
		s.close();
		return scs;
	}

	@Override
	public void updatePokemon(Pokemon p) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Transaction trs = s.beginTransaction();
		s.update(p);
		trs.commit();
		s.close();
	}

	@Override
	public void deletePokemon(int id) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Transaction trs = s.beginTransaction();
		s.delete(new Pokemon(id));
		trs.commit();
		s.close();
	}

}
