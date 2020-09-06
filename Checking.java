import java.io.*;
import java.util.*;

public class Checking {
    
    /***********************Attributes ***********************/
    private String FirstName;
    private String LastName;
    private int AccountNumber;
    private boolean Checking;
    private boolean Savings;
    private double startingBalance;
    private double interestRate;
    private boolean change = false; //true if a deposit, withdrawl, or payment is made
    private double currBalance = startingBalance; 

    public Checking(){
        
    }

    public void setFirstName(String FirstNameIn){
        this.FirstName = FirstNameIn;
    }

    public String getFirstName(){
        return FirstName;
    }

    public void setLastName(String LastNameIn){
        this.LastName = LastNameIn;
    }

    public String getLastName(){
        return LastName;
    }

    public void setAccountNumber(int AccountNumberIn){
        this.AccountNumber = AccountNumberIn;
    }

    public int getAccountNumber(){
        return AccountNumber;
    }

    public void setSavings(boolean SavingsIn){
        this.Savings = SavingsIn;
    }

    public boolean getSavings(){
        return Savings;
    }

    public void setCheckings(boolean CheckingIn){
        this.Checking = CheckingIn;
    }

    public boolean getCheckings(){
        return Checking;
    }

    public void setStartingBalance(double StartingBalanceIn){
        this.startingBalance = StartingBalanceIn;
    }

    public double getStartingBalance(){
        return startingBalance;
    }

    public void setInterestRate (double InterestRateIn){
        this.interestRate = InterestRateIn;
    }

    public double getInterestRate (){
        return interestRate;
    }

    public boolean getChange(){
        return change;
    }
    public void setChange(){
        this.change = true;
    }

    public void setcurrBalance(double currBalanceIn){
        this.currBalance = currBalanceIn;
    }

    public double getcurrBalance(){
        return currBalance;
    }

    
    /*********************** Methods ***********************/

    

    /**
     * balanceInquiry() helper function
     * checks value of change flag
     * returns startingBalance if false and currentBalance otherwise
     */
    public double currentBalance(){
        if(!change){
            return startingBalance;
        }

        return currBalance;
    }
    //completed
    public double balanceInquiry(){

        return currentBalance();
    }

    //completed
    public void paySomeone(Checking recipient, double amountPay){
        //first check if you have sufficient funds
        if(currBalance < amountPay && amountPay <0){
            System.out.println("Error: Check amount entered and balance.");
            return;
        }else{

            //log change
            String logInput = ("\n" +FirstName + " " + LastName +  " paid $" + amountPay + " to " + recipient.getFirstName() + " " + recipient.getLastName());
            WriteToFile(logInput);

            recipient.setcurrBalance(recipient.currBalance += amountPay);
            currBalance-=amountPay;
        }
    }

    //completed
    public double deposit(double amount){
        if(amount < 0){
            System.out.println("Insufficient funds to complete transaction");
            return 0;
        }

        double currentBal = currentBalance();
        double finalBal = (currentBal + amount);
        setcurrBalance(finalBal);
        setChange();

        //log change
        String logInput = ("\n" +FirstName + " " + LastName +  " deposited $" + amount + ".");
        WriteToFile(logInput);


        return finalBal;
    }
    //completed
    public double withdraw(double amount){

        if(currBalance < amount){
            System.out.println("Insufficient funds to complete transaction.");
            return 0;
        }

        double currentBal = currentBalance();
        double finalBal = (currentBal - amount);
        setcurrBalance(finalBal);
        setChange();

        //log change
        String logInput = ("\n" +FirstName + " " + LastName +  " withdrew $" + amount + ".");
        WriteToFile(logInput);


        return finalBal;
    }

    /**
     * creates a new file in user's desktop
     */
    public File createFile(){
        //create new txt file
        try{
            File log = new File("LogTransaction.txt");

            if(log.createNewFile()){
                System.out.println("New File created: " + log.getName());
            }else{
                System.out.println("ERROR: File already exists.");
            }


        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Recieves String edit which it then appends
     * to created file
     */
    public void WriteToFile(String edit){
        try{
            FileWriter w = new FileWriter("LogTransaction.txt", true);
            w.write(edit);
            w.close();
        }catch(IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }
    }
    
    public boolean logTransactions(){
        File logTrans = createFile();

        return true;
    }

}