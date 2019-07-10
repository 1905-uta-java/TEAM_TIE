package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Trade {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tradeSequence")
	@SequenceGenerator(name="tradeSequence", sequenceName="SQ_TRADE_PK")
	@Column(name="trade_id")
	private int id;
	
	@OneToOne
	private Pokemon pkmn_1;
	@OneToOne
	private Pokemon pkmn_2;
	
	public Trade(int id) {
		setId(id);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public Pokemon getPkmn_1() {
		return pkmn_1;
	}

	public void setPkmn_1(Pokemon pkmn_1) {
		this.pkmn_1 = pkmn_1;
	}

	public Pokemon getPkmn_2() {
		return pkmn_2;
	}

	public void setPkmn_2(Pokemon pkmn_2) {
		this.pkmn_2 = pkmn_2;
	}
}
