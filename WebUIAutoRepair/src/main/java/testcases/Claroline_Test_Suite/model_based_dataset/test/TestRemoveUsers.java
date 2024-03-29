package testcases.Claroline_Test_Suite.model_based_dataset.test;

import config.DriverConfig;
import org.openqa.selenium.chrome.ChromeDriver;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Login;
import testcases.Claroline_Test_Suite.model_based_dataset.sql.SQL_Process;
import testcases.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;

public class TestRemoveUsers {
    private WebDriver driver;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeMethod
    public void setUp() throws Exception {
        SQL_Process SQLProcess = new SQL_Process();
        if (!SQLProcess.containUser("aa","aa")){
            SQLProcess.addUser("aa","aa");
        }
        SQLProcess.close();
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(Constants.getClarolineURL());
    }

    @Test
    public void testRemoveUsers() throws IOException {
      Login.login(driver,Constants.Claroline_ADMIN_USER_NAME,Constants.Claroline_ADMIN_PASSWORD);
        driver.findElement(By.id("breadcrumbLine")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'Claroline')])[2]")).click();
        driver.findElement(By.linkText("Platform administration")).click();
        driver.findElement(By.linkText("User list")).click();
        acceptNextAlert = true;
        driver.findElement(By.xpath("(//img[@alt='Delete'])[2]")).click();
        assertTrue(closeAlertAndGetItsText().contains("Are you sure to delete"));
        assertEquals(driver.findElement(By.xpath("//div[@id='claroBody']/div[2]/div")).getText(), "Deletion of the user was done sucessfully");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
        SQL_Process SQLProcess = new SQL_Process();
        if (SQLProcess.containUser("aaa","aaa")){
            SQLProcess.addUser("aaa","aaa");
        }
        SQLProcess.close();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
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
