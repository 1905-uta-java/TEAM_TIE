package com.revature.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.dao.PokemonDao;
import com.revature.dao.TeamDao;
import com.revature.dao.TradeDao;
import com.revature.dao.TrainerDao;
import com.revature.models.Pokemon;
import com.revature.models.Team;
import com.revature.models.Trainer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class daoTest {
	
	@Autowired
	PokemonDao pkmndao;
	
	@Autowired
	TrainerDao traindao;
	
	@Autowired
	TeamDao teamdao;
	
	@Autowired
	TradeDao tradedao;
	
	@Test
	public void teamDaoTests() {
		Team t = new Team();
		t.setTeamName("test02");
		int id = teamdao.createTeam(t);
		Team t2 = teamdao.getTeamById(id);
		assertEquals(t.getTeamName(), t2.getTeamName());
		t2.setTeamName("test03");
		teamdao.updateTeam(t2);
		Team t3 = teamdao.getTeamById(id);
		assertEquals("test03", t3.getTeamName());
		teamdao.deleteTeam(id);
	}
	
	@Test
	public void trainerDaoTests() {
		Trainer t = new Trainer();
		t.setLogin("testusr2");
		t.setPass("testpass2");
		t.setEmail("test@two.com");
		int id = traindao.createTrainer(t);
		Trainer t2 = traindao.getTrainerById(id);
		assertEquals(t.getPass(), t2.getPass());
		Trainer tbn = traindao.getTrainerByLogin("testusr2");
		assertEquals(id, tbn.getId());
		Team tt = new Team();
		tt.setTeamName("usrtst");
		int tmid = teamdao.createTeam(tt);
		tbn.setTeam_id(tt);
		traindao.editTrainer(tbn);
		Trainer t3 = traindao.getTrainerById(id);
		assertEquals(tmid, t3.getTeam_id().getId());
		traindao.deleteTrainer(id);
		teamdao.deleteTeam(tmid);
	}
	
	@Test
	public void pokemonDaoTests() {
		Pokemon p = new Pokemon();
		Trainer tr = new Trainer();
		tr.setLogin("testusr3");
		tr.setPass("testpass3");
		tr.setEmail("test@three.com");
		int trid = traindao.createTrainer(tr);
		p.setPkmn_id(5);
		p.setMove_one("1");
		p.setTrainer_id(traindao.getTrainerById(trid));
		int pkid = pkmndao.createPokemon(p);
		Pokemon p2 = pkmndao.getPokemonById(pkid);
		assertEquals(p.getMove_one(), p2.getMove_one());
		p2.setMove_two("2");
		pkmndao.updatePokemon(p2);
		Pokemon p3 = pkmndao.getPokemonById(pkid);
		assertEquals(p2.getMove_two(), p3.getMove_two());
		pkmndao.deletePokemon(pkid);
		traindao.deleteTrainer(trid);
	}
}
