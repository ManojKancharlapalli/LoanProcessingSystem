package com.jfsfeb.loanprocessingsystem.repository;

import java.util.ArrayList;
import java.util.List;

import com.jfsfeb.loanprocessingsystem.dto.AdminInfo;
import com.jfsfeb.loanprocessingsystem.dto.LoansInfo;
import com.jfsfeb.loanprocessingsystem.dto.RequestInfo;
import com.jfsfeb.loanprocessingsystem.dto.CustomersInfo;
import com.jfsfeb.loanprocessingsystem.dto.LoanApproverInfo;

public class DataBase {

	public static final List<AdminInfo> ADMINDB = new ArrayList<AdminInfo>();
	public static final List<LoanApproverInfo> APPROVERDB = new ArrayList<LoanApproverInfo>();
	public static final List<CustomersInfo> CUSTOMERDB = new ArrayList<CustomersInfo>();
	public static final List<LoansInfo> LOANDB = new ArrayList<LoansInfo>();
	public static final List<RequestInfo> REQUESTDB = new ArrayList<RequestInfo>();

	public static void addToDB() {
		AdminInfo admininfo = new AdminInfo();
		admininfo.setAdminEmailId("admin1@sbi.com");
		admininfo.setAdminPassword("123456");
		ADMINDB.add(admininfo);

		AdminInfo admininfo1 = new AdminInfo();
		admininfo1.setAdminEmailId("admin2@sbi.com");
		admininfo1.setAdminPassword("654321");
		ADMINDB.add(admininfo1);

		LoanApproverInfo approverinfo = new LoanApproverInfo();
		approverinfo.setApproverEmailId("approver1@sbi.com");
		approverinfo.setApproverPassword("123456");
		APPROVERDB.add(approverinfo);

		LoanApproverInfo approverinfo1 = new LoanApproverInfo();
		approverinfo1.setApproverEmailId("approver2@sbi.com");
		approverinfo1.setApproverPassword("654321");
		APPROVERDB.add(approverinfo1);

		CustomersInfo customerinfo = new CustomersInfo();
		customerinfo.setCustomerId(111);
		customerinfo.setCustomerName("manoj");
		customerinfo.setCustomerEmailId("manoj@gmail.com");
		customerinfo.setMobileNumber(903011069);
		customerinfo.setAadharNumber(232136864);
		customerinfo.setOccupation("farmer");
		customerinfo.setIncome(100000.0);
		CUSTOMERDB.add(customerinfo);

		CustomersInfo customerinfo1 = new CustomersInfo();
		customerinfo1.setCustomerId(123);
		customerinfo1.setCustomerName("sagar");
		customerinfo1.setCustomerEmailId("sagar@gmail.com");
		customerinfo1.setMobileNumber(944044489);
		customerinfo1.setAadharNumber(345654765);
		customerinfo1.setOccupation("solider");
		customerinfo1.setIncome(200000.0);
		CUSTOMERDB.add(customerinfo1);

		CustomersInfo customerinfo2 = new CustomersInfo();
		customerinfo2.setCustomerId(234);
		customerinfo2.setCustomerName("charan");
		customerinfo2.setCustomerEmailId("charan@gmail.com");
		customerinfo2.setMobileNumber(944167385);
		customerinfo2.setAadharNumber(197654321);
		customerinfo2.setOccupation("Doctor");
		customerinfo2.setIncome(300000.0);
		CUSTOMERDB.add(customerinfo2);

		LoansInfo loaninfo = new LoansInfo();
		loaninfo.setLoanId(101);
		loaninfo.setLoanType("HomeConstruction");
		loaninfo.setLoanAmount(500000.00);
		loaninfo.setLoanInterest(8.5);
		loaninfo.setAvailable(true);
		LOANDB.add(loaninfo);

		LoansInfo loaninfo1 = new LoansInfo();
		loaninfo1.setLoanId(102);
		loaninfo1.setLoanType("HomeExtention");
		loaninfo1.setLoanAmount(100000.00);
		loaninfo1.setLoanInterest(2.5);
		loaninfo1.setAvailable(true);
		LOANDB.add(loaninfo1);

		LoansInfo loaninfo2 = new LoansInfo();
		loaninfo2.setLoanId(103);
		loaninfo2.setLoanType("LandPurchase");
		loaninfo2.setLoanAmount(250000.00);
		loaninfo2.setLoanInterest(6.5);
		loaninfo2.setAvailable(true);
		LOANDB.add(loaninfo2);

		LoansInfo loaninfo3 = new LoansInfo();
		loaninfo3.setLoanId(104);
		loaninfo3.setLoanType("HomeRemodeling");
		loaninfo3.setLoanAmount(50000.00);
		loaninfo3.setLoanInterest(1.5);
		loaninfo3.setAvailable(false);
		LOANDB.add(loaninfo3);

	}
}
