package com.kodgemisi.votingApp.controller;

import com.kodgemisi.votingApp.domain.User;
import com.kodgemisi.votingApp.repository.UserRepository;
import com.kodgemisi.votingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by ozge on 28.08.2016.
 */
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String newUser(Model model){

		model.addAttribute("user", new User());
		return "user/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String createUser(@ModelAttribute @Valid User user, BindingResult bindingResult){

		if(bindingResult.hasErrors()){
			return "redirect:/users/register";
		}

		this.userRepository.save(user);
		return "redirect:/users/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(){
		return "user/login";
	}

}
