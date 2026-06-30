
package Models;

import java.time.LocalDateTime;// used to automatically get the current data and time from the system
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Transaction {
    
    // Attributes:
    private SimpleStringProperty type;         // Deposit or Withdraw
    private SimpleDoubleProperty amount;       // Value of transaction
    private SimpleStringProperty date;         // Date and time of transaction (auto from system)
    private SimpleDoubleProperty balanceAfter; // Balance remaining after transaction

    
    // Constructor: date is taken automatically from system
    public Transaction(String type, double amount, double balanceAfter)
    {
        this.type = new SimpleStringProperty(type); // wrapping String into JavaFX property for TableView.  
        this.amount = new SimpleDoubleProperty(amount);
        this.balanceAfter = new SimpleDoubleProperty(balanceAfter);
        this.date = new SimpleStringProperty(LocalDateTime.now().toString());
    }
    
    
    // Getters: used by TableView to display data
    public String getType()
    { 
        return type.get(); 
    }
    
    public double getAmount() 
    { 
        return amount.get(); 
    }
    
    public String getDate()
    { 
        return date.get(); 
    }
    
    public double getBalanceAfter()
    { 
        return balanceAfter.get(); 
    }
    
    
    
}

