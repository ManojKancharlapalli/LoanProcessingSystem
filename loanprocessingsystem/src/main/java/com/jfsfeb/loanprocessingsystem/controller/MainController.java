package com.jfsfeb.loanprocessingsystem.controller;

import com.jfsfeb.loanprocessingsystem.repository.DataBase;

public class MainController {
	public static void main(String[] args) {
		DataBase.addToDB();
		LoanProcessingSystemController.loanProcessingSystemOperations();
	}
}
