package com.kodgemisi.votingApp.service;

import com.kodgemisi.votingApp.domain.Answer;
import com.kodgemisi.votingApp.domain.Choice;
import com.kodgemisi.votingApp.domain.QuestionDto;
import com.kodgemisi.votingApp.domain.User;
import com.kodgemisi.votingApp.repository.AnswerRepository;
import com.kodgemisi.votingApp.repository.ChoiceRepository;
import com.kodgemisi.votingApp.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ozge on 01.09.2016.
 */
@Service
@Transactional
public class AnswerService {

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private ChoiceRepository choiceRepository;

	@Autowired
	private UserRepository userRepository;

	public Answer createAnswer(User user, QuestionDto questionDto) {

		Choice choice = choiceRepository.findById(questionDto.getChoiceId());

		choice.setVoteCount(choice.getVoteCount() + 1);

		Answer answer = new Answer();
		answer.setOwner(user);
		answer.setChoice(choice);
		user = userRepository.findByEmail(user.getEmail());
		user.getAnswers().add(answer);
		answerRepository.save(answer);

		choice.getAnswers().add(answer);

		choiceRepository.save(choice);
		this.userRepository.save(user);

		return answer;
	}
}
