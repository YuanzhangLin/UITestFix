package testcases.collabtive.study.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ManageProjectPage extends CollabtivePage {
    private WebDriver driver;
    @FindBy(xpath = ".//*[@id='contentwrapper']/div[1]/ul/li[6]/a")
    private WebElement manageUserLink;
    @FindBy(xpath = ".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")
    private WebElement manageMilestoneLink;
    @FindBy(xpath = ".//*[@id='contentwrapper']/div[1]/ul/li[3]/a")
    private WebElement manageTaskLink;
    @FindBy(id = "assignto")
    private WebElement reassignTasksList;
    @FindBy(xpath = "//*[@id=\"datepicker_project\"]/table/tbody/tr[8]/td/a")
    private WebElement confirmTime;
    @FindBy(css = "button[type='submit']")
    private WebElement confirm;
    @FindBy(xpath = ".//*[@id='mainmenue']/li[3]/a")
    private WebElement administrationLink;
    @FindBy(id = "end")
    private WebElement projectDate;
    @FindBy(id = "edit_butn")
    private WebElement editProjectButton;
    @FindBy(xpath = "html/body/div[2]/div[2]/div[2]/div/div[1]/div[2]/div")
    private WebElement percentageStatus;


    public ManageProjectPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String getProjectBudget() {
        return driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div/div[1]/div[2]/ul/li[4]/a")).getText();
    }

    public String getProjectDescription() throws InterruptedException {
        WebElement descrButton = driver.findElement(By.id("desctoggle"));
        descrButton.click();
        Thread.sleep(2000);
        return driver.findElement(By.id("descript")).getText();
    }

    public ManageUserPage goToManageUserPage() throws InterruptedException {
        manageUserLink.click();
        ManageUserPage page = new ManageUserPage(driver);
        return page;
    }

    public ManageUserPage reassignTasks() throws InterruptedException {
        new Select(reassignTasksList).selectByVisibleText("admin");
        confirm.click();
        Thread.sleep(3000);
        ManageUserPage page = new ManageUserPage(driver);
        return page;
    }

    public AdminPage goToAdminPage() {
        administrationLink.click();
        AdminPage page = new AdminPage(driver);
        return page;
    }

    public ManageMilestonePage goToManageMilestoneLink() {
        manageMilestoneLink.click();
        ManageMilestonePage page = new ManageMilestonePage(driver);
        return page;
    }

    public ManageTaskPage goToManageTaskPage() {
        manageTaskLink.click();
        ManageTaskPage page = new ManageTaskPage(driver);
        return page;
    }

    public void editProjectDate(String date) throws InterruptedException {
        projectDate.clear();
        projectDate.sendKeys(date);
		confirmTime.click();
        Thread.sleep(1500);
		confirm.click();
        Thread.sleep(2000);
    }

    public void openEditProjectFrame() throws InterruptedException {
        editProjectButton.click();
        Thread.sleep(2000);
    }

    public String getProjectDate() {
        return projectDate.getAttribute("value");
    }

    public String getPercentageStatus() {
        return percentageStatus.getText();
    }

}
