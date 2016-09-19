package com.example.stickyhabits;

public class NumericQuestion extends Question {
	
	private String contribution;
	private String contributionValue;
	private String contributionValueRank;
	
	public NumericQuestion(String question) {
		super(question);
	}
	
	public NumericQuestion(String question, String contribution, String contributionValue, String contributionValueRank) {
		super(question);
		this.contribution = contribution;
		this.contributionValue = contributionValue;
		this.contributionValueRank = contributionValueRank;
	}
	
	public String toString() {	
		return "numeric" + ":" + question + ":" + contribution + ":" + contributionValue + ":" + contributionValueRank;
	}

	public String getContribution() {
		return contribution;
	}

	public void setContribution(String contribution) {
		this.contribution = contribution;
	}

	public String getContributionValue() {
		return contributionValue;
	}

	public void setContributionValue(String contributionValue) {
		this.contributionValue = contributionValue;
	}

	public String getContributionValueRank() {
		return contributionValueRank;
	}

	public void setContributionValueRank(String contributionValueRank) {
		this.contributionValueRank = contributionValueRank;
	}

}
