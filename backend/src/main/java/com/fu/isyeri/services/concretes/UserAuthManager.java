package com.fu.isyeri.services.concretes;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fu.isyeri.dto.Credentials;
import com.fu.isyeri.dto.UserDto;
import com.fu.isyeri.entities.User;
import com.fu.isyeri.errors.AuthException;
import com.fu.isyeri.repository.UserRepository;
import com.fu.isyeri.result.AuthResult;
import com.fu.isyeri.services.abstracts.UserAuthService;

@Service
public class UserAuthManager implements UserAuthService{

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	public UserAuthManager(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	@Override
	public AuthResult authenticate(Credentials credentials) {
		User user = userRepository.findByUsername(credentials.getUsername());
		
		if (user == null) {
			throw new AuthException();
		}
		
		boolean matches = passwordEncoder.matches(credentials.getPassword(), user.getPassword());
		
		if(!matches) {
			throw new AuthException();
		}
		
		UserDto userDto = new UserDto(user);
		
		AuthResult result = new AuthResult();
		result.setUser(userDto);
		return result;
	}




}
