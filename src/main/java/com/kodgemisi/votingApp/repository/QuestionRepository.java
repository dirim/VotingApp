package com.kodgemisi.votingApp.repository;

import com.kodgemisi.votingApp.domain.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by ozge on 17.08.2016.
 */
@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

		Question findQuestionById(@Param("id") Long id);
}
