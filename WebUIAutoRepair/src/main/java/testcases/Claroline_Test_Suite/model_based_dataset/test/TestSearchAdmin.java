package testcases.Claroline_Test_Suite.model_based_dataset.test;
import config.DriverConfig;
import org.openqa.selenium.chrome.ChromeDriver;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Login;
import testcases.Constants;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

public class TestSearchAdmin {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeMethod
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get(Constants.getClarolineURL());
  }

  @Test
  public void testSearchAdmin() throws Exception {
    Login.login(driver,Constants.Claroline_ADMIN_USER_NAME,Constants.Claroline_ADMIN_PASSWORD);
    driver.findElement(By.id("breadcrumbLine")).click();
    driver.findElement(By.xpath("(//a[contains(text(),'Claroline')])[2]")).click();
    driver.findElement(By.linkText("Platform administration")).click();
    driver.findElement(By.linkText("Advanced")).click();
    driver.findElement(By.id("lastName")).click();
    driver.findElement(By.id("lastName")).clear();
    driver.findElement(By.id("lastName")).sendKeys("Dan");
    driver.findElement(By.xpath("//input[@value='Search user']")).click();
    driver.findElement(By.linkText("Advanced")).click();
    driver.findElement(By.xpath("//input[@value='Search user']")).click();
    driver.findElement(By.id("L0")).click();
    assertEquals(driver.findElement(By.id("L0")).getText(), "Dan");
    driver.findElement(By.xpath("//div[@id='claroBody']/table[2]")).click();
    assertEquals(driver.findElement(By.linkText("test@test.com")).getText(), "test@test.com");
    driver.findElement(By.xpath("//div[@id='claroBody']/table[2]")).click();
    driver.findElement(By.xpath("//div[@id='claroBody']/table[2]/tbody/tr/td[6]")).click();
    assertTrue(driver.findElement(By.xpath("//div[@id='claroBody']/table[2]/tbody/tr/td[6]")).getText().contains("Administrator"));
    driver.findElement(By.linkText("Logout")).click();
  }

  @AfterMethod
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

}
