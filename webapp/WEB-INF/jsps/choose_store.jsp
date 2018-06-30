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
<script src="http://apps.bdimg.com/libs/jquery/1.11.1/jquery.js"></script>
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/i18n/defaults-*.min.js"></script>
<script src="style/ordersystem.js"></script>
<link rel="stylesheet" href="style/ordersystem.css">
<base href="<%=basePath%>>">
<title>选择商铺</title>
</head>
<body>
<%@include file="user.jsp"%>
<br><br><br><br><br><br>
<center>
<label style="font-size:18px;">客官您好，你当前位置在<a data-toggle="modal" data-target="#checkCity"><c:out value="${city }"/>市</a>，为了更好的服务，请选择以下信息</label><br/><br/>
<div class="modal fade" id="checkCity" tabindex="-1" role="dialog">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">修改当前位置</h4>
				</div>
				<div class="modal-body">
				<div class="form-group">
							<label for="recipient-name" class="control-label">请输入修改的市区名称:</label> <input
								type="text" class="form-control" id="userTel"
								style="width: 250px">
						</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="infor()">修改</button>
				</div>
				</div>
				</div>
				</div>
<select name="" id="country" onchange="country()" class="selectpicker">
<option value="0">请选择地区</option>
<c:forEach items="${country}" var="country">

<option value="${country.countryId}"><c:out value="${country.countryName}"/></option>
</c:forEach>
</select>
<select name="" id="store" onchange="store()" class="selectpicker">
<option value="0">请选择店铺</option>
</select>
<br><br><br>
<span style="color:red">注：若未选择店铺，该系统默认从总部（龙泉驿区店）订购</span>
<br><br>
<a href="index">不需要，直接去点餐</a>
</center>

<script type="text/javascript">
$('.selectpicker').selectpicker({
	  style: 'btn-info',
	  size: 4
	});

function country(){
	if($("#country").val()==='0'){
	$("#store").empty();
    $("#store").append("<option>请选择店铺</option>");
    $('#store').selectpicker('refresh'); 
	}
	else{
		$.ajax({
			type:"POST",
			url:"chooseStore",
			data:{
				"countryId":$("#country").val()
			},
			dataType:"JSON",
			success:function(data){
				var str="<option>请选择店铺</option>";
				var json = eval(data);
				$.each(json, function (index) {
                    //循环获取数据    
                    str += "<option value="+json[index].storeName+">" + json[index].storeName + "</option>";
                }); 
				$("#store").empty();
				$("#store").append(str);
				$('#store').selectpicker('refresh'); 
			},
			error:function(HttpRequest){
				alert(HttpRequest.status);
			},
		});
	}

}

function store(){
	if($("#store").val()==='请选择店铺'){
		alert("请选择店铺");
	}
	else{
		window.location.href("saveStore?store="+$("#store").val());
	}
	
}

function infor(){
	alert("修改错误，输入的位置不存在");
}

</script>
</body>
</html>