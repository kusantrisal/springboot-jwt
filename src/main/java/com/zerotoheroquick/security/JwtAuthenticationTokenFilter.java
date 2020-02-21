package com.zerotoheroquick.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.zerotoheroquick.model.JwtAuthenticationToken;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter{

	public JwtAuthenticationTokenFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		log.debug("Attempting Authentication : {}",  request );
		
		String header = request.getHeader("Authorization");
		if (header == null || !header.startsWith("JWT")) {
			throw new RuntimeException("JWT Token is missing or doesn't have write prefix");
		}
		String authenticationToken = header.substring(4);

		JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);

		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		log.info("Successful Authentication");
        chain.doFilter(request, response);
	}

}
