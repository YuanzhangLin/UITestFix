package autorepair.patch;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import utils.UtilsProperties;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Experiment {
    public String algorithmName = "";
    public String scriptName ;
    public List<Record> records;

    public Experiment( String scriptName)  {
        try {
            this.algorithmName = UtilsProperties.getConfigProperties().getProperty("match");
        }catch (IOException ioException) {
            ioException.printStackTrace();
        }
        this.scriptName = scriptName;
        records = new ArrayList<>();
    }

    public static Experiment load(String savePath) throws FileNotFoundException {
        Gson gson = new Gson();
        return gson.fromJson(new BufferedReader(new FileReader(
                        savePath)),
                new TypeToken<Experiment>(){}.getType());
    }
}
