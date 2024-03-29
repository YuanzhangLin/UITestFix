package testcases.mantisbt.model_based_dataset.test;

import config.DriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.*;
import testcases.Constants;
import testcases.mantisbt.model_based_dataset.sql.SQL_Process;

import java.util.concurrent.TimeUnit;

public class DeleteUserTest {
    private static WebDriver driver;

    @BeforeMethod

    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(testcases.Constants.getMantisUrl());
        // Login User Administrator
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(testcases.Constants.Mantisbt_ADMIN_USER_NAME);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(Constants.Mantisbt_ADMIN_PASSWORD);
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        Thread.sleep(2000);
        SQL_Process sql_process = new SQL_Process();
        sql_process.clearUser();
        if (!sql_process.containUser("Demo User New")){
            sql_process.addUser("Demo User New","user","ss@eamil.com","123456");
        }
        sql_process.closeSQL();
    }

    @Test(priority = 0)
    public static void userTest() throws Exception {

        driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Demo User New']")).click();
        driver.findElement(By.xpath("//input[@value='Delete User']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value='Delete Account']")).click();
        AssertJUnit.assertEquals(driver.findElement(By.xpath("//a[text()='administrator']")).getText(),
                "administrator");
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    public static void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

}
