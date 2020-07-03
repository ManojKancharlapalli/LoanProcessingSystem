package com.jfsfeb.loanprocessingsystem.dto;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class LoansInfo implements Serializable {
	private int loanId;
	private String loanType;
	private double loanAmount;
	private double loanInterest;
	private boolean isAvailable;

}