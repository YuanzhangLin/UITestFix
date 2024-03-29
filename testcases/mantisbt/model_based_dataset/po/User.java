package testcases.mantisbt.model_based_dataset.po;

import testcases.mantisbt.model_based_dataset.sql.SQL_Process;

import java.io.IOException;
import java.sql.SQLException;

public class User {

    public static void addUser(String username, String realname, String email) throws InterruptedException, SQLException, ClassNotFoundException, IOException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containUser(username)){
            sql_process.deleteUser(username);
        }
        sql_process.addUser(username,realname,email,"123456");

        sql_process.closeSQL();
    }

    public static void addUser(String username, String realname, String email,String password) throws InterruptedException, SQLException, ClassNotFoundException, IOException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containUser(username)){
            sql_process.deleteUser(username);
        }
        sql_process.addUser(username,realname,email,password);
        sql_process.closeSQL();
    }

    public static void deleteUser(String username) throws InterruptedException, SQLException, ClassNotFoundException, IOException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containUser(username)){
            sql_process.deleteUser(username);
        }
        sql_process.closeSQL();
    }
    public static void deleteUser(String username, String realname) throws InterruptedException, SQLException, ClassNotFoundException, IOException {
        SQL_Process sql_process = new SQL_Process();
//        if (sql_process.containUser(username)){
            sql_process.deleteUser(username,realname);
//        }
        sql_process.closeSQL();
    }

    public static void clearUser() throws  SQLException, ClassNotFoundException, IOException {
        SQL_Process sql_process = new SQL_Process();
        sql_process.clearUser();
        sql_process.closeSQL();
    }
}
