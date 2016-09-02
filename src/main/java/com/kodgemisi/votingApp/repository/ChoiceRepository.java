package com.kodgemisi.votingApp.repository;

import com.kodgemisi.votingApp.domain.Choice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by ozge on 17.08.2016.
 */
@Repository
public interface ChoiceRepository extends CrudRepository<Choice, Long> {

	Choice findById(@Param("choiceId") Long id);

}
