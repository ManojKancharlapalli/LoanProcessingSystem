package com.jfsfeb.loanprocessingsystemwithjdbc.service;

import java.util.List;

import com.jfsfeb.loanprocessingsystemwithjdbc.dao.LoanProcessingSystemDAO;
import com.jfsfeb.loanprocessingsystemwithjdbc.dto.CustomerInfo;
import com.jfsfeb.loanprocessingsystemwithjdbc.dto.EmployeeInfo;
import com.jfsfeb.loanprocessingsystemwithjdbc.dto.LoanInfo;
import com.jfsfeb.loanprocessingsystemwithjdbc.dto.StatusInfo;
import com.jfsfeb.loanprocessingsystemwithjdbc.exception.LPSException;
import com.jfsfeb.loanprocessingsystemwithjdbc.factory.LPSFactory;
import com.jfsfeb.loanprocessingsystemwithjdbc.validation.LoanProcessingSystemValidationImpl;

public class LoanProcessingSystemServiceImpl implements LoanProcessingSystemService {
	LoanProcessingSystemDAO dao = LPSFactory.getLoanProcessingSystemDAOImplInstance();
	LoanProcessingSystemValidationImpl validation = LPSFactory.getValidationInstance();

	@Override
	public EmployeeInfo adminLogin(EmployeeInfo employee) {
		if(validation.validateEmail(employee.getEmailId())) {
			if(validation.validatePassword(employee.getPassword())) {
				if(validation.validateRole(employee.getRole())) {
					return dao.adminLogin(employee);
				}
			}
		}
		throw new LPSException("Invalid credentials");			
				
					
	}

	@Override
	public List<LoanInfo> showLoansInfo() {
		return dao.showLoansInfo();
	}

	@Override
	public boolean updateLoan(int loanId, double loanInterest) {
		if(validation.validateId(loanId)) {
	    	 if(validation.validateLoanInterest(loanInterest)) {
	    		 return dao.updateLoan(loanId,loanInterest);
	    	 }
	     }
	     throw new LPSException("Invalid values");
			
		}

	@Override
	public boolean addLoan(LoanInfo loan) {
		if (validation.validateId(loan.getLoanId())) {
			if (validation.validateName(loan.getLoanType())) {
				if (validation.validateAmount(loan.getLoanAmount())) {
					if (validation.validateLoanInterest(loan.getLoanInterest())) {
						if (validation.validatePeriod(loan.getLoanPeriod())) {
							return dao.addLoan(loan);
						}
					}

				}
			}

		}

		throw new LPSException("Enter correct details");
	}


	@Override
	public boolean removeLoan(int loanId) {
		if (validation.validateId(loanId)) {
			return dao.removeLoan(loanId);
		}
		throw new LPSException("Enter valid Loan Id");
	}

	@Override
	public List<StatusInfo> viewAllStatus() {
		return dao.viewAllStatus();
	}

	@Override
	public boolean registerCustomer(CustomerInfo customer) {
		if (validation.validateId(customer.getCustomerId())) {
			if (validation.validateName(customer.getCustomerName())) {
				if (validation.validateEmail(customer.getCustomerEmailId())) {
					if (validation.validateMobile(customer.getMobileNumber())) {
						if (validation.validateAadharId(customer.getAadharNumber())) {
							if (validation.validateOccupation(customer.getOccupation())) {
								if (validation.validateAmount(customer.getIncome())) {
									if (validation.validateLoanType(customer.getLoanType())) {
										if (validation.validateAmount(customer.getLoanAmount())) {
											return dao.registerCustomer(customer);
										}
									}
								}
							}
						}
					}
				}
			}

		}
		throw new LPSException("Enter correct details");
	}

	@Override
	public StatusInfo getStatusById(int customerId) {
		if(validation.validateId(customerId)) {
			return dao.getStatusById(customerId);
			}
			throw new LPSException("No stataus found..");
		}

		

	@Override
	public List<CustomerInfo> showCustomerInfo() {
	   return dao.showCustomerInfo();
	}

	@Override
	public boolean updateStatus(StatusInfo status) {
		if(validation.validateId(status.getCustomerId())) {
	    	   if(validation.validateStatus(status.getStatus())) {
	    		   return dao.updateStatus(status);
	    	   }
	       }
			throw new LPSException("Invalid values");
		}


	@Override
	public List<CustomerInfo> getCustomerByLoanType(String loanType) {
		if (validation.validateLoanType(loanType)) {

			return dao.getCustomerByLoanType(loanType);
		}

		throw new LPSException("Invalid value..");

	}

}
