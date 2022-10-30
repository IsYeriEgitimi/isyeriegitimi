package com.fu.isyeri.services.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fu.isyeri.entities.Role;
import com.fu.isyeri.repository.RoleRepository;
import com.fu.isyeri.result.DataResult;
import com.fu.isyeri.result.Result;
import com.fu.isyeri.services.abstracts.RoleService;

@Service
public class RoleManager implements RoleService{
	
	private RoleRepository roleRepository;

	public RoleManager(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public DataResult<List<Role>> getAll() {
		return new DataResult<List<Role>>(roleRepository.findAll(), true, "Roller getirildi"); 
	}

	@Override
	public Result add(Role role) {
		roleRepository.save(role);
		return new Result(true, "Rol eklendi");
	}

}
