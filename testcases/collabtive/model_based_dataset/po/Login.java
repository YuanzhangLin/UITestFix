package testcases.collabtive.model_based_dataset.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testcases.Constants;

public class Login {

    public static void login(WebDriver driver,String username, String pass){
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(Constants.Collabtive_ADMIN_USER_NAME);
        driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(Constants.Collabtive_ADMIN_PASSWORD);
        driver.findElement(By.xpath("//*[@id=\"loginform\"]/fieldset/div[4]/button")).click();
    }
}
