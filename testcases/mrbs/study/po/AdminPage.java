package testcases.mrbs.study.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage extends MRBSPage {
    private WebDriver driver;
    @FindBy(name = "NewUserName")
    private WebElement username;
    @FindBy(name = "NewUserPassword")
    private WebElement password;
    @FindBy(css = "body > form > input[type='submit']")
    private WebElement confirm;
    @FindBy(name = "name")
    private WebElement buildingName;
    @FindBy(xpath = "//input[@value='Add Area']")
    private WebElement addBuildingButton;
    @FindBy(xpath = "(//input[@name='name'])[2]")
    private WebElement roomName;
    @FindBy(name = "description")
    private WebElement roomDescription;
    @FindBy(name = "capacity")
    private WebElement roomCapacity;
    @FindBy(xpath = "//input[@value='Add Room']")
    private WebElement addRoomButton;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public DayPage login(String user, String pass) {
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        username.sendKeys(user);
        password.sendKeys(pass);
        confirm.click();
        DayPage page = new DayPage(driver);
        return page;
    }

    public void addBuilding(String name) throws InterruptedException {
        buildingName.sendKeys(name);
        addBuildingButton.click();
        Thread.sleep(1000);
    }

    public String getBuildingName(int i) {
        WebElement name = driver.findElement(By.xpath("html/body/table[2]/tbody/tr[2]/td[1]/ul/li[" + i + "]/a[1]"));
        return name.getText();
    }

    public void selectBuilding(int i) throws InterruptedException {
        WebElement name = driver.findElement(By.xpath("html/body/table[2]/tbody/tr[2]/td[1]/ul/li[" + i + "]/a[1]"));
        name.click();
        Thread.sleep(2000);
    }


    public void removeBuilding(int i) throws InterruptedException {
        WebElement remove = driver.findElement(By.xpath("(//a[contains(text(),'Delete')])[" + i + "]"));
        remove.click();
        Thread.sleep(1000);
    }

    public void addRoom(String name, String descr, String cap) throws InterruptedException {
        roomName.sendKeys(name);
        roomDescription.sendKeys(descr);
        roomCapacity.sendKeys(cap);
        addRoomButton.click();
        Thread.sleep(1000);
    }

    public String getRoomInfo(int i) {
        WebElement info = driver.findElement(By.xpath("html/body/table[2]/tbody/tr[2]/td[2]/ul/li[" + i + "]"));
        return info.getText();
    }

    public DelPage removeRoom(int i) {
        WebElement remove = driver.findElement(By.xpath("html/body/table[2]/tbody/tr[2]/td[2]/ul/li[" + i + "]/a[2]"));
        remove.click();
        DelPage page = new DelPage(driver);
        return page;
    }


}
