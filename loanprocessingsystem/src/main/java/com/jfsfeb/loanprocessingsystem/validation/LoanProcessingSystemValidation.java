package com.jfsfeb.loanprocessingsystem.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfsfeb.loanprocessingsystem.exception.LPSException;

public class LoanProcessingSystemValidation {

	public boolean validateId(int id) throws LPSException {
		String idRegx = "[\\d&&[^0]][\\d]{2}";
		boolean isValidated = Pattern.matches(idRegx, String.valueOf(id));
		if (isValidated) {
			return true;
		} else {
			throw new LPSException(
					"Please Enter Valid Id and It Should Contains Exactly 3 Digits and first Digit should be a non zero digit");
		}
	}

	public boolean validateName(String name) throws LPSException {
		String nameRegEx = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";
		boolean result = false;
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LPSException("Name should contains only Alpabates");
		}
		return result;
	}

	public boolean validateEmail(String email) throws LPSException {
		String emailRegx = "[\\w&&[^_]]{3,50}[@]{1}\\D{2,50}[.]{1}\\D{2,50}";
		boolean isValidated = Pattern.matches(emailRegx, email);
		if (isValidated) {
			return true;
		} else {
			throw new LPSException("Enter Proper EmailId, contains  @ and extensions(.com,.in,.org,..)");
		}
	}

	public boolean validatePassword(String password) throws LPSException {
		String passwordRegx = "^.{4,10}$";
		boolean isValidated = Pattern.matches(passwordRegx, password);
		if (isValidated) {
			return true;
		} else {
			throw new LPSException("Enter Valid Password, Which Contains Minimum 4 charaters and Maximum 10");
		}
	}
	
	public boolean validateAmount(double amount) throws LPSException {
		String salaryRegEx = "[0-9]{1,7}(\\.[0-9]*)?";
		boolean result = false;

		if (Pattern.matches(salaryRegEx, String.valueOf(amount))) {
			result = true;
		} else {
			throw new LPSException("Please Enter valid amount, It Should Contain  Digits limit 7");
		}
		return result;
	}
	
	public boolean validatedMobileNumber(long mobileNumber) throws LPSException {
		String mobileNumberRegEx = "(0/91)?[6-9][0-9]{9}";
		boolean result = false;
		if (Pattern.matches(mobileNumberRegEx, String.valueOf(mobileNumber))) {
			result = true;
		} else {
			throw new LPSException("Mobile Number  will start with  6 to 9 and It should contains 10 numbers...!");
		}
		return result;
	}
	
	public boolean validatedAadharNumber(long aadharNumber) throws LPSException {
		String aadharNumberRegEx = "^([0-9]{12}\\d{11})$";
		boolean result = false;
		if (Pattern.matches(aadharNumberRegEx, String.valueOf(aadharNumber))) {
			result = true;
		} else {
			throw new LPSException("AadharNumber should contains 12 numbers...!");
		}
		return result;
	}
	public boolean validateIntrest(double interest) throws LPSException {
		String rateOfInterestRateRegEx = "[0-9]{1,5}(\\.[0-9]*)?";

		boolean result = false;

		if (Pattern.matches(rateOfInterestRateRegEx, String.valueOf(interest))) {
			result = true;
		} else {
			throw new LPSException("Please Enter valid Interest, It contains limit of 5 Digits");
		}
		return result;
	}
}

