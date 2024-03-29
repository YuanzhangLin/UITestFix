package testcases.Claroline_Test_Suite.model_based_dataset.po;

import testcases.Claroline_Test_Suite.model_based_dataset.sql.SQL_Process;

import java.io.IOException;
import java.sql.SQLException;

public class Category {
    public static void deleteCategory(String name, String code) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        sql_process.deleteCategory(name, code);
        sql_process.close();
    }

    public static void addCategory(String name, String code) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        sql_process.deleteCategory(name, code);
        sql_process.addCategory(name,code);
        sql_process.close();
    }
    public static void clean() throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        sql_process.cleanCategory();
        sql_process.close();
    }
}
