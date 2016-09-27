<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  

%>  
<!--  <meta http-equiv="Refresh" content="1;url=login/hello">-->
<html>
Welcome
<form action="<%=path%>/login/tangyi" method="get">
<button type="submit">提交</button>
</form>
</html>