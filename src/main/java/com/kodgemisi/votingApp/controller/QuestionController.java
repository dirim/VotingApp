package com.kodgemisi.votingApp.controller;

import com.kodgemisi.votingApp.domain.Answer;
import com.kodgemisi.votingApp.domain.Choice;
import com.kodgemisi.votingApp.domain.Question;
import com.kodgemisi.votingApp.domain.User;
import com.kodgemisi.votingApp.repository.AnswerRepository;
import com.kodgemisi.votingApp.repository.QuestionRepository;
import com.kodgemisi.votingApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ozge on 17.08.2016.
 */

@Controller
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newQuestion(Model model, @AuthenticationPrincipal User user){

		model.addAttribute("user", user);
		model.addAttribute("question", new  Question());

		return "question/questionCreateForm";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String createQuestion(@ModelAttribute @Valid Question question, BindingResult bindingResult,
									@AuthenticationPrincipal User user){

		if(bindingResult.hasErrors()){
			return "redirect:/questions/new";
		}

		for (Choice choice: question.getChoices()) {
			choice.setQuestion(question);
		}

		question.setOwner(user);
		this.questionRepository.save(question);
		return "redirect:/questions";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listQuestions(Model model, @RequestParam(value = "mine", required = false) boolean mine,
								@AuthenticationPrincipal User user){

		if(mine){
			model.addAttribute("questions", this.questionRepository.findByOwner(user));
		}
		else{
			model.addAttribute("questions", this.questionRepository.findAll());
		}

		return "question/questionList";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showQuestion(Model model, @PathVariable("id") Long id,
							   @AuthenticationPrincipal User user){

		Answer ans = this.answerRepository.findByOwner(user);

		if (ans != null) {
			model.addAttribute("answered", true);
		}

		model.addAttribute("question", this.questionRepository.findOne(id));

		return "question/questionDetail";
	}

}
