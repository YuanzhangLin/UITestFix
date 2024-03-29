package autorepair.state.statematchine;

import autorepair.patch.Experiment;
import autorepair.patch.Patch;
import autorepair.patch.Patches;
import autorepair.state.datacollect.JsonProcess;
import autorepair.state.datacollect.PreDomNodeInfo;
import autorepair.state.edge.Event;
import autorepair.state.edge.EventType;
import autorepair.state.graph.StateFlowGraphImpl;
import autorepair.state.script.ScriptSequenceImpl;
import autorepair.state.vertex.StateVertex;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StateMachineImpl {

    public StateVertex getSourceStateVertex() {
        return sourceStateVertex;
    }

    public void setSourceStateVertex(StateVertex sourceStateVertex) {
        this.sourceStateVertex = sourceStateVertex;
    }

    public StateVertex getTargetStateVertex() {
        return targetStateVertex;
    }

    public void setTargetStateVertex(StateVertex targetStateVertex) {
        this.targetStateVertex = targetStateVertex;
    }

    public ScriptSequenceImpl getScriptSequence() {
        return scriptSequence;
    }

    public void setScriptSequence(ScriptSequenceImpl scriptSequence) {
        this.scriptSequence = scriptSequence;
    }

    public StateFlowGraphImpl getStateFlowGraph() {
        return stateFlowGraph;
    }

    public void setStateFlowGraph(StateFlowGraphImpl stateFlowGraph) {
        this.stateFlowGraph = stateFlowGraph;
    }

    private StateVertex sourceStateVertex;
    private StateVertex targetStateVertex;
    private ScriptSequenceImpl scriptSequence;
    private StateFlowGraphImpl stateFlowGraph;

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    private Experiment experiment;



    public String getLastUrl() {
        return lastUrl;
    }

    public void setLastUrl(String lastUrl) {
        this.lastUrl = lastUrl;
    }

    private String lastUrl = "";

    public String getLastPageSource() {
        return lastPageSource;
    }

    public void setLastPageSource(String lastPageSource) {
        this.lastPageSource = lastPageSource;
    }

    private String lastPageSource = "";


    public Patches getPatches() {
        return patches;
    }

    public void setPatches(Patches patches) {
        this.patches = patches;
    }

    private Patches patches;


    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    private String savePath;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

    public StateMachineImpl(String className, String savePath) {
        this.savePath = savePath;
        scriptSequence = new ScriptSequenceImpl(className);
        stateFlowGraph = new StateFlowGraphImpl(className);
        patches = new Patches();
        experiment = new Experiment(className);
    }

    public StateVertex collectPageData(WebDriver driver) throws IOException {
        // Collect page information as state
        StateVertex stateVertex = stateFlowGraph.addStateVertex(driver,this.savePath);
        // save page information
        return stateVertex;
    }

    public Event addWebElementEvent(WebDriver driver, ProceedingJoinPoint proceedingJoinPoint, String absoluteXpath) {
        WebElement webElement = null;
        if (proceedingJoinPoint.getTarget() instanceof WebElement) {
            webElement = (WebElement) proceedingJoinPoint.getTarget();
            List<PreDomNodeInfo> preDomNodeInfoList;
            try {
                preDomNodeInfoList = JsonProcess.readPreDomNodeInfoJson(savePath + sourceStateVertex.getStateVertexId() + File.separator + "preDomNodeInfo.json");
            } catch (IOException ioException) {
                System.out.println("ioexception");
                preDomNodeInfoList = new ArrayList<>();
            }
            System.out.println (preDomNodeInfoList.size());
            Event event = stateFlowGraph.addEvent(this, webElement,
                    proceedingJoinPoint,
                    absoluteXpath, preDomNodeInfoList, EventType.WebElementType);
            return event;
        } else {
            return null;
        }
    }

    public Event addWebDriverFindElementEvent(WebDriver driver, ProceedingJoinPoint proceedingJoinPoint, String absoluteXpath) throws Throwable {
        WebElement webElement = (WebElement) proceedingJoinPoint.proceed();
        return this.addWebDriverFindElementEvent(driver, proceedingJoinPoint, absoluteXpath, webElement);
    }


    public Event addWebDriverFindElementEvent(WebDriver driver, ProceedingJoinPoint proceedingJoinPoint, String absoluteXpath, WebElement webElement) {
        List<PreDomNodeInfo> preDomNodeInfoList;
        try {
            preDomNodeInfoList = JsonProcess.readPreDomNodeInfoJson(savePath + sourceStateVertex.getStateVertexId() + File.separator + "preDomNodeInfo.json");
        } catch (IOException ioException) {
            System.out.println("ioexception");
            preDomNodeInfoList = new ArrayList<>();
        }
        System.out.println(preDomNodeInfoList.size());
        Event event = stateFlowGraph.addEvent(this, webElement,
                proceedingJoinPoint,
                absoluteXpath, preDomNodeInfoList, EventType.WebElementType);
        return event;
    }


    public void save(String savePath) {
        scriptSequence.save(savePath);
        stateFlowGraph.save(savePath);
        if (patches.getPatches().size() > 0) {
            saveAsJson(savePath.replace("output","patches"), "patch",
                    patches);
        }
        saveAsJson(savePath.replace("output","experiment"), "experiment",
                experiment);
    }

    public void saveAsJson(String savePath, String name, Object objects) {
        File file = new File(savePath);
        if (!file.exists()) {
            boolean result = file.mkdirs();
        }
        try (Writer writer = new FileWriter(savePath + name + ".json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(objects, writer);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void load(String savePath) throws IOException, ClassNotFoundException {
        scriptSequence = scriptSequence.load(savePath);
        stateFlowGraph = stateFlowGraph.load(savePath);
    }

    public void addEvent2ScriptSequence(Event event) {
        scriptSequence.addEvent(event);
    }


}
