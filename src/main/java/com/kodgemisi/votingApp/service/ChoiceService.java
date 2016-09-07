package com.kodgemisi.votingApp.service;

import com.kodgemisi.votingApp.domain.Answer;
import com.kodgemisi.votingApp.domain.Choice;
import com.kodgemisi.votingApp.domain.QuestionDto;
import com.kodgemisi.votingApp.repository.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ozge on 01.09.2016.
 */
@Service
@Transactional
public class ChoiceService {

	@Autowired private ChoiceRepository choiceRepository;

	public Choice getChoiceById(Long id){
		return choiceRepository.findById(id);
	}
}
