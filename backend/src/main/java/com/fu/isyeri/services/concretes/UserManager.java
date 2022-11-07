package com.fu.isyeri.services.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fu.isyeri.entities.User;
import com.fu.isyeri.repository.UserRepository;
import com.fu.isyeri.result.DataResult;
import com.fu.isyeri.result.Result;
import com.fu.isyeri.services.abstracts.UserService;

@Service
public class UserManager implements UserService{
	
	private UserRepository userRepository;

	public UserManager(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public DataResult<List<User>> getAll() {
		return new DataResult<List<User>>(userRepository.findAll(), true, "Kullanıcılar getirildi");
	}

	@Override
	public Result add(User user) {
		userRepository.save(user);
		return new Result(true, "Kullanıcı eklendi");
	}

	@Override
	public Result delete(int id) {
		userRepository.deleteById(id);
		return new Result(true, "Kullanıcı silindi");
	}

}
