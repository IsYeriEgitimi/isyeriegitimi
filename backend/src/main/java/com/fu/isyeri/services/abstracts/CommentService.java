package com.fu.isyeri.services.abstracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fu.isyeri.entities.Comment;
import com.fu.isyeri.entities.User;

public interface CommentService {

	Page<Comment> findByCompany(int id, Pageable pageable);
	Comment add(Comment comment, User user);
}
