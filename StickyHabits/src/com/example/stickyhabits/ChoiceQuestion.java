package com.example.stickyhabits;

import java.util.ArrayList;

public class ChoiceQuestion extends Question {
	
	private ArrayList<Answer> answers;

	protected ChoiceQuestion(String question) {
		super(question);
		// TODO Auto-generated constructor stub
	}

	public ChoiceQuestion(String question, ArrayList<Answer> answers) {
		super(question);
		this.answers = answers;
	}

	public String toString() {	
		
		String choiceQuestion = "choice" + ":" + question + ":" + answers.size();
		
		for(int i = 0; i < answers.size(); ++i) {
			choiceQuestion += ":" + answers.get(i).toString();
		}
		return choiceQuestion;
	}
	
	public ArrayList<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}
	
	

}
