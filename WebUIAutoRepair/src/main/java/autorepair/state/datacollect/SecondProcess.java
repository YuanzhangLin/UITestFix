package autorepair.state.datacollect;

import autorepair.state.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * rebuild the tree.
 */
public class SecondProcess {

    public static List<DomNodeInfo> domNodeInfoList;
    public static List<PreDomNodeInfo> preDomNodeInfoList;
    public static Map<Integer,Integer> idMapping;

    public static List<DomNodeInfo> runProcess(List<PreDomNodeInfo> inPreDomNodeInfoList){
        preDomNodeInfoList = inPreDomNodeInfoList;
        init();
        buildTree();
        updateId();
        updateChildren();
        labelType();
        setLeafChildren();
        setDomPath();
        return domNodeInfoList;

    }

    private static void setDomPath() {
        for (DomNodeInfo domNodeInfo : domNodeInfoList){
            if (domNodeInfo.getParent() == -1){
                for (int child : domNodeInfo.getChildren()){
                    setDomPathByDFS(domNodeInfoList.get(child), new ArrayList<>(), 0);
                }
            }
        }
    }

    public static void setDomPathByDFS(DomNodeInfo domNodeInfo, List<Integer> domPath, int currentId ){
        if (domNodeInfo.isLeaf()){
            domNodeInfo.getDomPath().addAll(domPath);
            domNodeInfo.getDomPath().add(currentId);
        }else{
            domNodeInfo.getDomPath().addAll(domPath);
            domNodeInfo.getDomPath().add(currentId);
            for (int child : domNodeInfo.getChildren()){
                setDomPathByDFS(domNodeInfoList.get(child), domNodeInfo.getDomPath(), domNodeInfo.getNewElementId());
            }
        }
    }

    private static void setLeafChildren() {
        for (DomNodeInfo domNodeInfo : domNodeInfoList){
            if (domNodeInfo.isLeaf()){
                int parent = domNodeInfo.getParent();
                while(parent != -1){
                    domNodeInfoList.get(parent).getLeafChildren().add(domNodeInfo.getNewElementId());
                    parent = domNodeInfoList.get(parent).getParent();
                }
            }
        }
    }

    private static void labelType() {
        for (DomNodeInfo domNodeInfo : domNodeInfoList){
            if (!domNodeInfo.isLeaf()){
                domNodeInfo.setElementType(Constants.CONTAINER);
            }else if (domNodeInfo.isLeaf() && domNodeInfo.getText().equals("")){
                domNodeInfo.setElementType(Constants.ICON);
            }else {
                domNodeInfo.setElementType(Constants.TEXT);
            }
        }
    }

    public static void init(){
        idMapping = new HashMap<>();
        domNodeInfoList =new ArrayList<>();
    }

    public static void buildTree(){
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList){
            if (preDomNodeInfo.isCoreElement()){
                DomNodeInfo domNodeInfo = dataCopy(preDomNodeInfo);
                domNodeInfoList.add(domNodeInfo);
                domNodeInfo.setLeaf(true);
                idMapping.put(domNodeInfo.getElementId(),domNodeInfoList.size()-1);
                if (domNodeInfo.getParent()!=-1 && !preDomNodeInfoList.get(domNodeInfo.getParent()).isCoreElement()){
                    //find the real parent
                    int parent = domNodeInfo.getParent();
                    while(parent != -1 && !preDomNodeInfoList.get(parent).isCoreElement()){
                        parent = preDomNodeInfoList.get(parent).getParent();
                    }
                    domNodeInfo.setParent(parent);
                }
            }
        }
    }

    //id ->newId, parentId ->newParentId
    public static void updateId(){
        for (DomNodeInfo domNodeInfo : domNodeInfoList){
            domNodeInfo.setNewElementId(idMapping.get(domNodeInfo.getElementId()));
            if (domNodeInfo.getParent() != -1){
                domNodeInfo.setParent(idMapping.get(domNodeInfo.getParent()));
            }
        }
    }

    public static void updateChildren(){
        for (DomNodeInfo domNodeInfo :domNodeInfoList){
            if (domNodeInfo.getParent() != -1){
                domNodeInfoList.get(domNodeInfo.getParent()).getChildren().add(domNodeInfo.getNewElementId());
                domNodeInfoList.get(domNodeInfo.getParent()).setLeaf(false);
            }
        }
    }

    public static DomNodeInfo dataCopy(PreDomNodeInfo preDomNodeInfo){
        DomNodeInfo domNodeInfo = new DomNodeInfo();
        domNodeInfo.setAttributes(preDomNodeInfo.getAttributes());
        domNodeInfo.setElementId(preDomNodeInfo.getElementId());
        domNodeInfo.setId(preDomNodeInfo.getId());
        domNodeInfo.setName(preDomNodeInfo.getName());
        domNodeInfo.setXpath(preDomNodeInfo.getXpath());
        domNodeInfo.setTagName(preDomNodeInfo.getTagName());
        if (preDomNodeInfo.getPresentElementId() == -1 || preDomNodeInfo.getText().length() > preDomNodeInfoList.get(preDomNodeInfo.getPresentElementId()).getText().length()){
            domNodeInfo.setText(preDomNodeInfo.getText());
        }else{
            domNodeInfo.setText(preDomNodeInfoList.get(preDomNodeInfo.getPresentElementId()).getText());
        }
        domNodeInfo.setLinkText(preDomNodeInfo.getLinkText());
        domNodeInfo.setX(preDomNodeInfo.getX());
        domNodeInfo.setY(preDomNodeInfo.getY());
        domNodeInfo.setWidth(preDomNodeInfo.getWidth());
        domNodeInfo.setHeight(preDomNodeInfo.getHeight());
        domNodeInfo.setDisplayed(preDomNodeInfo.isDisplayed());
        domNodeInfo.setEnable(preDomNodeInfo.isEnable());
        domNodeInfo.setSelected(preDomNodeInfo.isSelected());
        domNodeInfo.setParent(preDomNodeInfo.getParent());
        if (preDomNodeInfo.getPresentElementId() == -1){
            domNodeInfo.setLeaf(false);
        }else{
            domNodeInfo.setLeaf(preDomNodeInfoList.get(preDomNodeInfo.getPresentElementId()).isLeaf());
        }
        domNodeInfo.setPresentId(preDomNodeInfo.getPresentElementId());
        return domNodeInfo;
    }

}
