package testcases.mrbs.model_based_dataset.sql;
import org.testng.annotations.*;
import testcases.Constants;

import java.io.IOException;

public class MRBSConstants {

	// public static String ADMIN_PASSWORD = "a8ee26ca6c7c";
	public static String ADMIN_PASSWORD = "secret";
	public static String ADMIN_USER_NAME = "administrator";
	public static String BASE_URL;

	static {
		try {
			BASE_URL = Constants.getMRBSUrl();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
