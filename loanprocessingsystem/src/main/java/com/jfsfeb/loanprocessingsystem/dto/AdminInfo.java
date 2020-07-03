package com.jfsfeb.loanprocessingsystem.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@SuppressWarnings("serial")
public class AdminInfo implements Serializable {

	private String adminEmailId;
	@ToString.Exclude
	private String adminPassword;

}
