package com.fu.isyeri.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@PreAuthorize("principal.role.level == 2") // Level 2 -> Admin
	public Result add(@Valid @RequestBody Role role){
		return roleService.add(role);
	}
	
	@DeleteMapping("/delete")
	@PreAuthorize("principal.role.level == 2")
	public Result delete(@RequestParam int id) {
		return roleService.delete(id);
	}
	
	@PutMapping("/update/{roleId}")
	@PreAuthorize("principal.role.level == 2")
	public DataResult<Role> update(@Valid @RequestBody Role updatedRole, @PathVariable int roleId) {
		return new DataResult<Role>(roleService.update(updatedRole, roleId), true, "Rol güncellendi");
	}
}
