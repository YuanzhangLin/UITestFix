package testcases.mantisbt.model_based_dataset.po;

import testcases.mantisbt.model_based_dataset.sql.SQL_Process;

import java.io.IOException;
import java.sql.SQLException;

public class Project {

    public static void addProject(String projectName) throws InterruptedException, SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        sql_process.deleteProject(projectName);
        sql_process.addProject(projectName);
        sql_process.closeSQL();
    }

    public static void deleteProject(String projectName) throws InterruptedException, SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containProject(projectName)){
            sql_process.deleteProject(projectName);
        }
        sql_process.closeSQL();
    }
    public static void cleanSubProject() throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        sql_process.cleanSubProjectRelation();
        sql_process.closeSQL();
    }

    public static void deleteSubProject(String subjectName) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containProject(subjectName)){
            sql_process.deleteProject(subjectName);
        }
        sql_process.cleanSubProjectRelation();
        sql_process.closeSQL();
    }

    public static void addSubProject(String subProjectName, String project) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (!sql_process.containProject(subProjectName)){
            sql_process.addProject(subProjectName);
        }
        if (!sql_process.containProject(project)){
            sql_process.addProject(project);
        }
        sql_process.cleanSubProjectRelation();
        sql_process.addSubProjectRelation(sql_process.getProjectId(subProjectName),sql_process.getProjectId(project));
        sql_process.closeSQL();
    }
}
