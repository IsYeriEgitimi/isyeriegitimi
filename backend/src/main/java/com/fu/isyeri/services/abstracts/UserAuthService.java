package com.fu.isyeri.services.abstracts;

import org.springframework.security.core.userdetails.UserDetails;

import com.fu.isyeri.dto.Credentials;
import com.fu.isyeri.result.AuthResult;

public interface UserAuthService {

	AuthResult authenticate(Credentials credentials);
	
	UserDetails getUserDetails(String token);
	
	void clearToken(String token);
}
