package com.zerotoheroquick.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.zerotoheroquick.model.JwtAuthenticationToken;
import com.zerotoheroquick.model.JwtUser;
import com.zerotoheroquick.model.JwtUserDetails;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private JwtValidator jwtValidator;

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
		String token = jwtAuthenticationToken.getToken();
		JwtUser jwtUser = jwtValidator.validate(token);
	
		if(jwtUser == null) {
			throw new RuntimeException("JWT token is incorrect");
		}
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(jwtUser.getRole());
		JwtUserDetails jwtUserDetails = new JwtUserDetails(jwtUser.getUsername(), jwtUser.getId(), grantedAuthorities, token);
		log.info("Returning user {}",jwtUserDetails.toString() );
		return jwtUserDetails;

	}

}
