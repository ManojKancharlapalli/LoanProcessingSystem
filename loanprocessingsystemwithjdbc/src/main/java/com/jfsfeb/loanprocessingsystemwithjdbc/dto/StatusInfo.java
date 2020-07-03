package com.jfsfeb.loanprocessingsystemwithjdbc.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class StatusInfo implements Serializable {
	private int customerId;
	private Date interviewDate;
	private String status;
}// end of the class