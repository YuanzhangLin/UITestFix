package testcases.mrbs.model_based_dataset.sql;

import java.io.IOException;
import java.sql.SQLException;

public class Area {

    public static void addArea(String areaName) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containAreas(areaName)) {
            sql_process.deleteAreas(areaName);
        }
        sql_process.addAreas(areaName);
        sql_process.sqlClose();
    }

    public static void addArea(String areaName, String id) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        sql_process.deleteAreas(areaName, id);
        sql_process.addAreas(areaName, id);
        System.out.println(sql_process);
        sql_process.sqlClose();
    }

    public static void deleteArea(String areaName) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containAreas(areaName)) {
            sql_process.deleteAreas(areaName);
        }
        sql_process.addAreas(areaName);
        sql_process.sqlClose();
    }

    public static void cleanArea() throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        sql_process.cleanAreas();
        sql_process.sqlClose();
    }

}
