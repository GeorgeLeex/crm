<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="e" uri="http://org.wangxg/jsp/extl" %>
<%@ page import="com.neuedu.system.tools.ConvertUtils" %>
<%@ page import="java.util.*"  %>
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
			padding-left: 77%;
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
			vertical-align: middle;
			text-align: center;
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
			List<Map<String, Object>> managers = (List<Map<String, Object>>)session.getAttribute("managers");
			if (session.getAttribute("ms") == null) {
				session.setAttribute("ms", "-- 无 --:null"+ ConvertUtils.convertMapListToString(managers, "name", "us_no").toString());
			}
		%>
		<div id="body" class="size">
			<div id="b1">
				<!--<h3>&nbsp;&nbsp;&nbsp;客户流失管理</h3>-->
				<ol class="breadcrumb" style="margin-bottom: 0px;padding-left: 72px;" id="breadcrumb">
					<li><span>客户管理</span></li>
					<li style="color: #269ABC;"><span>客户流失管理</span></li>
				</ol>
			</div>
			<div id="b2">
				<a class="btn btn-xs btn-primary" onclick="query()">查询</a>
			</div>
			<div id="b3">
				<form id="form01" action="/crm/lx_FindAllCustomerLoss.do" method="post" class="form-horizontal">
					<table class="table table-bordered table-hover">
						<tr>
							<td align="center" id="label01">客户</td>
							<td><e:text name="qkh02" placeholder="请输入客户名称" styleClass="form-control"/></td>
							<td align="center" id="label01">客户经理</td>
							<td><e:select value="${sessionScope.ms}" name="qusno" header="true" styleClass="form-control"/></td>
							<td align="center" id="label01">状态</td>
							<td>
								<select class="form-control" name="qkhls06">
									<option value="null">全部</option>
									<option value="0">暂缓流失</option>
									<option value="1">确认流失</option>
								</select>
							</td>
						</tr>
					</table>
				<table class="table table-bordered table-hover">
					<tr>
						<th>编号</th>
						<th>客户</th>
						<th>客户经理</th>
						<th>上次下单时间</th>
						<th>确认流失时间</th>
						<th>状态</th>
						<th class="col-md-2">操作</th>
					</tr>
					<c:choose>
						<c:when test="${!empty rows}">
							<c:forEach var="row" items="${rows}">
								<tr>
									<td>${row.khls_no}</td>
									<td>${row.kh02}</td>
									<td>${row.name}</td>
									<td>${row.khls09}</td>
									<td>${row.khls04}</td>
									<td>${row.khls06}</td>
									<td>
										<c:choose>
											<c:when test="${row.khls06=='暂缓流失'}">
												<a class="btn btn-xs btn-primary" href="/crm/lx_FindOneCustomerLoss.do?khls_no=${row.khls_no}&nowPage=${nowPage}">暂缓流失</a>
												<a class="btn btn-danger btn-xs" href="/crm/lx_FindOneCustomerLoss.do?khls_no=${row.khls_no}&nowPage=${nowPage}&flag=loss">确认流失</a>
											</c:when>
											<c:otherwise>
												&nbsp;
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="8" align="center">
									共${rowCount }条记录,每页<input id="numbers" name="numbers" type="text" size="1" class="input1" value="${numbers}" style="text-align: center;"/>条, 第
									<span id="nowPage">${nowPage}</span>页/ 共<span id="pageCount">${pageCount}</span>页
									<c:choose>
										<c:when test="${nowPage != 1}">
											<button class="btn1" formaction="/crm/lx_FindAllCustomer.do?nowPage=1">第一页</button>
										</c:when>
										<c:otherwise>
											第一页
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${nowPage - 1 >= 1}">
											<button class="btn1" formaction="/crm/lx_FindAllCustomerLoss.do?nowPage=${nowPage - 1}">上一页</button>
										</c:when>
										<c:otherwise>上一页</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${nowPage + 1 <= pageCount}">
											<button class="btn1" formaction="/crm/lx_FindAllCustomerLoss.do?nowPage=${nowPage + 1}">下一页</button>
										</c:when>
										<c:otherwise>下一页</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${nowPage < pageCount}">
											<button class="btn1" formaction="/crm/lx_FindAllCustomerLoss.do?nowPage=${pageCount}">最后一页</button>
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
				form.action = '/crm/lx_FindAllCustomerLoss.do?nowPage=' + page;
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