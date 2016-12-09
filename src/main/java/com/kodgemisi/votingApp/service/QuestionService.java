package com.kodgemisi.votingApp.service;

import com.kodgemisi.votingApp.domain.Choice;
import com.kodgemisi.votingApp.domain.Question;
import com.kodgemisi.votingApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.TimeZone;

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

	public long calculateRemainingTime(Long id){

		Question question = this.questionRepository.findById(id);
		long dueTime = question.getCreationDate().getTime().getTime() + question.getTimeout() * 1000;
		Calendar now = Calendar.getInstance();
		long remainingTime = dueTime - now.getTime().getTime();

		return remainingTime;
	}

	public void calculateTimeoutForNoonAndMidnight(Question question){

		if (question.getTimeout() == 120000) {

//			TimeZone tz = TimeZone.getTimeZone("Turkey");

			LocalDateTime today = LocalDateTime.now();
			LocalDateTime timeForNoon = LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 12, 00, 00);

			if(today.isAfter(timeForNoon)){

				LocalDateTime newNoon = timeForNoon.plusHours(24);
				long timeDiff = today.until(newNoon, ChronoUnit.SECONDS);
				question.setTimeout((int) timeDiff);

			} else {

				long timeDifference = today.until(timeForNoon, ChronoUnit.SECONDS);
				question.setTimeout((int) timeDifference);

			}

		} else if (question.getTimeout() == 000000) {

			LocalDateTime t =  LocalDateTime.now();
			LocalDateTime timeForMidnight = LocalDateTime.of(t.getYear(), t.getMonth(), t.getDayOfMonth(), 23, 59, 00);

			if(t.isAfter(timeForMidnight)){

				long timeDiff = t.until(timeForMidnight.plusHours(24), ChronoUnit.SECONDS);
				question.setTimeout((int) timeDiff);

			} else {

				long timeDifference = t.until(timeForMidnight, ChronoUnit.SECONDS);
				question.setTimeout((int) timeDifference);

			}
		}
	}
}
