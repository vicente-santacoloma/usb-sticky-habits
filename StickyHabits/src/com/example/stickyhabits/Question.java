package com.example.stickyhabits;

public abstract class Question {
	
	protected String question;
	
	protected Question(String question) {
		this.question = question;
	}

	public abstract String toString();
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
