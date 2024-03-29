package testcases.Claroline_Test_Suite.model_based_dataset.test;

import config.DriverConfig;
import org.testng.annotations.*;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Login;
import testcases.Claroline_Test_Suite.model_based_dataset.sql.SQL_Process;
import testcases.Constants;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestDeletingCategory {
    WebDriver driver;
    static int in = 1;
    private StringBuffer verificationErrors = new StringBuffer();
    private boolean acceptNextAlert = true;

    @BeforeMethod
    public void before() throws IOException, SQLException, ClassNotFoundException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getClarolineURL());
        SQL_Process SQLProcess = new SQL_Process();
        if (!SQLProcess.containCategory("Software Eng","SE112")){
            SQLProcess.addCategory("Software Eng","SE112");
        }
        SQLProcess.close();
    }

    @Test
    public void testDeletingCategory() throws Exception {
        Login.login(driver,Constants.Claroline_ADMIN_USER_NAME,Constants.Claroline_ADMIN_PASSWORD);
        driver.findElement(By.linkText("Platform administration")).click();
        driver.findElement(By.linkText("Manage course categories")).click();
        driver.findElement(By.xpath("//*[@id=\"claroBody\"]/table/tbody/tr[3]/td[6]")).click();
        Assert.assertTrue(closeAlertAndGetItsText().contains("Are you sure to delete"));
        // Warning: verifyTextPresent   may require manual changes
        //try {
            Assert.assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
                    .matches("^[\\s\\S]*Category deleted\\.[\\s\\S]*$"));
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
    }

    @AfterMethod
    public void end() throws SQLException, IOException, ClassNotFoundException {
        driver.quit();
        SQL_Process SQLProcess = new SQL_Process();
        if (SQLProcess.containCategory("Software Eng","SE112")){
            SQLProcess.deleteCategory("Software Eng","SE112");
        }
        SQLProcess.close();
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

}
