package testcases.mrbs.model_based_dataset.sql;

import java.io.IOException;
import java.sql.SQLException;

public class Room {
    public static void addRoom(String name, String cap) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containRoom(name)) {
            sql_process.deleteRoom(name);
        }
        sql_process.addRoom(name, cap);
        sql_process.sqlClose();
    }

    public static void addRoom(String name, String cap, String area_id) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containRoom(name)) {
            sql_process.deleteRoom(name);
        }
        sql_process.addRoom(name, cap, area_id);
        sql_process.sqlClose();
    }

    public static void clearRoom() throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        sql_process.clearRoom();
        sql_process.sqlClose();
    }

    public static void deleteRoom(String name) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containRoom(name)) {
            sql_process.deleteRoom(name);
        }
        sql_process.sqlClose();
    }
}
