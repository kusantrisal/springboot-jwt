package com.zerotoheroquick.security;

import org.springframework.stereotype.Component;

import com.zerotoheroquick.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenGenerator {

	public String generate(JwtUser jwtUser) {
		log.info("Generating jwt token for user {}", jwtUser.toString());
		Claims body = Jwts.claims().setSubject(jwtUser.getUsername());
		body.put("userId", jwtUser.getId());
		body.put("role", jwtUser.getRole());

		return Jwts.builder()
				// .setExpiration(exp)
				.setClaims(body).signWith(SignatureAlgorithm.HS512, "mySecret").compact();
	}

}
