package com.fu.isyeri.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private int id;
	
	@Column(name = "Province")
	private String province;
	
	@Column(name = "District")
	private String district;
	
	@Column(name = "Neighborhood")
	private String neighborhood;
	
	@Column(name = "Road")
	private String road;
	
	@Column(name = "No")
	private String no;
	
}
