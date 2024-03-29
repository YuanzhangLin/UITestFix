package config;

import utils.UtilsProperties;

import java.io.File;
import java.util.Properties;

public class Settings {
    public final static String PROJECT_PATH = System.getProperty("user.dir") + File.separator;
    public static String sep = File.separator;

    public final static String OUTPUT_PATH = PROJECT_PATH + "output" + File.separator;

    public final static String SUSTECH_PROPERTIES_PATH = PROJECT_PATH + "src" + File.separator + "main" + File.separator
            + "java" + File.separator + "autorepair" + File.separator + "match" + File.separator + "sustech"
            + File.separator + "sustech.properties";
    public final static String OPENCV_DLL_PATH = PROJECT_PATH + "dll" + File.separator;

    public final static String PATCH_PATH = PROJECT_PATH + "patch" + File.separator;
    public static String sftmPath = PROJECT_PATH + "src" + File.separator + "main" + File.separator + "java" + File.separator + "autorepair" +
            File.separator + "match" + File.separator + "sftm2023" + File.separator;
    public final static String REGEX_FOR_GETTING_INDEX = "\\[(.+)\\]";

    public static boolean aspectLock = true;

    public static String preDomNodeJsonName = "preDomNodeInfo.json";
    public static String domNodeJsonName = "domNodeInfo.json";


    public static boolean isRepair;

    public static String patchSavePath = "D:\\rep\\WebUIAutoRepair\\output\\";
    public static String patchSaveName = "addressbook";
    public static String currentMethod;

}
