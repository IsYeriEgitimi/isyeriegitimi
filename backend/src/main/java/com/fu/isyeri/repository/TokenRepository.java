package com.fu.isyeri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fu.isyeri.entities.Token;

public interface TokenRepository extends JpaRepository<Token, String>{

}
