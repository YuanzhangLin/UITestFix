package utils;

import autorepair.state.edge.Identification;
import org.apache.commons.io.FileUtils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;

import static config.Settings.OPENCV_DLL_PATH;

public class UtilsSeleniumHelper {
    /* OpenCV bindings. */
    static {
        System.load(OPENCV_DLL_PATH + Core.NATIVE_LIBRARY_NAME + ".dll");
    }


    public static String getHtml(WebDriver driver) {
        return driver.getPageSource();
    }

    public static void labelScreenRed(String fullScreenPath, WebElement webElement, String targetPath) {
        Mat img = Highgui.imread(fullScreenPath);
        Point location = webElement.getLocation();
        Dimension size = webElement.getSize();
        Core.rectangle(img, new org.opencv.core.Point(location.x, location.y),
                new org.opencv.core.Point(location.x + size.width,
                        location.y + size.height), new Scalar(0, 0, 255));
        File fileParent = new File(targetPath.substring(0, targetPath.lastIndexOf(File.separator)));
        if (!fileParent.isFile()) {
            fileParent.mkdirs();
        }
        Highgui.imwrite(targetPath, img);
    }
    public static void labelScreenBlue(String fullScreenPath, WebElement webElement, String targetPath) {
        Mat img = Highgui.imread(fullScreenPath);
        Point location = webElement.getLocation();
        Dimension size = webElement.getSize();
        Core.rectangle(img, new org.opencv.core.Point(location.x, location.y),
                new org.opencv.core.Point(location.x + size.width,
                        location.y + size.height), new Scalar(255, 0, 0));
        File fileParent = new File(targetPath.substring(0, targetPath.lastIndexOf(File.separator)));
        if (!fileParent.isFile()) {
            fileParent.mkdirs();
        }
        Highgui.imwrite(targetPath, img);
    }


    public static String getUrl(WebDriver  driver){
        String script = "var url      = window.location.href; return url;";
        return (String) ((JavascriptExecutor)driver).executeScript(script);
    }

    public static void captureScreen(WebElement webElement, String savePath) {
        System.out.println(savePath);
        try {
            File screenshot = webElement.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(savePath));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }catch (WebDriverException webDriverException){
            webDriverException.printStackTrace();

        }
    }

    public static Identification getWebElementIdentification(WebElement webElement) {
        String webElementString = webElement.toString();
        String locator = webElement.toString().substring(webElementString.indexOf(" -> ") + " -> ".length());
        String how = locator.substring(0, locator.indexOf(": "));
        locator = locator.substring(locator.indexOf(": ") + ": ".length(), locator.length() - 1);
        return new Identification(Identification.getHowByString(how), locator);
    }

}
