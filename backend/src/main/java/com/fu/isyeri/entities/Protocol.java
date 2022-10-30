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
@Table(name = "Protocols")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "company" })
public class Protocol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "protocol_name")
	private String protocolName;
	
	@Column(name = "protocol_file_type") // PDF
	private String protocolFileType;

	@OneToOne(mappedBy = "protocol")
	private Company company;

}
