package config;

import utils.UtilsProperties;

import java.io.File;
import java.io.IOException;

public class DriverConfig {
    public static String CHROME_VERSION = "109";

    static {
        try {
            CHROME_VERSION = UtilsProperties.getConfigProperties().getProperty("chrome_version").trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String DRIVER_PATH = Settings.PROJECT_PATH
            + "driver" + File.separator + "chromedriver_" + CHROME_VERSION + ".exe";
}
