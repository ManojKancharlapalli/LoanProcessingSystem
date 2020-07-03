package com.jfsfeb.loanprocessingsystem.factory;

import com.jfsfeb.loanprocessingsystem.dao.LoanProcessingSystemDAO;
import com.jfsfeb.loanprocessingsystem.dao.LoanProcessingSystemDAOImpl;
import com.jfsfeb.loanprocessingsystem.services.LoanProcessingSystemServices;
import com.jfsfeb.loanprocessingsystem.services.LoanProcessingSystemServicesImpl;
import com.jfsfeb.loanprocessingsystem.validation.LoanProcessingSystemValidation;

public class LPSFactory {
  
	private LPSFactory() {
		
	}
	public static LoanProcessingSystemDAO getLoanProcessingSystemDAOImplInstance() {
		LoanProcessingSystemDAO dao = new LoanProcessingSystemDAOImpl();
		return dao;
	}
	public static LoanProcessingSystemServices getLoanProcessingSystemServicesImplInstance() {
		LoanProcessingSystemServices servicesDao = new LoanProcessingSystemServicesImpl();
		return servicesDao;
	}
	public static LoanProcessingSystemValidation getValidationInstance() {
		LoanProcessingSystemValidation validation = new LoanProcessingSystemValidation();
		return validation;
	}
}
