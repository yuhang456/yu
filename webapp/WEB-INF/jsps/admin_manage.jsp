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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="style/admin/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="style/admin/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="style/admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="style/admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="style/admin/static/h-ui.admin/css/style.css" />
<link rel="stylesheet" href="style/ordersystem.css">
<title>商品管理</title>
</head>
<body>
	<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span> 商品管理<span class="c-gray en">&gt;</span>
	商品管理 <a class="btn btn-success radius r"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="page-container">
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a> <a class="btn btn-primary radius" data-title="添加商品"
				data-href="admin/admin_addgoods" onclick="Hui_admin_tab(this)"
				href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加商品</a></span>
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="" value=""></th>
						<th>商品展示</th>
						<th>商品编号</th>
						<th>商品名称</th>
						<th>商品单价</th>
						<th>商品余量</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${goods }" var="goods">
						<tr class="text-c">
							<td><input type="checkbox" value="" name=""></td>
							<td><img class="imgshow" src="${goods.goodsPicture}" /></td>
							<td><c:out value="${goods.goodsNum }"></c:out></td>
							<td><c:out value="${goods.goodsName }"></c:out></td>
							<td><c:out value="${goods.goodsPrice }"></c:out></td>
							<td><c:out value="${goods.goodsSurplus }"></c:out></td>
							<td class="f-14 td-manage"><a style="text-decoration: none"
								class="ml-5" href='admin/admin_addgoods?goodsNum=${goods.goodsNum }&goodsName=${goods.goodsName }&goodsPrice=${goods.goodsPrice}&goodsSurplus=${goods.goodsSurplus}'
								title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a
								style="text-decoration: none" class="ml-5"
								onClick="article_del(this,'${goods.goodsNum }')" href="javascript:;"
								title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="style/admin/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="style/admin/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="style/admin/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="style/admin/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="style/admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="style/admin/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="style/admin/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript">
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"aLengthMenu": [[4, 8, 12, 16], [4, 8, 12, 16]],
	"bStateSave": true,//状态保存
	"pading":false,
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,5]}// 不参与排序的列
	]
});
/*商品修改*/
function article_edit(a){
	alert(a);
	$.ajax({
		type:"POST",
		url:"admin/admin_addgoods",
		data:{
			"goodsNum":goodsNum,
			"goodsName":goodsName,
			"goodsPrice":goodsPrice,
			"goodsSurplus":goodsSurplus
		},
		dataType:"JSON",
		success:function(data){
			alert("aa");
		},
		error : function(XMLHttpRequest) {
			alert(XMLHttpRequest.status);
			alert("添加失败，系统错误");
		},
	});
	
}
/*商品添加*/
function article_add(title,url,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*商品删除*/
function article_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'GET',
			url: 'admin/admin_delgoods?goodsNum='+id,
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}


</script>
</body>
</html>