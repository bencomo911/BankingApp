
import java.util.*;
import java.io.*;
public class Main {

    

    /**
     * Method read, scans, and parses data from provided csv file
     * using it to create Checking objects with the data as it is
     * being parsed
     * Every object is then added to an Arraylist of Checking object 
     * which it then returns
     */
    public static ArrayList<Checking>  FileReader() {


        ArrayList<Checking> CheckingAccounts = new ArrayList<Checking>();

        try {
            Scanner sc = new Scanner(new File("/Users/dafnebencomo/Python/VisualStudio/CS3331/PA1/BankUsers.csv"));
            sc.useDelimiter("\n");
            int counter = 0;
            while (sc.hasNext()) {

                //skips feature titles
                if(counter == 0){
                    sc.next();
                    counter++;
                }
                String [] currentLineValues = sc.next().split(",");
                
                Checking myChecking = new Checking();
                for(int i=0; i<currentLineValues.length; i++){
                    switch(i){
                        case 0:
                            myChecking.setFirstName(currentLineValues[i]);
                            break;
                        case 1:
                            myChecking.setLastName(currentLineValues[i]);
                            break;
                        case 2:
                            int conversionInt = Integer.parseInt(currentLineValues[i]);
                            myChecking.setAccountNumber(conversionInt);
                            break;
                        case 3:
                            boolean conversionBool = Boolean.parseBoolean(currentLineValues[i]);
                            myChecking.setCheckings(conversionBool);
                            break;

                        case 4:
                            boolean conversionBool1 = Boolean.parseBoolean(currentLineValues[i]);
                            myChecking.setSavings(conversionBool1);
                            break;

                        case 5:
                            Double conversionDouble = Double.parseDouble(currentLineValues[i]);
                            myChecking.setStartingBalance(conversionDouble);
                            break;

                        case 6:
                            String val = currentLineValues[i].replace("%", "");
                            Double conversion = Double.parseDouble(val);
                            myChecking.setInterestRate(conversion);
                            break;

                        default:
                            System.out.println("Default");
                    }

                }
                myChecking.setcurrBalance(myChecking.getStartingBalance());
                CheckingAccounts.add(myChecking);
            }

            sc.close();
        } catch (Exception e) {
            System.out.println(e.getClass());
        }

        return CheckingAccounts;

    }

    
    public static void displayMenu(String name){
        System.out.println("\n\n********* WELCOME " + name+ " ************\nCOMMAND MENU");
		System.out.println("**************");
		System.out.println("BAL provides current balance in checking account\n" + 
				"\nPAY send money to another person's account\n" + 
				"\nDEPOSIT deposit money into checking account\n" + 
                "\nWITHDRAW withdraw money from checking account\n" + 
				"\nEXIT quit the program");
		System.out.println("*************\n\n");
    }
    public static void main(String[] args) {
        ArrayList<Checking> CustomerList= FileReader();
        HashMap<String, Checking> CustomerHashMap = new HashMap<String, Checking>();
        //convert arraylist to HashMap
        for(Checking account: CustomerList){
            CustomerHashMap.put(account.getFirstName(), account);
        }

        Scanner sc = new Scanner(System.in);
        
        //******* LOGIN **********
        System.out.println("\nEnter username:\nPlease use correct capitalization.");
        String userName = sc.nextLine();
        Checking user = CustomerHashMap.get(userName);
        user.logTransactions();

        displayMenu(user.getFirstName());

        
        //********** MENU SWITCH **********
        System.out.println("What would you like to do? First word of action suffices");
        String userAction = sc.nextLine();
        do{
            switch(userAction.toLowerCase()){
                case("menu"):
                    displayMenu(user.getFirstName());
                    System.out.println("\nWhat would you like to do? First word of action suffices");
                    userAction = sc.nextLine();
                    break;

                case("pay"):
                    System.out.println("Please enter the name of the person you would like to send money to:");
                    String recipient1 = sc.nextLine();
                    System.out.println("Please enter the amount payable:");
                    int amount = Integer.parseInt(sc.nextLine());
                    user.paySomeone(CustomerHashMap.get(recipient1), amount);
                    System.out.println("Enter menu to return to menu:");
                    userAction = sc.nextLine();
                    break;

                case("deposit"):
                    System.out.println("Please enter the amount you would like to deposit:");
                    double total = Double.parseDouble(sc.nextLine());
                    user.deposit(total);
                    System.out.println("Enter menu to return to menu:");
                    userAction = sc.nextLine();
                    break;

                case("withdraw"):
                    System.out.println("Please enter the amount you would like to withdraw:");
                    double total1 = Double.parseDouble(sc.nextLine());
                    user.deposit(total1);
                    System.out.println("Enter menu to return to menu:");
                    userAction = sc.nextLine();
                    break;

            }
        }while(!userAction.equals("exit"));
        
            
        }
 
    }
