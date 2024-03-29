package autorepair.patch;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Patches {
    public List<Patch> getPatches() {
        return patches;
    }

    public void setPatches(List<Patch> patches) {
        this.patches = patches;
    }

    private List<Patch> patches ;
    public Patches(){
        patches = new ArrayList<>();
    }


    public static Patches load(String savePath) throws FileNotFoundException {
        Gson gson = new Gson();
        return gson.fromJson(new BufferedReader(new FileReader(
                        savePath)),
                new TypeToken<Patches>(){}.getType());
    }

}
