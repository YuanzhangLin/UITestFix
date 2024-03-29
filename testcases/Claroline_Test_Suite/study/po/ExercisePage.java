package testcases.Claroline_Test_Suite.study.po;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExercisePage extends ClarolinePage{
	private WebDriver driver;
	@FindBy(linkText="New exercise")
	private WebElement newExerciseButton;
	@FindBy(xpath="(//img[@alt='Delete'])[2]")
	private WebElement removeExerciseButton;
	@FindBy(xpath="(//img[@alt='Modify'])[2]")
	private WebElement editExerciseButton;
	@FindBy(xpath="(//img[@alt='Make visible'])[2]")
	private WebElement makeVisible;
	@FindBy(xpath=".//*[@id='claroBody']/table/tbody/tr[2]/td[4]/a/img")
	private WebElement visibility;
	@FindBy(linkText="Exercise 001")
	private WebElement exerciseLink;
	@FindBy(xpath="(//img[@alt='Statistics'])[2]")
	private WebElement exeStats;

	public ExercisePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public EditExercisePage newExercise(){
		newExerciseButton.click();
		EditExercisePage page = new EditExercisePage(driver);
		return page;
	}
	
	public void removeExercise() throws InterruptedException{
		removeExerciseButton.click();
		Alert alert = driver.switchTo().alert();
		Thread.sleep(2000);
		alert.accept();
		Thread.sleep(2000);
	}
	
	public EditExercisePage editExercise(){
		editExerciseButton.click();
		EditExercisePage page = new EditExercisePage(driver);
		return page;
	}
	
	public void  makeExerciseVisible() throws InterruptedException{
		makeVisible.click();
		Thread.sleep(1000);
	}
	
	public String getExerciseVisibilityTitle(){
		return visibility.getAttribute("alt");
	}
	
	public ExerciseSubmitPage goInsideExercise() throws InterruptedException{
		Thread.sleep(2000);
		exerciseLink.click();
		ExerciseSubmitPage page = new ExerciseSubmitPage(driver);
		return page;
	}
	
	public TrackExercisePage viewStats() throws InterruptedException{
		Thread.sleep(2000);
		exeStats.click();
		TrackExercisePage page = new TrackExercisePage(driver);
		return page;
	}
}
