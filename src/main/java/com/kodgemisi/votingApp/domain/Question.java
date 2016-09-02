package com.kodgemisi.votingApp.domain;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * Created by ozge on 17.08.2016.
 */
@Entity
public class Question extends BaseEntity {

	private String name;

	private String description;

	@OneToMany(mappedBy = "question", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Choice> choices;

	private int timeout;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User owner;

	public Question() {
	}

	public Question(String name, String description, int timeout, User owner, List<Choice> choices) {
		this.name = name;
		this.description = description;
		this.timeout = timeout;
		this.owner = owner;
		this.choices = choices;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
