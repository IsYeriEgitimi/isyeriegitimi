package com.fu.isyeri.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fu.isyeri.annotations.UniqueLevel;

import lombok.Data;

@Entity
@Data
@Table(name = "Roles")
public class Role {

	@Id 
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "Name")
	private String name;
	
	@UniqueLevel
	@Column(name = "Level")
	private byte level;
	
}
