package testcases.collabtive.model_based_dataset.sql;

import utils.UtilsProperties;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Random;

import static testcases.Constants.getCollabtiveDBName;
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
    int id = new Random(System.currentTimeMillis()).nextInt(1550) + 100;

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
    }

    public SQL_Process() throws SQLException, ClassNotFoundException, IOException {
        initSql(getCollabtiveDBName(), USER, PASS);
        version = UtilsProperties.getConfigProperties().getProperty("version").trim();

    }

    public Statement initSql(String dbName, String user, String pass) throws SQLException, ClassNotFoundException {
        this.dbName = dbName;
        System.out.println(dbName);
        Class.forName(JDBC_DRIVER);                               // 注册 JDBC 驱动
        System.out.println("连接数据库...");                       // 打开链接
        conn = DriverManager.getConnection(DB_URL + dbName, user, pass);
        System.out.println(" 实例化Statement对象...");       // 执行查询
        stmt = conn.createStatement();
        return stmt;
    }


    public boolean containUser(String _username) throws SQLException, ClassNotFoundException {
        String sql;
        sql = "SELECT * FROM user";
        ResultSet rs = executeQuerySql(sql);
        while (rs.next()) {
            // 通过字段检索
            String username = rs.getString("name");
            if (Objects.equals(username, _username)) {
                return true;
            }
        }
        return false;
    }

    public boolean containProject(String _projectName) throws SQLException, ClassNotFoundException {
        String sql;
        sql = "SELECT * FROM projekte";
        ResultSet rs = executeQuerySql(sql);
        while (rs.next()) {
            // 通过字段检索
            String username = rs.getString("name");
            if (Objects.equals(username, _projectName)) {
                return true;
            }
        }
        return false;
    }


    public void addUser(String name) throws SQLException {
        id = new Random(System.currentTimeMillis()).nextInt(1550);
        String sql =
                "INSERT INTO `" + dbName + "`.`user` " +
                        "(`ID`, `name`, `email`, `tel1`, `tel2`, `pass`, `company`, `lastlogin`," +
                        " `zip`, `gender`, `url`, `adress`, `adress2`, `state`, `country`, `tags`," +
                        " `locale`, `avatar`, `rate`) " +
                        "VALUES ('" + id + "', '" + name + "', 'd', NULL, NULL, '', '', ''," +
                        " NULL, '', '', '', '', '', '', '', '', '', NULL);";
        stmt.execute(sql);
    }

    public void addUser(String name,int id ) throws SQLException {
        String sql =
                "INSERT INTO `" + dbName + "`.`user` " +
                        "(`ID`, `name`, `email`, `tel1`, `tel2`, `pass`, `company`, `lastlogin`," +
                        " `zip`, `gender`, `url`, `adress`, `adress2`, `state`, `country`, `tags`," +
                        " `locale`, `avatar`, `rate`) " +
                        "VALUES ('" + id + "', '" + name + "', 'd', NULL, NULL, '', '', ''," +
                        " NULL, '', '', '', '', '', '', '', '', '', NULL);";
        stmt.execute(sql);
    }

    public void addProject(String projectName) throws SQLException {
        int id = new Random(System.currentTimeMillis()).nextInt(1550);
        String sql = "INSERT INTO `" + dbName + "`.`projekte` (`ID`, `name`" +
                ", `desc`, `start`, `end`, `status`, `budget`)" +
                " VALUES ('" + id + "', '" + projectName + "', 's', '1670930994', '1670930994', '1', '0')";
        stmt.execute(sql);
        sql = "INSERT INTO `" + dbName + "`.`projekte_assigned` (`ID`, `user`, `projekt`) " +
                "VALUES ('" + id + "', '1', '" + id + "');";
        stmt.execute(sql);
    }

    public void addProject(String projectName, int id) throws SQLException {
        String sql = "INSERT INTO `" + dbName + "`.`projekte` (`ID`, `name`" +
                ", `desc`, `start`, `end`, `status`, `budget`)" +
                " VALUES ('" + id + "', '" + projectName + "', 's', '1670930994', '1670930994', '1', '0')";
        stmt.execute(sql);
        sql = "INSERT INTO `" + dbName + "`.`projekte_assigned` (`ID`, `user`, `projekt`) " +
                "VALUES ('" + id + "', '1', '" + id + "');";
        stmt.execute(sql);
    }

    public void cleanProjecteAssigned() throws SQLException {
        String sql = "DELETE FROM `projekte_assigned` WHERE 1";
        stmt.execute(sql);
    }

    public void addTaskList(int projectId, int tasklistId, int stoneId) throws SQLException {
        String sql = "INSERT INTO `" + dbName + "`.`tasklist`" +
                " (`ID`, `project`, `name`, `desc`, `start`, `status`, `access`, `milestone`) " +
                "VALUES ('" + tasklistId + "', '" + projectId + "', 'My tasks', '', '1670930994', '1', '0', '" + stoneId + "');";
        stmt.execute(sql);
    }

    public void cleanRole() throws SQLException {
        String sql = "DELETE FROM `roles` WHERE name != 'Admin' ";
        stmt.execute(sql);
    }


    public void addTaskAssign(int userId, int taskId) throws SQLException {
        String sql = "INSERT INTO `" + dbName + "`.`tasks_assigned` (`ID`, `user`, `task`)" +
                " VALUES (NULL, '" + userId + "', '" + taskId + "');";
        stmt.execute(sql);
    }

    public void cleanTaskAssign() throws SQLException {
        String sql = "DELETE FROM `tasks_assigned` WHERE 1";
        stmt.execute(sql);
    }

    public void cleanStone() throws SQLException {
        String sql = "DELETE FROM `milestones` WHERE 1";
        stmt.execute(sql);
    }

    public void addMileStone(int stoneId, int projectId) throws SQLException {
        String sql = "INSERT INTO `" + dbName + "`.`milestones`" +
                " (`ID`, `project`, `name`, `desc`, `start`, `end`, `status`) " +
                "VALUES ('" + stoneId + "', '" + projectId + "', 'stone', '421', '1672057409', '1679057409', '1');";
        stmt.execute(sql);
    }



    public void deleteUser(String name) throws SQLException {
        String sql = "DELETE FROM `user` WHERE name = '" + name + "'";
        stmt.execute(sql);
    }

    public void cleanUser() throws SQLException {
        String sql = "DELETE FROM `user` WHERE  name != 'admin'";
        stmt.execute(sql);
    }

    public void deleteProject(String name) throws SQLException {
        String sql = "DELETE FROM `projekte` WHERE name = '" + name + "'";
        stmt.execute(sql);
    }

    public void cleanProject() throws SQLException {
        String sql = "DELETE FROM `projekte` WHERE 1";
        stmt.execute(sql);
    }

    public void cleanTask() throws SQLException {
        String sql = "DELETE FROM `tasks` WHERE 1";
        stmt.execute(sql);
    }

    public void cleanTasklist() throws SQLException {
        String sql = "DELETE FROM `tasklist` WHERE 1";
        stmt.execute(sql);
    }

    public void close() throws SQLException {
        stmt.close();
        conn.close();
    }

    public void addTask(int taskListId, int projectId, int id ) throws SQLException {
        String sql = "INSERT INTO `" + dbName + "`.`tasks`" +
                " (`ID`, `start`, `end`, `title`, `text`, `liste`, `status`, `project`) " +
                "VALUES ('" + id + "', '1670938237', '1679938600', 'taskname', 'taskname', '" + taskListId + "', '1', '" + projectId + "');";
        stmt.execute(sql);
    }

    public ResultSet executeQuerySql(String sql) throws SQLException, ClassNotFoundException {
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
}
