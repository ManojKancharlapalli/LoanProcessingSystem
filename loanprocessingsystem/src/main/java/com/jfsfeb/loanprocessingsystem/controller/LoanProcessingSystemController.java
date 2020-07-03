package com.jfsfeb.loanprocessingsystem.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.jfsfeb.loanprocessingsystem.dto.LoansInfo;
import com.jfsfeb.loanprocessingsystem.dto.RequestInfo;
import com.jfsfeb.loanprocessingsystem.dto.CustomersInfo;
import com.jfsfeb.loanprocessingsystem.exception.LPSException;
import com.jfsfeb.loanprocessingsystem.factory.LPSFactory;
import com.jfsfeb.loanprocessingsystem.services.LoanProcessingSystemServices;

import lombok.extern.log4j.Log4j;

@Log4j
public class LoanProcessingSystemController {
	private static final LoanProcessingSystemServices SERVICE = LPSFactory.getLoanProcessingSystemServicesImplInstance();
	public static Scanner scanner = new Scanner(System.in);

	public static int checkChoice() {
		boolean flag = false;
		int choice = 0;
		do {
			try {
				choice = scanner.nextInt();
				flag = true;
			} catch (InputMismatchException e) {
				flag = false;
				log.error("Invalid Choice, It should contails only digits");
				scanner.next();
			} catch (LPSException e) {
				flag = false;
				log.error(e.getMessage());
			}
		} while (!flag);
		return choice;
	}

	public static int checkId() {
		boolean flag = false;
		int id = 0;
		do {
			try {
				id = scanner.nextInt();
				flag = true;
			} catch (InputMismatchException e) {
				flag = false;
				log.error("Invalid Id,It should contails only digits");
				scanner.next();
			} catch (LPSException e) {
				flag = false;
				log.error(e.getMessage());
			}
		} while (!flag);

		return id;
	}

	public static String checkName() {
		String name = null;
		boolean flag = false;
		do {
			try {
				name = scanner.next();
				flag = true;
			} catch (LPSException e) {
				flag = false;
				log.error(e.getMessage());
			}
		} while (!flag);
		// return customerName.toLowerCase();
		return name;

	}

	public static String checkEmailId() {
		String emailId = null;
		boolean flag = false;
		do {
			try {
				emailId = scanner.next();
				flag = true;
			} catch (LPSException e) {
				flag = false;
				log.error(e.getMessage());
			}
		} while (!flag);
		emailId.toLowerCase();
		return emailId;
	}

	public static String checkPassword() {
		String password = null;
		boolean flag = false;
		do {
			try {
				password = scanner.next();
				flag = true;
			} catch (LPSException e) {
				flag = false;
				log.error(e.getMessage());
			}
		} while (!flag);

		return password;
	}

	private static Double checkLoanAmount() {
		boolean flag = false;
		double loanAmount = 0;
		do {
			try {
				loanAmount = scanner.nextInt();
				flag = true;
			} catch (InputMismatchException e) {
				flag = false;
				log.error("Loan should contails only digits ");
				scanner.next();
			}
		} while (!flag);
		return loanAmount;
	}

	private static boolean checkAvailability() {
		boolean isAvail = false;
		boolean flag = false;
		do {
			try {
				isAvail = scanner.nextBoolean();
				flag = true;
			} catch (InputMismatchException e) {
				log.error("Enter Boolean value true/false");
				flag = false;
				scanner.next();
			}
		} while (!flag);
		return isAvail;
	}

	public static long checkLong() {
		boolean flag = false;
		long choice = 0;
		do {
			try {
				choice = scanner.nextLong();
				flag = true;
			} catch (InputMismatchException e) {
				flag = false;
				log.error("Invalid Choice, It should contails only digits");
				scanner.next();
			} catch (LPSException e) {
				flag = false;
				log.error(e.getMessage());
			}
		} while (!flag);
		return choice;
	}

	public static void loanProcessingSystemOperations() {

		CustomersInfo customerInfo = null;
		LoansInfo loanInfo = null;
		int choice = 0;
		int check = 0;
		int customerChoice = 0;
		int customerId = 0;
		int loanId = 0;
		String emailId = null;
		String password = null;
		String name = null;
		String loanType = null;
		double loanAmount = 0.0;
		double loanInterest = 0.0;
		boolean isAvailable = false;
		long mobileNumber = 0;
		long aadharNumber = 0;
		String occupation = null;
		double income = 0.0;
		LocalDate interviewDate = null;

		do {
			log.info("************* Welcome To Loan Processing System *************");
			log.info("1.Customer Page");
			log.info("2.Loan Approval Department Page");
			log.info("3.Admin Page");
			log.info("Enter your choice you want to enter");
			choice = checkChoice();

			switch (choice) {

// Customer Page

			case 1:
				log.info("Welcome Customer");
				log.info("---------------------------");

				try {
					log.info("Select your Choice");
					log.info("---------------------------");
					do {
						log.info("1. View all Loans");
						log.info("2. Search a Loan");
						log.info("3. Customer Application Form");
						log.info("4. Request a Loan");
						log.info("5. To Track status of the Loan");
						log.info("0. Exit");
						log.info("Enter your choice");
						customerChoice = checkChoice();

						switch (customerChoice) {

						case 1:
							try {
								List<LoansInfo> allLoans = SERVICE.showLoansInfo();
								log.info("Loans available :");
								log.info("-------------------------------");

								log.info(String.format("%-10s %-35s %-25s %-35s %-35s", "LOAN ID",
										"LOAN TYPE", "LOAN AMOUNT", "LOAN INTEREST", "IS AVAILABLE"));

								for (LoansInfo loan : allLoans) {

									log.info(String.format("%-10s %-35s %-25s %-35s %-35s", loan.getLoanId(),
											loan.getLoanType(), loan.getLoanAmount(), loan.getLoanInterest(),
											loan.isAvailable()));
								}
							
							} catch (LPSException e) {
								log.error(e.getMessage());
							}
							break;

						case 2:
							loansSearch();
							break;

						case 3:
							log.info("Enter Customer Registration Details");
							log.info("---------------------------------");
							customerId = (int) (Math.random() * 1000);
							if (customerId <= 100) {
								customerId = customerId + 100;
							}
							log.info("Customer Id : " + customerId);
							scanner.nextLine();
							log.info("Enter your Full Name : (Ex:Ram Akula)");
							name = checkName();
							log.info("Enter your Email Id : (Ex:ram@gmail.com)");
							emailId = checkEmailId();
							log.info("Enter your Mobile Number : (Ex:909211068)");
							mobileNumber = checkLong();
							log.info("Enter your Aadhra Number : (Ex:234567892)");
							aadharNumber = checkLong();
							log.info("Enter your Occupation : (Ex:Farmer)");
							occupation = checkName();
							log.info("Enter your Annual Income : (Ex:10000)");
							income = checkLoanAmount();
							customerInfo = new CustomersInfo();
							customerInfo.setCustomerId(customerId);
							customerInfo.setCustomerName(name);
							customerInfo.setCustomerEmailId(emailId);
							customerInfo.setMobileNumber(mobileNumber);
							customerInfo.setAadharNumber(aadharNumber);
							customerInfo.setOccupation(occupation);
							customerInfo.setIncome(income);

							try {
								SERVICE.register(customerInfo);
								log.info("You are Registered Sucessfully");
							} catch (LPSException e) {
								log.error(e.getMessage());
							}
							break;

						case 4:
							log.info("Requesting a Loan");
							log.info("-----------------------");
							log.info("Enter Customer Id");
							customerId = checkId();
							log.info("Enter loan Id");
							loanId = checkId();

							try {
								SERVICE.loanRequest(customerId, loanId);
								log.info("Sucessfully Request Placed to Appover ");
							} catch (LPSException e) {
								log.error(e.getMessage());
							}
							break;

						case 5:
							log.info("Enter Customer ID to track the status of Loan:");
							customerId = checkId();

							try {

								List<RequestInfo> request = SERVICE.searchRequestInfo(customerId);

								log.info(String.format("%-20s %-20s  %-35s %-35s ", "CUSTOMER ID", "LOAN ID",
										"INTERVIEW DATE", "IS ACCEPTED"));

								for (RequestInfo info : request) {

									log.info(String.format("%-20s %-20s  %-35s  %-35s", info.getCustomerId(),
											info.getLoanId(), info.getInterviewDate(), info.isAccepted()));
								}
							} catch (LPSException e) {
								log.error(e.getMessage());
							}
							break;

						case 0:
							break;

						default:
							log.info("Invalid Choice, Choice Must be in Between 0 or 1 or 2 or 3 or 4 or 5");
							break;
						}
					} while (customerChoice != 0);// user while ends

				} catch (LPSException e) {
					log.error(e.getMessage());
				}
				break;

// LAD Page
			case 2:
				log.info("Loan Approver LogIn Page");
				log.info("---------------------------");
				log.info("Enter Approver Email id");
				emailId = checkEmailId();
				log.info("Enter Approver password");
				password = checkPassword();

				try {
					SERVICE.approverLogin(emailId, password);
					log.info("Loan Approver logged in");
					log.info("---------------------------");

					do {

						log.info("1. View All Loans");
						log.info("2. View All Customers");
						log.info("3. View All Loan Requests");
						log.info("4. To Accept the request and Shedule Interview");
						log.info("0. Log Out");
						log.info("Enter your choice");
						check = checkChoice();

						switch (check) {

						case 1:
							try {
								List<LoansInfo> allLoans = SERVICE.showLoansInfo();
								log.info("Loans Available :");
								log.info("-------------------------------");

								log.info(String.format("%-10s %-35s %-25s %-35s %-35s ", "LOAN ID",
										"LOAN TYPE", "LOAN AMOUNT", "LOAN INTEREST", "IS AVAILABLE"));

								for (LoansInfo loan : allLoans) {

									log.info(String.format("%-10s %-35s %-25s %-35s %-35s ", loan.getLoanId(),
											loan.getLoanType(), loan.getLoanAmount(), loan.getLoanInterest(),
											loan.isAvailable()));
								}

							} catch (LPSException e) {
								log.error(e.getMessage());
							}
							break;

						case 2:
							try {
								List<CustomersInfo> customersInfo = SERVICE.showCustomerInfo();
								log.info("Customers of bank are :");
								log.info("----------------------");

								log.info(String.format("%-20s %-20s %-30s %-25s %-25s %-20s %-35s",
										"CUSTOMER ID", "NAME", "EMAIL ID", "MOBILE NUMBER", "AADHAR NUMBER",
										"OCCUPATION", "INCOME"));

								for (CustomersInfo info : customersInfo) {

									log.info(String.format("%-20s %-20s %-30s %-25s %-25s %-20s %-35s ",
											info.getCustomerId(), info.getCustomerName(), info.getCustomerEmailId(),
											info.getMobileNumber(), info.getAadharNumber(), info.getOccupation(),
											info.getIncome()));
								}

							} catch (LPSException e) {
								log.error(e.getMessage());
							}
							break;

						case 3:
							try {
								List<RequestInfo> requestInfos = SERVICE.showRequestsInfo();
								log.info("Requests for Loans are :");
								log.info("-----------------------");

								log.info(String.format("%-20s %-20s  %-35s %-35s", "CUSTOMER ID", "LOAN ID",
										"INTERVIEW DATE", "IS ACCEPTED"));

								for (RequestInfo info : requestInfos) {

									log.info(String.format("%-20s %-20s  %-35s %-35s ", info.getCustomerId(),
											info.getLoanId(), info.getInterviewDate(), info.isAccepted()));
								}
							} catch (LPSException e) {
								log.error(e.getMessage());
							}
							break;

						case 4:
							log.info("To Approve Request and Shedule Interview");
							log.info("----------------");
							log.info("Enter Customer Id");
							customerId = checkId();
							log.info("Enter Loan Id :");
							loanId = checkId();
							try {
								log.info("Enter Interview Data");
								interviewDate = LocalDate.parse(scanner.next());
							} catch (DateTimeParseException e) {
								log.error("Please provide valid date format(yyyy-mm-dd)");
							}

							try {
								SERVICE.loanApproval(customerId, loanId, interviewDate);

								log.info("Loan Approved Sucessfully");
							} catch (LPSException e) {
								log.error(e.getMessage());
							}

						case 0:
							break;

						default:
							log.error("Choice Should Be in Between 0 or 1 or 2 or 3 or 4");
							break;

						}
					} while (check != 0); // do while for Approver options // Approver activities end
				} catch (LPSException e) {
					log.error(e.getMessage());
				}
				break;

			case 3:
				log.info("Admin LogIn Page");
				log.info("---------------------------");
				log.info("Enter Admin Email id");
				emailId = checkEmailId();
				log.info("Enter Admin password");
				password = checkPassword();

				try {
					SERVICE.adminLogin(emailId, password);
					log.info("Admin logged in");
					log.info("---------------------------");

					do {
						log.info("1. Add Loans");
						log.info("2. Remove Loans");
						log.info("3. View All Loans");
						log.info("4. View All Customers");
						log.info("5. View Status of all Applications");
						log.info("0. Log Out");
						log.info("Enter your choice");
						check = checkChoice();

						switch (check) {
						case 1:
							loanInfo = new LoansInfo();
							log.info("Enter the Details to add the New Loan");
							log.info("----------------------------------------");
							log.info("Enter the Loan Id");
							loanId = checkId();
							log.info("Enter the Loan Type");
							loanType = checkName();
							log.info("Enter the Loan Amount");
							loanAmount = checkLoanAmount();
							log.info("Enter Interest for the Loan");
							loanInterest = checkLoanAmount();
							log.info("Enter Availability of Loan");
							isAvailable = checkAvailability();

							loanInfo.setLoanId(loanId);
							loanInfo.setLoanType(loanType);
							loanInfo.setLoanAmount(loanAmount);
							loanInfo.setLoanInterest(loanInterest);
							loanInfo.setAvailable(isAvailable);
							try {
								boolean addLoan = SERVICE.addLoan(loanInfo);
								if (addLoan) {
									log.info("New loan added successfully");
								}
							} catch (Exception e) {
								log.error(e.getMessage());
							}
							break;

						case 2:
							loanInfo = new LoansInfo();
							log.info("Enter Loan Id To Remove");
							loanId = checkId();
							loanInfo.setLoanId(loanId);

							try {
								SERVICE.deleteLoan(loanId);
								log.info("Loan Removed Sucessfully");
							} catch (LPSException e) {
								log.error(e.getMessage());
							}
							break;

						case 3:
							try {
								List<LoansInfo> allLoans = SERVICE.showLoansInfo();
								log.info("Loans Available :");
								log.info("-------------------------------");

								log.info(String.format("%-10s %-35s %-25s %-35s %-35s", "LOAN ID",
										"LOAN TYPE", "LOAN AMOUNT", "LOAN INTEREST", "IS AVAILABLE"));

								for (LoansInfo loan : allLoans) {

									log.info(String.format("%-10s %-35s %-25s %-35s %-35s", loan.getLoanId(),
											loan.getLoanType(), loan.getLoanAmount(), loan.getLoanInterest(),
											loan.isAvailable()));
								}

							} catch (LPSException e) {
								log.error(e.getMessage());
							}
							break;

						case 4:
							try {
								List<CustomersInfo> customersInfo = SERVICE.showCustomerInfo();
								log.info("Customers of bank are :");
								log.info("----------------------");

								log.info(String.format("%-20s %-20s %-30s %-25s %-25s %-20s %-35s",
										"CUSTOMER ID", "NAME", "EMAIL ID", "MOBILE NUMBER", "AADHAR NUMBER",
										"OCCUPATION", "INCOME"));

								for (CustomersInfo info : customersInfo) {

									log.info(String.format("%-20s %-20s %-30s %-25s %-25s %-20s %-35s",
											info.getCustomerId(), info.getCustomerName(), info.getCustomerEmailId(),
											info.getMobileNumber(), info.getAadharNumber(), info.getOccupation(),
											info.getIncome()));
								}

							} catch (LPSException e) {
								log.error(e.getMessage());
							}
							break;

						case 5:
							try {
								List<RequestInfo> requestInfos = SERVICE.showRequestsInfo();
								log.info("Status of the All Applications :");
								log.info("-----------------------");

								log.info(String.format("%-20s %-20s %-35s %-35s ", "CUSTOMER ID", "LOAN ID",
										"INTERVIEW DATE", "IS ACCEPTED"));

								for (RequestInfo info : requestInfos) {

									log.info(String.format("%-20s %-20s %-35s %-35s ", info.getCustomerId(),
											info.getLoanId(), info.getInterviewDate(), info.isAccepted()));
								}
							} catch (LPSException e) {
								log.error(e.getMessage());
							}
							break;

						case 0:
							break;

						default:
							log.error("Choice Should Be in Between 0 or 1 or 2 or 3 or 4 or 5");
							break;

						}
					} while (check != 0); // do while for Admin options // Admin activities end
				} catch (LPSException e) {
					log.error(e.getMessage());
				}
				break;

			default:
				log.error("Invalid Choice, Choice Must be in beween 1 or 2 or 3");
				break;

			}// Switch case end

		} while (true);// Main and outer most do while

	}// End Of Main

	public static void loansSearch() {
		int searchLoanBy;
		int loanId;
		String loanType;

		do {
			log.info("1. Search Loan By Id");
			log.info("2. Search Loan By Loan Type");
			log.info("0. Search Exit");
			log.info("Enter Your Choice");
			searchLoanBy = checkChoice();
			LoansInfo loanInfo = new LoansInfo();

			switch (searchLoanBy) {
			case 1:
				log.info("Enter Loan ID To Search:");
				loanId = checkId();
				loanInfo.setLoanId(loanId);

				try {
					List<LoansInfo> loans = SERVICE.searchLoans(loanInfo);

					log.info(String.format("%-10s %-35s %-25s %-35s %-35s", "LOAN ID", "LOAN TYPE",
							"LOAN AMOUNT", "LOAN INTEREST", "IS AVAILABLE"));

					for (LoansInfo loan : loans) {

						log.info(String.format("%-10s %-35s %-25s %-35s %-35s", loan.getLoanId(),
								loan.getLoanType(), loan.getLoanAmount(), loan.getLoanInterest(), loan.isAvailable()));
					}

				} catch (LPSException e) {
					log.error(e.getMessage());
				}
				break;

			case 2:
				scanner.nextLine();
				log.info("Enter Loan Type");
				loanType = checkName();
				loanInfo.setLoanType(loanType);

				try {
					List<LoansInfo> loans = SERVICE.searchLoansByType(loanInfo);

					log.info(String.format("%-10s %-35s %-25s %-35s %-35s", "LOAN ID", "LOAN TYPE",
							"LOAN AMOUNT", "LOAN INTEREST", "IS AVAILABLE"));

					for (LoansInfo loan : loans) {

						log.info(String.format("%-10s %-35s %-25s %-35s %-35s", loan.getLoanId(),
								loan.getLoanType(), loan.getLoanAmount(), loan.getLoanInterest(), loan.isAvailable()));
					}

				} catch (LPSException e) {
					log.error(e.getMessage());
				}
				break;
			case 0:
				break;

			default:
				log.info("Invali Choice, Choice Must Be In Between 0 or 1 or 2");
				break;
			}

		} while (searchLoanBy != 0);

	}// End Of LoansSearch

}// End of customer
