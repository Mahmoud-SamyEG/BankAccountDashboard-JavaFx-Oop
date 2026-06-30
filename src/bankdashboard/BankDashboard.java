
package bankdashboard;

import Models.Transaction;


import Models.SavingsAccount;
import Models.CheckingAccount;
import Models.HomeLoan;
import Models.CarLoan;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView; // displays a scrollable list of items
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.TableView;// displays data in a table with rows and columns
import javafx.scene.control.TableColumn;// represents a single column in the TableView, takes <RowType, ColumnDataType>
import javafx.scene.control.cell.PropertyValueFactory;// links a TableColumn to a getter method in the row class
import javafx.scene.chart.BarChart; 
import javafx.scene.chart.CategoryAxis; 
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart; // contains Series and Data classes for chart data
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.layout.Background;
import static javafx.scene.layout.Background.fill;
import static javafx.scene.paint.Color.GRAY;
import static javafx.scene.paint.Color.ORANGE;
import static javafx.scene.paint.Color.RED;


public class BankDashboard extends Application {
    
    
    //flag: tracks whether dark mode is on or off
    private boolean isDarkMode = false;
    
   @Override
   public void start(Stage primaryStage)
   {
       
    //===create account objects===  
    SavingsAccount mahmoud =new SavingsAccount("001" ,"Mahmoud " , 5000 ,0.03);   
    CheckingAccount ahmed =new CheckingAccount("002" ,"Ahmed" , 6000 ,500);   
    HomeLoan ali =new HomeLoan("003" ,"Ali" , 8000 ,0.05 ,"Zagzig");
    CarLoan yousef =new CarLoan("004" , "Yousef" , 4000 ,0.04 ,"Toyota");   
       
    
    

    
    
    // Main layout that divides the screen into Left, Center, Right, Top, Bottom   
    BorderPane Bpane=new BorderPane(); 
      
      
    //====SIDEBAR====: 
    Label accountLabel =new Label("Accounts:"); // Label shown above the account list
    accountLabel.setBackground(Background.fill(GRAY));
    
    ListView<String> accountList =new ListView<>();// List that shows all account names
    
            
    accountList.getItems().addAll(
            "💰 Saving- " + mahmoud.getOwnerName() ,
            "🏦 Checking- " + ahmed.getOwnerName(),
            "🏠 HomeLoan- " + ali.getOwnerName(),
            "🚗 CarLoan- " + yousef.getOwnerName()
              
            );
    
 
    VBox slidebar =new VBox();
    slidebar.setSpacing(10);
    slidebar.setPadding(new Insets(10));
    slidebar.getChildren().addAll(accountLabel , accountList);
    slidebar.setPrefWidth(200);
    Bpane.setLeft(slidebar);
    
       
    
    
    // ===== TABLE VIEW =====
    
    
    TableView<Transaction> tableView = new TableView<>();

    
    // Create Columns:
    // Column 1:shows the type of transaction (Deposit or Withdraw)
    TableColumn<Transaction, String> typeCol = new TableColumn<>("Type");
    typeCol.setCellValueFactory(new PropertyValueFactory<>("type")); // links to getType()

    // Column 2: shows the amount of the transaction
    TableColumn<Transaction, Double> amountCol = new TableColumn<>("Amount");
    amountCol.setCellValueFactory(new PropertyValueFactory<>("amount")); // links to getAmount()

    // Column 3: shows the date and time of the transaction
    TableColumn<Transaction, String> dateCol = new TableColumn<>("Date");
    dateCol.setCellValueFactory(new PropertyValueFactory<>("date")); // links to getDate()

    // Column 4: shows the balance remaining after the transaction
    TableColumn<Transaction, Double> balanceCol = new TableColumn<>("Balance After");
    balanceCol.setCellValueFactory(new PropertyValueFactory<>("balanceAfter")); // links to getBalanceAfter()

    
    tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
   
    
    tableView.getColumns().addAll(typeCol, amountCol, dateCol, balanceCol);

    // Add TableView to Borderpane in Center
    Bpane.setCenter(tableView);
    
    // When user clicks an account in the sidebar, show its transactions
    accountList.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
    tableView.getItems().clear(); // clear old transactions
    
    switch (newVal.intValue()) {
        case 0: tableView.getItems().addAll(mahmoud.getTransactions()); break;
        case 1: tableView.getItems().addAll(ahmed.getTransactions()); break;
        case 2: tableView.getItems().addAll(ali.getTransactions()); break;
        case 3: tableView.getItems().addAll(yousef.getTransactions()); break;
    }
    });
    
    
    
    
    // ===== BAR CHART =====

    // Horizontal axis: shows account names as text
    CategoryAxis xAxis = new CategoryAxis();
    xAxis.setLabel("Accounts"); // label shown below the axis
    
    
    // Vertical axis: shows balance values as numbers
    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Balance"); // label shown beside the axis

    // Create the BarChart using the two axes
    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
    barChart.setTitle("Balance Overview"); // title shown above the chart

    // Series: holds the data to be displayed in the chart
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    //series.setName("Balance"); // name shown in the chart legend

    // Add data: each entry is (account name, balance value)
    series.getData().add(new XYChart.Data<>("Saving", mahmoud.getBalance()));
    series.getData().add(new XYChart.Data<>("Checking ",ahmed.getBalance()));
    series.getData().add(new XYChart.Data<>("HomeLoan", ali.getBalance()));
    series.getData().add(new XYChart.Data<>("CarLoan", yousef.getBalance()));

    // Add series to the chart
    barChart.getData().add(series);

    // Place the chart at the bottom of the screen
    Bpane.setBottom(barChart);
    

    
    
    
    //*** Buttonns >>[TRANSFER DIALOG and MONTHLY REPORT and Dark/Light mode] ***
    
    // Button in the main screen that opens the transfer dialog
    Button transferBtn = new Button("💸 Transfer");
    transferBtn.setPrefWidth(100);
    transferBtn.setBackground(Background.fill(ORANGE));
    
    // Button to generate monthly report for selected account
    Button reportBtn = new Button("📊 Monthly Report");
    reportBtn.setBackground(Background.fill(ORANGE));
 
    
    // Dark/Light mode toggle button
    Button modeBtn = new Button("🌙 Dark Mode");
    modeBtn.setPrefWidth(200);
    modeBtn.setBackground(Background.fill(ORANGE));
 
   
    // Place three buttons at the top using HBox
    HBox topBar = new HBox(10, transferBtn, reportBtn, modeBtn); 
    topBar.setPadding(new Insets(10));
    Bpane.setTop(topBar); 
    
    
    

    
 // The CSS javafx for (Dark and Light Mode)using AI ====    
    // ===== DEFINE LIGHT MODE =====
    Runnable applyLight = () -> {
    // Main background
    Bpane.setStyle("-fx-background-color: white;");
    topBar.setStyle("-fx-background-color: white;");
    
    // Buttons - back to default
    transferBtn.setStyle("");
    reportBtn.setStyle("");
    modeBtn.setStyle("");
    
    // Sidebar - back to default
    slidebar.setStyle("");
    accountLabel.setStyle("");
    accountList.setStyle("");
    
    // TableView - back to default
    tableView.setStyle("");
    tableView.lookupAll(".column-header").forEach(node -> node.setStyle(""));
    tableView.lookupAll(".column-header-background").forEach(node -> node.setStyle(""));
    tableView.lookupAll(".label").forEach(node -> node.setStyle(""));
    
    // BarChart - back to default
    barChart.setStyle("");
    barChart.lookup(".chart-plot-background").setStyle("");
    barChart.lookup(".chart-legend").setStyle("");
    barChart.lookup(".chart-title").setStyle("");
    barChart.lookup(".axis-label").setStyle("");
    xAxis.setStyle("");
    yAxis.setStyle("");
    yAxis.lookup(".axis-label").setStyle("-fx-text-fill: black;");
   
    
    // Bar colors - back to original orange
    series.getData().forEach(data -> data.getNode().setStyle(""));
    };
    
    
    // ===== DEFINE DARK MODE =====
    Runnable applyDark = () -> {
    // Main background
    Bpane.setStyle("-fx-background-color: #1E2A3A;");
    topBar.setStyle("-fx-background-color: #0F3460;");
    
    // Buttons
    String darkBtn = "-fx-background-color: #00B4D8; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8px; -fx-padding: 8px 16px;";
    transferBtn.setStyle(darkBtn);
    reportBtn.setStyle(darkBtn);
    modeBtn.setStyle(darkBtn);
    
    // Sidebar
    slidebar.setStyle("-fx-background-color: #16213E; -fx-padding: 10px;");
    accountLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
    accountList.setStyle("-fx-background-color: #16213E; -fx-control-inner-background: #16213E; -fx-border-color: #00B4D8; -fx-border-radius: 5px;");
    
    // TableView
    tableView.setStyle("-fx-background-color: #1E2A3A; -fx-control-inner-background: #1E2A3A;");
    tableView.lookupAll(".column-header").forEach(node -> node.setStyle("-fx-background-color: #0F3460;"));
    tableView.lookupAll(".column-header-background").forEach(node -> node.setStyle("-fx-background-color: #0F3460;"));
    tableView.lookupAll(".label").forEach(node -> node.setStyle("-fx-text-fill: white;"));
    
    // BarChart
    barChart.setStyle("-fx-background-color: #1E2A3A;");
    barChart.lookup(".chart-plot-background").setStyle("-fx-background-color: #1E2A3A;");
    barChart.lookup(".chart-legend").setStyle("-fx-background-color: #1E2A3A;");
    barChart.lookup(".chart-title").setStyle("-fx-text-fill: white;");
    barChart.lookup(".axis-label").setStyle("-fx-text-fill: white;");
    xAxis.setStyle("-fx-tick-label-fill: white; -fx-text-fill: white;");
    yAxis.setStyle("-fx-tick-label-fill: white; -fx-text-fill: white;");
    yAxis.lookup(".axis-label").setStyle("-fx-text-fill: white;");
    
    // Bar colors
    series.getData().forEach(data -> data.getNode().setStyle("-fx-bar-fill: #00B4D8;"));
    };

    // Apply light mode by default on startup
    applyLight.run();

    
    // ===== DARK / LIGHT MODE ACTION =====
    // Mode button action
    modeBtn.setOnAction(e -> {
    if (!isDarkMode) {
        isDarkMode = true;
        modeBtn.setText("☀️ Light Mode");
        applyDark.run();
    } else {
        isDarkMode = false;
        modeBtn.setText("🌙 Dark Mode");
        applyLight.run();
    }
    });
    
   
    

    

    

    //>> Define what happens when the Transfer button is clicked
    transferBtn.setOnAction(e -> {

    // Create the dialog window
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Transfer Money"); // title shown at the top of the dialog

    // Add Confirm and Cancel buttons to the dialog
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    // Create a grid layout to organize elements inside the dialog
    GridPane grid = new GridPane();
    grid.setHgap(10); 
    grid.setVgap(10); 
    grid.setPadding(new Insets(20)); 

    // Dropdown to select the account to transfer FROM
    ComboBox<String> fromBox = new ComboBox<>();
    fromBox.getItems().addAll(
        "💰 Saving- " + mahmoud.getOwnerName(),
        "🏦 Checking- " + ahmed.getOwnerName(),
        "🏠 HomeLoan- " + ali.getOwnerName(),
        "🚗 CarLoan- " + yousef.getOwnerName()
    );

    // Dropdown to select the account to transfer TO
    ComboBox<String> toBox = new ComboBox<>();
    toBox.getItems().addAll(
        "💰 Saving- " + mahmoud.getOwnerName(),
        "🏦 Checking- " + ahmed.getOwnerName(),
        "🏠 HomeLoan- " + ali.getOwnerName(),
        "🚗 CarLoan- " + yousef.getOwnerName()
    );

    
    // When user selects an account in fromBox,remove that account from toBox options
    fromBox.setOnAction(e2 -> {

    String selectedFrom = fromBox.getValue();

    // Clear old items
    toBox.getItems().clear();

    // Add only different accounts
    if (!selectedFrom.contains("Saving"))
        toBox.getItems().add("💰 Saving- " + mahmoud.getOwnerName());

    if (!selectedFrom.contains("Checking"))
        toBox.getItems().add("🏦 Checking- " + ahmed.getOwnerName());

    if (!selectedFrom.contains("HomeLoan"))
        toBox.getItems().add("🏠 HomeLoan- " + ali.getOwnerName());

    if (!selectedFrom.contains("CarLoan"))
        toBox.getItems().add("🚗 CarLoan- " + yousef.getOwnerName());
    });
    
    
    
    // Input field where user types the amount to transfer
    TextField amountField = new TextField();
    amountField.setPromptText("Enter Amount"); // placeholder text"نص تلميحي"

    
    // Add labels and inputs to the grid
    // (column, row)
    grid.add(new Label("From:"), 0, 0);   
    grid.add(fromBox, 1, 0);              
    grid.add(new Label("To:"), 0, 1);     
    grid.add(toBox, 1, 1);               
    grid.add(new Label("Amount:"), 0, 2); 
    grid.add(amountField, 1, 2);         

    // Set the grid as the content of the dialog
    dialog.getDialogPane().setContent(grid);

    
    // Show dialog and wait for user to press OK or Cancel (ifPresent (result -> == if the user pressed a button)
    dialog.showAndWait().ifPresent(result -> {
    
    // Only execute if user pressed OK
    if (result == ButtonType.OK) {
        
        // Get selected account names
        String from = fromBox.getValue();
        String to = toBox.getValue();
        
        // Get the amount entered by user
        double amount;

        //Validation to prevent the character
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException error) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Numbers only!");

            alert.showAndWait();
            return;
        }
        
        
    //Validation:    
    if (amount <= 0) {

       Alert alert = new Alert(Alert.AlertType.ERROR);

       alert.setTitle("Invalid Amount");

       alert.setHeaderText(null);

       alert.setContentText(
        "Amount must be greater than 0!"
        );

    alert.showAndWait();

    return;
    }
        
    
        
        
        // Find the FROM account and withdraw
        if (from.contains("Saving")) mahmoud.withdraw(amount);
        else if (from.contains("Checking")) ahmed.withdraw(amount);
        else if (from.contains("HomeLoan")) ali.withdraw(amount);
        else if (from.contains("CarLoan")) yousef.withdraw(amount);
        
        // Find the TO account and deposit
        if (to.contains("Saving")) mahmoud.deposit(amount);
        else if (to.contains("Checking")) ahmed.deposit(amount);
        else if (to.contains("HomeLoan")) ali.deposit(amount);
        else if (to.contains("CarLoan")) yousef.deposit(amount);
        
        // Update TableView
        int selectedIndex = accountList.getSelectionModel().getSelectedIndex();
        tableView.getItems().clear();
        switch (selectedIndex) {
            case 0: tableView.getItems().addAll(mahmoud.getTransactions()); break;
            case 1: tableView.getItems().addAll(ahmed.getTransactions()); break;
            case 2: tableView.getItems().addAll(ali.getTransactions()); break;
            case 3: tableView.getItems().addAll(yousef.getTransactions()); break;
        }
        
        // Update BarChart with new balances
        series.getData().clear();
        series.getData().add(new XYChart.Data<>("Saving", mahmoud.getBalance()));
        series.getData().add(new XYChart.Data<>("Checking", ahmed.getBalance()));
        series.getData().add(new XYChart.Data<>("HomeLoan", ali.getBalance()));
        series.getData().add(new XYChart.Data<>("CarLoan", yousef.getBalance()));
        
        
        
        // Reapply current mode colors after chart update
        if (isDarkMode)
        {
        series.getData().forEach(data -> data.getNode().setStyle("-fx-bar-fill: #00B4D8;"));
        } 
        else
        {
        series.getData().forEach(data -> data.getNode().setStyle(""));
        }
    
    
        
         }
       });

    });
   
    
    
    
    //>> Define what happens when Monthly Report button is clicked
    reportBtn.setOnAction(e -> {
    
    // Get selected account index from sidebar
    int selectedIndex = accountList.getSelectionModel().getSelectedIndex();
    
    // If no account selected, do nothing
    if (selectedIndex == -1) return;
    
    // Get the selected account
    String report = "";
    switch (selectedIndex)
    {
        case 0:
            mahmoud.applyMonthlyUpdate();
            report = "Account: " + mahmoud.getAccountNumber() + "\n" +
                     "Owner: " + mahmoud.getOwnerName() + "\n" +
                     "Balance After Update: " + mahmoud.getBalance() + "\n" +
                     "Interest Rate: " + (mahmoud.getInterestRate() * 100) + "%\n" +
                     "Transactions: " + mahmoud.getTransactions().size();
            break;
            
        case 1:
            ahmed.applyMonthlyUpdate();
            report = "Account: " + ahmed.getAccountNumber() + "\n" +
                     "Owner: " + ahmed.getOwnerName() + "\n" +
                     "Balance After Update: " + ahmed.getBalance() + "\n" +
                     "Overdraft Limit: " + ahmed.getOverdraftLimit() + "\n" +
                     "Transactions: " + ahmed.getTransactions().size();
            break;
            
        case 2:
            ali.applyMonthlyUpdate();
            report = "Account: " + ali.getAccountNumber() + "\n" +
                     "Owner: " + ali.getOwnerName() + "\n" +
                     "Balance After Update: " + ali.getBalance() + "\n" +
                     "Interest Rate: " + (ali.getInterestRate() * 100) + "%\n" +
                     "Property: " + ali.getPropertyAddress() + "\n" +
                     "Transactions: " + ali.getTransactions().size();
            break;
            
      case 3:
            yousef.applyMonthlyUpdate();
            report = "Account: " + yousef.getAccountNumber() + "\n" +
                     "Owner: " + yousef.getOwnerName() + "\n" +
                     "Balance After Update: " + yousef.getBalance() + "\n" +
                     "Interest Rate: " + (yousef.getInterestRate() * 100) + "%\n" +
                     "Car Model: " + yousef.getCarModel() + "\n" +
                     "Transactions: " + yousef.getTransactions().size();
            break;
            
    }
    
    // Show report in a dialog
    Dialog<ButtonType> reportDialog = new Dialog<>();
    reportDialog.setTitle("Monthly Report");
    reportDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
    reportDialog.getDialogPane().setContent(new Label(report));
    reportDialog.showAndWait();
    
    // Update BarChart with new balances
    series.getData().clear();
    series.getData().add(new XYChart.Data<>("Saving", mahmoud.getBalance()));
    series.getData().add(new XYChart.Data<>("Checking", ahmed.getBalance()));
    series.getData().add(new XYChart.Data<>("HomeLoan", ali.getBalance()));
    series.getData().add(new XYChart.Data<>("CarLoan", yousef.getBalance()));
    
    
    // Reapply current mode colors after chart update
    if (isDarkMode)
    {
    series.getData().forEach(data -> data.getNode().setStyle("-fx-bar-fill: #00B4D8;"));
    }
    else
    {
    series.getData().forEach(data -> data.getNode().setStyle(""));
    }
    
    });
    
    
    
    
    
    Scene scene =new Scene(Bpane ,1200 ,700);
    primaryStage.setTitle("Bank Account Dashboard");
    primaryStage.setScene(scene);
    primaryStage.setResizable(true);
    primaryStage.show();
       
   }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
