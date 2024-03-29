package testcases.addressbook.model_based_dataset.test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import config.DriverConfig;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import testcases.Constants;
import testcases.addressbook.model_based_dataset.po.Address;
import testcases.addressbook.model_based_dataset.po.Group;

public class TestExportAllRecordCSVFile {
    WebDriver driver;
    Random random = new Random();
    int rand_number = random.nextInt(1520000);
    static int expected_row_count = 0;
    private static String downloadPath = "F:\\下载";

    @BeforeMethod
    public void before() throws IOException, SQLException, ClassNotFoundException {
        System.setProperty("webdriver.chrome.driver",
                DriverConfig.DRIVER_PATH);
        Address.cleanAddress();
        Group.cleanGroup();
        Address.addAddress("MassCB","MassCB");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(Constants.getAddressBookUrl());
        expected_row_count = driver
                .findElements(By.cssSelector("table#maintable.sortcompletecallback-applyZebra>tbody>tr")).size();
        System.out.println(expected_row_count);
    }

    @Test(priority = 0)
    public void testExportAllRecordCSVFile() {

        driver.findElement(By.xpath("//*[@id='nav']/ul/li[7]/a")).click(); // back to home
        driver.findElement(By.xpath("//*[@id=\"content\"]/form[3]/input")).click();
        File file = getLatestFilefromDir(downloadPath);
        String csvFileName = file.getName();
        System.out.println("CSV File Downloaded is :- " + csvFileName);
        Assert.assertTrue(getRecordsCountInCSV(downloadPath, csvFileName) <= expected_row_count); // To verify that
    }

    private File getLatestFilefromDir(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile;
    }

    @AfterMethod
    public void end() {
        driver.quit();
    }


    public int getRecordsCountInCSV(String downloadPath, String csvFileName) {
        int lineNumberCount = 0;
        try {
            if (!csvFileName.isEmpty() || csvFileName != null) {
                String filePath = downloadPath + System.getProperty("file.separator") + csvFileName;
                System.out.println(filePath);
                File file = new File(filePath);
                if (file.exists()) {
                    System.out.println("File found :" + csvFileName);
                    FileReader fr = new FileReader(file);
                    LineNumberReader linenumberreader = new LineNumberReader(fr);
                    while (linenumberreader.readLine() != null) {
                        lineNumberCount++;
                    }
                    //To remove the header
                    lineNumberCount = lineNumberCount - 1;
                    System.out.println("Total number of lines found in csv : " + (lineNumberCount));
                    linenumberreader.close();
                } else {
                    System.out.println("File does not exists");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineNumberCount;
    }
}
