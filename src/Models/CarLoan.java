
package Models;

import interfaces.Auditable;
import interfaces.Printable;


// Represents a car loan account with a car model, extends LoanAccount
public class CarLoan extends LoanAccount implements Auditable,Printable {
   
    private String carModel;
    
    
    //Constructor:
    public CarLoan(String accountNumber, String ownerName, double balance, double interestRate,String carModel)
    {
        super(accountNumber, ownerName, balance, interestRate);
        this.carModel = carModel;
    }
    
    
    //Getter:
    public String getCarModel() 
    {
        return carModel;
    }
    
    
    // Generates and prints a detailed statement for the car loan account, including car model
    @Override
     public void generateStatement()
    {
        System.out.println("==== Car Loan Account Statement ====");
        System.out.println("The Account Number : " + accountNumber);
        System.out.println("The Name of Owner : " + ownerName);
        System.out.println("Remaining Balance : " + balance);
        System.out.println("Interest Rate : " + (getInterestRate() * 100) + "%");
        System.out.println("Car Model: " + carModel);
    }
    
     
     // Implementation of Auditable and Printable interfaces.
    @Override
    public void audit()
    {
      System.out.println("Auditing Car Loan Account: "+accountNumber);
    }
    
    @Override
    public void printDetails()
    {
      System.out.println("Account: " + accountNumber + " | Owner: " + ownerName + " | Balance: " + balance);
    }
    
     
    
}
