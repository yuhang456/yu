/**
 * 
 */
// 从商品页面把商品添加到购物车
function addgoodscar(goodsNum,user) {
	if(user == null){
	alert("添加购物车失败，你未登录，请登录后再对商品进行操作");
	}
	else{
	$.ajax({
		type : "GET",
		data : {},
		url : "addcar?goodsNum=" + goodsNum,
		datatype : "json",
		success : function(data) {
			if (data.error == null) {
				alert(data.goodsName + "已成功加入购物车");
			} else {
				alert(data.error)
			}
		},
		error : function(XMLHttpRequest) {
			alert(XMLHttpRequest.status);
			alert("添加失败，系统错误");
		},
	});
	}
}

// 在购物车操作商品（增加商品，更新商品购买数量和价格，还有商品的总价格）
function addOneGoodsNum(goodsNum, goodsPrice, goodsBuyNum, obj) {
	var goodsBuyNum = goodsBuyNum + 1;
	$
			.ajax({
				type : "GET",
				data : {},
				url : "shopcar/addGoods?goodsNum=" + goodsNum,
				datatype : "json",
				success : function(data) {
					if (data.error == null) {
						$(obj).parent().next().html(goodsPrice * goodsBuyNum);
						$(obj)
								.parent()
								.html(
										'<button onclick="reduceOneGoodsNum('
												+ goodsNum
												+ ','
												+ goodsPrice
												+ ','
												+ goodsBuyNum
												+ ',this)">-</button><input type="text" value='
												+ goodsBuyNum
												+ ' style="width:30px"/><button id="add" onclick="addOneGoodsNum('
												+ goodsNum + ',' + goodsPrice
												+ ',' + goodsBuyNum
												+ ',this)">+</button>');
						$("#totalGoodsPrice").html(data.totalGoodsPrice);
					} else {
						alert("添加失败，你购买的商品大于商品剩余量");
					}
				},
				error : function(XMLHttpRequest) {
					// alert(XMLHttpRequest.status);
					alert("发生错误");
				},

			});

}

// 在购物车操作商品（减少商品，更新商品购买数量和价格，还有商品的总价格）
function reduceOneGoodsNum(goodsNum, goodsPrice, goodsBuyNum, obj) {
	var goodsBuyNum = goodsBuyNum - 1;
	$
			.ajax({
				type : "GET",
				data : {},
				url : "shopcar/reduceGoods?goodsNum=" + goodsNum,
				datatype : "json",
				success : function(data) {
					if (data.error == null) {
						$(obj).parent().next().html(goodsPrice * goodsBuyNum);
						$(obj)
								.parent()
								.html(
										'<button onclick="reduceOneGoodsNum('
												+ goodsNum
												+ ','
												+ goodsPrice
												+ ','
												+ goodsBuyNum
												+ ',this)">-</button><input type="text" value='
												+ goodsBuyNum
												+ ' style="width:30px"/><button onclick="addOneGoodsNum('
												+ goodsNum + ',' + goodsPrice
												+ ',' + goodsBuyNum
												+ ',this)">+</button>');
						$("#totalGoodsPrice").html(data.totalGoodsPrice);
					} else {
						alert("减少失败，你购买的商品数小于1");
					}
				},
				error : function(XMLHttpRequest) {
					alert("发生错误");
				},

			});

}

// 登陆模块
function userLogin() {
	var isrememberPassword=$("#rememberPassword").is(":checked");
	var userTel = $("#userTel").val();
	var userPassword = $("#userPassword").val();
	var piccode=$("#checkcode").val();
	$.ajax({
		type : "POST",
		data : {
			"userTel" : userTel,
			"userPassword" : userPassword,
			"isrememberPassword":isrememberPassword,
			"piccode":piccode
		},
		url : "user/login",
		dataType : "json",
		success : function(data) {
			if(data.error==="success"){
			alert("用户登陆成功");
			window.location.href("index");
			}
			else{
			alert(data.error);
			}
		},
		error : function(XMLHttpRequest) {
			alert(XMLHttpRequest.status);
			alert("系统错误");
		},

	});
}
//验证是否同意点餐系统协议
function isChooseCheckBox(){
	if($("#isChooseBox").is(":checked")){
		$("#isShow").html('<button type="button" class="btn btn-primary" onclick="userRegister()">注册</button>');
	}
	else{
		$("#isShow").html('<button type="button" class="btn btn-primary" disabled="disabled" onclick="userRegister()">注册</button>');
	}
}

//用户注册
function userRegister(){
	if($("#userTelRegister").val()==""){
		$("#userTelInfor").html("该选项为必填项，不能为空");
	}
	else if(isError==0){
		//使表单无法提交
	}
	else if($("#userPasswordRegister").val()==""){
		$("#userPasswordInfor").html("该选项为必填项，不能为空");
	}
	else if($("#reUserPasswordRegister").val()==""){
		$("#reUserPasswordInfor").html("该选项为必填项，不能为空");
	}
	else if($("#userPasswordRegister").val()!=$("#reUserPasswordRegister").val()){
		$("#reUserPasswordInfor").html("两次输入的密码不一致");
	}
	else if($("#userPayPassword").val()==""){
		$("#userPayPasswordInfor").html("该选项为必填项，不能为空");
	}
	else{
		$.ajax({
			type : "POST",
			data : {
				"userName":$("#userNameRegister").val(),
				"userSex":$('input:radio:checked').val(),
				"userPassword":$("#userPasswordRegister").val(),
				"userTel":$("#userTelRegister").val(),
				"userPayable":0,
				"userAddress":$("#userAddress").val(),
				"userPower":0,
				"userPayPassword":$("#userPayPassword").val(),
				"power":"user"
			},
			url : "user/userRegister",
			dataType : "json",
			success : function(data) {
				alert(data.error);
				window.location.href("index");
			},
			error : function(XMLHttpRequest) {
				//alert(XMLHttpRequest.status);
			 alert("系统错误");
			},

		});
	}
	
}

var isError=0;//将错误信息和提交按钮信息整合，判断是否可以提交表单,1为正确，0为错误
//验证用户电话是否被注册
function isExistUserTel() {
	$("#userTelRegister").keyup(function() {
		$.ajax({
			type : "POST",
			data : {
				"userTel" : $("#userTelRegister").val()
			},
			url : "user/isExistUserTel",
			dataType : "json",
			success : function(data) {
				if(data.error!=""){
					isError=0;
				}
				else{
					isError=1;
				}
				$("#userTelInfor").html(data.error);
			},
			error : function(XMLHttpRequest) {
				//alert(XMLHttpRequest.status);
			 alert("系统错误");
			},

		});
	});
}

//修改用户信息
function informationUpdata(userName,userTel,userAddress){
	$("#information").html('<table class="table table-striped"><tr><td>姓名：</td><td><input type="text" id="userName" class="form-control" style=width:200px value="'+
			userName+'"/></td></tr><tr><td>性别：</td><td><input type="radio" name="sex" value="1" checked="checked" />男<input type="radio" name="sex" value="0" />女</td></tr><tr><td>联系电话：</td><td><fieldset disabled><input type="text" class="form-control" style=width:200px id="userTel" value="'+
			userTel+'"/></fieldset></td></tr><tr><td>我的地址：</td><td><input type="text" class="form-control" style=width:200px id="userAddress" value="'+
			userAddress+'"/></td></tr></table></div>');
	$("#informationButton").html('<button onclick="informationKeep()" class="btn btn-primary btn-sm">保存</button>');
}

//获取修改后的用户信息的值
function informationKeep(){
	$.ajax({
		type:"POST",
		url:"user/userInforUpdata",
		data:{
			"userName":$("#userName").val(),
			"userSex":$("input:radio:checked").val(),
			"userTel":$("#userTel").val(),
			"userAddress":$("#userAddress").val()
		},
		dataType:"json",
		success:function(data){
			alert(data.error);
			window.location.href("/OrderingSystem/user/userinfor");
		},
		error:function(){
			alert("系统错误");
		},
	});
}
//修改登录密码
function updataPassword(userPassword,userTel){
	if($("#checkPassword").val()!=userPassword){
		alert("验证登录密码错误");
	}
	else if($("#newPassword").val()===""){
		alert("修改的新密码不能为空");
	}
	else if($("#newPassword").val()===userPassword){
		alert("修改的新密码不能与最近一次登录密码相同");
	}
	else if($("#newPassword").val()!=$("#againNewPassword").val()){
		alert("两次输入的密码不一致");
	}
	else{
		$.ajax({
			type:"POST",
			data:{
				"userPassword":$("#newPassword").val(),
				"userTel":userTel
			},
			url:"user/updatePassword",
			dataType:"json",
			success:function(data){
				if(data.error==="success"){
				alert("修改成功，请用户重新的登录");
				window.location.href("/OrderingSystem/user/exit");
				}
				else{
					alert("修改失败");
				}
			},
			error:function(){
				alert("系统错误");
			},
		});
	}
}
//修改用户的支付密码
function updataPayPassword(userPayPassword,userTel){
	if($("#checkPayPassword").val()!=userPayPassword){
		alert("验证支付密码错误");
	}
	else if($("#newPayPassword").val()===""){
		alert("修改的支付密码不能为空");
	}
	else if($("#newPayPassword").val()===userPayPassword){
		alert("修改的支付新密码不能与最近一次支付密码相同");
	}
	else if($("#newPayPassword").val()!=$("#againNewPayPassword").val()){
		alert("两次输入的密码不一致");
	}
	else{
		$.ajax({
			type:"POST",
			data:{
				"userPayPassword":$("#newPayPassword").val(),
				"userTel":userTel
			},
			url:"user/updatePayPassword",
			dataType:"json",
			success:function(data){
				if(data.error==="success"){
				alert("修改成功,请牢记支付密码");
				window.location.href("/OrderingSystem/user/userinfor");
				}
				else{
					alert("修改失败");
				}
			},
			error:function(){
				alert("系统错误");
			},
		});
	}
}

//用户提交购物车订单
function commitOrder(userTel,userAddress){
	$.ajax({
		type:"POST",
		url:"shopcar/commitOrder",
		data:{
			"userTel":userTel,
			"userAddress":userAddress
			},
		dataType:"json",
		success:function(data){
			if(data.error==="success"){
				window.location.href("shopcar/orderInfor");
			}
			else{
				alert("提交订单失败");
			}
		},
		error:function(XMLHttpRequest){
			//alert(XMLHttpRequest.status);
			alert("系统错误");
		}
	});
}
//取消用户订单
function cancelMyorder(orderNum){
	var r=confirm("是否取消该订单");
	if (r==true)
	  {
		$.ajax({
			type:"POST",
			url:"user/cancelMyOrder",
			data:{
				"orderNum":orderNum
			},
			dataType:"json",
			success:function(data){
				if(data.error==="success"){
				alert("订单取消成功");
				window.location.href("/OrderingSystem/user/myOrderView?orderNum="+orderNum);
				}
				else{
					alert("订单取消失败");
				}
			},
			error:function(){
				alert("系统错误");
			}
		});
	  }
}
//用户支付密码付款
function payMoney(totalGoodsPrice,userPayable,orderNum,userTel){
	if(totalGoodsPrice>userPayable){
		alert("你的钱包余额不足，请充值后再付款");
	}
	else if($("#getAblePayMoneyPassword").val()===""){
		alert("支付密码为空，请重新输入");
	}
	else{
		$.ajax({
			type:"POST",
			url:"user/ablePayMoney",
			data:{
				"totalGoodsPrice":totalGoodsPrice,
				"orderNum":orderNum,
				"userTel":userTel,
				"userPayPassword":$("#getAblePayMoneyPassword").val()
			},
			dataType:"JSON",
			success:function(data){
				if(data.error==="success"){
					alert("支付成功,祝你用餐愉快");
					window.location.href("/OrderingSystem/user/myOrderView?orderNum="+orderNum);
				}
				else if(data.error==="passwordError"){
					alert("支付密码输入错误");
				}
				else{
					alert("支付失败");
				}
			},
			error:function(XMLHttpRequest){
				alert(XMLHttpRequest.status);
				alert("系统错误");
			}
		});
	}
}
//重新生成验证码
function reCreateCode(){
	$("#imagecode").attr("src","user/code?flag="+Math.random());
}

//验证管理员登陆
function admin_login(){
	if($("#adminName").val()===""){
		$("#login_error").html("用户名不能为空");
	}
	else if($("#adminPassword").val()===""){
		$("#login_error").html("密码不能为空");
	}
	else if($("#checkcode").val()===""){
		$("#login_error").html("验证码不能为空");
	}
	else{
		$.ajax({
			type:"POST",
			url:"checkAdminLogin",
			data:{
				"userTel":$("#adminName").val(),
				"userPassword":$("#adminPassword").val(),
				"checkcode":$("#checkcode").val(),
			},
			dataType:"JSON",
			success:function(data){
				if(data.error==="success"){
					alert("登陆成功");
					window.location.href("admin");
				}
				else{
					alert(data.error);
				}
			},
			error:function(XMLHttpRequest){
				alert(XMLHttpRequest.status);
				alert("系统错误");
			}
		});
		
	}
}
//dataTables插件实现前端分页，模糊查询等等操作
$(document).ready(function() {
	$('#goodsIndex').dataTable( {
	"oLanguage": {
	"sLengthMenu": "每页显示 _MENU_ 条记录",
	"sZeroRecords": "抱歉， 没有找到",
	"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
	"sInfoEmpty": "没有数据",
	"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
	"sSearch": "搜索:" ,//右上角的搜索文本，可以写html标签
	"oPaginate": {
	"sFirst": "首页",
	"sPrevious": "前一页",
	"sNext": "后一页",
	"sLast": "尾页",
	},
	"sZeroRecords": "没有检索到数据",
	"sProcessing": "<img src='./loading.gif' />"
	},
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"aLengthMenu": [[5, 10, 15, 20], [5, 10, 15, 20]],
	"bStateSave": true,//状态保存
	
	} );
	} );

$(document).ready(function() {
	$('#carIndex').dataTable( {
	"oLanguage": {
	"sLengthMenu": "每页显示 _MENU_ 条记录",
	"sZeroRecords": "抱歉， 没有找到",
	"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
	"sInfoEmpty": "没有数据",
	"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
	"sSearch": "搜索:" ,//右上角的搜索文本，可以写html标签
	"oPaginate": {
	"sFirst": "首页",
	"sPrevious": "前一页",
	"sNext": "后一页",
	"sLast": "尾页",
	},
	"sZeroRecords": "没有检索到数据",
	"sProcessing": "<img src='./loading.gif' />"
	},
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"aLengthMenu": [[5, 10, 15, 20], [5, 10, 15, 20]],
	"bStateSave": true,//状态保存
	
	} );
	} );

/*
*以下是点餐系统管理员操作
*
*/

function updateGoodsInfor(){
	$.ajax({
		type:"POST",
		data:{
		goodsNum:$("#goodsNum").val(),
		goodsName:$("#goodsName").val(),
		goodsPrice:$("#goodsPrice").val(),
		goodsSurplus:$("#goodsSurplus").val(),
		},
		url:"admin/admin_updategoods",
		dataType:"TEXT",
		success:function(data){
		if(data>=1){
			alert("操作成功");
		}
		else{
			alert("操作失败");
		}
		},
		error:function(XMLHttpRequest){
			alert(XMLHttpRequest.status);
		},
	});
	
	
}
