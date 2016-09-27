package cn.ty.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ty.util.SignUtil;

public class CoreServlet extends HttpServlet{
	
	public static final Logger logger = LoggerFactory.getLogger(CoreServlet.class);
	
	 /**
	 * 确认请求来自微信服务器
	 * @throws IOException 
	 */
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try{
			// 微信加密签名
			String signature = request.getParameter("signature");
			//时间戳
			String timestamp = request.getParameter("timestamp");
			// 随机数	
			String nonce  = request.getParameter("nonce");
			// 随机字符串
			String echostr = request.getParameter("echostr");
			PrintWriter out = response.getWriter();
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			if(SignUtil.checkSignature(signature, timestamp, nonce)){
				out.print(echostr);
				logger.error("请求微信Token验证成功echostr = {}",echostr);
			}
			else {
				logger.error("请求微信Token验证失败");
			}
			out.close();
			out = null;
//			response.getWriter().append("Served at: ").append(request.getContextPath());
		}catch(Exception e){
			logger.error("请求微信验证方法异常",e);
		}
	}
}
