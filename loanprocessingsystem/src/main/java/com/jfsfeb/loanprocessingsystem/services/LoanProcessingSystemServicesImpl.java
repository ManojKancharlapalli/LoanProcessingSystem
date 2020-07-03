package com.jfsfeb.loanprocessingsystem.services;

import java.time.LocalDate;
import java.util.List;

import com.jfsfeb.loanprocessingsystem.dao.LoanProcessingSystemDAO;
import com.jfsfeb.loanprocessingsystem.dto.LoansInfo;
import com.jfsfeb.loanprocessingsystem.dto.RequestInfo;
import com.jfsfeb.loanprocessingsystem.exception.LPSException;
import com.jfsfeb.loanprocessingsystem.factory.LPSFactory;
import com.jfsfeb.loanprocessingsystem.validation.LoanProcessingSystemValidation;
import com.jfsfeb.loanprocessingsystem.dto.CustomersInfo;

public class LoanProcessingSystemServicesImpl implements LoanProcessingSystemServices {
	LoanProcessingSystemDAO dao = LPSFactory.getLoanProcessingSystemDAOImplInstance();
	LoanProcessingSystemValidation validation = LPSFactory.getValidationInstance();

	public boolean register(CustomersInfo customer) {
		if (validation.validateId(customer.getCustomerId())) {
			if (validation.validateName(customer.getCustomerName())) {
				if (validation.validateEmail(customer.getCustomerEmailId())) {
					if (validation.validatedMobileNumber(customer.getMobileNumber())) {
						if (validation.validatedAadharNumber(customer.getAadharNumber())) {
							if (validation.validateName(customer.getOccupation())) {
								if (validation.validateAmount(customer.getIncome())) {
									return dao.register(customer);
								}
							}
						}
					}

				}
			}

		}
		throw new LPSException("Enter correct details");
	}

	public boolean adminLogin(String adminEmailId, String adminPassword) {
		if (adminEmailId != null && adminPassword != null) {
			if (validation.validateEmail(adminEmailId)) {
				if (validation.validatePassword(adminPassword)) {
					return dao.adminLogin(adminEmailId, adminPassword);
				}
			}
		}
		throw new LPSException("Enter proper EmailId and Password");
	}

	public boolean approverLogin(String approverEmailId, String approverPassword) {
		if (approverEmailId != null && approverPassword != null) {
			if (validation.validateEmail(approverEmailId)) {
				if (validation.validatePassword(approverPassword)) {
					return dao.approverLogin(approverEmailId, approverPassword);
				}
			}
		}
		throw new LPSException("Enter proper EmailId and Password");
	}

	public boolean addLoan(LoansInfo loan) {
		if (validation.validateId(loan.getLoanId())) {
			if (validation.validateName(loan.getLoanType())) {
				if (validation.validateAmount(loan.getLoanAmount())) {
					if (validation.validateIntrest(loan.getLoanInterest())) {
						if (loan.isAvailable() == true || loan.isAvailable() == false) {
							return dao.addLoan(loan);
						}
					}

				}
			}

		}
		throw new LPSException("Enter correct details");
	}

	public boolean deleteLoan(int loanId) {
		if (validation.validateId(loanId)) {
			return dao.deleteLoan(loanId);
		}
		throw new LPSException("Enter valid Loan Id");
	}

	public List<LoansInfo> showLoansInfo() {
		return dao.showLoansInfo();
	}

	public List<CustomersInfo> showCustomerInfo() {
		return dao.showCustomerInfo();
	}

	public List<LoansInfo> searchLoans(LoansInfo loanInfo) {
		if (validation.validateId(loanInfo.getLoanId())) {
			return dao.searchLoans(loanInfo);
		}

		
		throw new LPSException("Enter valid Loan details ");
	}

	public List<RequestInfo> searchRequestInfo(int customerId) {
		if (validation.validateId(customerId)) {
			return dao.searchRequestInfo(customerId);
		}
		throw new LPSException("Enter valid Customer Id");
	}

	public List<RequestInfo> showRequestsInfo() {
		return dao.showRequestsInfo();
	}

	public boolean loanRequest(int customerId, int loanId) {
		if(validation.validateId(customerId)) {
			if(validation.validateId(loanId))
				return dao.loanRequest(customerId, loanId);
		}
		throw new LPSException("Enter valid Customer Id/Loan Id");
	}

	public boolean loanApproval(int customerId, int loanId, LocalDate interviewDate) {
		if(validation.validateId(customerId)) {
			if(validation.validateId(loanId)) {
				return dao.loanApproval(customerId, loanId, interviewDate);
			}
		}
		throw new LPSException("Enter valid Details");
	}

	public List<LoansInfo> searchLoansByType(LoansInfo Info) {
		// TODO Auto-generated method stub
		if (validation.validateName(Info.getLoanType())) {

			return dao.searchLoans(Info);
		}
		throw new LPSException("Enter valid Loan Type");
	}

}
