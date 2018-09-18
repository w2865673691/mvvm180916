package some.wp.com.mvvm.common;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;

@SuppressLint("SimpleDateFormat")
public class Constants {
	// http://wanghaijun.jiujs.com:8080/jiujs/api/wadl/user.wadl 用户相关的

	public static final SimpleDateFormat sdfYMDHMS = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat sdfYMD = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static final SimpleDateFormat sdfYMD1 = new SimpleDateFormat(
			"yyyyMMdd");

	public static interface Net {
		public static final String USER_AGENT = "";
		public static final int SERVER_PORT = 5703;
		public static final String SERVER_HOST = "192.168.3.116";//

		// 正式
		// public static final String FILE_NAME = "yxreal.pem";
		// public static final String HOST = "www.yunxiyx.com";
		// public static final String HTTP_BASE_URL = "https://" + HOST
		// + ":8443/SYS_S";
		// public static final String HTTPS_BASE_URL = "https://" + HOST
		// + ":8443/SYS_S";

		// 测试0
		public static final String FILE_NAME = "yxreal.pem";
		public static final String HOST = "www.jiabangbang365.com";
		public static final String HTTP_BASE_URL = "http://" + HOST
				+ ":80/family";
		public static final String HTTPS_BASE_URL = HTTP_BASE_URL;

		// 测试1
//		 public static final String FILE_NAME = "yxreal.pem";
//		 public static final String HOST = "192.168.3.25";
//		 public static final String HTTP_BASE_URL = "http://" + HOST
//		 + ":8090/family";
//		 public static final String HTTPS_BASE_URL = HTTP_BASE_URL;

		// log
		public static boolean LOG_ALL = true;
		public static boolean LOG_V = true;
		public static boolean LOG_D = true;
		public static boolean LOG_I = true;
		public static boolean LOG_W = true;
		public static boolean LOG_E = true;

	}

	public static interface Share {
		public static final String WX_APPID = "wx8bad11cd46817f1a";//
		public static final String WX_APPSECRET = "ad94a75ec0e09d9056a778c5516b50eb";

		public static final String QQ_APPID = "1106948694";// ID：1106948694
															// Key：eTWDN6JYKsCTJdYK

		public static final String BAIDU_APIKEY = "6Q9ReXnwUuvyfHGSaBVYY8jo";//
		public static final String BAIDU_SECRETKEY = "fMDq0t0u6xoCEcuvzQ9NrrnsUzQ45W7H";// apiKey和secretKey
	}

	public static interface DataBase {
		public static final String DATABASE_PROJECT = "data1.db";
		public static final String DATABASE_ACCOUNT = "data2.db";
		public static final String DATABASE_ADDRESS = "addr.db";
		public static final int VERSION_NORMAL = 1;
		public static final int VERSION_ACCOUNT = 2;
	}

	public static interface Controls {
		public static final String INTENT_PAGE = "INTENT_PAGE";
		public static final String INTENT_PAGE_DATA = "INTENT_PAGE_DATA";
		// public static final String INTENT_PAGE_DATA1 = "INTENT_PAGE_DATA1";
		public static final String INTENT_DATA = "INTENT_DATA";
		public static final String INTENT_DATA1 = "INTENT_DATA1";
		public static final String INTENT_DATA2 = "INTENT_DATA2";
		public static final String INTENT_DATA3 = "INTENT_DATA3";
		public static final String INTENT_DATA4 = "INTENT_DATA4";
		public static final String INTENT_DATA5 = "INTENT_DATA5";
		public static final String INTENT_DATA6 = "INTENT_DATA6";
		public static final String INTENT_USERNAME = "INTENT_USERNAME";
		public static final String INTENT_PHONE = "INTENT_PHONE";

		public static final String INTENT_ACTION_LANDACTION = "intent_action_landaction";

		public static final int REQUEST_CODE = 1;
		public static final int REQUEST_CODE1 = 2;
		public static final int REQUEST_CODE2 = 3;
		public static final int REQUEST_CODE3 = 4;
		public static final int REQUEST_CODE4 = 5;

		public static final int REQUEST_PIC0 = 6;
		public static final int REQUEST_PIC1 = 7;
		public static final int REQUEST_PIC2 = 8;

		public static final int REQUEST_WEB = 21;
		public static final int REQUEST_FINISH = 22;

		public static final int RESULT_CODE = 10;
		public static final int RESULT_CODE1 = 11;
		public static final int RESULT_WEB = 12;
		public static final int RESULT_FINISH = 13;

		public static final String RESULT_DATA = "RESULT_DATA";
	}


	// --------------------------------------
	public static interface URLS {

		/**
		 * 平台服务协议：http://192.168.3.25:8090/family/familyH5/Pages/app/manageRules.html 
		 */
		public static final String URL_manageRules = Net.HTTPS_BASE_URL
				+ "/familyH5/Pages/app/manageRules.html?type=0";

	}

}
