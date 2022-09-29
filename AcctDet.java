package easybank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AcctDet implements getDetails {

    private int userId;
    private String query, acctNum, acctType;
    private double amount, updatedAmt;
    Statement stat;

    /* STATIC VARIABLE. minimum amt and interest rate is common for all the accounts */
    protected static final double MIN_AMT = 500.0, INT_RATE = 0.005;

    public AcctDet(int userId, Statement stat) throws SQLException {
        query = "select * from acct_det where user_id = '" + userId + "'";
        ResultSet acctrs = stat.executeQuery(query);
        acctrs.next();
        acctNum = acctrs.getString("acct_num");
        acctType = acctrs.getString("acct_type");
        this.amount = acctrs.getDouble("amt");
        this.userId = userId;
        this.stat = stat;
    }

    public String[] getDetails() {
        String amtInString = Double.toString(amount);
        return new String[]{acctNum, acctType, amtInString};
    }

    public String withdraw(double withdrawAmt) throws SQLException {
        double balanceAmt = this.amount - withdrawAmt;
        String msg;
        if (balanceAmt < MIN_AMT) {
            updatedAmt = amount;
            msg = "Sorry you don't enough amount for withdraw";
        } else {
            updatedAmt = balanceAmt;
            msg = "Withdraw Successful";
        }
        setAmount(updatedAmt);
        return msg;
    }

    public void deposit(double depositAmt) throws SQLException {
        updatedAmt = this.amount + depositAmt;
        setAmount(updatedAmt);
    }

    private void setAmount(double Amount) throws SQLException {
        query = "update acct_det SET amt = " + updatedAmt + " where user_id = '" + this.userId + "';";
        this.stat.executeUpdate(query);
    }

    public double getAmount() throws SQLException {
        query = "select * from acct_det where user_id = '" + this.userId + "'";
        ResultSet rs = this.stat.executeQuery(query);
        rs.next();
        double amtInAcct = rs.getDouble("amt");
        return amtInAcct;
    }
}