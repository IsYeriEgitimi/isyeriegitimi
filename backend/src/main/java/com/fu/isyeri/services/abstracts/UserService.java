package com.fu.isyeri.services.abstracts;

import java.util.List;

import com.fu.isyeri.entities.User;
import com.fu.isyeri.result.DataResult;
import com.fu.isyeri.result.Result;

public interface UserService {

	DataResult<List<User>> getAll();
	
	Result add(User user);
	
	Result delete(int id);
}
