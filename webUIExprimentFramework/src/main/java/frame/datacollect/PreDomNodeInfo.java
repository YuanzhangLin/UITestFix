package frame.datacollect;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PreDomNodeInfo {


    private Map<String, String> attributes;
    int elementId;
    String id;
    String name = "";
    String xpath = "";
    String text = "";
    String tagName = "";
    String linkText = "";
    int x;
    int y;
    int width;
    int height;
    boolean isEnable;
    boolean isDisplayed;
    boolean isSelected;

    int parent; // ElementId; if parent is 0, this element is root.
    List<Integer> children = new ArrayList<>(); // childrenId;
    int validChildNumber = 0;

    List<Integer> validChildren = new ArrayList<>();
    boolean isCoreElement = true;
    private int coreElementId = -1;

    int depth = -1;
    private boolean isPresentElement = true;
    private int presentElementId = -1;
    private boolean isLeaf = false;

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public void addAttributes(String key, String value) {
        if (this.attributes.containsKey(key)) {
            this.attributes.put(key, this.attributes.get(key) + " " + value);
        } else {
            this.attributes.put(key, value);
        }
    }

    public int getCoreElementId() {
        return coreElementId;
    }

    public void setCoreElementId(int coreElementId) {
        this.coreElementId = coreElementId;
    }

    public List<Integer> getValidChildren() {
        return validChildren;
    }

    public void setValidChildren(List<Integer> validChildren) {
        this.validChildren = validChildren;
    }


    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public boolean getPresentElement() {
        return isPresentElement;
    }

    public void setPresentElement(boolean presentElementId) {
        isPresentElement = presentElementId;
    }

    public int getPresentElementId() {
        return presentElementId;
    }

    public void setPresentElementId(int presentElement) {
        this.presentElementId = presentElement;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getValidChildNumber() {
        return validChildNumber;
    }

    public void setValidChildNumber(int validChildNumber) {
        this.validChildNumber = validChildNumber;
    }

    public boolean isCoreElement() {
        return isCoreElement;
    }

    public void setCoreElement(boolean coreElement) {
        isCoreElement = coreElement;
    }

}