package testcases.mantisbt.model_based_dataset.test;

import java.util.concurrent.TimeUnit;

import config.DriverConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.*;
import testcases.Constants;
import testcases.mantisbt.model_based_dataset.po.Project;

public class CategoryTest {
    private static WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(testcases.Constants.getMantisUrl());
        Project.deleteProject("Project001 New");
        // Login User Administrator
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(testcases.Constants.Mantisbt_ADMIN_USER_NAME);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(Constants.Mantisbt_ADMIN_PASSWORD);
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 0)
    public static void addCategoryTest() throws Exception {
        // add
        driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();

        // build this project
        driver.findElement(By.xpath("//input[@value='Create New Project']")).click();
        Thread.sleep(1000);
        // Submit Form

        WebElement dropdown1 = driver.findElement(By.name("status"));
        Select dropdownEle1 = new Select(dropdown1);
        dropdownEle1.selectByVisibleText("release");
        Thread.sleep(1000);

        WebElement dropdown2 = driver.findElement(By.name("view_state"));
        Select dropdownEle2 = new Select(dropdown2);
        dropdownEle2.selectByVisibleText("public");
        Thread.sleep(1000);

        driver.findElement(By.name("name")).sendKeys("Project001 New");
        driver.findElement(By.name("description")).sendKeys("Description Description Description");

        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Add Project']")).click();
        Thread.sleep(5000);
        AssertJUnit.assertEquals(driver.findElement(By.xpath("//a[text()='Project001 New']")).getText(),
                "Project001 New");
        driver.findElement(By.xpath("//a[text()='Project001 New']")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("category")). sendKeys("Category1");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Add Category']")). click();
        Thread.sleep(3000);
        AssertJUnit.assertEquals(
                driver.findElement(By.xpath("/html/body/div[7]/a[1]/table/tbody/tr[3]/td[1]")).getText(),
                "Category1");
        // delete
        driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")). click();
        driver.findElement(By.xpath("//a[text()='Project001 New']")). click();
        Thread.sleep(1000);
        try {
            driver.findElement(By.xpath("//input[@value='Delete']")).click();
        } catch (
                Exception e) {
            Thread.sleep(4000);
            driver.findElement(By.xpath("//input[@value='Delete']")).click();
        }
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Delete Category']")).

                click();
        Thread.sleep(2000);
        AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).

                        getText(),
                "Edit Project");
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
