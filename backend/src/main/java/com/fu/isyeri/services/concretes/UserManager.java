package com.fu.isyeri.services.concretes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fu.isyeri.dto.UserUpdateDto;
import com.fu.isyeri.entities.User;
import com.fu.isyeri.errors.NotFoundException;
import com.fu.isyeri.repository.UserRepository;
import com.fu.isyeri.result.Result;
import com.fu.isyeri.services.abstracts.UserService;

@Service
public class UserManager implements UserService{
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	public UserManager(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Page<User> getAll(Pageable page) {
		return userRepository.findAll(page);
	}
	
	@Override
	public Result add(User user) {
		String encryptedPassword = this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		userRepository.save(user);
		return new Result(true, "Kullanıcı eklendi");
	}

	@Override
	public Result delete(String usernname) {
		User user = getUserByUsername(usernname);
		userRepository.delete(user);
		return new Result(true, "Kullanıcı silindi");
	}

	@Override
	public User getUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new NotFoundException();
		}
		return user;
	}
	
	@Override
	public User update(String username, UserUpdateDto userUpdateDto) {
		User user =  getUserByUsername(username);
		user.setDisplayName(userUpdateDto.getDisplayName());
		user.setRole(userUpdateDto.getRole());
		return userRepository.save(user);
	}

}
