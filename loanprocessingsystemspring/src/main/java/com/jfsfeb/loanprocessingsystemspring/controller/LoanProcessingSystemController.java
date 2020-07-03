package com.jfsfeb.loanprocessingsystemspring.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jfsfeb.loanprocessingsystemspring.dto.CustomerInfo;
import com.jfsfeb.loanprocessingsystemspring.dto.LoanInfo;
import com.jfsfeb.loanprocessingsystemspring.dto.LoanResponseInfo;
import com.jfsfeb.loanprocessingsystemspring.service.LoanProcessingSystemService;

@RestController
public class LoanProcessingSystemController {
	private LoanProcessingSystemService loanService;
	
	@PostMapping(path ="/addLoan",consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public void addLoan(@RequestBody LoanInfo loan) {
	boolean isAdded = loanService.addLoan(loan);
	LoanResponseInfo loanResponse = new LoanResponseInfo();
	if(isAdded) {
		loanResponse.setMessage("Loan added Successfully");
	}else {
		loanResponse.setError(true);
		loanResponse.setMessage("Unable to add loans");
	}
	}
    
	@GetMapping(path ="/getCustomerByLoanType",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public void getCustomerByLoanType(String loanType) {
		List<CustomerInfo> info = loanService.getCustomerByLoanType(loanType);
		LoanResponseInfo loanResponse = new LoanResponseInfo();
		if(info !=null && !info.isEmpty()) {
			loanResponse.setMessage("Customer Applied for" + loanType);
			//loanResponse.setData(info);
		}else {
			loanResponse.setError(true);
			loanResponse.setMessage("No Customer Applied for" + loanType);
		}
		}
	}

