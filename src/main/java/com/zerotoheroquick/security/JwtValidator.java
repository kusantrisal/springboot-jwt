package com.zerotoheroquick.security;

import org.springframework.stereotype.Component;

import com.zerotoheroquick.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

/**
 * Get userDetail (AKA JwtUser) from token
 *
 */
@Component
@Slf4j
public class JwtValidator {

	private String secret = "mySecret";

	public JwtUser validate(String token) {
		log.info("Validating token {}", token);
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

		JwtUser jwtUser = null ;
		try {
			jwtUser = new JwtUser();
			jwtUser.setUsername(body.getSubject());
			jwtUser.setId((String) body.get("userId"));
			jwtUser.setRole((String) body.get("role"));
		} catch (Exception e) {
			log.error("Error Validating token {} \n {}", token, e);
		}

		return jwtUser;

	}

}
