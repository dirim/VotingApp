package com.kodgemisi.votingApp.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by ozge on 17.08.2016.
 */
@Entity
public class Answer extends BaseEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "choiceId")
	private Choice choice;

	@ManyToOne(fetch = FetchType.EAGER)
	private User owner;

	public Answer() {
	}

	public Answer(Choice choice, User owner) {
		this.choice = choice;
		this.owner = owner;
	}

	public Choice getChoice() {
		return choice;
	}

	public void setChoice(Choice choice) {
		this.choice = choice;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
