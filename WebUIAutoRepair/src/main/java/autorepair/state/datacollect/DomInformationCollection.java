package autorepair.state.datacollect;

import com.google.common.io.Resources;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import utils.UtilsSeleniumHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Collect and save web page information.
 */
public class DomInformationCollection {

    private WebDriver driver;
    private int sharedindex;
    private JsonProcess jsonProcess;
    private List<PreDomNodeInfo> preDomNodeInfoList;
    private List<DomNodeInfo> domNodeInfoList;
    private String rootPath;
    private Logger logger = Logger.getLogger("com.test.testDemo");
    private BufferedImage img = null;


    public boolean stringToFile(String fileContent, String outPath, String fileName) {
        boolean result = false;
        File file = new File(outPath, fileName);
        OutputStream os = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            os = new FileOutputStream(file);
            os.write(fileContent.getBytes());
            os.flush();
            result = true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }


    public void collect(WebDriver driver, String savePath) throws IOException {
        this.driver = driver;
        rootPath = savePath;
        jsonProcess = new JsonProcess();
        /* screenshot */
        File srcfile;
        srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcfile, new File(rootPath + "fullScreen.png"));
        img = ImageIO.read(new File(rootPath + "fullScreen.png"));
        /*html*/
        String html = UtilsSeleniumHelper.getHtml(driver);
        parseHtml(html);
        stringToFile(html, savePath, "temp.html");
        /*tree*/
        preDomNodeInfoList = jsonProcess.getPreDomNodeInfoList();
        setRemainingInfo();
        jsonProcess.setPreDomNodeInfoList(preDomNodeInfoList);
        preDomNodeInfoList = DomNodePreProcess.processPreDomNodeInfoList(preDomNodeInfoList);
        jsonProcess.setPreDomNodeInfoList(preDomNodeInfoList);
        jsonProcess.writePreDomNodeInfoJsonFile(rootPath + "preDomNodeInfo.json");
        domNodeInfoList = SecondProcess.runProcess(preDomNodeInfoList);
        jsonProcess.setDomNodeInfoList(domNodeInfoList);
        jsonProcess.writeDomNodeInfoJsonFile(rootPath + "domNodeInfo.json");
//        getScreenShot();
    }

    /**
     * Get screenshots of all elements.
     *
     * @throws IOException
     */
    public void getScreenShot() throws IOException {
        for (DomNodeInfo domNodeInfo : domNodeInfoList) {
            WebElement webElement = (driver.findElement(By.xpath(domNodeInfo.getXpath())));
            File srcFile = webElement.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(rootPath + domNodeInfo.getNewElementId() + ".png"));
        }
    }

    /**
     * Set the information that Jsoup cannot extract yet.
     */
    public void setRemainingInfo() throws IOException {
        String xpathString = getXpathString();
        xpathString = xpathString.substring(0, xpathString.length() - 1);
        String infoList = getXPathFromLocation(xpathString);
        String[] infoArray = infoList.split(";");
        int index = 0;
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList) {
            String[] singleElementInfo = infoArray[index].split(",");
            if (singleElementInfo.length < 4 || infoArray[index].contains("N")) {
                preDomNodeInfo.setX(0);
                preDomNodeInfo.setY(0);
                preDomNodeInfo.setWidth(0);
                preDomNodeInfo.setHeight(0);
                preDomNodeInfo.setDisplayed(false);
            } else {
                int width = Math.max(Integer.parseInt(singleElementInfo[2]), 0);
                int height = Math.max(Integer.parseInt(singleElementInfo[3]), 0);
                preDomNodeInfo.setX(Math.max(Integer.parseInt(singleElementInfo[0]), 0));
                preDomNodeInfo.setY(Math.max(Integer.parseInt(singleElementInfo[1]), 0));
                preDomNodeInfo.setWidth(width);
                preDomNodeInfo.setHeight(height);
                preDomNodeInfo.setDisplayed(Integer.parseInt(singleElementInfo[4]) == 1);
                index++;
            }
            try {
                if (preDomNodeInfo.getTagName().equals("svg")) {
                    Point point = driver.findElement(By.xpath(preDomNodeInfo.getXpath())).getLocation();
                    preDomNodeInfo.setX(point.x);
                    preDomNodeInfo.setY(point.y);
                }
            } catch (WebDriverException webDriverException) {
                preDomNodeInfo.setX(0);
                preDomNodeInfo.setY(0);
            }
        }
    }

    private String getXpathString() {
        StringBuilder xpathString = new StringBuilder();
        for (PreDomNodeInfo preDomNodeInfo : preDomNodeInfoList) {
            String tempXpath = preDomNodeInfo.getXpath();
            tempXpath = tempXpath.replaceAll("'", "\\\\'");
            xpathString.append(tempXpath).append(",");
        }
        return xpathString.toString();
    }

    /**
     * Execute JavaScript script to get x, y, width, height。
     *
     * @param xpathString
     * @return
     */
    public String getXPathFromLocation(String xpathString) throws IOException {
        String scriptName = "/" + "isDisplayed.js";
        URL url = DomInformationCollection.class.getResource(scriptName);
        String rawFunction = Resources.toString(url, StandardCharsets.UTF_8);
        String script = String.format(
                "window.isDisplayed = function(element){return (%s).apply(null, arguments);}",
                rawFunction);
        ((JavascriptExecutor) driver).executeScript(script);
        String functionDefine = "window.processStr = function(str){\n" +
                "    str = str.substring(0,str.indexOf(\"px\"));\n" +
                "    return str;\n" +
                "}\n" +
                "window.getElementX = function(element){\n" +
                "　　　　        if(element.tagName == \"svg\"){\n" +
                "            var actualLeft = 0;\n" +
                "            var current = element.parentElement;\n" +
                "        }else{\n" +
                "            var actualLeft = element.offsetLeft;\n" +
                "            var current = element.offsetParent;\n" +
                "        }\n" +
                "　　　　while (current !== null){\n" +
                "　　　　　　actualLeft += current.offsetLeft;\n" +
                "            if(current.tagName == \"svg\"){\n" +
                "                current = current.parentElement;\n" +
                "            }else{\n" +
                "                current = current.offsetParent;\n" +
                "            }\n" +
                "　　　　}\n" +
                "       var computedStyle = getComputedStyle(element, null);\n" +
                "　　　　return actualLeft;" +
                "}\n" +
                "window.getElementY = function(element){\n" +
                "        if(element.tagName == \"svg\"){\n" +
                "            var actualLeft = 0;\n" +
                "            var current = element.parentElement;\n" +
                "        }else{\n" +
                "        　　　var actualLeft = element.offsetTop;\n" +
                "              var current = element.offsetParent;\n" +
                "        }　　\n" +
                "　　　　while (current !== null){\n" +
                "            if(current.tagName == \"svg\"){\n" +
                "              actualLeft += 0;\n" +
                "                current = current.parentElement;\n" +
                "            }else{\n" +
                "              actualLeft += current.offsetTop;\n" +
                "              current = current.offsetParent;\n" +
                "\n" +
                "            }\n" +
                "　　　　}\n" +
                "       var computedStyle = getComputedStyle(element, null);\n" +
                "　　　　return actualLeft;" +
                "}\n" +
                "window.getElementW = function(element){\n" +
                "    if(element.tagName == \"svg\"){\n" +
                "           var actualLeft = element.clientWidth;\n" +
                "           var computedStyle = getComputedStyle(element, null);\n" +
                "    }else{\n" +
                "     　　　　var actualLeft = element.offsetWidth;\n" +
                "           var computedStyle = getComputedStyle(element, null);\n" +
                "      }\n" +
                "   \n" +
                "　　　　return actualLeft;" +
                "}\n" +
                "window.getElementH = function(element){\n" +
                "       if(element.tagName == \"svg\"){\n" +
                "      　　  var actualLeft = element.clientHeight;\n" +
                "            var computedStyle = getComputedStyle(element, null);\n" +
                "       }else{\n" +
                "    　　　　var actualLeft = element.offsetHeight;\n" +
                "            var computedStyle = getComputedStyle(element, null);\n" +
                "        }\n" +
                "　　　　return actualLeft;" +
                "}\n" +
                "window.isDisplayedInViewport = function(elem) {\n" +
                "    try{" +
                "   if(window.isDisplayed(elem) == true){\n" +
                "        return 1;\n" +
                "    }else{\n" +
                "        return 0;\n" +
                "    }\n" +
                "}catch(e){" +
                "       return 0;" +
                "}" +
                "}\n" +
                "window.getInfo = function(obj){\n" +
                "    var str = \"\";\n" +
                "    if(obj != null && obj != undefined ){\n" +
                "      str += getElementX(obj);\n" +
                "    str += \",\";  \n" +
                "      str += getElementY(obj);    str += \",\";  \n" +
                "      str += getElementW(obj);    str += \",\";  \n" +
                "      str += getElementH(obj); str += \",\";  \n" +
                "      str += isDisplayedInViewport(obj);" +
                "    str += \";\";  \n" +
                "    }else{\n" +
                "    str += \"0,0,0,0,1;\"\n" +
                "    }" +
                "    return str;\n" +
                "}";
        String getXpathScript = "window.getPathTo = function() {" +
                "var input = \"" + xpathString + "\";\n" +
                "var inputlist = input.split(\",\");\n" +
                "var str = \"\";\n" +
                "for( i = 0; i< inputlist.length; i++){\n" +
                "    var obj = document.evaluate(inputlist[i], document).iterateNext();\n" +
                "    str += window.getInfo(obj);  \n" +
                "}" +
                "return str;"
                + "}";
        String getXpathFromPoint = "window.elemFromPoint = window.getPathTo();";
        ((JavascriptExecutor) driver).executeScript(functionDefine);
        ((JavascriptExecutor) driver).executeScript(getXpathScript);
        ((JavascriptExecutor) driver).executeScript(getXpathFromPoint);
        String xpath = (String) ((JavascriptExecutor) driver).executeScript("return window.elemFromPoint");
        return xpath;
    }

    /**
     * Depth-first traversal to obtain the xpath path.
     *
     * @param htmlString
     * @throws IOException
     */
    public void parseHtml(String htmlString) throws IOException {
        Document document = Jsoup.parse(htmlString);
        Elements elements = document.getElementsByTag("body");
        for (Element element : elements) {
            collectElement(element, "/html[1]/body[1]", -1);
            sharedindex++;
            getAllChildXpath(element, "/html[1]/body[1]", 0);
        }
    }

    public void getAllChildXpath(Element element, String nowPath, int parent) {
        if (element.tagName().equals("script"))
            return;
        if (element.tagName().toString().contains("<") || element.tagName().toString().contains(":"))
            return;
        Elements elements = element.children();
        Map<String, Integer> map = new HashMap<>();
        for (Element child : elements) {
            if (child.tag().toString().contains("<")) continue;
            if (child.tagName().toString().contains(":")) continue;
            if (map.containsKey(child.tagName())) {
                map.put(child.tagName(), map.get(child.tagName()) + 1);
            } else {
                map.put(child.tagName(), 1);
            }
            String childXpath = "";
            if (child.tagName().equals("svg")) {
                childXpath = nowPath + "/*[name()='svg'][" + map.get(child.tagName()) + "]";
            } else {
                childXpath = nowPath + "/" + child.tagName() + "[" + map.get(child.tagName()) + "]";
            }
            try {
                // exclude elements not found
                collectElement(child, childXpath, parent);
                sharedindex++;
                if (!child.tagName().equals("svg")) {
                    getAllChildXpath(child, childXpath, sharedindex - 1);
                }
            } catch (NoSuchElementException e) {
                System.out.println("[NoSuchElement]" + sharedindex);
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("[Exception]" + sharedindex);
                e.printStackTrace();
            }
        }
    }

    /**
     * Use Jsoup to parse attribute information.
     *
     * @param child
     * @param childXpath
     * @param parent
     * @throws IOException
     */
    public void collectElement(Element child, String childXpath, int parent) throws IOException {
        PreDomNodeInfo preDomNodeInfo = new PreDomNodeInfo();
        Map<String, String> attributes = new HashMap<>();
        for (Attribute obj : child.attributes().asList()) {
            String key = obj.getKey();
            String val = obj.getValue();
            attributes.put(key, val);
        }
        preDomNodeInfo.setAttributes(attributes);
        preDomNodeInfo.setElementId(sharedindex);
        preDomNodeInfo.setParent(parent);
        jsonProcess.addElementChild(parent, sharedindex, true);
        preDomNodeInfo.setXpath(childXpath);
        preDomNodeInfo.setTagName(child.tag().toString());
        preDomNodeInfo.setText(child.text());
        if (preDomNodeInfo.getTagName().equals("input")) {
            if (!child.attr("value").equals("") && child.text().equals("")) {
                preDomNodeInfo.setText(child.attr("value"));
            }
            if (preDomNodeInfo.getText().equals("")){
                try {
                    String text = driver.findElement(By.xpath(preDomNodeInfo.getXpath())).getAttribute("value");
                    preDomNodeInfo.setText(text);
                }catch (NoSuchElementException noSuchElementException){
                    
                }
            }
        }
        preDomNodeInfo.setLinkText(child.attr("href"));
        preDomNodeInfo.setName(child.attr("name"));
        preDomNodeInfo.setId(child.attr("id"));
        preDomNodeInfo.setX(0);
        preDomNodeInfo.setY(0);
        preDomNodeInfo.setHeight(0);
        preDomNodeInfo.setWidth(0);
        /** Since this part of the element is not used for the time being, it is time-consuming to obtain, so set it to false first */
        preDomNodeInfo.setSelected(false);
        preDomNodeInfo.setEnable(false);//
        preDomNodeInfo.setDisplayed(true);
        jsonProcess.addElement(preDomNodeInfo);
    }

}