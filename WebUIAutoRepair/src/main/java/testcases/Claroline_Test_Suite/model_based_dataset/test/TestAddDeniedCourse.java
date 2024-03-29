package testcases.Claroline_Test_Suite.model_based_dataset.test;
import config.DriverConfig;
import org.openqa.selenium.chrome.ChromeDriver;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Course;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Login;
import testcases.Constants;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class TestAddDeniedCourse {
  private WebDriver driver;

  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeMethod
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get(Constants.getClarolineURL());
    Course.deleteCourse("225");
  }

  @Test
  public void testAddDeniedCourse() throws Exception {
    Login.login(driver,Constants.Claroline_ADMIN_USER_NAME,Constants.Claroline_ADMIN_PASSWORD);
    driver.findElement(By.linkText("Create a course site")).click();
    driver.findElement(By.id("course_title")).click();
    driver.findElement(By.id("course_title")).clear();
    driver.findElement(By.id("course_title")).sendKeys("Computer Science");
    driver.findElement(By.id("course_officialCode")).click();
    driver.findElement(By.id("course_officialCode")).clear();
    driver.findElement(By.id("course_officialCode")).sendKeys("225");
    Select category = new Select(driver.findElement(By.xpath("//*[@id=\"mslist2\"]")));
    category.selectByIndex(0);
    driver.findElement(By.xpath("//*[@id=\"mandatories\"]/div/dl/dd[3]/table/tbody/tr/td[2]/a[2]/img")).click();
    //    driver.findElement(By.xpath("//*[@id=\"advancedInformation\"]/legend/a")).click();
//    driver.findElement(By.xpath("//*[@id=\"course_status_date\"]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [addSelection | id=mslist2 | label=Sciences]]
//    driver.findElement(By.xpath("//*[@id=\"course_publicationDay\"]")).click();
//    driver.findElement(By.xpath("//option[@value='2']")).click();
    driver.findElement(By.xpath("//fieldset[@id='mandatories']/div/dl/dd[3]/table/tbody/tr/td[2]/a[2]/img")).click();
    driver.findElement(By.id("mandatories")).click();
    driver.findElement(By.id("course_titular")).clear();
    driver.findElement(By.id("course_titular")).sendKeys("John");
    driver.findElement(By.name("changeProperties")).click();
    
    Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"claroBody\"]/div[2]/div")).getText(),
		"You have just created the course website : 225");
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
