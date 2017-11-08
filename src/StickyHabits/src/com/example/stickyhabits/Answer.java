package com.example.stickyhabits;

public class Answer {

	private String answer;
	private String score;
	
	public Answer(String answer, String score) {
		super();
		this.answer = answer;
		this.score = score;
	}

	public String toString() {
		return answer + ":" + score;
	}
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	
	
}
