package testcases.Claroline_Test_Suite.model_based_dataset.test;
import config.DriverConfig;
import org.testng.annotations.*;
import testcases.Claroline_Test_Suite.model_based_dataset.sql.SQL_Process;
import testcases.Constants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Course;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Login;


public class TestAddClass {
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
        if (SQLProcess.containClass("CS-A")){
            SQLProcess.deleteClass("CS-A");
        }
        SQLProcess.close();
    }

    @Test
    public void testAddClass(){
        Login.login(driver,Constants.Claroline_ADMIN_USER_NAME, Constants.Claroline_ADMIN_PASSWORD);
        Course.addClass(driver,"CS-A");
    }

    @AfterMethod
    public void end(){
        driver.quit();
    }

}
