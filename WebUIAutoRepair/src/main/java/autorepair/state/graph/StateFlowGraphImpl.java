package autorepair.state.graph;

import autorepair.state.candidate.CandidateEvent;
import autorepair.state.datacollect.DomInformationCollection;
import autorepair.state.datacollect.PreDomNodeInfo;
import autorepair.state.edge.Event;
import autorepair.state.edge.EventFactory;
import autorepair.state.edge.EventType;
import autorepair.state.statematchine.StateMachineImpl;
import autorepair.state.vertex.StateVertex;
import autorepair.state.vertex.StateVertexFactory;
import autorepair.state.vertex.StateVertexImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DirectedPseudograph;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.UtilsDomNode;
import utils.UtilsSeleniumHelper;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class StateFlowGraphImpl implements StateFlowGraph, Serializable {

    private static final long serialVersionUID = 123411117983488L;
    private final AbstractBaseGraph<StateVertex, Event> sfg;
    private final String className;


    public StateFlowGraphImpl(String className) {
        this.className = className;
        this.sfg = new DirectedPseudograph<>(Event.class);
    }

    public Set<Event> outgoingEdgesOf(StateVertex stateVertex){
        return sfg.outgoingEdgesOf(stateVertex);
    }

    public Set<StateVertex> getAllState() {
        return sfg.vertexSet();
    }

    public Set<Event> getAllEvent() {
        return sfg.edgeSet();
    }

    public StateVertex addStateVertex(StateMachineImpl stateMachine) throws IOException {
        StateVertex stateVertex = StateVertexFactory.createNewStateVertex(stateMachine);
        if (sfg.containsVertex(stateVertex)) {
            // Find the target vertex and return
            for (StateVertex tempStateVertex : sfg.vertexSet()) {
                if (tempStateVertex.equals(stateVertex)) {
                    return tempStateVertex;
                }
            }
            return null;
        } else {
            // add and return
            stateVertex.setStateVertexId(sfg.vertexSet().size());
            sfg.addVertex(stateVertex);
            DomInformationCollection targetDomInformationCollection = new DomInformationCollection();
            targetDomInformationCollection.collect(stateMachine.getDriver(),
                    stateMachine.getSavePath() +
                    stateVertex.getStateVertexId() + File.separator);
            return stateVertex;
        }
    }


    public StateVertex addStateVertex(WebDriver driver, String savePath) throws IOException {
        StateVertex stateVertex = StateVertexFactory.createNewStateVertex(driver);
        if (sfg.containsVertex(stateVertex)) {
            // Find the target vertex and return
            for (StateVertex tempStateVertex : sfg.vertexSet()) {
                if (tempStateVertex.equals(stateVertex)) {
                    return tempStateVertex;
                }
            }
            return null;
        } else {
            // add and return
            stateVertex.setStateVertexId(sfg.vertexSet().size());
            sfg.addVertex(stateVertex);
            DomInformationCollection targetDomInformationCollection = new DomInformationCollection();
            targetDomInformationCollection.collect(driver, savePath +
                    stateVertex.getStateVertexId() + File.separator);
            return stateVertex;
        }
    }

    public List<Event> getShortestPath(StateVertex start, StateVertex end) {
        if (!sfg.containsVertex(start)) {
            System.out.println("sfg not contain start");
        }
        if (!sfg.containsVertex(end)) {
            System.out.println("sfg not contain end");
        }
        if (start.getStateVertexId() == 16) {
            System.out.println(((StateVertexImpl) start).getInputText());
            System.out.println(((StateVertexImpl) end).getInputText());
            System.out.println(start.getDom());
            System.out.println(end.getDom());
        }

        System.out.println(sfg);
        System.out.println(start);
        System.out.println(end);
        return DijkstraShortestPath.findPathBetween(sfg, start, end).getEdgeList();
    }

    public StateVertex getStateVertexById(int id) {
        for (StateVertex stateVertex : sfg.vertexSet()) {
            if (stateVertex.getStateVertexId() == id) {
                return stateVertex;
            }
        }
        return null;
    }

    public Event addEvent(StateMachineImpl stateMachine, WebElement webElement,
                          ProceedingJoinPoint proceedingJoinPoint, String absoluteXpath,
                          List<PreDomNodeInfo> preDomNodeInfoList, EventType eventType) {
        String signature = proceedingJoinPoint.getSignature().toString();
        String sourceLocation = proceedingJoinPoint.getSourceLocation().toString();
        String method = signature.substring(signature.lastIndexOf(".") + 1, signature.lastIndexOf("("));
        int elementId = UtilsDomNode.getElementByAbsoluteXpath(absoluteXpath, preDomNodeInfoList);
        Event event = EventFactory.createNewEvent(stateMachine,
                UtilsSeleniumHelper.getWebElementIdentification(webElement),
                method, sourceLocation, absoluteXpath, elementId, eventType,
                Arrays.asList(proceedingJoinPoint.getArgs()));
        if (sfg.containsEdge(event)) {
            for (Event tempEvent : sfg.edgeSet()) {
                if (event.equals(tempEvent)) {
                    return tempEvent;
                }
            }
            return null;
        } else {
            event.setEventId(sfg.edgeSet().size());
            sfg.addEdge(getStateVertexById(event.getSourceVertexId()), getStateVertexById(event.getTargetVertexId()),
                    event);
            return event;
        }
    }


    public Event addEvent(StateVertex sourceStateVertex, StateVertex targetStateVertex, CandidateEvent candidateEvent,
                          int elementId) {
        Event event = EventFactory.createNewEvent(sourceStateVertex, targetStateVertex, candidateEvent.getIdentification(),
                candidateEvent.getEventType().toString(),
                "",
                candidateEvent.getIdentification().getValue(), elementId, EventType.WebElementType,
                candidateEvent.getArguments());
        if (sfg.containsEdge(event)) {
            for (Event tempEvent : sfg.edgeSet()) {
                if (event.equals(tempEvent)) {
                    return tempEvent;
                }
            }
            return null;
        } else {
            event.setEventId(sfg.edgeSet().size());
            sfg.addEdge(getStateVertexById(event.getSourceVertexId()), getStateVertexById(event.getTargetVertexId()),
                    event);
            return event;
        }
    }

    public void save(String savePath) {
        try ( // Create an ObjectOutputStream output stream
             ObjectOutputStream oos = new ObjectOutputStream(
                     new FileOutputStream(savePath + className + ".txt"))) {
            // serialize object to file
            oos.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StateFlowGraphImpl load(String savePath) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(savePath + className + ".txt"));
        return (StateFlowGraphImpl) ois.readObject();
    }

    @Override
    public String toString() {
        return "StateFlowGraphImpl{" +
                "sfg=" + sfg +
                '}';
    }
}

