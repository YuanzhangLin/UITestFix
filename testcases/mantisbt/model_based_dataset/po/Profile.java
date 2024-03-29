package testcases.mantisbt.model_based_dataset.po;

import testcases.mantisbt.model_based_dataset.sql.SQL_Process;

import java.io.IOException;
import java.sql.SQLException;

public class Profile {

    public static void addProfile(String platformName, String os, String os_build) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containProfile(platformName)){
            sql_process.deleteProject(platformName);
        }
        sql_process.addProfile(platformName,os,os_build);
        sql_process.closeSQL();
    }

    public static void deleteProfile(String platformName) throws InterruptedException, SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containProfile(platformName)){
            sql_process.deleteProject(platformName);
        }
        sql_process.closeSQL();
    }


}
