package com.jfsfeb.loanprocessingsystemwithjdbc.dto;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class CustomerInfo implements Serializable {

	private int customerId;
	private String customerName;
	private String customerEmailId;
	private long mobileNumber;
	private long aadharNumber;
	private String occupation;
	private double income;
	private String loanType;
	private double loanAmount;
}