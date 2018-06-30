<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <!-- 
<script src="http://apps.bdimg.com/libs/jquery/1.11.1/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
 -->

</head>
<body>
	<c:choose>
	<c:when test="${user == null }">
	<div class="userTop">
		<a href="choose_store" class="location"><c:out value="${location }"/></a>
		<a data-toggle="modal" data-target="#loginPop" class="userLogin">亲，请登录</a>
		<a class="userRegister" data-toggle="modal" data-target="#registerPop">注册账号</a>
		<a class="userAgreement" data-toggle="modal" data-target="#registerAgreement">点餐系统协议</a>
	</div>
	<div class="modal fade" id="loginPop" tabindex="-1" role="dialog">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">登陆账号</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="recipient-name" class="control-label">电话号码:</label> <input
								type="text" class="form-control" id="userTel"
								style="width: 250px">
						<p id=errorInfor style=color:red></p>
						</div>
						
						<div class="form-group">
							<label for="recipient-name" class="control-label">密码:</label> <input
								type="password" class="form-control" id="userPassword"
								style="width: 250px">
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">验证码:</label> 
							<input type="text"  class="form-control" id="checkcode" style=width:100px /><br>
						    <img alt="验证码" id="imagecode" src="user/code"/>
						     <a onclick="reCreateCode()">看不清楚</a><br>
						</div>
						<div class="checkbox">
							<label> <input id="rememberPassword" type="checkbox">记住密码</label>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="userLogin()">登陆</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="registerPop" tabindex="-1" role="dialog">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">注册账号</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="recipient-name" class="control-label">用户名:</label> <input
								type="text" class="form-control" id="userNameRegister"
								style="width: 250px">
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">性&nbsp;&nbsp;别:&nbsp;&nbsp;</label> 
							<input type="radio" name="sex" value="1" checked="checked" />男&nbsp;&nbsp;
							 <input type="radio" name="sex" value="0" />女
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">用户电话（<span style=color:#FF0000>必填</span>）:</label> <input
								type="text" class="form-control" id="userTelRegister"
								style=width:250px onclick="isExistUserTel()"/>
						<span id="userTelInfor" style=color:#FF0000></span>
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">用户地址:</label>
							<input type="text" class="form-control" id="userAddress"
								style="width: 250px">
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">密&nbsp;&nbsp;码:（<span style=color:#FF0000>必填</span>）</label> <input
								type="password" class="form-control" id="userPasswordRegister"
								style="width: 250px">
						<span id="userPasswordInfor" style=color:#FF0000></span>
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">确认密码:（<span style=color:#FF0000>必填</span>）</label> <input
								type="password" class="form-control" id="reUserPasswordRegister"
								style="width: 250px">
						<span id="reUserPasswordInfor" style=color:#FF0000></span>
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">支付密码:（<span style=color:#FF0000>必填</span>）</label> <input
								type="password" class="form-control" id="userPayPassword"
								style="width: 250px">
						<span id="userPayPasswordInfor" style=color:#FF0000></span>
						</div>
						<div class="checkbox">
							<label> <input type="checkbox" id="isChooseBox" onclick="isChooseCheckBox()"/>同意<span style=color:#FF0000>点餐系统注册协议</span>（本页右上角）</label>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<span id="isShow"><button type="button" class="btn btn-primary" disabled="disabled" onclick="userRegister()">注册</button></span>
				</div>
			</div>
		</div>
	</div>
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="registerAgreement">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
     <div class="modal-header">
	<center><h4 class="modal-title">点餐系统协议</h4></center>
	</div>
	<div class="modal-body">
	 <p>&nbsp;&nbsp;&nbsp;&nbsp;如果您选择接受本条款，即表示您同意接受协议各项条件的约束。如果您不同意本服务条款，则不能获得使用本协议的权利。您若有违反</p><p>本条款规定，点餐系统有权随时中止或终止您对账号的使用资格并保留追究相关法律责任的权利。本协议条款一旦发生变更，点餐系统将在网</p><p>页上公布修改内容。修改后的服务条款一旦在网页上公布即有效代替原来的服务条款。您可随时登录点餐系统官方网站查阅最新版服务条款。</p>
	<p>一、产品保护条款</p>
	<p>1、点餐系统是归属点餐公司版权所有。点餐系统产品的一切版权以及与点餐系统产品相关的所有信息内容，包括但不限于：文字表述及其组合、图标、图饰、图表、色彩、版面设计、数据、印刷材料、或电子文档等均受著作权法和国际著作权条约以及其他知识产权法律法规的保护。</p>
	<p>2、您须明白，使用本协议产品涉及到互联网服务，可能会受到各个环节不稳定因素的影响。因此服务存在不可抗力、计算机病毒或黑客攻击、系统不稳定、用户所在位置、用户关机以及其他任何技术、互联网络、通信线路原因等造成的服务中断或不能满足用户要求的风险。您须承担以上风险，点餐系统不作担保。</p>
	<p>3、如点餐系统的系统发生故障影响到本协议的正常运行，点餐系统承诺在第一时间内与相关单位配合，及时处理进行修复。但您因此而产生的经济损失，点餐系统不承担责任。此外，点餐系统保留不经事先通知为维修保养、升级或其他目的暂停本协议任何部分的权利。</p>
	<p>二、用户使用须知</p>
	<p>特别提醒您，使用互联网必须遵守国家有关的政策和法律，如刑法、国家安全法、保密法、计算机信息系统安全保护条例等，保护国家利益，保护国家安全，对于违法使用互联网络而引起的一切责任，由您负全部责任。</p>
	<p>1、您保证以真实的身份注册使用点餐系统的产品，向点餐系统所提供的个人身份资料信息真实、完整、有效，依据法律规定和约定对所提供的信息承担相应的法律责任。如果资料发生变化，您应及时更改。点餐系统会及时、有效地提供该项服务。在安全完成本协议的登记程序后，您应维持密码及账号的机密安全。您应对任何人利用您的密码及账号所进行的活动负完全的责任，点餐系统公司无法对非法或未经您授权使用您账号及密码的行为做出甄别，因此点餐系统公司不承担任何责任。</p>
	<p>2、盗取他人号码或利用网络通讯骚扰他人，均属于非法行为。您不得采用测试、欺骗等任何非法手段，盗取其他用户的账号和对他人进行骚扰。</p>
	<p>3、点餐系统产品属于群体类产品，使用点餐系统软件产品服务的用户之间引发的任何纠纷点餐系统公司将不负责任。</p>
	</div>
	</div>
  </div>
</div>
	</c:when>
	<c:otherwise>
	<div class="userTop">
		<a href="choose_store" class="location"><c:out value="${location }"/></a>	
		<c:if test="${user.userSex==0}">
		<a class="userInfor" href="user/userinfor">${user.userName }女士</a>
		</c:if>
		<c:if test="${user.userSex==1}">
		<a class="userInfor" href="user/userinfor">${user.userName }先生</a>
		</c:if>
		<span class="userInfor" style=left:330px>余额：￥${user.userPayable }<a data-toggle="modal" data-target="#recharge" style=color:#FF0000>&nbsp;&nbsp;充值</a></span>
		<c:if test="${user.userPower==0 }">
		<span class="userInfor" style=left:480px>权限：非会员<a data-toggle="modal" data-target="#openVIP" style=color:#FF0000>&nbsp;&nbsp;开通会员</a>（开通会员全店商品享九折优惠）</span>
		</c:if>
		<c:if test="${user.userPower==1 }">
		<span class="userInfor" style=left:480px>权限：会员<a data-toggle="modal" data-target="#openVIP" style=color:#FF0000>&nbsp;&nbsp;续费会员</a>（会员可享全店商品享九折优惠）</span>
		</c:if>
		<a class="userInforRight" href="index" style=right:260px>点餐系统主页</a>
		<a class="userInforRight" href="shopcar">我的购物车</a>
		<a class="exit" href="user/exit">退出</a>
		
	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="openVIP">
  	<div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
     <div class="modal-header">
	<center><h4 class="modal-title">开通会员请扫码付款(9.9/月)</h4></center>
	</div>
	<div class="modal-body">
	<img alt="" src="imgs/pic1.jpg" style=width:270px;height:330px;>
	</div>
	</div>
	</div>
	</div>
	
	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="recharge">
  	<div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
     <div class="modal-header">
	<center><h4 class="modal-title">充值请扫码支付(任意充值金额)</h4></center>
	</div>
	<div class="modal-body">
	<img alt="" src="imgs/pic1.jpg" style=width:270px;height:330px;>
	</div>
	</div>
	</div>
	</div>
	</div>
	
	
	</c:otherwise>
	</c:choose>
</body>
</html>