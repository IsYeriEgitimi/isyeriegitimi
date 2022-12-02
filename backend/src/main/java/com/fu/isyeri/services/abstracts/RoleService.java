package com.fu.isyeri.services.abstracts;

import java.util.List;

import com.fu.isyeri.entities.Role;
import com.fu.isyeri.result.DataResult;
import com.fu.isyeri.result.Result;

public interface RoleService {
	DataResult<List<Role>> getAll();
	
	Result add(Role role);
	
	Result delete(int id);
	
	Role update(Role updatedRole, int roleId);
}
