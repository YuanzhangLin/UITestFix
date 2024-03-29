package testcases.mrbs.model_based_dataset.sql;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Entry {

    public static void clear() throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        sql_process.clearEntry();
        sql_process.sqlClose();
    }

    public static void addEntry(WebDriver driver, String description) throws SQLException, IOException, ClassNotFoundException, InterruptedException {
        SQL_Process sql_process = new SQL_Process();
        sql_process.clearEntry();
        sql_process.sqlClose();
        driver.findElement(By.xpath("//a[text()='Area New']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[text()='Go To Today']")).click();
        Thread.sleep(1000);
        List<WebElement> list = driver.findElements(By.xpath("//img[@src='new.gif']"));
        list.get(0).click();
        Thread.sleep(1000);
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys(description);
        driver.findElement(By.name("description")).clear();
        driver.findElement(By.name("description")).sendKeys(description);
        driver.findElement(By.name("all_day")).click();
        driver.findElement(By.name("save_button")).click();
        Thread.sleep(2000);
        try {
            Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Demo Entry']")).getText(), "Demo Entry");
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td[1]/font/a")).click();
    }

    public static void addEntry(WebDriver driver, String name,String description) throws SQLException, IOException, ClassNotFoundException, InterruptedException {
        SQL_Process sql_process = new SQL_Process();
        sql_process.clearEntry();
        sql_process.sqlClose();
        driver.findElement(By.xpath("//a[text()='Area New']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[text()='Go To Today']")).click();
        Thread.sleep(1000);
        List<WebElement> list = driver.findElements(By.xpath("//img[@src='new.gif']"));
        list.get(0).click();
        Thread.sleep(1000);
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("description")).clear();
        driver.findElement(By.name("description")).sendKeys(description);
        driver.findElement(By.name("all_day")).click();
        driver.findElement(By.name("save_button")).click();
        Thread.sleep(2000);
        try {
            Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Demo Entry']")).getText(), "Demo Entry");
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td[1]/font/a")).click();
    }

    public static void addEntry(String name, String description) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        sql_process.clearEntry();
        sql_process.addEntry(name,description);
        sql_process.sqlClose();
    }

}
