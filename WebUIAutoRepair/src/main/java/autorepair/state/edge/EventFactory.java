package autorepair.state.edge;

import autorepair.state.statematchine.StateMachineImpl;
import autorepair.state.vertex.StateVertex;
import utils.UtilsProperties;

import java.io.IOException;
import java.util.List;

public class EventFactory {

    public static Event createNewEvent(StateMachineImpl stateMachine, Identification identification,
                                       String method, String codeLine, String absoluteXpath,
                                       int elementId, EventType eventType, List<Object> arguments) {

        String edgeMethod = "event";
        try{
            edgeMethod = UtilsProperties.getConfigProperties().getProperty("edge").trim();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        switch (edgeMethod){
            default:
                if (stateMachine.getTargetStateVertex() != null){
                    return new Event(stateMachine.getSourceStateVertex().getStateVertexId()
                            , stateMachine.getTargetStateVertex().getStateVertexId(),
                            identification,
                            method, codeLine, absoluteXpath, elementId,eventType,arguments);
                }else{
                    return new Event(stateMachine.getSourceStateVertex().getStateVertexId()
                            , stateMachine.getSourceStateVertex().getStateVertexId(),
                            identification,
                            method, codeLine, absoluteXpath, elementId,eventType,arguments);
                }
        }
    }


    public static Event createNewEvent(StateVertex sourceStateVertex,StateVertex targetStateVertex, Identification identification,
                                                  String method, String codeLine, String absoluteXpath,
                                                  int elementId, EventType eventType, List<Object> arguments) {

        String edgeMethod = "event";
        try{
            edgeMethod = UtilsProperties.getConfigProperties().getProperty("edge").trim();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        switch (edgeMethod){
            default:
                return new Event(sourceStateVertex.getStateVertexId()
                        , targetStateVertex.getStateVertexId(),
                        identification,
                        method, codeLine, absoluteXpath, elementId,eventType,arguments);
        }
    }

}
