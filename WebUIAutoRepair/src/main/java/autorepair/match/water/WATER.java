package autorepair.match.water;

import autorepair.state.datacollect.PreDomNodeInfo;
import com.sun.security.ntlm.NTLMException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.UtilsTxtLoader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WATER {
    static String text = "";
    static String xpath = "";
    static int sharedindex;
    public static WebElement retrieveWebElementFromDOMInfo(WebDriver driver, PreDomNodeInfo oldPreDomNodeInfo
            , String oldHtml, List<PreDomNodeInfo> newPreDomNodeInfoList) {
        sharedindex = 0 ;
        String id = oldPreDomNodeInfo.getId();
        String classAttr = oldPreDomNodeInfo.getAttributes().get("class");
        String nameAttr = oldPreDomNodeInfo.getAttributes().get("name");
        String tagName = oldPreDomNodeInfo.getTagName();
        text = "";
        xpath = oldPreDomNodeInfo.getXpath();
        System.out.println(oldPreDomNodeInfo
                .getXpath());
        checkHtml(oldHtml, oldPreDomNodeInfo.getXpath());
        System.out.println("text" + text);
        String xpath = oldPreDomNodeInfo.getXpath();
        List<WebElement> domElements = new LinkedList<WebElement>();

        if (id != null && !id.isEmpty()) {
            WebElement elem_id = null;
            try {
                elem_id = driver.findElement(By.id(id));
            } catch (Exception Ex) {

            }
            if (elem_id != null)
                return elem_id;
        }

        if (text != null && !text.isEmpty()) {
            WebElement elem_text = null;
            try {
                elem_text = driver.findElement(By.xpath("//*[text()='" + text + "']"));
            } catch (Exception Ex) {

            }
            if (elem_text != null)
                return elem_text;
        }

        if (nameAttr != null && !nameAttr.isEmpty()) {
            WebElement elem_name = null;
            try {
                elem_name = driver.findElement(By.name(nameAttr));
            } catch (Exception Ex) {

            }
            if (elem_name != null)
                return elem_name;
        }

        if (classAttr != null && !classAttr.isEmpty()) {
            WebElement elem_class = null;
            try {
                elem_class = driver.findElement(By.className(classAttr));
            } catch (Exception Ex) {

            }
            if (elem_class != null) {
                return elem_class;
            }
        }

        if (xpath != null && !xpath.isEmpty()) {
            WebElement elem_xpath = null;
            try {
                elem_xpath = driver.findElement(By.xpath(xpath));
            } catch (Exception Ex) {

            }
            if (elem_xpath != null)
                return elem_xpath;
        }


        for (PreDomNodeInfo preDomNodeInfo : newPreDomNodeInfoList) {
            if (getSimilarityScore(oldPreDomNodeInfo, preDomNodeInfo).compareTo(0.5) > 0) {
                System.out.println("get element by similarity:" + oldPreDomNodeInfo.getText());

                return driver.findElement(By.xpath(preDomNodeInfo.getXpath()));
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/form[1]/fieldset[1]/input[3]";
        String oldHtml = UtilsTxtLoader.readFile02("D:\\rep\\WebUIAutoRepair\\output\\new\\testcases\\Claroline_Test_Suite\\model_based_dataset\\TestAddAdmin\\2\\temp.html");
        checkHtml(oldHtml,"/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/form[1]/fieldset[1]/input[3]");
    }

    public static boolean checkHtml(String html, String xpath) {
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByTag("body");
        for (Element element : elements) {
            sharedindex++;
            getAllChildXpath(element,"/html[1]/body[1]",0);
        }
        return true;
    }


    private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static int computeLevenshteinDistance(CharSequence str1, CharSequence str2) {
        int[][] distance = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++)
            distance[i][0] = i;
        for (int j = 0; j <= str2.length(); j++)
            distance[0][j] = j;

        for (int i = 1; i <= str1.length(); i++)
            for (int j = 1; j <= str2.length(); j++)
                distance[i][j] = minimum(distance[i - 1][j] + 1, distance[i][j - 1] + 1,
                        distance[i - 1][j - 1] + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1));
        return distance[str1.length()][str2.length()];
    }

    private static Double getSimilarityScore(PreDomNodeInfo a, PreDomNodeInfo b) {
        double alpha = 0.9;
        double rho, rho1, rho2 = 0;
        if (a.getTagName().equals(b.getTagName())) {
            double levDist = computeLevenshteinDistance(a.getXpath(), b.getXpath());
            rho1 = 1 - levDist / Math.max(a.getXpath().length(), b.getXpath().length());
            if (Math.abs(a.getX() - b.getX()) <= 5 && Math.abs((a.getX() + a.getWidth()) - (b.getX() - b.getWidth())) <= 5
                    && Math.abs(a.getY() - b.getY()) <= 5 && Math.abs((a.getY() + a.getHeight()) - (b.getY() - b.getHeight())) <= 5) {
                rho2 = rho2 + 1;
            }
            rho2 = rho2 / 2;
            rho = (rho1 * alpha + rho2 * (1 - alpha));
            return rho;
        }
        return 0.0;
    }

    public  static  void getAllChildXpath(Element element,String nowPath,int parent){
        if (element.tagName().equals("script"))
            return;
        if (element.tag().toString().contains("<") )
            return;
        Elements elements = element.children();
        Map<String ,Integer> map = new HashMap<>();
        if (nowPath.equals(xpath)){
            text = element.text();
            System.out.println("get+" + element.tagName() +":"+element.text());
        }
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
                //排除找不到的元素
                sharedindex++;
                if (!child.tagName().equals("svg")){
                    getAllChildXpath(child,childXpath,sharedindex-1);
                }
            }catch (NoSuchElementException e ){
                System.out.println("[NoSuchElement]" + sharedindex);
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("[Exception]" + sharedindex);
                e.printStackTrace();
            }
        }
    }
}
