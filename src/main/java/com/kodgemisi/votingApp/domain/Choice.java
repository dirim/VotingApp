package com.kodgemisi.votingApp.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ozge on 17.08.2016.
 */
@Entity
public class Choice extends BaseEntity {

	private String text;

	private Long voteCount = 0L;

	@ManyToOne
	private Question question;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "choice")
	private Set<Answer> answers = new HashSet<>();

	public Choice() {
	}

	public Choice(String text, Question question, Set<Answer> answers) {
		this.text = text;
		this.question = question;
		this.answers = answers;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Long voteCount) {
		this.voteCount = voteCount;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}
}
