package testcases.mrbs.study.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class EditEntryPage {
	private WebDriver driver;
	@FindBy(name="name")
	private WebElement entryName;
	@FindBy(name="description")
	private WebElement entryDescription;
	@FindBy(css="td.CL > select[name='day']")
	private WebElement entryDay;
	@FindBy(css="td.CL > select[name='month']")
	private WebElement entryMonth;
	@FindBy(css="td.CL > select[name='year']")
	private WebElement entryYear;
	@FindBy(name="duration")
	private WebElement entryDuration;
	@FindBy(name="hour")
	private WebElement entryHour;
	@FindBy(name="minute")
	private WebElement entryMinute;
	@FindBy(name="areas")
	private WebElement entryArea;
	@FindBy(name="rooms[]")
	private WebElement entryRoom;
	@FindBy(name="save_button")
	private WebElement confirm;
	@FindBy(name="all_day")
	private WebElement entryAllDayEntry;
	@FindBy(xpath="(//input[@name='rep_type'])[2]")
	private WebElement entryDailyRep;
	@FindBy(name="rep_end_day")
	private WebElement entryUntilDay;
	@FindBy(name="rep_end_month")
	private WebElement entryUntilMonth;
	@FindBy(name="rep_end_year")
	private WebElement entryUntilYear;
	
	
	public EditEntryPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public DayPage addEntry(String name, String desc, String day, int month, String year, 
			String duration, String hour, String min, String area, String room) throws InterruptedException{
		entryName.sendKeys(name);
		entryDescription.sendKeys(desc);
		new Select(entryDay).selectByVisibleText(day);
		new Select(entryMonth).selectByIndex(month);
		new Select(entryYear).selectByVisibleText(year);
		entryDuration.clear();
		entryDuration.sendKeys(duration);
		entryHour.clear();
		entryHour.sendKeys(hour);
		entryMinute.sendKeys(min);
//		new Select(entryArea).selectByVisibleText(area);
		new Select(entryRoom).selectByVisibleText(room);
		confirm.click();
		Thread.sleep(1000);
		DayPage page = new DayPage(driver);
		return page;		
	}
	
	public DayPage addSerialEntry(String name, String desc, String day, int month, String year, 
			String untilDay, int untilMonth, String untilYear, String area, String room) throws InterruptedException{
		entryName.sendKeys(name);
		entryDescription.sendKeys(desc);
		new Select(entryDay).selectByVisibleText(day);
		new Select(entryMonth).selectByIndex(month);
		new Select(entryYear).selectByVisibleText(year);
		entryAllDayEntry.click();
		entryDailyRep.click();
		new Select(entryUntilDay).selectByVisibleText(untilDay);
		new Select(entryUntilMonth).selectByIndex(untilMonth);
		new Select(entryUntilYear).selectByVisibleText(untilYear);	
//		new Select(entryArea).selectByVisibleText(area);
		new Select(entryRoom).selectByVisibleText(room);
		confirm.click();
		Thread.sleep(1000);
		DayPage page = new DayPage(driver);
		return page;		
	}
	
	public EditEntryHandlerPage addConflictualEntry(String name, String desc, String day, int month, String year, 
			String duration, String hour, String min, String area, String room) throws InterruptedException{
		entryName.sendKeys(name);
		entryDescription.sendKeys(desc);
		new Select(entryDay).selectByVisibleText(day);
		new Select(entryMonth).selectByIndex(month);
		new Select(entryYear).selectByVisibleText(year);
		entryDuration.clear();
		entryDuration.sendKeys(duration);
		entryHour.clear();
		entryHour.sendKeys(hour);
		entryMinute.sendKeys(min);
//		new Select(entryArea).selectByVisibleText(area);
		new Select(entryRoom).selectByVisibleText(room);
		confirm.click();
		Thread.sleep(1000);
		EditEntryHandlerPage page = new EditEntryHandlerPage(driver);
		return page;		
	}
}
