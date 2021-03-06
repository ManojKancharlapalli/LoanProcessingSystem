package com.jfsfeb.loanprocessingsystemwithhibernate.dao;

import java.util.List;

import com.jfsfeb.loanprocessingsystemwithhibernate.dto.CustomerInfo;
import com.jfsfeb.loanprocessingsystemwithhibernate.dto.EmployeeInfo;
import com.jfsfeb.loanprocessingsystemwithhibernate.dto.LoanInfo;
import com.jfsfeb.loanprocessingsystemwithhibernate.dto.StatusInfo;

public interface LoanProcessingSystemDAO {
	
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
