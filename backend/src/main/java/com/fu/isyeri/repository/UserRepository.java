package com.fu.isyeri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fu.isyeri.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
}
