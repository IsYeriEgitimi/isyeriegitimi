package com.fu.isyeri.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fu.isyeri.entities.Role;
import com.fu.isyeri.result.DataResult;
import com.fu.isyeri.result.Result;
import com.fu.isyeri.services.abstracts.RoleService;

@RestController
@RequestMapping("/api/1.0/role")
public class RoleController {

	private RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<Role>> getAll(){
		return roleService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Role role){
		return roleService.add(role);
	}
}
