package com.kodgemisi.votingApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ozge on 09.12.2016.
 */

@Controller
public class HomeController {

	@RequestMapping("/")
	public String getHomePage() {

		return "home";
	}
}
