
package Models;


public abstract class LoanAccount extends BankAccount {
    
    //Annual interest rate applied monthly to increase loan balance;
    private double interestRate;
 
    //Constructor:
    public LoanAccount( String accountNumber, String ownerName, double balance,double interestRate)
    {
        super(accountNumber, ownerName, balance);
        this.interestRate = interestRate;
    }

    //Getter:
    public double getInterestRate() {
        return interestRate;
    }
    
    @Override
    public void applyMonthlyUpdate()
    {
        balance += (balance * interestRate);
    }
    
    @Override
    public abstract void generateStatement();
    
}
