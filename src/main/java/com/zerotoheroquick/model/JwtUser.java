package com.zerotoheroquick.model;

import lombok.Data;

@Data
public class JwtUser {

	private String username;
	private String id;
	private String role;

}
