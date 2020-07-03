package com.jfsfeb.loanprocessingsystemwithjdbc.dto;

import java.io.Serializable;

import lombok.Data;


@SuppressWarnings("serial")
@Data

public class EmployeeInfo implements Serializable {
	
		private String emailId;
		private String password;
		private String role;		
	}