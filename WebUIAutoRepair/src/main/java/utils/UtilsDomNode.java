package utils;

import autorepair.state.datacollect.DomNodeInfo;
import autorepair.state.datacollect.JsonProcess;
import autorepair.state.datacollect.PreDomNodeInfo;

import java.io.IOException;
import java.util.List;

public class UtilsDomNode {

    public static boolean checkDomNodeContainXpath(DomNodeInfo domNodeInfo, String xpath, String savePath) throws IOException {
        /**If the matched element is executed, check whether it belongs to the part before clustering.*/
        List<PreDomNodeInfo> preDomNodeInfoList =
                JsonProcess.readPreDomNodeInfoJson(savePath + "preDomNodeInfo.json");
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList){
            if (preDomNodeInfo.getXpath().equals(xpath)){
                if (preDomNodeInfo.getCoreElementId() == domNodeInfo.getElementId()){
                    return true;
                }
            }
        }
        return false;
    }

    public static int getElementByAbsoluteXpath(String absoluteXpath, List<PreDomNodeInfo> preDomNodeInfoList) {
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList) {
            if (preDomNodeInfo.getXpath().equals(absoluteXpath)) {
                return preDomNodeInfo.getElementId();
            }
        }
        return -1;
    }

    public static int getElementByAbsoluteXpath(String absoluteXpath, String savePath) throws IOException {
        List<PreDomNodeInfo> preDomNodeInfoList = JsonProcess.readPreDomNodeInfoJson(savePath + "preDomNodeInfo.json");
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList) {
            if (preDomNodeInfo.getXpath().equals(absoluteXpath)) {
                return preDomNodeInfo.getElementId();
            }
        }
        return -1;
    }

    public static DomNodeInfo getDomNodeElementByAbsoluteXpath(String absoluteXpath, String savePath) throws IOException {
        List<PreDomNodeInfo> preDomNodeInfoList = JsonProcess.readPreDomNodeInfoJson(savePath + "preDomNodeInfo.json");
        List<DomNodeInfo> domNodeInfos = JsonProcess.readDomInfoJson(savePath + "domNodeInfo.json");
        PreDomNodeInfo temp = null;
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList) {
            if (preDomNodeInfo.getXpath().equals(absoluteXpath)) {
                temp = preDomNodeInfo;
                break;
            }
        }
        if (temp == null) {
            return null;
        }
        for (DomNodeInfo domNodeInfo : domNodeInfos) {
            if (domNodeInfo.getElementId() == temp.getCoreElementId()) {
                return domNodeInfo;
            }
        }
        return null;
    }

    /**
     * Returns the element with the deepest depth.
     */
    public static PreDomNodeInfo getDeepestPreDomNode(DomNodeInfo domNodeInfo, String savePath) throws IOException {
        List<PreDomNodeInfo> preDomNodeInfoList = JsonProcess.readPreDomNodeInfoJson(savePath + "preDomNodeInfo.json");
        PreDomNodeInfo preDomNodeInfo = null;
        for (PreDomNodeInfo tempPreDomNode : preDomNodeInfoList) {
            if (tempPreDomNode.getCoreElementId() == domNodeInfo.getElementId()) {
                preDomNodeInfo = tempPreDomNode; // The deeper the depth, the later the join.
            }
        }
        return preDomNodeInfo;
    }

    public static PreDomNodeInfo getEventPreDomNode(DomNodeInfo domNodeInfo, String savePath) throws IOException {
        List<PreDomNodeInfo> preDomNodeInfoList = JsonProcess.readPreDomNodeInfoJson(savePath + "preDomNodeInfo.json");
        PreDomNodeInfo preDomNodeInfo = null;
        for (PreDomNodeInfo tempPreDomNode : preDomNodeInfoList) {
            if (tempPreDomNode.getCoreElementId() == domNodeInfo.getElementId()) {
                preDomNodeInfo = tempPreDomNode; // The deeper the depth, the later the join.
                if (preDomNodeInfo.getTagName().equals("input")||
                        preDomNodeInfo.getTagName().equals("button")||
                        preDomNodeInfo.getTagName().equals("a")){
                    return preDomNodeInfo;
                }
            }
        }
        return preDomNodeInfo;
    }

    public static PreDomNodeInfo getSameTagPreDomNode(DomNodeInfo domNodeInfo, String savePath,String tagName) throws IOException {
        List<PreDomNodeInfo> preDomNodeInfoList = JsonProcess.readPreDomNodeInfoJson(savePath + "preDomNodeInfo.json");
        PreDomNodeInfo preDomNodeInfo = null;
        for (PreDomNodeInfo tempPreDomNode : preDomNodeInfoList) {
            if (tempPreDomNode.getCoreElementId() == domNodeInfo.getElementId()) {
                if (tempPreDomNode.getTagName().equals(tagName)){
                        return tempPreDomNode;
                }
            }
        }
        return null;
    }
}
