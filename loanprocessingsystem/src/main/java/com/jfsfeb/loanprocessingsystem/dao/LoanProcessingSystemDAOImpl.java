package com.jfsfeb.loanprocessingsystem.dao;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import com.jfsfeb.loanprocessingsystem.repository.DataBase;
import com.jfsfeb.loanprocessingsystem.dto.AdminInfo;
import com.jfsfeb.loanprocessingsystem.dto.LoansInfo;
import com.jfsfeb.loanprocessingsystem.dto.RequestInfo;
import com.jfsfeb.loanprocessingsystem.dto.CustomersInfo;
import com.jfsfeb.loanprocessingsystem.dto.LoanApproverInfo;
import com.jfsfeb.loanprocessingsystem.exception.LPSException;

public class LoanProcessingSystemDAOImpl implements LoanProcessingSystemDAO {

	public boolean register(CustomersInfo customer) {
		for (CustomersInfo customerBean : DataBase.CUSTOMERDB) {
			if ((customerBean.getCustomerId() == customer.getCustomerId())
					|| (customerBean.getCustomerEmailId().equals(customer.getCustomerEmailId())
							|| (customerBean.getAadharNumber() == customer.getAadharNumber()))) {
				throw new LPSException(
						"Cannot Register, As Customer Already Exists with same Id or EmailId or AadharNumber");
			}
		}

		DataBase.CUSTOMERDB.add(customer);
		return true;
	}

	public boolean adminLogin(String adminEmailId, String adminPassword) {

		for (AdminInfo adminInfo : DataBase.ADMINDB) {
			if (adminInfo.getAdminEmailId().equalsIgnoreCase(adminEmailId)
					&& adminInfo.getAdminPassword().equals(adminPassword)) {
				return true;
			}
		}
		throw new LPSException("Invalid Admin Credentials");
	}

	public boolean approverLogin(String approverEmailId, String approverPassword) {

		for (LoanApproverInfo approverInfo : DataBase.APPROVERDB) {
			if (approverInfo.getApproverEmailId().equalsIgnoreCase(approverEmailId)
					&& approverInfo.getApproverPassword().equals(approverPassword)) {
				return true;
			}
		}
		throw new LPSException("Invalid Loan Approver Credentials");
	}

	public boolean addLoan(LoansInfo loan) {
		for (LoansInfo loanBean : DataBase.LOANDB) {
			if (loanBean.getLoanId() == loan.getLoanId()) {
				throw new LPSException("Cannot Add loan, As Loan Id Already Exists");
			}
		}
		DataBase.LOANDB.add(loan);
		return true;
	}

	public boolean deleteLoan(int loanId) {
		for (LoansInfo loanInfo : DataBase.LOANDB) {
			if (loanInfo.getLoanId() == loanId) {
				DataBase.LOANDB.remove(loanInfo);
				return true;
			}
		}
		throw new LPSException("Cannot Remove The loan, As loan Id Not Found");
	}

	public List<LoansInfo> showLoansInfo() {
		List<LoansInfo> loansList = new LinkedList<LoansInfo>();

		for (LoansInfo loan : DataBase.LOANDB) {
			loan.getLoanId();
			loan.getLoanType();
			loan.getLoanAmount();
			loan.getLoanInterest();
			loan.isAvailable();
			loansList.add(loan);
		}

		if (loansList.isEmpty()) {
			throw new LPSException("loans not Found ");
		} else {
			return loansList;
		}

	}

	public List<LoansInfo> searchLoans(LoansInfo loanInfo) {
		List<LoansInfo> loansList = new LinkedList<LoansInfo>();

		for (LoansInfo loanBean : DataBase.LOANDB) {

			if (loanBean.getLoanId() == loanInfo.getLoanId()) {
				loansList.add(loanBean);
			} else if (loanBean.getLoanType().equalsIgnoreCase(loanInfo.getLoanType())) {
				loansList.add(loanBean);
			}
		}

		if (loansList.isEmpty()) {
			throw new LPSException("Loan Not Found");
		} else {
			return loansList;
		}

	}

	public List<RequestInfo> searchRequestInfo(int customerId) {
		List<RequestInfo> requestList = new LinkedList<RequestInfo>();

		for (RequestInfo requestBean : DataBase.REQUESTDB) {

			if (requestBean.getCustomerId() == customerId) {
				requestList.add(requestBean);
			}
		}

		if (requestList.isEmpty()) {
			throw new LPSException("Loan Not Found");
		} else {
			return requestList;
		}

	}

	public List<CustomersInfo> showCustomerInfo() {
		List<CustomersInfo> customerList = new LinkedList<CustomersInfo>();

		for (CustomersInfo customerInfo : DataBase.CUSTOMERDB) {
			customerInfo.getCustomerId();
			customerInfo.getCustomerName();
			customerInfo.getCustomerEmailId();
			customerInfo.getMobileNumber();
			customerInfo.getAadharNumber();
			customerInfo.getOccupation();
			customerInfo.getIncome();
			customerInfo.getNoOfLoansApplied();
			customerList.add(customerInfo);
		}

		if (customerList.isEmpty()) {
			throw new LPSException("No Customer Found");
		} else {
			return customerList;
		}

	}

	public List<RequestInfo> showRequestsInfo() {
		List<RequestInfo> requestsList = new LinkedList<RequestInfo>();

		for (RequestInfo requestInfo : DataBase.REQUESTDB) {
			requestInfo.getLoanId();
			requestInfo.getCustomerId();
			requestInfo.isAccepted();
			requestsList.add(requestInfo);
		}

		if (requestsList.isEmpty()) {
			throw new LPSException("No Requests Placed");
		} else {
			return requestsList;
		}

	}

	public boolean loanRequest(int customerId, int loanId) {
		boolean isRequestExists = false;
		RequestInfo requestInfo = new RequestInfo();

		for (RequestInfo requestInfo1 : DataBase.REQUESTDB) {
			if (loanId == requestInfo1.getLoanId()) {
				isRequestExists = true;
			}
		}

		if (!isRequestExists) {
			for (CustomersInfo customer : DataBase.CUSTOMERDB) {
				if (customerId == customer.getCustomerId()) {
					for (LoansInfo loan : DataBase.LOANDB) {
						if ((loan.getLoanId() == loanId) && (loan.isAvailable() == true)) {
							requestInfo.setCustomerId(customerId);
							requestInfo.setLoanId(loanId);
							requestInfo.setAccepted(false);
							DataBase.REQUESTDB.add(requestInfo);
							return true;
						}
					}
				}
			}
		}

		throw new LPSException("Invalid Request");
	}

	public boolean loanApproval(int customerId, int loanId, LocalDate interviewDate) {
		RequestInfo requestInfo = new RequestInfo();
		CustomersInfo customer = new CustomersInfo();
		LoansInfo loan = new LoansInfo();
		int noOfLoansApplied = 0;
		boolean isValidReq = false;

		for (RequestInfo requestInfo2 : DataBase.REQUESTDB) {
			if (requestInfo2.getCustomerId() == customerId) {
				if ((requestInfo2.getLoanId() == loanId) && (interviewDate != null)
						&& (requestInfo2.isAccepted() == false)) {
					isValidReq = true;
					requestInfo = requestInfo2;
				}
			}
		}

		if (isValidReq) {
			for (LoansInfo loanInfo : DataBase.LOANDB) {
				if (loanInfo.getLoanId() == loanId) {
					loan = loanInfo;
				}
			}

			for (CustomersInfo customerInfo : DataBase.CUSTOMERDB) {
				if (customerInfo.getCustomerId() == customerId) {
					customer = customerInfo;
					noOfLoansApplied = customer.getNoOfLoansApplied();
				}
			}

			if (noOfLoansApplied < 3) {
				loan.setAvailable(false);
				noOfLoansApplied++;
				customer.setNoOfLoansApplied(noOfLoansApplied);
				requestInfo.setAccepted(true);
				requestInfo.setInterviewDate(interviewDate);
				return true;
			} else {
				DataBase.REQUESTDB.remove(requestInfo);
				throw new LPSException("Loan Cannot be Issued, as Customer Exceeds maximum limit of loans ");
			}

		} else {
			throw new LPSException("Loan Cannot be Issued, as Invalid CustomerID/LoanId/InterviewDate");

		}
	}

}