package cn.ty.util;

import org.apache.struts2.json.JSONReader;

import cn.ty.model.AccessToken;

public class WeChatUtil {

	public static AccessToken getAccessToken(String appId,String appSecret){
		JSONReader jsonReader = new JSONReader();
		String url = Constants.ACCESSTOKEN_URL.replace("AppID", appId).replace("AppSecret", appSecret);
		return null;
	}
	
	public static String httpRequest(String requestUrl,String requestMethod,String outpuStr){
		
		return "";
	}
}
