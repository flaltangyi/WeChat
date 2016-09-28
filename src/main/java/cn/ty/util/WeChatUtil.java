package cn.ty.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ty.model.AccessToken;

public class WeChatUtil {

	public static Logger logger = LoggerFactory.getLogger(WeChatUtil.class);
	
	public static AccessToken getAccessToken(String appId,String appSecret){
		AccessToken accessToken = null;
		String url = Constants.ACCESSTOKEN_URL.replace("AppID", appId).replace("AppSecret", appSecret);
		String json = httpRequest(url, "GET", null);
		JSONObject jsonObject = new JSONObject(json);
		if(jsonObject != null){
			try {
				accessToken = new AccessToken();
				accessToken.setAcessToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (Exception e) {
				accessToken = null;
				// TODO Auto-generated catch block
				logger.error("accessToken获取异常,得到的json = {}",json,e);
			}
		}
		return accessToken;
	}
	
	public static String httpRequest(String requestUrl,String requestMethod,String outpuStr){
		StringBuffer buffer = new StringBuffer();
		
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new X509TrustManager()};
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
			sslContext.init(null, tm, new SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象;
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
			httpsURLConnection.setSSLSocketFactory(ssf);
			httpsURLConnection.setDoOutput(true);
			httpsURLConnection.setDoInput(true);
			httpsURLConnection.setUseCaches(true);
			//设置请求方式
			httpsURLConnection.setRequestMethod(requestMethod);
			if("GET".equalsIgnoreCase(requestMethod))
				httpsURLConnection.connect();
			//当有数据需要提交时
			if(outpuStr != null){
				OutputStream outputStream = httpsURLConnection.getOutputStream();
				outputStream.write(outpuStr.getBytes("UTF-8"));
				outputStream.close();
			}
			//将返回的输入流转换成字符串
			InputStream inputStream = httpsURLConnection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader =new BufferedReader(inputStreamReader);
			
			String str  = bufferedReader.readLine();
			
			while (str != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			httpsURLConnection.disconnect();
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			logger.error("算法异常",e);
		} catch (NoSuchProviderException e) {
			logger.error("提供者异常",e);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			logger.error("密钥管理异常",e);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			logger.error("URL异常",e);
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			logger.error("协议异常",e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("打开连接时发生 I/O异常",e);
		}
		return null;
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		WeChatUtil weChatUtil = new WeChatUtil();
		try {
			PropertiesUtil propertiesUtil = new PropertiesUtil(Constants.CONF_PROPERTIES_PATH);
			Long overdueTime= Long.parseLong(propertiesUtil.getProperty("OVERDUE_ACCESS_TOKEN_TIME"));
			Long nowTime = System.currentTimeMillis();
			AccessToken accessToken = new AccessToken();
			if(nowTime >= overdueTime){
				//超时从新获取accessToken
				accessToken = weChatUtil.getAccessToken(Constants.AppID, Constants.AppSecret);
				propertiesUtil.writeProperties("OVERDUE_ACCESS_TOKEN_TIME", String.valueOf(nowTime+Constants.EFFECTIVE_TIME));
				propertiesUtil.writeProperties("ACCESS_TOKEN", accessToken.getAcessToken());
				logger.info("accessToken超时，重新获取 ACCESS_TOKEN={}",accessToken.getAcessToken());
			}else{
				logger.info("accessToken未超时");
			}
		} catch (Exception e) {
			logger.error("accessToken写入/出异常",e);
		}
		
	}
}
