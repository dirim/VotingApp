package com.kodgemisi.votingApp.controller;

import com.kodgemisi.votingApp.domain.*;
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
import java.text.ParseException;
import java.util.*;

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

	@Autowired
	private QuestionService questionService;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newQuestion(Model model, @AuthenticationPrincipal User user){

		model.addAttribute("user", user);
		model.addAttribute("question", new  Question());

		return "question/questionCreateForm";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String createQuestion(@ModelAttribute @Valid Question question, BindingResult bindingResult,
									@AuthenticationPrincipal User user) throws ParseException {

		if(bindingResult.hasErrors()){
			return "redirect:/questions/new";
		}

		for (Choice choice: question.getChoices()) {
			choice.setQuestion(question);
		}

		this.questionService.calculateTimeoutForNoonAndMidnight(question);
		question.setOwner(user);
		this.questionRepository.save(question);
		return "redirect:/questions";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listQuestions(Model model, @RequestParam(value = "mine", required = false) boolean mine,
								@AuthenticationPrincipal User user){

		if(mine){
			Iterable<Question> questions =  this.questionRepository.findByOwner(user);
			Iterable<Question> calculatedQuestions = this.questionService.calculateVotes(questions);
			model.addAttribute("questions", calculatedQuestions);
			model.addAttribute("mine", true);
		} else {
			Iterable<Question> questions = this.questionRepository.findAll();
			Iterable<Question> calculatedQuestions = this.questionService.calculateVotes(questions);
			model.addAttribute("questions", calculatedQuestions);

//			model.addAttribute("time", this.questionService.calculateRemainingTime(this.questionRepository.findAll().iterator().next().getId()));

			Map<Long, Long> questionsRemainingTime = new HashMap();
			for(Question question: questions){

				questionsRemainingTime.put(question.getId(), this.questionService.calculateRemainingTime(question.getId()));

			}
			model.addAttribute("timeList", questionsRemainingTime);
		}

		return "question/questionList";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showQuestion(Model model, @PathVariable("id") Long id,
							   @AuthenticationPrincipal User user){

		Answer answer = this.answerRepository.findByOwnerAndChoiceQuestion(user, this.questionRepository.findById(id));

		if (answer != null) {
			model.addAttribute("answered", "Your vote is saved");
			model.addAttribute("selectedChoice", answer.getChoice().getId());
		}
		model.addAttribute("question", this.questionRepository.findOne(id));

		model.addAttribute("remainingTime", this.questionService.calculateRemainingTime(id));

		return "question/questionDetail";
	}

	@RequestMapping(value = "/{id}/chart", method = RequestMethod.GET)
	@ResponseBody
	Map<String, Long> showQuestionResult(@PathVariable("id") Long id, @AuthenticationPrincipal User user){

		Question question = this.questionRepository.findById(id);
		List<Choice> choices = question.getChoices();
		Map<String,Long> voteCounts = new HashMap();

		for(Choice choice : choices){
			voteCounts.put(choice.getText(), choice.getVoteCount());
		}
		return voteCounts;
	}

}
