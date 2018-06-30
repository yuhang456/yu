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
<title>订单信息</title>
<!-- 
<script src="http://apps.bdimg.com/libs/jquery/1.11.1/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
  -->
<script src="style/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="style/bootstrap.min.js"></script>
<link rel="stylesheet" href="style/bootstrap.min.css">
<script src="style/ordersystem.js"></script>
<link rel="stylesheet" href="style/ordersystem.css">
</head>
<body>
	<%@include file="user.jsp"%>
	<center><h3>订单信息</h3>
	<br>
	<table class="table table-striped table-bordered" style=width:700px;text-align:center;>
		<tr>
			<td>订单编号</td>
			<td>${order.orderNum }</td>
		</tr>
		<tr>
			<td>店铺位置</td>
			<td>${order.orderStore}</td>
		</tr>
		<tr>
			<td>用户姓名</td>
			<td>${user.userName }</td>
		</tr>
		<tr>
			<td>用户电话</td>
			<td>${user.userTel }</td>
		</tr>
		<tr>
			<td>用户权限</td>
			<c:if test="${user.userPower==0 }">
				<td>非会员</td>
			</c:if>
			<c:if test="${user.userPower==1 }">
				<td>会员</td>
			</c:if>
		</tr>
		<tr>
			<td>用户钱包</td>
			<td>￥${user.userPayable }</td>
		</tr>
		<tr>
			<td>购买商品</td>
			<td>${order.goodsCarAllGoods}</td>
		</tr>
		<tr>
			<td>商品总价</td>
			<td>￥${order.totalGoodsPrice}</td>
		</tr>
		<tr>
			<td>下单时间</td>
			<td>${order.downOrderTime }</td>
		</tr>
		<tr>
			<td>订单状态</td>
			<c:if test="${order.orderStatus==0 }">
			<td>未支付</td>
			</c:if>
			<c:if test="${order.orderStatus==1 }">
			<td>已支付</td>
			</c:if>
			<c:if test="${order.orderStatus==2 }">
			<td>已取消</td>
			</c:if>
		</tr>
		<tr>
			<td>收货地址</td>
			<td>${order.userAddress}</td>
		</tr>
	</table>
	<span style=color:#FF0000>注：请在一个小时内完成支付，过时订单自动取消</span><br><br>
	<a href="user/userinfor/#myOrder">查看我的订单</a><br><br>
	<c:if test="${order.orderStatus==0 }">
	<button class="btn btn-danger" onclick="cancelMyorder(${order.orderNum})">取消订单</button>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#payMoney" aria-expanded="false" aria-controls="payMoney">
 去支付
</button>
<div class="collapse" id="payMoney"><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a data-toggle="modal" data-target="#zfbPayMoney" class="btn btn-primary">微信支付</a><br><br>
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="zfbPayMoney">
  	<div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
     <div class="modal-header">
	<center><h4 class="modal-title">扫一扫支付宝二维码付款</h4></center>
	</div>
	<div class="modal-body">
	<img alt="支付宝付款码" src="imgs/pic1.jpg" style=width:270px;height:330px;>
	</div>
	</div>
	</div>
	</div>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a data-toggle="modal" data-target="#WeChatPayMoney" class="btn btn-primary">微信支付</a><br><br>
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="WeChatPayMoney">
  	<div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
     <div class="modal-header">
	<center><h4 class="modal-title">扫一扫微信二维码付款</h4></center>
	</div>
	<div class="modal-body">
	<img alt="微信付款码" src="imgs/pic2.jpg" style=width:270px;height:330px;>
	</div>
	</div>
	</div>
	</div>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a data-toggle="modal" data-target="#ablePayMoney" class="btn btn-primary">余额支付</a><br><br>
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="ablePayMoney">
  	<div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
     <div class="modal-header">
	<h4 class="modal-title">钱包余额付款</h4>
	</div>
	<div class="modal-body">
	<label for="recipient-name" class="control-label">请输入支付密码:</label> 
	<input type="password" class="form-control" id="getAblePayMoneyPassword" style="width: 250px">
	</div>
		<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="payMoney(${order.totalGoodsPrice},${user.userPayable },${order.orderNum},'${user.userTel}')">支付</button>
				</div>
	</div>
	</div>
	</div>
</div>
</c:if>
<c:if test="${order.orderStatus==1||order.orderStatus==2 }">
<button class="btn btn-danger" disabled="disabled">取消订单</button>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button class="btn btn-primary" disabled="disabled">去支付</button>
</c:if>
	</center>
</html>