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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/admin/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="style/admin/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="style/admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="style/admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="style/admin/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>新增商品</title>
<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
	<form class="form form-horizontal" id="form-article-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">(不可更改)*</span>商品编号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${goods.goodsNum}" placeholder="" id="goodsNum" name="">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">商品名字：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${goods.goodsName }" placeholder="" id="goodsName" name="">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">商品价格：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${goods.goodsPrice }" placeholder="" id="goodsPrice" name="">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">商品余量：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${goods.goodsSurplus }" placeholder="" id="goodsSurplus" name="">
			</div>
		</div>


		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">(暂时不可更改)*</span>更改图片：</label>
					<button id="btn-star" class="btn btn-default btn-uploadstar radius ml-10">开始上传</button>
			</div>
		<div class="row cl">
			<div class="formControls col-xs-8 col-sm-9">
				<div class="uploader-list-container"> 
					<div class="queueList">
					</div>
					<div class="statusBar" style="display:none;">
						<div class="progress"> <span class="text">0%</span> <span class="percentage"></span> </div>
						<div class="info"></div>
						<div class="btns">
							<div class="uploadBtn">开始上传</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</form>
		<br/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" onclick="updateGoodsInfor()" class="btn btn-default btn-uploadstar radius ml-10">提交</button> 
</div>


<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="style/admin/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="style/admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="style/admin/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="style/admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="style/admin/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="style/admin/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="style/admin/lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript" src="style/admin/lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script src="style/ordersystem.js"></script>
</body>
</html>