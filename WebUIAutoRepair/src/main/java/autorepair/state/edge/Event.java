package autorepair.state.edge;

import org.openqa.selenium.interactions.Actions;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Event implements Serializable {

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    private EventType eventType;
    private static final long serialVersionUID = 123411166663488L;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getSourceVertexId() {
        return sourceVertexId;
    }

    public void setSourceVertexId(int sourceVertexId) {
        this.sourceVertexId = sourceVertexId;
    }

    public int getTargetVertexId() {
        return targetVertexId;
    }

    public void setTargetVertexId(int targetVertexId) {
        this.targetVertexId = targetVertexId;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCodeLine() {
        return codeLine;
    }

    public void setCodeLine(String codeLine) {
        this.codeLine = codeLine;
    }

    private int eventId;
    private int sourceVertexId;
    private int targetVertexId;
    private Identification identification;
    private String method;
    private String codeLine;

    public String getAbsoluteXpath() {
        return absoluteXpath;
    }

    public void setAbsoluteXpath(String absoluteXpath) {
        this.absoluteXpath = absoluteXpath;
    }

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }

    private String absoluteXpath;
    private int elementId;

    public List<Object> getArguments() {
        return arguments;
    }

    public void setArguments(List<Object> arguments) {
        this.arguments = arguments;
    }

    private List<Object> arguments;


    public Event(int sourceVertexId, int targetVertexId, Identification identification, String method, String codeLine,
        String absoluteXpath, int elementId, EventType eventType, List<Object> arguments) {
        this.sourceVertexId = sourceVertexId;
        this.targetVertexId = targetVertexId;
        this.identification = identification;
        this.method = method;
        this.codeLine = codeLine;
        this.absoluteXpath = absoluteXpath;
        this.elementId = elementId;
        this.eventType = eventType;
        this.arguments = arguments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return sourceVertexId == event.sourceVertexId && targetVertexId == event.targetVertexId && Objects.equals(identification, event.identification) && Objects.equals(method, event.method) && Objects.equals(codeLine, event.codeLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceVertexId, targetVertexId, identification, method, codeLine);
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventType=" + eventType +
                ", eventId=" + eventId +
                ", sourceVertexId=" + sourceVertexId +
                ", targetVertexId=" + targetVertexId +
                ", identification=" + identification +
                ", method='" + method + '\'' +
                ", arguments=" + arguments +
                '}';
    }
}
