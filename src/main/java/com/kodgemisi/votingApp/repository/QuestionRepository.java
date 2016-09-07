package com.kodgemisi.votingApp.repository;

import com.kodgemisi.votingApp.domain.Question;
import com.kodgemisi.votingApp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ozge on 17.08.2016.
 */
@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

		Question findById(@Param("id") Long id);

		Question findByOwner(@Param("owner") User owner);
}
