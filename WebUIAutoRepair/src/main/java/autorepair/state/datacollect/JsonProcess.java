package autorepair.state.datacollect;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.seleniumhq.jetty9.util.IO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JsonProcess {
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static List<PreDomNodeInfo> preDomNodeInfoList;
    static List<DomNodeInfo> domNodeInfoList;

    public JsonProcess() {
        preDomNodeInfoList = new ArrayList<>();
    }

    public JsonProcess(List<PreDomNodeInfo> preDomNodeInfoList) {
        this.preDomNodeInfoList = preDomNodeInfoList;
    }

    public JsonProcess(Collection<DomNodeInfo> domNodeInfoList) {
        this.domNodeInfoList = (List<DomNodeInfo>) domNodeInfoList;
    }

    public static void initWithRead(String jsonPath) throws IOException {
        File file = new File(jsonPath);
        if (!file.exists()) {
            file.createNewFile();
        }
        String content = FileUtils.readFileToString(file, "UTF-8");
        preDomNodeInfoList = gson.fromJson(content, new TypeToken<List<PreDomNodeInfo>>() {
        }.getType());
        if (preDomNodeInfoList == null) {
            preDomNodeInfoList = new ArrayList<>();
        }
    }

    public void setDomNodeInfoList(List<DomNodeInfo> inDomNodeInfoList) {
        domNodeInfoList = inDomNodeInfoList;

    }

    public void writePreDomNodeInfoJsonFile(String jsonPath) {
        try {
            FileUtils.writeStringToFile(new File(jsonPath), gson.toJson(preDomNodeInfoList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDomNodeInfoJsonFile(String jsonPath) {
        try {
            FileUtils.writeStringToFile(new File(jsonPath), gson.toJson(domNodeInfoList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addElement(PreDomNodeInfo preDomNodeInfo) {
        preDomNodeInfoList.add(preDomNodeInfo);
    }

    public void addElementChild(int parent, int child, boolean isDisplayed) {
        if (parent < 0) {
            return;
        }
        try {
            preDomNodeInfoList.get(parent).getChildren().add(child);
            if (isDisplayed) {
                preDomNodeInfoList.get(parent).setValidChildNumber(preDomNodeInfoList.get(parent).getValidChildNumber() + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<PreDomNodeInfo> readPreDomNodeInfoJson(String jsonPath) throws IOException {
        File file = new File(jsonPath);
        if (!file.exists()) {
            file.createNewFile();
        }
        String content = FileUtils.readFileToString(file, "UTF-8");
        return gson.fromJson(content, new TypeToken<List<PreDomNodeInfo>>() {
        }.getType());
    }

    public static List<DomNodeInfo> readDomInfoJson(String jsonPath) throws IOException {
        File file = new File(jsonPath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            }catch (IOException ioException){
                System.out.println(jsonPath);
                ioException.printStackTrace();
            }
        }
        String content = FileUtils.readFileToString(file, "UTF-8");
        return gson.fromJson(content, new TypeToken<List<DomNodeInfo>>() {
        }.getType());
    }

    public List<PreDomNodeInfo> getPreDomNodeInfoList() {
        return preDomNodeInfoList;
    }

    public void setPreDomNodeInfoList(List<PreDomNodeInfo> inPreDomNodeInfoList) {
        preDomNodeInfoList = inPreDomNodeInfoList;
    }

}