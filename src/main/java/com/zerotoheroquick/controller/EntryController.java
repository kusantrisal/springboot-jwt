package com.zerotoheroquick.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class EntryController {

	@GetMapping("/secure")
	public String secure() {
		return "Successfully accessed secure api";
	}
}
