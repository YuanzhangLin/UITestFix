package testcases.addressbook.model_based_dataset.po;

import testcases.addressbook.model_based_dataset.sql.SQL_Process;

import java.io.IOException;
import java.sql.SQLException;

public class Address {

    public static void addAddress(String firstname,String lastname) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containAddress(firstname)){
            sql_process.deleteAddress(firstname);
        }
        sql_process.addAddress(firstname,lastname);
        sql_process.Sql_close();
    }

    public static void deleteAddress(String firstname,String lastname) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containAddress(firstname)){
            sql_process.deleteAddress(firstname);
        }
        sql_process.Sql_close();
    }

}
