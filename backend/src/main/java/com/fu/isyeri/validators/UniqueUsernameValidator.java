package com.fu.isyeri.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.fu.isyeri.annotations.UniqueUsername;
import com.fu.isyeri.entities.User;
import com.fu.isyeri.repository.UserRepository;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		User user = userRepository.findByUsername(username);
		return (user == null) ? true : false;
	}

}
