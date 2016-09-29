package cn.ty.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageUtil {

	private static Logger logger = LoggerFactory.getLogger(MessageUtil.class);
	
    /** 
     * 解析微信发来的请求（XML） 
     *  
     * @param request 
     * @return 
     * @throws Exception 
     */  
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseXML(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		InputStream inputStream = null;
		try {
			// 从request中取得输入流  
			inputStream = request.getInputStream();
			// 读取输入流  
			SAXReader reader = new SAXReader();
			Document document =  reader.read(inputStream);
			// 得到xml根元素  
			Element root = document.getRootElement();
			// 得到根元素的所有子节点
			List<Element> elementList = root.elements();
			for(Element e : elementList){
				map.put(e.getName(), e.getText());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("解析XML文件出错异常："+e);
			
		}finally {
			try {
				inputStream.close();
				inputStream = null;
			} catch (IOException e) {
				logger.error("输入流关闭异常："+e);
			}
		}
		return map;
	}
	
}
