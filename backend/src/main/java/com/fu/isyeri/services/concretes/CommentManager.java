package com.fu.isyeri.services.concretes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.fu.isyeri.entities.Comment;
import com.fu.isyeri.entities.Company;
import com.fu.isyeri.entities.User;
import com.fu.isyeri.repository.CommentRepository;
import com.fu.isyeri.repository.CompanyRepository;
import com.fu.isyeri.services.abstracts.CommentService;

@Service
public class CommentManager implements CommentService{
	
	private CommentRepository commentRepository;
	private CompanyRepository companyRepository;

	public CommentManager(CommentRepository commentRepository, CompanyRepository companyRepository) {
		this.commentRepository = commentRepository;
		this.companyRepository = companyRepository;
	}

	@Override
	public Page<Comment> findByCompany(int companyId, Pageable pageable) {
		Company company = companyRepository.findById(companyId).get();
		return commentRepository.findByCompanyOrderByIdDesc(company, pageable);
	}

	@Override
	public Comment add(Comment comment, User user) {
		comment.setUser(user);
		return commentRepository.save(comment);
	}

}
