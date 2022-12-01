package com.fu.isyeri.services.abstracts;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.fu.isyeri.dto.UserUpdateDto;
import com.fu.isyeri.entities.User;
import com.fu.isyeri.result.Result;

public interface UserService {

	Page<User> getAll(Pageable page);
	
	Result add(User user);
	
	Result delete(String username);
	
	User getUserByUsername(String username);
	
	User update(String username, UserUpdateDto userUpdateDto); 
}
