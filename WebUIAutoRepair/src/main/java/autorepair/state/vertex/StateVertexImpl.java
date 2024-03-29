package autorepair.state.vertex;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.UtilsXpath;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StateVertexImpl implements StateVertex, Serializable {

    private static final long serialVersionUID = 123411177783488L;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    public int getStateVertexId() {
        return stateVertexId;
    }

    public void setStateVertexId(int stateVertexId) {
        this.stateVertexId = stateVertexId;
    }

    public String getDom() {
        return dom;
    }

    public void setDom(String dom) {
        this.dom = dom;
    }

    private int stateVertexId;
    private String dom;

    public List<String> getInputText() {
        return inputText;
    }

    public void setInputText(List<String> inputText) {
        this.inputText = inputText;
    }

    private List<String> inputText;


    public StateVertexImpl(int stateVertexId, String url, String dom, WebDriver driver) {
        this.dom = dom;
        this.url = url;
        this.stateVertexId = stateVertexId;
        this.inputText = new ArrayList<>();
        for (WebElement webElement : driver.findElements(By.tagName("input"))) {
            inputText.add(webElement.getText());
        }
    }

    public StateVertexImpl(String url, String dom, WebDriver driver) {
        this.dom = dom;
        this.url = url;
        this.inputText = new ArrayList<>();
        for (WebElement webElement : driver.findElements(By.tagName("input"))) {
            String value = webElement.getAttribute("value");
            inputText.add(value);
        }
    }

    @Override
    public String toString() {
        return "StateVertexImpl{" +
                "stateVertexId=" + stateVertexId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateVertexImpl that = (StateVertexImpl) o;
        return Objects.equals(dom.replaceAll("[0-9]+", ""),
                that.dom.replaceAll("[0-9]+", ""))
                && Objects.equals(inputText, that.inputText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dom.replaceAll("[0-9]+", ""), inputText);
    }
}
