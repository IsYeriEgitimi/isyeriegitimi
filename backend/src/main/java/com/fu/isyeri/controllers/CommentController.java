package com.fu.isyeri.controllers;

import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fu.isyeri.annotations.CurrentUser;
import com.fu.isyeri.dto.CommentDto;
import com.fu.isyeri.entities.Comment;
import com.fu.isyeri.entities.User;
import com.fu.isyeri.result.DataResult;
import com.fu.isyeri.result.Result;
import com.fu.isyeri.services.abstracts.CommentService;

@RestController
@RequestMapping("/api/1.0/comment")
public class CommentController {

	private CommentService commentService;
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping("/company/{id}")
	public DataResult<Page<CommentDto>> findByCompany(@PathVariable int id, Pageable pageable) {
		return new DataResult<Page<CommentDto>>(commentService.findByCompany(id, pageable).map(CommentDto :: new), true);
	}
	
	@PostMapping("/add")
	@PreAuthorize("principal.role.level == 1")
	public Result add(@Valid @RequestBody Comment comment, @CurrentUser User user){
		commentService.add(comment, user);
		return new Result(true, "Yorum başarıyla eklendi.");
	}
	
}
