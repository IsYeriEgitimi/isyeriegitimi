package com.fu.isyeri.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Entity
@Data
@Table(name = "Addresses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "company" })
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "province")
	private String province;
	
	@Column(name = "district")
	private String district;
	
	@Column(name = "neighborhood")
	private String neighborhood;
	
	@Column(name = "road")
	private String road;
	
	@Column(name = "no")
	private String no;
	
	@OneToOne(mappedBy = "address")
	private Company company;
	
}
