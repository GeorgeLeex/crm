<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<link rel="stylesheet" href="/crm/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/crm/css/size.css"/>
	<style type="text/css">
		#body {
			background-color: #F1F1F1;
			display: inline-block;
		}
		
		#b2 a {
			font-size: 16px;
				font-family: "微软雅黑";
				font-weight: 100;
		}
		
		#b2 {
			padding-left:77%;
    		margin-top: 30px;
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
		table tr td,th{
			vertical-align: middle;
			text-align: center;
		}
		.input1{
			border-radius: 4px;
		}
	</style>

	<body>
		<div id="body" class="size">
			<div id="b1">
				<!--<h3>&nbsp;&nbsp;&nbsp;客户信息管理>客户信息>历史订单>订单明细</h3>-->
				<ol class="breadcrumb" style="margin-bottom: 0px;padding-left: 72px;" id="breadcrumb">
				<li><span>客户管理</span></li>
				<li><span>客户信息管理</span></li>
				<li><span>客户信息</span></li>
				<li><span>历史订单</span></li>
				<li style="color: #269ABC;"><span>订单明细</span></li>
				</ol>
			</div>
			<div id="b2">
    			<a class="btn btn-xs btn-primary" onclick="javascript:history.back();">返回</a>
			</div>
			<c:set var="order" value="${requestScope.row.ls}"/>
			<c:set var="dds" value="${requestScope.row.dds}"/>
			<div id="b3">
				<form action="#" method="post" class="form-horizontal">
					<table class="table table-bordered table-hover">
						<tr>
							<td class="col-md-2" align="center" id="label01">订单编号</td>
							<td class="col-md-4">${order.ls_no}</td>
							<td class="col-md-2" align="center" id="label01">日期</td>
							<td class="col-md-4">${order.ls04}</td>
						</tr>
						<tr>
							<td align="center" id="label01">送货地址</td>
							<td>${order.ls05}</td>
							<td align="center" id="label01">总金额(元)</td>
							<td>${order.dd07}</td>
						</tr>
						<t>
							<td align="center" id="label01">状态</td>
							<td>${order.jy_name}</td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</form>
				<table class="table table-bordered table-hover">
					<tr>
						<th>商品名称</th>
						<th>数量</th>
						<th>单位</th>
						<th>单价</th>
						<th>金额(元)</th>
					</tr>
					<c:choose>
						<c:when test="${!empty dds}">
							<c:forEach var="dd" items="${dds}">
								<tr>
									<td>${dd.sp08}</td>
									<td>${dd.dd04}</td>
									<td>${dd.sp03}</td>
									<td>${dd.dd06}</td>
									<td>${dd.dd07}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js"></script>

</html>