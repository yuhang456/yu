<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>欢迎来到点餐系统</title>

<script src="http://apps.bdimg.com/libs/jquery/1.11.1/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- 
 <script src="style/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="style/bootstrap.min.js"></script>
<link rel="stylesheet" href="style/bootstrap.min.css">
<script src="style/jquery.dataTables.min.js"></script>
-->
<script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>
<script src="style/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" href="style/dataTables.bootstrap.min.css">
<script src="style/ordersystem.js"></script>
<link rel="stylesheet" href="style/ordersystem.css">
</head>
<body>
<%@include file="user.jsp"%>
<br><br><br><br>
<center>
	<a class="titleStyle" href="index">
<span class="glyphicon glyphicon-cutlery" aria-hidden="true"></span>
点餐系统
</a>
	</center>
	
	<table class="table table-bordered table-striped table-hover" id="goodsIndex">
	<thead>
		<tr>
			<th>商品展示</th>
			<th>商品编号</th>
			<th>商品名称</th>
			<th>商品单价</th>
			<th>商品余量</th>
			<th>购买商品</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${goods}" var="goods">
			<tr>
				<td><img class="imgshow" src="${goods.goodsPicture}" /></td>
				<td><c:out value="${goods.goodsNum }"></c:out></td>
				<td><c:out value="${goods.goodsName }"></c:out></td>
				<td><c:out value="${goods.goodsPrice }"></c:out></td>
				<td><c:out value="${goods.goodsSurplus }"></c:out></td>
				<c:if test="${user==null }">
				<td><button class="btn btn-primary btn-sm"
				onclick="addgoodscar(${goods.goodsNum },null)">加入购物车</button></td>
				</c:if>
				<c:if test="${user!=null }">
				<td><button class="btn btn-primary btn-sm"
				onclick="addgoodscar(${goods.goodsNum },'${user.userTel }')">加入购物车</button></td>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>