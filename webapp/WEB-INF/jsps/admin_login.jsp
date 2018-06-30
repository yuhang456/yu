<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>>">
<link rel="stylesheet" href="style/ordersystem.css">
<link rel="stylesheet" href="style/bootstrap.min.css">
<script src="style/jquery-3.3.1.min.js"></script>
<script src="style/ordersystem.js"></script>
<title>管理员登录</title>
</head>
<body background="style/admin/images/pic1.jpg">
	<div class=" form-group admin_login">
		<h2>点餐系统管理员登录</h2>
		<br />
		<br />
			<label for="exampleInputFile">用户名：</label> <input type="text"
				class="form-control" id="adminName" name="adminName" placeholder="请输入用户名"
				style="width: 260px" /><br/> <label for="exampleInputFile">密码：</label>
			<input type="password" id="adminPassword" class="form-control" name="adminPassword"
				placeholder="请输入密码" style="width: 260px" /><br /> 
			<div class="form-group">
				<label for="recipient-name" class="control-label">验证码:</label> <input
					type="text" class="form-control" id="checkcode"
					style="width: 100px" /><br> <img alt="验证码" id="imagecode"
					src="user/code"/> <a onclick="reCreateCode()">看不清楚</a><br>
			</div>
			<span id="login_error" style="color: #FF0000;font-size:15px"></span><br/><br/>
			<button class="btn btn-default" onclick="admin_login()">登陆</button> 
			<button class="btn btn-default">重置</button> 
	</div>
</body>
</html>