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

public class TestWiki {
    WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeMethod
    public void before() throws IOException {
        System.setProperty("webdriver.chrome.driver", DriverConfig.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getClarolineURL());
    }

    @Test
    public void testWiki() {
        Login.login(driver, Constants.Claroline_ADMIN_USER_NAME, Constants.Claroline_ADMIN_PASSWORD);
        driver.findElement(By.linkText("Platform administration")).click();
        driver.findElement(By.linkText("Modules")).click();
        driver.findElement(By.xpath("(//img[@alt='Properties'])[11]")).click();
        driver.findElement(By.linkText("Local settings")).click();
        driver.findElement(By.id("label_showWikiEditorToolbar_FALSE")).click();
        driver.findElement(By.id("label_forcePreviewBeforeSaving_TRUE")).click();
        driver.findElement(By.id("label_forcePreviewBeforeSaving_FALSE")).click();
        driver.findElement(By.id("label_showWikiEditorToolbar_TRUE")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        // Warning: verifyTextPresent may require manual changes
        //try {
            Assert.assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
                    .matches("^[\\s\\S]*Properties for Wiki, \\(CLWIKI\\) are now effective on server\\.[\\s\\S]*$"));
        //} catch (Error e) {
        //    verificationErrors.append(e.toString());
        //}
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.linkText("About")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void end() {
        driver.quit();
    }
}
