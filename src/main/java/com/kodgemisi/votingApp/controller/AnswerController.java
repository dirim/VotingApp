package com.kodgemisi.votingApp.controller;

import com.kodgemisi.votingApp.domain.Answer;
import com.kodgemisi.votingApp.domain.QuestionDto;
import com.kodgemisi.votingApp.domain.User;
import com.kodgemisi.votingApp.repository.AnswerRepository;
import com.kodgemisi.votingApp.repository.ChoiceRepository;
import com.kodgemisi.votingApp.repository.UserRepository;
import com.kodgemisi.votingApp.service.AnswerService;
import com.kodgemisi.votingApp.service.ChoiceService;
import com.kodgemisi.votingApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by ozge on 24.08.2016.
 */

@Controller
@RequestMapping("/answers")
public class AnswerController {

	@Autowired
	private AnswerService answerService;

	@Autowired
	private AnswerRepository answerRepository;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String answeringQuestion(@ModelAttribute @Valid QuestionDto questionDto, BindingResult bindingResult,
									@AuthenticationPrincipal User user) {

		if (bindingResult.hasErrors()) {
			return "redirect:/questions";
		}

		Answer ans = this.answerRepository.findByOwner(user);

		if(this.answerService.timeCalculation(questionDto) &&  (ans == null)){
			this.answerService.createAnswer(user, questionDto);
		}

		return "redirect:/questions/" + questionDto.getId();

	}

}
