package frame.experiment;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import frame.datacollect.PreDomNodeInfo;
import org.apache.commons.io.FileUtils;
import org.opencv.core.Mat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchResult {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Map<Integer, Integer> matchMap;
    private List<Integer> deleteList;
    private List<Integer> addList;

    public MatchResult() {
        matchMap = new HashMap<>();
        deleteList = new ArrayList<>();
        addList = new ArrayList<>();
    }

    public MatchResult(String matchResultPath) throws IOException {
        this();
        readMatchResult(matchResultPath);
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "matchMap=" + matchMap +
                ", deleteList=" + deleteList +
                ", addList=" + addList +
                '}';
    }

    public void readMatchResult(String jsonPath) throws IOException {
        File file = new File(jsonPath);
        if (!file.exists()) {
            file.createNewFile();
        }
        String content = FileUtils.readFileToString(file, "UTF-8");
        MatchResult matchResult = gson.fromJson(content, new TypeToken<MatchResult>() {
        }.getType());
        this.addList.addAll(matchResult.addList);
        this.deleteList.addAll(matchResult.deleteList);
        this.matchMap.putAll(matchResult.matchMap);
    }

    public void writeToJsonFile(String jsonPath) {
        try {
            FileUtils.writeStringToFile(new File(jsonPath), gson.toJson(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Integer> getMatchMap() {
        return matchMap;
    }

    public void setMatchMap(Map<Integer, Integer> matchMap) {
        this.matchMap = matchMap;
    }

    public List<Integer> getDeleteList() {
        return deleteList;
    }

    public void setDeleteList(List<Integer> deleteList) {
        this.deleteList = deleteList;
    }

    public List<Integer> getAddList() {
        return addList;
    }

    public void setAddList(List<Integer> addList) {
        this.addList = addList;
    }

}
