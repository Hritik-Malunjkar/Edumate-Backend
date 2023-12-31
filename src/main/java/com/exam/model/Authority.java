package com.exam.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;

public class Authority implements GrantedAuthority {
	
	private String authority;
	
	public Authority(String authority) {
		this.authority=authority;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}

}
