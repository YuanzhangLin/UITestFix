package autorepair.instrument.newprocess;

import autorepair.actions.WebElementAction;
import autorepair.match.MatchFactory;
import autorepair.state.edge.Event;
import autorepair.state.statematchine.StateMachineImpl;
import autorepair.state.vertex.StateVertex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.openqa.selenium.*;
import utils.UtilsXpath;

import java.util.Arrays;

public class NewWebElementProcess {

    public static Object webelementprocess(StateMachineImpl oldStateMachine, StateMachineImpl newStateMachine,
                                           WebDriver driver,
                                           ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            // If executed normally...
            WebElement webElement = (WebElement) proceedingJoinPoint.getTarget();
            return proceedingJoinPoint.proceed();
        }catch (Exception e ){

        }
        WebElement webElement = (WebElement) proceedingJoinPoint.getTarget();
        // collect the state before the
        StateVertex sourceStateVertex = newStateMachine.collectPageData(driver);
        newStateMachine.setSourceStateVertex(sourceStateVertex);
        Object object = null;
        Event oldEvent = oldStateMachine.getScriptSequence().getEvent(
                proceedingJoinPoint.getSourceLocation().toString(), proceedingJoinPoint.getSignature().getName()
        );
        // execute
        try {
            String absoluteXpath = UtilsXpath.generateXPathForWebElement(webElement, "");

            // add event
            Event newEvent = newStateMachine.addWebElementEvent(driver, proceedingJoinPoint, absoluteXpath);
            newStateMachine.addEvent2ScriptSequence(newEvent);
            object = proceedingJoinPoint.proceed();

        } catch (NoSuchElementException | ElementNotInteractableException exception) {
            String newXpath = MatchFactory.match(oldStateMachine, newStateMachine, oldEvent, driver);
            System.out.println("the xpath of the repaired element:"  + newXpath);
            WebElementAction.doWebElementActionHelper(driver.findElement(By.xpath(newXpath)),
                    oldEvent.getMethod(), Arrays.asList(proceedingJoinPoint.getArgs()));
        }

        // return the executed result.
        return object;
    }
}
