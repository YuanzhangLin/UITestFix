package testcases.mantisbt.model_based_dataset.sql;

import utils.UtilsProperties;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Random;

import static testcases.Constants.getClarolineDBName;
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
    int id = new Random(System.currentTimeMillis()).nextInt(550) + 100;

    public SQL_Process() throws SQLException, ClassNotFoundException, IOException {
        initSql(getMantisbtDBName(), USER, PASS);
        version = UtilsProperties.getConfigProperties().getProperty("version").trim();
    }

    public Statement initSql(String dbName, String user, String pass) throws SQLException, ClassNotFoundException {
        this.dbName = dbName;
        Class.forName(JDBC_DRIVER);                               // 注册 JDBC 驱动
        System.out.println("连接数据库...");                       // 打开链接
        this.conn = DriverManager.getConnection(DB_URL + dbName, user, pass);
        System.out.println(" 实例化Statement对象...");       // 执行查询
        this.stmt = conn.createStatement();
        return stmt;
    }

    public boolean containUser(String _username) throws SQLException, ClassNotFoundException {
        String sql;
        sql = "SELECT * FROM mantis_user_table";
        ResultSet rs = executeQuerySql(sql);
        while (rs.next()) {
            // 通过字段检索
            String username = rs.getString("username");
            if (Objects.equals(username, _username)) {
                return true;
            }
        }
        return false;
    }

    public boolean containNews(String _newsName) throws SQLException, ClassNotFoundException {
        String sql;
        sql = "SELECT * FROM mantis_news_table";
        ResultSet rs = executeQuerySql(sql);
        while (rs.next()) {
            // 通过字段检索
            String newsName = rs.getString("headline");
            if (Objects.equals(newsName, _newsName)) {
                return true;
            }
        }
        return false;
    }

    public boolean containProject(String _name) throws SQLException, ClassNotFoundException {
        String sql;
        sql = "SELECT * FROM mantis_project_table";
        ResultSet rs = executeQuerySql(sql);
        while (rs.next()) {
            // 通过字段检索
            String name = rs.getString("name");
            if (Objects.equals(name, _name)) {
                return true;
            }
        }
        return false;
    }

    public boolean containProfile(String platformName) throws SQLException, ClassNotFoundException {
        String sql;
        sql = "SELECT * FROM mantis_user_profile_table";
        ResultSet rs = executeQuerySql(sql);
        while (rs.next()) {
            // 通过字段检索
            String platform = rs.getString("platform");
            if (Objects.equals(platform, platformName)) {
                return true;
            }
        }
        return false;
    }

    public String getProjectId(String _name) throws SQLException, ClassNotFoundException {
        String sql;
        sql = "SELECT * FROM mantis_project_table";
        ResultSet rs = executeQuerySql(sql);
        while (rs.next()) {
            // 通过字段检索
            String name = rs.getString("name");
            if (Objects.equals(name, _name)) {
                return rs.getString("id");
            }
        }
        return "";
    }

    public void addUser(String username, String realname, String email, String password) throws SQLException, ClassNotFoundException {
        String sql = "";
        if (version.equals("old")) {
            sql = "INSERT INTO `" + dbName + "`.`mantis_user_table` (`id`, `username`, `realname`, `email`, " +
                    "`password`, `date_created`, `last_visit`, `enabled`, `protected`, `access_level`, " +
                    "`login_count`, `lost_password_request_count`, `failed_login_count`, `cookie_string`)" +
                    " VALUES ('" + id + "', '" + username + "', '" + realname + "', '" + email + "', " +
                    "'" + password + "','1970-01-01 00:00:01'," +
                    "'1970-01-01 00:00:01', '1', '0', '10', '0', '0', '0', '" + new Random().nextInt(100000) + "');";
        } else {
            sql = "INSERT INTO `" + dbName + "`.`mantis_user_table` (`id`, `username`, `realname`, `email`," +
                    " `password`, `enabled`, `protected`, `access_level`, `login_count`, " +
                    "`lost_password_request_count`, `failed_login_count`, `cookie_string`, `last_visit`, " +
                    "`date_created`)" +
                    " VALUES ('" + id + "', '" + username + "', '" + realname + "', '" + email + "', " +
                    "'" + password + "', " +
                    "'1', '0', '10', '0', '0', '0', '" + new Random().nextInt(10000) + "', '1', '1')";
        }
        System.out.println(sql);
        stmt.execute(sql);
    }

    public void addProfile(String platformName, String os, String os_build) throws SQLException {
        String sql = "";
        id = new Random().nextInt(550) + 20;
        sql = "INSERT INTO `" + dbName + "`.`mantis_user_profile_table` " +
                "(`id`, `user_id`, `platform`, `os`, `os_build`, `description`) " +
                "VALUES (" + id + ", '0', '" + platformName + "', '" + os + "', '" + os_build + "', '');";
        stmt.execute(sql);
    }

    public void addNews(String newsName, String body) throws SQLException {
        String sql = "";
        if (version.equals("old")) {
            sql = "INSERT INTO `" + dbName + "`.`mantis_news_table` (`id`, `project_id`, `poster_id`, `view_state`, `announcement`," +
                    " `headline`, `body`, `last_modified`, `date_posted`)" +
                    " VALUES (" + id + ", '0', '0', '10', '0', '" + newsName + "', '" + body + "', '1970-01-01 00:00:11', '1970-01-01 00:00:01');";
        } else {
            sql  = "INSERT INTO `" + dbName + "`.`mantis_news_table` (`id`, `project_id`, `poster_id`, `view_state`, `announcement`," +
                    " `headline`, `body`, `last_modified`, `date_posted`)" +
                    " VALUES (" + id + ", '0', '0', '10', '0', '" + newsName + "', '" + body + "', '1', '1');";
        }

        System.out.println(sql);
        stmt.execute(sql);
    }

    public void addProject(String name) throws SQLException {
        String sql = "";
        sql = "INSERT INTO `" + dbName + "`.`mantis_project_table` (`id`, `name`, `status`, `enabled`, `view_state`, `access_min`," +
                " `file_path`, `description`) " +
                "VALUES ('" + id + "', '" + name + "', '30', '1', '10', '10', '', '');";
        stmt.execute(sql);
        System.out.println(sql);
    }


    public void addSubProjectRelation(String child, String parent) throws SQLException {
        String sql = "";
        sql = "INSERT INTO `" + dbName + "`.`mantis_project_hierarchy_table` (`child_id`, `parent_id`) VALUES ('" +
                "" + child + "', '" + parent + "');";
        stmt.execute(sql);
    }

    public void cleanSubProjectRelation() throws SQLException {
        String sqlxg;
        sqlxg = "DELETE FROM `mantis_project_hierarchy_table` WHERE 1 ";
        System.out.println(sqlxg);
        stmt.execute(sqlxg);
    }

    public void deleteUser(String username) throws SQLException {
        String sqlxg;
        sqlxg = "delete from mantis_user_table where username = '" + username + "'";
        stmt.execute(sqlxg);
    }

    public void deleteUser(String username, String realname) throws SQLException {
        String sqlxg;
        sqlxg = "delete from mantis_user_table where username = '" + username + "' or realname = '" + realname + "'";
        stmt.execute(sqlxg);
    }

    public void clearUser() throws SQLException {
        String sql = "DELETE FROM `mantis_user_table` WHERE `username` != 'administrator'";
        stmt.execute(sql);
    }

    public void deleteNews(String newsName) throws SQLException {
        String sqlxg;
        sqlxg = "delete from mantis_news_table where headline = '" + newsName + "'";
        System.out.println(sqlxg);
        stmt.execute(sqlxg);
    }

    public void deleteProject(String name) throws SQLException {
        String sqlxg;
        sqlxg = "delete from mantis_project_table where name = '" + name + "'";
        System.out.println(sqlxg);
        stmt.execute(sqlxg);
    }

    public void deleteProfile(String platform) throws SQLException {
        String sqlxg;
        sqlxg = "delete from mantis_user_profile_table where platform = '" + platform + "'";
        System.out.println(sqlxg);
        stmt.execute(sqlxg);
    }


    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containProject("Project002")) {
            sql_process.deleteProject("Project002");
        }
        sql_process.addProject("Project002");
    }

    public void closeSQL() throws SQLException {
        this.stmt.close();
        this.conn.close();
    }

    public ResultSet executeQuerySql(String sql) throws SQLException, ClassNotFoundException {
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
}
