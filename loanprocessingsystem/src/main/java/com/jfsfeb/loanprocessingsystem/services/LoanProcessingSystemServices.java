package com.jfsfeb.loanprocessingsystem.services;

import java.time.LocalDate;
import java.util.List;

import com.jfsfeb.loanprocessingsystem.dto.CustomersInfo;
import com.jfsfeb.loanprocessingsystem.dto.LoansInfo;
import com.jfsfeb.loanprocessingsystem.dto.RequestInfo;

public interface LoanProcessingSystemServices {
	boolean register(CustomersInfo customer);

	boolean adminLogin(String adminEmailId, String adminPassword);

	boolean approverLogin(String approverEmailId, String approverPassword);

	boolean addLoan(LoansInfo loan);

	boolean deleteLoan(int loanId);

	List<LoansInfo> showLoansInfo();

	List<CustomersInfo> showCustomerInfo();

	List<LoansInfo> searchLoans(LoansInfo Info);

	List<RequestInfo> showRequestsInfo();
	
	List<RequestInfo> searchRequestInfo(int customerId);

	boolean loanApproval(int customerId, int loanId,LocalDate interviewDate);

	boolean loanRequest(int customerId, int loanId);
	
	List<LoansInfo> searchLoansByType(LoansInfo Info);

}
