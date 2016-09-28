package cn.ty.util;

/**
 * 常量
 * @author tangyi
 */
public class Constants {

	/**
	 * AppID(应用ID)
	 */
	public static String AppID = "wxfbbe2fc33fe3a37a";
	
	/**
	 * AppSecret(应用密钥)
	 */
	public static String AppSecret = "527c5cc178fda738f6231b1a3807e196";
	
	/**
	 * access_token接口
	 */
	public static String ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	/**
	 * conf.properties文件路径
	 */
	public static String CONF_PROPERTIES_PATH = "conf.properties";
	
	/**
	 * ACCESS_TOKEN有效时间(单位：ms)
	 */
	public static int EFFECTIVE_TIME = 7200000;
	
	/**
	 * 微信接口Token验证
	 */
	public static String TOKEN = "wojiaotangyi";
}
