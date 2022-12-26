package com.fu.isyeri.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fu.isyeri.entities.Comment;
import com.fu.isyeri.entities.Company;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	Page<Comment> findByCompany(Company company, Pageable pageable);
}
