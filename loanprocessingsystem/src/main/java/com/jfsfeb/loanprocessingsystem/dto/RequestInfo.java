package com.jfsfeb.loanprocessingsystem.dto;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
@SuppressWarnings("serial")
public class RequestInfo implements Serializable {
	private int customerId;
	private int loanId;
	private boolean isAccepted;
	private LocalDate interviewDate;
	}// end of the class
