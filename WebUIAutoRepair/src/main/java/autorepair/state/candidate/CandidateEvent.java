package autorepair.state.candidate;

import autorepair.state.edge.Identification;
import autorepair.state.vertex.StateVertex;
import autorepair.state.vertex.StateVertexImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CandidateEvent {

    public WebElementEventType getEventType() {
        return eventType;
    }

    public void setEventType(WebElementEventType eventType) {
        this.eventType = eventType;
    }

    public List<Object> getArguments() {
        return arguments;
    }

    public void setArguments(List<Object> arguments) {
        this.arguments = arguments;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    private Identification identification;
    private WebElementEventType eventType;
    private List<Object> arguments;

    public StateVertex getSourceStateVertex() {
        return sourceStateVertex;
    }

    public void setSourceStateVertex(StateVertex sourceStateVertex) {
        this.sourceStateVertex = sourceStateVertex;
    }
    private int elementId;

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }

    private StateVertex sourceStateVertex;


    public CandidateEvent(Identification identification, WebElementEventType eventType, List<Object> arguments,int elementId) {
        this.identification = identification;
        this.eventType = eventType;
        this.arguments = arguments;
        this.elementId = elementId;
    }


    public void execute(WebDriver driver){
        WebElement webElement = driver.findElement(identification.getBy());
        switch (eventType){
            case click:
                webElement.click();
                break;
            case sendKeys:
                webElement.sendKeys((CharSequence) arguments.get(0));
                break;
        }
    }


}
