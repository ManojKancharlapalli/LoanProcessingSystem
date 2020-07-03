package com.jfsfeb.loanprocessingsystemwithhibernate.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "lps_users")
public class EmployeeInfo implements Serializable {
	@Id
	@Column
	private int id;
	@Column
	private String name;
	@Column
	private String emailId;
	@Column
	private String password;
	@Column
	private String role;
}