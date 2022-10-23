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
@Table(name = "Protocol")
public class Protocol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "protocol_id")
	private int id;
	
	@Column(name = "ProtocolName")
	private String protocolName;
	
	@Column(name = "ProtocolFileType") // PDF
	private String protocolFileType;


}
