package autorepair.state.script;

import autorepair.state.edge.Event;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScriptSequenceImpl {
    private List<Event> edges;

    public List<Event> getEdges() {
        return edges;
    }

    public void setEdges(List<Event> edges) {
        this.edges = edges;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    private String className;

    public ScriptSequenceImpl(String className) {
        this.className = className;
        this.edges = new ArrayList<>();
    }

    public ScriptSequenceImpl load(String savePath) throws FileNotFoundException {
        Gson gson = new Gson();
        return gson.fromJson(new BufferedReader(new FileReader(savePath + className + ".json")),
                ScriptSequenceImpl.class);
    }

    public void save(String savePath) {
        File file = new File(savePath);
        if (!file.exists()) {
            boolean result = file.mkdirs();
        }
        try (Writer writer = new FileWriter(savePath + className + ".json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(this, writer);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void addEvent(Event event) {
        edges.add(event);
    }

    public Event getEvent(String codeLine, String method) {
        for (Event event : edges) {
            if ( event.getCodeLine().equals(codeLine) && event.getMethod().equals(method) ) {
                System.out.println(event);
                return event;
            }
        }
        return null;
    }

//    public Event getEvent(String codeLine, String method) {
//        for (Event event : edges) {
//            if ( event.getCodeLine().equals(codeLine)  ) {
//                System.out.println(event);
//                return event;
//            }
//        }
//        return null;
//    }
}
