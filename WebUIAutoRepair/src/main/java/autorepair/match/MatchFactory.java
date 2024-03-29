package autorepair.match;

import autorepair.match.sftm.SFTM;
import autorepair.match.sftm2023.SFTM2023;
import autorepair.match.vista.VISTA;
import autorepair.match.water.WATER;
import autorepair.match.webevo.WEBEVO;
import autorepair.state.datacollect.JsonProcess;
import autorepair.state.datacollect.PreDomNodeInfo;
import autorepair.state.edge.Event;
import autorepair.state.statematchine.StateMachineImpl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MatchFactory {


    public static String match(StateMachineImpl oldStateMachine, StateMachineImpl newStateMachine, Event oldEvent, WebDriver driver) throws
            IOException {
        String matchMethod = "water";
        try {
            matchMethod = UtilsProperties.getConfigProperties().getProperty("match").trim();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        switch (matchMethod) {
            case "sftm":
                return matchBySFTM(oldStateMachine, oldEvent, driver);
            case "sftm2023":
                return matchBySFTM2023(oldStateMachine, newStateMachine, oldEvent, driver);
            case "vista":
                return matchByVista(oldStateMachine, newStateMachine, oldEvent, driver);
            case "webevo":
                return matchByWebevo(driver, oldStateMachine, newStateMachine, oldEvent);
            default:
                // water(default)
                return matchByWATER(oldStateMachine, newStateMachine, oldEvent);
        }
    }

    public static String matchByVista(StateMachineImpl oldStateMachine, StateMachineImpl newStateMachine,
                                      Event oldEvent, WebDriver driver) throws IOException {
        String newFullScreenPath = newStateMachine.getSavePath() + newStateMachine.getSourceStateVertex().getStateVertexId() + File.separator
                + "fullScreen.png";
        String oldFullScreenPath = oldStateMachine.getSavePath() + File.separator
                + oldEvent.getSourceVertexId() + File.separator + "fullScreen.png";
        List<PreDomNodeInfo> oldPreDomNodeInfoList = JsonProcess.readPreDomNodeInfoJson(oldStateMachine.getSavePath() + File.separator
                + oldEvent.getSourceVertexId() + File.separator + "preDomNodeInfo.json");
        PreDomNodeInfo preDomNodeInfo = null;
        for (PreDomNodeInfo temp : oldPreDomNodeInfoList) {
            if (temp.getXpath().equals(oldEvent.getAbsoluteXpath())) {
                preDomNodeInfo = temp;
                break;
            }
        }
        return VISTA.retrieveDomNode(oldFullScreenPath, newFullScreenPath, preDomNodeInfo, driver);
    }

    public static String matchBySFTM(StateMachineImpl oldStateMachine, Event oldEvent, WebDriver driver)
            throws IOException {
        String oldHtml = UtilsTxtLoader.readFile02(oldStateMachine.getSavePath() + File.separator
                + oldEvent.getSourceVertexId() + File.separator + "temp.html");
        String newHtml = UtilsSeleniumHelper.getHtml(driver);
        SFTM sftm = new SFTM();

        sftm.match(oldHtml, newHtml);

        return sftm.getNewXpath(oldEvent.getAbsoluteXpath());
    }

    public static String matchBySFTM2023(StateMachineImpl oldStateMachine, StateMachineImpl newStateMachine, Event oldEvent, WebDriver driver)
            throws IOException {
        String oldHtml = UtilsTxtLoader.readFile02(oldStateMachine.getSavePath() + File.separator
                + oldEvent.getSourceVertexId() + File.separator + "temp.html");

        SFTM2023 sftm = new SFTM2023();
        sftm.match(oldStateMachine.getSavePath() + File.separator
                        + oldEvent.getSourceVertexId() + File.separator + "temp.html",
                newStateMachine.getSavePath() + File.separator
                        + newStateMachine.getSourceStateVertex().getStateVertexId()
                        + File.separator + "temp.html");

        return sftm.getNewXpath(oldEvent.getAbsoluteXpath());
    }

    public static String matchByWATER(StateMachineImpl oldStateMachine, StateMachineImpl newStateMachine,
                                      Event oldEvent) throws IOException {
        List<PreDomNodeInfo> oldPreDomNodeInfoList = JsonProcess.readPreDomNodeInfoJson(oldStateMachine.getSavePath() + File.separator
                + oldEvent.getSourceVertexId() + File.separator + "preDomNodeInfo.json");
        String oldHtml = UtilsTxtLoader.readFile02(oldStateMachine.getSavePath() + File.separator
                + oldEvent.getSourceVertexId() + File.separator + "temp.html");
        List<PreDomNodeInfo> newPreDomNodeInfoList = JsonProcess.readPreDomNodeInfoJson(newStateMachine.getSavePath() + File.separator
                + newStateMachine.getSourceStateVertex().getStateVertexId() + File.separator + "preDomNodeInfo.json");
        PreDomNodeInfo preDomNodeInfo = null;
        for (PreDomNodeInfo temp : oldPreDomNodeInfoList) {
            if (temp.getXpath().equals(oldEvent.getAbsoluteXpath())) {
                preDomNodeInfo = temp;
                break;
            }
        }
        if (preDomNodeInfo == null) {
            return "";
        }
        WebElement webElement = WATER.retrieveWebElementFromDOMInfo(newStateMachine.getDriver(),
                preDomNodeInfo, oldHtml, newPreDomNodeInfoList);
        if (webElement != null) {
            return UtilsXpath.generateXPathForWebElement(webElement, "");
        } else {
            return "";
        }
    }



    public static String matchByWebevo(WebDriver driver, StateMachineImpl oldStateMachine, StateMachineImpl newStateMachine, Event oldEvent) throws IOException {
        List<PreDomNodeInfo> newPreDomNodeInfoList = JsonProcess.readPreDomNodeInfoJson(newStateMachine.getSavePath() + File.separator
                + newStateMachine.getSourceStateVertex().getStateVertexId() + File.separator + "preDomNodeInfo.json");
        List<PreDomNodeInfo> oldPreDomNodeInfoList = JsonProcess.readPreDomNodeInfoJson(oldStateMachine.getSavePath() + File.separator
                + oldEvent.getSourceVertexId() + File.separator + "preDomNodeInfo.json");
        String targetText = oldPreDomNodeInfoList.get(oldEvent.getElementId()).getText();
        int index = oldEvent.getElementId();
        String targetPath = oldStateMachine.getSavePath() + oldEvent.getSourceVertexId() +
                File.separator + index + ".png";
        System.out.println(targetPath);
        System.out.println(newStateMachine.getSavePath() + File.separator
                + newStateMachine.getSourceStateVertex().getStateVertexId() + File.separator + "preDomNodeInfo.json");
        String candidateLocation = newStateMachine.getSavePath() + File.separator
                + newStateMachine.getSourceStateVertex().getStateVertexId() + File.separator
                + "candidate" + File.separator;
        WEBEVO webevo = new WEBEVO();
        String xpath = webevo.processingCandidatesImage(driver, candidateLocation, newPreDomNodeInfoList, targetText, targetPath);

        return xpath;
    }
}
