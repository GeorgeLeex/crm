<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			padding-left: 74%;
			margin-top: 30px;
		}
		
		#b3 {
			width: 1000px;
			margin: 10px auto;
		}
		
		.xx {
			color: red;
		}
		
		#label01 {
			vertical-align: middle;
		}
		
		.th1 {
			text-align: center;
		}
		
		.input1 {
			border-radius: 4px;
		}
		
		#breadcrumb span {
			font-size: large;
		}
		
		#b1 {}
	</style>

	<body>
		<div id="body" class="size">
			<div id="b1">
				<!--<h3>&nbsp;&nbsp;&nbsp;客户管理信息>客户信息>联系人</h3>-->
				<ol class="breadcrumb" style="margin-bottom: 0px;padding-left: 72px;" id="breadcrumb">
					<li><span>客户管理</span></li>
					<li><span>客户信息管理</span></li>
					<li><span>客户信息</span></li>
					<li style="color: #269ABC;"><span>联系人</span></li>
				</ol>
			</div>
			<div id="b2">
				<a class="btn btn-xs btn-primary" href="/crm/page/kh2.jsp?kh_no=${empty rows? kh_no : rows[0].kh_no}">新 建</a>
				<a class="btn btn-xs btn-primary" onclick="javascript:history.back();">返 回</a>
			</div>
			<div id="b3">
				<form action="#" method="post" class="form-horizontal">
					<table class="table table-bordered table-hover">
						<tr>
							<td class="col-md-2" align="center" id="label01">客户编号</td>
							<td class="col-md-4" align="center" id="label01">${rows[0].kh_no}</td>
							<td class="col-md-2" align="center" id="label01">客户名称</td>
							<td class="col-md-4" align="center" id="label01">${rows[0].kh02}</td>
						</tr>
					</table>
				</form>
				<table class="table table-bordered table-hover">
					<tr>
						<th class="th1">姓名</th>
						<th class="th1">性别</th>
						<th class="th1">职位</th>
						<th class="th1">办公电话</th>
						<th class="th1">手机</th>
						<th class="th1">备注</th>
						<th class="th1">操作</th>
					</tr>
					<c:choose>
						<c:when test="${!empty rows[0].lxr_no}">
							<c:forEach var="row" items="${rows}">
								<tr>
									<td align="center" id="lxr03" title="lxr03">${row.lxr03}</td>
									<td align="center" id="lxr04">${row.lxr04}</td>
									<td align="center" id="lxr05">${row.lxr05}</td>
									<td align="center" id="lxr06">${row.lxr06}</td>
									<td align="center" id="lxr07">${row.lxr07}</td>
									<td align="center" id="lxr08">${row.lxr08}</td>
									<td align="center" id="label01" class="col-md-1">
										<a href="/crm/lx_FindOneContact.do?lxr_no=${row.lxr_no}&kh_no=${row.kh_no}"><span class="glyphicon glyphicon-pencil"></span></a>
										&nbsp;&nbsp;&nbsp;
										<a href="/crm/lx_DeleteOneContact.do?lxr_no=${row.lxr_no}&kh_no=${row.kh_no}"><span class="glyphicon glyphicon-remove"></span></a>
									</td>
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
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
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
	<script type="text/javascript">
		$(document).ready(function(){
			var msg = '<%=request.getAttribute("msg")%>';
			if (msg != null && msg != 'null') {
				alert(msg);
			}
		});
	</script>
</html>