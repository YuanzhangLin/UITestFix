package testcases.collabtive.model_based_dataset.po;

import testcases.collabtive.model_based_dataset.sql.SQL_Process;

import java.io.IOException;
import java.sql.SQLException;

public class Project {
    public static void addProject(String project) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containProject(project)){
            sql_process.deleteProject(project);
        }
        sql_process.addProject(project);
        sql_process.close();
    }

    public static void deleteProject(String project) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containProject(project)){
            sql_process.deleteProject(project);
        }
        sql_process.close();
    }

    public static void cleanProject() throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        sql_process.cleanProject();
        sql_process.close();
    }

}
