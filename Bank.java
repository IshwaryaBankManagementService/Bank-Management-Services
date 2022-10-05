package easybank;

import java.sql.*;
import java.sql.Date;
//import java.time.LocalDateTime.*;
import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

public class Bank {
    private static Scanner myObj = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to EASY BANK");

        String userOption = null, option;
        double Amount;

        try {
            Statement stat = getConnection(); // connection establishment

            System.out.println("User ID: ");
            int userID = myObj.nextInt();
            myObj.nextLine();

            System.out.println("Password: ");
            String password = myObj.nextLine();

            // Constructor - stores the customer and account details for particular customer
            Customer cust1 = new Customer(userID, password, stat);
            AcctDet acctDet = new AcctDet(userID, stat);

            // Getting details and printing the values
            String name[] = cust1.getDetails();
            System.out.println("Hi " + name[0].concat(name[1]) + " welcome!");

            String acctDetails[] = acctDet.getDetails();

            System.out.println("Your account details, \n ACCOUNT TYPE: " + acctDetails[1] + "\n ACCOUNT NUMBER: " + acctDetails[0]
                    + "\n AMOUNT: $" + acctDetails[2]);

            System.out.println("Do you wish to (Case sensitive. Kindly type as below)");
            for (UserChoice opt : UserChoice.values()) {
                System.out.println(opt);
            }
            String userOpt = myObj.nextLine();
            userOption = userOpt.toUpperCase();
            UserChoice opt = UserChoice.valueOf(userOption);
            option = opt.getOption();

            switch (option) {
                case "1":
                    Amount = getAmountInDouble();

                    String withdrawMsg = acctDet.withdraw(Amount);
                    System.out.println(withdrawMsg);
                    break;
                case "2":
                    Amount = getAmountInDouble();

                    acctDet.deposit(Amount);
                    System.out.println("Deposit Successful");
                    break;
                default:
                    System.out.println("Wrong Option!");
                    return;
            }
            double balanceAmt = acctDet.getAmount();

            stat.close();
            System.gc();
            System.out.println("The balance in your amount is: $" + balanceAmt);

        } catch (NumberFormatException | SQLException idErr) {
            System.out.println("Incorrect format or value mismatch");
        }
/*//        Interest calculation method
            Calendar cal = Calendar.getInstance();
            Date todayDate = (Date) cal.getTime();
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date monthLastDay = (Date) cal.getTime();
            if (todayDate.equals(monthLastDay)) {
                for (AcctDet allCustAcct : newaccts) {
                    allCustAcct.calcInterest();
                }
            }*/
    }

    static Statement getConnection() throws SQLException {
        Statement stat;
        String host = "jdbc:mysql://localhost:3306/easybank";
        String uName = "root";
        String uPass = *****;
        Connection con = DriverManager.getConnection(host, uName, uPass);
        stat = con.createStatement();
        return stat;
    }

    static double getAmountInDouble() {
        System.out.println("Enter the amount: ");
        return (myObj.nextDouble());
    }

}



