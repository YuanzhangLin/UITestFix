package autorepair.patch;

import autorepair.state.datacollect.DomNodeInfo;
import autorepair.state.edge.Identification;
import autorepair.state.script.ScriptSequenceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.aspectj.lang.Signature;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Patch class, as a result of the last fix. A patch is only generated once when it is fixed.
 */
public class Patch {
    private String codeLine;

    public String getCodeLine() {
        return codeLine;
    }

    public void setCodeLine(String codeLine) {
        this.codeLine = codeLine;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    private Identification identification;

    public Patch(String codeLine, Identification identification, DomNodeInfo domNodeInfo, Signature signature) {
        this.codeLine = codeLine;
        this.identification = identification;
        this.signature = signature.toString();
        this.domNodeInfo  = domNodeInfo;
    }

    public DomNodeInfo getDomNodeInfo() {
        return domNodeInfo;
    }

    public void setDomNodeInfo(DomNodeInfo domNodeInfo) {
        this.domNodeInfo = domNodeInfo;
    }

    private DomNodeInfo domNodeInfo ; // Used to check whether the result is correct

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    private String signature; // Combine codeLine to uniquely identify an event.


    public static List<Patch> load(String savePath) throws FileNotFoundException {
        Gson gson = new Gson();
        return gson.fromJson(new BufferedReader(new FileReader(
                savePath)),
                new TypeToken<ArrayList<Patch>>(){}.getType());
    }


}
