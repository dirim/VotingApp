package com.kodgemisi.votingApp.repository;

import com.kodgemisi.votingApp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by ozge on 17.08.2016.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByName(@Param("name") String name);

	User findByEmail(@Param("email") String email);


}
