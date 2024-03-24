package org.jsp.bank.DAO;

public class BankCustumerDesk {

	public static BankDAo customerHelpDesk() {
		
		BankDAo bankDao = new BankDaoImp();
		
		return bankDao;
	}
}