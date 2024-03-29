package testcases.mantisbt.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@FindBy(name="username")
	private WebElement username;
	@FindBy(name="password")
	private WebElement password;
	@FindBy(css="input.button")
	private WebElement submit;
	private WebDriver driver;
	@FindBy(xpath="html/body/div[3]/font")
	private WebElement errorMsg;
		
	public LoginPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public MyViewPage login(String username, String password){
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.submit.click();
		MyViewPage page = new MyViewPage(driver);
		return page;
	}
	
	public LoginPage loginWrong(String username, String password){
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.submit.click();
		return this;
	}
	
	public String getErrorMsg(){
		return errorMsg.getText();
	}
	
	public String getInputButtonText(){
		return submit.getAttribute("value");
	}
	
}
