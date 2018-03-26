<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/crm/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/crm/css/size.css"/>
<title>title</title>
<style type="text/css">
	*{
		margin: 0;
		padding: 0;
	}
	#body {
		background-color: #F1F1F1;
		display: inline-block;
	}
	#b1{
		margin-left: 42px;
		display: inline-block;
	}
	#b2 a {
		font-size: 16px;
		font-family: "微软雅黑";
		font-weight: 100;
	}
	
	#b2 {
		padding-left: 77%;
		display: inline-block;
	}
	
	#b3 {
	width: 1000px;
	margin: 10px auto;
	}
	
	.xx {
		color: red;
	}
	#label01{
		vertical-align: middle;
	}
	#breadcrumb span{
		font-size: large;
	}
	tr,td,th{
		text-align:center;
	}
</style>
</head>
<body>
<div id="body" class="size">
	<div id="b1">
		<!--<h3 style="margin-top: 10px;">&nbsp;&nbsp;&nbsp;客户信息管理 > 客户信息</h3>-->
		<ol class="breadcrumb" style="margin-bottom: 0px;margin-left: 32px;" id="breadcrumb">
			<li><span>基础数据</span></li>
			<li><span>订单管理</span></li>
			<li style="color: #269ABC;"><span>订单明细查看</span></li>
		</ol>
	</div>
	<div id="b2">
		<input type="button" value="返回" onclick="javascript:location.href='/crm/lx_FindAllOrders.do?nowPage=${nowPage}'" class="btn btn-sm btn-primary" />
	</div>
	<div id="b3">
		<table class="table table-bordered table-hover">
			<tr>
				<td class="col-md-2">客户名称</td>
				<td class="col-md-4">${ls.kh02}</td>
				<td class="col-md-2">电话号码</td>
				<td class="col-md-4">${ls.kh10}</td>
			</tr>
			<tr>
				<td class="col-md-2">日期</td>
				<td class="col-md-4">${ls.ls04}</td>
				<td class="col-md-2">送货地址</td>
				<td class="col-md-4">${ls.ls05}</td>
			</tr>
			<tr>
				<td class="col-md-2">总金额</td>
				<td class="col-md-4">${ls.ls06}</td>
				<td class="col-md-2">订单状态</td>
				<td class="col-md-4">${ls.jy_name}</td>
			</tr>
		</table>
		<table class="table table-bordered table-hover">
			<tr>
				<th>商品名</th>
				<th>单价</th>
				<th>数量</th>
				<th>金额</th>
			</tr>
			<c:forEach var="dd" items="${dds}">
				<tr>
					<td>${dd.sp08}</td>
					<td>${dd.dd06}</td>
					<td>${dd.dd04}</td>
					<td>${dd.dd07}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>
<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js"></script>
</html>