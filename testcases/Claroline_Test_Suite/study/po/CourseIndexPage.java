package testcases.Claroline_Test_Suite.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CourseIndexPage {
	private WebDriver driver;
	@FindBy(id="CLQWZ")
	private WebElement exerciseButton;
	@FindBy(id="CLCAL")
	private WebElement agendaButton;
	@FindBy(id="CLUSR")
	private WebElement usersButton;

	public CourseIndexPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public ExercisePage goToExercisePage() throws InterruptedException{
		Thread.sleep(2000);
		exerciseButton.click();
		ExercisePage page = new ExercisePage(driver);
		return page;
	}
	
	public CalendarAgendaPage goToCalendarAgendaPage() throws InterruptedException{
		Thread.sleep(2000);
		agendaButton.click();
		Thread.sleep(1000);
		CalendarAgendaPage page = new CalendarAgendaPage(driver);
		return page;
	}
	
	public UserPage goToUsersPage() throws InterruptedException{
		Thread.sleep(2000);
		usersButton.click();
		UserPage page = new UserPage(driver);
		return page;
	}
}
