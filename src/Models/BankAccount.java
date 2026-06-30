
package Models;

import java.util.ArrayList;//import the arraylist class


public abstract class BankAccount {

    //Attributes: and they are protected for "Encapsulation"
    protected String accountNumber;
    protected String ownerName;
    protected double balance;
    
    protected ArrayList<Transaction> transactions =new ArrayList<>();//List that stores all transactions made on this account

    
    
    //Constructor:
    public BankAccount(String accountNumber, String ownerName, double balance)
    {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;

    }
    
    
    
    //Getters:
    public String getAccountNumber()
    {
        return accountNumber;
    }

    public String getOwnerName()
    {
        return ownerName;
    }
    
    public double getBalance()
    {
        return balance;
    }
    
    // Returns all transactions of this account
    //Getter to arrayList:
    public ArrayList<Transaction> getTransactions()
    {
    return transactions;
    }
    
    
    
    
    
    
    //Method for Deposit:
    public void deposit(double amountDeposited)
    {
        //Validation:       
        if (amountDeposited > 0) 
        {
            balance += amountDeposited;
            //Record the transaction after deposit
            addTransaction(new Transaction("Deposit" ,amountDeposited ,balance));
            
        }
        else
        {
            System.out.println("Invalid Deposit Amount");
        }
        
    }
    
    
    
    //Method for Withdraw:
    public void withdraw(double amountwithdrawn )
    {  
      //Validation:
        if (amountwithdrawn <= 0)
        {
            System.out.println("Invalid Withdraw Amount");
        } 
        else if (amountwithdrawn <= balance)
        {
            balance -= amountwithdrawn;
            //Record the transaction after withdraw
            addTransaction(new Transaction("Withdraw" ,amountwithdrawn ,balance));
            
        } 
        else 
        {
            System.out.println("Insufficient Balance");
        }
        
    }
    
    
    //Mehod to add new transaction:
    // Adds a new transaction to the account history
    public void addTransaction(Transaction t) {
    transactions.add(t);
    }

    
    
    //Abstract Mehods:
    public abstract void applyMonthlyUpdate();
    
    public abstract void generateStatement();

}
