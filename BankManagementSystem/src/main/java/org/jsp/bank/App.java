package org.jsp.bank;
import java.util.Scanner;

import org.jsp.bank.DAO.BankCustumerDesk;
import org.jsp.bank.DAO.BankDAo;
import org.jsp.bank.model.Bank;
import org.jsp.bank.DAO.*;
public class App 
{
    public static void main( String[] args )
    {
        BankDAo bankDao = BankCustumerDesk.customerHelpDesk();
       // bankDao.userRegistration(Bank bank);
        System.out.println();
        System.out.println("===> BANK MANAGEMENT SYSTEM <===");
        System.out.println();
        System.out.println("Enter \n 1. For Registration  \n 2. For Credit \n 3. For Debit \n 4. For Changing The Password  \n 5. For Mobile To Mobile Transaction  \n 6. For Check Balance");
         Scanner sc= new Scanner(System.in);
         int response=sc.nextInt();
         switch (response)
         {
         case 1: 
        	 System.out.println("Enter Your First Name");
        	 String first_Name=sc.next();
        	 System.out.println("Enter Your Last Name");
        	 String last_Name=sc.next();
        	 System.out.println("Enter Your Mobile Number");
        	 String mobile_Number=sc.next();
        	 System.out.println("Enter your email id");
        	 String email_id=sc.next();
        	 System.out.println("Enter Your Password");
        	 String password=sc.next();
        	 System.out.println("Enter Your Address");
        	 String address=sc.next();
        	 System.out.println("Enter Your Amount");
        	 double amount=sc.nextDouble();
        	 System.out.println("Enter your account number");
        	 String accountNumber=sc.next();
        	 
        	 Bank bank  = new Bank(first_Name,last_Name,mobile_Number,email_id,password,address,amount,accountNumber);
         bankDao.userRegistration(bank);
         break;
         case 2:
        	 System.out.println("Enter Your Account Number");
        	 boolean accStatus=true;
        	 while(accStatus)
        	 {
        		 String accNum=sc.next();
        		 if(accNum.length()==11)
        		 {
        			 accStatus=false;
        			 System.out.println("Enter Your Password");
        			 boolean passwordStatus=true;
        			 while(passwordStatus)
        			 {
        				 String userPassword=sc.next();
        				 if(userPassword.length()==4) {
        					 bankDao.credit(accNum, userPassword);
        				 }
        				 else
        				 {
        					 System.out.println("Invalid Password");
        					 System.out.println("Enter a valid 4 digits PAssword");
        				 }
        				 
        			 }
        		 }
        		 
        	 }
         case 3:
        	 System.out.println("Enter Your Acount Number ");
       	  
       	  boolean accountstatus = true;
       	  while(accountstatus) {
       		  String accountnumber = sc.next();
       	  
       		  if(accountnumber.length()==11) {
       			  accountstatus = false;
       			  
       			  System.out.println("Enter your password ");
       			   boolean passwordstatus = true ;
       			   while(passwordstatus) {
       				   String userpassword = sc.next();
       				   if(userpassword.length()==4) {
       					   passwordstatus = false;
       					   bankDao.debit(accountnumber, userpassword);
       					   
       				   }
       				   else {
       					   System.out.println("invlid passowrd ");
       					   System.out.println("Enter the 4 digits password ");
       					   passwordstatus = true;
       				   }
       				   
       			   }
       		  }
       			  
       		  else
       		  {
       			  System.out.println("Inavlid Account number & plaese Enter 11 digits");
    			  
    		  }
    	  } 
        	 
        break;
        
         case 4:
        	 System.out.println("Enter Your Account Number");
        	 String accountNumber1=sc.next();     	
 		     if(accountNumber1.length()==11) {
 		    	 
 		    	 bankDao.changingThePassword(accountNumber1);
 		    	 
 		     } else {
 		    	 System.out.println("Please Enter a Valid Account Number...!");
 		     }

        break;
        
        
         case 5:
        	 System.out.println("Enter the Sender mobile number");
       	  boolean mobilenumberstatus = true;
       	  while(mobilenumberstatus) {
       		  String mobileno1 = sc.next();
       		  if(mobileno1.length()==10) { 
       			  mobilenumberstatus = false;
       			  
       			  bankDao.mobileToMobileTransition(mobileno1);
       		  }
       		  else {
       			  System.out.println("Invalid mobile number ");
       			  
       		  }
       	  }  
       	  break;

       	  
         case 6:
 			System.out.println("Enter Your Account Number");
 			boolean acBaCh = true;
 			while(acBaCh)
 			{
 				String accNumCheckBalance = sc.next();
 				if (accNumCheckBalance.length()==11)
 				{
 					acBaCh = false;
 					boolean pwBaCh = true;
 					while(pwBaCh)
 					{
 						System.out.println("Enter Your Password ");
 						String checkBalance = sc.next();
 						if (checkBalance.length()==4)
 						{
 							pwBaCh = false;
 							bankDao.checkBalance(accNumCheckBalance, checkBalance);
 							
 						}
 						else
 						{
 							System.out.println("Invalide Password");
 							System.out.println("Enter A Valide 4 digits Password");
 							pwBaCh = true;
 						}
 					}
 				}
 				else
 				{
 					System.out.println("Invalid Account Number");
 					System.out.println("Please Enter A valide 11 digits Account Number");
 					acBaCh = true;
 				}
 			}
 			
 		default:
 			break;
 		}
        	 }
         
       
    }