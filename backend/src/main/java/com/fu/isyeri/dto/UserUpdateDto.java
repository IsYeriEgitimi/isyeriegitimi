package com.fu.isyeri.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fu.isyeri.entities.Role;
import lombok.Data;

@Data
public class UserUpdateDto {

	@NotNull(message = "{fu.constraint.displayName.NotNull.message}")
	@Size(min = 5, max = 255)
	private String displayName;
	
	private Role role;
	
}
