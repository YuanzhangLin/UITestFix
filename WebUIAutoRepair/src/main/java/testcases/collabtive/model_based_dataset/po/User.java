package testcases.collabtive.model_based_dataset.po;

import testcases.collabtive.model_based_dataset.sql.SQL_Process;

import java.io.IOException;
import java.sql.SQLException;

public class User {

    public static void deleteUser(String username) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containUser(username)) {
            sql_process.deleteUser(username);
        }
        sql_process.close();
    }

    public static void cleanUser() throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        sql_process.cleanUser();
        sql_process.close();
    }

    public static void addUser(String username) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containUser(username)) {
            sql_process.deleteUser(username);
        }
        sql_process.addUser(username, 10000);
        sql_process.close();
    }
}
