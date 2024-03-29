package autorepair.state.datacollect;


import java.util.List;

/**
 *
 */
public class DomNodePreProcess {
    public static List<PreDomNodeInfo> preDomNodeInfoList;

    public static List<PreDomNodeInfo> processPreDomNodeInfoList(List<PreDomNodeInfo> inPreDomNodeInfoList){
        preDomNodeInfoList = inPreDomNodeInfoList;
        // init the information.
        init(preDomNodeInfoList);
        checkIsDisplayed(preDomNodeInfoList);
        checkSize(preDomNodeInfoList);
        checkCoreElement(preDomNodeInfoList);
        setAllDepth(preDomNodeInfoList);
        treeClustering(preDomNodeInfoList);
        setAllCoreElement(preDomNodeInfoList);
        setClusterId(preDomNodeInfoList);
        return preDomNodeInfoList;
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
            if (preDomNodeInfo.getValidChildNumber() == 1 ) {//只有一个子元素，所以子元素不保留。
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
                preDomNodeInfo.setDisplayed(false);//虽然可以截图，但仍然统一视为无法显示。
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

    // coreElement is top element, which is used for rebuilding the tree.
    // present element is the bottom element, which is used for check the number of valid children.
    public static List<PreDomNodeInfo> treeClustering(List<PreDomNodeInfo> preDomNodeInfoList) {
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
            if (preDomNodeInfo.getXpath().equals("/html[1]/body[1]")) {
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
            if (preDomNodeInfo.getXpath().equals("/html[1]/body[1]")) {
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
            if (preDomNodeInfo.getXpath().equals("/html[1]/body[1]")) {
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