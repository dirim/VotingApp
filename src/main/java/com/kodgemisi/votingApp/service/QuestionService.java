package com.kodgemisi.votingApp.service;

import com.kodgemisi.votingApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ozge on 01.09.2016.
 */
@Service
@Transactional
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
}
