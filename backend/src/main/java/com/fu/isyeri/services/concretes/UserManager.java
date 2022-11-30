package com.fu.isyeri.services.concretes;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fu.isyeri.entities.User;
import com.fu.isyeri.repository.UserRepository;
import com.fu.isyeri.result.DataResult;
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
	public DataResult<List<User>> getAll() {
		return new DataResult<List<User>>(userRepository.findAll(), true, "Kullanıcılar getirildi");
	}

	@Override
	public Result add(User user) {
		String encryptedPassword = this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		userRepository.save(user);
		return new Result(true, "Kullanıcı eklendi");
	}

	@Override
	public Result delete(int id) {
		userRepository.deleteById(id);
		return new Result(true, "Kullanıcı silindi");
	}

}
