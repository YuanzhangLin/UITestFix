package testcases.collabtive.model_based_dataset.po;

import testcases.collabtive.model_based_dataset.sql.SQL_Process;

import java.io.IOException;
import java.sql.SQLException;

public class Task {
    public static void addTask() throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        sql_process.cleanProject();
        sql_process.cleanTasklist();
        sql_process.cleanTask();
        sql_process.cleanProjecteAssigned();
        sql_process.cleanStone();
        sql_process.cleanTaskAssign();
        sql_process.addProject("Project", 10000);
        sql_process.addMileStone(9999, 10000);
        sql_process.addTaskList(10000, 1000, 9999);
        sql_process.addTask(1000, 10000,999);
        sql_process.addTaskAssign(1,999);
        sql_process.close();
    }

    public static void cleanTask() throws SQLException, IOException, ClassNotFoundException{
        SQL_Process sql_process = new SQL_Process();
        sql_process.cleanProject();
        sql_process.cleanTasklist();
        sql_process.cleanTask();
        sql_process.cleanProjecteAssigned();
        sql_process.cleanStone();
        sql_process.cleanTaskAssign();
        sql_process.close();
    }


}
