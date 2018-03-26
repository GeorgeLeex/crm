<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e" %>
<%@ page import="java.util.*"  %>
<%@ page import="com.neuedu.system.tools.ConvertUtils" %>
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
	
	<%
		List<Map<String, Object>> managers = (List<Map<String, Object>>)session.getAttribute("managers");
		List<Map<String, Object>> djList = (List<Map<String, Object>>)session.getAttribute("djs");
		if (session.getAttribute("ms") == null) {
			session.setAttribute("ms", ConvertUtils.convertMapListToString(managers, "name", "us_no").toString());
		}
		if (session.getAttribute("djStr") == null) {
			session.setAttribute("djStr", ConvertUtils.convertMapListToString(djList, "zd_name", "zd_value").toString());
		}
	%>
	<body>
		<div id="body" class="size">
			<div id="b1">
				<!--<h3>&nbsp;&nbsp;&nbsp;客户流失管理</h3>-->
				<ol class="breadcrumb" style="margin-bottom: 0px;padding-left: 72px;" id="breadcrumb">
					<li><span>客户管理</span></li>
					<li style="color: #269ABC;"><span>客户信息管理</span></li>
				</ol>
			</div>
			<div id="b2">
				<a class="btn btn-xs btn-primary" href="/crm/page/kh1_4.jsp?nowPage=${nowPage}">新 建</a>
				<a class="btn btn-xs btn-primary" onclick="query()">查 询</a>
			</div>
			<form id="form01" action="" method="post">
			<div id="b3">
				<table class="table table-bordered">
					<tr>
						<td id="label01" class="col-md-2">客户名称</td>
						<td class="col-md-4">
							<e:text name="qkh02" styleClass="form-control" placeholder="请输入客户名称"/>
						</td>
						<td id="label01" class="col-md-2">法人</td>
						<td class="col-md-4">
							<e:text name="qkh14" styleClass="form-control" placeholder="请输入法人姓名"/>
						</td>
					</tr>
					<tr>
						<td id="label01" class="col-md-2">客户经理</td>
						<td class="col-md-4">
							<e:select value="${sessionScope.ms}" name="qus_no" styleClass="form-control" header="true"/>
						</td>
						<td id="label01" class="col-md-2">客户等级</td>
						<td class="col-md-4">
							<e:select value="${sessionScope.djStr}" name="qdj_no" styleClass="form-control" header="true"/>
						</td>
					</tr>
				</table>
				</form>
				
				<table class="table table-bordered">
					<tr>
						<th>编号</th>
						<th>客户名称</th>
						<th>客户经理</th>
						<th>客户等级</th>
						<th>电话</th>
						<th>法人</th>
						<th>开户银行</th>
						<th class="col-md-1">操作</th>
					</tr>
					<c:choose>
						<c:when test="${!empty rows}">
							<c:forEach var="row" items="${rows}">
								<tr>
									<td>${row.kh_no}</td>
									<td>${row.kh02}</td>
									<td>${row.name}</td>
									<td>${row.dj_name}</td>
									<td>${row.kh10}</td>
									<td>${row.kh14}</td>
									<td>${row.kh17}</td>
									<td>
										<a href="/crm/lx_FindOneCustomer.do?kh_no=${row.kh_no}&nowPage=${nowPage}"><span class="glyphicon glyphicon-pencil"></span></a>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="/crm/lx_DeleteOneCustomer.do?kh_no=${row.kh_no}"><span class="glyphicon glyphicon-remove"></span></a>
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
											<button class="btn1" formaction="/crm/lx_FindAllCustomer.do?nowPage=${nowPage - 1}">上一页</button>
										</c:when>
										<c:otherwise>上一页</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${nowPage + 1 <= pageCount}">
											<button class="btn1" formaction="/crm/lx_FindAllCustomer.do?nowPage=${nowPage + 1}">下一页</button>
										</c:when>
										<c:otherwise>下一页</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${nowPage < pageCount}">
											<button class="btn1" formaction="/crm/lx_FindAllCustomer.do?nowPage=${pageCount}">最后一页</button>
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
		function query(){
			var form = document.getElementById("form01");
			form.action = '/crm/lx_FindAllCustomer.do?nowPage=1';
			form.submit();
		}
	</script>
	<script type="text/javascript">
		function jump(){
			var nowPage = document.getElementById("nowPage").innerText;
			var pageCount = document.getElementById("pageCount").innerText;
			var page = document.getElementById("page").value;
			if (page < 1 || page > pageCount ) {
				alert('输入有误!');
			} else {
				var form = document.getElementById("form01");
				form.action = '/crm/lx_FindAllCustomer.do?nowPage=' + page;
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