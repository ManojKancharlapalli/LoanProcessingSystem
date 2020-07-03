package com.jfsfeb.loanprocessingsystemwithhibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.jfsfeb.loanprocessingsystemwithhibernate.dto.CustomerInfo;
import com.jfsfeb.loanprocessingsystemwithhibernate.dto.EmployeeInfo;
import com.jfsfeb.loanprocessingsystemwithhibernate.dto.LoanInfo;
import com.jfsfeb.loanprocessingsystemwithhibernate.dto.StatusInfo;
import com.jfsfeb.loanprocessingsystemwithhibernate.exception.LPSException;

public class LoanProcessingSystemDAOImpl implements LoanProcessingSystemDAO {
	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;

	@Override
	public EmployeeInfo adminLogin(EmployeeInfo employee) {
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select u from EmployeeInfo u where" + " emailId=:emailId and password=:password "
					+ "and role=:role";
			TypedQuery<EmployeeInfo> query = manager.createQuery(jpql, EmployeeInfo.class);
			query.setParameter("emailId", employee.getEmailId());
			query.setParameter("password", employee.getPassword());
			query.setParameter("role", employee.getRole());
			EmployeeInfo bean = query.getSingleResult();
			return bean;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		} finally {
			manager.close();
			factory.close();
		}

	}

	@Override
	public List<LoanInfo> showLoansInfo() {
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select l from LoanInfo l";
		TypedQuery<LoanInfo> query = manager.createQuery(jpql, LoanInfo.class);
		try {
			List<LoanInfo> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			throw new LPSException("loans are not availble");
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public boolean updateLoan(int loanId, double loanInterest) {
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			LoanInfo record = manager.find(LoanInfo.class, loanId);
			record.setLoanInterest(loanInterest);
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}

	}

	@Override
	public boolean addLoan(LoanInfo loan) {
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			LoanInfo bean = manager.find(LoanInfo.class, loan.getLoanId());
			manager.persist(loan);

			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			return false;
			// throw new LoanException("Loan details already exists");
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public boolean removeLoan(int loanId) {
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			LoanInfo record = manager.find(LoanInfo.class, loanId);
			manager.remove(record);
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<StatusInfo> viewAllStatus() {
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select s from ApplicationStatusBean s";
		TypedQuery<StatusInfo> query = manager.createQuery(jpql, StatusInfo.class);

		try {
			List<StatusInfo> recordList = query.getResultList();
			return recordList;
		} catch (Exception e) {
			throw new LPSException("No loan programs arae availble");
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public boolean registerCustomer(CustomerInfo customer) {
		try {

			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.find(LoanInfo.class, 10);
			manager.persist(customer);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		} finally {
			manager.close();

		}
	}

	@Override
	public StatusInfo getStatusById(int customerId) {
		factory  = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select s from StatusInfo s where s.customerId=:id";
		TypedQuery<StatusInfo> query = manager.createQuery(jpql,
				StatusInfo.class);
		query.setParameter("id", customerId);
		try {
			StatusInfo record = query.getSingleResult();


			return record;

		}catch(Exception e) {
			throw new LPSException("No loan programs are avilable");
		}finally {
			manager.close();
			
		}

	}

	@Override
	public List<CustomerInfo> showCustomerInfo() {
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select c from CustomerInfo c";
		TypedQuery<CustomerInfo> query = manager.createQuery(jpql,CustomerInfo.class);

		try {
			List<CustomerInfo> recordList = query.getResultList();
			return recordList;
		}catch (Exception e) {
			throw new LPSException("Customers not Found");
		}finally {
			manager.close();
			factory.close();
		}

	}
	

	@Override
	public boolean updateStatus(StatusInfo status) {
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(status);
			transaction.commit();
			return true;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<CustomerInfo> getCustomerByLoanType(String loanType) {
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select a from CustomerInfo a where a.loanType=:type";
		TypedQuery<CustomerInfo> query = manager.createQuery(jpql,CustomerInfo.class);
		query.setParameter("type", loanType);
		try {
			List<CustomerInfo> recordList = query.getResultList();
			return recordList;
		}catch (Exception e) {
			throw new LPSException("No loan programs arae availble");
		}finally {
			manager.close();
			factory.close();
		}


	}

}
