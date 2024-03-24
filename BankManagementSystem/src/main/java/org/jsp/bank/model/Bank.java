package org.jsp.bank.model;

public class Bank 
{
	private int	id;
	private String first_name;
	private String last_name;
	private String mobile_number;
	private String email_id;
	private String password;
	private String address;
	private double amount;
	private String accountNumber;
public Bank()
{
	
}
public Bank(String first_name, String last_name, String mobile_number,String email_id,  String password,
		String address, double amount,String accountNumber) {
	super();

	this.first_name = first_name;
	this.last_name = last_name;
	this.mobile_number = mobile_number;
	this.email_id=email_id;
	this.password = password;
	this.address = address;
	this.amount = amount;
	this.accountNumber=accountNumber;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFirst_name() {
	return first_name;
}
public void setFirst_name(String first_name) {
	this.first_name = first_name;
}
public String getLast_name() {
	return last_name;
}
public void setLast_name(String last_name) {
	this.last_name = last_name;
}
public String getMobile_number() {
	return mobile_number;
}
public void setMobile_number(String mobile_number) {
	this.mobile_number = mobile_number;
}
public String getEmail_id() {
	return email_id;
}
public void setEmail_id(String email_id) {
	this.email_id = email_id;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public String getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}
@Override
public String toString() {
	return "Bank [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", mobile_number="
			+ mobile_number + ", email_id=" + email_id + ", password=" + password + ", address=" + address + ", amount="
			+ amount + ", accountNumber=" + accountNumber + "]";
}



}