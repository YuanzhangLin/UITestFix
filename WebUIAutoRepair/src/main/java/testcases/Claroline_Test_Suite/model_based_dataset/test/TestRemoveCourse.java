package testcases.Claroline_Test_Suite.model_based_dataset.test;

import config.DriverConfig;
import org.openqa.selenium.chrome.ChromeDriver;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Course;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Login;
import testcases.Constants;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;

public class TestRemoveCourse {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getClarolineURL());
    }

    @Test
    public void testRemoveCourse() throws Exception {
        Login.login(driver, Constants.Claroline_ADMIN_USER_NAME, Constants.Claroline_ADMIN_PASSWORD);
        Course.addCourse(driver,"Computer Science");
        driver.findElement(By.linkText("Platform administration")).click();
        driver.findElement(By.linkText("Course list")).click();
        acceptNextAlert = true;
        driver.findElement(By.xpath("(//img[@alt='Delete'])[1]")).click();
        assertTrue(closeAlertAndGetItsText().contains("Are you sure to delete"));
        driver.findElement(By.xpath("//div[@id='claroBody']/div[2]/div")).click();
        assertEquals(driver.findElement(By.xpath("//div[@id='claroBody']/div[2]/div")).getText(), "The course has been successfully deleted");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
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
