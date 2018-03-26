<%@page import="com.neuedu.system.tools.ConvertUtils"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="e" uri="http://org.wangxg/jsp/extl" %>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map,java.util.HashMap" 
	import="com.neuedu.system.tools.ConvertUtils" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<link rel="stylesheet" href="/crm/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/crm/css/size.css" />
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
		
		#breadcrumb span {
			font-size: large;
		}
		
		table tr td,
		th {
			text-align: center;
			vertical-align: middle;
		}
		.input1 {
			border-radius: 4px;
		}
		.btn1 {  
	        color: #337ab7;      
	        background-color: #F1F1F1;  
	        border: 0px none;  //去边框  
	        font-family: "宋体";  
	        font-size: 15px;  
	        text-decoration:none;  //加下划线  
    	}  
    	.btn1:hover{  
	        border: none;  
	        cursor: hand;  
	        cursor: pointer;  
	        text-decoration:underline;  //加下划线  
    	}  
    	.btn1:focus {   
        	outline: none;    //去边框  
    	}
	</style>

	<body>
	<%
		List<Map<String, Object>> list = (List<Map<String, Object>>)session.getAttribute("fs");
		if (null == session.getAttribute("fsStr")) {
			//session.setAttribute("fsStr", " ==不限== :null" + ConvertUtils.convertMapListToString(list, "mname", "mno"));
			session.setAttribute("fsStr", ConvertUtils.convertMapListToString(list, "mname", "mno"));
		}
	%>
		<div id="body" class="size">
			<div id="b1">
				<!--<h3>&nbsp;&nbsp;&nbsp;数据字典管理 </h3>-->
				<ol class="breadcrumb" style="margin-bottom: 0px;padding-left: 72px;" id="breadcrumb">
					<li><span>权限管理</span></li>
					<li style="color: #269ABC;"><span>用户权限管理</span></li>
				</ol>
			</div>
			<div id="b2">
				<a class="btn btn-xs btn-primary" href="/crm/page/us4.jsp?nowPage=${nowPage}">新 建</a>
				<a class="btn btn-xs btn-primary" onclick="query()">查 询</a>
			</div>
			<div id="b3">
				<form id="form01" action="/crm/lx_FindAllAccess.do" method="post" class="form-horizontal">
					<table class="table table-bordered table-hover">
						<tr>
							<td align="center" id="label01">二级菜单</td>
							<td><e:text name="qsname" placeholder="请输入二级菜单名" styleClass="form-control"/></td>
							<td align="center" id="label01">一级菜单</td>
							<td><e:select name="qfno" value="${sessionScope.fsStr}" header="true" styleClass="form-control"/></td>
						</tr>
					</table>
				<table class="table table-bordered table-hover">
					<tr>
						<td align="center" id="label01">编号</td>
						<td align="center" id="label01">二级菜单</td>
						<td align="center" id="label01">一级菜单</td>
						<td align="center" id="label01">可访问角色</td>
						<td align="center" id="label01">访问路径</td>
						<td align="center" id="label01">操作</td>
					</tr>
					<c:choose>
						<c:when test="${!empty rows}">
							<c:forEach var="row" items="${rows}">
								<tr>
									<td class="com-md-2">${row.mno}</td>
									<td class="com-md-3">${row.sname}</td>
									<td class="com-md-3">${row.fname}</td>
									<td class="com-md-2">
										<c:choose>
											<c:when test="${fn:contains(row.ident, ',')}">
												<c:set var="idents" value="${fn:split(row.ident, ',')}"/>
												<c:forEach var="i" items="${idents}">
													[${i}]&nbsp;
												</c:forEach>
											</c:when>
											<c:otherwise>
												[${row.ident}]
											</c:otherwise>
										</c:choose>
									</td>
									<td class="com-md-1">${row.url}</td>
									<td class="col-md-1">
										<a href="/crm/lx_FindOneAccess.do?mno=${row.mno}&nowPage=${nowPage}"><span class="glyphicon glyphicon-pencil"></span></a>
										&nbsp;&nbsp;&nbsp;
										<a href="/crm/lx_DeleteOneAccess.do?mno=${row.mno}"><span class="glyphicon glyphicon-remove"></span></a>
									</td>
								</tr>
							</c:forEach>
								<tr>
									<td colspan="8" align="center">
										共${rowCount }条记录,每页<input id="numbers" name="numbers" type="text" size="1" class="input1" value="${numbers}" style="text-align: center;"/>条, 第
										<span id="nowPage">${nowPage}</span>页/ 共<span id="pageCount">${pageCount}</span>页
										<c:choose>
											<c:when test="${nowPage != 1}">
												<button class="btn1" formaction="/crm/lx_FindAllAccess.do?nowPage=1">第一页</button>
											</c:when>
											<c:otherwise>
												第一页												
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${nowPage - 1 >= 1}">
												<button class="btn1" formaction="/crm/lx_FindAllAccess.do?nowPage=${nowPage - 1}">上一页</button>
											</c:when>
											<c:otherwise>上一页</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${nowPage + 1 <= pageCount}">
												<button class="btn1" formaction="/crm/lx_FindAllAccess.do?nowPage=${nowPage + 1}">下一页</button>
											</c:when>
											<c:otherwise>下一页</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${nowPage < pageCount}">
												<button class="btn1" formaction="/crm/lx_FindAllAccess.do?nowPage=${pageCount}">最后一页</button>
											</c:when>
											<c:otherwise>最后一页</c:otherwise>
										</c:choose>
										转到<input id="page" type="text" size="1" class="input1" style="text-align: center;"/> 页
										<a class="input1 btn btn-sm btn-default" style="margin-bottom:5px;" onclick="jump()">GO</a>
									</td>
								</tr>
						</c:when>
						<c:otherwise>
							<tr>
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
							</tr>
							<tr>
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
				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		function query(){
			document.getElementById("form01").submit();
		}
	</script>
	<script type="text/javascript">
		function jump(){
			var nowPage = document.getElementById("nowPage").innerText;
			var pageCount = document.getElementById("pageCount").innerText;
			var page = document.getElementById("page").value;
			if ((page < 1) || (page > pageCount)) {
				alert('输入有误!');
			} else {
				var form = document.getElementById("form01");
				form.action = '/crm/lx_FindAllAccess.do?nowPage=' + page;
				form.submit();
			}
		}
	</script>
	<script type="text/javascript">
		$(document).ready(function(){
			var msg = '<%=request.getAttribute("msg")%>';
			if (msg != null && msg != 'null') {
				alert(msg);
			}
		});
	</script>
</html>