package com.jfsfeb.loanprocessingsystemwithhibernate.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@SuppressWarnings("serial")
@Entity
@Table(name = "statusInfo")
public class StatusInfo implements Serializable {
	@Id
	@Column
	private int customerId;
	@Column
	private Date interviewDate;
	@Column
	private String status;
}// end of the class