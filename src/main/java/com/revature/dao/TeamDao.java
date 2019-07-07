package com.revature.dao;

import java.util.List;

import com.revature.models.Team;

public interface TeamDao {
	public List<Team> getTeams();
	public Team getTeamById(int id);
	public int createTeam(Team t);
	public void updateTeam(Team t);
	public void deleteTeam(int id);
}
