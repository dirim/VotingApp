package com.kodgemisi.votingApp.controller;

import com.kodgemisi.votingApp.domain.Answer;
import com.kodgemisi.votingApp.domain.User;
import com.kodgemisi.votingApp.repository.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String answeringQuestion(Model model, @ModelAttribute @Valid Answer answer, BindingResult bindingResult,
									@AuthenticationPrincipal User user){

		if(bindingResult.hasErrors()){
			return "redirect:/questions";
		}

		answer.setOwner(user);
//		answer.setChoice();

		return "redirect:/questions/{id}";
	}


}
