package org.jsp.bank.DAO;

import org.jsp.bank.model.Bank;

public interface BankDAo {
public	void userRegistration(Bank bank);
public	void credit(String accNum,String password);
public	void debit(String accountNumber,String password);
public	void changingThePassword(String accountNumber);
public	void mobileToMobileTransition(String mobileNumber);
public	void checkBalance(String accNumCheckBalance,String checkBalance);
	

}