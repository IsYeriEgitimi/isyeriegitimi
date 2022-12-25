package com.fu.isyeri.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "student_full_name")
	private String studentFullName;
	
	@Column(name = "student_no")
	private String studentNo;
	
	@Column(name = "student_company")
	private String studentCompany;
	
	@Column(name = "student_date")
	@Temporal(TemporalType.DATE)
	private Date studentDate;
	
	
}
