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

public class Claroline_AddCourseExerciseQuestionsTest {
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
		EditQuestionPage eqp = eep.newQuestion();
		EditAnswerPage eap = eqp.addMCUAQuestion("Question 1");
		eqp = eap.addMCUA("3", "-3");
		Thread.sleep(1000);
		eqp.newQuestion();
		eap = eqp.addTFQuestion("Question 2");
		eqp = eap.addTFA("3", "-3");
		Thread.sleep(1000);
		eqp.newQuestion();
		eap = eqp.addMCMAQuestion("Question 3");
		eqp = eap.addMCMA("3", "0", "-3");
		eep = eqp.goToEditExercisePage();
		assertEquals("Question 1", eep.getQuestionTitle(1));
		assertEquals("Question 2", eep.getQuestionTitle(2));
		assertEquals("Question 3", eep.getQuestionTitle(3));
		assertEquals("Multiple choice (Unique answer)", eep.getQuestionKind(1));
		assertEquals("True/False", eep.getQuestionKind(2));
		assertEquals("Multiple choice (Multiple answers)", eep.getQuestionKind(3));
		eep.doLogout();
	}
}
