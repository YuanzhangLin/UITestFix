package autorepair.instrument.oldprocess;

import autorepair.state.edge.Event;
import autorepair.state.statematchine.StateMachineImpl;
import autorepair.state.vertex.StateVertex;
import org.apache.commons.io.FileUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.UtilsDomNode;
import utils.UtilsSeleniumHelper;
import utils.UtilsXpath;

import java.io.File;

public class OldWebElementProcess {

    public static Object webelementprocess(StateMachineImpl stateMachine, WebDriver driver,
                                           ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        WebElement webElement = (WebElement) proceedingJoinPoint.getTarget();
        System.out.println(webElement);
        boolean execute = false;
        Object object = null;
        if (proceedingJoinPoint.getSignature().getName().equals("getText")){
            object = proceedingJoinPoint.proceed();
            execute = true;
        }
        try {
            // 收集状态
            StateVertex sourceStateVertex = stateMachine.collectPageData(driver);
            stateMachine.setSourceStateVertex(sourceStateVertex);

            String absoluteXpath = UtilsXpath.generateXPathForWebElement(webElement, "");
            int index = UtilsDomNode.getElementByAbsoluteXpath(absoluteXpath, stateMachine.getSavePath() + sourceStateVertex.getStateVertexId() +
                    File.separator);
            UtilsSeleniumHelper.captureScreen(webElement,
                    stateMachine.getSavePath() + stateMachine.getSourceStateVertex().getStateVertexId() +
                            File.separator + index + ".png");
            UtilsSeleniumHelper.labelScreenRed(stateMachine.getSavePath() + stateMachine.getSourceStateVertex().getStateVertexId() + File.separator + "fullScreen.png",
                    webElement, stateMachine.getSavePath() + "event" +
                            File.separator + stateMachine.getScriptSequence().getEdges().size() + ".png");

            // 新增事件
            Event event = stateMachine.addWebElementEvent(driver, proceedingJoinPoint, absoluteXpath);
            stateMachine.addEvent2ScriptSequence(event);

//        StateVertex targetStateVertex = stateMachine.collectPageData(driver);
//        stateMachine.setTargetStateVertex(targetStateVertex);
//        stateMachine.setTargetStateVertex(stateMachine.getTargetStateVertex());
        }catch (StaleElementReferenceException staleElementReferenceException){
            staleElementReferenceException.printStackTrace();
        }
        if (!execute){
            //执行
            object = proceedingJoinPoint.proceed();
        }

        // 返回结果
        return object;

    }
}
