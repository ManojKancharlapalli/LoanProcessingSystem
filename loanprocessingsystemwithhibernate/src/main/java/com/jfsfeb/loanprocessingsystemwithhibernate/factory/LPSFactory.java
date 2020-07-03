package com.jfsfeb.loanprocessingsystemwithhibernate.factory;

import com.jfsfeb.loanprocessingsystemwithhibernate.dao.LoanProcessingSystemDAO;
import com.jfsfeb.loanprocessingsystemwithhibernate.dao.LoanProcessingSystemDAOImpl;
import com.jfsfeb.loanprocessingsystemwithhibernate.service.LoanProcessingSystemService;
import com.jfsfeb.loanprocessingsystemwithhibernate.service.LoanProcessingSystemServiceImpl;
import com.jfsfeb.loanprocessingsystemwithhibernate.validation.LoanProcessingSystemValidationImpl;

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
