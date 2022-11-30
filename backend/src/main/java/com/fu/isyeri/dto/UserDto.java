package com.fu.isyeri.dto;

import com.fu.isyeri.entities.Role;
import com.fu.isyeri.entities.User;

import lombok.Data;

@Data
public class UserDto {
	
	private String username;
	
	private String displayName;
	
	private Role role; 
	
	public UserDto(User user) {
		this.setUsername(user.getUsername());
		this.setDisplayName(user.getDisplayName());
		this.setRole(user.getRole());
	}
}
