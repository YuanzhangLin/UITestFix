package testcases.Claroline_Test_Suite.study.test;

import config.DriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testcases.Claroline_Test_Suite.study.po.*;
import testcases.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class Claroline_EnrolMultipleUsersTest {
	private WebDriver driver;
	
	@Before
	public void setUp() throws IOException{
		System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getClarolineURL());
	}
	
	@After
	public void tearDown(){
		driver.quit();
	}
	
	@Test
	public void runTest() throws InterruptedException, IOException {
		IndexPage ip = new IndexPage(driver);
		DesktopPage dp = ip.login("testuser1", "testuser1");
		AuthCoursesPage acp = dp.enrolToCourse();
		acp.searchCourse("Course001");
		acp.enrol();
		acp.doLogout();
		driver.get(Constants.getClarolineURL());
		dp = ip.login("testuser2", "testuser2");
		acp = dp.enrolToCourse();
		acp.searchCourse("Course001");
		acp.enrol();
		acp.doLogout();
		driver.get(Constants.getClarolineURL());
		dp = ip.login("testuser3", "testuser3");
		acp = dp.enrolToCourse();
		acp.searchCourse("Course001");
		acp.enrol();
		acp.doLogout();
		driver.get(Constants.getClarolineURL());
		dp = ip.login(Constants.Claroline_ADMIN_USER_NAME, Constants.Claroline_ADMIN_PASSWORD);
		CourseIndexPage cip = dp.goInsideCourse();
		UserPage up = cip.goToUsersPage();
		assertTrue(up.getBodyText(driver).contains("testuser1"));
		assertTrue(up.getBodyText(driver).contains("testuser2"));
		assertTrue(up.getBodyText(driver).contains("testuser3"));
		up.doLogout();		
	}
}
