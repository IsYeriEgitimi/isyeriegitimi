package com.fu.isyeri.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Companies")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "comment" })
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "company_email")
	private String companyEmail;
	
	@Column(name = "company_phone")
	private String companyPhone;
	
	@Column(name = "company_image")
	private String image;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Protocol protocol;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Address address;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Comment> comment;
	
}
