package com.revature.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
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
	
	@OneToMany(mappedBy="team_id", fetch=FetchType.EAGER)
	private List<Trainer> team_mates;
	
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
	
	public List<Trainer> getTeam_mates() {
		return team_mates;
	}
}
