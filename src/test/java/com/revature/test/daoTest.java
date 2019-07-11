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
import com.revature.models.Team;

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
}
