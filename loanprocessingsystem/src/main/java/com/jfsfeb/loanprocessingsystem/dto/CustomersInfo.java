package com.jfsfeb.loanprocessingsystem.dto;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class CustomersInfo implements Serializable {

	private int customerId;
	private String customerName;
	private String customerEmailId;
	private long mobileNumber;
	private long aadharNumber;
	private String occupation;
	private double income;
	private int noOfLoansApplied;
	

}
