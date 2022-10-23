package com.fu.isyeri.entities;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Company")
@NoArgsConstructor
@AllArgsConstructor
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private int id;
	
	@Column(name = "CompanyName")
	private String companyName;
	
	@Column(name = "CompanyEmail")
	private String companyEmail;
	
	@Column(name = "CompanyPhone")
	private String companyPhone;
	
	@OneToOne(cascade = CascadeType.ALL , orphanRemoval = true)
	private Protocol protocol;
	
	@OneToOne(cascade = CascadeType.ALL , orphanRemoval = true)
	private Address address;
	
}
