package testcases.mrbs.model_based_dataset.sql;

import utils.UtilsProperties;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Random;

import static testcases.Constants.getMRBSDBName;
import static testcases.Constants.getMantisbtDBName;

public class SQL_Process {
    Connection conn = null;
    Statement stmt = null;
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://10.16.10.235:3306/";
    final String USER = "root";
    final String PASS = "";
    String dbName = "";
    String version = "";
    int id = new Random().nextInt(850) + 10;

    public SQL_Process() throws SQLException, ClassNotFoundException, IOException {
        initSql(getMRBSDBName(), USER, PASS);
        version = UtilsProperties.getConfigProperties().getProperty("version").trim();

    }

    public boolean containAreas(String _areaName) throws SQLException, ClassNotFoundException {
        String sql;
        sql = "SELECT * FROM mrbs_area";
        ResultSet rs = executeQuerySql(sql);
        while (rs.next()) {
            // 通过字段检索
            String areaName = rs.getString("area_name");
            if (Objects.equals(areaName, _areaName)) {
                return true;
            }
        }
        return false;
    }

    public boolean containRoom(String name) throws SQLException, ClassNotFoundException {
        String sql;
        sql = "SELECT * FROM mrbs_room";
        ResultSet rs = executeQuerySql(sql);
        while (rs.next()) {
            // 通过字段检索
            String room_name = rs.getString("room_name");
            if (Objects.equals(room_name, name)) {
                return true;
            }
        }
        return false;
    }

    public void addAreas(String areaName) throws SQLException {
        String sql = "INSERT INTO `" + dbName + "`.`mrbs_area` (`id`, " +
                "`area_name`, `area_admin_email`)" +
                " VALUES ('" + id + "', '" + areaName + "', NULL);";
        stmt.execute(sql);
    }

    public void addAreas(String areaName,String id) throws SQLException {
        String sql = "INSERT INTO `" + dbName + "`.`mrbs_area` (`id`, " +
                "`area_name`, `area_admin_email`)" +
                " VALUES ('" + id + "', '" + areaName + "', NULL);";
        stmt.execute(sql);
    }

    public void addEntry(String name, String description) throws SQLException {
        id = new Random().nextInt(1000);
        String sql = "INSERT INTO `mrbs1261`.`mrbs_entry`" +
                " (`id`, `start_time`, `end_time`, `entry_type`, " +
                "`repeat_id`, `room_id`, `timestamp`, `create_by`, " +
                "`name`, `type`, `description`) VALUES " +
                "('" + id + "', '1672599360', '1672599460'," +
                " '0', '0', '1'," +
                " CURRENT_TIMESTAMP, '', '" + name + "', 'E', '" + description + "');";
        stmt.execute(sql);
    }


    public void addRoom(String name, String cap) throws SQLException {
        id = new Random().nextInt(1000);
        String sql = "INSERT INTO `" + dbName + "`.`mrbs_room`" +
                " (`id`, `area_id`, `room_name`, `description`," +
                " `capacity`, `room_admin_email`) " +
                "VALUES ('" + id + "', '1', '" + name + "'," +
                " 'er', '" + cap + "', 'er');";
        stmt.execute(sql);
    }

    public void addRoom(String name, String cap, String area_id) throws SQLException {
        id = new Random().nextInt(1000);
        String sql = "INSERT INTO `" + dbName + "`.`mrbs_room`" +
                " (`id`, `area_id`, `room_name`, `description`," +
                " `capacity`, `room_admin_email`) " +
                "VALUES ('" + id + "', '" + area_id + "', '" + name + "'," +
                " 'er', '" + cap + "', 'email@qq.com');";
        System.out.println(sql);
        stmt.execute(sql);
    }


    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql = new SQL_Process();

    }

    public void deleteAreas(String areaName) throws SQLException {
        String sqlxg;
        sqlxg = "delete from mrbs_area where area_name = '" + areaName + "'";
        stmt.execute(sqlxg);
    }

    public void deleteAreas(String areaName, String id ) throws SQLException {
        String sqlxg;
        sqlxg = "delete from mrbs_area where area_name = '" + areaName + "'";
        sqlxg = "delete from mrbs_area where id = '" + id + "'";
        stmt.execute(sqlxg);
    }

    public void cleanAreas() throws SQLException {
        String sqlxg;
        sqlxg = "delete from mrbs_area where 1";
        stmt.execute(sqlxg);
    }

    public void clearEntry() throws SQLException {
        String sql = "DELETE FROM `mrbs_entry` WHERE 1";
        stmt.execute(sql);
    }

    public void deleteRoom(String name) throws SQLException {
        String sql = "DELETE FROM `mrbs_room` WHERE  room_name = '" + name + "'";
        stmt.execute(sql);
    }

    public void clearRoom() throws SQLException {
        String sql = "DELETE FROM `mrbs_room` WHERE 1";
        stmt.execute(sql);
    }

    public Statement initSql(String dbName, String user, String pass) throws SQLException, ClassNotFoundException {
        this.dbName = dbName;
        Class.forName(JDBC_DRIVER);                               // 注册 JDBC 驱动
        System.out.println("连接数据库...");                       // 打开链接
        conn = DriverManager.getConnection(DB_URL + dbName, user, pass);
        System.out.println(" 实例化Statement对象...");       // 执行查询
        stmt = conn.createStatement();
        return stmt;
    }

    public void sqlClose() throws SQLException {
        stmt.close();
        conn.close();
    }

    public ResultSet executeQuerySql(String sql) throws SQLException, ClassNotFoundException {
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
}
