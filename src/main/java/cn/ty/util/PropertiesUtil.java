package cn.ty.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {

	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	
	private Properties props;
	private String fileName;
	public Properties getProps() {
		return props;
	}
	public void setProps(Properties props) {
		this.props = props;
	}
	
	public PropertiesUtil(String fileName) {
		this.fileName = fileName;
		readProperties(fileName);
	}
	
	/**
	 * 读取properties文件
	 */
	public void readProperties(String fileName){
		try {
			props = new Properties();
			FileInputStream fileInputStream = new FileInputStream(fileName);
			props.load(fileInputStream);
		}catch(Exception e) {
			// TODO Auto-generated catch block
			logger.error("读取properties文件异常");
		}
	}
	
	/**
	 * 获取某个属性
	 */
	public String getProperty(String key){
		return props.getProperty(key);
	}
	
	/**
	 * 写入properties文件
	 */
	public void writeProperties(String key ,String value){
		try {
			OutputStream outputStream = new FileOutputStream(fileName);
			props.setProperty(key, value);
			props.store(outputStream, "『comments』Update key：" + key);
		} catch (Exception e) {
			logger.error("写入properties文件异常");
		}
	}
}
