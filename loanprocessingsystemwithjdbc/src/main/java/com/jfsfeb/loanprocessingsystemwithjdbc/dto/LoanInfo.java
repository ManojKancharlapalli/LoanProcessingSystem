package com.jfsfeb.loanprocessingsystemwithjdbc.dto;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class LoanInfo implements Serializable {
	private int loanId;
	private String loanType;
	private double loanAmount;
	private double loanInterest;
	private int loanPeriod;

}
