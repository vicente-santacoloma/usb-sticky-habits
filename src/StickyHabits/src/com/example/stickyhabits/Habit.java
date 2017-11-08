package com.example.stickyhabits;

import java.util.ArrayList;

public class Habit {
	
	private String goal;
	private int totalScore = 0;
	private String start_date;
	private String duration;
	private String frequency;
	private int frequencyPosition;
	private String difficulty;
	private int difficultyPosition;
	private ArrayList<Question> questions;
	
	private User user;

	public Habit() {
		super();
	}
	
	public Habit(String goal) {
		super();
		this.goal = goal;
	}

	public Habit(String goal, String start_date, String duration,
			String frequency, String difficulty) {
		super();
		this.goal = goal;
		this.start_date = start_date;
		this.duration = duration;
		this.frequency = frequency;
		this.difficulty = difficulty;
	}
	
	public Habit(String goal, String start_date, String duration,
			String frequency, String difficulty, ArrayList<Question> questions) {
		super();
		this.goal = goal;
		this.start_date = start_date;
		this.duration = duration;
		this.frequency = frequency;
		this.difficulty = difficulty;
		this.questions = questions;
	}
	
	public Habit(String goal, String start_date, String duration,
			String frequency, int frequencyPosition, String difficulty,
			int difficultyPosition) {
		super();
		this.goal = goal;
		this.start_date = start_date;
		this.duration = duration;
		this.frequency = frequency;
		this.frequencyPosition = frequencyPosition;
		this.difficulty = difficulty;
		this.difficultyPosition = difficultyPosition;
	}
	
	public Habit(String goal, String start_date, String duration,
			String frequency, String difficulty, ArrayList<Question> questions,
			User user) {
		super();
		this.goal = goal;
		this.start_date = start_date;
		this.duration = duration;
		this.frequency = frequency;
		this.difficulty = difficulty;
		this.questions = questions;
		this.user = user;
	}
	
	public Habit(String goal, String start_date, String duration,
			String frequency, int frequencyPosition, String difficulty,
			int difficultyPosition, ArrayList<Question> questions) {
		super();
		this.goal = goal;
		this.start_date = start_date;
		this.duration = duration;
		this.frequency = frequency;
		this.frequencyPosition = frequencyPosition;
		this.difficulty = difficulty;
		this.difficultyPosition = difficultyPosition;
		this.questions = questions;
	}

	public Habit(String goal, String start_date, String duration,
			String frequency, int frequencyPosition, String difficulty,
			int difficultyPosition, ArrayList<Question> questions, User user) {
		super();
		this.goal = goal;
		this.start_date = start_date;
		this.duration = duration;
		this.frequency = frequency;
		this.frequencyPosition = frequencyPosition;
		this.difficulty = difficulty;
		this.difficultyPosition = difficultyPosition;
		this.questions = questions;
		this.user = user;
	}

	public String toString() {
		
		String habit = user.getUsername() + ":" + 
					   goal + ":" + 
					   totalScore + ":" +
					   start_date + ":" + 
					   duration + ":" + 
					   frequency + ":" + 
					   difficulty + ":" + 
					   questions.size();
		for(int i = 0; i < questions.size(); ++i) {
			habit += ":" + questions.get(i).toString();
		}
		return habit;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public int getFrequencyPosition() {
		return frequencyPosition;
	}

	public void setFrequencyPosition(int frequencyPosition) {
		this.frequencyPosition = frequencyPosition;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public int getDifficultyPosition() {
		return difficultyPosition;
	}

	public void setDifficultyPosition(int difficultyPosition) {
		this.difficultyPosition = difficultyPosition;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
