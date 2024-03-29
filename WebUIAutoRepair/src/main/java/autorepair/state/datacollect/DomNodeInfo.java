package autorepair.state.datacollect;


import autorepair.state.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomNodeInfo {
    @Override
    public String toString() {
        return "DomNodeInfo{" +
                "newElementId=" + newElementId +
                ", elementId=" + elementId +
                ", attributes=" + attributes +
                ", presentId=" + presentId +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", xpath='" + xpath + '\'' +
                ", text='" + text + '\'' +
                ", tagName='" + tagName + '\'' +
                ", linkText='" + linkText + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", isEnable=" + isEnable +
                ", isDisplayed=" + isDisplayed +
                ", isSelected=" + isSelected +
                ", parent=" + parent +
                ", children=" + children +
                ", isLeaf=" + isLeaf +
                ", matchType=" + matchType +
                ", elementType=" + elementType +
                ", similarityMapping=" + similarityMapping +
                ", leafChildren=" + leafChildren +
                ", domPath=" + domPath +
                '}';
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public boolean isDisplayed() {
        return isDisplayed;
    }

    public void setDisplayed(boolean displayed) {
        isDisplayed = displayed;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public List<Integer> getChildren() {
        return children;
    }

    public void setChildren(List<Integer> children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public int getMatchType() {
        return matchType;
    }

    public void setMatchType(int matchType) {
        this.matchType = matchType;
    }

    public int getElementType() {
        return elementType;
    }

    public void setElementType(int elementType) {
        this.elementType = elementType;
    }

    public Map<Integer, Double> getSimilarityMapping() {
        return similarityMapping;
    }

    public void setSimilarityMapping(Map<Integer, Double> similarityMapping) {
        this.similarityMapping = similarityMapping;
    }

    public List<Integer> getLeafChildren() {
        return leafChildren;
    }

    public void setLeafChildren(List<Integer> leafChildren) {
        this.leafChildren = leafChildren;
    }

    public List<Integer> getDomPath() {
        return domPath;
    }

    public void setDomPath(List<Integer> domPath) {
        this.domPath = domPath;
    }


    public int getNewElementId() {
        return newElementId;
    }

    public void setNewElementId(int newElementId) {
        this.newElementId = newElementId;
    }

    private int newElementId;
    //attr
    private int elementId;

    public int getPresentId() {
        return presentId;
    }

    public void setPresentId(int presentId) {
        this.presentId = presentId;
    }

    private Map<String, String> attributes;
    private int presentId;
    private String id;
    private String name = "";
    private String xpath = "";
    private String text = "";
    private String tagName = "";
    private String linkText = "";
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isEnable;
    private boolean isDisplayed;
    private boolean isSelected;
    // structure info
    private int parent; // ElementId;if parent is 0, this element is root.
    private List<Integer> children = new ArrayList<>(); //childrenId;
    private boolean isLeaf = false;
    // match info
    private int matchType = Constants.UN_MATCH;
    private int elementType = Constants.CONTAINER;//默认
    private Map<Integer, Double> similarityMapping = new HashMap<>();
    // container need a leaf list;
    private List<Integer> leafChildren = new ArrayList<>();
    // leaf need a path list;
    private List<Integer> domPath = new ArrayList<>();

}