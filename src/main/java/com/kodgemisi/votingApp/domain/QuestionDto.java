package com.kodgemisi.votingApp.domain;

/**
 * Created by ozge on 31.08.2016.
 */
public class QuestionDto {

	private Long id;

	private String choice;

	public QuestionDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}
}
