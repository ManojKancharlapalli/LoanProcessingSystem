package com.jfsfeb.loanprocessingsystemwithjdbc.service;

import java.util.List;

import com.jfsfeb.loanprocessingsystemwithjdbc.dto.CustomerInfo;
import com.jfsfeb.loanprocessingsystemwithjdbc.dto.EmployeeInfo;
import com.jfsfeb.loanprocessingsystemwithjdbc.dto.LoanInfo;
import com.jfsfeb.loanprocessingsystemwithjdbc.dto.StatusInfo;

public interface LoanProcessingSystemService {
	//admin
		public EmployeeInfo adminLogin(EmployeeInfo employee);
		public List<LoanInfo> showLoansInfo();
		public boolean updateLoan(int loanId,double loanInterest);
		public boolean addLoan(LoanInfo loan);
		public boolean removeLoan(int loanId);
		public List<StatusInfo> viewAllStatus();
		
		//customer
		public boolean registerCustomer(CustomerInfo customer);
		public StatusInfo getStatusById(int customerId);
		
		//LAD
		public List<CustomerInfo> showCustomerInfo();
		public boolean updateStatus(StatusInfo status);
		public List<CustomerInfo> getCustomerByLoanType(String loanType);
}
