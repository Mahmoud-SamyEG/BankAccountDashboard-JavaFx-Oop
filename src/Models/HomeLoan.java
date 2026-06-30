
package Models;

import interfaces.Auditable;
import interfaces.Printable;


// Represents a home loan account with a property address, extends LoanAccount
public class HomeLoan extends LoanAccount implements Auditable,Printable {
    
   private String propertyAddress;

   
   //Constructor:
    public HomeLoan(String accountNumber, String ownerName, double balance, double interestRate
           , String propertyAddress)
    {
        super(accountNumber, ownerName, balance, interestRate);
        this.propertyAddress = propertyAddress;
    }

    
    //Getter:
    public String getPropertyAddress()
    {
        return propertyAddress;
    }
    
    
    // Generates and prints a detailed statement for the home loan account, including property address
    @Override
    public void generateStatement()
    {
        System.out.println("==== Home Loan Account Statement ====");
        System.out.println("The Account Number : " + accountNumber);
        System.out.println("The Name of Owner : " + ownerName);
        System.out.println("Remaining Balance : " + balance);
        System.out.println("Interest Rate : " + (getInterestRate() * 100) + "%");
        System.out.println("Property Address : " + propertyAddress);
    }
    
    
    // Implementation of Auditable and Printable interfaces.
    @Override
    public void audit()
    {
      System.out.println("Auditing Home Loan Account: "+accountNumber);
    }
    
    @Override
    public void printDetails()
    {
      System.out.println("Account: " + accountNumber + " | Owner: " + ownerName + " | Balance: " + balance);
    }
    
    
    
    
    
}
