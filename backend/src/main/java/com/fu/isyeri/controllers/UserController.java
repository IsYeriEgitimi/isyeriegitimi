package com.fu.isyeri.controllers;

import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fu.isyeri.dto.UserDto;
import com.fu.isyeri.entities.User;
import com.fu.isyeri.result.DataResult;
import com.fu.isyeri.result.Result;
import com.fu.isyeri.services.abstracts.UserService;

@RestController
@RequestMapping("/api/1.0/user")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/getAll")
	public DataResult<Page<UserDto>> getAll(Pageable page){
		return new DataResult<Page<UserDto>>(userService.getAll(page).map(UserDto::new), true, "Kullanıcı listelendi");
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody User user){
		return userService.add(user);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestParam int id) {
		return userService.delete(id);
	}
	
}
