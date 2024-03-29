package testcases;

import config.UrlConfig;
import utils.UtilsProperties;

import java.io.IOException;

public class Constants {
    public static final String Mantisbt_ADMIN_USER_NAME = "administrator";
    public static final String Mantisbt_ADMIN_PASSWORD = "root";
    public static final String Claroline_ADMIN_USER_NAME = "admin";
    public static final String Claroline_ADMIN_PASSWORD = "123456";
    public static final String Collabtive_ADMIN_USER_NAME = "admin";
    public static final String Collabtive_ADMIN_PASSWORD = "123456";

    public static String getCollabtiveURL() {
        try {
            if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
                return UrlConfig.COLLABTIVE_NEW;
            } else {
                return UrlConfig.COLLABTIVE_OLD;
            }
        }catch (IOException ioException){
            return UrlConfig.COLLABTIVE_OLD;
        }
    }

    public static String getClarolineURL() {
        try {
            if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
                return UrlConfig.CLAROLINE_NEW;
            }
        }catch (IOException ioException){

        }
        return UrlConfig.CLAROLINE_OLD;
    }

    public static String getClarolineDBName() throws IOException {
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return "claroline1107";
        } else {
            return "claroline1115";
        }
    }

    public static String getAddressbookDBName() throws IOException {
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
                return "addressbook40";
        } else {
            return "addressbook61";
        }
    }


    public static String getMantisbtDBName() throws IOException {
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return "bugtracker120";
        } else {
            return "bugtracker118";
        }
    }


    public static String getCollabtiveDBName() throws IOException {
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return "collabtive10";
        } else {
            return "collabtive075";
        }
    }


    public static String getMRBSDBName() throws IOException {
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return "mrbs149";
        } else {
            return "mrbs1261";
        }
    }

    public static String getAddressBookUrl()  {
        try {
            if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
                return UrlConfig.ADDRESS_BOOK_NEW;
            } else {
                return UrlConfig.ADDRESS_BOOK_OLD;
            }
        }catch (IOException ioException){
        }
        return UrlConfig.ADDRESS_BOOK_OLD;
    }

    public static String getMantisUrl()   {
        try {
            if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
                return UrlConfig.MANTISBT_NEW;
            } else {
                return UrlConfig.MANTISBT_OLD;
            }
        }catch (IOException ioException){

        }
        return UrlConfig.MANTISBT_OLD;
    }

    public static String getMRBSUrl() throws IOException {
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return UrlConfig.MRBS_NEW;
        } else {
            return UrlConfig.MRBS_OLD;
        }
    }


    public static String getW3SchoolsUrl() throws IOException {
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version"));
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version")
                .trim().equals("new"));
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return UrlConfig.W3Schools_NEW;
        } else {
            return UrlConfig.W3Schools_OLD;
        }
    }
    public static String getHomedepot() throws IOException {
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version"));
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version")
                .trim().equals("new"));
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return UrlConfig.Homedepot_NEW;
        } else {
            return UrlConfig.Homedepot_OLD;
        }
    }
    public static String getDoubanMovie() throws IOException {
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version"));
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version")
                .trim().equals("new"));
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return UrlConfig.DoubanMovie_NEW;
        } else {
            return UrlConfig.DoubanMovie_OLD;
        }
    }
    public static String getDoubanMusic() throws IOException {
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version"));
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version")
                .trim().equals("new"));
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return UrlConfig.DoubanMusic_NEW;
        } else {
            return UrlConfig.DoubanMusic_OLD;
        }
    }
    public static String getApple() throws IOException {
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version"));
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version")
                .trim().equals("new"));
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return UrlConfig.Apple_NEW;
        } else {
            return UrlConfig.Apple_OLD;
        }
    }
    public static String getBeijing() throws IOException {
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version"));
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version")
                .trim().equals("new"));
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return UrlConfig.Beijing_NEW;
        } else {
            return UrlConfig.Beijing_OLD;
        }
    }
    public static String getXiaomi() throws IOException {
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version"));
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version")
                .trim().equals("new"));
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return UrlConfig.Xiaomi_NEW;
        } else {
            return UrlConfig.Xiaomi_OLD;
        }
    }
    public static String getHuawei() throws IOException {
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version"));
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version")
                .trim().equals("new"));
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return UrlConfig.Huawei_NEW;
        } else {
            return UrlConfig.Huawei_OLD;
        }
    }
    public static String getBook() throws IOException {
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version"));
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version")
                .trim().equals("new"));
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return UrlConfig.Book_NEW;
        } else {
            return UrlConfig.Book_OLD;
        }
    }
    public static String getYandex() throws IOException {
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version"));
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version")
                .trim().equals("new"));
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return UrlConfig.Yandex_NEW;
        } else {
            return UrlConfig.Yandex_OLD;
        }
    }
    public static String getXfinity() throws IOException {
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version"));
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version")
                .trim().equals("new"));
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return UrlConfig.Xfinity_NEW;
        } else {
            return UrlConfig.Xfinity_OLD;
        }
    }
    public static String getUsps() throws IOException {
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version"));
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version")
                .trim().equals("new"));
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return UrlConfig.Usps_NEW;
        } else {
            return UrlConfig.Usps_OLD;
        }
    }
    public static String getLinkedin() throws IOException {
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version"));
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version")
                .trim().equals("new"));
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return UrlConfig.Linkedin_NEW;
        } else {
            return UrlConfig.Linkedin_OLD;
        }
    }
    public static String getGithub14_16() throws IOException {
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version"));
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version")
                .trim().equals("new"));
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return UrlConfig.Github2014;
        } else {
            return UrlConfig.Github2016;
        }
    }
    public static String getGithub16_20() throws IOException {
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version"));
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version")
                .trim().equals("new"));
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return UrlConfig.Github2016;
        } else {
            return UrlConfig.Github2020;
        }
    }
    public static String getGithub20_now() throws IOException {
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version"));
        System.out.println(UtilsProperties.getConfigProperties().getProperty("version")
                .trim().equals("new"));
        if (UtilsProperties.getConfigProperties().getProperty("version").trim().equals("new")) {
            return UrlConfig.Github2020;
        } else {
            return UrlConfig.Github_now;
        }
    }
}
