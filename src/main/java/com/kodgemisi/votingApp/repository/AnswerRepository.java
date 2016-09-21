package com.kodgemisi.votingApp.repository;

import com.kodgemisi.votingApp.domain.Answer;
import com.kodgemisi.votingApp.domain.Question;
import com.kodgemisi.votingApp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ozge on 17.08.2016.
 */
@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {

	Answer findByOwner(@Param("owner") User owner);

	//List<Answer> findByChoiceQuestion(Question question);

	Answer findByOwnerAndChoiceQuestion(User user, Question question);
}
