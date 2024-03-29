package testcases.Claroline_Test_Suite.model_based_dataset.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import testcases.Claroline_Test_Suite.model_based_dataset.sql.SQL_Process;

import java.io.IOException;
import java.sql.SQLException;

import static org.testng.Assert.assertEquals;

public class Course {
    public static void addClass(WebDriver driver, String class_name){
        driver.findElement(By.linkText("Platform administration")).click();
        driver.findElement(By.linkText("Manage classes")).click();
        driver.findElement(By.linkText("Create a new class")).click();
        driver.findElement(By.name("class_name")).clear();
        driver.findElement(By.name("class_name")).sendKeys(class_name);
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        /*
         * String message=
         * driver.findElement(By.xpath("//*[@id=\"claroBody\"]/div[2]/div")).getText();
         * System.out.println (message);
         * Assert.assertTrue(message.contains("The new class has been created"));
         */
        try {
            Assert.assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
                    .matches("^[\\s\\S]*The new class has been created[\\s\\S]*$"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void addCourse(WebDriver driver, String courseName){
        driver.findElement(By.linkText("Create a course site")).click();
        driver.findElement(By.id("course_title")).clear();
        driver.findElement(By.id("course_title")).sendKeys(courseName);
        driver.findElement(By.id("course_officialCode")).clear();
        driver.findElement(By.id("course_officialCode")).sendKeys("CS213");
        Select category = new Select(driver.findElement(By.id("mslist2")));
        category.selectByVisibleText("Mathematics");
        driver.findElement(By.xpath("//*[@id=\"mandatories\"]/div/dl/dd[3]/table/tbody/tr/td[2]/a[2]/img")).click();
        driver.findElement(By.id("course_titular")).clear();
        driver.findElement(By.id("course_titular")).sendKeys("Javaria Imtiaz");
        driver.findElement(By.xpath("//*[@id=\"mandatories\"]/div/dl/dd[7]/label[1]")).click();
        driver.findElement(By.linkText("Advanced options")).click();
//        driver.findElement(By.id("course_status_date")).click();
        driver.findElement(By.name("changeProperties")).click();
        String message = driver.findElement(By.xpath("//*[@id=\"claroBody\"]/div[2]/div")).getText();
        Assert.assertTrue(message.contains("You have just created the course website : CS213"));
    }

    public static void deleteCourse(String courseName) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containCourse(courseName)){
            sql_process.deleteCourse(courseName);
        }
        sql_process.close();
    }

}
