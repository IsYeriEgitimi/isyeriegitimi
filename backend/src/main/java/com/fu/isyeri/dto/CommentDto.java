package com.fu.isyeri.dto;

import com.fu.isyeri.entities.Comment;
import com.fu.isyeri.entities.Company;

import lombok.Data;

@Data
public class CommentDto {
	
	private int id;
	
	private String comment;
	
	private Long timestamp;
	
	private UserDto user;
	
	private Company company;
	
	public CommentDto(Comment comment) {
		this.setId(comment.getId());
		this.setComment(comment.getComment());
		this.setTimestamp(comment.getTimestamp().getTime());
		this.setUser(new UserDto(comment.getUser()));
		this.setCompany(comment.getCompany());
	}
}
