package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UtilsProperties {

    public static Properties loadProperties(String savePath) throws IOException {
        Properties pros = new Properties();
        FileInputStream fis = new FileInputStream(savePath);
        pros.load(fis);
        return  pros;
    }

    public static Properties getConfigProperties() throws IOException {
        Properties properties = new Properties();
        InputStream is = UtilsProperties.class.getClassLoader().getResourceAsStream("config.properties");
        properties.load(is);
        return properties;
    }

}
