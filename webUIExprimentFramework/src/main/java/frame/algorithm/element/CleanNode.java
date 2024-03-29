package frame.algorithm.element;

import frame.datacollect.JsonProcess;
import frame.datacollect.PreDomNodeInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.NoSuchElementException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CleanNode {
    List<Node> nodes;
    public List<PreDomNodeInfo > preDomNodeInfoList;
    public static void main(String[] args) throws IOException {
        List<PreDomNodeInfo> oldDomNodeInfoList =
                JsonProcess.readPreDomNodeInfoJson(
                        "D:\\rep\\webUIDataset\\data\\test\\old"
                                + File.separator + "preDomNodeInfo.json");
        CleanNode htmlTreeNodes = new CleanNode(oldDomNodeInfoList,
                "D:\\rep\\webUIDataset\\data\\test\\old"
                        + File.separator + "old.html"
                );
    }

    public CleanNode(List<PreDomNodeInfo > preDomNodeInfoList, String htmlPath){
        this.preDomNodeInfoList = preDomNodeInfoList;
        checkHtml(htmlPath);
    }

    public boolean checkHtml( String htmlPath)  {
        Document document = Jsoup.parse(readFileAsString(htmlPath));
        Elements elements = document.getElementsByTag("body");
        for (Element element : elements){
            getAllChildXpath(element,"/html/body[1]");
        }
        return true;
    }
    public String readFileAsString(String fileName){
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));

        }catch (IOException e ){

        }
        return data;
    }

    public   void getAllChildXpath(Element element,String nowPath){
        if (element.tagName().equals("script"))
            return;
        if (element.tag().toString().contains("<") )
            return;
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList){
            if (nowPath.equals(preDomNodeInfo.getXpath())){
                preDomNodeInfo.setText(element.text());
                break;
            }
        }
        Elements elements = element.children();
        Map<String ,Integer> map = new HashMap<>();
        for (Element child : elements){
            if (child.tag().toString().contains("<")) continue;
            if (map.containsKey(child.tagName())){
                map.put(child.tagName(),map.get(child.tagName())+1);
            }else{
                map.put(child.tagName(),1);
            }
            String childXpath = "";
            if (child.tagName().equals("svg")){
                childXpath = nowPath+"/*[name()='svg']["+map.get(child.tagName())+"]";
            }else {
                childXpath = nowPath + "/" + child.tagName() + "["+map.get(child.tagName())+"]";
            }
            try{
                if (!child.tagName().equals("svg")){
                    getAllChildXpath(child,childXpath);
                }
            }catch (NoSuchElementException e ){
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
