package testcases.Claroline_Test_Suite.study.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class TrackExercisePage {
	private WebDriver driver;

	public TrackExercisePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getUserName(int i){
		WebElement name = driver.findElement(By.xpath(".//*[@id='claroBody']/table[1]/tbody/tr["+(i+2)+"]/td[1]/a"));
		return name.getText();
	}
	
	public String getUserResult(int i){
		WebElement result = driver.findElement(By.xpath(".//*[@id='claroBody']/table[1]/tbody/tr["+(i+2)+"]/td[2]"));
		return result.getText();
	}
}
