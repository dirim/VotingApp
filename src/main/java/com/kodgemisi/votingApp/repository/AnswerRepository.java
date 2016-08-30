package com.kodgemisi.votingApp.repository;

import com.kodgemisi.votingApp.domain.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ozge on 17.08.2016.
 */
@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
