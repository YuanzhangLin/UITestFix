package autorepair.instrument.oldprocess;

import autorepair.state.edge.Event;
import autorepair.state.statematchine.StateMachineImpl;
import autorepair.state.vertex.StateVertex;
import org.apache.commons.io.FileUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.UtilsDomNode;
import utils.UtilsSeleniumHelper;
import utils.UtilsXpath;

import java.io.File;
import java.nio.file.Files;

public class OldWebDriverProcess {

    public static Object findElementProcess(StateMachineImpl stateMachine, WebDriver driver, ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        WebElement webElement = (WebElement) proceedingJoinPoint.proceed();
        // collect the web page state.
        String absoluteXpath = UtilsXpath.generateXPathForWebElement(webElement, "");
        StateVertex sourceStateVertex = stateMachine.collectPageData(driver);
        stateMachine.setSourceStateVertex(sourceStateVertex);

        int index = UtilsDomNode.getElementByAbsoluteXpath(absoluteXpath, stateMachine.getSavePath() + sourceStateVertex.getStateVertexId() +
                File.separator);
        UtilsSeleniumHelper.captureScreen(webElement,
                stateMachine.getSavePath() + stateMachine.getSourceStateVertex().getStateVertexId() +
                        File.separator + index + ".png");
        UtilsSeleniumHelper.labelScreenRed(stateMachine.getSavePath() + stateMachine.getSourceStateVertex().getStateVertexId() + File.separator + "fullScreen.png",
                webElement, stateMachine.getSavePath() + "event" +
                        File.separator + stateMachine.getScriptSequence().getEdges().size() + ".png");

        // execute...
        Object object = proceedingJoinPoint.proceed();

        // collect the web page state.
//        stateMachine.setTargetStateVertex(stateMachine.getTargetStateVertex());
        // add event.
        Event event = stateMachine.addWebDriverFindElementEvent(driver, proceedingJoinPoint, absoluteXpath);
        stateMachine.addEvent2ScriptSequence(event);

        // return the object.
        return object;

    }

}
