
package Models;

import interfaces.Auditable;
import interfaces.Printable;

public class CheckingAccount extends BankAccount implements Auditable,Printable {
    
    private double overdraftLimit;
    
    
    //Constructor:
    public CheckingAccount(String accountNumber, String ownerName, double balance,double overdraftLimit)
    {
        super(accountNumber,ownerName,balance);
        this.overdraftLimit=overdraftLimit;
    }

    public double getOverdraftLimit()
    {
        return overdraftLimit;
    }
    
    
    @Override
    public void withdraw(double amountwithdrawn)
    {
        if (amountwithdrawn <= balance + overdraftLimit)
        {
            balance-=amountwithdrawn;
            addTransaction(new Transaction("Withdraw" ,amountwithdrawn ,balance));
        }
        else
            System.out.println("Insufficient Balance");
        
    }
    
    //apply monthly fee of 10 if balance under zero(overdraft penlaty)
    @Override
    public void applyMonthlyUpdate()
    {
        if (balance < 0)
        {
            balance -= 10;
        }
        
    }
    
    
    @Override
    public void generateStatement()
    {
        System.out.println("==== Checking Account Statement ====");
        System.out.println("The Account Number : " + accountNumber);
        System.out.println("The Name of Owner : " + ownerName);
        System.out.println("Balance : " + balance);
        System.out.println("The Overdraft Limit : " + overdraftLimit);
    }
    
    
    // Implementation of Auditable and Printable interfaces.
    @Override
    public void audit()
    {
      System.out.println("Auditing Checking Account: "+accountNumber);
    }
    
    @Override
    public void printDetails()
    {
      System.out.println("Account: " + accountNumber + " | Owner: " + ownerName + " | Balance: " + balance);
    }
    
}
