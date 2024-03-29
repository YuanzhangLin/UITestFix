package testcases.Claroline_Test_Suite.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditQuestionPage {
	private WebDriver driver;
	@FindBy(id="title")
	private WebElement title;
	@FindBy(id="MCUA")
	private WebElement multipleCondUniqueAnswer;
	@FindBy(id="TF")
	private WebElement trueFalse;
	@FindBy(id="MCMA")
	private WebElement multipleCondMultipleAnswer;
	@FindBy(css="input[type='submit']")
	private WebElement confirm;	
	@FindBy(linkText="Exercise 001")
	private WebElement exerciseLink;
	@FindBy(linkText="New question")
	private WebElement newQuestionLink;

	public EditQuestionPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public EditAnswerPage addMCUAQuestion(String tit) throws InterruptedException{
		title.sendKeys(tit);
		multipleCondUniqueAnswer.click();
		confirm.click();
		Thread.sleep(2000);
		EditAnswerPage page = new EditAnswerPage(driver);
		return page;
	}
	
	public EditAnswerPage addMCMAQuestion(String tit) throws InterruptedException{
		title.sendKeys(tit);
		multipleCondMultipleAnswer.click();
		confirm.click();
		Thread.sleep(2000);
		EditAnswerPage page = new EditAnswerPage(driver);
		return page;
	}
	
	public EditAnswerPage addTFQuestion(String tit) throws InterruptedException{
		title.sendKeys(tit);
		trueFalse.click();
		confirm.click();
		Thread.sleep(2000);
		EditAnswerPage page = new EditAnswerPage(driver);
		return page;
	}
	
	public EditExercisePage goToEditExercisePage(){
		exerciseLink.click();
		EditExercisePage page = new EditExercisePage(driver);
		return page;
	}
	
	public void newQuestion() throws InterruptedException{
		newQuestionLink.click();
		Thread.sleep(3000);
	}

}
