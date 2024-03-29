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

import static org.junit.Assert.assertEquals;

public class Claroline_DoCourseExerciseQuestionsMultipleUsersTest {
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
		CourseIndexPage cip = dp.goInsideCourse();
		ExercisePage ep = cip.goToExercisePage();
		ExerciseSubmitPage esp = ep.goInsideExercise();
		esp.doExerciseN(2, 2, 3);
		esp.doLogout();
		driver.get(Constants.getClarolineURL());
		dp = ip.login("testuser2", "testuser2");
		cip = dp.goInsideCourse();
		ep = cip.goToExercisePage();
		esp = ep.goInsideExercise();
		esp.doExerciseN(1, 2, 3);
		esp.doLogout();
		driver.get(Constants.getClarolineURL());
		dp = ip.login("testuser3", "testuser3");
		cip = dp.goInsideCourse();
		ep = cip.goToExercisePage();
		esp = ep.goInsideExercise();
		esp.doExerciseN(1, 1, 2);
		esp.doLogout();
		driver.get(Constants.getClarolineURL());
		dp = ip.login(Constants.Claroline_ADMIN_USER_NAME, Constants.Claroline_ADMIN_PASSWORD);
		cip = dp.goInsideCourse();
		ep = cip.goToExercisePage();
		TrackExercisePage tep = ep.viewStats();
		assertEquals("testuser1 testuser1", tep.getUserName(1));
		assertEquals("-3", tep.getUserResult(1));
		assertEquals("testuser2 testuser2", tep.getUserName(2));
		assertEquals("0", tep.getUserResult(2));
		assertEquals("testuser3 testuser3", tep.getUserName(3));
		assertEquals("6", tep.getUserResult(3));
		
	}
}
