package frame.config;

import java.io.File;

public class Settings {

    public static String rootPath = System.getProperty("user.dir") + File.separator;
    public static String chromeDriverPath = rootPath + "driver" + File.separator + "chromedriver_108.exe";
    public static final int UN_MATCH = 0;
    public static final int CONTAINER = 1; // a element represent a region.
    public static final int ICON = 2;
    public static final int TEXT = 3;
    public static String sftmPath = rootPath + "src" + File.separator + "main" + File.separator + "java" + File.separator + "frame" +
            File.separator + "algorithm" + File.separator + "sftm2023" + File.separator;
}
