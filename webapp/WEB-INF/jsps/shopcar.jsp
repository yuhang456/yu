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
<title>购物车</title>
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
	<a class="titleStyle" href="shopcar">
<span class="glyphicon glyphicon-cutlery" aria-hidden="true"></span>
购物车
</a>
	</center>
	<table class="table table-striped" id="carIndex">
	<thead>
		<tr>
			<th>商品展示</th>
			<th>商品编号</th>
			<th>商品名称</th>
			<th>商品单价</th>
			<th>购买数量</th>
			<th>商品价格</th>
			<th>商品操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${goodscar}" var="goodscar">
			<tr>
				<td><img class="imgshow" src="${goodscar.goodsPicture}"/></td>
				<td><c:out value="${goodscar.goodsNum }"></c:out></td>
				<td><c:out value="${goodscar.goodsName }"></c:out></td>
				<td><c:out value="${goodscar.goodsPrice }"></c:out></td>
				<td><button onclick="reduceOneGoodsNum(${goodscar.goodsNum },${goodscar.goodsPrice },${goodscar.goodsBuyNum},this)">-</button><input type="text" value="${goodscar.goodsBuyNum }" style="width:30px" class="goodsBuyNum"/><button onclick="addOneGoodsNum(${goodscar.goodsNum },${goodscar.goodsPrice },${goodscar.goodsBuyNum},this)">+</button></td>
				<td><c:out value="${goodscar.goodsPrice*goodscar.goodsBuyNum}"></c:out></td>
				<td><a class="btn btn-danger" href="shopcar/deleteGoods?goodsNum=${goodscar.goodsNum }">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<div class="totalGoodsPricesStyle">
	<span >合计：</span><span style=color:red>￥</span><span id="totalGoodsPrice" style=color:red>${totalGoodsPrice}</span><br><br>
	<button onclick="commitOrder('${user.userTel }','${user.userAddress }')" class="btn btn-primary btn-xm" style=width:125px;height:40px;>提交订单</button>
	</div>
</body>
</html>