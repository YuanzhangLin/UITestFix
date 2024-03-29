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

public class Claroline_RemoveCourseExerciseQuestionsTest {
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
	public void runTest() throws InterruptedException{
		IndexPage ip = new IndexPage(driver);
		DesktopPage dp = ip.login(Constants.Claroline_ADMIN_USER_NAME, Constants.Claroline_ADMIN_PASSWORD);
		CourseIndexPage cip = dp.goInsideCourse();
		ExercisePage ep = cip.goToExercisePage();
		EditExercisePage eep = ep.editExercise();
		eep.removeQuestion(1);
		eep.removeQuestion(1);
		eep.removeQuestion(1);
		driver.navigate().refresh();
		assertTrue(!eep.getBodyText(driver).contains("Question 1"));
		assertTrue(!eep.getBodyText(driver).contains("Question 2"));
		assertTrue(!eep.getBodyText(driver).contains("Question 3"));
		eep.doLogout();
	}
}
