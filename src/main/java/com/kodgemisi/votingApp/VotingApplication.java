package com.kodgemisi.votingApp;


import com.kodgemisi.votingApp.domain.Choice;
import com.kodgemisi.votingApp.domain.Question;
import com.kodgemisi.votingApp.domain.User;
import com.kodgemisi.votingApp.repository.QuestionRepository;
import com.kodgemisi.votingApp.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ozge on 17.08.2016.
 */
@SpringBootApplication
public class VotingApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(VotingApplication.class, args);
	}

	@Bean
	public SessionFactory sessionFactory(EntityManagerFactory emf) {
		if (emf.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("factory is not a hibernate factory");
		}
		return emf.unwrap(SessionFactory.class);
	}

	@Override
	public void run(String... strings) throws Exception {

		User user = new User("ozgee", "ozge", "123");
		this.userRepository.save(user);

	}
}
