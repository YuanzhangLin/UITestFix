package testcases.mantisbt.model_based_dataset.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import config.DriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.*;
import testcases.Constants;
import testcases.mantisbt.model_based_dataset.po.Project;

public class UnlinkMultipleSubprojectsTest {
    private static WebDriver driver;
    @BeforeMethod
 public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Constants.getMantisUrl());
        // Login User Administrator
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(Constants.Mantisbt_ADMIN_USER_NAME);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(Constants.Mantisbt_ADMIN_PASSWORD);
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 0)
    public static void unlinkMultipleSubProjectTest() throws Exception {
        Project.addProject("Project001");
        Project.addSubProject("Subject1","Project001");
        Project.addSubProject( "Subject2","Project001");
        // Delete Project 1
        driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
        driver.findElement(By.xpath("//a[text()='Project001']")).click();

        List<WebElement> unlinkList = driver.findElements(By.xpath("//input[@value='Unlink']"));
		while(!unlinkList.isEmpty()){
				unlinkList.get(0).click();
				Thread.sleep(3000);
			unlinkList = driver.findElements(By.xpath("//input[@value='Unlink']"));
		}
        AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(),
                "Edit Project");
        Project.deleteProject("Project001");
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
