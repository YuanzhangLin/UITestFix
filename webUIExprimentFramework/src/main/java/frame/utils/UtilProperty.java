package frame.utils;

import java.io.*;
import java.util.Properties;

public class UtilProperty {
    public static Properties readProperties(String path) throws IOException {
        Properties properties = new Properties();
        InputStream in = new FileInputStream(path);
        properties.load(in);
        return properties;
    }
}
