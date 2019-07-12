package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Ignore;
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
import com.revature.models.Trade;
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
	
	@Ignore
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
	
	@Ignore
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
	
	@Ignore
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
	
	// The most complicated test...
	@Ignore
	@Test
	public void tradeDaoTests() {
		Trainer tr1 = new Trainer("test4train1", "pass41", "test4@train1.com");
		Trainer tr2 = new Trainer("test4train2", "pass42", "test4@train2.com");
		int trid1 = traindao.createTrainer(tr1);
		int trid2 = traindao.createTrainer(tr2);
		Pokemon pk1 = new Pokemon();
		pk1.setPkmn_id(41);
		pk1.setMove_one("1");
		pk1.setTrainer_id(traindao.getTrainerById(trid1));
		Pokemon pk2 = new Pokemon();
		pk2.setPkmn_id(82);
		pk2.setMove_one("1");
		pk2.setTrainer_id(traindao.getTrainerById(trid2));
		int pkid1 = pkmndao.createPokemon(pk1);
		int pkid2 = pkmndao.createPokemon(pk2);
		Trade t1 = new Trade();
		t1.setPkmn_1(pkmndao.getPokemonById(pkid1));
		t1.setPkmn_2(pkmndao.getPokemonById(pkid2));
		int trid = tradedao.createTrade(t1);
		Trade t2 = tradedao.getTradeById(trid);
		assertEquals(t1.getPkmn_1().getPkmn_id(), t2.getPkmn_1().getPkmn_id());
		tradedao.acceptTrade(t2);
		Trainer tr1a = traindao.getTrainerById(trid1);
		assertEquals(82, tr1a.getPokemon().get(0).getPkmn_id());
		assertNull(tradedao.getTradeById(trid));
		traindao.deleteTrainer(trid1);
		traindao.deleteTrainer(trid2);
	}
}
