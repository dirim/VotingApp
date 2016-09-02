package com.kodgemisi.votingApp.domain;

/**
 * Created by ozge on 31.08.2016.
 */
public class QuestionDto {

	private Long id;

	private Long choiceId;

	public QuestionDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(Long choiceId) {
		this.choiceId = choiceId;
	}
}
