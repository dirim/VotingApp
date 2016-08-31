package com.kodgemisi.votingApp.controller;

import com.kodgemisi.votingApp.domain.*;
import com.kodgemisi.votingApp.repository.ChoiceRepository;
import com.kodgemisi.votingApp.repository.QuestionRepository;
import com.kodgemisi.votingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by ozge on 24.08.2016.
 */

@Controller
@RequestMapping("/answers")
public class AnswerController {

	@Autowired
	private ChoiceRepository choiceRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String answeringQuestion(@ModelAttribute @Valid QuestionDto questionDto, BindingResult bindingResult,
									@AuthenticationPrincipal User user){

		if(bindingResult.hasErrors()){
			return "redirect:/questions";
		}

		Answer answer =  new Answer();
		answer.setOwner(user);

		Choice choice = new Choice();
		choice.setText(questionDto.getChoice());
		answer.setChoice(choice);

		user.getAnswers().add(answer);


		choice.setVoteCount(choice.getVoteCount() + 1);
		choice.getAnswers().add(answer);


		return "redirect:/questions/{id}";
	}


}
