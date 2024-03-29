package utils;

import config.Settings;
import org.opencv.core.Point;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UtilsXpath {

    public static String getElementXPath(JavascriptExecutor js, WebElement element) {
        return (String) js
                .executeScript("var getElementXPath = function(element) {" + "return getElementTreeXPath(element);"
                        + "};" + "var getElementTreeXPath = function(element) {" + "var paths = [];"
                        + "for (; element && element.nodeType == 1; element = element.parentNode)  {" + "var index = 0;"
                        + "for (var sibling = element.previousSibling; sibling; sibling = sibling.previousSibling) {"
                        + "if (sibling.nodeType == Node.DOCUMENT_TYPE_NODE) {" + "continue;" + "}"
                        + "if (sibling.nodeName == element.nodeName) {" + "++index;" + "}" + "}"
                        + "var tagName = element.nodeName.toLowerCase();"
                        + "var pathIndex = (\"[\" + (index+1) + \"]\");" + "paths.splice(0, 0, tagName + pathIndex);"
                        + "}" + "return paths.length ? \"/\" + paths.join(\"/\") : null;" + "};"
                        + "return getElementXPath(arguments[0]);", element);
    }

    public static String generateXPathForWebElement(WebElement childElement, String current) {
        String childTag = childElement.getTagName();
        if (childTag.equals("html")) {
            return "/html[1]" + current;
        }
        WebElement parentElement = childElement.findElement(By.xpath(".."));
        List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
        int count = 0;
        for (int i = 0; i < childrenElements.size(); i++) {
            WebElement childrenElement = childrenElements.get(i);
            String childrenElementTag = childrenElement.getTagName();
            if (childTag.equals(childrenElementTag)) {
                count++;
            }
            if (childElement.equals(childrenElement)) {
                return generateXPathForWebElement(parentElement, "/" + childTag + "[" + count + "]" + current);
            }
        }
        return null;
    }

    public static String getXPathFromLocation(Point matches, WebDriver driver) {
        String getXpathScript = "window.getPathTo = function(element) {" + "	if (element===document.body) "
                + "		return element.tagName + '[1]'; " + "	var ix= 0; "
                + "	var siblings= element.parentNode.childNodes; " + "	for (var i= 0; i<siblings.length; i++) {"
                + "		var sibling= siblings[i];" + "		if (sibling===element)"
                + "			return getPathTo(element.parentNode)+'/'+element.tagName+'['+(ix+1)+']';"
                + "		if (sibling.nodeType===1 && sibling.tagName===element.tagName)" + " 			ix++;" + "	}"
                + "};";
        String elemFromPoint = "elemForPoint = document.elementFromPoint(" + matches.x + "," + matches.y + ");";
        String getXpathFromPoint = "window.elemFromPoint = window.getPathTo(elemForPoint);";
        ((JavascriptExecutor) driver).executeScript(getXpathScript + elemFromPoint + getXpathFromPoint);
        String xpath = (String) ((JavascriptExecutor) driver).executeScript("return window.elemFromPoint");
        String result = "/HTML[1]/" + xpath;

        return result.toLowerCase();
    }

}
