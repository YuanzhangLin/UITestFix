package testcases.Claroline_Test_Suite.study.test;

import config.DriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testcases.Claroline_Test_Suite.model_based_dataset.po.Category;
import testcases.Claroline_Test_Suite.study.po.AdminPage;
import testcases.Claroline_Test_Suite.study.po.CourseCreatePage;
import testcases.Claroline_Test_Suite.study.po.DesktopPage;
import testcases.Claroline_Test_Suite.study.po.IndexPage;
import testcases.Constants;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class Claroline_AddDeniedCourseTest {
	private WebDriver driver;
	
	@Before
	public void setUp() throws IOException, SQLException, ClassNotFoundException {
		System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getClarolineURL());
		Category.addCategory("Humanities","003");
	}
	
	@After
	public void tearDown(){
		driver.quit();
	}
	
	@Test
	public void runTest() throws InterruptedException{
		IndexPage ip = new IndexPage(driver);
		DesktopPage dp = ip.login(Constants.Claroline_ADMIN_USER_NAME, Constants.Claroline_ADMIN_PASSWORD);
		AdminPage ap = dp.goToAdminPage();
		CourseCreatePage ccp = ap.addCourse();
		ccp.addDeniedCourse("Course003", "003", "Humanities");
		assertEquals("You have just created the course website : 003", ccp.getMessage());
		ap = ccp.goToAdminPage();
		ap.doLogout();
	}
}
