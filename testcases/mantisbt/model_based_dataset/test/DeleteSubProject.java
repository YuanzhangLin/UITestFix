package testcases.mantisbt.model_based_dataset.test;

import config.DriverConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.*;
import testcases.Constants;
import testcases.mantisbt.model_based_dataset.po.Project;

import java.util.concurrent.TimeUnit;

public class DeleteSubProject {
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
        Project.addProject("Project001");
        Project.addSubProject("Project001","Subject001");
    }

    @Test(priority = 0)
    public static void subProjectTest() throws Exception {
        driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
        Thread.sleep(3000);
        try {

            driver.findElement(By.xpath("//a[text()='" + "Subject001" + "']")).click();
        } catch (NoSuchElementException noSuchElementException) {
            driver.findElement(By.xpath("//a[text()='» " + "Subject001" + "']")).click();
        }
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@value='Delete Project']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value='Delete Project']")).click();
        Thread.sleep(2000);
        AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(), "Projects");
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
