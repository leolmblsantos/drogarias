package com.projetoautomacao.drogaria.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.projetoautomacao.drogaria.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
