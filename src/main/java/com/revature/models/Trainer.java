package com.revature.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id",
		  scope=Trainer.class)
@Entity
public class Trainer {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="trainerSequence")
	@SequenceGenerator(name="trainSequence", sequenceName="SQ_TRAIN_PK")
	@Column(name="trainer_id")
	private int id;
	
	private Date created;
	
	private String email;
	
	@Column(unique=true)
	private String login;
	
	@JsonIgnore
	private String pass;
	
	@ManyToOne
	private Team team_id;
	
	
	@OneToMany(mappedBy="trainer_id", fetch=FetchType.EAGER)
	private List<Pokemon> pkmn;
	
	private int is_lead;
	
	public Trainer() {
		super();
	}
	
	public Trainer(int id) {
		super();
		this.id = id;
	}
	
	public Trainer(String login, String pass, String email) {
		this.setLogin(login);
		this.setPass(pass);
		this.setEmail(email);
		long now = System.currentTimeMillis();
		this.setCreated(new Date(now));
	}
	
	public Trainer(int id, Date created, String login, String pass, String email, Team team_id, int is_lead) {
		super();
		this.id = id;
		this.setCreated(created);
		this.setLogin(login);
		this.setPass(pass);
		this.setEmail(email);
		this.setTeam_id(team_id);
		this.setIs_lead(is_lead);
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Team getTeam_id() {
		return team_id;
	}

	public void setTeam_id(Team team_id) {
		this.team_id = team_id;
	}

	public int getIs_lead() {
		return is_lead;
	}

	public void setIs_lead(int is_lead) {
		this.is_lead = is_lead;
	}
	
	public List<Pokemon> getPokemon(){
		return pkmn;
	}
}
