package com.zerotoheroquick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zerotoheroquick.model.JwtUser;
import com.zerotoheroquick.security.JwtTokenGenerator;

@RestController
@RequestMapping("/token")
public class TokenController {

	@Autowired
	private JwtTokenGenerator tokenGenerator;

	@PostMapping("/generateToken")
	public String generateToken(@RequestBody JwtUser jwtUser) {
		return tokenGenerator.generate(jwtUser);
	}
	
	@GetMapping("/test")
	public String test() {
		return "Successfully accessed /token/test api";
	}
}
