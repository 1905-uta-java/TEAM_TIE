package com.revature.dao;

import java.util.List;

import com.revature.models.Pokemon;

public interface PokemonDao {
	public List<Pokemon> getPokemon();
	public Pokemon getPokemonById(int id);
	public int createPokemon(Pokemon p);
	public void updatePokemon(Pokemon p);
	public void deletePokemon(int id);
}
