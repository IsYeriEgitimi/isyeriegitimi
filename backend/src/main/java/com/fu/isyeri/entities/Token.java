package com.fu.isyeri.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user" })
public class Token {

	@Id
	private String token;
	
	@ManyToOne
	private User user;
}
