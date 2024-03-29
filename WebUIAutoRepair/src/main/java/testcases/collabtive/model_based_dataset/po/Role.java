package testcases.collabtive.model_based_dataset.po;

import testcases.collabtive.model_based_dataset.sql.SQL_Process;

import java.io.IOException;
import java.sql.SQLException;

public class Role {

    public static void cleanProject() throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        sql_process.cleanRole();
        sql_process.close();
    }
}
