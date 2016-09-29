package com.kodgemisi.votingApp.service;

import com.kodgemisi.votingApp.domain.Choice;
import com.kodgemisi.votingApp.domain.Question;
import com.kodgemisi.votingApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ozge on 01.09.2016.
 */
@Service
@Transactional
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	public Iterable<Question> calculateVotes(Iterable<Question> questions) {

		for(Question question: questions){
			long voteCount = 0;
			for(Choice choice: question.getChoices()){
				voteCount += choice.getVoteCount();
			}
			question.setVoteCount(voteCount);
		}
		return questions;
	}

}
