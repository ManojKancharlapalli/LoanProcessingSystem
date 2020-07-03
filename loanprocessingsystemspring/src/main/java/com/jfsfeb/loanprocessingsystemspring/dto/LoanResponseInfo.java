package com.jfsfeb.loanprocessingsystemspring.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@JsonRootName("loanResponse")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class LoanResponseInfo {
	
	private boolean error;
	private String message;
	private List<LoanInfo> data;

}
