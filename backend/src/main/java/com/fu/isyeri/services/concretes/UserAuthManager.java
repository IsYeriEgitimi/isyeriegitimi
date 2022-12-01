package com.fu.isyeri.services.concretes;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fu.isyeri.dto.Credentials;
import com.fu.isyeri.dto.UserDto;
import com.fu.isyeri.entities.Token;
import com.fu.isyeri.entities.User;
import com.fu.isyeri.errors.AuthException;
import com.fu.isyeri.repository.TokenRepository;
import com.fu.isyeri.repository.UserRepository;
import com.fu.isyeri.result.AuthResult;
import com.fu.isyeri.services.abstracts.UserAuthService;

@Service
public class UserAuthManager implements UserAuthService{

	private UserRepository userRepository;
	private TokenRepository tokenRepository;
	private PasswordEncoder passwordEncoder;
	
	public UserAuthManager(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenRepository tokenRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenRepository = tokenRepository;
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
		
		String token = generateRandomToken();
		Token tokenEntity = new Token();
		tokenEntity.setToken(token);
		tokenEntity.setUser(user);
		tokenRepository.save(tokenEntity);
		
		AuthResult result = new AuthResult();
		result.setUser(userDto);
		result.setToken(token);
		return result;
	}


	private String generateRandomToken() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	@Transactional
	public UserDetails getUserDetails(String token) {
		Optional<Token> optionalToken = tokenRepository.findById(token);
		if (!optionalToken.isPresent()) {			
			return null;
		}
		return optionalToken.get().getUser();
	}
	public void clearToken(String token) {
		tokenRepository.deleteById(token);
	}

}
