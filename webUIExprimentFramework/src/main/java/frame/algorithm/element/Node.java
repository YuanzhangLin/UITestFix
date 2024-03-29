package frame.algorithm.element;

import frame.datacollect.PreDomNodeInfo;

public class Node extends PreDomNodeInfo {
    Node(PreDomNodeInfo preDomNodeInfo){
        this.setX(preDomNodeInfo.getX());
        this.setY(preDomNodeInfo.getY());
        this.setWidth(preDomNodeInfo.getWidth());
        this.setHeight(preDomNodeInfo.getHeight());
        this.setXpath(preDomNodeInfo.getXpath());
        this.setText(preDomNodeInfo.getText());
        this.setAttributes(preDomNodeInfo.getAttributes()); // class,name
        this.setId(preDomNodeInfo.getId());
    }

}
