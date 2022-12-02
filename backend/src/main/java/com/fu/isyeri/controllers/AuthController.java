package com.fu.isyeri.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fu.isyeri.dto.Credentials;
import com.fu.isyeri.result.AuthResult;
import com.fu.isyeri.result.Result;
import com.fu.isyeri.services.abstracts.UserAuthService;


@RestController
@RequestMapping("/api/1.0/auth")
public class AuthController {
	
	private UserAuthService userAuthService;
	
	public AuthController(UserAuthService userAuthService) {
		this.userAuthService = userAuthService;
	}
	
	@PostMapping("/login")
	AuthResult handleAuthentication(@RequestBody Credentials credentials) {
		return userAuthService.authenticate(credentials);
	}
	
	@PostMapping("/logout")
	Result handleLogout(@RequestHeader(name = "Authorization") String authorization) {
		String token = authorization.substring(7);
		userAuthService.clearToken(token);
		return new Result(true, "Çıkış yapıldı");
	}

}
