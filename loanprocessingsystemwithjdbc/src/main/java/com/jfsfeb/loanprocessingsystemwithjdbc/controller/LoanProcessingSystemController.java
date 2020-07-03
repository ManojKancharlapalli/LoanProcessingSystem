package com.jfsfeb.loanprocessingsystemwithjdbc.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.jfsfeb.loanprocessingsystemwithjdbc.dto.CustomerInfo;
import com.jfsfeb.loanprocessingsystemwithjdbc.dto.EmployeeInfo;
import com.jfsfeb.loanprocessingsystemwithjdbc.dto.LoanInfo;
import com.jfsfeb.loanprocessingsystemwithjdbc.dto.StatusInfo;
import com.jfsfeb.loanprocessingsystemwithjdbc.exception.LPSException;
import com.jfsfeb.loanprocessingsystemwithjdbc.factory.LPSFactory;
import com.jfsfeb.loanprocessingsystemwithjdbc.service.LoanProcessingSystemService;

import lombok.extern.log4j.Log4j;

/**
 * @author Manoj
 *
 */
@Log4j
public class LoanProcessingSystemController {

	@SuppressWarnings("unused")
	public static void loanProcessinSystemOperations() {

		boolean flag = false;

		// user login****//

		String emailId = null;
		String password = null;
		String role = null;

		// **loan details*****//

		int loanId = 0;
		String loanType = null;
		double loanInterest = 0.0;
		int loanPeriod = 0;
		double loanAmount = 0;

		// *****Customer details***//
		int customerId = 0;
		String customerName = null;
		String customerEmailId = null;
		long mobileNumber = 0;
		long aadharNumber = 0;
		String occupation = null;
		double income = 0;

		// *** Status details**//
		int statusCustomerId = 0;
		String status = null;
		LocalDate interviewDate = null;

		// ****services***//
		LoanProcessingSystemService SERVICE = LPSFactory.getLoanProcessingSystemServicesImplInstance();

		// **Bean**//
		LoanInfo loan = null;
		StatusInfo statusInfo = null;

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		do {
			try {
				log.info("************* Welcome To Loan Processing System *************");
				log.info("1) Admin/LAD Page");
				log.info("2) Customer page");
				log.info("3) To exit");
				log.info("Enter your choice");
				int choice = scanner.nextInt();
				switch (choice) {

				case 1:

					EmployeeInfo info = new EmployeeInfo();

					log.info("---Enter your login credentials---");

					log.info("Enter EmailId :");
					emailId = scanner.next();
					info.setEmailId(emailId);

					log.info("Enter Password :");
					password = scanner.next();
					info.setPassword(password);

					log.info("Enter role");
					role = scanner.next();
					info.setRole(role);
					try {
						info = SERVICE.adminLogin(info);
						if ((info.getEmailId().equalsIgnoreCase(emailId)) && (info.getPassword().equals(password))) {
							log.info("You have logged in successfully");
							
						}
						if (info.getRole().equalsIgnoreCase("admin")) {
							do {
								try {

									log.info("Enter your choice...");
									log.info("1) Add Loans");
									log.info("2) Update loan");
									log.info("3) Delete loan");
									log.info("4) view all loans");
									log.info("5) To view all Customer statuses");
									log.info("6) To Exit");
									int choice1 = scanner.nextInt();
									/* admin */switch (choice1) {
									case 1:
										do {
											do {
												try {
													log.info("Enter Loan Id :");
													loanId = scanner.nextInt();

													flag = true;

												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("Loan Id should be in  digits");
													scanner.next();
												}
											} while (!flag);

											log.info("Enter loan Type :");
											loanType = scanner.next();
											do {
												try {
													log.info("Enter loan Amount:");
													loanAmount = scanner.nextInt();

													flag = true;
												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("loan Amount should be in digits");
													scanner.nextLine();
												}
											} while (!flag);
											do {
												try {
													log.info("Enter loan Interest :");
													loanInterest = scanner.nextDouble();
													flag = true;
												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("loan Interest should be in digits");
													scanner.nextLine();
												}
											} while (!flag);
											do {
												try {
													log.info("Enter loan Period :");
													loanPeriod = scanner.nextInt();

													flag = true;
												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("Period should be in digits");
													scanner.nextLine();

												}
											} while (!flag);
											loan = new LoanInfo();
											loan.setLoanId(loanId);
											loan.setLoanType(loanType);
											loan.setLoanAmount(loanAmount);
											loan.setLoanInterest(loanInterest);
											loan.setLoanPeriod(loanPeriod);
											try {
												boolean add = SERVICE.addLoan(loan);
												if (add) {
													log.info("Loan are added successfully...!");
												} else {
													System.err.println("Loan program already exist..!");
												}
												flag = true;
											} catch (LPSException e) {
												flag = false;
												System.err.println(e.getMessage());
											}
										} while (!flag);
										break;

									case 2:
										do {

											do {
												try {

													log.info("Enter loan Id to update Loan Interest");
													loanId = scanner.nextInt();
													flag = true;
												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("Loan Id should be in digits");
													scanner.nextLine();
												}

											} while (!flag);

											do {
												try {
													log.info("Enter Loan Interest :");
													loanInterest = scanner.nextDouble();

													flag = true;
												} catch (InputMismatchException e) {
													flag = false;
													System.err.println("Interest should be in digits");
													scanner.nextLine();

												}

											} while (!flag);// ended

											try {

												boolean updateLoanStatus = SERVICE.updateLoan(loanId, loanInterest);
												if (updateLoanStatus) {
													log.info("loan program updated");
												} else {
													System.err.println("Given loan Id is not availble");
												}
												flag = true;
											} catch (LPSException e) {
												flag = false;
												System.err.println(e.getMessage());
											}
										} while (!flag);
										break;

									case 3:

										do {
											try {

												log.info("Enter the loan Id to delete :");
												loanId = scanner.nextInt();
												flag = true;

											} catch (InputMismatchException e) {
												flag = false;
												System.err.println("loan Id should be in digits..");
												scanner.nextLine();
											}

										} while (!flag);
										try {
											System.out.println(loanId);
											boolean remove = SERVICE.removeLoan(loanId);
											if (remove) {
												log.info("Loan program is removed succesfully");
											} else {
												log.info("Loan program is not removed");
											}

										} catch (LPSException e) {

											System.err.println(e.getMessage());
										}

										break;
									case 4:
										log.info("view all loan");
										try {
											List<LoanInfo> beans = SERVICE.showLoansInfo();
											log.info("------------------------------------------------------"
													+ "-------------------------------------");
											for (LoanInfo bean : beans) {
												log.info(beans.toString());
											}

											log.info("------------------------------------------------------"
													+ "-------------------------------------");
										} catch (LPSException e) {
											System.err.println(e.getMessage());
										}

										break;
									case 5:
										log.info("checking status");
										try {
											List<StatusInfo> statusList = SERVICE.viewAllStatus();
											log.info("------------------------------------------------------"
													+ "---------------------------");
											for (StatusInfo statusBean : statusList) {
												log.info(statusBean.toString());
											}

											log.info("------------------------------------------------------"
													+ "-----------------------------");
										} catch (LPSException e) {
											System.err.println(e.getMessage());
										}

										break;

									case 6:
										loanProcessinSystemOperations();
									default:
										System.err.println("Invalid Choice!!Enter choice as 1 or 2 or 3 or 4 or 5 or 6");
										break;
									}
								} catch (InputMismatchException ex) {
									System.err.println("Incorrect entry. Please enter valid choice");
									scanner.nextLine();
								}

							} while (true);

						}

						else if (info.getRole().equalsIgnoreCase("approver")) {

							do {

								try {

									log.info("Enter your choice...");
									log.info("1) View all loans");
									log.info("2) View all Customers");
									log.info("3) Update application status");
									log.info("4) View all Customer based on loan type");
									log.info("5) To Exit");
									int choice2 = scanner.nextInt();
									/* lad */switch (choice2) {
									case 1:

										log.info("view all loan ");

										try {
											List<LoanInfo> loans = SERVICE.showLoansInfo();
											log.info("------------------------------------------------------"
													+ "-------------------------------------");
											for (LoanInfo loanBean : loans) {
												log.info(loanBean.toString());
											}

											log.info("------------------------------------------------------"
													+ "-------------------------------------");
										} catch (LPSException e) {
											System.err.println(e.getMessage());
										}
										break;
									case 2:
										try {
											List<CustomerInfo> formList = SERVICE.showCustomerInfo();
											log.info("------------------------------------------------------"
													+ "----------------------------------------------"
													+ "------------------------------------------------");
											for (CustomerInfo bean : formList) {
												log.info(bean.toString());
											}

											log.info("------------------------------------------------------"
													+ "-----------------------------------------------------------------"
													+ "-----------------------------------------------");
										} catch (LPSException e) {
											System.err.println(e.getMessage());
										}
										break;
									case 3:
										do {
											try {
												statusInfo = new StatusInfo();
												log.info("Enter the customerId  :");
												customerId = scanner.nextInt();
												flag = true;
											} catch (InputMismatchException e) {
												flag = false;
												System.err.println("customer Id should be exactly 3 digits");
												scanner.nextLine();
											}

										} while (!flag);
										do {
											try {
												log.info("Enter interview date: ");

												interviewDate = LocalDate.parse(scanner.next());

												flag = true;
											} catch (DateTimeParseException e) {
												flag = false;
												System.err.println("Please provide valid date format(yyyy-mm-dd)");

											}
										} while (!flag);

										log.info("Enter status: ");
										status = scanner.next();

										statusInfo.setCustomerId(customerId);
										statusInfo.setInterviewDate(java.sql.Date.valueOf(interviewDate));

										statusInfo.setStatus(status);
										try {

											boolean updateStatus = SERVICE.updateStatus(statusInfo);
											if (updateStatus) {
												log.info("Updated succesfully..!");
											} else {
												System.err.println("Given customer id not found");
											}
										} catch (LPSException e) {
											System.err.println(e.getMessage());
										}

										break;

									case 4:

										log.info("Enter loan type :");
										loanType = scanner.next();

										try {

											List<CustomerInfo> beans = SERVICE.getCustomerByLoanType(loanType);

											log.info("------------------------------------------------------"
													+ "-------------------------------------------------------------------------------------");
											for (CustomerInfo infos : beans) {
												log.info(infos.toString());
											}

											log.info("------------------------------------------------------"
													+ "-----------------------------------------------------------"
													+ "--------------------------");
										} catch (LPSException e) {
											System.err.println(e.getMessage());
										}

										break;
									case 5:
										loanProcessinSystemOperations();
									default:
										System.err.println("Invalid choice.Please enter choice as 1 or 2 or 3 or 4 or 5");
										break;
									}

								} catch (InputMismatchException ex) {
									System.err.println("Incorrect entry. Please enter valid choice");
									scanner.nextLine();
								}

							} while (true);
						}
					} catch (Exception e) {
						System.err.println("Invalid Credentials");

					}
					break;
				case 2:
					// ***customer operation****//
					log.info("*************Welcome to customer page*************");
					do {

						try {
							log.info("1) View all loan");
							log.info("2) Apply Loan By Filling form");
							log.info("3) To check status of Application");
							log.info("4) To Exit");
							int choice2 = scanner.nextInt();
							switch (choice2) {
							case 1:
								log.info("view all loan ");
								try {
									List<LoanInfo> loans = SERVICE.showLoansInfo();
									log.info("------------------------------------------------------"
											+ "-------------------------------------");
									for (LoanInfo bean : loans) {

										log.info(loans.toString());
									}
									log.info("------------------------------------------------------"
											+ "-------------------------------------");
								} catch (LPSException e) {
									System.err.println(e.getMessage());
								}
								break;

							case 2:
								log.info("Fill the details in form..");
								CustomerInfo formBean = new CustomerInfo();// modified
								customerId = (int) (Math.random() * 1000);
								log.info("Your Customer id: " + customerId);

								log.info("Enter your Name:");
								customerName = scanner.next();
								log.info("Enter your EmailId:");
								customerEmailId = scanner.next();
								do {
									try {
										log.info("Enter your Mobile Number:");
										mobileNumber = scanner.nextLong();
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Mobile number should be in digits..");
										scanner.nextLine();
									}
								} while (!flag);

								do {
									try {
										log.info("Enter your Aadhar Number:");
										aadharNumber = scanner.nextLong();
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Aadhar number should be in digits..");
										scanner.nextLine();

									}
								} while (!flag);
								log.info("Enter your Occupation:");
								occupation = scanner.next();
								log.info("Enter your Annual Income:");
								income = scanner.nextDouble();
								log.info("Enter loan type :");
								loanType = scanner.next();

								do {
									try {
										log.info("Enter loan amount :");
										loanAmount = scanner.nextDouble();
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Amount should be in digits.");
										scanner.nextLine();
									}

								} while (!flag);// ended

								formBean.setCustomerId(customerId);
								log.info(formBean.getCustomerId());
								formBean.setCustomerName(customerName);
								formBean.setCustomerEmailId(customerEmailId);
								formBean.setMobileNumber(mobileNumber);
								formBean.setAadharNumber(aadharNumber);
								formBean.setOccupation(occupation);
								formBean.setIncome(income);
								formBean.setLoanType(loanType);
								formBean.setLoanAmount(loanAmount);
								try {
									boolean appStatus = SERVICE.registerCustomer(formBean);
									log.info(formBean.getCustomerId());
									if (appStatus) {
										log.info("Submitted Form successfully");
										log.info("Remember your customer id for future reference");
									}
								} catch (LPSException e) {

									System.err.println(e.getMessage());
								}

								break;

							case 3:
								log.info("Enter your customer id to check status");
								do {
									try {
										customerId = scanner.nextInt();

										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Customer id should be in digits..");
										scanner.nextLine();
									}

								} while (!flag);

								try {
									StatusInfo check = SERVICE.getStatusById(customerId);
									log.info("----------------------------------------"
											+ "--------------------------------------------");
									log.info(check.toString());
									log.info("--------------------------------------------"
											+ "----------------------------------------");
								} catch (LPSException e) {

									System.err.println(e.getMessage());
								}
								break;
							case 4:
								loanProcessinSystemOperations();
								break;
							default:

								System.err.println("Invalid choice.Please enter chioce as 1 or 2 or 3 or 4");
								break;
							}
						} catch (InputMismatchException ex) {
							System.err.println("Incorrect entry. Please enter only choices");
							scanner.nextLine();
						}

					} while (true);
				case 3:
					loanProcessinSystemOperations();
					break;
				default:

					System.err.println("Invalid Choice.. Please enter choice as 1 or 2 or 3");
					break;
				}
			} catch (InputMismatchException ex) {
				System.err.println("Incorrect entry. Please enter only choices");
				scanner.nextLine();
			}
		} while (true);

	}// End of method

}// End of class
