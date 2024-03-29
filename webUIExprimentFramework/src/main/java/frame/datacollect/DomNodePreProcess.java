package frame.datacollect;


import frame.config.Settings;
import java.util.List;

public class DomNodePreProcess {
    public static List<PreDomNodeInfo> preDomNodeInfoList;
    public static String rootPath = Settings.rootPath;
    public static String savePath = rootPath + "timetest2\\";
    public static String version = "originalNew";

    public static List<PreDomNodeInfo> processPreDomNodeInfoList(List<PreDomNodeInfo> inPreDomNodeInfoList){
        preDomNodeInfoList = inPreDomNodeInfoList;
        // init information.
        init(inPreDomNodeInfoList);
        // Removing some elements because isDisplayed attributes of these elements are false.
        checkIsDisplayed(inPreDomNodeInfoList);
        // Removing some elements because sizes of these elements are too small.
        checkSize(inPreDomNodeInfoList);
        // Merging element into parent element if the element is the only valid child element.
        checkCoreElement(inPreDomNodeInfoList);
        // Setting the depth of all element.
        setAllDepth(inPreDomNodeInfoList);
        treeGrouping(inPreDomNodeInfoList);
        // Travelling all element by dfs. The coreElement will be used to rebuild the tree.
        setAllCoreElement(inPreDomNodeInfoList);
        setClusterId(inPreDomNodeInfoList);
        return inPreDomNodeInfoList;
    }

    private static void setClusterId(List<PreDomNodeInfo> preDomNodeInfoList) {
        for (PreDomNodeInfo preDomNodeInfo: preDomNodeInfoList){
            if (preDomNodeInfo.getCoreElementId() != -1
                    && preDomNodeInfoList.get(preDomNodeInfo.getCoreElementId()).getId().equals("")
                    && !preDomNodeInfo.getId().equals("")){
                preDomNodeInfoList.get(preDomNodeInfo.getCoreElementId()).setId(preDomNodeInfo.getId());
            }
        }
    }

    public static List<PreDomNodeInfo> init(List<PreDomNodeInfo> preDomNodeInfoList) {
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList) {
            preDomNodeInfo.setValidChildNumber(preDomNodeInfo.getChildren().size());
            preDomNodeInfo.setDepth(-1);
            preDomNodeInfo.setLeaf(false);
            preDomNodeInfo.getValidChildren().clear();
            preDomNodeInfo.getValidChildren().addAll(preDomNodeInfo.getChildren());
            preDomNodeInfo.setCoreElement(true);
        }
        return preDomNodeInfoList;
    }

    public static List<PreDomNodeInfo> checkIsDisplayed(List<PreDomNodeInfo> preDomNodeInfoList) {
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList) {
            if (!preDomNodeInfo.isDisplayed()) {
                preDomNodeInfo.setCoreElement(false);
                if (preDomNodeInfo.getParent() > 0) {
                    preDomNodeInfoList.get(preDomNodeInfo.getParent())
                            .getValidChildren().removeIf(integer -> integer == preDomNodeInfo.getElementId());
                    preDomNodeInfoList.get(preDomNodeInfo.getParent())
                            .setValidChildNumber(preDomNodeInfoList.get(preDomNodeInfo.getParent()).getValidChildren().size());
                }
            }
        }
        return preDomNodeInfoList;
    }

    public static List<PreDomNodeInfo> checkCoreElement(List<PreDomNodeInfo> preDomNodeInfoList) {
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList) {
            if (!preDomNodeInfo.isDisplayed() && !preDomNodeInfo.isCoreElement()) continue;
            if (preDomNodeInfo.getValidChildNumber() == 1 ) {
                for (int childId : preDomNodeInfo.getChildren()) {
                    PreDomNodeInfo child = preDomNodeInfoList.get(childId);
                    child.setCoreElement(false);
                    child.setCoreElementId(-1);
                    for (String key : child.getAttributes().keySet()) {
                        preDomNodeInfo.addAttributes(key, child.getAttributes().get(key));
                    }
                }
            }
        }
        return preDomNodeInfoList;
    }

    public static List<PreDomNodeInfo> checkSize(List<PreDomNodeInfo> preDomNodeInfoList) {
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList) {
            if (preDomNodeInfo.getWidth() < 5 || preDomNodeInfo.getHeight() < 5) {
                preDomNodeInfo.setCoreElement(false);
                preDomNodeInfo.setDisplayed(false);
                if (preDomNodeInfo.getParent() > 0) {
                    preDomNodeInfoList.get(preDomNodeInfo.getParent())
                            .getValidChildren().removeIf(integer -> integer == preDomNodeInfo.getElementId());
                    preDomNodeInfoList.get(preDomNodeInfo.getParent())
                            .setValidChildNumber(preDomNodeInfoList.get(preDomNodeInfo.getParent()).getValidChildren().size());
                }
            }
        }
        return preDomNodeInfoList;
    }

    //coreElement is the top element of the grouping.
    //present is the bottom element of the grouping.
    public static List<PreDomNodeInfo> treeGrouping(List<PreDomNodeInfo> preDomNodeInfoList) {
        int maxDepth = 0;
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList) {
            maxDepth = Math.max(maxDepth, preDomNodeInfo.getDepth());
        }
        // set leaf
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList) {
            if (preDomNodeInfo.getValidChildNumber() == 0) {
                preDomNodeInfo.setLeaf(true);
            }
        }
        // grouping from down to top -> find the present element
        setAllPresent(preDomNodeInfoList);
        return preDomNodeInfoList;
    }

    public static void setAllPresent(List<PreDomNodeInfo> preDomNodeInfoList) {
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList) {
            if (preDomNodeInfo.getXpath().equals("/html/body[1]")) {
                presentByDFS(preDomNodeInfo);
            }
        }
    }

    public static int presentByDFS(PreDomNodeInfo root) {
        if (root.getValidChildren().size() != 1) {
            root.setPresentElement(true);
            root.setPresentElementId(root.getElementId());
            for (int child : root.getChildren()){
                presentByDFS(preDomNodeInfoList.get(child));
            }
            return root.getPresentElementId();
        } else {
            for (int child : root.getChildren()){
                if (child != root.getValidChildren().get(0)){
                    presentByDFS(preDomNodeInfoList.get(child));
                }
            }
            int presentElementId = presentByDFS(preDomNodeInfoList.get(root.getValidChildren().get(0)));
            root.setPresentElementId(presentElementId);
            root.setPresentElement(false);
            return presentElementId;
        }
    }

    public static void setAllCoreElement(List<PreDomNodeInfo> preDomNodeInfoList) {
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList) {
            if (preDomNodeInfo.getXpath().equals("/html/body[1]")) {
                coreElementByDFS(preDomNodeInfo,0);
            }
        }
    }

    public static void coreElementByDFS(PreDomNodeInfo root, int coreElementId) {
        if (root.isCoreElement()){
            root.setCoreElementId(root.getElementId());
        }else if (!root.isDisplayed()){
            root.setCoreElementId(-1);
        }else{
            root.setCoreElementId(coreElementId);
        }
        for (int child : root.getChildren()){
            coreElementByDFS(preDomNodeInfoList.get(child),root.getCoreElementId());
        }

    }


    public static void setAllDepth(List<PreDomNodeInfo> preDomNodeInfoList) {
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList) {
            if (preDomNodeInfo.getXpath().equals("/html/body[1]")) {
                depthByDFS(preDomNodeInfo, 1);
            }
        }
    }

    public static void depthByDFS(PreDomNodeInfo root, int depth) {
        root.setDepth(depth);
        for (int child : root.getChildren()) {
            int tempChild = child;
            if (!preDomNodeInfoList.get(tempChild).isCoreElement())
                depthByDFS(preDomNodeInfoList.get(tempChild), depth);
            else
                depthByDFS(preDomNodeInfoList.get(tempChild), depth + 1);
        }
    }

}