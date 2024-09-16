package com.dmm.task.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@RequestMapping("/create")
	public String index() {
		return "create";
	}

	@RequestMapping("/edit")
	public String test() {
		return "edit";
	}
	
	@GetMapping("/login")
	public String login() {
			return "login";
		}
	
	@GetMapping("/loginForm")
	public String loginForm() {
			return "login";
		}
	}

