package testcases.mantisbt.model_based_dataset.po;

import testcases.mantisbt.model_based_dataset.sql.SQL_Process;

import java.io.IOException;
import java.sql.SQLException;

public class News {

    public static void addNews(String newsName, String body) throws InterruptedException, SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containNews(newsName)) {
            sql_process.deleteNews(newsName);
        }
        sql_process.addNews(newsName, body);
        sql_process.closeSQL();
    }


    public static void deleteNews(String newsName) throws InterruptedException, SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containNews(newsName)) {
            sql_process.deleteNews(newsName);
        }
        sql_process.closeSQL();
    }
}
