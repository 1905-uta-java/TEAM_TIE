package com.revature.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Team {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="teamSequence")
	@SequenceGenerator(name="teamSequence", sequenceName="SQ_TEAM_PK")
	@Column(name="team_id")
	private int id;
	
	private Date created;
	
	@Column(unique=true)
	private String team_name;
	
	public Team() {
		super();
	}
	
	public Team(int id) {
		super();
		this.id = id;
	}
	
	public Team(int id, Date created, String team_name) {
		super();
		this.id = id;
		this.created = created;
		this.team_name = team_name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	public String getTeamName() {
		return team_name;
	}

	public void setTeamName(String team_name) {
		this.team_name = team_name;
	}
}
