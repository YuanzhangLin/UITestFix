package autorepair.state.edge;

import org.openqa.selenium.By;

import java.io.Serializable;
import java.util.Objects;

public class Identification implements Serializable {

    private static final long serialVersionUID = -1608879189549535808L;

    public Identification(By by) {
        setHow(getHowByBy(by));
        setValue(getvalueByBy(by));
    }


    public enum How {
        xpath, name, id, tagName,
        linkText, partialText, cssSelector, partialLinkText
    }

    public How getHow() {
        return how;
    }

    public void setHow(How how) {
        this.how = how;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private How how;
    private String value;

    public Identification() {
    }

    public Identification(How how, String value) {
        this.how = how;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Identification{" +
                "how=" + how +
                ", value='" + value + '\'' +
                '}';
    }

    public static How getHowByString(String how) {
        switch (how) {
            case "name":
                return How.name;
            case "xpath":
                return How.xpath;
            case "id":
                return How.id;
            case "tagName":
                return How.tagName;
            case "text":
                return How.linkText;
            case "partialText":
                return How.partialText;
            case "cssSelector":
                return How.cssSelector;
            case "partialLinkText":
                return How.partialLinkText;
            default:
                return null;
        }
    }

    public By getBy() {
        switch (how) {
            case name:
                return By.name(value);
            case xpath:
                return By.xpath(value);
            case id:
                return By.id(value);
            case tagName:
                return By.tagName(value);
            case linkText:
                return By.linkText(value);
            case partialText:
                return By.partialLinkText(value);
            case cssSelector:
                return By.cssSelector(value);
            case partialLinkText:
                return By.partialLinkText(value);
            default:
                return null;
        }
    }


    private How getHowByBy(By by) {
        String byString = by.toString();
        String method = byString.substring(byString.indexOf(".") + 1, byString.indexOf(":"));
        System.out.println(method);
        return getHowByString(method);
    }

    private String getvalueByBy(By by) {
        String byString = by.toString();
        String value = byString.substring(byString.indexOf(" ") + 1);
        System.out.println(value);
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identification that = (Identification) o;
        return how == that.how && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(how, value);
    }
}
