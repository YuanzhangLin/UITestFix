package autorepair.state.vertex;

import autorepair.state.statematchine.StateMachineImpl;
import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.UtilsProperties;
import utils.UtilsSeleniumHelper;

import java.io.IOException;


public class StateVertexFactory {

    public static StateVertex createNewStateVertex(WebDriver driver) {
        String stateVertexMethod = "StateVertexImpl";
        try{
            stateVertexMethod = UtilsProperties.getConfigProperties().getProperty("state_vertex").trim();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        switch (stateVertexMethod){
            default:
                return new StateVertexImpl(driver.getCurrentUrl(), driver.getPageSource(),driver);
        }
    }

    public static StateVertex createNewStateVertex(StateMachineImpl stateMachine) {
        WebDriver driver = stateMachine.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 1);
        String url = "";
        String page = "";
            url = driver.getCurrentUrl();
            page = driver.getPageSource();
        String stateVertexMethod = "StateVertexImpl";
        try{
            stateVertexMethod = UtilsProperties.getConfigProperties().getProperty("state_vertex").trim();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        switch (stateVertexMethod){
            default:
                return new StateVertexImpl(url, page,driver);
        }
    }
}
