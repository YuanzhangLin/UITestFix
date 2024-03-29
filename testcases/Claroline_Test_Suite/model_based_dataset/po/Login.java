package testcases.Claroline_Test_Suite.model_based_dataset.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
    public static void login(WebDriver driver, String username, String password){
        driver.findElement(By.id("login")).clear();
        driver.findElement(By.id("login")).sendKeys(username);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    }
}
