package easybank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer implements getDetails {
    String custsql, firstName, lastName;

    public Customer(int user_id, String password, Statement stat) throws SQLException {
        custsql = "select * from customer where user_id = '" + user_id + "'" + "and pwd = '" + password + "'";
        ResultSet custrs = stat.executeQuery(custsql);
        custrs.next();
        this.firstName = custrs.getString("first_name");
        this.lastName = custrs.getString("last_name");
    }

    public String[] getDetails() {
        return new String[]{firstName, lastName};
    }
}
