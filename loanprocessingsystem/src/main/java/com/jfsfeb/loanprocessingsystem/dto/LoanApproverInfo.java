package com.jfsfeb.loanprocessingsystem.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@SuppressWarnings("serial")
public class LoanApproverInfo implements Serializable {

	private String approverEmailId;
	@ToString.Exclude
	private String approverPassword;

}
