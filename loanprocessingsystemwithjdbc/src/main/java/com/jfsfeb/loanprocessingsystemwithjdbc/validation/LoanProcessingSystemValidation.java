package com.jfsfeb.loanprocessingsystemwithjdbc.validation;

public interface LoanProcessingSystemValidation {
	public boolean validateName(String name);
	public boolean validatePassword(String password);
	public boolean validateRole(String role);
	public boolean validateId(int id) ;
	public boolean validatePeriod(int period);
	public boolean validateAmount(double id);
	public boolean validateStatus(String status);
	public boolean validateAadharId(long aadharId);
	public boolean validateOccupation(String gender);
	public boolean validateLoanType(String loanType);
	public boolean validateLoanInterest(double loanIntrest);
	public boolean validateMobile(long mobile);
	public boolean validateEmail(String email);
}
