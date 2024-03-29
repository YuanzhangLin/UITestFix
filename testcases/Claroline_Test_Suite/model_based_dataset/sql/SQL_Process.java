package testcases.Claroline_Test_Suite.model_based_dataset.sql;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Random;

import static testcases.Constants.getClarolineDBName;

public class SQL_Process {
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://10.16.10.235:3306/";
    final String USER = "root";
    final String PASS = "";
    String dbName = "";
    int id = new Random(System.currentTimeMillis()).nextInt(150) + 50;

    public SQL_Process() throws SQLException, ClassNotFoundException, IOException {
        initSql(getClarolineDBName(), USER, PASS);
    }

    public boolean containUser(String _nom, String _prenom) throws SQLException, ClassNotFoundException {
        String sql;
        sql = "SELECT * FROM cl_user";
        ResultSet rs = executeQuerySql(sql);
        while (rs.next()) {
            // 通过字段检索
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            if (Objects.equals(nom, _nom) && Objects.equals(_prenom, prenom)) {
                return true;
            }
        }
        return false;
    }


    public boolean containClass(String _className) throws SQLException, ClassNotFoundException {
        String sql;
        sql = "SELECT * FROM cl_class";
        ResultSet rs = executeQuerySql(sql);
        while (rs.next()) {
            // 通过字段检索
            String className = rs.getString("name");
            if (Objects.equals(className, _className)) {
                return true;
            }
        }
        return false;
    }

    public boolean containCourse(String _code) throws SQLException, ClassNotFoundException {
        String sql;
        sql = "SELECT * FROM cl_cours";
        ResultSet rs = executeQuerySql(sql);
        while (rs.next()) {
            // 通过字段检索
            String code = rs.getString("code");
            if (Objects.equals(code, _code)) {
                return true;
            }
            String administrativeNumber = rs.getString("administrativeNumber");
            if (Objects.equals(administrativeNumber, _code)) {
                return true;
            }
        }
        return false;
    }

    public boolean containCategory(String _name, String _code) throws SQLException, ClassNotFoundException {
        String sql;
        sql = "SELECT * FROM cl_category";
        ResultSet rs = executeQuerySql(sql);
        while (rs.next()) {
            // 通过字段检索
            String name = rs.getString("name");
            String code = rs.getString("code");
            if (Objects.equals(name, _name) && Objects.equals(code, _code)) {
                return true;
            }
        }
        return false;
    }

    public void addUser(String _nom, String _prenom) throws SQLException {
        String data = "INSERT INTO `" + dbName + "`.`cl_user`" +
                " (`user_id`, `nom`, `prenom`, `username`, `password`, " +
                "`language`, `authSource`, `email`, `officialCode`, `officialEmail`, " +
                "`phoneNumber`, `pictureUri`, `creatorId`, `isPlatformAdmin`, `isCourseCreator`) " +
                "VALUES (" + id + ", '" + _nom + "', '" + _prenom + "', 'empty', 'empty', NULL, 'claroline'," +
                " NULL, NULL, NULL, NULL, NULL, NULL, '0', '0');";
        stmt.execute(data);
    }

    public void addUser(String _nom, String _prenom, String username, String password) throws SQLException {
        String data = "INSERT INTO `" + dbName + "`.`cl_user`" +
                " (`user_id`, `nom`, `prenom`, `username`, `password`, " +
                "`language`, `authSource`, `email`, `officialCode`, `officialEmail`, " +
                "`phoneNumber`, `pictureUri`, `creatorId`, `isPlatformAdmin`, `isCourseCreator`) " +
                "VALUES (" + id + ", '" + _nom + "', '" + _prenom + "', '" +
                username + "', '" + password + "', NULL, 'claroline'," +
                " NULL, NULL, NULL, NULL, NULL, NULL, '0', '0');";
        stmt.execute(data);
    }

    public void addClass(String name) throws SQLException {
        String sql = "INSERT INTO `" + dbName + "`.`cl_class` " +
                "(`id`, `name`, `class_parent_id`, `class_level`)" +
                " VALUES ('15', '" + name + "', NULL, '0');";
        stmt.execute(sql);

    }

    public void addCategory(String name, String code) throws SQLException {
        id = new Random().nextInt(1000) + 30;
        int rank = new Random().nextInt(500);
        String sql = "INSERT INTO `" + dbName + "`.`cl_category`" +
                " (`id`, `name`, `code`, `idParent`, `rank`, `visible`, `canHaveCoursesChild`)" +
                " VALUES ('" + id + "', '" + name + "', '" + code + "', '0', '" + rank + "', '1', '1');";
        stmt.execute(sql);
    }

    public void addCourse(String code, String administrativeNumber, String directory,
                          String dbName, String intitule, String language, String titulaires,
                          String email) {
        String sql = "INSERT INTO `" + dbName + "`.`cl_cours`" +
                " (`cours_id`, `code`, `isSourceCourse`, `sourceCourseId`," +
                " `administrativeNumber`, `directory`, `dbName`, `language`, " +
                "`intitule`, `titulaires`, `email`, `extLinkName`, `extLinkUrl`, " +
                "`visibility`, `access`, `registration`, `registrationKey`," +
                " `diskQuota`, `versionDb`, `versionClaro`, `lastVisit`, `lastEdit`, " +
                "`creationDate`, `expirationDate`, `defaultProfileId`, `status`, `userLimit`) " +
                "VALUES ('" + id + "', " + code + ", '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL," +
                " NULL, 'visible', 'public', 'open', NULL, NULL, 'NEVER SET', 'NEVER SET'," +
                " NULL, NULL, NULL, NULL, '', 'enable', '0')";
    }

    public void deleteUser(String _nom, String _prenom) throws SQLException {
        String sqlxg;
        sqlxg = "delete from cl_user where nom = '" + _nom + "' and prenom = '" + _prenom + "'";
        stmt.execute(sqlxg);
    }

    public void deleteCategory(String name, String code) throws SQLException {
        String sqlxg;
        sqlxg = "delete from cl_category where name = '" + name + "' and code = '" + code + "'";
        stmt.execute(sqlxg);
    }

    public void cleanCategory() throws SQLException {
        String sqlxg;
        sqlxg = "delete from cl_category where 1";
        stmt.execute(sqlxg);
    }

    public void deleteClass(String name) throws SQLException {
        String sqlxg;
        sqlxg = "delete from cl_class where name = '" + name + "'";
        stmt.execute(sqlxg);
    }

    public void deleteCourse(String code) throws SQLException {
        String sqlxg;
        sqlxg = "delete from cl_cours where code = '" + code + "'";
        stmt.execute(sqlxg);
        sqlxg = "delete from cl_cours where administrativeNumber = '" + code + "'";
        stmt.execute(sqlxg);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        SQL_Process SQLProcess = new SQL_Process();

//        user.initSql("jdbc:mysql://10.16.10.235:3306/claroline1107","myuser","mypassword","claroline1107");
//        if (!SQLProcess.containUser("test", "test2")) {
//            SQLProcess.addUser("test", "test2");
//        }
//        SQLProcess.deleteUser("test", "test2");
    }


    public void executeSql(String sql) throws SQLException, ClassNotFoundException {
        stmt.execute(sql);
    }

    public ResultSet executeQuerySql(String sql) throws SQLException, ClassNotFoundException {

        return stmt.executeQuery(sql);
    }

    static Connection conn = null;
    static Statement stmt = null;

    public Statement initSql(String dbName, String user, String pass) throws SQLException, ClassNotFoundException {
        this.dbName = dbName;
        Class.forName(JDBC_DRIVER);                               // 注册 JDBC 驱动
        System.out.println("连接数据库...");                       // 打开链接
        conn = DriverManager.getConnection(DB_URL + dbName, user, pass);
        System.out.println(" 实例化Statement对象...");       // 执行查询
        stmt = conn.createStatement();
        return stmt;
    }

    public void close() throws SQLException {
        stmt.close();
        conn.close();
    }

}
