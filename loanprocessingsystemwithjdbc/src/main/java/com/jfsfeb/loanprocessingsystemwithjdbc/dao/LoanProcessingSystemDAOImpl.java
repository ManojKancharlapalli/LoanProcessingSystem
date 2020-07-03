package com.jfsfeb.loanprocessingsystemwithjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.jfsfeb.loanprocessingsystemwithjdbc.dto.CustomerInfo;
import com.jfsfeb.loanprocessingsystemwithjdbc.dto.EmployeeInfo;
import com.jfsfeb.loanprocessingsystemwithjdbc.dto.LoanInfo;
import com.jfsfeb.loanprocessingsystemwithjdbc.dto.StatusInfo;
import com.jfsfeb.loanprocessingsystemwithjdbc.exception.LPSException;
import com.jfsfeb.loanprocessingsystemwithjdbc.utility.DBConnector;

public class LoanProcessingSystemDAOImpl implements LoanProcessingSystemDAO {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet result = null;
	DBConnector dbConnector = new DBConnector();

	@Override
	public EmployeeInfo adminLogin(EmployeeInfo employee) {
		connection = dbConnector.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(dbConnector.getQuery("loginQuery"))) {

			statement.setString(1, employee.getEmailId());
			statement.setString(2, employee.getPassword());
			statement.setString(3, employee.getRole());
			result = statement.executeQuery();

			if (result.next()) {
				return employee;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		throw new LPSException("Invalid credentials");

	}

	@Override
	public List<LoanInfo> showLoansInfo() {
		List<LoanInfo> loans = new LinkedList<LoanInfo>();
		try (Connection connection = dbConnector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(dbConnector.getQuery("showLoansQuery"));) {

			while (resultSet.next()) {
				LoanInfo loan = new LoanInfo();

				loan.setLoanId(resultSet.getInt("loanId"));
				loan.setLoanType(resultSet.getString("loanType"));
				loan.setLoanAmount(resultSet.getDouble("loanAmount"));
				loan.setLoanInterest(resultSet.getDouble("loanIntrest"));
				loan.setLoanPeriod(resultSet.getInt("loanPeriod"));
				loans.add(loan);
			}
			if (loans.isEmpty()) {
				throw new LPSException("no loan is avilable");
			} else {
				return loans;
			}

		} catch (Exception e) {
			throw new LPSException(e.getMessage());
		}

	}
	@Override
	public boolean updateLoan(int loanId, double loanInterest) {

		try (Connection connection = dbConnector.getConnection();
				PreparedStatement statement = connection.prepareStatement(dbConnector.getQuery("updateLoanQuery"))) {
			statement.setDouble(1, loanInterest);
			statement.setInt(2, loanId);
			int count = statement.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		throw new LPSException("Given id not found");
	}

	@Override
	public boolean addLoan(LoanInfo loan) {
		try (Connection connection = dbConnector.getConnection();
				PreparedStatement statement = connection.prepareStatement(dbConnector.getQuery("addLoanQuery"))) {

			statement.setInt(1, loan.getLoanId());
			statement.setString(2, loan.getLoanType());
			statement.setDouble(3, loan.getLoanInterest());
			statement.setInt(4, loan.getLoanPeriod());
			statement.setDouble(5, loan.getLoanAmount());
			int count = statement.executeUpdate();

			return true;

		} catch (Exception e) {

			throw new LPSException("Loan program already exists..!");

		}

	}

	@Override
	public boolean removeLoan(int loanId) {
		try (Connection connection = dbConnector.getConnection();
				PreparedStatement statement = connection.prepareStatement(dbConnector.getQuery("deleteLoanQuery"));) {

			statement.setInt(1, loanId);
			int count = statement.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		throw new LPSException("Given id  not found");
	}

	@Override
	public List<StatusInfo> viewAllStatus() {
		try (Connection connection = dbConnector.getConnection();
				PreparedStatement stmt = connection.prepareStatement(dbConnector.getQuery("geAllStatusQuery"));) {
			result = stmt.executeQuery();
			List<StatusInfo> infos = new ArrayList<StatusInfo>();
			while (result.next()) {
				StatusInfo info = new StatusInfo();
				info.setCustomerId(result.getInt("customerId"));
				info.setInterviewDate(result.getDate("interviewDate"));
				info.setStatus(result.getString("status"));
				infos.add(info);
			}
			return infos;
		} catch (Exception e) {
			e.printStackTrace();

		}
		throw new LPSException("No status are availble");
	}

	@Override
	public boolean registerCustomer(CustomerInfo customer) {
		try (Connection connection = dbConnector.getConnection();
				PreparedStatement statement = connection.prepareStatement(dbConnector.getQuery("registerCustomer"));) {

			statement.setInt(1, customer.getCustomerId());
			statement.setString(2, customer.getCustomerName());
			statement.setString(3, customer.getCustomerEmailId());
			statement.setLong(4, customer.getMobileNumber());
			statement.setDouble(5, customer.getAadharNumber());
			statement.setString(6, customer.getOccupation());
			statement.setDouble(7, customer.getIncome());
			statement.setString(8, customer.getLoanType());
			statement.setDouble(9, customer.getLoanAmount());
			int count = statement.executeUpdate();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public StatusInfo getStatusById(int customerId) {
		try (Connection connection = dbConnector.getConnection();
				PreparedStatement preparedstatement = connection
						.prepareStatement(dbConnector.getQuery("getStatusByIdQuery"));) {
			preparedstatement.setInt(1, customerId);
			try (ResultSet resultset = preparedstatement.executeQuery();) {
				if (resultset.next()) {
					StatusInfo Bean = new StatusInfo();
					Bean.setCustomerId(resultset.getInt("customerId"));
					Bean.setInterviewDate(resultset.getDate("interviewDate"));
					Bean.setStatus(resultset.getString("status"));

					return Bean;
				}
			}
		} catch (Exception e) {
			throw new LPSException(e.getMessage());
		}
		throw new LPSException("Given Application Id is not Exists");

	}

	@Override
	public List<CustomerInfo> showCustomerInfo() {
		try (Connection connection = dbConnector.getConnection();
				PreparedStatement stmt = connection.prepareStatement(dbConnector.getQuery("showCustomersQuery"));) {
			result = stmt.executeQuery();
			List<CustomerInfo> applicationFormBeans = new ArrayList<CustomerInfo>();
			while (result.next()) {
				CustomerInfo customers = new CustomerInfo();
				customers.setCustomerId(result.getInt("customerId"));
				customers.setCustomerName(result.getString("customerName"));
				customers.setCustomerEmailId(result.getString("customerEmailId"));
				customers.setMobileNumber(result.getLong("mobileNumber"));
				customers.setAadharNumber(result.getLong("aadhraNumber"));
				customers.setOccupation(result.getString("occupation"));
				customers.setIncome(result.getDouble("income"));
				customers.setLoanType(result.getString("loanType"));
				customers.setLoanAmount(result.getDouble("loanAmount"));
				applicationFormBeans.add(customers);
			}
			return applicationFormBeans;
		} catch (SQLException e) {
			throw new LPSException("No status available");
		} catch (Exception e) {
			throw new LPSException("No applications are available");
		}

	}

	@Override
	public boolean updateStatus(StatusInfo status) {
		try (Connection connection = dbConnector.getConnection();
				PreparedStatement statement = connection.prepareStatement
						(dbConnector.getQuery("statusQuery"))) {
			statement.setInt(1, status.getCustomerId());
			statement.setDate(2, status.getInterviewDate());
			statement.setString(3, status.getStatus());
			int count = statement.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
		
			throw new LPSException("Given id is already exist");
		}
	}


	@Override
	public List<CustomerInfo> getCustomerByLoanType(String loanType) {
		try (Connection connection = dbConnector.getConnection();
				PreparedStatement stmt = connection.prepareStatement
				(dbConnector.getQuery("getAllApplicationsByLoanTypeQuery"));) {
			stmt.setString(1, loanType);
			result = stmt.executeQuery();
			List<CustomerInfo> applicationFormBeans = new ArrayList<CustomerInfo>();
			while (result.next()) {
				CustomerInfo customers = new CustomerInfo();
				customers.setCustomerId(result.getInt("customerId"));
				customers.setCustomerName(result.getString("customerName"));
				customers.setCustomerEmailId(result.getString("customerEmailId"));
				customers.setMobileNumber(result.getLong("mobileNumber"));
				customers.setAadharNumber(result.getLong("aadhraNumber"));
				customers.setOccupation(result.getString("occupation"));
				customers.setIncome(result.getDouble("income"));
				customers.setLoanType(result.getString("loanType"));
				customers.setLoanAmount(result.getDouble("loanAmount"));
				applicationFormBeans.add(customers);
			}
			if(applicationFormBeans.size()!=0) {
			return applicationFormBeans;
			}else {
				throw new LPSException("No applications are available for given type");
			}
		} catch (SQLException e) {
		    throw new LPSException("No applications are available");
		}
		catch (Exception e) {
			throw new LPSException("No applications are available");
		}
	}

}
