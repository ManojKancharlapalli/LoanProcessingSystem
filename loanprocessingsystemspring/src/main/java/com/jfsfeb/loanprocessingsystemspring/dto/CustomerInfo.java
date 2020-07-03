package com.jfsfeb.loanprocessingsystemspring.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@SuppressWarnings("serial")
@Entity
@Table(name = "lps_customers_info")
public class CustomerInfo implements Serializable {
    @Id
	@Column
	private int customerId;
    @Column
	private String customerName;
    @Column
	private String customerEmailId;
    @Column
	private long mobileNumber;
    @Column
	private long aadharNumber;
    @Column
	private String occupation;
    @Column
	private double income;
    @Column
	private String loanType;
    @Column
	private double loanAmount;
}