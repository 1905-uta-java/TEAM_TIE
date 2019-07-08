package com.revature.dao;

import java.util.List;

import com.revature.models.Trainer;

public interface TrainerDao {
	public List<Trainer> getTrainers();
	public Trainer getTrainerById(int id);
	public int createTrainer(Trainer t);
	public void editTrainer(Trainer t);
	public void deleteTrainer(int id);
}