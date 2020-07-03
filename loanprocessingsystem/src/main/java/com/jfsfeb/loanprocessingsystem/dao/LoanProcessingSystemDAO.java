package com.jfsfeb.loanprocessingsystem.dao;

import java.time.LocalDate;
import java.util.List;

import com.jfsfeb.loanprocessingsystem.dto.LoansInfo;
import com.jfsfeb.loanprocessingsystem.dto.RequestInfo;
import com.jfsfeb.loanprocessingsystem.dto.CustomersInfo;

public interface LoanProcessingSystemDAO {
	boolean register(CustomersInfo customer);

	boolean adminLogin(String adminEmailId, String adminPassword);

	boolean approverLogin(String approverEmailId, String approverPassword);

	boolean addLoan(LoansInfo loan);

	boolean deleteLoan(int loanId);

	List<LoansInfo> searchLoans(LoansInfo Info);

	List<LoansInfo> showLoansInfo();

	List<CustomersInfo> showCustomerInfo();

	List<RequestInfo> showRequestsInfo();
	
	List<RequestInfo> searchRequestInfo(int customerId);

	boolean loanApproval(int customerId, int loanId,LocalDate interviewDate );

	boolean loanRequest(int customerId, int loanId);

}
