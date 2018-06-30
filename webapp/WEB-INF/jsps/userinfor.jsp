<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
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
<title>个人信息</title>
<script src="style/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="style/bootstrap.min.js"></script>
<link rel="stylesheet" href="style/bootstrap.min.css">
<script src="style/ordersystem.js"></script>
<link rel="stylesheet" href="style/ordersystem.css">
</head>
<body>
<div class="fixStyle">
<%@include  file="user.jsp"%>
 <div id="nav"  style=position:absolute;>
      <ul>
        <li><a href="user/userinfor/#userinforAndUpdata">个人信息</a></li>
        <li><a href="user/userinfor/#updataPassword">修改密码</a></li>
        <li><a href="user/userinfor/#myVIP">我的会员</a></li>
        <li><a href="user/userinfor/#myPayable">我的余额</a></li>
        <li><a href="user/userinfor/#myOrder">我的订单</a></li>
      </ul>
    </div>
<div class="overStyle"></div>
    </div>
<div id="userinforAndUpdata">
<div class="fixTop">
<p>个人信息</p>
<hr>
<div id="information">
<table class="table table-striped">
<tr>
<td>姓名：</td>
<td>${user.userName }</td>
</tr>
<tr>
<td>性别：</td>
<c:if test="${user.userSex ==0}">
<td>女</td>
</c:if>
<c:if test="${user.userSex ==1}">
<td>男</td>
</c:if>
</tr>
<tr>
<td>联系电话：</td>
<td>${user.userTel }</td>
</tr>
<tr>
<td>我的地址：</td>
<td>${user.userAddress }</td>
</tr>
</table>
</div>
<div id="informationButton">
<button onclick="informationUpdata('${user.userName }','${user.userTel }','${user.userAddress }')" class="btn btn-primary btn-sm">修改</button>
</div>
</div>
</div>
<div id="updataPassword">
<div class="fixTop">
<p>修改密码</p>
<hr>
<div class="updataPassword">
<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#updataPasswordd" aria-expanded="false" aria-controls="updataPasswordd">
  修改登录密码
</button>
<div class="updataPayPassword">
<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#updataPayPasswordd" aria-expanded="false" aria-controls="updataPayPasswordd">
  修改支付密码
</button>
<div class="collapse" id="updataPayPasswordd"><br>
验证支付密码：<input type="password" class="form-control" id="checkPayPassword" style=width:200px />
新密码：<input type="password" class="form-control" id="newPayPassword" style=width:200px />
确认新密码:<input type="password" class="form-control" id="againNewPayPassword" style=width:200px /><br>
<button onclick="updataPayPassword('${user.userPayPassword}','${user.userTel }')" class="btn btn-primary btn-sm">修改</button>
</div>
</div>
<div class="collapse" id="updataPasswordd"><br>
验证登录密码：<input type="password" class="form-control" id="checkPassword" style=width:200px />
新密码：<input type="password" class="form-control" id="newPassword" style=width:200px />
确认新密码:<input type="password" class="form-control" id="againNewPassword" style=width:200px /><br>
<button onclick="updataPassword('${user.userPassword}','${user.userTel }')" class="btn btn-primary btn-sm">修改</button>
</div>
</div>

</div>
</div>
<div id="myVIP">
<div class="fixTop">
<p>我的会员</p>
<hr>
<div id="information">
<table class="table table-striped">
<tr>
<td>姓名：</td>
<td>${user.userName }</td>
</tr>
<tr>
<td>联系电话：</td>
<td>${user.userTel }</td>
</tr>
<tr>
<td>权限：</td>
<c:if test="${user.userPower==0 }">
<td>非会员</td>
</c:if>
<c:if test="${user.userPower==1 }">
<td>会员</td>
</c:if>
</tr>
</table>
</div>
</div>
</div>

<div id="myPayable">
<div class="fixTop">
<p>我的余额</p>
<hr>
<div id="information">
<table class="table table-striped">
<tr>
<td>姓名：</td>
<td>${user.userName }</td>
</tr>
<tr>
<td>联系电话：</td>
<td>${user.userTel }</td>
</tr>
<tr>
<td>钱包余额：</td>
<td>￥${user.userPayable }</td>
</tr>
</table>
</div>
</div>
</div>

<div id="myOrder">
<div class="fixTop">
<p>我的订单</p>
<hr>
<div id="information">
<table class="table table-striped  table-bordered" style=text-align:center>
<thead>
<tr>
<td>订单编号</td>
<td>用户电话</td>
<td>收货地址</td>
<td>订单商品</td>
<td>订单总价</td>
<td>订单状态</td>
<td>订单详情</td>
<td>订单操作</td>
</tr>
</thead>
<tbody>
<c:forEach items="${order}" var="order">
<tr>
<td>${order.orderNum}</td>
<td>${order.userTel}</td>
<td>${order.userAddress}</td>
<td>${order.goodsCarAllGoods}</td>
<td>${order.totalGoodsPrice}</td>
<c:if test="${order.orderStatus==0 }">
			<td>未支付</td>
			</c:if>
			<c:if test="${order.orderStatus==1 }">
			<td>已支付</td>
			</c:if>
			<c:if test="${order.orderStatus==2 }">
			<td>已取消</td>
			</c:if>

<td><a href="user/myOrderView?orderNum=${order.orderNum }">详情</a></td>
<td><a href="user/deleteMyOrder?orderNum=${order.orderNum}">删除</a></td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br>
</div>
</div>
</body>
</html>