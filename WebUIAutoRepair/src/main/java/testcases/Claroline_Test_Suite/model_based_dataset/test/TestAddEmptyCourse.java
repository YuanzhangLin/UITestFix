package testcases.Claroline_Test_Suite.model_based_dataset.test;

import config.DriverConfig;
import org.testng.annotations.*;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Login;
import testcases.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAddEmptyCourse {
    WebDriver driver;
    static int in = 1;
    private StringBuffer verificationErrors = new StringBuffer();
    private boolean acceptNextAlert = true;

    @BeforeMethod
    public void before() throws IOException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getClarolineURL());
    }

    @Test
    public void testAddEmptyCourse() throws Exception {
        Login.login(driver,Constants.Claroline_ADMIN_USER_NAME, Constants.Claroline_ADMIN_PASSWORD);
        driver.findElement(By.linkText("Create a course site")).click();
        driver.findElement(By.name("changeProperties")).click();
        String message = driver.findElement(By.xpath("//*[@id=\"claroBody\"]/div[2]/div/span[1]")).getText();
        Assert.assertTrue(message.contains("Course title needed"));
        String message2 = driver.findElement(By.xpath("//*[@id='claroBody']/div[2]/div/span[2]")).getText();
        Assert.assertTrue(message2.contains("Course code needed"));
    }

    @AfterMethod
	public void end(){
		driver.quit();
	}


}
