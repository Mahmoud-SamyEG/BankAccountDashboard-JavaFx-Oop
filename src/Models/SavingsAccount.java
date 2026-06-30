
package Models;

import interfaces.Auditable;
import interfaces.Printable;

public class SavingsAccount extends BankAccount implements Auditable,Printable {
    
    
    private double interestRate;
    
    
    //Constructor:
    public SavingsAccount(String accountNumber, String ownerName, double balance,double interestRate)
    {
        super(accountNumber, ownerName, balance);
        this.interestRate = interestRate;
    }
    
    
    public double getInterestRate()
    {
        return interestRate;
    }
    
    
    //add Interst on the balance
    @Override
    public void applyMonthlyUpdate()
    {
        //balance = balance + (balance*interestRate)
        balance += balance * interestRate;
    }
    
    
    // Generates and prints a detailed statement for the savings account, including interest rate
    @Override
    public void generateStatement()
    {
        System.out.println("==== Saving Account Statement ====");
        System.out.println("The Account Number : " + accountNumber);
        System.out.println("The Name of Owner : " + ownerName);
        System.out.println("Balance : " + balance);
        System.out.println("Interest Rate : " + (interestRate * 100) + "%");
    }
    
    
    // Implementation of Auditable and Printable interfaces.
    @Override
    public void audit()
    {
        System.out.println("Auditing Saving Account: "+accountNumber);
    }
    
    @Override
    public void printDetails()
    {
    
        System.out.println("Account: " + accountNumber + " | Owner: " + ownerName + " | Balance: " + balance);
    }
    
    
    
    
}
