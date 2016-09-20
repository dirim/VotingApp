package com.kodgemisi.votingApp.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;

/**
 * Created by ozge on 31.08.2016.
 */
public class QuestionDto {

	private Long id;

	private Long choiceId;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar creationDate;

	private int timeout;

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

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
