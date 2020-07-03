package com.jfsfeb.loanprocessingsystemwithhibernate.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@SuppressWarnings("serial")
@Entity
@Table(name = "lps_loans_info")
public class LoanInfo implements Serializable {
	@Id
	@Column
	private int loanId;
	@Column
	private String loanType;
	@Column
	private double loanAmount;
	@Column
	private double loanInterest;
	@Column
	private int loanPeriod;

}
