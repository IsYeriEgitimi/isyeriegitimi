package com.fu.isyeri.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.fu.isyeri.annotations.UniqueLevel;
import com.fu.isyeri.entities.Role;
import com.fu.isyeri.repository.RoleRepository;

public class UniqueRoleLevelValidator implements ConstraintValidator<UniqueLevel, Byte>{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public boolean isValid(Byte level, ConstraintValidatorContext context) {
		
		Role role = roleRepository.findByLevel(level);
		return (role == null) ? true : false;
	}



}
