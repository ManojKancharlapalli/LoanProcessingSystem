package com.jfsfeb.loanprocessingsystemwithjdbc.factory;

import com.jfsfeb.loanprocessingsystemwithjdbc.dao.LoanProcessingSystemDAO;
import com.jfsfeb.loanprocessingsystemwithjdbc.dao.LoanProcessingSystemDAOImpl;
import com.jfsfeb.loanprocessingsystemwithjdbc.service.LoanProcessingSystemService;
import com.jfsfeb.loanprocessingsystemwithjdbc.service.LoanProcessingSystemServiceImpl;

import com.jfsfeb.loanprocessingsystemwithjdbc.validation.LoanProcessingSystemValidationImpl;

public class LPSFactory {
  
	private LPSFactory() {
		
	}
	public static LoanProcessingSystemDAO getLoanProcessingSystemDAOImplInstance() {
		LoanProcessingSystemDAO dao = new LoanProcessingSystemDAOImpl();
		return dao;
	}
	public static LoanProcessingSystemService getLoanProcessingSystemServicesImplInstance() {
		LoanProcessingSystemService serviceDao = new LoanProcessingSystemServiceImpl();
		return serviceDao;
	}
	public static LoanProcessingSystemValidationImpl getValidationInstance() {
		LoanProcessingSystemValidationImpl validation = new LoanProcessingSystemValidationImpl();
		return validation;
	}
}
