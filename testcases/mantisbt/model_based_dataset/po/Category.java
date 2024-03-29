package testcases.mantisbt.model_based_dataset.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Category {
    public static void addCategory(WebDriver driver, String category) throws InterruptedException {
        driver.findElement(By.name("category")).sendKeys("Category1");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Add Category']")).click();
        Thread.sleep(1000);
    }
}
