<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="/crm/bootstrap/css/bootstrap.min.css" />
		<style type="text/css">
			@import url("/crm/css/right_page.css")
		</style>
		<link rel="stylesheet" href="/crm/css/size.css" />
	</head>
<!-- ********     2018.3.10  sql语句错误 去YxSelectServlet.java-->
	<body>
		<div id="right" class="size">
			<ol class="breadcrumb" id="breadcrumb">
				<li><span>营销管理</span></li>
				<li style="color: #269ABC;"><span>客户开发计划</span></li>
			</ol>
			<form action="" method="post">
				<table id="table02" class="table table-bordered table-hover">
					<tr>
						<th>编号</th>
						<th>客户名称</th>
						<th>概要</th>
						<th>联系人</th>
						<th>联系人电话</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
					<c:forEach var="ibm" items="${rows }">
					<tr>
						<td>${ibm.xs_no }</td>
						<td>${ibm.xs03 }</td>
						<td>${ibm.xs02 }</td>
						<td>${ibm.xs06 }</td>
						<td>${ibm.xs07 }</td>
						<td>${ibm.xs10 }</td>
						<td class="col-md-2 operation1">
							<a href="/crm/dgp_kfUpdateServlet.do?xs_no=${ibm.xs_no }">
								<span class="glyphicon glyphicon-wrench span1"></span>
							</a>
							<a href="">
								<span class="glyphicon glyphicon-thumbs-up span1"></span>
							</a>
							<a href="">
								<span class="glyphicon glyphicon-thumbs-down span1"></span>
							</a>
						</td>
					</tr>
					</c:forEach>
					<tr>
						<td colspan="7">
							<p align="center" style="padding-top: 10px;">
								共59条记录 每页<input type="text" size="1" class="input1" />条 第
								<input type="text" size="1" class="input1" />页/共5页
								<a href="">第一页</a>
								<a href="">上一页</a>
								<a href="">下一页</a>
								<a href="">最后一页</a> 转到<input class="input1" type="text" size="1" />页
								<button size="1" class="btn btn-success btn-sm" class="input1">GO</button>
							</p>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
	<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js"></script>

</html>