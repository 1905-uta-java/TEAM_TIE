package com.revature.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id",
		  scope=Pokemon.class)
@Entity
public class Pokemon {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pkmnSequence")
	@SequenceGenerator(name="pkmnSequence", sequenceName="SQ_PKMN_PK")
	@Column(name="pkmn_pk_id")
	private int id;
	
	private int pkmn_id;
	
	private Date created;
	
	private String nickname;
	
	@ManyToOne
	private Trainer trainer_id;
	
	private String move_one;
	private String move_two;
	private String move_three;
	private String move_four;
	
	public Pokemon() {
		super();
	}
	
	public Pokemon(int id) {
		super();
		this.id = id;
	}
	
	public Pokemon(int id, Date created, int pkmn_id, Trainer trainer_id, String m1, String m2, String m3, String m4, String nickname) {
		super();
		this.id = id;
		this.setCreated(created);
		this.trainer_id = trainer_id;
		this.setPkmn_id(pkmn_id);
		this.setMove_one(m1);
		this.setMove_two(m2);
		this.setMove_three(m3);
		this.setMove_four(m4);
		this.setNickname(nickname);
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
	
	public void genCreated() {
		long now = System.currentTimeMillis();
		this.setCreated(new Date(now));
	}
	
	public Trainer getTrainer_id() {
		return trainer_id;
	}
	
	public void setTrainer_id(Trainer trainer_id) {
		this.trainer_id = trainer_id;
	}

	public String getMove_one() {
		return move_one;
	}

	public void setMove_one(String move_one) {
		this.move_one = move_one;
	}

	public String getMove_two() {
		return move_two;
	}

	public void setMove_two(String move_two) {
		this.move_two = move_two;
	}

	public String getMove_three() {
		return move_three;
	}

	public void setMove_three(String move_three) {
		this.move_three = move_three;
	}

	public String getMove_four() {
		return move_four;
	}

	public void setMove_four(String move_four) {
		this.move_four = move_four;
	}

	public int getPkmn_id() {
		return pkmn_id;
	}

	public void setPkmn_id(int pkmn_id) {
		this.pkmn_id = pkmn_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
