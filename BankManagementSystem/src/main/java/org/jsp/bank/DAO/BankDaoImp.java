package org.jsp.bank.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import org.jsp.bank.model.Bank;

public  class BankDaoImp implements BankDAo
{
	public  String url="jdbc:mysql://localhost:3306/teca52?user=root&password=12345";

	public void userRegistration(Bank bank) {
		String insert="insert into bank(first_name, last_name, mobile_number,email_id, password, address, amount,accountNumber) values(?,?,?,?,?,?,?,?)";
         
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(insert);
			preparedStatement.setString(1,bank.getFirst_name());
			preparedStatement.setString(2,bank.getLast_name());
			preparedStatement.setString(3,bank.getMobile_number());
			String tempname= bank.getFirst_name().toLowerCase();
			Random random=new Random();
			int tempnum=random.nextInt(1000);
			String bankemailid=tempname+tempnum+"@teca52.com";
			preparedStatement.setString(4,bankemailid);
			preparedStatement.setString(5,bank.getPassword());
			preparedStatement.setString(6,bank.getAddress());
			preparedStatement.setDouble(7, bank.getAmount());
			String ac="10000000000";
			if(ac.length()==11) {
				ac+=10000000000l;
			}
			preparedStatement.setString(8,bank.getAccountNumber());
			int result=preparedStatement.executeUpdate();
			if(result!=0)
			{
				System.out.println("Account Created Successfully");
				try {
					Thread.sleep(2000);
					System.out.println("Your emailid is "+bankemailid);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("Account not created try again");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void credit(String accNum,String password) {
		Scanner sc=new Scanner(System.in);
		try {
			Connection connection =DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement("select * from bank where accountNumber=? and password=?");
			ps.setString(1, accNum);
			ps.setString(2, password);
			ResultSet resultSet=ps.executeQuery();
			if(resultSet.next())
			{
				System.out.println("Enter the Amount");
				boolean amountStatus=true;
				while(amountStatus)
				{
					double amount=sc.nextDouble();
					if(amount>0)
					{
						amountStatus=false;
						double dataBaseAmount=resultSet.getDouble("Amount");
						double sumOfAmount=dataBaseAmount+amount;
						String update="update bank set amount=? where  accountNumber=? and password=?";
						PreparedStatement ps1=connection.prepareStatement(update);
						ps1.setDouble(1, sumOfAmount);
						ps1.setString(2, accNum);
						ps1.setString(3, password);
						int result=ps1.executeUpdate();
						if(result!=0)
						{
							System.out.println("Amount Credited Successfully");
						}
						else
						{
							System.out.println("Not Credited");
						}
					}
					else {
						amountStatus=true;
						System.out.println("Enter Amount Greater Than 0");
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void debit(String accountNumber,String password) {
		Scanner sc=new Scanner(System.in);
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement("select * from bank where accountNumber=? and password=?");
			ps.setString(1, accountNumber);
			ps.setString(2, password);
			ResultSet resultSet=ps.executeQuery();
			if(resultSet.next())
			{
				System.out.println("Enter your Amount");
				boolean amountstatus=true;
				while(amountstatus)
				{
					double useramount=sc.nextDouble();
					if (useramount>=0)
					{
						amountstatus=false;
						double databaseamount=resultSet.getDouble("Amount");
						if(databaseamount>=useramount)
						{
							double sub=databaseamount-useramount;
							String update="update bank set amount=? where accountNumber=? and password=?";
							PreparedStatement ps1=connection.prepareStatement(update);
							ps1.setDouble(1, sub);
							ps1.setString(2, accountNumber);
							ps1.setString(3,password);
						int result=	ps1.executeUpdate();
						if(result!=0)
						{
							for (int i=0;i<3;i++)
							{
								try {
									Thread.sleep(1000);
									System.out.print(".");
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							System.out.println("Amount Debited Successfull...");
							
						}
						else
						{
						System.out.println("Server Problem");
						}
						}
						else
						{
							System.out.println("Insufficient Balance");
							amountstatus=true;
						}
					}
					else
					{
						System.out.println("Invalid Amount");
						System.out.println("Enter amount greater than 0");
						amountstatus=true;
					}
				}
			}
			else
			{
				System.out.println("Not Ok");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	public void changingThePassword(String accountNumber) {
		Scanner sc=new Scanner(System.in);
              String select ="select * from bank where accountNumber=?"	;
              try {
				Connection connection =DriverManager.getConnection(url);
				PreparedStatement ps1=connection.prepareStatement(select);
				ps1.setString(1, accountNumber);
				ResultSet resultSet=ps1.executeQuery();
				if(resultSet.next())
				{
					System.out.println("Enter New Password");
					String newPassword=sc.next();
					System.out.println("Enter Confirm Password");
					String cPassword=sc.next();
					if(newPassword.equals(cPassword))
					{
						String update="update bank set password=? where accountNumber=?";
						PreparedStatement ps2=connection.prepareStatement(update);
						ps2.setString(1, newPassword);
						ps2.setString(2, accountNumber);
						int result=ps2.executeUpdate();
						if(result!=0)
						{
							System.out.println("Password Updated Successfully...!");
						}
						else
						{
							System.out.println("There Is An Error");
						}
					}
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
	}

	public void mobileToMobileTransition(String mobileNumber) {
		Scanner sc=new Scanner(System.in);
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement pse = connection.prepareStatement("select * from bank where mobile_number=?");
			pse.setString(1, mobileNumber);
			ResultSet  rs = pse.executeQuery();
			if(rs.next()) {
				String senderName = rs.getString("first_name");
				System.out.println("Welcome "+senderName);
				System.out.println("Enter Receiver Name : ");
				String resName = sc.next();
				System.out.println("Enter The Reciver mobile number");
				boolean remobilenumberstatus = true ;
				while (remobilenumberstatus) {
					String remobilenumber = sc.next();
					if(remobilenumber.length()==10) {
						remobilenumberstatus = false ;
						PreparedStatement  pre = connection.prepareStatement("select * from bank where mobile_number=? and first_name=?");
						pre.setString(1, remobilenumber);
						pre.setString(2, resName);
						ResultSet resultset = pre.executeQuery();
						if(resultset.next()) {
							String name = resultset.getString("first_name");
							System.out.println("Receiver Name : "+name);
							System.out.println("Enter the Amount You Send");
							boolean amountstatus = true ;
							while(amountstatus) {
								double samount = sc.nextDouble();
								if(samount>=0) {
									amountstatus = false ;
									double senderdataamount = rs.getDouble("amount");
									if(senderdataamount>=samount) {
										double sendamount = senderdataamount-samount;
										
										double recvdataamount = resultset.getDouble("amount");
										double ramount = recvdataamount+samount;
				
										try {
											System.out.print("Processing");
											Thread.sleep(1000);
											System.out.print(".");
											Thread.sleep(1000);
											System.out.print(".");
											Thread.sleep(1000);
											System.out.print(".");
											System.out.println();
											
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										PreparedStatement pm1 = connection.prepareStatement("update bank set amount=? where mobile_number=?");
										pm1.setDouble(1, sendamount );
										pm1.setString(2, mobileNumber);
										
										int result1 = pm1.executeUpdate();
										if(result1!=0) {
											System.out.println("Your Amount Debited sucessfully...");
										}
										else {
											System.out.println("Transaction falied..!!");
										}
										
										try {
											Thread.sleep(2000);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
										PreparedStatement pm = connection.prepareStatement("update bank set amount=? where mobile_number=?");
										pm.setDouble(1,ramount );
										pm.setString(2, remobilenumber);
										
										int result = pm.executeUpdate();
										if(result!=0) {
											System.out.println("Amount Credited sucessfully...");
										}
										else {
											System.out.println("Not Recevied..!!");
										}
										try {
											Thread.sleep(2000);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										System.out.println("Sender remaning balance :"+sendamount);
										try {
											Thread.sleep(2000);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										System.out.println("recevier total amount :"+ramount);
										
										
									}
									else {
										System.out.println("Insufficient blance");
										System.out.println("Your Ac balance "+senderdataamount+"/-");
										amountstatus = true ;
									}
								}
								else {
									System.out.println("Enter the valid Amount");
									
								}
							}
							
						}
						else {
							System.out.println("Invalid Details..!!");
						}
					}
					else {
						System.out.println("Enter Valid 10 digits mobile number ");
						remobilenumberstatus = true ;
						
					}
				}
				
				
			}
			else {
				System.out.println("Inavlid Mobile Number");
				
			}
			
			
		} catch (SQLException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
		
	public void checkBalance(String accNumCheckBalance,String checkBalance) {
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement("select * from bank where accountNumber=? and password=?");
			preparedStatement.setString(1,accNumCheckBalance);
			preparedStatement.setString(2,checkBalance);
			ResultSet resultSet =preparedStatement.executeQuery();
			if (resultSet.next())
			{
				try {
					Thread.sleep(3000);
					double balance = resultSet.getDouble("Amount");
					System.out.println("Your Bank Balance is : "+balance);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}