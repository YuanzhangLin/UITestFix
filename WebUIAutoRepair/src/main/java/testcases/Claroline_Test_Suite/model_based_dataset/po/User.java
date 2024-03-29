package testcases.Claroline_Test_Suite.model_based_dataset.po;

import testcases.Claroline_Test_Suite.model_based_dataset.sql.SQL_Process;

import java.io.IOException;
import java.sql.SQLException;

public class User {

    public static void addUser(String _nom, String _prenom, String username, String password) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (!sql_process.containUser(_nom,_prenom)){
            sql_process.addUser(_nom,_prenom,username,password);
        }
        sql_process.close();
    }
}
