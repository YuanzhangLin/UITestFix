package testcases.addressbook.model_based_dataset.sql;

import utils.UtilsProperties;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Random;

import static testcases.Constants.getAddressbookDBName;
import static testcases.Constants.getClarolineDBName;

public class SQL_Process {
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://10.16.10.235:3306/";
    final String USER = "root";
    final String PASS = "";
    String dbName = "";
    int id = new Random(System.currentTimeMillis()).nextInt(550) + 50;
    String version = "";

    public SQL_Process() throws SQLException, ClassNotFoundException, IOException {
        initSql(getAddressbookDBName(), USER, PASS);
        version = UtilsProperties.getConfigProperties().getProperty("version").trim();
    }

    public boolean containAddress(String firstname) throws SQLException, ClassNotFoundException {
        String sql;
        sql = "SELECT * FROM addressbook";
        ResultSet rs = executeQuerySql(sql);
        while (rs.next()) {
            // 通过字段检索
            String code = rs.getString("firstname");
            if (Objects.equals(code, firstname)) {
                return true;
            }
        }
        return false;
    }

    public boolean containGroup(String group_name) throws SQLException, ClassNotFoundException {
        String sql;
        sql = "SELECT * FROM group_list";
        ResultSet rs = executeQuerySql(sql);
        while (rs.next()) {
            // 通过字段检索
            String code = rs.getString("group_name");
            if (Objects.equals(code, group_name)) {
                return true;
            }
        }
        return false;
    }


    public void addAddress(String firstname, String lastname) throws SQLException {

        String sql = "";
        if (version.equals("old")) {
            sql = "INSERT INTO `" + dbName + "`.`addressbook`" +
                    " (`domain_id`, `id`, `firstname`, `lastname`, `company`, `address`," +
                    " `home`, `mobile`, `work`, `fax`, `email`, `email2`, `homepage`, `bday`, " +
                    "`bmonth`, `byear`, `address2`, `phone2`, `notes`, `created`, `modified`, `deprecated`, `password`, `login`, `role`)" +
                    " VALUES ('0', '" + id + "', '" + firstname + "', '" + lastname + "', 'd', '', '', '', '', '', '', '', '', " +
                    "'1', 'May', '3432', '', '', '', " +
                    "'2022-12-02 00:00:00', '2022-12-22 00:00:00', '0000-00-00 00:00:00'," +
                    " NULL, NULL, NULL);";
        } else {
            sql = "INSERT INTO `" + dbName + "`.`addressbook` " +
                    "(`id`, `firstname`, `lastname`, `address`, `home`," +
                    " `mobile`, `work`, `email`, `email2`, `bday`, `bmonth`, `byear`, `address2`, `phone2`)" +
                    " VALUES ('" + id + "', '" + firstname + "', '" + lastname + "', 'asd', 'fe'," +
                    " '', '', '', '', '1', 'May', '123', '', '');";
        }
        stmt.execute(sql);
    }


    public void addGroup(String group_name, String group_header, String group_footer) throws SQLException {
        String sql = "";
        if (version.equals("old")) {
            sql = "INSERT INTO `" + dbName + "`.`group_list` " +
                    "(`domain_id`, `group_id`, `group_parent_id`, `created`, `modified`, " +
                    "`deprecated`, `group_name`, `group_header`, `group_footer`) " +
                    "VALUES ('0', '" + id + "', '1', " +
                    "'2022-12-01 00:00:00', '2022-12-01 00:00:00', NULL, '" + group_name + "', '" + group_header + "', '" +
                    group_footer + "');";
        } else {
            sql = "INSERT INTO `" + dbName + "`.`group_list`(`group_id`, `group_parent_id`, `group_name`, `group_header`, `group_footer`)" +
                    " VALUES ('" + id + "',NULL,'" +
                    group_name + "','" + group_header + "','" + group_footer + "')";
        }

        stmt.execute(sql);
    }

    public void deleteAddress(String firstname) throws SQLException {
        String sql = "DELETE FROM `addressbook` WHERE firstname='" + firstname + "'";
        stmt.execute(sql);
    }

    public void cleanAddress() throws SQLException {
        String sql = "DELETE FROM `addressbook` WHERE 1";
        stmt.execute(sql);
    }

    public void deleteGroup(String group_name) throws SQLException {
        String sql = "DELETE FROM `group_list` WHERE group_name='" + group_name + "'";
        stmt.execute(sql);
    }


    public void cleanGroup() throws SQLException {
        String sql = "DELETE FROM `group_list` WHERE 1";
        stmt.execute(sql);
    }

    public void executeSql(String sql) throws SQLException, ClassNotFoundException {
        stmt.execute(sql);
    }

    public static ResultSet executeQuerySql(String sql) throws SQLException, ClassNotFoundException {

        return stmt.executeQuery(sql);
    }

    static Connection conn = null;
    static Statement stmt = null;
    static int a = 0;

    public Statement initSql(String dbName, String user, String pass) throws SQLException, ClassNotFoundException {
        this.dbName = dbName;
        Class.forName(JDBC_DRIVER);                               // 注册 JDBC 驱动
        System.out.println("连接数据库...");                       // 打开链接
        conn = DriverManager.getConnection(DB_URL + dbName, user, pass);
        System.out.println(" 实例化Statement对象...");       // 执行查询
        stmt = conn.createStatement();
        return stmt;
    }

    public void Sql_close() throws SQLException {
        stmt.close();
        conn.close();
    }

}
