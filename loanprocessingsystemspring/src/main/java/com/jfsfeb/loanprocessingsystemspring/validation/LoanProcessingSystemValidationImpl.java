package com.jfsfeb.loanprocessingsystemspring.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.jfsfeb.loanprocessingsystemspring.exception.LPSException;
@Component
public class LoanProcessingSystemValidationImpl implements LoanProcessingSystemValidation {

	public boolean validateId(int id) {
		String idRegEx = "[\\d&&[^0]][\\d]{2}";
		boolean result = false;
		if (Pattern.matches(idRegEx, String.valueOf(id))) {
			result = true;
		} else {
			throw new LPSException(
					"Please Enter Valid Id and It Should Contains Exactly 3 Digits and first Digit should be a non zero digit");
		}
		return result;
	}

	public boolean validatePeriod(int period) {
		String idRegEx = "[0-9]{1}[0-9]{1}";
		boolean result = false;
		if (Pattern.matches(idRegEx, String.valueOf(period))) {
			result = true;
		} else {
			throw new LPSException("loan period should contains exactly 2 digits" + " but not start with 0..");
		}
		return result;
	}

	public boolean validateAmount(double id) {
		String idRegEx = "[0-9]{1,7}(\\.[0-9]*)?";
		boolean result = false;
		if (Pattern.matches(idRegEx, String.valueOf(id))) {
			result = true;
		} else {
			throw new LPSException("Please Enter valid amount, It Should Contain  Digits limit 7");
		}
		return result;
	}

	public boolean validateStatus(String status) {
		String statusRegEx = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{2,}$";
		boolean result = false;
		Pattern pattern = Pattern.compile(statusRegEx);
		Matcher matcher = pattern.matcher(status);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LPSException("Application status should  contain  atleast 3 characters "
					+ "and should not contain any digits and special symbols...");
		}
		return result;
	}

	// aadar id
	public boolean validateAadharId(long aadharId) {
		String idRegEx = "[0-9]{1}[0-9]{11}";
		boolean result = false;

		if (Pattern.matches(idRegEx, String.valueOf(aadharId))) {
			result = true;
		} else {
			throw new LPSException("Aadhar Id should contains exactly 12" + " digits but not start with 0...");
		}
		return result;
	}

	public boolean validateName(String name) {
		String nameRegEx = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{2,}$";
		boolean result = false;
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(name);

		if (matcher.matches()) {
			result = true;
		} else {

			throw new LPSException("Name should  atleast contains 3"
					+ " characters and should not contain digits and special symbols..!");

		}
		return result;

	}

	public boolean validatePassword(String password) {

		String passwordRegEx = "^.{4,10}$";
		boolean result = false;
		Pattern pattern = Pattern.compile(passwordRegEx);
		Matcher matcher = pattern.matcher(password);
		if (matcher.matches()) {
			result = true;
		} else {

			throw new LPSException("Enter Valid Password, Which Contains Minimum 4 charaters and Maximum 10 ");
		}
		return result;
	}

	public boolean validateOccupation(String gender) {
		String nameRegEx = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{2,}$";
		boolean result = false;
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(gender);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LPSException(
					"Occupation should contain  atleast 3 characters" + " and should not contain digits..!");
		}
		return result;
	}

	public boolean validatedAddress(String name) {
		String nameRegEx = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{2,}$";
		boolean result = false;
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LPSException("Address should contain  atleast 3 characters"
					+ " and should not contain digits and special symbols..!");
		}
		return result;
	}

	public boolean validateRole(String role) {
		String nameRegEx = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{2,}$";
		boolean result = false;
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(role);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LPSException("Role should  contain atleast 3 caharcters"
					+ " and should not contain digits and special symbols..!");
		}
		return result;
	}

	public boolean validateLoanType(String loanType) {
		String nameRegEx = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";
		boolean result = false;
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(loanType);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LPSException("Loan type should  contain  atleast 3"
					+ " characters and should not contain any digits and special symbols...");
		}
		return result;
	}

	public boolean validateLoanInterest(double interest) {
		String idRegEx = "[0-9]{2,3}(\\.[0-9]*)?";
		boolean result = false;
		if (Pattern.matches(idRegEx, String.valueOf(interest))) {
			result = true;
		} else {
			throw new LPSException("loan intrest rate should contains 2-3 digits but not start with 0..");
		}
		return result;
	}

	public boolean validateMobile(long mobile) {
		String mobileRegEx = "(0/91)?[6-9][0-9]{9}";
		boolean result = false;
		if (Pattern.matches(mobileRegEx, String.valueOf(mobile))) {
			result = true;
		} else {
			throw new LPSException("Mobile Number  will start with  6 to 9 and It should contains 10 numbers...!");
		}
		return result;
	}

	public boolean validateEmail(String email) {
		String emailRegEx = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		boolean result = false;
		Pattern pattern = Pattern.compile(emailRegEx);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			result = true;
		}

		else {
			throw new LPSException("Enter Emailid in proper manner it  contains alphanumeric "
					+ "characters and @ with extension(.com)...!");
		}
		return result;
	}

}
